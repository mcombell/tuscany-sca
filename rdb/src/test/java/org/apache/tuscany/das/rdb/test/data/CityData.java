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
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.test.framework.TestData;

public class CityData extends TestData {

    private static Object[][] cityData = {{new Integer(1), "Lizard Lick", "1"}, 
        {new Integer(2), "Morrisville", "1"},
        {new Integer(3), "Breckenridge", "2"}, 
        {new Integer(4), "Barstow", "3"}, 
        {new Integer(5), "Sacramento", "3"}

    };

    public CityData(Connection c) {
        super(c, cityData);
    }

    public String getTableName() {
        return "CITIES";
    }

    public void doDeletes() throws SQLException {
        deleteRowsFromTable();

    }

    public void doInserts() throws SQLException {
        insertRows();

    }

}
