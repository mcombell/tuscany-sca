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
 * These tests attempt to duplicate customer reported errors and then to verify
 * any necessary fix.
 * 
 */

import java.util.Iterator;
import java.util.Random;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CompanyDeptData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.DepartmentData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class CorrectedDefectTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();

        new CompanyData(getAutoConnection()).refresh();
        new DepartmentData(getAutoConnection()).refresh();
        new CompanyDeptData(getAutoConnection()).refresh();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Dilton's bug for adding new child data object
     */
    public void testAddNewOrder() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());
        // Read some customers and related orders
        Command select = das.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER "
                + "ON CUSTOMER.ID = ANORDER.CUSTOMER_ID");

        DataObject root = select.executeQuery();

        DataObject cust = root.getDataObject("CUSTOMER[1]");

        // Save ID and Order Count
        int custID = cust.getInt("ID");
        int custOrderCount = cust.getList("orders").size();

        // Create a new Order and add to customer1
        DataObject order = root.createDataObject("ANORDER");

        order.set("ID", new Integer(99));
        order.set("PRODUCT", "The 99th product");
        order.set("QUANTITY", new Integer(99));
        cust.getList("orders").add(order);

        assertEquals(custOrderCount + 1, cust.getList("orders").size());

        // Build apply changes command      
        das.applyChanges(root);

        // verify cust1 relationship updates
        select = das.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER "
                + "ON CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = ?");

        select.setParameter(1, new Integer(custID));
        root = select.executeQuery();

        assertEquals(custOrderCount + 1, root.getList("CUSTOMER[1]/orders").size());

    }

    public void testDiltonsInsertWorkaround() throws Exception {

        // String sql = "insert into conmgt.serverstatus (statusid,
        // managedserverid, timestamp) values (316405209, 316405209, '2005-11-23
        // 19:29:52.636')";
        // String sql = "insert into conmgt.serverstatus (managedserverid,
        // timestamp) values (316405209, '2005-11-23 19:29:52.636')";
        String sql = "insert into conmgt.serverstatus (managedserverid, timestamp) values (?, ?)";

        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand(sql);
        insert.setParameter(1, new Integer(316405209));
        insert.setParameter(2, "2005-11-23 19:29:52.636");
        insert.execute();

        // Verify
        Command select = das.createCommand("Select * from conmgt.SERVERSTATUS");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("SERVERSTATUS").size());

    }

    public void testWASDefect330118() throws Exception {

        // Create the group and set common connection
        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());

        // Read all customers and add one
        Command read = das.getCommand("all customers");
        DataObject root = read.executeQuery();
        int numCustomers = root.getList("CUSTOMER").size();

        DataObject newCust = root.createDataObject("CUSTOMER");
        newCust.set("ID", new Integer(100));
        newCust.set("ADDRESS", "5528 Wells Fargo Drive");
        newCust.set("LASTNAME", "Gerkin");

        // Now delete this new customer
        newCust.delete();

        das.applyChanges(root);

        // Verify
        root = read.executeQuery();
        assertEquals(numCustomers, root.getList("CUSTOMER").size());

    }

    /**
     * Should be able to explicitly set a parameter to null. But, should require
     * that the parameter type is set.
     */
    public void testDiltonsNullParameterBug1() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into CUSTOMER values (?, ?, ?)");
        insert.setParameter(1, new Integer(10));
        insert.setParameter(2, null);
        insert.setParameter(3, "5528 Wells Fargo Dr");
        insert.execute();

        // Verify
        Command select = das.createCommand("Select * from CUSTOMER where ID = 10");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }

    /**
     * Error by not setting a parameter
     */
    public void testDiltonsNullParameterBug2() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into CUSTOMER values (?, ?, ?)");
        insert.setParameter(1, new Integer(10));
        // insert.setParameterValue("LASTNAME", null);
        insert.setParameter(3, "5528 Wells Fargo Dr");

        try {
            insert.execute();
            fail();
        } catch (RuntimeException e) {
            // Expected since "LASTNAME" parameter not set
        }
    }

    /**
     * Set parameter to empty string
     */
    public void testDiltonsNullParameterBug3() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into CUSTOMER values (?, ?, ?)");
        insert.setParameter(1, new Integer(10));
        insert.setParameter(2, "");
        insert.setParameter(3, "5528 Wells Fargo Dr");
        insert.execute();

        // Verify
        Command select = das.createCommand("Select * from CUSTOMER where ID = 10");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }

    public void testUpdateChildThatHasGeneratedKey() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());

        // Read a specific company based on the known ID
        Command readCust = das.getCommand("all companies and departments");
        DataObject root = readCust.executeQuery();
        DataObject lastCustomer = root.getDataObject("COMPANY[3]");
        Iterator i = lastCustomer.getList("departments").iterator();
        Random generator = new Random();
        int random = generator.nextInt(1000) + 1;
        DataObject department;
        while (i.hasNext()) {
            department = (DataObject) i.next();
            // System.out.println("Modifying department: " +
            // department.getString("NAME"));
            department.setString("NAME", "Dept-" + random);
            random = random + 1;
        }

        das.applyChanges(root);
    }

    /**
     * Yin Chen reports ... "In the class Statement, method: public int
     * executeUpdate(Parameters parameters) - its tossing out RuntimeException
     * when the value of the parameter is null. "
     * 
     * His example build a update statement and sets one parameter value to be
     * null. I will try to duplicate with an insert since that is simpler
     * 
     */
    public void testYingChen12162005() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into CUSTOMER values (?, ?, ?)");
        insert.setParameter(1, new Integer(10));
        insert.setParameter(2, "Williams");
        insert.setParameter(3, null);
        insert.execute();

        // Verify
        Command select = das.createCommand("Select * from CUSTOMER where ID = 10");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertNull(root.get("CUSTOMER[1]/ADDRESS"));

    }

    /**
     * Formely tests concerning Tuscany-433. The error causing these tests was cleared up when
     * the method for handling parameters was changed.
     */
    public void testReadModifyApply() throws Exception {

        // Provide updatecommand programmatically via config
        ConfigHelper helper = new ConfigHelper();
        Table customerTable = helper.addTable("CUSTOMER", "CUSTOMER");
        helper.addUpdateStatement(customerTable, "update CUSTOMER set LASTNAME = ? where ID = ?", "LASTNAME ID");

        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());

        //Read customer 1
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        //Modify customer
        customer.set("LASTNAME", "Pavick");

        das.applyChanges(root);

        //Verify changes
        root = select.executeQuery();
        assertEquals("Pavick", root.getString("CUSTOMER[1]/LASTNAME"));

    }

    public void testReadModifyApply1() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("basicCustomerMappingWithCUD2.xml"), getConnection());
        //Read customer 1
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        //Modify customer
        customer.set("LASTNAME", "Pavick");

        //Build apply changes command
        das.applyChanges(root);

        //Verify changes
        root = select.executeQuery();
        assertEquals("Pavick", root.getString("CUSTOMER[1]/LASTNAME"));

    }

}
