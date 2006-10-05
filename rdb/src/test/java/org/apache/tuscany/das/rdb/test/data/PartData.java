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
package org.apache.tuscany.das.rdb.test.data;

import java.sql.Connection;
import java.sql.Types;

import org.apache.tuscany.das.rdb.test.framework.TestDataWithExplicitColumns;

public class PartData extends TestDataWithExplicitColumns {

    // CREATE TABLE PART (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(50), QUANTITY INT, PARENT_ID INT )

    private static String[] partColumns = {"ID", "NAME", "QUANTITY", "PARENT_ID"};

    private static int[] columnTypes = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER};

    private static Object[][] partData = {{Integer.valueOf(1), "Engine", Integer.valueOf(1), null},
        {Integer.valueOf(2), "Block", Integer.valueOf(1), Integer.valueOf(1)}, 
        {Integer.valueOf(3), "Cam Shaft", Integer.valueOf(2), Integer.valueOf(1)},
        {Integer.valueOf(4), "Piston", Integer.valueOf(8), Integer.valueOf(1)}, 
        {Integer.valueOf(5), "Piston Ring", Integer.valueOf(2), Integer.valueOf(4)}};

    public PartData(Connection connection) {
        super(connection, partData, partColumns, columnTypes);
    }

    public String getTableName() {
        return "PART";
    }

}
