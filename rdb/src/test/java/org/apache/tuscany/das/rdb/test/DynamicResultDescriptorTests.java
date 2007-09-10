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

import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class DynamicResultDescriptorTests extends DasTest {

    protected void setUp() throws Exception {    
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
    }
    
    protected void tearDown() throws Exception {
    	super.tearDown();
    }
	
    /**
     * Set result descriptor on Command dynamically
     */
    public void testSetResultDescriptors() throws Exception {
    	ConfigFactory factory = ConfigFactory.INSTANCE;
    	DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read a customer
        Command select = das.createCommand("SELECT ID, LASTNAME, ADDRESS FROM CUSTOMER WHERE CUSTOMER.ID = 1");
        
        List resultDescriptorList = new ArrayList();
        //as long as columnIndex is correct, order in ArrayList does not matter
        ResultDescriptor desc3 = factory.createResultDescriptor();
        desc3.setColumnIndex(3);
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.String");
        desc3.setTableName("CUSTOMER");

        ResultDescriptor desc1 = factory.createResultDescriptor();
        desc1.setColumnIndex(1);
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");
        
        ResultDescriptor desc2 = factory.createResultDescriptor();
        desc2.setColumnIndex(2);
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");
                
        resultDescriptorList.add(desc3);
        resultDescriptorList.add(desc1);
        resultDescriptorList.add(desc2);
        
        select.setResultDescriptors(resultDescriptorList);
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
    }
    
    /**
     * Set a result descriptor on Command dynamically
     * and later replace it with another invalid one, and check failure
     */
    public void testReplaceResultDescriptors() throws Exception {
    	ConfigFactory factory = ConfigFactory.INSTANCE;
    	DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read a customer
        Command select = das.createCommand("SELECT ID, LASTNAME, ADDRESS FROM CUSTOMER WHERE CUSTOMER.ID = 1");
        
        List resultDescriptorList = new ArrayList();
        //as long as columnIndex is correct, order in ArrayList does not matter
        ResultDescriptor desc3 = factory.createResultDescriptor();
        desc3.setColumnIndex(3);
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.String");
        desc3.setTableName("CUSTOMER");

        ResultDescriptor desc1 = factory.createResultDescriptor();
        desc1.setColumnIndex(1);
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");
        
        ResultDescriptor desc2 = factory.createResultDescriptor();
        desc2.setColumnIndex(2);
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");
                
        resultDescriptorList.add(desc3);
        resultDescriptorList.add(desc1);
        resultDescriptorList.add(desc2);
        
        select.setResultDescriptors(resultDescriptorList);
        DataObject root = select.executeQuery();
        assertEquals(1, root.getList("CUSTOMER").size());
        
        //Now use invalid one
        resultDescriptorList.clear();
        
        desc3.setColumnIndex(3);
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.Int");//incompatible type
        desc3.setTableName("CUSTOMER");

        desc1.setColumnIndex(1);
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");
        
        desc2 = factory.createResultDescriptor();
        desc2.setColumnIndex(2);
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");
        
        resultDescriptorList.add(desc3);
        resultDescriptorList.add(desc2);
        resultDescriptorList.add(desc1);
        
        select.setResultDescriptors(resultDescriptorList);
        try{
        	root = select.executeQuery();
        	fail("Expected exception");
        }catch(Exception e){
        	assertTrue(e instanceof ClassCastException);
        }
    }
    
    /**
     * Set result descriptor on Command dynamically
     * , overriding the one set from static config.
     */
    public void testOverrideResultDescriptorsFromConfig() throws Exception {
    	ConfigFactory factory = ConfigFactory.INSTANCE;
    	DAS das = DAS.FACTORY.createDAS(getConfig("customerMappingWithResultDescriptor.xml"), getConnection());
        Command select = das.getCommand("testSelectCustomer");
        DataObject root = select.executeQuery();
        List custList = root.getList("CUSTOMER");
        assertEquals("Williams", ((DataObject)custList.get(0)).getString("LASTNAME"));
        
        List resultDescriptorList = new ArrayList();

        ResultDescriptor desc3 = factory.createResultDescriptor();
        desc3.setColumnIndex(3);
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.Int");//incompatible type
        desc3.setTableName("CUSTOMER");

        ResultDescriptor desc1 = factory.createResultDescriptor();
        desc1.setColumnIndex(1);
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");
        
        ResultDescriptor desc2 = factory.createResultDescriptor();
        desc2.setColumnIndex(2);
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");
                
        resultDescriptorList.add(desc3);
        resultDescriptorList.add(desc1);
        resultDescriptorList.add(desc2);
        
        select.setResultDescriptors(resultDescriptorList);
        try{
        	select.executeQuery();
        	fail("Expected exception");
        }catch(Exception e){
        	assertTrue(e instanceof ClassCastException);
        }    	
    }
    
    /**
     * Set null result descriptor on Command dynamically 
     */
    public void testNullResultDescriptors() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read a customer
        Command select = das.createCommand("SELECT ID, LASTNAME, ADDRESS FROM CUSTOMER WHERE CUSTOMER.ID = 1");
        
        List resultDescriptorList = null;

        try{
        	select.setResultDescriptors(resultDescriptorList);

        	select.executeQuery();
        	this.assertTrue("Derby has DBMS metadata, so it will be used", true);
        }catch(RuntimeException e){
        	fail("Expected to succeed with no exception");
        } 
    }
    
    /**
     * Set -ve columnIndex in result descriptor on Command dynamically
     * Set will not be sorted and Type mismatch wi (Int and String for ID)
     * will throw exception 
     */
    public void testNegativeIndexResultDescriptors() throws Exception {
    	ConfigFactory factory = ConfigFactory.INSTANCE;
    	DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read a customer
        Command select = das.createCommand("SELECT ID, LASTNAME, ADDRESS FROM CUSTOMER WHERE CUSTOMER.ID = 1");
        
        List resultDescriptorList = new ArrayList();
        
        ResultDescriptor desc3 = factory.createResultDescriptor();
        desc3.setColumnIndex(-3);//invalid
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.String");
        desc3.setTableName("CUSTOMER");

        ResultDescriptor desc1 = factory.createResultDescriptor();
        desc1.setColumnIndex(1);
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");
        
        ResultDescriptor desc2 = factory.createResultDescriptor();
        desc2.setColumnIndex(2);
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");
                
        resultDescriptorList.add(desc3);
        resultDescriptorList.add(desc1);
        resultDescriptorList.add(desc2);

        try{
        	select.setResultDescriptors(resultDescriptorList);

        	select.executeQuery();
        	fail("Expected exception");
        }catch(RuntimeException e){
        	assertTrue("Exception as sorting wil not happen due to -ve columnIndex", true);
        } 
    }
    
    /**
     * Set matching columnIndices in result descriptor on Command dynamically 
     */
    public void testMatchingIndexResultDescriptor() throws Exception {
    	ConfigFactory factory = ConfigFactory.INSTANCE;
    	DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read a customer
        Command select = das.createCommand("SELECT ID, LASTNAME, ADDRESS FROM CUSTOMER WHERE CUSTOMER.ID = 1");
        
        List resultDescriptorList = new ArrayList();
        
        ResultDescriptor desc3 = factory.createResultDescriptor();
        desc3.setColumnIndex(1);//two times index is 1
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.String");
        desc3.setTableName("CUSTOMER");

        ResultDescriptor desc1 = factory.createResultDescriptor();
        desc1.setColumnIndex(1);
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");
        
        ResultDescriptor desc2 = factory.createResultDescriptor();
        desc2.setColumnIndex(2);
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");
                
        resultDescriptorList.add(desc3);
        resultDescriptorList.add(desc1);
        resultDescriptorList.add(desc2);

        try{
        	select.setResultDescriptors(resultDescriptorList);

        	select.executeQuery();
        	fail("Expected exception");
        }catch(RuntimeException e){
        	assertTrue(e.getMessage().indexOf("Two columns in Result Descriptor can not have same index")!= -1);
        } 
    }    
    
    public void testAddRemoveResultDescriptor() throws Exception {
    	ConfigFactory factory = ConfigFactory.INSTANCE;

    	ResultDescriptor desc3 = factory.createResultDescriptor();
        desc3.setColumnIndex(3);//two times index is 1
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.String");
        desc3.setTableName("CUSTOMER");

    	ResultDescriptor desc1 = factory.createResultDescriptor();
        desc1.setColumnIndex(1);
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");
        
        ResultDescriptor desc2 = factory.createResultDescriptor();
        desc2.setColumnIndex(2);
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");

    	DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read a customer
        Command select = das.createCommand("SELECT ID, LASTNAME, ADDRESS FROM CUSTOMER WHERE CUSTOMER.ID = 1");
        select.addResultDescriptor(desc3);
        select.addResultDescriptor(desc1);
        select.addResultDescriptor(desc2);
        //select.printResultDescriptors(System.out);
        assertEquals(1, ((ResultDescriptor)select.getResultDescriptors().get(0)).getColumnIndex());
        assertEquals(2, ((ResultDescriptor)select.getResultDescriptors().get(1)).getColumnIndex());
        assertEquals(3, ((ResultDescriptor)select.getResultDescriptors().get(2)).getColumnIndex());
        
        select.removeResultDescriptor(2);
        //select.printResultDescriptors(System.out);
        assertEquals(1, ((ResultDescriptor)select.getResultDescriptors().get(0)).getColumnIndex());
        assertEquals(3, ((ResultDescriptor)select.getResultDescriptors().get(1)).getColumnIndex());
        
        select.removeResultDescriptor(desc3);
        //select.printResultDescriptors(System.out);
        assertEquals(1, ((ResultDescriptor)select.getResultDescriptors().get(0)).getColumnIndex());
        
        select.addResultDescriptor(null);
        select.removeResultDescriptor(null);
        //select.printResultDescriptors(System.out);
        assertEquals(1, ((ResultDescriptor)select.getResultDescriptors().get(0)).getColumnIndex());        
    }
}
