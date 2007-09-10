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

public class SingerData extends TestDataWithExplicitColumns {
    // CREATE TABLE SINGER (ID INT , NAME VARCHAR(20))

    private static int[] singerTypes = {Types.INTEGER, Types.VARCHAR};

    private static Object[][] singerData = {{new Integer(1), "John"},
        {new Integer(2), "Jane"}, {new Integer(3), "Lata"}};

    private static String[] singerColumns = {"ID", "NAME"};

    public SingerData(Connection connection) {
        super(connection, singerData, singerColumns, singerTypes);
    }

    public String getTableName() {
        return "SINGER";
    }
}

