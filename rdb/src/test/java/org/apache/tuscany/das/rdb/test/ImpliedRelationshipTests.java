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

import java.util.Iterator;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

public class ImpliedRelationshipTests extends DasTest {
    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Ensure that an implied relationship is not created when a defined one already exists
     * 
     * @throws Exception
     */
    public void testRelationshipAlreadyDefined() throws Exception {
        ConfigHelper helper = new ConfigHelper();
        Relationship r = helper.addRelationship("CUSTOMER.ID", "ANORDER.CUSTOMER_ID");
        r.setName("definedRelationship");

        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());
        Command select = das.createCommand("select * from CUSTOMER left join ANORDER "
                + "ON CUSTOMER.ID = ANORDER.CUSTOMER_ID");

        DataObject root = select.executeQuery();
        DataObject cust = root.getDataObject("CUSTOMER[1]");
        Iterator i = cust.getType().getProperties().iterator();
        while (i.hasNext()) {
            Property p = (Property) i.next();
            if (!p.getType().isDataType()) {
                assertEquals(p.getName(), "definedRelationship");
            }
        }
    }

    /**
     * Add a new Order to a list of Customers without defining any config information
     * 
     * @throws Exception
     */
    public void testAddNewOrder() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());

        Command select = das.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER "
                + "ON CUSTOMER.ID = ANORDER.CUSTOMER_ID");

        DataObject root = select.executeQuery();

        DataObject cust = root.getDataObject("CUSTOMER[1]");

        // Save ID and Order Count
        int custID = cust.getInt("ID");
        int custOrderCount = cust.getList("ANORDER").size();

        // Create a new Order and add to customer1
        DataObject order = root.createDataObject("ANORDER");

        order.set("ID", Integer.valueOf(99));
        order.set("PRODUCT", "The 99th product");
        order.set("QUANTITY", Integer.valueOf(99));
        cust.getList("ANORDER").add(order);

        assertEquals(custOrderCount + 1, cust.getList("ANORDER").size());

        // Build apply changes command
        das.applyChanges(root);

        // verify cust1 relationship updates
        select = das.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID "
                 + "where CUSTOMER.ID = ?");

        select.setParameter(1, Integer.valueOf(custID));
        root = select.executeQuery();

        assertEquals(custOrderCount + 1, root.getList("CUSTOMER[1]/ANORDER").size());
    }
}
