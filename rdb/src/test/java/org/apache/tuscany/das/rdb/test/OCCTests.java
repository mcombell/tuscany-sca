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
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class OCCTests extends DasTest {
	protected void setUp() throws Exception {
		super.setUp();
		
		new BookData(getAutoConnection()).refresh();
	}

	
	public void testSimpleOCC() throws Exception {
        
		DAS das = DAS.FACTORY.createDAS(getConfig("BooksConfig.xml"), getConnection());
		//Read a book instance
        Command select = das.getCommand("select book 1");
		DataObject root = select.executeQuery();
		DataObject book = root.getDataObject("BOOK[1]");
		//Change a field to mark the instance 'dirty'
		book.setInt("QUANTITY", 2);

		// Explicitly change OCC column in database to force collision
        Command update = das.getCommand("update book 1");
		update.setParameter(1, new Integer(100));
		update.execute();		

        //Try to apply changes and catch the expected OCC Exception
		try {		
			das.applyChanges(root);
			fail("An OCCException should be thrown");
		} catch (RuntimeException ex) {
			if ( !ex.getMessage().equals("OCC Exception") )
				throw ex;
		}
	}
	
	public void testManagedOCC() throws Exception {
		DAS das = DAS.FACTORY.createDAS(getConfig("ManagedBooksConfig.xml"), getConnection());
		Command select = das.getCommand("select book 1");
		DataObject root = select.executeQuery();
		DataObject book = root.getDataObject("BOOK[1]");
		//Change a field to mark the instance 'dirty'
		book.setInt("QUANTITY", 2);
		int occValue = book.getInt("OCC");
		das.applyChanges(root);
		
		root = select.executeQuery();
		book = root.getDataObject("BOOK[1]");
		assertEquals(occValue + 1, book.getInt("OCC"));		
	}
	
	public void testManagedOCCFailure() throws Exception {
		DAS das = DAS.FACTORY.createDAS(getConfig("ManagedBooksConfig.xml"), getConnection());
		//Read a book instance
        Command select = das.getCommand("select book 1");
		DataObject root = select.executeQuery();
		DataObject book = root.getDataObject("BOOK[1]");
		//Change a field to mark the instance 'dirty'
		book.setInt("QUANTITY", 2);

		
		DAS das2 = DAS.FACTORY.createDAS(getConfig("ManagedBooksConfig.xml"), getConnection());
		//Read a book instance
        Command select2= das2.getCommand("select book 1");
		DataObject root2 = select2.executeQuery();
		DataObject book2 = root2.getDataObject("BOOK[1]");
		//Change a field to mark the instance 'dirty'
		book2.setInt("QUANTITY", 5);
		das2.applyChanges(root2);

        //Try to apply changes and catch the expecetd OCC Exception
		try {		
			das.applyChanges(root);
			fail("An OCCException should be thrown");
		} catch (RuntimeException ex) {
			if ( !ex.getMessage().equals("OCC Exception") )
				throw ex;
		}
	}
}
