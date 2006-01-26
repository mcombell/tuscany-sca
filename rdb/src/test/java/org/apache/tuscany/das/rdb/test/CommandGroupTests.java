/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.das.rdb.test;

/*
 * This test
 * 
 */

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

/**
 * 
 * This tests use of the XML Config file. Tests will utilize the
 * customer-orders-orderdetails tables. The plan is for the config file to have
 * a section that applies to all commands and another that applies to specific
 * commands.
 * 
 * The config file will be used to initialize a command factory that will then
 * return named commands.
 * 
 * There will be two read commands:
 * 
 * 1) Return all customers 2) Return a specific customer (by ID) and related
 * orders and order details
 * 
 * A test will demonstrate the creation of the factory and then reuse of
 * commands created from the same config data file
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
     * Read all customers
     */
    public void testRead() throws Exception {

        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getCustomerOrderConfig());
        commandGroup.setConnection(getConnection());

        Command read = commandGroup.getCommand("all customers");
        DataObject root = read.executeQuery();

        assertEquals(5, root.getList("CUSTOMER").size());

    }
    
    /**
     * Read all customers with 
     */
    public void testReadWithConnectionProperties() throws Exception {

        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CustOrdersConnectionProps.xml"));
//        commandGroup.setConnection(getConnection());

        Command read = commandGroup.getCommand("all customers");
        DataObject root = read.executeQuery();

        assertEquals(5, root.getList("CUSTOMER").size());
        
    }
    

    /**
     * Read all customers, select a specific customer. Then read that customer
     * and related orders. Modify an order and flush changes back
     */
    public void testRead2() throws Exception {

        //Create the group and set common connection
        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getCustomerOrderConfig());
        commandGroup.setConnection(getConnection());

        //Read all customers and remember the first one
        Command read = commandGroup.getCommand("all customers");
        DataObject root = read.executeQuery();
        Integer id = (Integer) root.get("CUSTOMER[1]/ID");

        //Read the specific Customer from above and its related orders
        Command custOrders = commandGroup.getCommand("customer and orders");
        custOrders.setParameterValue("ID", id);
        root = custOrders.executeQuery();

        //Modify the first order and flush this change back to the database
        root.setString("CUSTOMER[1]/orders[1]/PRODUCT", "Defibrillator");
        Integer orderId = (Integer)root.get("CUSTOMER[1]/orders[1]/ID");
        ApplyChangesCommand flush = commandGroup.getApplyChangesCommand();
        flush.execute(root);

        // Verify
        Command orderByID = commandGroup.getCommand("order by id");
        orderByID.setParameterValue("ID", orderId);
        assertEquals("Defibrillator", root.getString("ANORDER[1]/PRODUCT"));

    }

    // Utilities

    private InputStream getCustomerOrderConfig() throws FileNotFoundException {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("CustomersOrdersConfig.xml");
    }

    private InputStream getConfig(String fileName) throws FileNotFoundException {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }   
    
    
}
