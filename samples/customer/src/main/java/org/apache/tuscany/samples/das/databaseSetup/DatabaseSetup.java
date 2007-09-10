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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringTokenizer;

public class DatabaseSetup{
    protected Statement s;
    protected String platformName = "Not initialized";
    protected String driverName = "Not initialized";
    protected String databaseURL = "Not initialized";
    protected String databaseName = null; 
    protected String userName;
    protected String password;
    
    // Data Types
    public static final String stringType = "VARCHAR";
    public static final String integerType = "INT";
    public static final String timestampType = "TIMESTAMP";
    public static final String floatType = "FLOAT";
    public static final String decimalType = "DECIMAL";
    protected Connection connection;
    
    //database types
    public static final String DERBY = "derby";
    public static final String MYSQL = "mysql";
    public static final String DB2 = "db2";
    
    public DatabaseSetup(String databaseInfo){
    	StringTokenizer strTok = new StringTokenizer(databaseInfo, "-");
    	
    	if(strTok.hasMoreTokens()){
    		databaseName =  strTok.nextToken();
    	}
    	
    	if(strTok.hasMoreTokens()){
    		userName = strTok.nextToken();
    	}
    	
    	if(strTok.hasMoreTokens()){
    		password = strTok.nextToken();
    	}
    	
        initConnectionProtocol(databaseName);
        initConnection();
        try{
        	this.setUp();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }

    protected void initConnectionProtocol( String databaseName){
    }

    private void initConnection() {

        try {
            Class.forName(driverName).newInstance();
            Properties props = new Properties();
            //System.out.println("platform name:"+this.platformName);
            
            if(this.platformName.equals(DERBY)){
                if (userName != null) {
                	//System.out.println("derby trying for "+userName+","+password+","+databaseURL);
                    connection = DriverManager.getConnection(databaseURL, userName, password);
                } else {
                  	//System.out.println("derby trying for "+databaseURL);                                  	
                    connection = DriverManager.getConnection(databaseURL);
                }            	
            }
            if(this.platformName.equals(DB2)){
            	if (userName != null && password != null) {
            		props.put("user", userName);
            		props.put("password", password);
            		
            		//System.out.println("db2 trying for "+databaseURL+"user,pwd"+userName+","+password);
            		connection = DriverManager.getConnection(databaseURL, props);            		
            	}else{
            		//System.out.println("db2 trying for "+databaseURL);
            		connection = DriverManager.getConnection(databaseURL);
            	}            	
            }
            if(this.platformName.equals(MYSQL)){
            	databaseURL=databaseURL+"?user="+userName+"&password="+password+"&createDatabaseIfNotExist="+"true";
              	//System.out.println("mysql trying for "+userName+","+password+","+databaseURL);                
            	connection = DriverManager.getConnection(databaseURL, props);
            }

            connection.setAutoCommit(true);
        } catch (SQLException e) {
        	e.printStackTrace();
            if (e.getNextException() != null) {
                e.getNextException().printStackTrace();
            } else {
                e.printStackTrace();
            }

            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public Connection getConnection(){
    	return this.connection;
    }
    
    protected void setUp() throws Exception {
        System.out.println("Setting up for " + platformName + " run!");
    }

    private void insertData(){
    }
    
    private void dropTables() {        
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
    }

    private void createTables() {
    }

    protected void createProcedures() {
    }

    //
    // This section povides methods that return strings for table creation.
    // Platform-specific sublcasses
    // can override these as necessary
    //

    protected String getCreateCustomer() {
    	return null;
    }
    // /////////////////

    protected String getForeignKeyConstraint(String pkTable, String pkColumn, String foreignKey) {
    	return null;
    }

    protected String getStringColumn(String name, int length) {
    	return null;
    }

    protected String getIntegerColumn(String name) {
    	return null;
    }

    protected String getGeneratedKeyClause() {
    	return null;
    }

    protected String getDecimalColumn(String name, int size1, int size2) {
    	return null;
    }

    protected String getFloatColumn(String name) {
    	return null;
    }

    protected String getTimestampColumn(String name) {
    	return null;
    }        
}