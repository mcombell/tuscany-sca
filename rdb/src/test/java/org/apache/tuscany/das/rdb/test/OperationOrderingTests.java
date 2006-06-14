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
import org.apache.tuscany.das.rdb.test.data.CityData;
import org.apache.tuscany.das.rdb.test.data.StateData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class OperationOrderingTests extends DasTest {

	protected void setUp() throws Exception {
		super.setUp();

		CityData city = new CityData(getAutoConnection());
		StateData state = new StateData(getAutoConnection());

		city.doDeletes();
		state.doDeletes();
		state.doInserts();
		city.doInserts();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public OperationOrderingTests() {
		super();
	}

	public void testInsert() throws Exception {
		DAS das = DAS.FACTORY.createDAS(getConfig("cityStates.xml"));
		Command select = das
				.createCommand(
						"Select * from STATES inner join CITIES on STATES.ID = CITIES.STATE_ID",
						getConfig("cityStates.xml"));
		select.setConnection(getConnection());
		DataObject root = select.executeQuery();

		int numberOfStates = root.getList("STATES").size();
		int numberOfCities = root.getList("CITIES").size();

		DataObject atlanta = root.createDataObject("CITIES");
		atlanta.setString("NAME", "Atlanta");
		atlanta.setInt("ID", 6);

		// Create a new Company
		DataObject georgia = root.createDataObject("STATES");
		georgia.setInt("ID", 4);
		georgia.setString("NAME", "GA");

		georgia.getList("cities").add(atlanta);

		das.setConnection(getConnection());
		das.applyChanges(root);

		select.setConnection(getConnection());
		root = select.executeQuery();
		assertEquals(numberOfCities + 1, root.getList("CITIES").size());
		assertEquals(numberOfStates + 1, root.getList("STATES").size());
	}

}
