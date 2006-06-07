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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.tuscany.das.rdb.Converter;
import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.RelationshipWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.impl.InsertCommandImpl;
import org.apache.tuscany.das.rdb.impl.ParameterImpl;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

public class InsertGenerator {

	public static final InsertGenerator instance = new InsertGenerator();

	private static final boolean debug = false;

	public InsertGenerator() {
		super();
	}

	public InsertCommandImpl getInsertCommand(MappingWrapper config,
			DataObject changedObject, Table t) {
		ArrayList parameters = new ArrayList();
		TableWrapper table = new TableWrapper(t);
		StringBuffer statement = new StringBuffer("insert into ");
		statement.append(t.getTableName());

		Iterator i = getAttributeProperties(changedObject, config).iterator();

		ArrayList attributes = new ArrayList();
		while (i.hasNext()) {
			Property attr = (Property) i.next();
			if (!table.isGeneratedColumnProperty(attr.getName())) {
				attributes.add(attr.getName());
				parameters.add(changedObject.getType().getProperty(
						attr.getName()));
			}
		}

		statement.append("(");
		Iterator attrs = attributes.iterator();
		while (attrs.hasNext()) {
			String name = (String) attrs.next();
			statement.append("");
			statement.append(name);
			if (attrs.hasNext())
				statement.append(", ");
			else
				statement.append(")");
		}

		statement.append(" values (");
		for (int idx = 1; idx <= attributes.size(); idx++) {
			statement.append('?');
			if (idx < attributes.size())
				statement.append(", ");
			else
				statement.append(")");
		}

		InsertCommandImpl cmd = new InsertCommandImpl(statement.toString());
		Iterator params = parameters.iterator();
		for (int idx = 1; params.hasNext(); idx++) {
			Property property = (Property) params.next();
			Parameter p = new ParameterImpl();
			p.setName(property.getName());
			p.setType(property.getType());
			p.setConverter(getConverter(table, property.getName()));
			p.setIndex(idx);
			cmd.addParameter(p);

		}
		DebugUtil.debugln(getClass(), debug, statement.toString());
		return cmd;

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

	private List getAttributeProperties(DataObject obj, MappingWrapper config) {
		ArrayList fields = new ArrayList();
		Iterator i = obj.getType().getProperties().iterator();
		while (i.hasNext()) {
			Property p = (Property) i.next();
			if (p.getType().isDataType()) {
				if (obj.isSet(p))
					fields.add(p);
			} else {
				if ( obj.isSet(p) ) {
					Relationship relationship = config.getRelationshipByReference(p);
					if ( p.getOpposite().isMany() || (hasState(config, relationship, obj))) {							
						RelationshipWrapper r = new RelationshipWrapper(
							relationship);
						Iterator keys = r.getForeignKeys().iterator();
						while (keys.hasNext()) {
							String key = (String) keys.next();
							Property keyProp = obj.getType().getProperty(key);
							fields.add(keyProp);
						}
					}

				}
			}
		}

		return fields;

	}

	public Collection getInsertParameters(MappingWrapper config,
			DataObject changedObject, Table table) {
		ArrayList parameters = new ArrayList();
		TableWrapper wrapper = new TableWrapper(table);

		Iterator i = getAttributeProperties(changedObject, config).iterator();

		while (i.hasNext()) {
			Property attr = (Property) i.next();
			if (!wrapper.isGeneratedColumnProperty(attr.getName()))
				parameters.add(changedObject.getType().getProperty(
						attr.getName()));
		}

		return parameters;
	}

	private boolean hasState(MappingWrapper config, Relationship rel, DataObject changedObject) {							
			
			if ( !rel.isMany()) {
				Table t = config.getTableByTypeName(changedObject.getType().getName());
				TableWrapper tw = new TableWrapper(t);
				RelationshipWrapper rw = new RelationshipWrapper(rel);
				if (( rel.getForeignKeyTable().equals(t.getTableName())) &&
						( Collections.disjoint(tw.getPrimaryKeyProperties(),rw.getForeignKeys()) ))
					return true;			
			}
				
		return false;
	}

}
