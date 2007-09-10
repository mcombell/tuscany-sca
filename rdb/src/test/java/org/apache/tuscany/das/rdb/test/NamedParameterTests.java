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

import java.util.Iterator;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.Create;
import org.apache.tuscany.das.rdb.config.Delete;
import org.apache.tuscany.das.rdb.config.Parameter;
import org.apache.tuscany.das.rdb.config.Parameters;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.Update;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class NamedParameterTests  extends DasTest {
    protected void setUp() throws Exception {    
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
    }
    
    protected void tearDown() throws Exception {
    	super.tearDown();
    }
 
    /**
     * Test passing <Parameters><Parameter></Paramerers> to create/update/delete
     * through config, with duplicate indexes
     * @throws Exception
     */
    public void testCUDNamedParameters() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("namedParameter.xml"), getConnection());
        // Read customer 1
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

        // Modify customer
        DataObject customer = (DataObject) root.get("CUSTOMER[1]");
        customer.set("LASTNAME", "Pavick");
        customer.set("ADDRESS", "Pavick's Address");
        // Build apply changes command
        try{
        	das.applyChanges(root);
        	fail("Expected exception");
        }catch(RuntimeException e){
        	assertTrue(e.getMessage().startsWith("Parameters with improper indexes!"));
        }
        
        //create
        root = select.executeQuery();
        DataObject newCustomer = root.createDataObject("CUSTOMER");
        newCustomer.setInt("ID", 6);
        newCustomer.setString("LASTNAME", "Louise");
        newCustomer.setString("ADDRESS", "U.K.");
        try{
        	das.applyChanges(root);
        	fail("Expected exception");
        }catch(RuntimeException e){
        	assertTrue(e.getMessage().startsWith("Parameters with duplicate indexes!"));
        }
        
        //delete
        root = select.executeQuery();
        DataObject toBeDeleted = root.getDataObject("CUSTOMER[1]");
        toBeDeleted.setString("LASTNAME", "Pavick");
        toBeDeleted.delete();
        das.applyChanges(root);
        root = select.executeQuery();
        assertEquals(null, root.getDataObject("CUSTOMER[1]"));
        
    }
        
    /**
     * Test passing <Parameter>List to Command through config
     * @throws Exception
     */    
    public void testCommandNamedParameters() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("namedParameter.xml"), getConnection());
    	//select
    	Command select = das.getCommand("getCustomer");
    	select.setParameter("ID", new Integer(1));
    	DataObject root = select.executeQuery();
    	List customers = root.getList("CUSTOMER");
    	assertEquals(1, customers.size());
    	DataObject customer = (DataObject)customers.get(0);
    	assertEquals("Williams", customer.get("LASTNAME"));
    	
    	//insert - command in config has <Parameter>
    	Command insert = das.getCommand("createCustomer");
    	//see order is not maintained, but config depicts it through index attrb
    	//if index is missing , auto increment logic will be followed
    	insert.setParameter("LASTNAME", "Louise");
    	insert.setParameter("ADDRESS", "TPO");
    	insert.setParameter("ID", new Integer(6));
    	insert.execute();
    	select.setParameter("ID", new Integer(6));
    	root = select.executeQuery();
    	customers = root.getList("CUSTOMER");
    	assertEquals(1, customers.size());
    	customer = (DataObject)customers.get(0);
    	assertEquals("Louise", customer.get("LASTNAME"));
    	
    	//insert - command in config has no <Parameter>
    	Command insertNoParam = das.getCommand("createCustomerNoParam");
    	//if param/index is missing in cfg, auto increment logic will be followed
    	insertNoParam.setParameter("ID", new Integer(7));
    	insertNoParam.setParameter("LASTNAME", "Louise7");
    	insertNoParam.setParameter("ADDRESS", "TPO7");
    	insertNoParam.execute();
    	select.setParameter("ID", new Integer(7));
    	root = select.executeQuery();
    	customers = root.getList("CUSTOMER");
    	assertEquals(1, customers.size());
    	customer = (DataObject)customers.get(0);
    	assertEquals("Louise7", customer.get("LASTNAME"));    	
    	
    	//update
    	Command update = das.getCommand("updateCustomer");
    	update.setParameter("LASTNAME", "NoLouise");
    	update.setParameter("ID", new Integer(6));
    	update.execute();
    	select.setParameter("ID", new Integer(6));
    	root = select.executeQuery();
    	customers = root.getList("CUSTOMER");
    	assertEquals(1, customers.size());
    	customer = (DataObject)customers.get(0);
    	assertEquals("NoLouise", customer.get("LASTNAME"));
    	
    	//update - no param in config
    	Command updateNoParam = das.getCommand("updateCustomerNoParam");
    	updateNoParam.setParameter("LASTNAME", "YesLouise");
    	updateNoParam.setParameter("ID", new Integer(6));
    	updateNoParam.execute();
    	select.setParameter("ID", new Integer(6));
    	root = select.executeQuery();
    	customers = root.getList("CUSTOMER");
    	assertEquals(1, customers.size());
    	customer = (DataObject)customers.get(0);
    	assertEquals("YesLouise", customer.get("LASTNAME"));
    	
    	//delete
    	Command delete = das.getCommand("deleteCustomer");
    	delete.setParameter("ID", new Integer(6));
    	delete.execute();
    	select.setParameter("ID", new Integer(6));
    	root = select.executeQuery();
    	customers = root.getList("CUSTOMER");
    	assertEquals(0, customers.size());

    	//delete - cfg command with no param
    	Command deleteNoParam = das.getCommand("deleteCustomerNoParam");
    	deleteNoParam.setParameter("ID", new Integer(7));
    	deleteNoParam.execute();
    	select.setParameter("ID", new Integer(7));
    	root = select.executeQuery();
    	customers = root.getList("CUSTOMER");
    	assertEquals(0, customers.size());    	
    }
    
    /**
     * Test passing <Parameter>List to Command through program
     * @throws Exception
     */    
    public void testProgrammaticCommandNamedParameters() throws Exception {
    	ConfigHelper helper = new ConfigHelper();    	
	    //Command insertAdhoc = das.createCommand("insert into CUSTOMER values (?, ?, ?)");
    	org.apache.tuscany.das.rdb.config.Command insertAdhoc = ConfigFactory.INSTANCE.createCommand();
    	insertAdhoc.setKind("insert");
    	insertAdhoc.setSQL("insert into CUSTOMER values (?, ?, ?)");
    	insertAdhoc.setName("insertCustomer");
	    Parameter parameter1 = ConfigFactory.INSTANCE.createParameter();
		parameter1.setName("ID");
		Parameter parameter2 = ConfigFactory.INSTANCE.createParameter();
		parameter2.setName("LASTNAME");
		Parameter parameter3 = ConfigFactory.INSTANCE.createParameter();
		parameter3.setName("ADDRESS");
		insertAdhoc.getParameter().add(parameter1);
		insertAdhoc.getParameter().add(parameter2);
		insertAdhoc.getParameter().add(parameter3);
		Config cfg = helper.getConfig();
		cfg.getCommand().add(insertAdhoc);
		DAS das = DAS.FACTORY.createDAS(cfg, getConnection());
		
		//setup is over, now set values in params and execute
		Command insert = das.getCommand("insertCustomer");
		insert.setParameter("ID", new Integer(6));
		insert.setParameter("LASTNAME", "Anthony");
		insert.setParameter("ADDRESS", "IND");
		insert.execute();
		
		Command select = das.createCommand("select * from CUSTOMER where ID = ?");
    	select.setParameter("ID", new Integer(6));
    	DataObject root = select.executeQuery();
    	List customers = root.getList("CUSTOMER");
    	assertEquals(1, customers.size());
    	DataObject customer = (DataObject)customers.get(0);
    	assertEquals("Anthony", customer.get("LASTNAME"));
    }
    
    /**
     * Test set/getParameter(name) on Command
     * @throws Exception
     */    
    public void testNamedParameters() throws Exception {
	    DAS das = DAS.FACTORY.createDAS(getConnection());
	    Command insertAdhoc = das.createCommand("insert into CUSTOMER values (?, ?, ?)");
	    insertAdhoc.setParameter("ID", new Integer(6));
	    insertAdhoc.setParameter("LASTNAME", "MyLastName");
	    insertAdhoc.setParameter("ADDRESS", "MyLastAddress");
	    assertEquals("MyLastAddress", insertAdhoc.getParameter("ADDRESS"));
	    insertAdhoc.execute();
	    
	    Command select = das.createCommand("select * from CUSTOMER where ID = ?");
    	select.setParameter("ID", new Integer(6));
    	DataObject root = select.executeQuery();
    	List customers = root.getList("CUSTOMER");
    	assertEquals(1, customers.size());
    	DataObject customer = (DataObject)customers.get(0);
    	assertEquals("MyLastName", customer.get("LASTNAME"));	    
    	
    }    
}
