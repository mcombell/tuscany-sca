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

import java.util.List;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;
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

        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CompanyConfig.xml"));
        Command read = commandGroup.getCommand("all companies");
        DataObject root = read.executeQuery(); 
        assertEquals(3, root.getList("COMPANY").size());

    }
    
    //Read list of companies
    public void testReadCompaniesWithDepartments() throws Exception {

        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CompanyConfig.xml"));
        Command read = commandGroup.getCommand("all companies and departments");
        DataObject root = read.executeQuery(); 
        DataObject firstCompany = root.getDataObject("COMPANY[1]");
        List departments = firstCompany.getList("departments");
        assertEquals(0, departments.size());

    }  
    
    public void testddDepartmentToFirstCompany() throws Exception {
        
        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CompanyConfig.xml"));
        Command read = commandGroup.getCommand("all companies and departments");
        DataObject root = read.executeQuery();
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");
        int deptCount = firstCustomer.getList("departments").size();
        
        DataObject newDepartment = root.createDataObject("DEPARTMENT");
        firstCustomer.getList("departments").add(newDepartment); 
        
        ApplyChangesCommand apply = commandGroup.getApplyChangesCommand();
        apply.execute(root);
        
        //verify
        root = read.executeQuery();
        firstCustomer = root.getDataObject("COMPANY[1]");
        assertEquals (deptCount + 1, firstCustomer.getList("departments").size());
    }  
    

}
