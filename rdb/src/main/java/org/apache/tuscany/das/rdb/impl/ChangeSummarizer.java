/**
*
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package org.apache.tuscany.das.rdb.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.Key;
import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.QualifiedColumn;
import org.apache.tuscany.das.rdb.util.DebugUtil;
import org.apache.tuscany.sdo.impl.AttributeImpl;
import org.apache.tuscany.sdo.impl.ChangeSummaryImpl;
import org.apache.tuscany.sdo.impl.ChangeSummarySettingImpl;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataObject;
import commonj.sdo.Type;

public class ChangeSummarizer {

	private static final boolean debug = false;

	private Changes changes = new Changes();

	private FactoryRegistry registry;

	private MappingWrapper mapping = new MappingWrapper();

	private ConnectionImpl connection;

	private HashMap generatedKeys = new HashMap();

	public ChangeSummarizer() {
		// Empty Constructor
	}

	public Changes loadChanges(DataObject root) {
		ChangeSummary changeSummary = root.getDataGraph().getChangeSummary();
		if (changeSummary.isLogging())
			((ChangeSummaryImpl) changeSummary).summarize();

		List changedObjects = changeSummary.getChangedDataObjects();
		DebugUtil.debugln(getClass(), debug,
				"List of changed objects contains " + changedObjects.size()
						+ " object(s)");

		changes.setInsertOrder(mapping.getInsertOrder());
		changes.setDeleteOrder(mapping.getDeleteOrder());

		Iterator i = changedObjects.iterator();
		while (i.hasNext()) {
			DataObject o = (DataObject) i.next();
			
			if (!(o.equals(root))) {
				createChange(changeSummary, o);
			}
		}

		return changes;
	}

	public void createChange(ChangeSummary changeSummary,
			DataObject changedObject) {

		if (changeSummary.isCreated(changedObject)) {
			DebugUtil.debugln(getClass(), debug, "Change is a create");
			if (!changeSummary.isDeleted(changedObject)) {
				ChangeFactory factory = getRegistry().getFactory(
						changedObject.getType());
				String propagatedID = (String) generatedKeys.get(changedObject
						.getType().getName());
				changes.addInsert(factory.createInsertOperation(changedObject,
						propagatedID));
			}
		} else if (changeSummary.isDeleted(changedObject)) {
			ChangeFactory factory = getRegistry().getFactory(
					changedObject.getType());
			DebugUtil.debugln(getClass(), debug, "Change is a delete");
			changes.addDelete(factory.createDeleteOperation(changedObject));
		} else {
			// bumpCollisionField(changedObject);
			DebugUtil.debugln(getClass(), debug, "Change is a modify");
			List attrList = changeSummary.getOldValues(changedObject);
			if (hasAttributeChange(attrList)) {
				ChangeFactory factory = getRegistry().getFactory(
						changedObject.getType());
				DebugUtil.debugln(getClass(), debug, "Attribute Change for "
						+ changedObject.getType().getName());
				String propagatedID = (String) generatedKeys.get(changedObject
						.getType().getName());
				changes.addUpdate(factory.createUpdateOperation(changedObject,
						propagatedID));
			} else {
				// Reference change
				List values = changeSummary.getOldValues(changedObject);
				Iterator i = values.iterator();
				while (i.hasNext()) {
					ChangeSummarySettingImpl setting = (ChangeSummarySettingImpl) i
							.next();

					if (setting.getFeature() instanceof EReference) {
						DebugUtil.debugln(getClass(), debug,
								"Reference change for "
										+ changedObject.getType().getName());

						EReference ref = (EReference) setting.getFeature();

						DebugUtil.debugln(getClass(), debug, ref.getName());
						if (ref.getEOpposite().getUpperBound() == ETypedElement.UNBOUNDED_MULTIPLICITY) {
							ChangeFactory factory = getRegistry().getFactory(
									changedObject.getType());
							changes.addUpdate(factory
									.createUpdateOperation(changedObject));
						}

					}
				}
			}
		}

	}

	private boolean hasAttributeChange(List theChanges) {
		Iterator i = theChanges.iterator();
		while (i.hasNext()) {
			ChangeSummarySettingImpl setting = (ChangeSummarySettingImpl) i.next();
			if (setting.getFeature() instanceof AttributeImpl)
				return true;
		}
		return false;
	}

	public void addCreateCommand(Type type, Command cmd) {
		ChangeFactory cf = getRegistry().getFactory(type);
		cf.setCreateCommand((InsertCommandImpl) cmd);
	}

	public void addUpdateCommand(Type type, Command cmd) {
		ChangeFactory cf = getRegistry().getFactory(type);
		cf.setUpdateCommand((WriteCommandImpl) cmd);
	}

	public void addDeleteCommand(Type type, Command cmd) {
		ChangeFactory cf = getRegistry().getFactory(type);
		cf.setDeleteCommand((WriteCommandImpl) cmd);

	}

	private FactoryRegistry getRegistry() {
		if (this.registry == null) {
			this.registry = new FactoryRegistry(mapping, connection);
		}
		return this.registry;
	}

	public void setConnection(ConnectionImpl connection) {
		this.connection = connection;
	}

	public void setMapping(Config map) {
		this.mapping = new MappingWrapper(map);
		Iterator i = mapping.getConfig().getTable().iterator();
		while (i.hasNext()) {
			Table t = (Table) i.next();
			Iterator columns = t.getColumn().iterator();
			while ( columns.hasNext()) {
				Column c = (Column) columns.next();
				if ( c.isPrimaryKey() && c.isGenerated()) {
					DebugUtil.debugln(getClass(), debug, "adding generated key "
							+ t.getName() + "."
							+ c.getName());
					generatedKeys.put(t.getName(), c.getName());
				}
			}
		}
	}

	public void addRelationship(String parentName, String childName) {
		mapping.addRelationship(parentName, childName);
	}

	public void addPrimaryKey(String columnName) {
		mapping.addPrimaryKey(columnName);
	}

	public void addCollisionColumn(String columnName) {
		mapping.addCollisionColumn(columnName);
	}

	public void addPrimarykey(Key key) {
		mapping.addPrimaryKey(key);
	}

	public void addGeneratedPrimaryKey(String columnName) {
		QualifiedColumn col = new QualifiedColumn(columnName);
		generatedKeys.put(col.getTableName(), col.getColumnName());
		mapping.addGeneratedPrimaryKey(columnName);
	}

	public void addConverter(String name, String converterName) {
		mapping.addConverter(name, converterName);
	}

}
