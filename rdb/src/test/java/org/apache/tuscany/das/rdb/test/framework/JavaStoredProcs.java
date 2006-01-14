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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Stored Procedures for DB2 and Derby SP tests
 *
 */
public class JavaStoredProcs {

	public static void getAllCompanies(ResultSet[] results) throws SQLException {

		Connection conn = DriverManager
				.getConnection("jdbc:default:connection");
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM COMPANY");
		results[0] = ps.executeQuery();
	}
	
	public static void deleteCustomer(int theId) throws SQLException {

		Connection conn = DriverManager
				.getConnection("jdbc:default:connection");
		PreparedStatement ps = conn.prepareStatement("DELETE FROM CUSTOMER WHERE ID = ?");
		ps.setInt(1, theId);
		ps.execute();
		
	}

	public static void getNamedCompany(String theName, ResultSet[] results) throws SQLException {

		Connection conn = DriverManager
				.getConnection("jdbc:default:connection");
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM COMPANY WHERE NAME = ?");
		ps.setString(1, theName);
		results[0] = ps.executeQuery();
	}
	
	public static void getCustomerAndOrders(int theId, ResultSet[] results) throws SQLException {

		Connection conn = DriverManager
				.getConnection("jdbc:default:connection");
		PreparedStatement ps = 
			conn.prepareStatement("SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID WHERE CUSTOMER.ID = ?");
		ps.setInt(1, theId);
		results[0] = ps.executeQuery();
	}
	
	public static void getNamedCustomers(String theName, int[] outCount, ResultSet[] results) throws SQLException {

		Connection conn = DriverManager
				.getConnection("jdbc:default:connection");
		PreparedStatement ps = 
			conn.prepareStatement("SELECT * FROM CUSTOMER WHERE LASTNAME = ?");
		ps.setString(1, theName);
		results[0] = ps.executeQuery();
		
		ps = conn.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE LASTNAME = ?");
		ps.setString(1, theName);

		ResultSet rs = ps.executeQuery();
		rs.next();
		outCount[0] = rs.getInt(1);
	}
		
}
