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
import org.apache.tuscany.das.rdb.test.data.CompanyEmpData;
import org.apache.tuscany.das.rdb.test.data.EmployeeData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class OneToOneRelationshipTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CompanyData(getAutoConnection()).refresh();
        new EmployeeData(getAutoConnection()).refresh();
        new CompanyEmpData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Read Company and traverse to EOTM
     */
    public void test1() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyEmployeeConfig.xml"), getConnection());

        Command read = das.getCommand("get companies with employee of the month");
        DataObject root = read.executeQuery();
        DataObject employee = root.getDataObject("COMPANY[1]/company->employee_opposite");

        assertEquals("Mary Smith", employee.getString("NAME"));
    }

    /**
     * Read Employee and traverse to Company
     */
    public void test2() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyEmployeeConfig.xml"), getConnection());

        Command read = das.getCommand("get named employee with company");
        read.setParameter(1, "Mary Smith");
        DataObject root = read.executeQuery();
        DataObject company = root.getDataObject("EMPLOYEE[1]/company->employee");

        assertEquals("ACME Publishing", company.getString("NAME"));
    }

    /**
     * Un-assign employee O' month
     */
    public void test3() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyEmployeeConfig.xml"), getConnection());

        Command read = das.getCommand("get companies with employee of the month");
        DataObject root = read.executeQuery();
        DataObject company = root.getDataObject("COMPANY[1]");
        company.setDataObject("company->employee_opposite", null);
        assertNull(company.getDataObject("company->employee_opposite"));

        //Flush changes
        das.applyChanges(root);

        //Verify
        root = read.executeQuery();
        company = root.getDataObject("COMPANY[1]");
        assertNull(company.getDataObject("company->employee_opposite"));
    }

    /**
     * Delete employee O' month
     */
    public void test4() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyEmployeeConfig.xml"), getConnection());

        Command read = das.getCommand("get companies with employee of the month");
        DataObject root = read.executeQuery();
        DataObject company = root.getDataObject("COMPANY[1]");
        DataObject employee = company.getDataObject("company->employee_opposite");
        employee.delete();
        assertNull(company.getDataObject("company->employee_opposite"));

        //Flush changes
        das.applyChanges(root);

        //Verify
        root = read.executeQuery();
        company = root.getDataObject("COMPANY[1]");
        assertNull(company.getDataObject("company->employee_opposite"));
    }

    /**
     * Add new employee O' month
     */
    public void test5() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyEmployeeConfig.xml"), getConnection());

        Command read = das.getCommand("get companies with employee of the month");
        DataObject root = read.executeQuery();
        DataObject company = root.getDataObject("COMPANY[1]");

        //Create a new employee
        DataObject employee = root.createDataObject("EMPLOYEE");
        employee.setString("NAME", "Joe Hotshot");

        //Assigne a EOTM
        //Strangely this statement results in "Could not find relationships" error
        //although "company.setDataObject("company->employee_opposite", null);" dos not   
        company.setDataObject("company->employee_opposite", employee);

        //Flush changes
        das.applyChanges(root);

        //Verify
        root = read.executeQuery();

        employee = root.getDataObject("COMPANY[1]/company->employee_opposite");

        assertEquals("Joe Hotshot", employee.getString("NAME"));

    }

    public void testRestrictedOneToOneRelationship() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("OneToOneRestrictedConfig.xml"), getConnection());

        Command read = das.getCommand("get named employee with company");
        read.setParameter(1, "Mary Smith");
        DataObject root = read.executeQuery();
        DataObject mary = root.getDataObject("EMPLOYEE[1]");
        DataObject company = mary.getDataObject("company");

        DataObject bob = root.createDataObject("EMPLOYEE");
        bob.setString("NAME", "bob");
        bob.setString("SN", "E0005");
        bob.setInt("MANAGER", 0);

        bob.setDataObject("company", company);

        try {
            das.applyChanges(root);
            fail("Relationship modification should not be allowed.");
        } catch (RuntimeException ex) {
            assertEquals("Can not modify a one to one relationship that is key restricted", ex.getMessage());
        }
        assertEquals("ACME Publishing", company.getString("NAME"));

    }
}
