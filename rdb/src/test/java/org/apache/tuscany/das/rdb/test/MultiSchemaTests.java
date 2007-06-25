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
package org.apache.tuscany.das.rdb.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.MultiSchemaData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class MultiSchemaTests extends DasTest{
    protected void setUp() throws Exception {        
        super.setUp();
       	new MultiSchemaData(getAutoConnection()).refresh();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
	/**If database is present connect to it and create necessary tables, procedures, data etc.
	 * 
	 * Below is test database schema required. 
	 * Schema Layout - 
	 * Database: DASTEST
	 * Schema: DASTEST1
	 * 	   Tables: CUSTOMER, EMPLOYEE, CITY, ORDERDETAILS
	 * Schema: DASTEST2
	 * 	   Tables: CUSTOMER, ACCOUNT, CITY 
	 * Schema: DASTEST3
	 * 	   Tables: CUSTOMER, CUSTORDER, ORDERDETAILSDESC
	 */
	
	public void testMulitiSchemaCase1() throws Exception {
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig1.xml"), getConnection());
		//test case 1 - CRUD when schemaName, tableName, typeName present - DASTEST1.CUSTOMER
		//System.out.println("Result:test case 1 - CRUD when schemaName, tableName, typeName present:DASTEST1.CUSTOMER");
		//test select
		//System.out.println("Result:test case 1 - SELECT");
		assertEquals(getCustomers(das).size(), 3);
		
		//test insert - basic
		//System.out.println("Result:test case 1 - INSERT(execute())");
		addCustomer(das);
		assertEquals(getCustomers(das).size(), 4);
		
		//test insert - use SDO
		//System.out.println("Result:test case 1 - INSERT(applyChanges())");
		addSDOCustomer(das);
		assertEquals(getCustomers(das).size(), 5);
		
		//test delete - basic
		//System.out.println("Result:test case 1 - DELETE(execute())");
		deleteCustomer(das);
		assertEquals(getCustomers(das).size(), 4);
		
		//test delete - SDO
		//System.out.println("Result:test case 1 - DELETE(applyChanges())");
		deleteSDOCustomer(das);
		assertEquals(getCustomers(das).size(), 3);
		
		//test update 
		//System.out.println("Result:test case 1 - UPDATE");
		changeFirstCustomerName(das);
		assertEquals( ((DataObject)getCustomers(das).get(0)).get("LASTNAME"), "Williams");
		//System.out.println("******************************************");
	}
	
	public void testMultiSchemaCase2()throws Exception{		
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig2.xml"), getConnection());
		//test case 1 - CRUD when schemaName, tableName present, typeName not present - DASTEST2.ACCOUNT
		//System.out.println("Result:test case 2 - CRUD when schemaName, tableName present, typeName not present:DASTEST2.ACCOUNT");
		//test select
		//System.out.println("Result:test case 2 - SELECT");
		assertEquals(getAccounts(das).size(), 4);
		
		//test insert - basic
		//System.out.println("Result:test case 2 - INSERT(execute())");
		addAccount(das);
		assertEquals(getAccounts(das).size(), 5);
		
		//test insert - SDO
		//System.out.println("Result:test case 2 - INSERT(applyChanges())");
		addSDOAccount(das);
		assertEquals(getAccounts(das).size(), 6);
		
		//test delete - basic
		//System.out.println("Result:test case 2 - DELETE(execute())");
		deleteAccount(das);
		assertEquals(getAccounts(das).size(), 5);
		
		//test delete - SDO
		//System.out.println("Result:test case 2 - DELETE(applyChanges())");
		deleteSDOAccount(das);
		assertEquals(getAccounts(das).size(), 4);
		
		//test update 
		//System.out.println("Result:test case 2 - UPDATE");
		changeFirstAccountBalance(das);
		assertEquals( ((DataObject)getAccounts(das).get(0)).get("BALANCE"), 45000);
		//System.out.println("******************************************");		
	}
	
	public void testMultiSchemaCase3() throws Exception{
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig3.xml"), getConnection());
		//test case 3 - CRUD when <Table> is not there in config - DASTEST1.CITY
		//System.out.println("Result:test case 3 - CRUD when <Table> is not there in config:DASTEST1.CITY");
		//test select
		//System.out.println("Result:test case 3 - SELECT");
		assertEquals(getCities(das).size(), 2);
		
		//test insert - basic
		//System.out.println("Result:test case 3 - INSERT(execute())");
		addCity(das);
		assertEquals(getCities(das).size(), 3);
		
		//test insert - SDO
		//System.out.println("Result:test case 3 - INSERT(applyChanges())");
		assertEquals(addSDOCity(das),"Expected failure to insert");
		assertEquals(getCities(das).size(), 3);
		
		//test delete - basic
		//System.out.println("Result:test case 3 - DELETE(execute())");
		deleteCity(das);
		assertEquals(getCities(das).size(), 2);
		
		//test delete - SDO
		//System.out.println("Result:test case 3 - DELETE(applyChanges())");
		assertEquals(deleteSDOCity(das), "Expected failure to delete");
		assertEquals(getCities(das).size(), 2);
		
		//test update 
		//System.out.println("Result:test case 3 - UPDATE");
		assertEquals(changeFirstCityName(das), "Expected failure to update");
		//System.out.println("******************************************");
	}
	
	public void testMultiSchemaCase4() throws Exception{		
		//System.out.println("Result:test case 4 - with multi schema support ON, have table with no schemaName"); 
		try{
		//get das handle for invalid config
			DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaInvalidDasConfig.xml"), getConnection());
    	}catch(Exception e){
    		//No schemaName provided for tableName CUSTORDER when schemaNameSupport is ON
   			assertEquals("No schemaName provided for tableName CUSTORDER when schemaNameSupport is ON", 
    					e.getMessage());
    	}
    	
		//System.out.println("******************************************");
	}
	
	public void testMultiSchemaCase5() throws Exception{
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig5.xml"), getConnection());
		
		//test case 5 - SELECT when CUSTOMER.ID is present in DASTEST1 and DASTEST2
		//and DASTEST2.CUSTOMER <Table> not in config
		//System.out.println("Result:test case 5 - SELECT when CUSTOMER.ID is present in DASTEST1 and DASTEST2 and"+
			//	" DASTEST2.CUSTOMER <Table> not in config");
		DataObject root = getCustomersFrom2SchemasWithOneSchemaNotInConfig(das);
		assertEquals(root.getList("DASTEST1_CUSTOMER").size(), 3);
		assertEquals(root.getList("DASTEST2.CUSTOMER").size(), 3);
		//System.out.println("******************************************");
	}

	public void testMultiSchemaCase6()throws Exception{
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig6.xml"), getConnection());
		//test case 6 - SELECT when CUSTOMER.ID is present in DASTEST1 and DB2ADMIN - and both <table> entries are in Config
		//System.out.println("Result:test case 6 - SELECT when CUSTOMER.ID is present in DASTEST1 and DB2ADMIN, and both <table> in config");
		//test select
		//System.out.println("Result:test case 6 - SELECT");
		DataObject root = getCustomersFrom2SchemasBothSchemaInConfig(das);
		assertEquals(root.getList("DASTEST1_CUSTOMER").size(),3);
		assertEquals(root.getList("DASTEST3_CUSTOMER").size(),3);
		//System.out.println("******************************************");
	}

	public void testMultiSchemaCase7()throws Exception{
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig7.xml"), getConnection());
		//test case 7 - SELECT when CITY.INDX is present in DASTEST1 and DASTEST2 and no enrty for
		//any <Table> in config
		//System.out.println("Result:test case 7 - SELECT when CITY.INDX is present in DASTEST1 and DASTEST2, and no <table> in config");
		//test select
		//System.out.println("Result:test case 7 - SELECT");
		DataObject root = getCitiesFrom2SchemasNoneInConfig(das);
		assertEquals(root.getList("DASTEST1.CITY").size(), 2);
		assertEquals(root.getList("DASTEST2.CITY").size(), 2);
		//System.out.println("******************************************");
	}
	
	public void testMultiSchemaCase8()throws Exception{
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig8.xml"), getConnection());

		//test case 8 - SELECT using ResultDescriptor - DASTEST2.ACCOUNT
		//System.out.println("Result:test case 8 - SELECT using ResultDescriptor:DASTEST2.ACCOUNT");
		assertEquals(getAccountRSDesc(das).size(), 4);
		//System.out.println("******************************************");
	}
	
	public void testMultiSchemaCase9()throws Exception{
		DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig9.xml"), getConnection());
		//test case 9, 11 - Implied relationship , PK when both tables in same schema DASTEST2.CUSTOMER, DASTEST2.ACCOUNT
		//System.out.println("Result:test case 9, 11 - Implied relationship when both tables in same schema DASTEST2.CUSTOMER, DASTEST2.ACCOUNT");
		DataObject root = getSingleSchemaCustomersAccounts(das);
		assertEquals(root.getList("DASTEST2.CUSTOMER").size(), 2);
		assertEquals(root.getList("DASTEST2.ACCOUNT").size(), 4);
		//System.out.println("******************************************");
	}	
	
	private static Date kbday;
    private static Date tbday;
    private static DateFormat myformat = new SimpleDateFormat("yyyy.MM.dd");
	static {
        try {
            kbday = myformat.parse("1957.09.27");
            tbday = myformat.parse("1966.12.20");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
	
    public void testMultiSchemaCase12()throws Exception{
    	DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig12.xml"), getConnection());
    	//test case 12 - use converter with multi-schema ON:DASTEST1.EMPLOYEE
    	//System.out.println("Result:test case 12 - converter on DASTEST1.EMPLOYEE.LASTNAME");
        Command read = das.getCommand("testArbitraryConverter");
        // Read
        DataObject root = read.executeQuery();

        // Verify
        assertEquals(kbday,root.getDate("DASTEST1.EMPLOYEE[1]/LASTNAME"));
		
        // Modify
        root.setDate("DASTEST1.EMPLOYEE[1]/LASTNAME", tbday);
        das.applyChanges(root);

        // Read
        root = read.executeQuery();

        // Verify
        assertEquals(tbday, root.getDate("DASTEST1.EMPLOYEE[1]/LASTNAME"));
        
        //System.out.println("******************************************");
    }
    
    public void testMultiSchemaCase13()throws Exception{
    	DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig13.xml"), getConnection());

    	//Explicit relationship between DASTEST1.CUSTOMER and DASTEST2.ACCOUNT
    	//System.out.println("Result:test case13 - Explicit relationship when both tables in different schema DASTEST1.CUSTOMER, DASTEST2.ACCOUNT");
        // Read some customers and related accounts
        Command select = das
                .createCommand("SELECT * FROM DASTEST1.CUSTOMER LEFT JOIN DASTEST2.ACCOUNT "+
                		" ON DASTEST1.CUSTOMER.ID = DASTEST2.ACCOUNT.CUSTOMER_ID");

        DataObject root = select.executeQuery();

        DataObject cust1 = root.getDataObject("DASTEST1_CUSTOMER[1]");
        DataObject cust2 = root.getDataObject("DASTEST1_CUSTOMER[2]");

        // Save IDs
        Integer cust1ID = (Integer) cust1.get("ID");
        Integer cust2ID = (Integer) cust2.get("ID");
        
        //System.out.println("cust 1 ID :"+cust1ID);
        //System.out.println("cust 2 ID :"+cust2ID);
        // save account count
        Integer cust1AccountCount = Integer.valueOf(cust1.getList("accounts").size());
        Integer cust2AccountCount = Integer.valueOf(cust2.getList("accounts").size());

        // Move an account to cust1 from cust2
        DataObject account = (DataObject) cust2.getList("accounts").get(0);
        cust1.getList("accounts").add(account);

        // Flush changes
        das.applyChanges(root);

        // verify cust1 relationship updates
        select = das
                .createCommand("SELECT * FROM DASTEST1.CUSTOMER LEFT JOIN DASTEST2.ACCOUNT ON "
                        + "DASTEST1.CUSTOMER.ID = DASTEST2.ACCOUNT.CUSTOMER_ID where DASTEST1.CUSTOMER.ID = ?");
        select.setParameter(1, cust1ID);

        root = select.executeQuery();
        assertEquals((cust1AccountCount.intValue() + 1), root.getList("DASTEST1_CUSTOMER[1]/accounts").size());

        // verify cust2 relationship updates
        select.setParameter(1, cust2ID);
        root = select.executeQuery();
        assertEquals((cust2AccountCount.intValue() - 1),root.getList("DASTEST1_CUSTOMER[1]/accounts").size());
        //System.out.println("******************************************");        
    }
    
    //steer away from implied relationships - we dont support multi schema there!!!
    public void testMultiSchemaCase14()throws Exception{
    	DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig14.xml"), getConnection());

    	//Explicit compound key relationship between DASTEST1.ORDERDETAILS and DASTEST3.ORDERDETAILSDESC
    	//ORDERID <-> ORDERID, PRODUCTID<->PRODUCTID
    	//System.out.println("Result:test case14 - Explicit compound key relationship when both " +
    		//	"tables in different schema DASTEST1.ORDERDETAILS, DASTEST3.ORDERDETAILSDESC");

    	// Read some order details and related order details descs
        Command select = das
                .createCommand("SELECT * FROM DASTEST1.ORDERDETAILS LEFT JOIN DASTEST3.ORDERDETAILSDESC " +
                		" ON DASTEST1.ORDERDETAILS.ORDERID = DASTEST3.ORDERDETAILSDESC.ORDERID " +
                		" AND DASTEST1.ORDERDETAILS.PRODUCTID = DASTEST3.ORDERDETAILSDESC.PRODUCTID");

        DataObject root = select.executeQuery();

        DataObject ordDet1 = root.getDataObject("DASTEST1.ORDERDETAILS[1]");
        DataObject ordDet2 = root.getDataObject("DASTEST1.ORDERDETAILS[2]");

        // Save IDs
        Integer ord1ID = (Integer) ordDet1.get("ORDERID");
        Integer prod1ID = (Integer) ordDet1.get("PRODUCTID");
        
        Integer ord2ID = (Integer) ordDet2.get("ORDERID");
        Integer prod2ID = (Integer) ordDet2.get("PRODUCTID");
        
        // save order count
        Integer order1DetDescCount = Integer.valueOf(ordDet1.getList("orderDetailsDesc").size());
        Integer order2DetDescCount = Integer.valueOf(ordDet2.getList("orderDetailsDesc").size());

        // Move an order detail desc to ord det1 from ord det2
        DataObject orderDetailsDesc = (DataObject) ordDet2.getList("orderDetailsDesc").get(0);
        ordDet1.getList("orderDetailsDesc").add(orderDetailsDesc);

        // Flush changes
        das.applyChanges(root);

        // verify ord det1 relationship updates
        select = das
        .createCommand("SELECT * FROM DASTEST1.ORDERDETAILS LEFT JOIN DASTEST3.ORDERDETAILSDESC " +
        		" ON DASTEST1.ORDERDETAILS.ORDERID = DASTEST3.ORDERDETAILSDESC.ORDERID " +
        		" AND DASTEST1.ORDERDETAILS.PRODUCTID = DASTEST3.ORDERDETAILSDESC.PRODUCTID " +
        		" where DASTEST1.ORDERDETAILS.ORDERID = ? AND DASTEST1.ORDERDETAILS.PRODUCTID = ?");
        select.setParameter(1, ord1ID);
        select.setParameter(2, prod1ID);

        root = select.executeQuery();
        assertEquals((order1DetDescCount.intValue() + 1),
        		root.getList("DASTEST1.ORDERDETAILS[1]/orderDetailsDesc").size());

        // verify ord det2 relationship updates
        select.setParameter(1, ord2ID);
        select.setParameter(2, prod2ID);
        
        root = select.executeQuery();
        assertEquals((order2DetDescCount.intValue() - 1),
        		root.getList("DASTEST1.ORDERDETAILS[1]/orderDetailsDesc").size());    	
    }
    
	/**
	 * Display result
	 * @param list 
	 * @param numCols - number of columns in each row
	 */
	public static void printList(List list, int numCols){
		if(list != null) 
			System.out.println("list size:"+list.size());
		
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<numCols; j++){
				System.out.print("   col"+(j+1)+":"+(((DataObject)list.get(i)).getString(j)) );
			}
			System.out.println();
		}
	}
	
	public static void printMultiSchemaList(DataObject root, String[] lists, String[] cols){
		if(lists != null && cols != null){
			if(lists.length != cols.length) return;
			
			for(int ii=0; ii<lists.length; ii++){
				List list = root.getList(lists[ii]);
				printList(list, Integer.parseInt(cols[ii]));
			}
		}
	}	
	
	/**********START TEST CASE 1***********************************************/
	/**
	 * select
	 * @return
	 */
    public final List getCustomers(DAS das) {

        Command read = das.getCommand("SelectDASTEST1CUSTOMER");
        DataObject root = read.executeQuery();        
        return root.getList("DASTEST1_CUSTOMER");
    }
    /**
     * insert
     *
     */
     public final void addCustomer(DAS das){
    	Command insert = das.getCommand("InsertDASTEST1CUSTOMER");
        insert.execute();
    }

     public final void addSDOCustomer(DAS das){
        Command read = das.getCommand("SelectDASTEST1CUSTOMER");
        DataObject root = read.executeQuery();

        DataObject newCustomer = root.createDataObject("DASTEST1_CUSTOMER");

        newCustomer.setInt("ID", 5);
        newCustomer.setString("LASTNAME", "PennyDAS");
        newCustomer.setString("ADDRESS", "HSRDAS");

        das.applyChanges(root);
        root = read.executeQuery();
    }
        
    /**
     * delete
     *
     */
    public final void deleteCustomer(DAS das){
    	Command delete = das.getCommand("DeleteDASTEST1CUSTOMER");
        delete.execute();	
    }

    public final void deleteSDOCustomer(DAS das) {
        Command readAll = das.getCommand("SelectDASTEST1CUSTOMER");
        DataObject root = readAll.executeQuery();

        List allCustomers = root.getList("DASTEST1_CUSTOMER");

        DataObject lastCustomer = (DataObject)allCustomers.get(allCustomers.size()-1);
        if(lastCustomer != null){
        	//System.out.println("Deleting customer named: " + lastCustomer.getString("LASTNAME"));
        	lastCustomer.delete();
        }

        das.applyChanges(root);
    }
    
    /**
     * update
     *
     */
    public final void changeFirstCustomerName(DAS das) {
        Command readAll = das.getCommand("SelectDASTEST1CUSTOMER");
        DataObject root = readAll.executeQuery();

        DataObject firstCustomer = root.getDataObject("DASTEST1_CUSTOMER[1]");
        firstCustomer.set("LASTNAME", "Williams");
        
        das.applyChanges(root);
    }
    /**********END TEST CASE 1***********************************************/
    
	/**********START TEST CASE 2***********************************************/
	/**
	 * select
	 * @return
	 */
    public final List  getAccounts(DAS das){
        Command read = das.getCommand("SelectDASTEST2ACCOUNT");
        DataObject root = read.executeQuery();        
        return root.getList("DASTEST2.ACCOUNT");
    }
    /**
     * insert
     *
     */
     public final void addAccount(DAS das){
    	Command insert = das.getCommand("InsertDASTEST2ACCOUNT");
        insert.execute();
    }
     
    public final void addSDOAccount(DAS das){
        Command read = das.getCommand("SelectDASTEST2ACCOUNT");
        DataObject root = read.executeQuery();
        
        List custList = root.getList("DASTEST1_CUSTOMER");
        DataObject curCust = null;
        for(int i=0; i<custList.size(); i++){
        	curCust = (DataObject)custList.get(i);
        	if(curCust.getInt("ID") == 2) {
        		break;
        	}
        }
        
        List acctList = null;
        if(curCust != null){
        	acctList = curCust.getList("accounts");
        	
        	DataObject newAccount = root.createDataObject("DASTEST2.ACCOUNT");
            newAccount.setInt("ACCOUNT_ID", 60);
            //newAccount.setInt("CUSTOMER_ID", 2); - taken from parent, so dont set here,else will throw exception
            newAccount.setInt("BALANCE",104);
            
            acctList.add(newAccount);
            das.applyChanges(root);  
        }        	
    }
        
    /**
     * delete
     *
     */
    public final void deleteAccount(DAS das){
    	Command delete = das.getCommand("DeleteDASTEST2ACCOUNT");
        delete.execute();	
    }

    public final void deleteSDOAccount(DAS das) {
        Command readAll = das.getCommand("SelectDASTEST2ACCOUNT");
        DataObject root = readAll.executeQuery();

        List custList = root.getList("DASTEST1_CUSTOMER");
        
        DataObject curCust = null;
        for(int i=0; i<custList.size(); i++){
        	curCust = (DataObject)custList.get(i);
        	if(curCust.getInt("ID") == 2) {
        		break;
        	}
        }
        
        List acctList = null;
        if(curCust != null){
        	acctList = curCust.getList("accounts");
        	if(acctList != null){
            	((DataObject)acctList.get(acctList.size()-1)).delete();
            }
        }        

        das.applyChanges(root);
    }
    
    /**
     * update
     *
     */
    public final void changeFirstAccountBalance(DAS das) {
        Command readAll = das.getCommand("SelectDASTEST2ACCOUNT");
        DataObject root = readAll.executeQuery();

        DataObject firstAccount = root.getDataObject("DASTEST2.ACCOUNT[1]");
        firstAccount.set("BALANCE", 45000);
        
        das.applyChanges(root);
    }
    /**********END TEST CASE 2***********************************************/
    
	/**********START TEST CASE 3***********************************************/
	/**
	 * select
	 * @return
	 */
    public final List  getCities(DAS das){
        Command read = das.getCommand("SelectDASTEST1CITY");
        DataObject root = read.executeQuery();        
        return root.getList("DASTEST1.CITY");
    }
    /**
     * insert
     *
     */
     public final void addCity(DAS das){
    	Command insert = das.getCommand("InsertDASTEST1CITY");
        insert.execute();
    }
     
    public final String addSDOCity(DAS das){
        Command read = das.getCommand("SelectDASTEST1CITY");
        DataObject root = read.executeQuery();

        DataObject newCity = root.createDataObject("DASTEST1.CITY");
        newCity.setInt("INDX", 3);
        newCity.setString("NAME", "San Jose");

        try{
        das.applyChanges(root);
        }catch(Exception e){
        	if(e.getMessage().equals("Table DASTEST1.CITY was changed in the DataGraph but is not present in the Config")){
        		//ignore
        		return "Expected failure to insert";
        	}
        	else{
        		throw new RuntimeException(e);
        	}
        }
        return null;
    }
        
    /**
     * delete
     *
     */
    public final void deleteCity(DAS das){
    	Command delete = das.getCommand("DeleteDASTEST1CITY");
        delete.execute();	
    }

    public final String deleteSDOCity(DAS das) {
        Command readAll = das.getCommand("SelectDASTEST1CITY");
        DataObject root = readAll.executeQuery();

        List allCities = root.getList("DASTEST1.CITY");

        DataObject lastCity = (DataObject)allCities.get(allCities.size()-1);
        if(lastCity != null){
        	//System.out.println("Deleting city: " + lastCity.getString("NAME"));
        	lastCity.delete();
        }

        try{
            das.applyChanges(root);
        }catch(Exception e){
        	if(e.getMessage().equals("Table DASTEST1.CITY was changed in the DataGraph but is not present in the Config")){
        		//ignore
        		return "Expected failure to delete";
        	}
        	else{
        		throw new RuntimeException(e);
        	}
        }
        return null;
    }
    
    /**
     * update
     *
     */
    public final String changeFirstCityName(DAS das) {
        Command readAll = das.getCommand("SelectDASTEST1CITY");
        DataObject root = readAll.executeQuery();

        DataObject firstCity = root.getDataObject("DASTEST1.CITY[1]");
        firstCity.set("NAME", "New York");
        
        try{
        	das.applyChanges(root);
        }catch(Exception e){
        	if(e.getMessage().equals("Table DASTEST1.CITY was changed in the DataGraph but is not present in the Config")){
        		//ignore
        		return "Expected failure to update";
        	}
        	else{
        		throw new RuntimeException(e);
        	}        	
        }
        return null;
    }
    /**********END TEST CASE 3***********************************************/
    /**********START TEST CASE 5***********************************************/    
    public final DataObject getCustomersFrom2SchemasWithOneSchemaNotInConfig(DAS das) {
        Command read = das.getCommand("Select2SchemasWithOneSchemaNotInConfig");
        DataObject root = read.executeQuery();
        return root;
    }
    /**********END TEST CASE 5***********************************************/    

    /**********START TEST CASE 6***********************************************/    

    public final DataObject getCustomersFrom2SchemasBothSchemaInConfig(DAS das) {

        Command read = das.getCommand("Select2IDsBothInConfig");
        DataObject root = read.executeQuery();
        return root;
    }
    
    /**********END TEST CASE 6***********************************************/ 
    /**********START TEST CASE 7***********************************************/
    public final DataObject getCitiesFrom2SchemasNoneInConfig(DAS das){
    	Command read = das.getCommand("Select2CitiesNoneInConfig");
        DataObject root = read.executeQuery();
        return root;    	
    }
    /**********END TEST CASE 7***********************************************/
    /**********START TEST CASE 8***********************************************/    
    
    public final List  getAccountRSDesc(DAS das){
    	Command read = das.getCommand("ResultDescriptorAccountSelect");
        DataObject root = read.executeQuery();        
        return root.getList("DASTEST2.ACCOUNT");
    }
    /**********END TEST CASE 8***********************************************/

    /**********START TEST CASE 9***********************************************/    

    public final DataObject getSingleSchemaCustomersAccounts(DAS das){
        Command read = das.getCommand("ImpliedRelationshipSelect");
        DataObject root = read.executeQuery();
        return root;
    }
    /**********END TEST CASE 9***********************************************/    
}
