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
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.exception.OptimisticConcurrencyException;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class OCCTests extends DasTest {
    protected void setUp() throws Exception {
        super.setUp();

        new BookData(getAutoConnection()).refresh();
        new CustomerData(getAutoConnection()).refresh();
    }

    public void testAutomaticOCC() throws SQLException {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read customer with particular ID
        Command select = das.createCommand("Select * from CUSTOMER where ID = 1");
        DataObject root = select.executeQuery();

        // Explicitly update the DB to force a collision
        Command update = das.createCommand("update CUSTOMER set LASTNAME = 'Smith' where ID = 1");
        update.execute();
        
        DataObject customer = root.getDataObject("CUSTOMER[1]");

        // Modify customer
        customer.set("LASTNAME", "Pavick");

        // Build apply changes command
        try {
            das.applyChanges(root);
            fail("An OCCException should be thrown");
        } catch (OptimisticConcurrencyException ex) {
            if (!ex.getMessage().equals("An update collision occurred")) {
                throw ex;
            }
        }
      
    }
    
    public void testSimpleOCC() throws SQLException  {

        DAS das = DAS.FACTORY.createDAS(getConfig("BooksConfig.xml"), getConnection());
        // Read a book instance
        Command select = das.getCommand("select book 1");
        DataObject root = select.executeQuery();
        DataObject book = root.getDataObject("BOOK[1]");
        // Change a field to mark the instance 'dirty'
        book.setInt("QUANTITY", 2);

        // Explicitly change OCC column in database to force collision
        Command update = das.getCommand("update book 1");
        update.setParameter(1, new Integer(100));
        update.execute();

        // Try to apply changes and catch the expected An update collision occurred
        try {
            das.applyChanges(root);
            fail("An OCCException should be thrown");
        } catch (OptimisticConcurrencyException ex) {
            if (!ex.getMessage().equals("An update collision occurred")) {
                throw ex;
            }
        }
    }

    public void testManagedOCC() throws SQLException  {
        DAS das = DAS.FACTORY.createDAS(getConfig("ManagedBooksConfig.xml"), getConnection());
        Command select = das.getCommand("select book 1");
        DataObject root = select.executeQuery();
        DataObject book = root.getDataObject("BOOK[1]");
        // Change a field to mark the instance 'dirty'
        book.setInt("QUANTITY", 2);
        int occValue = book.getInt("OCC");
        das.applyChanges(root);

        root = select.executeQuery();
        book = root.getDataObject("BOOK[1]");
        assertEquals(occValue + 1, book.getInt("OCC"));
    }

    public void testManagedOCCFailure() throws SQLException  {
        DAS das = DAS.FACTORY.createDAS(getConfig("ManagedBooksConfig.xml"), getConnection());
        // Read a book instance
        Command select = das.getCommand("select book 1");
        DataObject root = select.executeQuery();
        DataObject book = root.getDataObject("BOOK[1]");
        // Change a field to mark the instance 'dirty'
        book.setInt("QUANTITY", 2);

        DAS das2 = DAS.FACTORY.createDAS(getConfig("ManagedBooksConfig.xml"), getConnection());
        // Read a book instance
        Command select2 = das2.getCommand("select book 1");
        DataObject root2 = select2.executeQuery();
        DataObject book2 = root2.getDataObject("BOOK[1]");
        // Change a field to mark the instance 'dirty'
        book2.setInt("QUANTITY", 5);
        das2.applyChanges(root2);

        // Try to apply changes and catch the expecetd An update collision occurred
        try {
            das.applyChanges(root);
            fail("An OCCException should be thrown");
        } catch (OptimisticConcurrencyException ex) {
            if (!ex.getMessage().equals("An update collision occurred")) {
                throw ex;
            }
        }
    }

    public void testProvidedConfig() throws SQLException  {
        // Create config programmatically
        Config config = ConfigFactory.INSTANCE.createConfig();
        ConfigHelper helper = new ConfigHelper(config);
        helper.addPrimaryKey("BOOK.BOOK_ID");
        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());

        // Read a book instance
        Command select = das.createCommand("SELECT * FROM BOOK WHERE BOOK_ID = 1");
        DataObject root = select.executeQuery();
        DataObject book = root.getDataObject("BOOK[1]");
        // Change a field to mark the instance 'dirty'
        book.setInt("QUANTITY", 2);

        // Flush the change

        das.applyChanges(root);

        // Verify
        root = select.executeQuery();
        book = root.getDataObject("BOOK[1]");
        assertEquals(2, book.getInt("QUANTITY"));
    }
}
