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

import java.sql.SQLException;

import junit.framework.Test;

public class MySQLSetup extends DatabaseSetup {

	public MySQLSetup(Test test) {
		super(test);
	}

	protected void initConnectionProtocol() {
		
		platformName = "MySQL";
		driverName = "com.mysql.jdbc.Driver";
		databaseURL = "jdbc:mysql:///dastest?user=dastester&password=dastester";

	}
	
	
	protected void createProcedures () {
		
		String createGetAllCompanies = 
			"CREATE PROCEDURE `dastest`.`GETALLCOMPANIES` () "+ 
			"	SELECT * FROM COMPANY ";
		  
		  
		String createDeleteCustomer = 
			"CREATE PROCEDURE `dastest`.`DELETECUSTOMER` (theId INT) " +
		    "  DELETE FROM CUSTOMER WHERE ID = theId ";


//		String createGetNamedCustomers = 
//			"CREATE PROCEDURE `dastest`.`GETNAMEDCUSTOMERS`(IN thename VARCHAR(30), OUT theCount INTEGER ) " +
//		    " BEGIN " +
//		    "  SELECT * FROM CUSTOMER AS CUSTOMER WHERE LASTNAME = theName; " +
//            "  SET theCount =  (SELECT COUNT(*) FROM CUSTOMER WHERE LASTNAME = theName); " +
//            " END ";
		  
		String createGetCustomerAndOrders = 
			" CREATE PROCEDURE `dastest`.`GETCUSTOMERANDORDERS` (theId INT) " +
		    " SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID WHERE CUSTOMER.ID =  theId "; 
		

		String createGetNamedCompany = 
			" CREATE PROCEDURE `dastest`.`GETNAMEDCOMPANY` (theName VARCHAR(100)) " +
		    " SELECT * FROM COMPANY WHERE NAME = theName"; 
		
		System.out.println("Creating procedures");
		try {
			
			s.execute(createGetAllCompanies);
			s.execute(createDeleteCustomer);
			s.execute(createGetNamedCompany);
			s.execute(createGetCustomerAndOrders);
//			s.execute(createGetNamedCustomers);
//TODO - add this back after DB2 problem is resolved
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	
	//Overrides for table creation
	protected String getCreateCompany() {
		return "CREATE TABLE COMPANY (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, NAME VARCHAR(30))";
	}
	protected String getCreateEmployee() {
		return "CREATE TABLE EMPLOYEE (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, NAME VARCHAR(30), SN VARCHAR(10), MANAGER SMALLINT, DEPARTMENTID INT)";
	}
	protected String getCreateDepartment() {
		return "CREATE TABLE DEPARTMENT (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, NAME VARCHAR(30), LOCATION VARCHAR(30), NUMBER VARCHAR(10), COMPANYID INT, EOTM INT)";
	}
	protected String getCreateTypeTest() {
		return "CREATE TABLE TYPETEST (ID INT PRIMARY KEY NOT NULL, ATIMESTAMP DATETIME, ADECIMAL DECIMAL(9,2), AFLOAT FLOAT)";
	}

}
