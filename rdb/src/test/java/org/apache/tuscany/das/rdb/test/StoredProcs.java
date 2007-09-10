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
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class StoredProcs extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CompanyData(getAutoConnection()).refresh();
        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMultipleResultSets() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command read = das.createCommand("{call GETALLCUSTOMERSANDORDERS()}");

        DataObject root = read.executeQuery();

        // Verify
        assertEquals(5, root.getList("CUSTOMER").size());
        assertEquals(4, root.getList("ANORDER").size());
    }

    // Call a simple stored proc to read all companies
    public void testGetCompanies() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command read = das.createCommand("{call GETALLCOMPANIES()}");

        DataObject root = read.executeQuery();

        // Verify
        assertEquals(3, root.getList("COMPANY").size());
        assertTrue(root.getInt("COMPANY[1]/ID") > 0);

    }

    public void testGetNamedCompany() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command read = das.createCommand("{call GETNAMEDCOMPANY(?)}");

        read.setParameter(1, "MegaCorp");
        DataObject root = read.executeQuery();

        assertEquals("MegaCorp", root.getString("COMPANY[1]/NAME"));

    }

    public void testGetNamedCompanyByName() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command read = das.createCommand("{call GETNAMEDCOMPANY(?)}");

        read.setParameter(1, "MegaCorp");
        DataObject root = read.executeQuery();

        assertEquals("MegaCorp", root.getString("COMPANY[1]/NAME"));
    }

    // Retreive heirarchy using a stored proc ... new programming model
    public void testGetCustomersAndOrder() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());
        Command read = das.createCommand("{call getCustomerAndOrders(?)}");
        read.setParameter(1, new Integer(1));

        DataObject root = read.executeQuery();

        DataObject customer = (DataObject) root.getList("CUSTOMER").get(0);
        assertEquals(2, customer.getList("orders").size());

    }

    /**
     * Call a stored proc requiring an in parameter and producing 
     * an out parameter and a resultset
     * 
     * This stored proc takes a lastname argument and returns a 
     * graph of customers with that last name. The number of read 
     * customers is returned in
     * the out parameter
     */
    public void testGetNamedCustomers() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("storedProcTest.xml"), getConnection());
        Command read = das.getCommand("getNamedCustomers");
        read.setParameter(1, "Williams");
        DataObject root = read.executeQuery();

        Integer customersRead = (Integer) read.getParameter(2);

        assertEquals(4, customersRead.intValue());
        assertEquals(customersRead.intValue(), root.getList("CUSTOMER").size());

    }

    // TODO - Resolve issue with programmatic creation of GETNAMEDCUSTOMERS on DB2 and
    // re-enable this test

    // Simplest possible SP write
    public void testDelete() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command delete = das.createCommand("{call DELETECUSTOMER(?)}");
        delete.setParameter(1, new Integer(1));
        delete.execute();

        // Verify DELETE
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();
        assertTrue(root.getList("CUSTOMER").isEmpty());

    }

}
