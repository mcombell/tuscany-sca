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
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CompanyDeptData;
import org.apache.tuscany.das.rdb.test.data.DepEmpData;
import org.apache.tuscany.das.rdb.test.data.DepartmentData;
import org.apache.tuscany.das.rdb.test.data.EmployeeData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class BestPracticeTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CompanyData(getAutoConnection()).refresh();
        new DepartmentData(getAutoConnection()).refresh();
        new EmployeeData(getAutoConnection()).refresh();
        new CompanyDeptData(getAutoConnection()).refresh();
        new DepEmpData(getAutoConnection()).refresh();

    }

    //Read list of companies
    public void testReadCompanies() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());
        Command read = das.getCommand("all companies");
        DataObject root = read.executeQuery();
        assertEquals(3, root.getList("COMPANY").size());

    }

    //Read list of companies
    public void testReadCompaniesWithDepartments() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());
        Command read = das.getCommand("all companies and departments");
        DataObject root = read.executeQuery();

        Iterator i = root.getList("COMPANY").iterator();
        while (i.hasNext()) {
            DataObject d = (DataObject) i.next();
            List departments = d.getList("departments");
            if (d.getString("NAME").equals("Do-rite plumbing") || d.getString("NAME").equals("ACME Publishing")) {
                assertEquals(0, departments.size());
            } else {
                assertEquals(1, departments.size());
            }
        }

    }

    public void testddDepartmentToFirstCompany() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());
        Command read = das.getCommand("all companies and departments");
        DataObject root = read.executeQuery();
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");
        int deptCount = firstCustomer.getList("departments").size();

        DataObject newDepartment = root.createDataObject("DEPARTMENT");
        firstCustomer.getList("departments").add(newDepartment);

        das.applyChanges(root);

        //verify
        root = read.executeQuery();
        firstCustomer = root.getDataObject("COMPANY[1]");
        assertEquals(deptCount + 1, firstCustomer.getList("departments").size());
    }

    /**
     * Test ability to correctly flush heirarchy of objects that have generated
     * keys
     */
    public void testFlushCreateHeirarchy() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());
        Command select = das.getCommand("all companies and departments");
        DataObject root = select.executeQuery();

        // Create a new Company
        DataObject company = root.createDataObject("COMPANY");
        company.setString("NAME", "Do-rite Pest Control");

        // Create a new Department
        //Do not set ID or CompanyID since these are generated
        DataObject department = root.createDataObject("DEPARTMENT");
        department.setString("NAME", "Do-rite Pest Control");
        department.setString("LOCATION", "The boonies");
        department.setString("DEPNUMBER", "101");

        // Associate the new department with the new company
        company.getList("departments").add(department);

        // Get apply command
        das.applyChanges(root);

        // Save the id
        Integer id = (Integer) company.get("ID");

        // Verify the change

        select = das.getCommand("company by id with departments");
        select.setParameter(1, id);
        root = select.executeQuery();
        assertEquals("Do-rite Pest Control", root.getDataObject("COMPANY[1]").getString("NAME"));

    }

    /**
     * Test ability to get an empty graph with the Types/Properties intact
     */
    public void testGetEmptyGraph() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());

        Command select = das.getCommand("company by id with departments");
        Integer idOfNoExistingCompany = new Integer(-1);
        select.setParameter(1, idOfNoExistingCompany);
        DataObject root = select.executeQuery();

        //Will fail if there is no property named "COMPANY"
        assertEquals(0, root.getList("COMPANY").size());

    }

}
