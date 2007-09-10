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

import java.util.List;

import commonj.sdo.DataObject;

public interface TableRegistry {
    /**
     * Get the table with the specified name and primary key
     * 
     * @param tableName
     * @param primaryKey
     * @return DataObject
     */
    DataObject get(String tableName, List primaryKey);

    /**
     * Add the table with the specified name and primary key
     * 
     * @param tableName
     * @param primaryKey
     * @param value
     */
    void put(String tableName, List primaryKey, DataObject value);

    boolean contains(String name, List list);
}