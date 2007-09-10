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

import commonj.sdo.DataObject;

public class CustomerClient {
    private static final String DEFAULT_CUSTOMER_CONFIG = "CustomersConfig.xml";
    
	private DAS das = null;
    private final String configFile;
    
    /**
     * Default constructor
     *
     */
	public CustomerClient(){	
        this.configFile = DEFAULT_CUSTOMER_CONFIG;
	}
    
    /**
     * Constructor receiving the das config file to be used
     * @param configFile DAS configuration file
     */
    public CustomerClient(String configFile) {
        this.configFile = configFile;
    }
    
    /**
     * Helper method to get a stream from the customer config file 
     * @param fileName
     * @return
     */
    protected InputStream getConfig(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
    
    /**
     * Get a reference to DAS, initialize it if necessary
     * @return DAS reference
     */
    protected DAS getDAS() {
        if (this.das == null ) {
            this.das = DAS.FACTORY.createDAS(getConfig(this.configFile));
        }
        return this.das;
    }

	
	/**
	 * select
	 * @return
	 */
    public final List getCustomers() {
        Command read = this.getDAS().getCommand("AllCustomers");
        DataObject root = read.executeQuery();
        return root.getList("CUSTOMER");
    }

    /**
     * insert
     *
     */
    public final void addCustomer() {
        Command read = this.getDAS().getCommand("AllCustomers");
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
        Command readAll = this.getDAS().getCommand("AllCustomers");
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
        Command readAll = this.getDAS().getCommand("AllCustomers");
        DataObject root = readAll.executeQuery();

        DataObject firstCustomer = root.getDataObject("CUSTOMER[1]");
        firstCustomer.set("LASTNAME", "BlueBerry");
        
        das.applyChanges(root);
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
     * cleanup
     *
     */
    public void releaseResources() {
        das.releaseResources();
    }   
    
    /**
     * Main customer application
     */    
    public static void main(String[] args){
        String customerConfigFile = "CustomersConfig.xml"; //this can be from input params too as below
        
        if(args != null && args.length >0){
            customerConfigFile = args[0];
        }
        
        
        //initialize customer database using helper class
        CustomerDatabaseInitializer dbInitializer = new CustomerDatabaseInitializer(customerConfigFile);
        try {
            dbInitializer.Initialize();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        
        //perform customer operations using DAS
        CustomerClient customerClient = new CustomerClient(customerConfigFile);        
        //test select
        System.out.println();
        System.out.println("Result:select all customers");
        printList(customerClient.getCustomers());
        
        //test insert
        System.out.println();
        System.out.println("Result:insert new customer");
        customerClient.addCustomer();
        printList(customerClient.getCustomers());
        
        //test update
        System.out.println();
        System.out.println("Result:update first customer");
        customerClient.changeFirstCustomerName();
        printList(customerClient.getCustomers());
        
        //test delete
        System.out.println();
        System.out.println("Result:delete last customer");
        customerClient.deleteCustomer();
        printList(customerClient.getCustomers());
    }
}