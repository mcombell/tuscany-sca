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

import org.apache.tuscany.das.rdb.test.framework.TestData;

public class CustomerData extends TestData {

    private static Object[][] customerData = {{Integer.valueOf(1), "Williams", "1212 foobar lane"},
        {Integer.valueOf(2), "Daniel", "156 Brentfield Loop"}, {Integer.valueOf(3), "Williams", "456 penny lane"},
        {Integer.valueOf(4), "Williams", "5000 pineville"}, {Integer.valueOf(5), "Williams", "100000 firefly lane"}};

    public CustomerData(Connection connection) {
        super(connection, customerData);
    }

    public String getTableName() {
        return "CUSTOMER";
    }

}
