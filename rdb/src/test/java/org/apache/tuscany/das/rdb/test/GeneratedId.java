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

/*
 * Generated IDs
 * 
 * 
 */

import java.util.Iterator;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.DepartmentData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class GeneratedId extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CompanyData(getAutoConnection()).refresh();
        new DepartmentData(getAutoConnection()).refresh();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // Test insert into row with generated ID
    public void testInsert() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into COMPANY (NAME) values (?)");
        insert.setParameter(1, "AAA Rental");
        insert.execute();

        // Verify insert
        // Verify
        Command select = das.createCommand("Select ID, NAME from COMPANY");
        DataObject root = select.executeQuery();

        assertEquals(4, root.getList("COMPANY").size());
        assertTrue(root.getInt("COMPANY[1]/ID") > 0);

    }

    // Test back to back insertions with the same command
    public void testInsert2() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into COMPANY (NAME) values (?)");
        insert.setParameter(1, "AAA Rental");
        insert.execute();

        // insert another using same command
        insert.setParameter(1, "BBB Rental");
        insert.execute();

        // Verify insert
        // Verify
        Command select = das.createCommand("Select ID, NAME from COMPANY");
        DataObject root = select.executeQuery();

        assertEquals(5, root.getList("COMPANY").size());

    }

    // Test ability to retrieve and utilize the generated key
    public void testInsert3() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand("insert into COMPANY (NAME) values (?)");
        insert.setParameter(1, "AAA Rental");
        insert.execute();
        //       Integer key = (Integer) insert.getParameterValue("generated_key");
        Integer key = (Integer) insert.getGeneratedKey();

        // Verify insert
        Command select = das.createCommand("Select ID, NAME from COMPANY where ID = ?");
        select.setParameter(1, key);
        DataObject root = select.executeQuery();
        assertEquals(key, root.get("COMPANY[1]/ID"));

    }

    // Test insert into row with generated ID and generated insert
    public void testInsert4() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());
        Command select = das.getCommand("all companies");
        DataObject root = select.executeQuery();

        DataObject company = root.createDataObject("COMPANY");
        company.setString("NAME", "Phil's Tires");
        // This shouldn't do anything
        company.setInt("ID", 999);

        das.applyChanges(root);

        // Verify insert                 
        root = select.executeQuery();

        assertEquals(4, root.getList("COMPANY").size());
        Iterator i = root.getList("COMPANY").iterator();
        while (i.hasNext()) {
            DataObject comp = (DataObject) i.next();
            assertFalse(comp.getInt("ID") == 999);
        }

    }
    
    /** 
     * Test insert into row with generated ID and no attributes set 
     */ 
    public void testInsert5() throws Exception { 

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection()); 
        Command select = das.getCommand("all companies"); 
        DataObject root = select.executeQuery(); 

        root.createDataObject("COMPANY"); 

        das.applyChanges(root); 

        // Verify insert 
        root = select.executeQuery(); 
        assertEquals(4, root.getList("COMPANY").size()); 

    } 


    // Test ability to propogate generated values back to owning data objects
    public void testPropagateIds() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("CompanyConfig.xml"), getConnection());
        Command select = das.getCommand("all companies");
        DataObject root = select.executeQuery();

        // Create a new Company
        DataObject company = root.createDataObject("COMPANY");
        company.setString("NAME", "Do-rite Pest Control");

        // verify pre-condition (id is not there until after flush)
        assertNull(company.get("ID"));

        // Flush changes      
        das.applyChanges(root);

        // Save the id
        Integer id = (Integer) company.get("ID");

        // Verify the change
        select = das.createCommand("Select * from COMPANY where ID = ?");
        select.setParameter(1, id);
        root = select.executeQuery();
        assertEquals("Do-rite Pest Control", root.getDataObject("COMPANY[1]").getString("NAME"));

    }

    /**
     * Same as above but metadata provided by XML config file
     */
    public void testPropagateIdsXML() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("basicCompanyMapping.xml"), getConnection());
        Command select = das.createCommand("Select * from COMPANY");
        DataObject root = select.executeQuery();

        // Create a new Company
        DataObject company = root.createDataObject("COMPANY");
        company.setString("NAME", "Do-rite Pest Control");

        // verify pre-condition (id is not there until after flush)
        assertNull(company.get("ID"));

        // Flush changes      
        das.applyChanges(root);

        // Save the id
        Integer id = (Integer) company.get("ID");

        // Verify the change
        select = das.createCommand("Select * from COMPANY where ID = ?");
        select.setParameter(1, id);
        root = select.executeQuery();
        assertEquals("Do-rite Pest Control", root.getDataObject("COMPANY[1]").getString("NAME"));

    }

    /**
     * Test ability to correctly flush heirarchy of objects that have generated
     * keys
     */
    public void testFlushCreateHeirarchy() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("basicCompanyDepartmentMapping.xml"), getConnection());
        String selectCompanys = "SELECT * FROM COMPANY LEFT JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID";

        Command select = das.createCommand(selectCompanys);
        DataObject root = select.executeQuery();

        // Create a new Company
        DataObject company = root.createDataObject("COMPANY");
        company.setString("NAME", "Do-rite Pest Control");

        // Create a new Department
        // Do not set ID or CompanyID since these are generated
        // ID INT, NAME VARCHAR(30), LOCATION VARCHAR(30), NUMBER VARCHAR(10),
        // COMPANYID INT, EOTM INT
        DataObject department = root.createDataObject("DEPARTMENT");
        department.setString("NAME", "Do-rite Pest Control");
        department.setString("LOCATION", "The boonies");
        department.setString("DEPNUMBER", "101");

        // Associate the new department with the new company
        company.getList("departments").add(department);

        das.applyChanges(root);

        // Save the id
        Integer id = (Integer) company.get("ID");

        // Verify the change
        String selectString = "SELECT * FROM COMPANY LEFT JOIN DEPARTMENT "
                + "ON COMPANY.ID = DEPARTMENT.COMPANYID WHERE COMPANY.ID = ?";

        select = das.createCommand(selectString);
        select.setParameter(1, id);
        root = select.executeQuery();
        assertEquals("Do-rite Pest Control", root.getDataObject("COMPANY[1]").getString("NAME"));

    }

    /**
     * Test ability to correctly flush heirarchy of objects that have generated
     * keys even when a created object has legal but NULL property values
     */
    public void testFlushCreateHeirarchy2() throws Exception {

        String selectCompanys = "SELECT * FROM COMPANY LEFT JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID";

        DAS das = DAS.FACTORY.createDAS(getConfig("basicCompanyDepartmentMapping.xml"), getConnection());
        Command select = das.createCommand(selectCompanys);
        DataObject root = select.executeQuery();

        // Create a new Company
        DataObject company = root.createDataObject("COMPANY");
        company.setString("NAME", "Do-rite Pest Control");

        // Create a new Department
        // Do not set ID or CompanyID since these are generated
        // ID INT, NAME VARCHAR(30), LOCATION VARCHAR(30), NUMBER VARCHAR(10),
        // COMPANYID INT, EOTM INT
        DataObject department = root.createDataObject("DEPARTMENT");
        department.setString("NAME", "Do-rite Pest Control");
        // Do not set this property to force storing NULL to DB
        // department.setString("LOCATION", "The boonies");
        department.setString("DEPNUMBER", "101");

        // Associate the new department with the new company
        company.getList("departments").add(department);

        das.applyChanges(root);

        // Save the id
        Integer id = (Integer) company.get("ID");

        // Verify the change
        String selectString = "SELECT * FROM COMPANY LEFT JOIN DEPARTMENT "
                + "ON COMPANY.ID = DEPARTMENT.COMPANYID WHERE COMPANY.ID = ?";

        select = das.createCommand(selectString);
        select.setParameter(1, id);
        root = select.executeQuery();
        assertEquals("Do-rite Pest Control", root.getDataObject("COMPANY[1]").getString("NAME"));

    }

    // Test that error is thrown when no key has been generated (as in a select)
    public void testRead() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("Select * from COMPANY");
        select.executeQuery();

        try {
            select.getGeneratedKey();
            fail("Should throw exception");
        } catch (RuntimeException e) {
            assertEquals("This method is only valid for insert commands", e.getMessage());
        }
    }

}