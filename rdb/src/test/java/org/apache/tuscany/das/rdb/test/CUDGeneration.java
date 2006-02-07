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

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
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

		// Read customer with particular ID
		Command select = Command.FACTORY
				.createCommand("Select * from CUSTOMER where ID = 1");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();

		DataObject customer = (DataObject) root.get("CUSTOMER[1]");

		// Modify customer
		customer.set("LASTNAME", "Pavick");

		// Build apply changes command
		ApplyChangesCommand apply = Command.FACTORY
				.createApplyChangesCommand(getBasicCustomerMappingWithInvalidCUD());
		apply.setConnection(getConnection());

		// Flush changes
		try {
			apply.execute(root);
			fail("Should fail with invalid SQL.  Provided CUD not used!!");
		} catch (RuntimeException e) {
			// Everything OK
		}

	}

	public void testInsertCUDGeneration() throws Exception {
		Command select = Command.FACTORY
				.createCommand("Select * from CUSTOMER where ID = 1");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();

		DataObject customer = root.createDataObject("CUSTOMER");
		customer.setInt("ID", 720);
		customer.set("LASTNAME", "foobar");
		customer.set("ADDRESS", "asdfasdf");

		ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand();
		apply.addPrimaryKey("CUSTOMER.ID");
		apply.setConnection(getConnection());
		apply.execute(root);

		select = Command.FACTORY
				.createCommand("select * from CUSTOMER where ID = 720");
		select.setConnection(getConnection());
		root = select.executeQuery();

		assertEquals(1, root.getList("CUSTOMER").size());
	}

	public void testReadModifyApply() throws Exception {

		// Build the select command to read a specific customer and related
		// orders
		Command select = Command.FACTORY
				.createCommand(
						"SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = :ID",
						getMappingModel_1xM_uni_no_cud_as_stream());

		// Parameterize the command
		select.setConnection(getConnection());
		select.setParameterValue("ID", new Integer(1));

		// Get the graph
		DataObject root = select.executeQuery();

		// Modify a customer
		DataObject customer = (DataObject) root.get("Customer[1]");
		customer.set("LASTNAME", "Pavick");

		// Modify an order
		DataObject order = (DataObject) customer.get("orders[1]");
		order.setString("PRODUCT", "Kitchen Sink 001");

		ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand();
		apply.setConnection(getConnection());
		apply.setMapping(getMappingModel_1xM_uni_no_cud_as_stream());

		// Flush changes
		apply.execute(root);

	}

	/**
	 * Same as previous version but uses explicit model creation
	 */
	public void testReadModifyApply2() throws Exception {

		// Build the select command
		Command select = Command.FACTORY
				.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = :ID");

		// Set minimum metadata necessary to describe relationship
		select.addRelationship("CUSTOMER.ID", "ANORDER.CUSTOMER_ID");
		select.addPrimaryKey("CUSTOMER.ID");
		select.addPrimaryKey("ANORDER.ID");

		// Parameterize the command
		select.setConnection(getConnection());
		select.setParameterValue("ID", new Integer(1));

		// Get the graph
		DataObject root = select.executeQuery();

		// Modify a customer
		DataObject customer = (DataObject) root.get("CUSTOMER[1]");
		customer.set("LASTNAME", "Pavick");

		// Modify an order
		DataObject order = (DataObject) customer.getList("ANORDER").get(0);
		order.setString("PRODUCT", "Kitchen Sink 001");

		ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand();
		apply.setConnection(getConnection());
		apply.addRelationship("CUSTOMER.ID", "ANORDER.CUSTOMER_ID");
		apply.addPrimaryKey("CUSTOMER.ID");
		apply.addPrimaryKey("ANORDER.ID");
		// Flush changes
		apply.execute(root);

	}

	// Utilities
	// private FileInputStream getBasicCustomerMappingWithCUD()
	// throws FileNotFoundException {
	// return new FileInputStream("basicCustomerMappingWithCUD.xml");
	// }

	private InputStream getBasicCustomerMappingWithInvalidCUD()
			throws FileNotFoundException {
//		return new FileInputStream("src/test/resources/basicCustomerMappingWithInvalidCUD.xml");
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("basicCustomerMappingWithInvalidCUD.xml");
	}

	private InputStream getMappingModel_1xM_uni_no_cud_as_stream()
			throws FileNotFoundException {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("1xM_mapping_no_cud.xml");
	}

	/**
	 * Provides only a definition of the table
	 */
	// private FileInputStream getBasicCustomerMappingModel()
	// throws FileNotFoundException {
	// return new FileInputStream("basicCustomerMapping.xml");
	// }
}
