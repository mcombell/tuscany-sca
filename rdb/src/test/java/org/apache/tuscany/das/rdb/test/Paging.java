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

import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.Pager;
import org.apache.tuscany.das.rdb.impl.PagerImpl;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class Paging extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
    }

    public void testPaging() throws SQLException {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Build command to read all customers
        Command custCommand = das.createCommand("select * from CUSTOMER order by ID");

        // Create a pager with the command
        Pager pager = new PagerImpl(custCommand, 2);

        // Get and work with first page
        DataObject root = pager.next();
        DataObject customer1 = root.getDataObject("CUSTOMER[1]");
        DataObject customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(1, customer1.getInt("ID"));
        assertEquals(2, customer2.getInt("ID"));

        // Get and work with the second page
        root = pager.next();
        customer1 = root.getDataObject("CUSTOMER[1]");
        customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(3, customer1.getInt("ID"));
        assertEquals(4, customer2.getInt("ID"));

        // First page again
        root = pager.previous();
        customer1 = root.getDataObject("CUSTOMER[1]");
        customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(1, customer1.getInt("ID"));
        assertEquals(2, customer2.getInt("ID"));

    }

    public void testRandomPage() throws SQLException {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Build the select command
        Command select = das.createCommand("select * from CUSTOMER order by ID");

        // Create a pager
        Pager pager = new PagerImpl(select, 2);

        // Get the first page
        DataObject root = pager.getPage(1);
        DataObject customer1 = root.getDataObject("CUSTOMER[1]");
        DataObject customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(1, customer1.getInt("ID"));
        assertEquals(2, customer2.getInt("ID"));

        // Get the second page
        root = pager.getPage(2);
        customer1 = root.getDataObject("CUSTOMER[1]");
        customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(3, customer1.getInt("ID"));
        assertEquals(4, customer2.getInt("ID"));

        // Get the first page again
        root = pager.getPage(1);
        customer1 = root.getDataObject("CUSTOMER[1]");
        customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(1, customer1.getInt("ID"));
        assertEquals(2, customer2.getInt("ID"));
    }
    
    
/*    public void testLightPager() throws SQLException {
        
        
        // Build the command to page with
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("select * from CUSTOMER order by ID");
        
        //Create the light Pager instance
        Pager lp = Pager.FACTORY.createLightPager(2);

        // Get the first page
        DataObject root = pager.getPage(1);
        DataObject customer1 = root.getDataObject("CUSTOMER[1]");
        DataObject customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(1, customer1.getInt("ID"));
        assertEquals(2, customer2.getInt("ID"));

        // Get the second page
        root = pager.getPage(2);
        customer1 = root.getDataObject("CUSTOMER[1]");
        customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(3, customer1.getInt("ID"));
        assertEquals(4, customer2.getInt("ID"));

        // Get the first page again
        root = pager.getPage(1);
        customer1 = root.getDataObject("CUSTOMER[1]");
        customer2 = root.getDataObject("CUSTOMER[2]");
        assertEquals(1, customer1.getInt("ID"));
        assertEquals(2, customer2.getInt("ID"));
    }*/
    

}