/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.das.rdb.test;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.test.company.CompanyFactory;
import org.apache.tuscany.das.rdb.test.customer.DataGraphRoot;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.helper.TypeHelper;

public class ExceptionTests extends DasTest {

    public ExceptionTests() {
        super();
    }

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMissingConnection() throws Exception {
        Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1");
        try {
            readCustomers.executeQuery();
            fail("RuntimeException should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("A DASConnection object must be specified before executing the query.", ex.getMessage());
        }

    }

    public void testMissingMapping() throws Exception {
        Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1");
        readCustomers.setConnection(getConnection());
        SDOUtil.registerStaticTypes(CompanyFactory.class);
        readCustomers.setDataObjectModel(TypeHelper.INSTANCE.getType(DataGraphRoot.class));

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
            Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1", getConfig("NonExistingFile.xml"));
            fail("Error should be thrown");
        } catch (Error e) {
            assertEquals(
                    "Cannot load configuration from a null InputStream. Possibly caused by an incorrect config xml file name",
                    e.getMessage());
        }
    }

    /**
     * Test ability to describe problem when a user passes an empty stream
     */
    public void testEmptyStream2() throws Exception {
        try {
            CommandGroup.FACTORY.createCommandGroup(getConfig("NonExistingFile.xml"));
            fail("Error should be thrown");
        } catch (Error e) {
            assertEquals(
                    "Cannot load configuration from a null InputStream. Possibly caused by an incorrect config xml file name",
                    e.getMessage());
        }
    }
}
