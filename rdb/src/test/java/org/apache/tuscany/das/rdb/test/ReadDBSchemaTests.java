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

/*
 * Test the ability to query Database schema(metadata) information using regular DAS APIs
 * This is speciic to DB2
 * 
 */

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.das.rdb.test.framework.SDOPrinter;


import commonj.sdo.DataObject;


public class ReadDBSchemaTests extends DasTest {

	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	
	public void testReadTableInfo() throws Exception {
		
		Command select = Command.FACTORY.createCommand("SELECT * from SYSIBM.SYSTABLES WHERE TYPE = 'T'");	
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();
		
		DataObject table = (DataObject)root.get("SYSTABLES[1]");
		
		assertEquals('T', table.getChar("TYPE"));
			
	}

	
	
	//Utilities
	
	private void write(String label, ResultSet rs) throws IOException, SQLException {
		
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		System.out.println("Contents of ResultSet from " + label);
		for (int i = 1; i <= count; i++) {
			System.out.print("\t");
			System.out.println (md.getColumnLabel(i));
		}
		System.out.println("");
		while (rs.next()) {
			for (int i = 1; i <= count; i++) {
				System.out.print("\t");
				System.out.print(rs.getString(i));
			}
			System.out.println("\t");
		}
		System.out.println("done");
	}
	
	
	private void printGraph(DataObject auth) {
		System.out.println(SDOPrinter.print(auth.getDataGraph()));
		
	}

	
}
