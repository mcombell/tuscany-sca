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
package org.apache.tuscany.das.rdb.config.wrapper;

import org.apache.log4j.Logger;

public class QualifiedColumn {

    private final String tableName;

    private final String columnName;
    private final String schemaName;//JIRA-952

    private final Logger logger = Logger.getLogger(QualifiedColumn.class);

    public QualifiedColumn(String name) {
    	this.schemaName = "";
        int index = name.indexOf('.');
        if ( index == -1 ) {
            throw new RuntimeException("Column " + name + " must be qualified with a table name");
        }
        tableName = name.substring(0, index);
        columnName = name.substring(index + 1);

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Table name:  " + tableName);
            this.logger.debug("Column name: " + columnName);
        }
    }

    //JIRA-952
    public QualifiedColumn(String name, boolean isDatabaseSchemaNameSupported) {
        int index = name.indexOf('.');
        if ( index == -1 ) {
            throw new RuntimeException("Column " + name + " must be qualified with a table name and optional schema name");
        }
        
        int lastIndex = name.lastIndexOf('.');
        		
        if(index == lastIndex && isDatabaseSchemaNameSupported){
        	throw new RuntimeException("Column " + name + " must be qualified with a table name and schema name");
        }
                
        if(isDatabaseSchemaNameSupported){
            schemaName = name.substring(0, index);
            tableName = name.substring(index+1, lastIndex);
            columnName = name.substring(lastIndex + 1);        	
        }
        else{
        	schemaName = "";
            tableName = name.substring(0, index);
            columnName = name.substring(index + 1);
        }

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Table name:  " + tableName);
            this.logger.debug("Column name: " + columnName);
        }
    }
    public String getTableName() {
        return this.tableName;
    }

    //JIRA-952
    public String getSchemaName() {
        return this.schemaName;
    }
    public String getColumnName() {
        return this.columnName;
    }
    //JIRA-952
    public String toString(){
    	if(this.schemaName == null || this.schemaName.equals(""))
    		return this.tableName+"."+this.columnName;
    	else
    		return this.schemaName+"."+this.tableName+"."+this.columnName;
    }
}
