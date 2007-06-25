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
package org.apache.tuscany.samples.das.customer;

import java.io.InputStream;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.util.ConfigUtil;
import org.apache.tuscany.samples.das.databaseSetup.DB2Setup;
import org.apache.tuscany.samples.das.databaseSetup.DerbySetup;
import org.apache.tuscany.samples.das.databaseSetup.MySQLSetup;

import commonj.sdo.DataObject;

public class CustomerClient {
	private DAS das = null;
	public static final String DERBY = "derby";
	public static final String MYSQL = "mysql";
	public static final String DB2 = "db2";
	
	private CustomerClient(){		
	}

	/**If database is present connect to it and create necessary tables, procedures, data etc.
	 * If database is not present create database and then create the above elements. Create database
	 * is implemented for MySQL and Derby (not for IBM DB2).
	 */
	
	protected boolean checkIfDBPresent(String dbType, String dbName, String user, String password){
    	try {
        	if(dbType.equals(DERBY)){
        		new DerbySetup(dbName+"-"+user+"-"+password);
        	}
        	
        	if(dbType.equals(DB2)){
        		new DB2Setup(dbName+"-"+user+"-"+password);
        	}
        	
        	if(dbType.equals(MYSQL)){
        		new MySQLSetup(dbName+"-"+user+"-"+password);
        	}
        	
        } catch(Exception e){
        	e.printStackTrace();
        }
        
        return true;
    }
	
	private void init(String configFile){
		try{
			this.das = DAS.FACTORY.createDAS(getConfig(configFile));
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args){
		String configFile = "Customers.xml";//this can be from input params too as below
		
		if(args != null && args.length >0){
			configFile = args[0];
		}
		
		CustomerClient cclient = new CustomerClient();
		
		Config config = ConfigUtil.loadConfig(cclient.getClass().getClassLoader().getResourceAsStream(configFile));
		
		String dbType = null;
		String dbURL = null;
		String user = null;
		String password = null;
		
		if(config.getConnectionInfo().getConnectionProperties().getDriverClass().indexOf(DERBY) != -1){
			dbType = DERBY;
		}
		
		if(config.getConnectionInfo().getConnectionProperties().getDriverClass().indexOf(MYSQL) != -1){
			dbType = MYSQL;
		}
		
		if(config.getConnectionInfo().getConnectionProperties().getDriverClass().indexOf(DB2) != -1){
			dbType = DB2;
		}
	
		//get connection info from config
		dbURL = config.getConnectionInfo().getConnectionProperties().getDatabaseURL();
		user = config.getConnectionInfo().getConnectionProperties().getUserName();
		password = config.getConnectionInfo().getConnectionProperties().getPassword();
		
		System.out.println("connection info from config***************");
		System.out.println("dbName:"+dbURL+" user:"+user+" password:"+password);
		System.out.println("******************************************");
		//dt create/connect/create schema
		cclient.checkIfDBPresent(dbType, dbURL, user, password);

		//get das handle
		cclient.init(configFile);
		
		//test select
		System.out.println("Result:select all customers");
		printList(cclient.getCustomers());
		
		//test insert
		System.out.println("Result:insert new customer");
		cclient.addCustomer();
		printList(cclient.getCustomers());
		
		//test update
		System.out.println("Result:update first customer");
		cclient.changeFirstCustomerName();
		printList(cclient.getCustomers());
		
		//test delete
		System.out.println("Result:delete last customer");
		cclient.deleteCustomer();
		printList(cclient.getCustomers());
	}

	/**
	 * display result 
	 * @param customers
	 */
	public static void printList(List customers){
		for(int i=0; i<customers.size(); i++){
			System.out.println("   ID:"+(((DataObject)customers.get(i)).getInt("ID"))+
						" LASTNAME:"+(((DataObject)customers.get(i)).getString("LASTNAME"))+ 
						" ADDRESS:"+(((DataObject)customers.get(i)).getString("ADDRESS")));
		}
	}
	
	/**
	 * select
	 * @return
	 */
    public final List getCustomers() {

        Command read = das.getCommand("AllCustomers");
        DataObject root = read.executeQuery();
        return root.getList("CUSTOMER");
    }

    /**
     * insert
     *
     */
    public final void addCustomer() {
        Command read = das.getCommand("AllCustomers");
        DataObject root = read.executeQuery();

        DataObject newCustomer = root.createDataObject("CUSTOMER");
        newCustomer.setInt("ID", 5);
        newCustomer.setString("LASTNAME", "Jenny");
        newCustomer.setString("ADDRESS", "USA");

        das.applyChanges(root);
    }

    /**
     * delete
     *
     */
    public final void deleteCustomer() {
        Command readAll = das.getCommand("AllCustomers");
        DataObject root = readAll.executeQuery();

        List allCustomers = root.getList("CUSTOMER");

        DataObject lastCustomer = (DataObject)allCustomers.get(allCustomers.size()-1);
        if(lastCustomer != null){
        	System.out.println("Deleting customer named: " + lastCustomer.getString("LASTNAME"));
        	lastCustomer.delete();
        }

        das.applyChanges(root);
    }

    /**
     * update
     *
     */
    public final void changeFirstCustomerName() {
        Command readAll = das.getCommand("AllCustomers");
        DataObject root = readAll.executeQuery();

        DataObject firstCustomer = root.getDataObject("CUSTOMER[1]");
        firstCustomer.set("LASTNAME", "BlueBerry");
        
        das.applyChanges(root);
    }
        
    /**
     * cleanup
     *
     */
    public void releaseResources() {
        das.releaseResources();
    }

    /**Utilities
     * 
     * @param fileName
     * @return
     */
    private InputStream getConfig(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}