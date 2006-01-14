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
 * These are the simplest possible uses of the JDBC DAS.  In this mode, the
 * programming model is not much more than JDBC
 * 
 * The assumptions for these tests are:
 * 
 * Single type
 * Client explicitly Read/Create/Update/Delete commands
 * No O/R mapping metadata
 * SDO change history is not used
 * Dynamic DataObjects
 * No specified graph model
 * 
 * 
 */

import org.apache.tuscany.das.rdb.Command;
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

		//Create and initialize command to read customers
		Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1");	
		readCustomers.setConnection(getConnection());

		//Read
		DataObject root = readCustomers.executeQuery();
		
		//Verify 
		assertEquals(1, root.getInt("CUSTOMER[1]/ID"));
	}
	
	/**
	 * Read a specific customer
	 */
	public void testReadSingle2() throws Exception {

		//Create and initialize command to read customers
		Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1");	
		readCustomers.setConnection(getConnection());

		//Read
		DataObject root = readCustomers.executeQuery();
		
		DataObject cust = root.getDataObject("CUSTOMER[1]");
		
		int n = (cust.getType().getProperties()).size();
		for (int i=0; i<n; i++) {
			System.out.println(cust.get(i));
		}
		
		
		//Verify 
		assertEquals(1, root.getInt("CUSTOMER[1]/ID"));
	}
	
	/**
	 * Read a specific customer
	 * Same as above but tests tolerance of white space in provided SQL
	 */
	public void testReadSingleWithWhiteSpace() throws Exception {

		//Create and initialize command to read customers
		Command readCustomers = Command.FACTORY.createCommand("   select * from CUSTOMER where ID = 1");	
		readCustomers.setConnection(getConnection());

		//Read
		DataObject root = readCustomers.executeQuery();
		
		//Verify 
		assertEquals(1, root.getInt("CUSTOMER[1]/ID"));
	}
	
	/**
	 * Read all customers with a specific last name
	 */
	public void testReadMultiple() throws Exception {

		//Create and initialize command to read customers
		Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where LASTNAME = 'Williams'");	
		readCustomers.setConnection(getConnection());

		//Read
		DataObject root = readCustomers.executeQuery();
		
		//Verify 
		assertEquals(4, root.getList("CUSTOMER").size());
	}
	
	/**
	 * Read all customers with a specific last name 
	 * LASTNAME value is provided via a parameter
	 */
	public void testReadMultipleWithParameters() throws Exception {

		//Create and initialize command to read customers
		Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where LASTNAME = :LASTNAME");	
		readCustomers.setConnection(getConnection());

		//Parameterize the command
		readCustomers.setParameterValue("LASTNAME", "Williams");
		DataObject root = readCustomers.executeQuery();
		
		//Verify 
		assertEquals(4, root.getList("CUSTOMER").size());
	}

	public void testInsert() throws Exception {
		
		Command insert = Command.FACTORY.createCommand("insert into CUSTOMER values (10, 'Williams', '5528 Wells Fargo Dr')");	
		insert.setConnection(getConnection());
		insert.execute();

		//Verify
		Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 10");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();	
		assertEquals(1, root.getList("CUSTOMER").size());
		assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

	}

	public void testInsertWithParameters() throws Exception {
		
		Command insert = Command.FACTORY.createCommand("insert into CUSTOMER values (:ID, :LASTNAME, :ADDRESS)");	
		insert.setConnection(getConnection());
		insert.setParameterValue("ID", new Integer(10));
		insert.setParameterValue("LASTNAME", "Williams");
		insert.setParameterValue("ADDRESS", "5528 Wells Fargo Dr");
		insert.execute();

		//Verify
		Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 10");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();	
		assertEquals(1, root.getList("CUSTOMER").size());
		assertEquals("5528 Wells Fargo Dr", root.get("CUSTOMER[1]/ADDRESS"));

	}

	
	public void testDelete() throws Exception {

		//Verify pre-condition
		Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 1");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();	
		assertEquals(1, root.getList("CUSTOMER").size());	
		
		//Create and execute the delete command
		Command delete = Command.FACTORY.createCommand("delete from CUSTOMER where ID = 1");
		delete.setConnection(getConnection());
		delete.execute();
		
		//Verify delete by reusing the original select command
		root = select.executeQuery();	
		assertEquals(0, root.getList("CUSTOMER").size());
		
	}
	
	
	public void testUpdate() throws Exception {
		
		//Verify pre-condition
		Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 1");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();	
		assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));

		Command update = Command.FACTORY.createCommand("update CUSTOMER set LASTNAME = 'Pavick' where ID = 1");	
		update.setConnection(getConnection());
		update.execute();
		
		//Verify update - reuse select command
		root = select.executeQuery();	
		assertEquals("Pavick", root.get("CUSTOMER[1]/LASTNAME"));
		
	}
	
	public void testUpdateWithParameters() throws Exception {

		//Verify pre-condition
		Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 1");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();	
		assertFalse(root.get("CUSTOMER[1]/LASTNAME").equals("Pavick"));
		
		Command update = Command.FACTORY.createCommand("update CUSTOMER set LASTNAME = :LASTNAME where ID = :ID");	
		update.setConnection(getConnection());
		update.setParameterValue("LASTNAME", "Pavick");
		update.setParameterValue("ID", new Integer(1));
		update.execute();
		
		//Verify update - reuse select command
		root = select.executeQuery();	
		assertEquals("Pavick", root.get("CUSTOMER[1]/LASTNAME"));
		
	}

}