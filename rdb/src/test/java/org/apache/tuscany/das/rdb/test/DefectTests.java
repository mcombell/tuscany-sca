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
 * These tests attempt to duplicate customer reported errors and then to verify
 * any necessary fix.
 * 
 */

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CompanyDeptData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.DepEmpData;
import org.apache.tuscany.das.rdb.test.data.DepartmentData;
import org.apache.tuscany.das.rdb.test.data.EmployeeData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

/**
 *
 *
 */
public class DefectTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();

        new CompanyData(getAutoConnection()).refresh();
        new DepartmentData(getAutoConnection()).refresh();
        new EmployeeData(getAutoConnection()).refresh();
        new CompanyDeptData(getAutoConnection()).refresh();
        new DepEmpData(getAutoConnection()).refresh();
        new BookData(getAutoConnection()).refresh();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Read a specific customer
     */
    public void testDiltonsInsert() throws Exception {

        // String sql = "insert into conmgt.serverstatus (statusid,
        // managedserverid, timestamp) values (316405209, 316405209, '2005-11-23
        // 19:29:52.636')";
        String sql = "insert into conmgt.serverstatus (managedserverid, timestamp) " 
                + "values (316405209, '2005-11-23 19:29:52.636')";
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command insert = das.createCommand(sql);
        insert.execute();

        // Verify
        Command select = das.createCommand("Select * from conmgt.serverstatus where statusid = 1");
        DataObject root = select.executeQuery();
                
        assertEquals(1, root.getList("SERVERSTATUS").size());

    }


    /**
     * Test expected failure when applyChanges processes DO with no PK columns.  We
     * should throw a better error than NPE
     */

    //now with JIRA-1464 , we will get exception in select itself , so can not reach upto
    //applyChanges
    /*public void testReadUpdateWithNoPKColumns() throws Exception {
        
        DAS das = DAS.FACTORY.createDAS(getConfig("BooksConfig.xml"),getConnection());
        // Read a book instance
        Command select = das.createCommand("SELECT NAME, AUTHOR, QUANTITY, OCC FROM BOOK WHERE BOOK_ID = 1");
        DataObject root = select.executeQuery();

        DataObject book = root.getDataObject("BOOK[1]");
        // Change a field to mark the instance 'dirty'
        book.setInt("QUANTITY", 2);

        try {
            das.applyChanges(root);
            fail("An exception should be thrown since the DO has no PK defined");
        } catch (NullPointerException ex) {
            fail("We should do better than an NPE");
        }
    }*/
    

}
