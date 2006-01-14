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
import org.apache.tuscany.das.rdb.test.company.CompanyPackage;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

public class ExceptionTests extends DasTest {

	public ExceptionTests() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		new CustomerData(getAutoConnection()).refresh();
		new OrderData(getAutoConnection()).refresh();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMissingConnection() throws Exception {
		Command readCustomers = Command.FACTORY
		.createCommand("select * from CUSTOMER where ID = 1");
		try {
			readCustomers.executeQuery();
			fail("RuntimeException should be thrown");
		} catch ( RuntimeException ex) {
			assertEquals("A DASConnection object must be specified before executing the query.", ex.getMessage());
		}
		
	}
	public void testMissingMapping() throws Exception {
		Command readCustomers = Command.FACTORY
				.createCommand("select * from CUSTOMER where ID = 1");
		readCustomers.setConnection(getConnection());
		readCustomers.setDataObjectModel(SDOUtil
				.adaptType(CompanyPackage.eINSTANCE.getDocumentRoot()));

		try {
			readCustomers.executeQuery();
			fail("Exception should be thrown");
		} catch (RuntimeException ex) {
			assertEquals("An SDO Type with name CUSTOMER was not found", ex
					.getMessage());
		}

	}

}
