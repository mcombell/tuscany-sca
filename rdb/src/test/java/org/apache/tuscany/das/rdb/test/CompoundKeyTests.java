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

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

/**
 * Tests for Compound Keys
 */
public class CompoundKeyTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new OrderDetailsData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRead() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command getOrderDetails = das.createCommand("Select * from ORDERDETAILS where ORDERID = ? AND PRODUCTID = ?");

        getOrderDetails.setParameter(1, new Integer(1));
        getOrderDetails.setParameter(2, new Integer(1));

        DataObject root = getOrderDetails.executeQuery();

        DataObject orderDetail = (DataObject) root.get("ORDERDETAILS[1]");
        assertEquals(1.1f, orderDetail.getFloat("PRICE"), 0.01);

    }

    public void testReadModifyWrite2() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("OrdersOrderDetailsConfig.xml"), getConnection());
        Command getOrderDetails = das.createCommand("Select * from ORDERDETAILS where ORDERID = 1 AND PRODUCTID = 1");

        DataObject root = getOrderDetails.executeQuery();

        DataObject orderDetails = (DataObject) root.get("ORDERDETAILS[1]");
        assertEquals(1.1f, orderDetails.getFloat("PRICE"), 0.01);

        // Modify
        orderDetails.setFloat("PRICE", 0f);

        // Build apply changes command

        das.applyChanges(root);

        // Verify
        root = getOrderDetails.executeQuery();
        orderDetails = root.getDataObject("ORDERDETAILS[1]");
        assertEquals(0f, orderDetails.getFloat("PRICE"), 0.01);

    }

    public void testReadOrdersAndDetails2() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("OrdersOrderDetailsConfig.xml"), getConnection());
        Command read = das.createCommand("SELECT * FROM ANORDER LEFT JOIN ORDERDETAILS " 
                + "ON ANORDER.ID = ORDERDETAILS.ORDERID ORDER BY ANORDER.ID");

        DataObject root = read.executeQuery();

        DataObject firstOrder = root.getDataObject("ANORDER[1]");
        assertEquals(1, firstOrder.getInt("ID"));
        assertEquals(2, firstOrder.getList("ORDERDETAILS").size());

    }

    public void testReadAndDelete() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("OrdersOrderDetailsConfig.xml"), getConnection());
        Command getOrderDetails = das.createCommand("Select * from ORDERDETAILS where ORDERID = ? AND PRODUCTID = ?");

        getOrderDetails.setParameter(1, new Integer(1));
        getOrderDetails.setParameter(2, new Integer(1));

        DataObject root = getOrderDetails.executeQuery();

        DataObject orderDetail = (DataObject) root.get("ORDERDETAILS[1]");
        orderDetail.delete();
        das.applyChanges(root);

    }
}
