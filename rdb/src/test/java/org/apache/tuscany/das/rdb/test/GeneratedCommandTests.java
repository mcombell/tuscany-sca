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

import java.util.List;

import org.apache.tuscany.das.rdb.test.commands.ReadCustomersByLastnameCommand;
import org.apache.tuscany.das.rdb.test.commands.ReadCustomersCommand;
import org.apache.tuscany.das.rdb.test.commands.ReadCustomersStaticTypesCommand;
import org.apache.tuscany.das.rdb.test.commands.ReadCustomersWithShapeCommand;
import org.apache.tuscany.das.rdb.test.commands.SimpleReadCustomersWithShapeCommand;
import org.apache.tuscany.das.rdb.test.customer.Customer;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class GeneratedCommandTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReadCustomers() throws Exception {
        ReadCustomersCommand cmd = new ReadCustomersCommand();
        cmd.setConnection(getConnection());

        DataObject root = cmd.executeQuery();
        assertEquals(5, root.getList("CUSTOMER").size());
    }

    public void testReadSomeCustomers() throws Exception {
        ReadCustomersByLastnameCommand cmd = new ReadCustomersByLastnameCommand();
        cmd.setConnection(getConnection());
        cmd.setParameter(1, "Williams");

        DataObject root = cmd.executeQuery();
        assertEquals(4, root.getList("CUSTOMER").size());

    }

    public void testReadCustomersStaticTypes() throws Exception {
        ReadCustomersStaticTypesCommand cmd = new ReadCustomersStaticTypesCommand();
        cmd.setConnection(getConnection());
        cmd.setParameter(1, "Williams");

        DataObject root = cmd.executeQuery();

        List customers = root.getList("Customer");
        assertEquals(4, customers.size());

        Customer cust1 = (Customer) customers.get(0);
        assertEquals("Williams", cust1.getLastName());

    }

    public void testSimpleReadCustomersWithShape() throws Exception {
        SimpleReadCustomersWithShapeCommand cmd = new SimpleReadCustomersWithShapeCommand();
        cmd.setConnection(getConnection());
        DataObject root = cmd.executeQuery();
        assertEquals(5, root.getList("CUSTOMER").size());
    }

    public void testReadCustomersOrdersWithShape() throws Exception {
        ReadCustomersWithShapeCommand cmd = new ReadCustomersWithShapeCommand();
        cmd.setConnection(getConnection());

        DataObject root = cmd.executeQuery();
        assertEquals(5, root.getList("CUSTOMER").size());
    }

}
