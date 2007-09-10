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
 * This test
 * 
 */

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.das.rdb.util.ConfigUtil;

import commonj.sdo.DataObject;

/**
 * 
 * This tests use of the XML Config file. Tests will utilize the 
 * customer-orders-orderdetails tables. The plan is for the config file to have a
 * section that applies to all commands and another that applies to specific commands.
 * 
 * The config file will be used to initialize a command factory that will then return named commands.
 * 
 * There will be two read commands:
 * 
 * 1) Return all customers 2) Return a specific customer (by ID) and related orders and order details
 * 
 * A test will demonstrate the creation of the factory and then reuse of commands created from the same config data file
 * 
 */
public class CommandGroupTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Read
     */
    public void testRead() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());

        Command read = das.getCommand("all customers");
        DataObject root = read.executeQuery();

        assertEquals(5, root.getList("CUSTOMER").size());

    }

    /**
     * Read
     */
    public void testReadUsingConfigInput() throws Exception {
        Config config = ConfigUtil.loadConfig(getConfig("CustomersOrdersConfig.xml"));
        DAS das = DAS.FACTORY.createDAS(config, getConnection());

        Command read = das.getCommand("all customers");
        DataObject root = read.executeQuery();

        assertEquals(5, root.getList("CUSTOMER").size());

    }

    /**
     * Read an order using parm marker
     */
    public void testReadWithParmmarker() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());

        Command read = das.getCommand("order by id");
        read.setParameter(1, new Integer(1));
        DataObject root = read.executeQuery();

        assertEquals("recombobulator", root.getString("ANORDER[1]/PRODUCT"));

    }

    /**
     * Specify connection properties in config. Add explicit update command
     */
    public void testUpdate() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());

        Command read = das.getCommand("all customers");
        DataObject root = read.executeQuery();
        // Verify precondition
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));
        int id = root.getInt("CUSTOMER[1]/ID");

        Command update = das.getCommand("update customer");
        update.setParameter(1, new Integer(id));
        update.execute();

        // Verify update - reuse select command
        root = read.executeQuery();
        assertEquals("Pavick", root.get("CUSTOMER[1]/LASTNAME"));

    }

    /**
     * Read all customers, select a specific customer. Then read that 
     * customer and related orders. Modify an order and flush changes back
     */
    public void testRead2() throws Exception {

        // Create the group and set common connection
        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());

        // Read all customers and remember the first one
        Command read = das.getCommand("all customers");
        DataObject root = read.executeQuery();
        Integer id = (Integer) root.get("CUSTOMER[1]/ID");

        // Read the specific Customer from above and its related orders
        Command custOrders = das.getCommand("customer and orders");
        custOrders.setParameter(1, id);
        root = custOrders.executeQuery();

        // Modify the first order and flush this change back to the database
        root.setString("CUSTOMER[1]/orders[1]/PRODUCT", "Defibrillator");
        Integer orderId = (Integer) root.get("CUSTOMER[1]/orders[1]/ID");
        das.applyChanges(root);

        // Verify
        Command orderByID = das.getCommand("order by id");
        orderByID.setParameter(1, orderId);
        assertEquals("Defibrillator", root.getString("ANORDER[1]/PRODUCT"));

    }

}
