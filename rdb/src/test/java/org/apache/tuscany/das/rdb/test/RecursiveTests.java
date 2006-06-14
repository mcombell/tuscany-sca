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

import java.util.Iterator;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.ResultSetShape;
import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.impl.ReadCommandImpl;
import org.apache.tuscany.das.rdb.test.data.PartData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;
import commonj.sdo.Type;

public class RecursiveTests extends DasTest {

	protected void setUp() throws Exception {
		super.setUp();
		new PartData(getAutoConnection()).refresh();
	}

	public void testReadEngineParts() throws Exception {
		
		DAS das = DAS.FACTORY.createDAS(getConfig("PartsConfig.xml"));
		//Table definition
		//CREATE TABLE PART (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(50), QUANTITY INT, PARENT_ID INT );

		String threeLevelPartsSQL = "SELECT P1.*, P2.*, P3.* FROM PART AS P1 LEFT JOIN PART AS P2 ON P1.ID = P2.PARENT_ID "
				+ "LEFT JOIN PART AS P3 on P2.ID = P3.PARENT_ID WHERE P1.ID = 1";
		Command select = das.createCommand(threeLevelPartsSQL, getConfig("PartsConfig.xml"));
		select.setConnection(getConnection());

		String[] columns = {"ID", "NAME", "QUANTITY", "PARENT_ID", "ID", "NAME", "QUANTITY", "PARENT_ID", "ID", "NAME", "QUANTITY", "PARENT_ID"};
		String[] tables = {"PART", "PART", "PART", "PART", "PART", "PART", "PART", "PART", "PART", "PART", "PART", "PART"};
		Type[] types = {SDODataTypes.INTEGEROBJECT, SDODataTypes.STRING, SDODataTypes.INTEGEROBJECT, SDODataTypes.INTEGEROBJECT, SDODataTypes.INTEGEROBJECT, SDODataTypes.STRING, SDODataTypes.INTEGEROBJECT, SDODataTypes.INTEGEROBJECT, SDODataTypes.INTEGEROBJECT, SDODataTypes.STRING, SDODataTypes.INTEGEROBJECT, SDODataTypes.INTEGEROBJECT};
		ResultSetShape shape = new ResultSetShape(tables, columns, types);

		//Hack until we provide a nicer API
		((ReadCommandImpl)select).setResultSetShape(shape);	
		
		// Need to set the key explicitly. The aggregate of columns not working
		// because of null values
		DataObject root = select.executeQuery();

		assertEquals(5, root.getList("PART").size());
	//	printList(root.getList("PART"));
		DataObject engine = root.getDataObject("PART.0");
		assertEquals("Engine", engine.getString("NAME"));
		
		assertEquals(3, engine.getList("subparts").size());
		
		DataObject piston = null;
		Iterator i = engine.getList("subparts").iterator();
		while ( i.hasNext() ) {
			DataObject obj = (DataObject)i.next();
			if ( obj.getString("NAME").equals("Piston"))
				piston = obj;
		}
		
		assertEquals("Piston", piston.getString("NAME"));
		assertEquals(1, piston.getList("subparts").size());
		assertEquals("Piston Ring", piston.getDataObject("subparts.0").getString("NAME"));

	}
	
}
