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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ResultSetShape;
import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;


import commonj.sdo.DataObject;
import commonj.sdo.Type;

/**
 * Tests the Converter framwork
 */
public class ConverterTests extends DasTest {

	
	protected void setUp() throws Exception {
		super.setUp();
		new CustomerData(getAutoConnection()).refresh();
	}


	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	private static DateFormat myformat = new SimpleDateFormat("yyyy.MM.dd"); 

	private static Date kbday;
	private static Date tbday;
	
	static {
		try {
			kbday = myformat.parse("1957.09.27");
			tbday = myformat.parse("1966.12.20");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	/**
	 * This tests the use of an arbitrary converter.  The column converted is a VARCAHAR.
	 * ResultSetShape is used to specify that the property will be a SDODataTypes.DATE.
	 * 
	 * So this example uses a converter that transforms a string column into a date property
	 * and conversely, a date property back to a string for the underlying column.
	 * 
	 * The converter returns 1957.09.27 if the column value is "Williams" and 1966.12.20
	 * if the value is "Pavick"
	 * 
	 * On write, the converter returns "Pavick" if the property value is 1966.12.20
	 * and "Williams" if the property value is 1957.09.27
	 * 
	 */
	public void testArbitraryConverter() throws Exception {
		
		//Create and initialize command to read customers
		Command read = Command.FACTORY.createCommand("select * from CUSTOMER where ID = 1");	
		read.setConnection(getConnection());

		String[] columns = { "ID", "LASTNAME", "ADDRESS" };
		String[] tables = { "CUSTOMER", "CUSTOMER", "CUSTOMER" };
		Type[] types = { SDODataTypes.INTEGER, SDODataTypes.DATE, SDODataTypes.STRING };
		ResultSetShape shape = new ResultSetShape(tables, columns, types);
		read.setResultSetShape(shape);
		
		read.addConverter("CUSTOMER.LASTNAME", "org.apache.tuscany.das.rdb.test.mappings.SillyDateStringConverter");
				
		//Read
		DataObject root = read.executeQuery();
		
		//Verify 
		assertEquals(kbday, root.getDate("CUSTOMER[1]/LASTNAME"));
		
		//Modify
		root.setDate("CUSTOMER[1]/LASTNAME", tbday);	
		
		ApplyChangesCommand write = Command.FACTORY.createApplyChangesCommand();
		write.addPrimaryKey("CUSTOMER.ID");
		write.setConnection(getConnection());
		write.addConverter("CUSTOMER.LASTNAME", "org.apache.tuscany.das.rdb.test.mappings.SillyDateStringConverter");
		write.execute(root);
		
		//Read
		root = read.executeQuery();

		//Verify 
		assertEquals(tbday, root.getDate("CUSTOMER[1]/LASTNAME"));
		
	}
	
}
