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
package org.apache.tuscany.das.rdb;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.graphbuilder.schema.ResultSetTypeMap;

import commonj.sdo.Type;

/**
 * Describes the structure of the result set returned from execution 
 * of a SELECT statement.  This description is typcially not required since the 
 * shape can be retreived from the JDBC ResultSetMetadata.  However, some platforms
 * such as Oracle do not support fully suport ResultSetMedata.
 * <p>
 * There may also be a performance boost when using this interface.
 * 
 * 
 */
public class ResultSetShape {

	private final String[] columns;
	private final String[] tables;
	private final Type[] types;	
	
	public ResultSetShape(String[] t, String[] c, Type[] dataTypes) {
		this.columns = c;
		this.tables = t;
		this.types = dataTypes;	
	}

	public ResultSetShape(ResultSetMetaData metadata) throws SQLException {
		columns = new String[metadata.getColumnCount()];
		tables = new String[metadata.getColumnCount()];
		types = new Type[metadata.getColumnCount()];	
		
		ResultSetTypeMap typeMap = ResultSetTypeMap.instance;
		for (int i = 1; i <= metadata.getColumnCount(); i++) {
			tables[i-1] = metadata.getTableName(i);
			columns[i-1] = metadata.getColumnName(i);
			types[i-1] = typeMap.getType(metadata.getColumnType(i), true);
		}
	}

	public int getColumnCount() {
		return columns.length;
	}

	public String getTableName(int i) {
		return tables[i-1];
	}
	
	public String getColumnName(int i) {
		return columns[i-1];
	}
	
	public Type getColumnType(int i) {
		return types[i-1];
	}
	
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(" column/table/type: ");
		for (int i=0; i < columns.length; i++) {
			result.append(columns[i]);
			result.append('\t');
			result.append(tables[i]);
			result.append('\t');
			if ( types[i] == null ) 
				result.append("null");
			else 
				result.append(types[i].getName());			
			result.append('\n');
		}
		
		return result.toString();
	}

}
