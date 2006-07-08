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

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

/**
 * Tests the Converter framwork
 */
public class ProgrammaticConfigTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new BookData(getAutoConnection()).refresh();
        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Simple read followed by a write. This should fail since there is no
     * config associaed with the applychanges command
     */
    public void test_1() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read a book instance
        Command select = das.createCommand("SELECT * FROM BOOK WHERE BOOK_ID = 1");      
        DataObject root = select.executeQuery();
        DataObject book = root.getDataObject("BOOK[1]");
        // Change a field to mark the instance 'dirty'
        book.setInt("QUANTITY", 2);        

        try {
           das.applyChanges(root);
            fail("An exception should be thrown since here is no config to identify the primary key");
        } catch (RuntimeException ex) {
            // Expected
        }
    }

    /**
     * Simple read followed by a write. Config instance is generated
     * programmatically using the ConfigHelper.
     */
    public void test_2() throws Exception {
    	 // Create config programmatically
        ConfigHelper helper = new ConfigHelper();
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
    
    
    /**
     * Test ability to read a compound graph
     */
    public void test_3() throws Exception {

        String statement = "SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID WHERE CUSTOMER.ID = 1";

        // Read some customers and related orders
        // Create relationship config programmatically
        ConfigHelper helper = new ConfigHelper();
        helper.addRelationship("CUSTOMER.ID", "ANORDER.CUSTOMER_ID");
        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());
        Command select = das.createCommand(statement);       

        DataObject root = select.executeQuery();
        DataObject customer = root.getDataObject("CUSTOMER[1]");

        assertEquals(2, customer.getList("ANORDER").size());

    }
    
    /**
     * Programatically create table config with "property" name
     */
    public void test_4() throws Exception {

        String statement = "SELECT * FROM BOOK WHERE BOOK.BOOK_ID = ?";

        // Create Table config programmatically
        ConfigHelper helper = new ConfigHelper();
        helper.addTable("BOOK", "Book");
        helper.addPrimaryKey("Book.BOOK_ID");
        
        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());
        Command select = das.createCommand(statement);       
        select.setParameterValue(1, new Integer(1));

        DataObject root = select.executeQuery();
        
        DataObject newBook = root.createDataObject("Book");     
        newBook.setString("NAME", "Ant Colonies of the Old World");
        newBook.setInt("BOOK_ID", 1001);
        root.getList("Book").add(newBook);
                
        das.applyChanges(root);
        
        //Verify
        select.setParameterValue(1, new Integer(1001));
        root = select.executeQuery();
        assertEquals("Ant Colonies of the Old World", root.getString("Book[1]/NAME"));
        
    }
}
