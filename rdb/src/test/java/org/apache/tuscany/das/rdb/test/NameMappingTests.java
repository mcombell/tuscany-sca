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
 * 
 * 
 */

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;


public class NameMappingTests extends DasTest {

	protected void setUp() throws Exception {
		super.setUp();
		
		new CustomerData(getAutoConnection()).refresh();
		new OrderData(getAutoConnection()).refresh();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test ability to assign DataObject type and propertyaliases with xml file
	 */
	public void testRead() throws Exception {

		// Read a customer
		Command select = Command.FACTORY.createCommand(
				"SELECT * FROM CUSTOMER WHERE CUSTOMER.ID = 1", getCustomerMapping());
		select.setConnection(getConnection());

		DataObject root = select.executeQuery();
		DataObject customer = root.getDataObject("Customer[1]");
		assertEquals(1, customer.getInt("id"));
		assertEquals("1212 foobar lane", customer.getString("address"));
		assertEquals("Williams", customer.getString("lastname"));
		
	}


	// Utilities

	private InputStream getCustomerMapping()
			throws FileNotFoundException {
//		return new FileInputStream("src/test/resources/customerMapping.xml");
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("customerMapping.xml");
	}


}
