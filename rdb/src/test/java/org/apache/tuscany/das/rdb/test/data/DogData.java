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

public class DogData extends TestDataWithExplicitColumns {

    /*    CREATE TABLE DOG (
     ID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
     OWNER_ID INTEGER UNSIGNED NOT NULL,
     NAME VARCHAR(20) NOT NULL,
     BREED VARCHAR(20) NULL,
     OCC_COUNT INTEGER UNSIGNED NULL,
     PRIMARY KEY(ID),
     );*/

    //id and owner_id omitted.  id is auto-generated. owner_id is filled in by DogOwner data
    private static int[] columnTypes = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

    private static Object[][] data = {{"Fido", "Mutt", new Integer(1)}, 
        {"Max", "German Shepherd", new Integer(1)},
        {"Saddie", "Collie", new Integer(1)}};

    private static String[] columns = {"NAME", "BREED", "OCC_COUNT"};

    public DogData(Connection connection) {
        super(connection, data, columns, columnTypes);
    }

    public String getTableName() {
        return "DOG";
    }

}
