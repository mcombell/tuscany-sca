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


import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;


public class StoredProcs extends DasTest {

	protected void setUp() throws Exception {
		super.setUp();

		new CompanyData(getAutoConnection()).refresh();
		new CustomerData(getAutoConnection()).refresh();
		new OrderData(getAutoConnection()).refresh();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// Call a simple stored proc to read all companies
	public void testGetCompanies() throws Exception {
		
		Command read = Command.FACTORY.createCommand("{call GETALLCOMPANIES()}");
		read.setConnection(getConnection());
		DataObject root = read.executeQuery();

		//Verify
		assertEquals(3, root.getList("COMPANY").size());
		assertTrue(root.getInt("COMPANY[1]/ID") > 0);

	}

	public void testGetNamedCompany() throws Exception {

		Command read = Command.FACTORY.createCommand("{call GETNAMEDCOMPANY(?)}");

		read.setConnection(getConnection());
		read.setParameterValue(1, "MegaCorp");
		DataObject root = read.executeQuery();

		assertEquals("MegaCorp", root.getString("COMPANY[1]/NAME"));

	}

	public void testGetNamedCompanyByName() throws Exception {
		Command read = Command.FACTORY
				.createCommand("{call GETNAMEDCOMPANY(:NAME)}");

		read.setConnection(getConnection());
		read.setParameterValue("NAME", "MegaCorp");
		DataObject root = read.executeQuery();

		assertEquals("MegaCorp", root.getString("COMPANY[1]/NAME"));
	}


	// Retreive heirarchy using a stored proc ... new programming model
	public void testGetCustomersAndOrder() throws Exception {

		Command read = Command.FACTORY.createCommand("{call getCustomerAndOrders(?)}");
		read.setConnection(getConnection());
		read.setParameterValue(1, new Integer(1));

		//Set minimum metadata necessary to describe relationship
		read.addRelationship("CUSTOMER.ID", "ANORDER.CUSTOMER_ID");
		
		DataObject root = read.executeQuery();

		DataObject customer = (DataObject) root.getList("CUSTOMER").get(0);
		assertEquals(2, customer.getList("ANORDER").size());

	}

	/**
	 * Call a stored proc requiring an in parameter and producing an out
	 * parameter and a resultset
	 * 
	 * This stored proc takes a lastname argument and returns a graph of
	 * customers with that last name. The number of read customers is returned
	 * in the out parameter
	 */
	public void testGetNamedCustomers() throws Exception {

		Command read = Command.FACTORY.createCommand("{call GETNAMEDCUSTOMERS(?,?)}");
		read.setConnection(getConnection());
		read.setParameterValue(1, "Williams");
		read.addParameter(2, Parameter.OUT, SDODataTypes.INTEGER);
		DataObject root = read.executeQuery();		
		
		Integer customersRead = (Integer) read.getParameterValue(2);

		assertEquals(4, customersRead.intValue());
		assertEquals(customersRead.intValue(), root.getList("CUSTOMER").size());

	}
	
	//TODO - Resolve issue with programmatic creation of GETNAMEDCUSTOMERS on DB2 and
	//re-enable this test
	
	
	// Simplest possible SP write
	public void testDelete() throws Exception {
		
		Command delete = Command.FACTORY.createCommand("{call DELETECUSTOMER(?)}");	
		delete.setConnection(getConnection());
		delete.setParameterValue(1, new Integer(1));
		delete.execute();

		// Verify DELETE
		Command select = Command.FACTORY.createCommand("Select * from CUSTOMER where ID = 1");	
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();
		assertTrue(root.getList("CUSTOMER").isEmpty());

	}

/*	// For debug
	public void testRawCall() throws Exception {

		Connection c = getConnection();
		CallableStatement cs = c.prepareCall("{call GETNAMEDCUSTOMERS(?,?)}");
		ParameterMetaData pm = cs.getParameterMetaData();
		int count = pm.getParameterCount();
		for (int i = 1; i <= count; i++) {
			int mode = pm.getParameterMode(i);
			if (mode == ParameterMetaData.parameterModeOut
					|| mode == ParameterMetaData.parameterModeInOut)
				cs.registerOutParameter(i, pm.getParameterType(i));
		}
		cs.setString(1, "Williams");
		// cs.registerOutParameter(2,java.sql.Types.INTEGER);
		boolean isResultSet = cs.execute();
		System.out.println("Has a result set => " + isResultSet);
		ResultSet rs = cs.getResultSet();

		if (isResultSet) {
			System.out.println("Results are: ");
			while (rs.next()) {
				System.out.println(rs.getObject(2));
			}
		}
		System.out.println("Count is =>" + cs.getObject(2));
		c.commit();
	}

	// For debug
	public void testRawCall2() throws Exception {

		Connection c = getConnection();
		CallableStatement cs = c.prepareCall("{call getCustomerAndOrders(?)}");
		cs.setObject(1, new Integer(1));
		boolean isResultSet = cs.execute();
		System.out.println("call getCustomerAndOrders(?) has a result set => "
				+ isResultSet);
		ResultSet rs = cs.getResultSet();

		write(rs);
		c.commit();
	}

	public void testGetAllOrders() throws Exception {

		System.out.println("all orders");
		Connection c = getConnection();
		PreparedStatement s = c.prepareStatement("select * from anorder");
		write (s.executeQuery());
		c.commit();

	}*/
	
}