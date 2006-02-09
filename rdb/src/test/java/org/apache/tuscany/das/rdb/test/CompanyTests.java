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

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.test.company.CompanyFactory;
import org.apache.tuscany.das.rdb.test.company.CompanyType;
import org.apache.tuscany.das.rdb.test.company.DatagraphRoot;
import org.apache.tuscany.das.rdb.test.company.DepartmentType;
import org.apache.tuscany.das.rdb.test.company.EmployeeType;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CompanyDeptData;
import org.apache.tuscany.das.rdb.test.data.DepEmpData;
import org.apache.tuscany.das.rdb.test.data.DepartmentData;
import org.apache.tuscany.das.rdb.test.data.EmployeeData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.DataObject;
import commonj.sdo.helper.TypeHelper;

public class CompanyTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CompanyData(getAutoConnection()).refresh();
        new DepartmentData(getAutoConnection()).refresh();
        new EmployeeData(getAutoConnection()).refresh();
        new CompanyDeptData(getAutoConnection()).refresh();
        new DepEmpData(getAutoConnection()).refresh();

    }

    public void testSimple() throws Exception {

        // Build the select command
        Command selectCommand = Command.FACTORY.createCommand("select COMPANY.NAME, "
                + "EMPLOYEE.NAME, EMPLOYEE.SN, EMPLOYEE.MANAGER, "
                + "DEPARTMENT.NAME, DEPARTMENT.LOCATION, DEPARTMENT.NUMBER from COMPANY, DEPARTMENT, EMPLOYEE "
                + "where COMPANY.ID=DEPARTMENT.COMPANYID and DEPARTMENT.ID=EMPLOYEE.DEPARTMENTID", getCompanyMappingModel());

        // Parameterize the command
        selectCommand.setConnection(getConnection());

        // Get the graph
        DataObject root = selectCommand.executeQuery();

        // Get a company
        DataObject company = (DataObject) root.getList("CompanyType").get(0);
        assertEquals("MegaCorp", company.get("NAME"));

        // Get a department
        DataObject department = (DataObject) company.getList("departments").get(0);
        assertEquals("Advanced Technologies", department.get("NAME"));

        DataObject employee = (DataObject) department.getList("employees").get(0);
        assertEquals("John Jones", employee.get("NAME"));
    }

    public void testSimpleStatic() throws Exception {

    	SDOUtil.registerStaticTypes(CompanyFactory.class);
        // Build the select command
        Command selectCommand = Command.FACTORY.createCommand("select COMPANY.NAME, "
                + "EMPLOYEE.NAME, EMPLOYEE.SN, EMPLOYEE.MANAGER, "
                + "DEPARTMENT.NAME, DEPARTMENT.LOCATION, DEPARTMENT.NUMBER from COMPANY, DEPARTMENT, EMPLOYEE "
                + "where COMPANY.ID=DEPARTMENT.COMPANYID and DEPARTMENT.ID=EMPLOYEE.DEPARTMENTID", getCompanyMappingModel());

        // Parameterize the command
        selectCommand.setConnection(getConnection());
        selectCommand.setDataObjectModel(TypeHelper.INSTANCE.getType(DatagraphRoot.class));
        selectCommand.addConverter("DEPARTMENT.NUMBER", "org.apache.tuscany.das.rdb.test.mappings.StringToIntegerConverter");
        selectCommand.addConverter("EMPLOYEE.MANAGER", "org.apache.tuscany.das.rdb.test.mappings.IntegerToBooleanConverter");

        // Get the graph
        DatagraphRoot root = (DatagraphRoot) selectCommand.executeQuery();
        
        
        CompanyType company = (CompanyType) root.getCompanies().get(0);

        assertEquals("MegaCorp", company.getName());

        // Get a department
        DepartmentType department = (DepartmentType) company.getDepartments().get(0);
        assertEquals("Advanced Technologies", department.getName());

        EmployeeType employee = (EmployeeType) department.getEmployees().get(0);

        assertEquals("John Jones", employee.getName());
    }

    private InputStream getCompanyMappingModel() throws FileNotFoundException {
        return getClass().getClassLoader().getResourceAsStream("companyMapping.xml");
    }

}
