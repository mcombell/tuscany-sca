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

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.test.company.CompanyFactory;
import org.apache.tuscany.das.rdb.test.customer.DataGraphRoot;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.DataObject;
import commonj.sdo.helper.TypeHelper;

public class ExceptionTests extends DasTest {

    public ExceptionTests() {
        super();
    }

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();
        new BookData(getAutoConnection()).refresh();
        new OrderDetailsData(getAutoConnection()).refresh();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMissingConnection() throws Exception {
        Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1");
        try {
            readCustomers.executeQuery();
            fail("RuntimeException should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("A DASConnection object must be specified before executing the query.", ex.getMessage());
        }

    }


    public void testMissingMapping() throws Exception {
        Command readCustomers = Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1");
        readCustomers.setConnection(getConnection());
        SDOUtil.registerStaticTypes(CompanyFactory.class);
        readCustomers.setDataObjectModel(TypeHelper.INSTANCE.getType(DataGraphRoot.class));

        try {
            readCustomers.executeQuery();
            fail("Exception should be thrown");
        } catch (RuntimeException ex) {
            assertEquals("An SDO Type with name CUSTOMER was not found", ex.getMessage());
        }

    }

    /**
     * Test ability to describe problem when a user passes an empty stream
     */
    public void testEmptyStream() throws Exception {
        try {
            Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1", getConfig("NonExistingFile.xml"));
            fail("Error should be thrown");
        } catch (RuntimeException e) {
            assertEquals(
                    "Cannot load configuration from a null InputStream. Possibly caused by an incorrect config xml file name",
                    e.getMessage());
        }
    }

    /**
     * Test ability to describe problem when a user passes an empty stream
     */
    public void testEmptyStream2() throws Exception {
        try {
            CommandGroup.FACTORY.createCommandGroup(getConfig("NonExistingFile.xml"));
            fail("Error should be thrown");
        } catch (RuntimeException e) {
            assertEquals(
                    "Cannot load configuration from a null InputStream. Possibly caused by an incorrect config xml file name",
                    e.getMessage());
        }
    }
    
    
    /**
     * Test nice error message when Conofig does not use Aliased name
     * Alis is defined for BOOK -> Book but PK config uses BOOK.BOOK_ID
     * 
     * In this test case, the create is silently never performed!!!
     * 
     * TODO - Uncomment out
     */
    public void test_4() throws Exception {

        String statement = "SELECT * FROM BOOK WHERE BOOK.BOOK_ID = :ID";

        // Create Table config programmatically
        ConfigHelper helper = new ConfigHelper();
        helper.addTable("BOOK", "Book");
        helper.addPrimaryKey("BOOK.BOOK_ID");
        
        Command select = Command.FACTORY.createCommand(statement, helper.getConfig());
        select.setConnection(getConnection());
        select.setParameterValue("ID", new Integer(1));

        DataObject root = select.executeQuery();
        
        DataObject newBook = root.createDataObject("Book");
        newBook.setString("NAME", "Ant Colonies of the Old World");
        newBook.setInt("BOOK_ID", 1001);
        root.getList("Book").add(newBook);
               
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand(helper.getConfig());
        apply.setConnection(getConnection());
        apply.execute(root);
        
        //Verify
        select.setParameterValue("ID", new Integer(1001));
        root = select.executeQuery();
        //TODO - Uncomment to test TUSCANY-250
//        assertEquals("Ant Colonies of the Old World", root.getString("Book[1]/NAME"));
        
    }
    
    
    public void testReadOrdersAndDetails2() throws Exception {

        Command read = Command.FACTORY
				.createCommand(
						"SELECT * FROM ANORDER LEFT JOIN ORDERDETAILS ON ANORDER.ID = ORDERDETAILS.ORDERID ORDER BY ANORDER.ID",
						getConfig("InvalidConfig1.xml"));
		read.setConnection(getConnection());

		try {
			DataObject root = read.executeQuery();
		} catch ( Exception ex ) {
			assertEquals("The parent table (xxx) in relationship ORDERDETAILS was not found.", ex.getMessage());
		}	

    }
  
    
}
