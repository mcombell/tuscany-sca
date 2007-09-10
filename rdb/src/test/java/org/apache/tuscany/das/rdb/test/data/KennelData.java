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

public class KennelData extends TestDataWithExplicitColumns {

    /*    CREATE TABLE KENNEL (
     ID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
     KNUMBER INTEGER UNSIGNED NULL,
     KIND VARCHAR(20) NULL,
     OCC_COUNT INTEGER UNSIGNED NULL,
     PRIMARY KEY(ID)
     );*/

    //id omitted.  id is auto-generated.
    private static int[] columnTypes = {Types.INTEGER, Types.VARCHAR, Types.INTEGER};

    private static Object[][] data = {{new Integer(100), "Small", new Integer(1)}, 
        {new Integer(101), "Small", new Integer(1)},
        {new Integer(102), "Large", new Integer(1)}};

    private static String[] columns = {"KNUMBER", "KIND", "OCC_COUNT"};

    public KennelData(Connection connection) {
        super(connection, data, columns, columnTypes);
    }

    public String getTableName() {
        return "KENNEL";
    }

}
