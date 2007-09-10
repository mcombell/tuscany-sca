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
package org.apache.tuscany.das.rdb.test;

/*
 * Test capability to participate in an extenrlly managed transaction. 
 * The client is managing the transaction boundary so the DAS will not issue
 * commit/rollback
 * 
 */

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class TransactionTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Read and modify a customer. Uses a "passive" connection
     */
    public void testReadModifyApply() throws Exception {

        // Create and initialize a DAS connection and initialize for externally
        // managed transaction boundaries
        java.sql.Connection c = getConnection();

        DAS das = DAS.FACTORY.createDAS(c);
        // Read customer 1
        Command select = das.createCommand("select * from CUSTOMER");
        DataObject root = select.executeQuery();
        
        int numCustomers = root.getList("CUSTOMER").size();

        //Create two identical customers.  Will force insert error on dup key
        
        DataObject custA = root.createDataObject("CUSTOMER");
        custA.set("ID", new Integer(100));
        custA.set("ADDRESS", "5528 Wells Fargo Drive");
        custA.set("LASTNAME", "Gerkin");
       
        DataObject custB = root.createDataObject("CUSTOMER");
        custB.set("ID", new Integer(100));
        custB.set("ADDRESS", "5528 Wells Fargo Drive");
        custB.set("LASTNAME", "Gerkin");
        try {
            das.applyChanges(root);
            fail("An exception was expected");
        } catch (Exception e) {
            //do nothing
        }

        // Verify that the changes did not go through
        // Roll back should discard al changes
        root = select.executeQuery();
        assertEquals(numCustomers, root.getList("CUSTOMER").size());

    }

}
