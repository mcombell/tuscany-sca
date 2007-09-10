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
//JIRA-841
public class OrderDetailsDescriptionData extends TestData {

    // CREATE TABLE ORDERDETAILSDESC (ID INT NOT NULL, ORDERID INT NOT NULL, PRODUCTID INT NOT NULL, 
	// DESCR VARCHAR, 
    // PRIMARY KEY ID, FOREIGN KEY (ORDERID, PRODUCTID))

    protected static Object[][] orderDetailsDescriptionData = 
    {   {new Integer(1), new Integer(1), new Integer(1), "Descr 1,1,1"},
        {new Integer(2), new Integer(1), new Integer(1), "Descr 2,1,1"}, 
        {new Integer(3), new Integer(1), new Integer(2), "Descr 3,1,2"},
        {new Integer(4), new Integer(1), new Integer(2), "Descr 4,1,2"},
        {new Integer(5), new Integer(2), new Integer(1), "Descr 6,2,1"},
        {new Integer(6), new Integer(2), new Integer(2), "Descr 6,2,2"}};

    public OrderDetailsDescriptionData(Connection c) {
        super(c, orderDetailsDescriptionData);
    }

    public String getTableName() {
        return "ORDERDETAILSDESC";
    }
}
