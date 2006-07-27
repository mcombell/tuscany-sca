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
import java.sql.SQLException;
import java.sql.Statement;

import junit.extensions.TestSetup;
import junit.framework.Test;

public class DatabaseSetup extends TestSetup {

    protected String platformName = "Not initialized";

    protected String driverName = "Not initialized";

    protected String databaseURL = "Not initialized";

    private Connection connection;

    protected Statement s;

    public DatabaseSetup(Test test) {
        super(test);
        initConnectionProtocol();
        initConnection();
        DasTest.connection = connection;
    }

    protected void initConnectionProtocol() {
        // Subclasses provide implementation
    }

    private void initConnection() {

        try {

            Class.forName(driverName).newInstance();
            connection = DriverManager.getConnection(databaseURL);
            connection.setAutoCommit(false);

        } catch (Exception e) {
            if (e instanceof SQLException) {
            	if ( ((SQLException) e).getNextException() != null )
            		((SQLException) e).getNextException().printStackTrace();
            	else	
            		e.printStackTrace();
            			
            }
                
            throw new RuntimeException(e);
        }

    }

    protected void setUp() throws Exception {

        System.out.println("Setting up for " + platformName + " run");

        s = connection.createStatement();

        try {
            dropTables();
            dropProcedures();
            createTables();
            createProcedures();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

    }

    protected void tearDown() throws Exception {

        System.out.println("Ending " + platformName + " run");
        connection.close();

    }

    private void dropTables() {

//        System.out.println("Dropping tables");

        String[] statements = {

        "DROP TABLE CUSTOMER", "DROP TABLE ANORDER", "DROP TABLE ORDERDETAILS", "DROP TABLE ITEM",
                "DROP TABLE COMPANY", "DROP TABLE EMPLOYEE", "DROP TABLE DEPARTMENT", "DROP TABLE BOOK",
                "DROP TABLE PART", "DROP TABLE TYPETEST", "DROP TABLE CITIES", "DROP TABLE STATES",
                "DROP TABLE conmgt.SERVERSTATUS"

        };

        for (int i = 0; i < statements.length; i++) {
            try {
                s.execute(statements[i]);
            } catch (SQLException e) {
                // If the table does not exist then ignore the exception on drop
                if ((!e.getMessage().contains("does not exist")) &&
                		(!e.getMessage().contains("Unknown table")))
                    throw new RuntimeException(e);
            }
        }
    }

    protected void dropProcedures() {

//        System.out.println("Dropping procedures");

        String[] statements = {

        "DROP PROCEDURE GETALLCOMPANIES", "DROP PROCEDURE DELETECUSTOMER", "DROP PROCEDURE GETNAMEDCOMPANY",
                "DROP PROCEDURE GETCUSTOMERANDORDERS", "DROP PROCEDURE GETNAMEDCUSTOMERS"

        };

        for (int i = 0; i < statements.length; i++) {
            try {
                s.execute(statements[i]);
            } catch (SQLException e) {
                // If the proc does not exist then ignore the exception on drop
                if (!e.getMessage().contains("does not exist"))
                    throw new RuntimeException(e);
            }
        }
    }

    private void createTables() {

//        System.out.println("Creating tables");

        try {

            s.execute(getCreateCustomer());
            s.execute(getCreateAnOrder());
            s.execute(getCreateOrderDetails());
            s.execute(getCreateItem());
            s.execute(getCreateCompany());
            s.execute(getCreateEmployee());
            s.execute(getCreateDepartment());
            s.execute(getCreateBook());
            s.execute(getCreatePart());
            s.execute(getCreateTypeTest());
            s.execute(getCreateStates());
            s.execute(getCreateCities());
            s.execute(getCreateServerStatus());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void createProcedures() {

//        System.out.println("Creating procedures");
        try {

            s.execute("CREATE PROCEDURE GETALLCOMPANIES() PARAMETER STYLE JAVA LANGUAGE JAVA READS SQL DATA DYNAMIC RESULT SETS 1 EXTERNAL NAME 'org.apache.tuscany.das.rdb.test.framework.JavaStoredProcs.getAllCompanies'");
            s.execute("CREATE PROCEDURE DELETECUSTOMER(theId int) PARAMETER STYLE JAVA LANGUAGE JAVA MODIFIES SQL DATA EXTERNAL NAME 'org.apache.tuscany.das.rdb.test.framework.JavaStoredProcs.deleteCustomer'");
            s.execute("CREATE PROCEDURE GETNAMEDCOMPANY(theName VARCHAR(100)) PARAMETER STYLE JAVA LANGUAGE JAVA READS SQL DATA DYNAMIC RESULT SETS 1 EXTERNAL NAME 'org.apache.tuscany.das.rdb.test.framework.JavaStoredProcs.getNamedCompany'");
            s.execute("CREATE PROCEDURE GETCUSTOMERANDORDERS(theID INTEGER) PARAMETER STYLE JAVA LANGUAGE JAVA READS SQL DATA DYNAMIC RESULT SETS 1 EXTERNAL NAME 'org.apache.tuscany.das.rdb.test.framework.JavaStoredProcs.getCustomerAndOrders'");
            s.execute("CREATE PROCEDURE GETNAMEDCUSTOMERS(theName VARCHAR(100), OUT theCount INTEGER) PARAMETER STYLE JAVA LANGUAGE JAVA READS SQL DATA DYNAMIC RESULT SETS 1 EXTERNAL NAME 'org.apache.tuscany.das.rdb.test.framework.JavaStoredProcs.getNamedCustomers'");
            // TODO - "GETNAMEDCUSTOMERS" is failing on DB2 with SQLCODE: 42723. Need to investigate
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //
    // This section povides methods that return strings for table creation.
    // Platform-specific sublcasses
    // can override these as necessary
    //

    protected String getCreateCustomer() {
        return "CREATE TABLE CUSTOMER (ID INT PRIMARY KEY NOT NULL, LASTNAME VARCHAR(30) DEFAULT 'Garfugengheist', ADDRESS VARCHAR(30))";
    }

    protected String getCreateAnOrder() {
        return "CREATE TABLE ANORDER (ID INT PRIMARY KEY NOT NULL, PRODUCT VARCHAR(30), QUANTITY INT, CUSTOMER_ID INT)";
    }

    protected String getCreateOrderDetails() {
        return "CREATE TABLE ORDERDETAILS (ORDERID INT NOT NULL, PRODUCTID INT NOT NULL, PRICE FLOAT, PRIMARY KEY (ORDERID, PRODUCTID))";
    }

    protected String getCreateItem() {
        return "CREATE TABLE ITEM (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(30))";
    }

    protected String getCreateCompany() {
        return "CREATE TABLE COMPANY (ID INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY ,NAME VARCHAR(30), EOTMID INT)";
    }

    protected String getCreateEmployee() {
        return "CREATE TABLE EMPLOYEE (ID INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY, NAME VARCHAR(30), SN VARCHAR(10), MANAGER SMALLINT, DEPARTMENTID INT)";
    }

    protected String getCreateDepartment() {
        return "CREATE TABLE DEPARTMENT (ID INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY, NAME VARCHAR(30), LOCATION VARCHAR(30), NUMBER VARCHAR(10), COMPANYID INT)";
    }

    protected String getCreateBook() {
        return "CREATE TABLE BOOK (BOOK_ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(50), AUTHOR VARCHAR(30), QUANTITY INT, OCC INTEGER)";
    }

    protected String getCreatePart() {
        return "CREATE TABLE PART (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(50), QUANTITY INT, PARENT_ID INT )";
    }

    protected String getCreateTypeTest() {
        return "CREATE TABLE TYPETEST (ID INT PRIMARY KEY NOT NULL, ATIMESTAMP TIMESTAMP, ADECIMAL DECIMAL(9,2), AFLOAT FLOAT)";
    }

    protected String getCreateStates() {
        return "CREATE TABLE STATES (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(2))";
    }

    protected String getCreateCities() {
        return "CREATE TABLE CITIES (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(50), STATE_ID INT, CONSTRAINT FK1 FOREIGN KEY (STATE_ID) REFERENCES STATES (ID) ON DELETE NO ACTION ON UPDATE NO ACTION)";
    }

    protected String getCreateServerStatus() {

        return "CREATE TABLE CONMGT.SERVERSTATUS (STATUSID INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1), MANAGEDSERVERID INTEGER NOT NULL, TIMESTAMP TIMESTAMP NOT NULL)";

    }

}
