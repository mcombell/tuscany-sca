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

/*
 * 
 * 
 */

import java.sql.SQLException;
import java.util.Vector;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsData;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsDescriptionData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class CompoundKeyRelationshipTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        //new OrderData(getAutoConnection()).refresh();
        //new ProductData(getAutoConnection()).refresh();
        new OrderDetailsData(getAutoConnection()).refresh();
        new OrderDetailsDescriptionData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test ability to read a compound graph
     */
    public void testRead() throws Exception {

        String statement = "SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC "
                + "ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID AND " +
                  " ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID " +
                  " WHERE ORDERDETAILS.ORDERID = 1 AND ORDERDETAILS.PRODUCTID = 1";

        DAS das = DAS.FACTORY.createDAS(getConfig("OrderDetailsAndDescription.xml"), getConnection());
        // Read some order details and related order details description
        Command select = das.createCommand(statement);

        DataObject root = select.executeQuery();
        DataObject orderdetails = root.getDataObject("ORDERDETAILS[1]");
        
        assertEquals(2, orderdetails.getList("orderDetailsDesc").size());
    }

    /**
     * Same as above except uses xml file for relationhip and key information.
     * Employs CUD generation.
     */
    public void testRelationshipModification2() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("OrderDetailsAndDescription.xml"), getConnection());
        // Read some order details and related order details descs
        Command select = das
                .createCommand("SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID " +
                		" AND ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID");

        DataObject root = select.executeQuery();

        DataObject ordDet1 = root.getDataObject("ORDERDETAILS[1]");
        DataObject ordDet2 = root.getDataObject("ORDERDETAILS[2]");

        // Save IDs
        Integer ord1ID = (Integer) ordDet1.get("ORDERID");
        Integer prod1ID = (Integer) ordDet1.get("PRODUCTID");
        
        Integer ord2ID = (Integer) ordDet2.get("ORDERID");
        Integer prod2ID = (Integer) ordDet2.get("PRODUCTID");
        
        // save order count
        Integer order1DetDescCount = new Integer(ordDet1.getList("orderDetailsDesc").size());
        Integer order2DetDescCount = new Integer(ordDet2.getList("orderDetailsDesc").size());

        // Move an order detail desc to ord det1 from ord det2
        DataObject orderDetailsDesc = (DataObject) ordDet2.getList("orderDetailsDesc").get(0);
        ordDet1.getList("orderDetailsDesc").add(orderDetailsDesc);

        // Flush changes
        das.applyChanges(root);

        // verify ord det1 relationship updates
        select = das
        .createCommand("SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID " +
        		" AND ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID where ORDERDETAILS.ORDERID = ? AND ORDERDETAILS.PRODUCTID = ?");
        select.setParameter(1, ord1ID);
        select.setParameter(2, prod1ID);

        root = select.executeQuery();
        assertEquals(order1DetDescCount.intValue() + 1, root.getList("ORDERDETAILS[1]/orderDetailsDesc").size());

        // verify ord det2 relationship updates
        select.setParameter(1, ord2ID);
        select.setParameter(2, prod2ID);
        
        root = select.executeQuery();
        assertEquals(order2DetDescCount.intValue() - 1, root.getList("ORDERDETAILS[1]/orderDetailsDesc").size());
    }
    
    public void testFKBehavior() throws SQLException {
        DAS das = DAS.FACTORY.createDAS(getConfig("OrderDetailsAndDescription.xml"), getConnection());
        // Read some order details and related order details descs
        Command select = das
                .createCommand("SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID " +
                		" AND ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID");

        DataObject root = select.executeQuery();

        DataObject ordDet1 = root.getDataObject("ORDERDETAILS[1]");
        DataObject ordDet2 = root.getDataObject("ORDERDETAILS[2]");

        // Save IDs
        Integer ord1ID = (Integer) ordDet1.get("ORDERID");
        Integer prod1ID = (Integer) ordDet1.get("PRODUCTID");
        
        // Move an order det desc to ord det1 from ord det2
        DataObject orderDetDesc = (DataObject) ordDet2.getList("orderDetailsDesc").get(0);
        ordDet1.getList("orderDetailsDesc").add(orderDetDesc);
        orderDetDesc.setInt("ORDERID", ord1ID.intValue());
       
        try {
            das.applyChanges(root);
            fail("An exception should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("Foreign key properties should not be set when the corresponding relationship has changed", ex.getMessage());
        }

    }
    
    public void testFKBehavior2() throws SQLException {
        DAS das = DAS.FACTORY.createDAS(getConfig("OrderDetailsAndDescription.xml"), getConnection());
        // Read some order details and related order details descs
        Command select = das
                .createCommand("SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID " +
                		" AND ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID");

        DataObject root = select.executeQuery();

        DataObject ordDet1 = root.getDataObject("ORDERDETAILS[1]");
        DataObject ordDet2 = root.getDataObject("ORDERDETAILS[2]");

        // Save IDs
        Integer ord1ID = (Integer) ordDet1.get("ORDERID");
        Integer prod1ID = (Integer) ordDet1.get("PRODUCTID");
        
        // Create an order det desc for ord det1
        DataObject orderDetDesc = root.createDataObject("ORDERDETAILSDESC");
        orderDetDesc.setInt("ID", 500);
        orderDetDesc.setInt("ORDERID", ord1ID.intValue());
        ordDet1.getList("orderDetailsDesc").add(orderDetDesc);       
       
        try {
            das.applyChanges(root);
            fail("An exception should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("Foreign key properties should not be set when the corresponding relationship has changed", ex.getMessage());
        }
    }

    //add relationship through config helper
    public void testValidFKColumn() throws SQLException {
        ConfigHelper helper = new ConfigHelper();
        Vector parentColumnNames = new Vector();
        Vector childColumnNames = new Vector();

        parentColumnNames.add(0, "ORDERDETAILS.ORDERID");
        parentColumnNames.add(1, "ORDERDETAILS.PRODUCTID");

        childColumnNames.add(0, "ORDERDETAILSDESC.ORDERID");
        childColumnNames.add(1, "ORDERDETAILSDESC.PRODUCTID");        
        
        Relationship r = helper.addRelationship(parentColumnNames, childColumnNames);

        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());
        Command select = das.createCommand("SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID " +
                		" AND ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID");

        DataObject root = select.executeQuery();  
        DataObject ordDet1 = root.getDataObject("ORDERDETAILS[1]"); 
        int order1DetDescCount = ordDet1.getList("ORDERDETAILSDESC").size();
        
        DataObject orderDetDesc = root.createDataObject("ORDERDETAILSDESC");
        orderDetDesc.setInt("ID", 500);
        
        if(ordDet1 == null) System.out.println("order det1 is null");
        if(ordDet1.getList("ORDERDETAILSDESC") == null)System.out.println("list is null");
        
        ordDet1.getList("ORDERDETAILSDESC").add(orderDetDesc);   
        try {
            das.applyChanges(root);
            
            select = das.createCommand("SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID " +
    		" AND ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID");

            int modOrder1DetDescCount = select.executeQuery().
            	getDataObject("ORDERDETAILS[1]").getList("ORDERDETAILSDESC").size();
            this.assertEquals(modOrder1DetDescCount, order1DetDescCount+1);
        } catch (RuntimeException ex) {
            fail("Exception was not expected:DETAILS:"+ ex.getMessage());
        }
    }
    
    //add invalid relationship through config helper using invalid FK column name
    public void testInvalidFKColumn() throws SQLException {
        ConfigHelper helper = new ConfigHelper();
        Vector parentColumnNames = new Vector();
        Vector childColumnNames = new Vector();

        parentColumnNames.add(0, "ORDERDETAILS.ORDERID");
        parentColumnNames.add(1, "ORDERDETAILS.PRODUCTID");

        childColumnNames.add(0, "ORDERDETAILSDESC.ORDERID_INVALID");
        childColumnNames.add(1, "ORDERDETAILSDESC.PRODUCTID");        
        
        Relationship r = helper.addRelationship(parentColumnNames, childColumnNames);

        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());
        Command select = das.createCommand("SELECT * FROM ORDERDETAILS LEFT JOIN ORDERDETAILSDESC ON ORDERDETAILS.ORDERID = ORDERDETAILSDESC.ORDERID " +
                		" AND ORDERDETAILS.PRODUCTID = ORDERDETAILSDESC.PRODUCTID");

        DataObject root = select.executeQuery();  
        DataObject ordDet1 = root.getDataObject("ORDERDETAILS[1]"); 
        int order1DetDescCount = ordDet1.getList("ORDERDETAILSDESC").size();
        
        DataObject orderDetDesc = root.createDataObject("ORDERDETAILSDESC");
        orderDetDesc.setInt("ID", 500);
        
        if(ordDet1 == null) System.out.println("order det1 is null");
        if(ordDet1.getList("ORDERDETAILSDESC") == null)System.out.println("list is null");
        
        ordDet1.getList("ORDERDETAILSDESC").add(orderDetDesc);   
        try {
            das.applyChanges(root);
            fail("Exception was expected");
        } catch (RuntimeException ex) {
        	assertEquals("Invalid foreign key column: ORDERID_INVALID", ex.getMessage());            
        }
    }    
}
