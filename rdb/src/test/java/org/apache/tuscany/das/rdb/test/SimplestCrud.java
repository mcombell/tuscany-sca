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
 * These are the simplest possible uses of the JDBC DAS. In this mode, the programming model is not much more than JDBC
 * 
 * The assumptions for these tests are:
 * 
 * Single type Client explicitly Read/Create/Update/Delete commands 
 * No O/R mapping metadata SDO change history is not used Dynamic DataObjects No
 * specified graph model
 * 
 * 
 */

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class SimplestCrud extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Read a specific customer
     */
    public void testReadSingle() throws Exception {

        // Create and initialize command to read customers
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command readCustomers = das.createCommand("select * from CUSTOMER where ID = 1");

        // Read
        DataObject root = readCustomers.executeQuery();

        // Verify
        assertEquals(1, root.getInt("CUSTOMER[1]/ID"));
    }

    /**
     * Read a specific customer
     */
    public void testReadSingle2() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Create and initialize command to read customers
        Command readCustomers = das.createCommand("select * from CUSTOMER where ID = 1");

        // Read
        DataObject root = readCustomers.executeQuery();

        // Verify
        assertEquals(1, root.getInt("CUSTOMER[1]/ID"));
    }

    /**
     * Read a specific customer Same as above but tests tolerance of white space in provided SQL
     */
    public void testReadSingleWithWhiteSpace() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Create and initialize command to read customers
        Command readCustomers = das.createCommand("   select * from CUSTOMER where ID = 1");

        // Read
        DataObject root = readCustomers.executeQuery();

        // Verify
        assertEquals(1, root.getInt("CUSTOMER[1]/ID"));
    }

    /**
     * Read all customers with a specific last name
     */
    public void testReadMultiple() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Create and initialize command to read customers
        Command readCustomers = das.createCommand("select * from CUSTOMER where LASTNAME = 'Williams'");

        // Read
        DataObject root = readCustomers.executeQuery();

        // Verify
        assertEquals(4, root.getList("CUSTOMER").size());
    }

    /**
     * Read all customers with a specific last name LASTNAME value is provided via a parameter
     */
    public void testReadMultipleWithParameters() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Create and initialize command to read customers
        Command readCustomers = das.createCommand("select * from CUSTOMER where LASTNAME = ?");

        // Parameterize the command
        readCustomers.setParameter(1, "Williams");
        DataObject root = readCustomers.executeQuery();

        // Verify
        assertEquals(4, root.getList("CUSTOMER").size());
    }

    public void testInsert() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into CUSTOMER values (10, 'Williams', '5528 Wells Fargo Dr')");
        insert.execute();

        // Verify
        Command select = das.createCommand("Select * from CUSTOMER where ID = 10");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }

    public void testInsertWithParameters() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into CUSTOMER values (?, ?, ?)");
        insert.setParameter(1, Integer.valueOf(10));
        insert.setParameter(2, "Williams");
        insert.setParameter(3, "5528 Wells Fargo Dr");
        insert.execute();

        // Verify
        Command select = das.createCommand("Select * from CUSTOMER where ID = 10");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }

    public void testDelete() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Verify pre-condition
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());

        // Create and execute the delete command
        Command delete = das.createCommand("delete from CUSTOMER where ID = 1");
        delete.execute();

        // Verify delete by reusing the original select command
        root = select.executeQuery();
        assertEquals(0, root.getList("CUSTOMER").size());

    }

    public void testUpdate() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Verify pre-condition
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        Command update = das.createCommand("update CUSTOMER set LASTNAME = 'Pavick' where ID = 1");
        update.execute();

        // Verify update - reuse select command
        root = select.executeQuery();
        assertEquals("Pavick", root.get("CUSTOMER[1]/LASTNAME"));

    }

    public void testUpdateWithParameters() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Verify pre-condition
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        Command update = das.createCommand("update CUSTOMER set LASTNAME = ? where ID = ?");
        update.setParameter(1, "Pavick");
        update.setParameter(2, Integer.valueOf(1));
        update.execute();

        // Verify update - reuse select command
        root = select.executeQuery();
        assertEquals("Pavick", root.get("CUSTOMER[1]/LASTNAME"));

    }

    public void testUpdateWithParmarkers() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Verify pre-condition
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        Command update = das.createCommand("update CUSTOMER set LASTNAME = ? where ID = ?");
        update.setParameter(1, "Pavick");
        update.setParameter(2, Integer.valueOf(1));
        update.execute();

        //Verify update - reuse select command
        root = select.executeQuery();
        assertEquals("Pavick", root.get("CUSTOMER[1]/LASTNAME"));

    }

}