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

public class VisitData extends TestDataWithExplicitColumns {

    /*
     * CREATE TABLE VISIT ( ID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, 
     * CHECK_IN TIMESTAMP NULL, CHECK_OUT TIMESTAMP NULL, OCC_COUNT INTEGER UNSIGNED
     * NULL, PRIMARY KEY(ID) );
     */

    // id omitted. id is auto-generated.
    private static int[] columnTypes = {Types.TIMESTAMP, Types.TIMESTAMP, Types.INTEGER};

    private static Object[][] data = {
        {getTimestamp("2006-10-20 00:00:00.0"), getTimestamp("2006-10-22 00:00:00.0"), Integer.valueOf(1)},
        {getTimestamp("2006-10-20 00:00:00.0"), getTimestamp("2006-10-22 00:00:00.0"), Integer.valueOf(1)},
        {getTimestamp("2006-10-20 00:00:00.0"), getTimestamp("2006-10-22 00:00:00.0"), Integer.valueOf(1)}};

    private static String[] columns = {"CHECK_IN", "CHECK_OUT", "OCC_COUNT"};

    public VisitData(Connection connection) {
        super(connection, data, columns, columnTypes);
    }

    public String getTableName() {
        return "VISIT";
    }

}
