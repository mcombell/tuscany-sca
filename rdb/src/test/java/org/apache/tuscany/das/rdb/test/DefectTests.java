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
 * These tests attempt to duplicate customer reported errors and then to verify
 * any necessary fix.
 * 
 */

import java.util.Iterator;
import java.util.Random;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CompanyDeptData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.DepEmpData;
import org.apache.tuscany.das.rdb.test.data.DepartmentData;
import org.apache.tuscany.das.rdb.test.data.EmployeeData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class DefectTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
        
        new CompanyData(getAutoConnection()).refresh();
        new DepartmentData(getAutoConnection()).refresh();
        new EmployeeData(getAutoConnection()).refresh();
        new CompanyDeptData(getAutoConnection()).refresh();
        new DepEmpData(getAutoConnection()).refresh();
        
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Read a specific customer
     */
    public void testDiltonsInsert() throws Exception {

        // String sql = "insert into conmgt.serverstatus (statusid,
        // managedserverid, timestamp) values (316405209, 316405209, '2005-11-23
        // 19:29:52.636')";
        String sql = "insert into conmgt.serverstatus (managedserverid, timestamp) values (316405209, '2005-11-23 19:29:52.636')";

        Command insert = Command.FACTORY.createCommand(sql);
        insert.setConnection(getConnection());
        insert.execute();

        // Verify
        Command select = Command.FACTORY
                .createCommand("Select * from conmgt.serverstatus where statusid = 316405209");
        select.setConnection(getConnection());
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("conmgt.serverstatus").size());

    }



    public void testDiltonsInsertWorkaround() throws Exception {

        // String sql = "insert into conmgt.serverstatus (statusid,
        // managedserverid, timestamp) values (316405209, 316405209, '2005-11-23
        // 19:29:52.636')";
        // String sql = "insert into conmgt.serverstatus (managedserverid,
        // timestamp) values (316405209, '2005-11-23 19:29:52.636')";
        String sql = "insert into conmgt.serverstatus (managedserverid, timestamp) values (:serverid, :timestamp)";

        Command insert = Command.FACTORY.createCommand(sql);
        insert.setParameterValue("serverid", new Integer(316405209));
        insert.setParameterValue("timestamp", "2005-11-23 19:29:52.636");
        insert.setConnection(getConnection());
        insert.execute();

        // Verify
        Command select = Command.FACTORY.createCommand("Select * from conmgt.serverstatus");
        select.setConnection(getConnection());
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("SERVERSTATUS").size());

    }

    public void testWASDefect330118() throws Exception {

        // Create the group and set common connection
        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CustomersOrdersConfig.xml"));
        commandGroup.setConnection(getConnection());

        // Read all customers and add one
        Command read = commandGroup.getCommand("all customers");
        DataObject root = read.executeQuery();
        int numCustomers = root.getList("CUSTOMER").size();

        DataObject newCust = root.createDataObject("CUSTOMER");
        newCust.set("ID", new Integer(100));
        newCust.set("ADDRESS", "5528 Wells Fargo Drive");
        newCust.set("LASTNAME", "Gerkin");

        // Now delete this new customer
        newCust.delete();

        ApplyChangesCommand apply = commandGroup.getApplyChangesCommand();
        apply.execute(root);

        // Verify
        root = read.executeQuery();
        assertEquals(numCustomers, root.getList("CUSTOMER").size());

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

        Command insert = Command.FACTORY
                .createCommand("insert into CUSTOMER values (:ID, :LASTNAME, :ADDRESS)");
        insert.setConnection(getConnection());
        insert.setParameterValue("ID", new Integer(10));
        insert.setParameterValue("LASTNAME", "Williams");
        insert.setParameterValue("ADDRESS", null);
        insert.execute();

        // Verify
        Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 10");
        select.setConnection(getConnection());
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }

    /**
     * Try a workaround using CommandGroup
     */
    public void testYingChen12162005Workaraound() throws Exception {

        // Create the group and set common connection
        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CustomerConfig.xml"));
        commandGroup.setConnection(getConnection());

        Command insert = commandGroup.getCommand("insert customer");
        insert.setParameterValue("ID", new Integer(10));
        insert.setParameterValue("LASTNAME", "Williams");
        insert.setParameterValue("ADDRESS", null);
        insert.execute();

        // Verify
        Command select = commandGroup.getCommand("read customer 10");
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }
    
    


    /**
     * Should be able to explicitly set a parameter to null.  But, should require
     * that the parameter type is set.
     */
    public void testDiltonsNullParameterBug1() throws Exception {
        
        Command insert = Command.FACTORY.createCommand("insert into CUSTOMER values (:ID, :LASTNAME, :ADDRESS)");   
        insert.setConnection(getConnection());
        insert.setParameterValue("ID", new Integer(10));
        insert.setParameterValue("LASTNAME", null);
        insert.setParameterType("LASTNAME", SDODataTypes.STRING);
        insert.setParameterValue("ADDRESS", "5528 Wells Fargo Dr");
        insert.execute();

        //Verify
        Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 10");
        select.setConnection(getConnection());
        DataObject root = select.executeQuery();    
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }
   

    /**
     * Error by not setting a parameter
     */    
    public void testDiltonsNullParameterBug2() throws Exception {
        
        Command insert = Command.FACTORY.createCommand("insert into CUSTOMER values (:ID, :LASTNAME, :ADDRESS)");   
        insert.setConnection(getConnection());
        insert.setParameterValue("ID", new Integer(10));
//        insert.setParameterValue("LASTNAME", null);
        insert.setParameterValue("ADDRESS", "5528 Wells Fargo Dr");
        
        try {
            insert.execute();
            fail();
        }
        catch (RuntimeException e) {
            //Expected since "LASTNAME" parameter not set
        }
    }    
    
    /**
     * Set parameter to empty string
     */    
    public void testDiltonsNullParameterBug3() throws Exception {
        
        Command insert = Command.FACTORY.createCommand("insert into CUSTOMER values (:ID, :LASTNAME, :ADDRESS)");   
        insert.setConnection(getConnection());
        insert.setParameterValue("ID", new Integer(10));
        insert.setParameterValue("LASTNAME", "");
        insert.setParameterValue("ADDRESS", "5528 Wells Fargo Dr");
        insert.execute();

        //Verify
        Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 10");
        select.setConnection(getConnection());
        DataObject root = select.executeQuery();    
        assertEquals(1, root.getList("CUSTOMER").size());
        assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

    }
    
    public void testUpdateChildThatHasGeneratedKey() throws Exception {

        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CompanyConfig.xml"));
                        
        //Read a specific company based on the known ID
        Command readCust = commandGroup.getCommand("all companies and departments");
        DataObject root = readCust.executeQuery();       
        DataObject lastCustomer = root.getDataObject("COMPANY[3]");
        Iterator i = lastCustomer.getList("departments").iterator();
        Random generator = new Random();
        int random = generator.nextInt(1000) + 1;
        DataObject department;
        while (i.hasNext()) {
            department = (DataObject)i.next();
            System.out.println("Modifying department: " + department.getString("NAME"));
            department.setString("NAME", "Dept-" + random);
            random = random + 1;
        } 
        
        ApplyChangesCommand apply = commandGroup.getApplyChangesCommand();
        apply.execute(root);
        
    }  
         
    
    /**
     * Tuscany-433
     * 
     * Read and modify a customer.  
     * Provide needed Create/Update/Delete statements programatically
     * 
     * Fails if provided udpate statement does not include all parameters
     */
    public void testReadModifyApply()
        throws Exception
    {

        //Read customer 1
        Command select = Command.FACTORY.createCommand( "Select * from CUSTOMER where ID = 1" );
        select.setConnection( getConnection() );
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get( "CUSTOMER[1]" );

        //Modify customer
        customer.set( "LASTNAME", "Pavick" );


        // Provide updatecommand programmatically via config
        ConfigHelper helper = new ConfigHelper();
        helper.addUpdateStatement("update CUSTOMER set LASTNAME = :LASTNAME where ID = :ID", "CUSTOMER");         
        
        //Build apply changes command
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand(helper.getConfig());
        apply.setConnection( getConnection() );

        //Flush changes
        apply.execute( root );

        //Verify changes
        root = select.executeQuery();
        assertEquals( "Pavick", root.getString( "CUSTOMER[1]/LASTNAME" ) );

    }

    /**
     * 
     * Tuscany-433
     * 
     * 
     * Read and modify a customer.  
     * Provide needed Create/Update/Delete statements via xml file
     * 
     * Same as in CrudWithChangeHistory but provided partial update statement
     * 
     * Fails if provided udpate statement does not include all parameters
     * 
     */
    public void testReadModifyApply1()
        throws Exception
    {

        //Read customer 1
        Command select = Command.FACTORY.createCommand( "Select * from CUSTOMER where ID = 1" );
        select.setConnection( getConnection() );
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get( "CUSTOMER[1]" );

        //Modify customer
        customer.set( "LASTNAME", "Pavick" );

        //Build apply changes command
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand(getConfig("basicCustomerMappingWithCUD2.xml"));
        apply.setConnection( getConnection() );

        //Flush changes
        apply.execute( root );

        //Verify changes
        root = select.executeQuery();
        assertEquals( "Pavick", root.getString( "CUSTOMER[1]/LASTNAME" ) );

    }    
    
    
    
    
    
    
    
    /**
     * Test problem with Random
     */    
    public void testRandomNumber() throws Exception {
        
        Random generator = new Random();
        int number = generator.nextInt(1000) + 1;
        assertTrue(number > 0 & number <= 1000);

    }
    
    
    public void testTablePropertyName() throws Exception {
        
    }
    
    
    
    
    
}