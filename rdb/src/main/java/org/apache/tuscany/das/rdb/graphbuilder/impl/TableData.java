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
package org.apache.tuscany.das.rdb.graphbuilder.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TableData {
    private final Logger logger = Logger.getLogger(TableData.class);

    private Map columnData = new HashMap();

    private List primaryKey = new ArrayList();

    private final String name;

    private boolean hasValidPrimaryKey = true;

    public TableData(String tableName) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Creating TableData for table " + tableName);
        }

        this.name = tableName;
    }

    public void addData(String columnName, boolean isPrimaryKeyColumn, Object data) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Adding column " + columnName + " with value " + data);
        }

        columnData.put(columnName, data);
        if (isPrimaryKeyColumn) {
            if (data == null) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Column " + columnName + " is a primary key column and is null");
                }
                hasValidPrimaryKey = false;
            }
            primaryKey.add(data);
        }
    }

    public Object getColumnData(String columnName) {
        return columnData.get(columnName);
    }

    public String getTableName() {
        return this.name;
    }

    /**
     * @return
     */
    public List getPrimaryKeyValues() {
        return primaryKey;
    }

    public boolean hasValidPrimaryKey() {
        return hasValidPrimaryKey;
    }
}
