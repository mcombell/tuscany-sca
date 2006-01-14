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
package org.apache.tuscany.das.rdb.generator.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.tuscany.das.rdb.Converter;
import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.RelationshipWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.impl.OptimisticWriteCommandImpl;
import org.apache.tuscany.das.rdb.impl.ParameterImpl;
import org.apache.tuscany.das.rdb.impl.UpdateCommandImpl;
import org.apache.tuscany.das.rdb.impl.WriteCommandImpl;
import org.apache.tuscany.das.rdb.util.DebugUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.sdo.EChangeSummarySetting;
import org.eclipse.emf.ecore.sdo.EProperty;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

public class UpdateGenerator {

	private boolean debug = false;

	public static UpdateGenerator instance = new UpdateGenerator();

	public UpdateGenerator() {
		super();
	}

	public WriteCommandImpl getUpdateCommand(MappingWrapper mapping, DataObject changedObject, Table table) {
		ArrayList parameters = new ArrayList();
		Type type = changedObject.getType();
		TableWrapper t = new TableWrapper(table);
		StringBuffer statement = new StringBuffer("update ");
		statement.append(table.getName());
		statement.append(" set ");

		
		ChangeSummary summary = changedObject.getDataGraph().getChangeSummary();
		Iterator i = getChangedFields(mapping, summary, changedObject).iterator();

		while (i.hasNext()) {
			EAttribute attr = (EAttribute) i.next();
			Column c = t.getColumnByPropertyName(attr.getName());
			if ((c != null) && (c.isCollision() || c.isPrimaryKey())) {
				// get rid of comma if OCC or PK is last field
				if (!i.hasNext()) {
					statement
							.delete(statement.length() - 2, statement.length());
				}
			} else {
				parameters.add(type.getProperty(attr.getName()));
				statement.append(c == null ? attr.getName() : c.getName());
				statement.append(" = ?");
				if (i.hasNext())
					statement.append(", ");
			}
		}

		statement.append(" where ");

		Iterator names = t.getPrimaryKeyNames().iterator();
		Iterator properties = t.getPrimaryKeyProperties().iterator();
		while (names.hasNext() && properties.hasNext()) {
			String name = (String) names.next();
			String property = (String) properties.next();
			statement.append(name);
			statement.append(" = ?");
			if (names.hasNext() && properties.hasNext())
				statement.append(" and ");
			parameters.add(type.getProperty(property));
		}

		if (t.getCollisionColumn() != null) {
			statement.append(" and ");
			statement.append(t.getCollisionColumn().getName());
			statement.append(" = ?");
			parameters.add(type.getProperty(t.getCollisionColumnPropertyName()));
		}

		
		WriteCommandImpl updateCommand;
		if ( t.getCollisionColumn() != null )
			updateCommand = new OptimisticWriteCommandImpl(statement.toString());
		else
			updateCommand = new UpdateCommandImpl(statement.toString());
		
		Iterator params = parameters.iterator();
		for (int idx = 1; params.hasNext(); idx++ ) {
			Property p = (Property)params.next();
			Parameter param = new ParameterImpl();
			param.setName(p.getName());
			param.setType(p.getType());
			param.setConverter(getConverter(t, p.getName()));
			param.setIndex(idx);
			updateCommand.addParameter(param);
		}
		DebugUtil.debugln(getClass(), debug, statement.toString());
		return updateCommand;
	}

	private List getFields(DataObject obj) {
		EClass clazz = ((EObject) obj).eClass();
		return clazz.getEAttributes();
	}


	private List getChangedFields(MappingWrapper mapping, ChangeSummary summary, DataObject obj) {
		ArrayList changes = new ArrayList();
		Iterator i = summary.getOldValues(obj).iterator();
		while (i.hasNext()) {
			EChangeSummarySetting setting = (EChangeSummarySetting) i.next();
			if (setting.getFeature() instanceof EAttribute)
				changes.add(setting.getFeature());
			else if ( setting.getFeature() instanceof EReference) {
				EReference ref = (EReference) setting.getFeature();
				if ( ref.getUpperBound() != ETypedElement.UNBOUNDED_MULTIPLICITY ) {
					RelationshipWrapper r = new RelationshipWrapper(mapping.getRelationshipByName(ref.getEOpposite().getName()));
					Iterator keys = r.getForeignKeys().iterator();
					while ( keys.hasNext()) {
						String key = (String) keys.next();
						EProperty p = (EProperty) obj.getType().getProperty(key);
						changes.add(p.getEStructuralFeature());
					}
				}
					
			}
		}
		return changes;
	}
	
	
	private Converter getConverter(TableWrapper tw, String name) {
		String converter = tw.getConverter(name);
		if ( converter != null ) {
			try {
				return (Converter) Class.forName(converter).newInstance();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return null;
	}

	public Collection getUpdateParameters(DataObject changedObject, Table table) {
		Type type = changedObject.getType();
		TableWrapper wrapper = new TableWrapper(table);
		Collection pkNames = wrapper.getPrimaryKeyProperties();
		ArrayList parameters = new ArrayList();
		ArrayList pkParams = new ArrayList();
		
		Iterator i = getFields(changedObject).iterator();
		while (i.hasNext()) {
			EAttribute attr = (EAttribute) i.next();
			String field = attr.getName();

			Parameter p = getParameter(wrapper, type.getProperty(field));
			if (pkNames.contains(field)) {
				pkParams.add(p);
			} else {
				parameters.add(p);
			}
		}
		parameters.addAll(pkParams);
		return parameters;
		
	}

	private Parameter getParameter(TableWrapper wrapper, Property property) {
		Parameter param = new ParameterImpl();
		param.setName(property.getName());
		param.setType(property.getType());
		param.setConverter(getConverter(wrapper, property.getName()));
		
		return param;
	}
}

