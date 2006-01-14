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
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.impl.InsertCommandImpl;
import org.apache.tuscany.das.rdb.impl.ParameterImpl;
import org.apache.tuscany.das.rdb.util.DebugUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

public class InsertGenerator {

	public static final InsertGenerator instance = new InsertGenerator();

	private static final boolean debug = false;

	public InsertGenerator() {
		super();
	}

	public InsertCommandImpl getInsertCommand(DataObject changedObject, Table t) {
		ArrayList parameters = new ArrayList();
		TableWrapper table = new TableWrapper(t);
		StringBuffer statement = new StringBuffer("insert into ");
		statement.append(t.getName());

		Iterator i = getFields(changedObject).iterator();

		ArrayList attributes = new ArrayList();
		while (i.hasNext()) {
			EAttribute attr = (EAttribute) i.next();
			if ((!table.isGeneratedColumnProperty(attr.getName())) && ((EObject)changedObject).eIsSet(attr)) {
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
		for ( int idx=1; idx <= attributes.size(); idx++) {
			statement.append('?');
			if (idx < attributes.size())
				statement.append(", ");
			else
				statement.append(")");
		}

		InsertCommandImpl cmd = new InsertCommandImpl(statement.toString());
		Iterator params = parameters.iterator();
		for (int idx=1; params.hasNext(); idx++ ) {
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

	private List getFields(DataObject obj) {
		EClass clazz = ((EObject) obj).eClass();
		return clazz.getEAttributes();
	}

	public Collection getInsertParameters(DataObject changedObject, Table table) {
		ArrayList parameters = new ArrayList();
		TableWrapper wrapper = new TableWrapper(table);

		Iterator i = getFields(changedObject).iterator();

		while (i.hasNext()) {
			EAttribute attr = (EAttribute) i.next();
			if (!wrapper.isGeneratedColumnProperty(attr.getName()))
				parameters.add(changedObject.getType().getProperty(
						attr.getName()));
		}

		return parameters;
	}


}

