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
import org.apache.tuscany.das.rdb.util.DebugUtil;

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

	public UpdateCommandImpl getUpdateCommand(MappingWrapper mapping, DataObject changedObject, Table table) {
		ArrayList parameters = new ArrayList();
		Type type = changedObject.getType();
		TableWrapper t = new TableWrapper(table);
		StringBuffer statement = new StringBuffer("update ");
		statement.append(table.getTableName());
		statement.append(" set ");

		
		ChangeSummary summary = changedObject.getDataGraph().getChangeSummary();
		Iterator i = getChangedFields(mapping, summary, changedObject).iterator();

		while (i.hasNext()) {
			Property attr = (Property) i.next();
			Column c = t.getColumnByPropertyName(attr.getName());
			if ((c != null) && (c.isCollision() || c.isPrimaryKey())) {
				// get rid of comma if OCC or PK is last field
				if (!i.hasNext()) {
					statement
							.delete(statement.length() - 2, statement.length());
				}
			} else {
				parameters.add(attr);
				statement.append(c == null ? attr.getName() : c.getColumnName());
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
			statement.append(t.getCollisionColumn().getColumnName());
			statement.append(" = ?");
			parameters.add(type.getProperty(t.getCollisionColumnPropertyName()));
		}

		
		UpdateCommandImpl updateCommand;
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

	private List getAttributeProperties(DataObject obj) {
		ArrayList fields = new ArrayList();
		Iterator i = obj.getType().getProperties().iterator();
		while ( i.hasNext() ) {
			Property p = (Property) i.next();
			if ( p.getType().isDataType())
				fields.add(p);
		}
		return fields;
	}


	private List getChangedFields(MappingWrapper mapping, ChangeSummary summary, DataObject obj) {
		ArrayList changes = new ArrayList();
		Iterator i = summary.getOldValues(obj).iterator();
		while (i.hasNext()) {
			ChangeSummary.Setting setting = (ChangeSummary.Setting) i.next();
			if (setting.getProperty().getType().isDataType()) {
				changes.add(setting.getProperty());
			} else  {
				Property ref = setting.getProperty();
				if ( !ref.isMany() ) {												
					RelationshipWrapper r = new RelationshipWrapper(mapping.getRelationshipByReference(ref));
					
					Iterator keys = r.getForeignKeys().iterator();
					while ( keys.hasNext()) {
						String key = (String) keys.next();
						Property p = obj.getType().getProperty(key);
						changes.add(p);
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
		
		Iterator i = getAttributeProperties(changedObject).iterator();
		while (i.hasNext()) {
			Property attr = (Property) i.next();
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

