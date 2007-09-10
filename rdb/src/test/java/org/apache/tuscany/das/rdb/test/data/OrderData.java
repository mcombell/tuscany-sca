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
        {new Integer(1), "recombobulator", new Integer(47), new Integer(1)},
        {new Integer(2), "wrench", new Integer(17), new Integer(3)}, 
        {new Integer(3), "pliers", new Integer(500), new Integer(1)},
        {new Integer(4), "Tooth Paste", new Integer(12), new Integer(2)}};

    public OrderData(Connection c) {
        super(c, orderData);
    }

    public String getTableName() {
        return "ANORDER";
    }

}
