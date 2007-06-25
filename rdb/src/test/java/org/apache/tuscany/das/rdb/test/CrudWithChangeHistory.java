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
 * This provdes the simplest examples that make use of the change history. The assumptions are:
 * 
 * Single type Change history utilized Dynamic DataObjects
 * 
 * 
 */

import java.util.Iterator;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class CrudWithChangeHistory extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
        new OrderDetailsData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDeleteAndCreate() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("basicCustomerMappingWithCUD2.xml"), getConnection());
        // Read customer 1
        Command select = das.createCommand("Select * from CUSTOMER");
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        int customerId = customer.getInt("ID");
        // Modify customer
        customer.delete();

        DataObject newCustomer = root.createDataObject("CUSTOMER");
        newCustomer.setInt("ID", 9999);
        newCustomer.setString("LASTNAME", "Jones");

        // Build apply changes command
        das.applyChanges(root);

        // Verify changes
        root = select.executeQuery();
        boolean found = false;
        Iterator i = root.getList("CUSTOMER").iterator();
        while (i.hasNext()) {
            customer = (DataObject) i.next();
            assertFalse(customerId == customer.getInt("ID"));
            if (customer.getInt("ID") == 9999) {
                found = true;
            }
        }

        assertTrue(found);

    }

    /**
     * Read and modify a customer. Provide needed Create/Update/Delete statements programatically
     */
    public void testReadModifyApply() throws Exception {

        // Provide updatecommand programmatically via config
        ConfigHelper helper = new ConfigHelper();
        Table customerTable = helper.addTable("CUSTOMER", "CUSTOMER");
        helper.addUpdateStatement(customerTable, "update CUSTOMER set LASTNAME = ?, ADDRESS = ? "
                + "where ID = ?", "LASTNAME ADDRESS ID");

        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());
        // Read customer 1
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        // Build apply changes command
        das.applyChanges(root);

        // Verify changes
        root = select.executeQuery();
        assertEquals("Pavick", root.getString("CUSTOMER[1]/LASTNAME"));

    }

    /**
     * Read and modify a customer. Provide needed Create/Update/Delete statements via xml file
     */
    public void testReadModifyApply1() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("basicCustomerMappingWithCUD.xml"), getConnection());
        // Read customer 1
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        // Build apply changes command
        das.applyChanges(root);

        // Verify changes
        root = select.executeQuery();
        assertEquals("Pavick", root.getString("CUSTOMER[1]/LASTNAME"));

    }

    /**
     * Same as previous but: Utilizes generated CUD statements Key info provided programatically
     */
    public void testReadModifyApply2() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read customer with particular ID
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        DataObject customer = root.getDataObject("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        // Build apply changes command
        das.applyChanges(root);

        // Verify the change
        root = select.executeQuery();
        assertEquals("Pavick", root.getDataObject("CUSTOMER[1]").getString("LASTNAME"));

    }

    /**
     * Builds on previous but: 1. Key info provided via XML file
     */
    public void testReadModifyApply3() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("basicCustomerMapping.xml"), getConnection());
        // Read customer with particular ID
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        // Build apply changes command
        das.applyChanges(root);

        // Verify the change
        root = select.executeQuery();
        assertEquals("Pavick", root.getDataObject("CUSTOMER[1]").getString("LASTNAME"));

    }

    /**
     * Builds on previous but: 1. Uses a named command
     */
    public void testReadModifyApply4() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("CustomerConfig.xml"), getConnection());
        // Read customer with particular ID
        Command select = das.getCommand("getCustomer");
        select.setParameter(1, 1);
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        DataObject customer = (DataObject) root.get("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        // Build apply changes command
        das.applyChanges(root);

        // Verify the change
        root = select.executeQuery();
        assertEquals("Pavick", root.getDataObject("CUSTOMER[1]").getString("LASTNAME"));

    }
    
    public void testReadModifyApplyMultipleRows() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Verify pre-condition
        Command select = das.createCommand("Select * from CUSTOMER");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));
        assertFalse(root.get("CUSTOMER[2]/LASTNAME").equals("Silva"));

        
        // Read and modify customer 1
        DataObject customer1 = (DataObject) root.get("CUSTOMER[1]");
        customer1.set("LASTNAME", "Pavick");

        // Read and modify customer 2
        DataObject customer2 = (DataObject) root.get("CUSTOMER[2]");
        customer2.set("LASTNAME", "Silva");
        
        // Build apply changes command
        das.applyChanges(root);

        // Verify changes
        root = select.executeQuery();
        assertEquals("Pavick", root.getString("CUSTOMER[1]/LASTNAME"));
        assertEquals("Silva", root.getString("CUSTOMER[2]/LASTNAME"));
    }    
    
    /**
     * Test ability to handle multiple changes to the graph including Creates/Updates/Deletes Employs generated CUD
     */
    public void testReadModifyDeleteInsertApply() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("basicCustomerMapping.xml"), getConnection());
        // Read some customers
        Command select = das.createCommand("Select * from CUSTOMER where LASTNAME = 'Williams'");
        DataObject root = select.executeQuery();

        DataObject cust1 = (DataObject) root.getList("CUSTOMER").get(0);
        DataObject cust2 = (DataObject) root.getList("CUSTOMER").get(1);
        DataObject cust3 = (DataObject) root.getList("CUSTOMER").get(2);

        // Modify a customer
        cust1.set("LASTNAME", "Pavick");
        int cust1ID = cust1.getInt("ID");

        // Save IDs before delete
        int cust2ID = cust2.getInt("ID");
        int cust3ID = cust3.getInt("ID");
        // Delete a couple
        cust2.delete();
        cust3.delete();

        // Create a new customer
        DataObject cust4 = root.createDataObject("CUSTOMER");
        cust4.set("ID", Integer.valueOf(100));
        cust4.set("ADDRESS", "5528 Wells Fargo Drive");
        cust4.set("LASTNAME", "Gerkin");

        // Build apply changes command
        das.applyChanges(root);

        // Verify deletes
        select = das.createCommand("Select * from CUSTOMER where ID = ?");
        select.setParameter(1, Integer.valueOf(cust2ID));
        root = select.executeQuery();
        assertTrue(root.getList("CUSTOMER").isEmpty());
        // reparameterize same command
        select.setParameter(1, Integer.valueOf(cust3ID));
        root = select.executeQuery();
        assertTrue(root.getList("CUSTOMER").isEmpty());

        // verify insert
        select.setParameter(1, Integer.valueOf(100));
        root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Drive", root.getString("CUSTOMER[1]/ADDRESS"));
        assertEquals("Gerkin", root.getString("CUSTOMER[1]/LASTNAME"));

        // verify update
        select.setParameter(1, Integer.valueOf(cust1ID));
        root = select.executeQuery();
        assertEquals("Pavick", root.getString("CUSTOMER[1]/LASTNAME"));

    }

    public void testReadModifyApplyWithAssumedID() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read customer with particular ID
        Command select = das.createCommand("Select * from CUSTOMER");
        DataObject root = select.executeQuery();

        DataObject customer = root.getDataObject("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        DataObject customerForDelete = getCustomerByLastName(root, "Daniel");
        customerForDelete.delete();

        DataObject newCustomer = root.createDataObject("CUSTOMER");
        newCustomer.set("LASTNAME", "NewCustomer");
        newCustomer.setInt("ID", 9000);

        // Build apply changes command
        das.applyChanges(root);

        // Verify the change
        root = select.executeQuery();
        assertEquals("Pavick", getCustomerByLastName(root, "Pavick").getString("LASTNAME"));
        assertEquals("NewCustomer", getCustomerByLastName(root, "NewCustomer").getString("LASTNAME"));
        assertNull(getCustomerByLastName(root, "Daniel"));

    }

    public void testReadModifyApplyWithAssumedIDFailure() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("Select * from ORDERDETAILS");

        DataObject root = select.executeQuery();

        DataObject od = root.getDataObject("ORDERDETAILS[1]");

        // Modify customer
        od.setInt("PRODUCTID", 72);

        // Flush changes -- This should fail because Order Details does not have
        // a column that
        // we can assume to be an ID
        try {
            das.applyChanges(root);
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().contains("changed in the DataGraph but is not present in the Config"));
        }

    }

    public void testReadModifyApplyWithAssumedIDFailure2() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("Select * from ORDERDETAILS");
        DataObject root = select.executeQuery();

        DataObject od = root.getDataObject("ORDERDETAILS[1]");
        od.delete();

        // Flush changes -- This should fail because Order Details does not have
        // a column that
        // we can assume to be an ID
        try {
            das.applyChanges(root);
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().contains("changed in the DataGraph but is not present in the Config"));
        }

    }

    public void testReadModifyApplyWithAssumedIDFailure3() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("Select * from ORDERDETAILS");
        DataObject root = select.executeQuery();

        DataObject od = root.createDataObject("ORDERDETAILS");

        // Modify customer
        od.setInt("PRODUCTID", 72);
        od.setInt("ORDERID", 500);

        // Flush changes -- This should fail because Order Details does not have
        // a column that
        // we can assume to be an ID
        try {
            das.applyChanges(root);
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().contains("changed in the DataGraph but is not present in the Config"));
        }

    }

    private DataObject getCustomerByLastName(DataObject root, String name) {
        Iterator i = root.getList("CUSTOMER").iterator();
        while (i.hasNext()) {
            DataObject obj = (DataObject) i.next();
            if (name.equals(obj.getString("LASTNAME"))) {
                return obj;
            }
        }
        return null;
    }
}
