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

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.company.CompanyFactory;
import org.apache.tuscany.das.rdb.test.customer.CustomerFactory;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.sdo.util.SDOUtil;

public class ExceptionTests extends DasTest {

    public ExceptionTests() {
        super();
    }

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
        new BookData(getAutoConnection()).refresh();
        new OrderDetailsData(getAutoConnection()).refresh();
        new CompanyData(getAutoConnection()).refresh();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMissingConnection() throws Exception {
        DAS das = DAS.FACTORY.createDAS((Connection) null);

        try {
            Command readCustomers = das.createCommand("select * from CUSTOMER where ID = 1");
            readCustomers.executeQuery();
            fail("RuntimeException should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("No connection has been provided and no data source has been specified", ex.getMessage());
        }

    }

    public void testUnregisteredTypes() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("staticInvalid.xml"), getConnection());
        Command readCustomers = das.createCommand("select * from CUSTOMER where ID = 1");

        try {
            readCustomers.executeQuery();

            fail("Exception should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("SDO Types have not been registered for URI invalidURI", ex.getMessage());
        }
    }

    public void testMissingMapping() throws Exception {
        SDOUtil.registerStaticTypes(CustomerFactory.class);
        DAS das = DAS.FACTORY.createDAS(getConfig("staticCustomer.xml"), getConnection());
        Command readCustomers = das.createCommand("select * from CUSTOMER where ID = 1");

        try {
            readCustomers.executeQuery();

            fail("Exception should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("An SDO Type with name CUSTOMER was not found", ex.getMessage());
        }

    }

    /**
     * Test ability to describe problem when a user passes an empty stream
     */
    public void testEmptyStream() throws Exception {
        try {
            DAS.FACTORY.createDAS(getConfig("NonExistingFile.xml"));
            fail("Error should be thrown");
        } catch (RuntimeException e) {
            assertEquals("Cannot load configuration from a null InputStream. Possibly caused " 
                    + "by an incorrect config xml file name", e.getMessage());
        }
    }

    /**
     * Test ability to describe problem when a user passes an empty stream
     */
    public void testEmptyStream2() throws Exception {
        try {
            DAS.FACTORY.createDAS(getConfig("NonExistingFile.xml"));
            fail("Error should be thrown");
        } catch (RuntimeException e) {
            assertEquals("Cannot load configuration from a null InputStream. Possibly caused "
                    + "by an incorrect config xml file name", e.getMessage());
        }
    }

    public void testReadOrdersAndDetails2() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("InvalidConfig1.xml"), getConnection());
        Command read = das.createCommand("SELECT * FROM ANORDER LEFT JOIN ORDERDETAILS "
                + "ON ANORDER.ID = ORDERDETAILS.ORDERID ORDER BY ANORDER.ID");

        try {
            read.executeQuery();
        } catch (Exception ex) {
            assertEquals("The parent table (xxx) in relationship ORDERDETAILS was not found.", ex.getMessage());
        }

    }

    public void testMismatchedDataObjectModel() throws SQLException {
        SDOUtil.registerStaticTypes(CompanyFactory.class);
        DAS das = DAS.FACTORY.createDAS(getConfig("companyMappingWithConverters.xml"), getConnection());
        Command read = das.createCommand("select * from company");
        try {
            read.executeQuery();
        } catch (RuntimeException ex) {
            assertEquals("Type CompanyType does not contain a property named ID", ex.getMessage());
        }
    }

}
