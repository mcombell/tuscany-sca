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
package org.apache.tuscany.samples.das.companyweb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;

import commonj.sdo.DataObject;

public class CompanyClient {

    private Random generator = new Random();

    private DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"));

    public final List getCompanies() {

        Command read = das.getCommand("all companies");
        DataObject root = read.executeQuery();
        return root.getList("COMPANY");

    }

    public final List getCompaniesWithDepartments() {

        Command read = das.getCommand("all companies and departments");
        DataObject root = read.executeQuery();
        return root.getList("COMPANY");
    }

    public final List getDepartmentsForCompany(int id) {
        Command read = das.getCommand("all departments for company");
        read.setParameter(1, new Integer(id));
        DataObject root = read.executeQuery();
        return root.getList("COMPANY");
    }

    public final void addDepartmentToFirstCompany() {
        Command read = das.getCommand("all companies and departments");
        DataObject root = read.executeQuery();
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");

        DataObject newDepartment = root.createDataObject("DEPARTMENT");
        newDepartment.setString("NAME", "Default Name");
        firstCustomer.getList("departments").add(newDepartment);

        das.applyChanges(root);

    }

    public final void deleteDepartmentsFromFirstCompany() {

        // This section gets the ID of the first Company just so I can
        // demonstrate a parameterized command next
        Command readAll = das.getCommand("all companies and departments");
        DataObject root = readAll.executeQuery();
        int idOfFirstCustomer = root.getInt("COMPANY[1]/ID");
        System.out.println("ID of first company is: " + idOfFirstCustomer);

        // Read a specific company based on the known ID
        Command readCust = das.getCommand("company by id with departments");
        readCust.setParameter(1, new Integer(idOfFirstCustomer));
        root = readCust.executeQuery();

        // Delete all the comany's departments from the graph
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");

        // Shallow copy of list for deleting. This is required to avoid the
        // dreaded
        // ConcurrentModificationException since #delete operation also removes
        // from the original list
        List allDepartments = new ArrayList(firstCustomer.getList("departments"));

        Iterator i = allDepartments.iterator();
        DataObject department;
        while (i.hasNext()) {
            department = (DataObject) i.next();
            System.out.println("Deleting department named: " + department.getString("NAME"));
            department.delete();
        }

        das.applyChanges(root);

    }

    public final void changeFirstCompanysDepartmentNames() {

        // This section gets the ID of the first Company just so I can
        // demonstrate a parameterized command next
        Command readAll = das.getCommand("all companies and departments");
        DataObject root = readAll.executeQuery();
        int idOfFirstCustomer = root.getInt("COMPANY[1]/ID");
        System.out.println("ID of first company is: " + idOfFirstCustomer);

        // Read a specific company based on the known ID
        Command readCust = das.getCommand("company by id with departments");
        readCust.setParameter(1, new Integer(idOfFirstCustomer));
        root = readCust.executeQuery();

        // Modify all the comany's department names
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");
        Iterator i = firstCustomer.getList("departments").iterator();
        DataObject department;
        while (i.hasNext()) {
            department = (DataObject) i.next();
            System.out.println("Modifying department: " + department.getString("NAME"));
            department.setString("NAME", getRandomDepartmentName());
        }
        das.applyChanges(root);

    }
    
    
    public void releaseResources() {
        das.releaseResources();
    }

    // Utilities

    private String getRandomDepartmentName() {
        int number = generator.nextInt(1000) + 1;
        return "Dept-" + number;
    }

    private InputStream getConfig(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
