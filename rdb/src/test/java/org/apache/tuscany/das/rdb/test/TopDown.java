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

import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.customer.AnOrder;
import org.apache.tuscany.das.rdb.test.customer.Customer;
import org.apache.tuscany.das.rdb.test.customer.CustomerFactory;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.DataObject;

public class TopDown extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();

    }

    // Uses dynamic SDOs but user provides the model
    public void testUserProvidedModelDynamic() throws SQLException {

        DAS das = DAS.FACTORY.createDAS(getConfig("staticCustomerOrder.xml"), getConnection());

        Command select = das.getCommand("Customer and Orders");

        SDOUtil.registerStaticTypes(CustomerFactory.class);

        // Parameterize the command
        select.setParameter(1, Integer.valueOf(1));

        // Get the graph - DataGraphRoot is from the typed package
        DataObject root = select.executeQuery();

        // Modify a customer
        Customer customer = (Customer) root.getDataObject("Customer[1]");
        customer.setLastName("Pavick");

        // Modify an order
        AnOrder order = (AnOrder) customer.getOrders().get(0);
        order.setProduct("Kitchen Sink 001");

        // Flush changes
        das.applyChanges((DataObject) root);

    }

}