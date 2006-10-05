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

public class BookData extends TestDataWithExplicitColumns {

    // CREATE TABLE BOOK (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(50), AUTHOR VARCHAR(30), QUANTITY INT, OCC INTEGER)

    private static int[] bookTypes = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};

    private static Object[][] bookData = {{Integer.valueOf(1), "The Brothers Karamazov", "Fyodor Dostoevsky", 
            Integer.valueOf(5), Integer.valueOf(17)},
        {Integer.valueOf(2), "Cat in the Hat", "Doctor Seuss", Integer.valueOf(10), Integer.valueOf(1)}};

    private static String[] bookColumns = {"BOOK_ID", "NAME", "AUTHOR", "QUANTITY", "OCC"};

    public BookData(Connection connection) {
        super(connection, bookData, bookColumns, bookTypes);
    }

    public String getTableName() {
        return "BOOK";
    }

}
