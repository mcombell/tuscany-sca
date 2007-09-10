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
package org.apache.tuscany.samples.das.databaseSetup;

import java.sql.SQLException;

public class MySQLSetup extends DatabaseSetup {

    public MySQLSetup(String databaseInfo){
        super(databaseInfo);        
    }

    protected void initConnectionProtocol(String databaseURL){
    	this.platformName=MYSQL;
        this.driverName = "com.mysql.jdbc.Driver";
        this.databaseURL = databaseURL;
    }

    public void setUp(){
    	try{
    		super.setUp();    		

            try {
            	s = connection.createStatement();
            	
                dropTriggers();
                dropSequences();
                dropTables();
                dropProcedures();

                createSequences();
                createTables();
                createTriggers();
                createProcedures();
                insertData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}  
    	
    	System.out.println("Database setup complete!");
    }
    
    private void insertData(){
    	System.out.println("Inserting data in tables");
    	
        String[] statements = {"INSERT INTO CUSTOMER VALUES (1,'John','USA')",
        		"INSERT INTO CUSTOMER VALUES (2,'Amita','INDIA')",
        		"INSERT INTO CUSTOMER VALUES (3,'Patrick','UK')",
        		"INSERT INTO CUSTOMER VALUES (4,'Jane','UN')"};

        for (int i = 0; i < statements.length; i++) {
            try {                	
                s.execute(statements[i]);
            } catch (SQLException e) {
                // If the table does not exist then ignore the exception on drop
                if ((!(e.getMessage().indexOf("does not exist") >= 0)) && (!(e.getMessage().indexOf("Unknown table") >= 0)) 
                        && (!(e.getMessage().indexOf("42704") >= 0))) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    private void dropTables() {
        System.out.println("Dropping tables");

        String[] statements = {"DROP TABLE CUSTOMER"};

        for (int i = 0; i < statements.length; i++) {
            try {
                s.execute(statements[i]);
            } catch (SQLException e) {
                // If the table does not exist then ignore the exception on drop
                if ((!(e.getMessage().indexOf("does not exist") >= 0)) && (!(e.getMessage().indexOf("Unknown table") >= 0)) 
                        && (!(e.getMessage().indexOf("42704") >= 0))) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    protected void dropTriggers() {

    }

    protected void createTriggers() {

    }

    protected void dropSequences() {

    }

    protected void createSequences() {

    }

    protected void dropProcedures() {

        System.out.println("Dropping procedures");

        String[] statements = {"DROP PROCEDURE GETNAMEDCUSTOMERS"};

        for (int i = 0; i < statements.length; i++) {
            try {
                s.execute(statements[i]);
            } catch (SQLException e) {
                // If the proc does not exist then ignore the exception on drop
                if (!(e.getMessage().indexOf("does not exist") >= 0) && !(e.getMessage().indexOf("42704") >= 0)) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void createTables() {
         System.out.println("Creating tables");

        try {

            s.execute(getCreateCustomer());

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
        return "CREATE TABLE CUSTOMER (" + getIntegerColumn("ID") + " PRIMARY KEY NOT NULL, " 
            + getStringColumn("LASTNAME", 30)
                + " DEFAULT 'Garfugengheist', " + getStringColumn("ADDRESS", 30) + ")";
    }

    // /////////////////

    protected String getForeignKeyConstraint(String pkTable, String pkColumn, String foreignKey) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("CONSTRAINT FK1 FOREIGN KEY (");
        buffer.append(foreignKey);
        buffer.append(") REFERENCES ");
        buffer.append(pkTable);
        buffer.append("(");
        buffer.append(pkColumn);
        buffer.append(") ON DELETE NO ACTION ON UPDATE NO ACTION");
        return buffer.toString();
    }

    protected String getStringColumn(String name, int length) {
        return name + ' ' + stringType + "(" + new Integer(length).toString() + ")";
    }

    protected String getIntegerColumn(String name) {
        return name + ' ' + integerType;
    }

    protected String getDecimalColumn(String name, int size1, int size2) {
        return name + ' ' + decimalType + "(" + new Integer(size1).toString() + ',' 
            + new Integer(size2).toString() + ")";
    }

    protected String getFloatColumn(String name) {
        return name + ' ' + floatType;
    }

    protected String getTimestampColumn(String name) {
        return name + ' ' + timestampType;
    }        
    
    protected void createProcedures() {
        String createGetNamedCustomers = "CREATE PROCEDURE `dastest`.`GETNAMEDCUSTOMERS`(IN thename VARCHAR(30), "
                + "OUT theCount INTEGER ) " + " BEGIN "
                + "  SELECT * FROM CUSTOMER AS CUSTOMER WHERE LASTNAME = theName; "
                + "  SET theCount =  (SELECT COUNT(*) FROM CUSTOMER WHERE LASTNAME = theName); " + " END ";
        
        System.out.println("Creating procedures");
        try {
            s.execute(createGetNamedCustomers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getGeneratedKeyClause() {
        return "AUTO_INCREMENT";
    }
}
