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

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class AliasTests extends DasTest {

    protected void setUp() throws Exception {
    
        super.setUp();
        new BookData(getAutoConnection()).refresh();
        new CustomerData(getAutoConnection()).refresh();
    }

    /**
     * Tests the use of column aliasing. The property name change is found in the 
     * BooksConfig.xml file Otherwise similar to testTableAlias
     * 
     * @throws Exception
     */
    public void testColumnAlias() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("BooksConfigWithAlias.xml"), getConnection());

        Command select = das.getCommand("get all books");
        // select.setConnection( getConnection() );

        DataObject root = select.executeQuery();

        DataObject book = root.getDataObject("Book[2]");
        book.set("Writer", "Dr. Seuss");

        das.applyChanges(root);

        select = das.getCommand("get Cat in the Hat");

        root = select.executeQuery();
        // Ensure the change actually updated
        assertEquals("Dr. Seuss", root.getString("Book[1]/Writer"));
    }

    /**
     * Tests to ensure that columns are being properly read when using an Alias, 
     * just a bunch of simple creating, renaming, and deleting of entries
     * while using an alias.
     */
    public void testColumnData() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("BooksConfigWithAlias.xml"), getConnection());

        Command select = das.getCommand("get book by ID");
        select.setParameter(1, new Integer(1));

        // *******Verifys a column entry is readable
        DataObject root = select.executeQuery();
        assertEquals("Fyodor Dostoevsky", root.getString("Book[1]/Writer"));

        DataObject bookToChange = root.getDataObject("Book[1]");
        bookToChange.set("Writer", "Fyodor Dostoevskii"); // His last name COULD be spelt like that too!

        das.applyChanges(root);

        // *******Verifys correct information in entry after a column data is changed
        select = das.getCommand("get all books");
        // select.setConnection(getConnection());

        root = select.executeQuery();
        bookToChange = null;
        Iterator i = root.getList("Book").iterator();
        while (i.hasNext()) {
            DataObject d = (DataObject) i.next();
            if ("Fyodor Dostoevskii".equals(d.getString("Writer"))) {
                bookToChange = d;
            }
        }
        assertFalse(bookToChange == null);

        bookToChange.delete();

        das.applyChanges(root);

        // *******Verifys correct in table, after entry is deleted
        select = das.getCommand("get all books");
        // select.setConnection(getConnection());

        root = select.executeQuery();
        assertEquals(1, root.getList("Book").size());
        assertEquals("Doctor Seuss", root.getString("Book[1]/Writer"));
    }

    /**
     * Test to check if updating a table works when using Aliasing Previously this was test_4 in ExceptionTests.
     */
    public void testTableAlias() throws Exception {

        // Create Table config programmatically
        // ConfigHelper helper = new ConfigHelper();
        // helper.addTable("BOOK", "Book");
        // helper.addPrimaryKey("BOOK.BOOK_ID");

        DAS das = DAS.FACTORY.createDAS(getConfig("BooksConfigWithAlias.xml"), getConnection());
        Command select = das.getCommand("get book by ID");
        select.setParameter(1, new Integer(1));

        DataObject root = select.executeQuery();

        DataObject newBook = root.createDataObject("Book");
        newBook.setString("NAME", "Ant Colonies of the Old World");
        newBook.setInt("BOOK_ID", 1001);
        root.getList("Book").add(newBook);

        das.applyChanges(root);

        root = select.executeQuery();

        // Verify
        select.setParameter(1, new Integer(1001));
        root = select.executeQuery();

        assertEquals("Ant Colonies of the Old World", root.getString("Book[1]/NAME"));
    }

    /**
     * Test ability to assign DataObject type and propertyaliases with xml file
     */
    public void testRead() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConfig("customerMapping.xml"), getConnection());
        // Read a customer
        Command select = das.createCommand("SELECT * FROM CUSTOMER WHERE CUSTOMER.ID = 1");

        DataObject root = select.executeQuery();
        DataObject customer = root.getDataObject("Customer[1]");
        assertEquals(1, customer.getInt("id"));
        assertEquals("1212 foobar lane", customer.getString("address"));
        assertEquals("Williams", customer.getString("lastname"));

    }
}
