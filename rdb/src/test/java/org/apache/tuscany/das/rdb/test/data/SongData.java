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

public class SongData extends TestDataWithExplicitColumns {
    // CREATE TABLE SONG (ID INT , TITLE VARCHAR(20), SINGERID INT)

    private static int[] songTypes = {Types.INTEGER, Types.VARCHAR, Types.INTEGER};

    private static Object[][] songData = {{null, "ABCD", new Integer(1)},
        {new Integer(20), "Lamb", new Integer(1)},
        {new Integer(30), "La ra ra", new Integer(2)}};

    private static String[] songColumns = {"ID", "TITLE", "SINGERID"};

    public SongData(Connection connection) {
        super(connection, songData, songColumns, songTypes);
    }

    public String getTableName() {
        return "SONG";
    }
}

