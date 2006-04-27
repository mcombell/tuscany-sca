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
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;


import commonj.sdo.DataObject;

public class OCCTests extends DasTest {
	protected void setUp() throws Exception {
		super.setUp();
		
		new BookData(getAutoConnection()).refresh();
	}

	
	public void testSimpleOCC() throws Exception {
		
		//Read a book instance
		Command select = Command.FACTORY.createCommand("SELECT * FROM BOOK WHERE BOOK_ID = 1");
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();
		DataObject book = root.getDataObject("BOOK[1]");
		//Change a field to mark the instance 'dirty'
		book.setInt("QUANTITY", 2);

		// Explicitly change OCC column in database to force collision
		Command update = Command.FACTORY
				.createCommand("update BOOK set OCC = :OCC where BOOK_ID = 1");
		update.setConnection(getConnection());
		update.setParameterValue("OCC", new Integer(100));
		update.execute();

		//Try to flush the change
		ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand(getConfig("BooksConfig.xml"));
		apply.setConnection(getConnection());

		try {
			apply.execute(root);
			fail("An OCCException should be thrown");
		} catch (RuntimeException ex) {
			if ( !ex.getMessage().equals("OCC Exception") )
				throw ex;
		}
	}
}
