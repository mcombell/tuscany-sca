/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package org.apache.tuscany.das.rdb.impl;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.graphbuilder.schema.ResultSetTypeMap;

import commonj.sdo.Type;
import commonj.sdo.helper.TypeHelper;

/**
 * Describes the structure of the result set returned from 
 * execution of a SELECT statement. This description is typcially 
 * not required since the shape can be retreived from the JDBC 
 * ResultSetMetadata. However, some platforms such as Oracle do not 
 * support fully suport ResultSetMedata.
 * <p>
 * There may also be a performance boost when using this interface.
 * 
 * 
 */
public class ResultSetShape {

    private final String[] columns;

    private final String[] tables;

    private final Type[] types;

    private final String[] schema;//JIRA-952
    //JIRA-952
    public ResultSetShape(ResultSetMetaData metadata, Config model) throws SQLException {
        columns = new String[metadata.getColumnCount()];
        tables = new String[metadata.getColumnCount()];
        types = new Type[metadata.getColumnCount()];
        schema = new String[metadata.getColumnCount()];

        ResultSetTypeMap typeMap = ResultSetTypeMap.INSTANCE;
        for (int i = 1; i <= metadata.getColumnCount(); i++) {
        	if(model.isDatabaseSchemaNameSupported()){
            	if(metadata.getSchemaName(i) != null && !metadata.getSchemaName(i).equals("")){
            		//tables[i - 1] = metadata.getSchemaName(i)+"."+metadata.getTableName(i);
            		tables[i - 1] = metadata.getTableName(i);
            		schema[i - 1] = metadata.getSchemaName(i);
            	}
            	else{
            		tables[i - 1] = metadata.getTableName(i);
            		schema[i - 1] = "";
            	}        		
        	}
        	else{
        		tables[i - 1] = metadata.getTableName(i);
        		schema[i - 1] = "";
        	}
            columns[i - 1] = metadata.getColumnName(i);
            types[i - 1] = typeMap.getType(metadata.getColumnType(i), true);
        }
    }

    //JIRA-952
    public ResultSetShape(List resultDescriptor, Config model) {
        TypeHelper helper = TypeHelper.INSTANCE;
        int size = resultDescriptor.size();
        columns = new String[size];
        tables = new String[size];
        types = new Type[size];
        schema = new String[size];

        for (int i = 0; i < size; i++) {
            ResultDescriptor desc = (ResultDescriptor) resultDescriptor.get(i);
            if(model.isDatabaseSchemaNameSupported()){
            	if(desc.getSchemaName() != null && !desc.getSchemaName().equals("")){
            		tables[i] = desc.getTableName();
            		schema[i] = desc.getSchemaName();
            	}else{
            		tables[i] = desc.getTableName();
            		schema[i] = "";
            	}
            		
            }else{
           		tables[i] = desc.getTableName();
           		schema[i] = "";
            }
            columns[i] = desc.getColumnName();

            int idx = desc.getColumnType().lastIndexOf('.');
            String uri = desc.getColumnType().substring(0, idx);
            String typeName = desc.getColumnType().substring(idx + 1);

            types[i] = helper.getType(uri, typeName);
            if (types[i] == null) {
                throw new RuntimeException("Could not find type " + desc.getColumnType() 
                        + " for column " + desc.getColumnName());
            }
        }

    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getTableName(int i) {
        return tables[i - 1];
    }

    //JIRA-952
    public String getSchemaName(int i) {
        return schema[i - 1];
    }
    public String getColumnName(int i) {
        return columns[i - 1];
    }

    public Type getColumnType(int i) {
        return types[i - 1];
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(" column/table/schema/type: ");
        for (int i = 0; i < columns.length; i++) {
            result.append(columns[i]);
            result.append('\t');
            result.append(tables[i]);
            result.append('\t');
            result.append(schema[i]);
            result.append('\t');
            if (types[i] == null) {
                result.append("null");
            } else {
                result.append(types[i].getName());
            }
            result.append('\n');
        }

        return result.toString();
    }

}
