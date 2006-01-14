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
package org.apache.tuscany.das.rdb.test.framework;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;

/**
 *
 */
public class DasTest extends TestCase {

	public static Connection connection = null;
	private boolean usingDefaultSetup = false;

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() throws Exception {
//		if (usingDefaultSetup)
//			connection = null;
	}

	protected Connection getAutoConnection() throws SQLException {
		
		Connection c = primGetConnection();
		c.setAutoCommit(true);
		return connection;

	}

	protected Connection getConnection() throws SQLException {
		
		Connection c = primGetConnection();
		c.setAutoCommit(false);
		return connection;
	}
	
	/**
	 * This provides the default connection for runing single test cases on a 
	 * chosen platform.    
	 */
	private Connection primGetConnection() {
		if (connection == null)
			defaultSetup();
		return connection;
	}


	
	/**
	 * This is a bit of a hack since it counts on constructor initialization of the 
	 * DatabaseSet up class and also calls its setUp method directly.  This is a misuse 
	 * of this JUnit TestSetup subclass .
	 * 
	 * TODO - refactor to avoid this hackiness ... could move this logic to its own
	 * class that is then invoked by DatabaseSetUp
	 */
	private void defaultSetup() {
		
		usingDefaultSetup = true;
		
//		DatabaseSetup setUp = new DB2Setup(this);
        DatabaseSetup setUp = new DerbySetup(this);
		try {
			setUp.setUp();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
}
