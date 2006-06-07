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

import java.util.Collection;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.Converter;
import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.impl.DeleteCommandImpl;
import org.apache.tuscany.das.rdb.impl.ParameterImpl;
import org.apache.tuscany.das.rdb.util.DebugUtil;

public class DeleteGenerator {

	public static final DeleteGenerator instance = new DeleteGenerator();

	private static final boolean debug = false;

	public DeleteGenerator() {
		super();
	}

	public String getDeleteStatement(Table t) {
		TableWrapper table = new TableWrapper(t);

		StringBuffer statement = new StringBuffer();
		statement.append("delete from ");
		statement.append(t.getTableName());
		statement.append(" where ");

		Iterator names = table.getPrimaryKeyNames().iterator();
		Iterator properties = table.getPrimaryKeyProperties().iterator();
		while (names.hasNext() && properties.hasNext()) {
			String name = (String) names.next();
			statement.append(name);
			statement.append(" = ?");
			if (names.hasNext() && properties.hasNext())
				statement.append(" and ");
		}

		DebugUtil.debugln(getClass(), debug, statement.toString());
		return statement.toString();
	}

	public Collection getDeleteParameters(Table table) {
		TableWrapper wrapper = new TableWrapper(table);
		return wrapper.getPrimaryKeyProperties();
	}

	public DeleteCommandImpl getDeleteCommand(Table t) {
		DeleteCommandImpl deleteCommand = new DeleteCommandImpl(getDeleteStatement(t));
		
		Iterator i = getDeleteParameters(t).iterator();
		for(int idx=1; i.hasNext(); idx++) {
			String property = (String) i.next();
			Parameter p = new ParameterImpl();
			p.setName(property);
			p.setType(SDODataTypes.OBJECT);
			p.setConverter(getConverter(t, property));
			p.setIndex(idx);
			deleteCommand.addParameter(p);
		}
		return deleteCommand;
	}
	
	private Converter getConverter(Table t, String name) {
		TableWrapper tw = new TableWrapper(t);
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

}

