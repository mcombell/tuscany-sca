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

public class OrderData extends TestData {

    protected static Object[][] orderData = {
        {Integer.valueOf(1), "recombobulator", Integer.valueOf(47), Integer.valueOf(1)},
        {Integer.valueOf(2), "wrench", Integer.valueOf(17), Integer.valueOf(3)}, 
        {Integer.valueOf(3), "pliers", Integer.valueOf(500), Integer.valueOf(1)},
        {Integer.valueOf(4), "Tooth Paste", Integer.valueOf(12), Integer.valueOf(2)}};

    public OrderData(Connection c) {
        super(c, orderData);
    }

    public String getTableName() {
        return "ANORDER";
    }

}
