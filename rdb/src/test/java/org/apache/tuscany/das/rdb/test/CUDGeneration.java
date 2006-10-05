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

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class CUDGeneration extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
    }

    /**
     * This tests provides invalid SQL and should fail on Apply.execute. If not 
     * then the engine is generating CUD and overlooking the provided
     * statements.
     */
    public void testCUDGeneration1() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("basicCustomerMappingWithInvalidCUD.xml"), getConnection());

        // Read customer with particular ID
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        // Flush changes
        try {
            das.applyChanges(root);
            fail("Should fail with invalid SQL.  Provided CUD not used!!");
        } catch (RuntimeException e) {
            // Everything OK
        }

    }

    public void testInsertCUDGeneration() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());

        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();

        DataObject customer = root.createDataObject("CUSTOMER");
        customer.setInt("ID", 720);
        customer.set("LASTNAME", "foobar");
        customer.set("ADDRESS", "asdfasdf");

        das.applyChanges(root);

        select = das.createCommand("select * from CUSTOMER where ID = 720");
        root = select.executeQuery();

        assertEquals(1, root.getList("CUSTOMER").size());
    }

    public void testReadModifyApply() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("1xM_mapping_no_cud.xml"), getConnection());

        // Build the select command to read a specific customer and related
        // orders
        Command select = das.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON " 
                + "CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = ?");

        // Parameterize the command
        select.setParameter(1, Integer.valueOf(1));

        // Get the graph
        DataObject root = select.executeQuery();

        // Modify a customer
        DataObject customer = (DataObject) root.get("Customer[1]");
        customer.set("LASTNAME", "Pavick");

        // Modify an order
        DataObject order = (DataObject) customer.get("orders[1]");
        order.setString("PRODUCT", "Kitchen Sink 001");

        das.applyChanges(root);

    }

}
