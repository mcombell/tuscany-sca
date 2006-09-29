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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.CityData;
import org.apache.tuscany.das.rdb.test.data.StateData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class OperationOrderingTests extends DasTest {

    public OperationOrderingTests() {
        super();
    }

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

    public void testInsert() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("cityStates.xml"), getConnection());
        Command select = das.createCommand("Select * from STATES inner join CITIES on STATES.ID = CITIES.STATE_ID");
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

        das.applyChanges(root);

        root = select.executeQuery();
        assertEquals(numberOfCities + 1, root.getList("CITIES").size());
        assertEquals(numberOfStates + 1, root.getList("STATES").size());
    }

    public void testDeletes() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("cityStates.xml"), getConnection());
        Command select = das.createCommand("Select * from STATES inner join CITIES on STATES.ID = CITIES.STATE_ID");
        DataObject root = select.executeQuery();

        DataObject firstState = root.getDataObject("STATES[1]");
        String stateName = firstState.getString("NAME");

        List cityNames = new ArrayList();
        Iterator i = firstState.getList("cities").iterator();
        while (i.hasNext()) {
            DataObject firstCity = (DataObject) i.next();
            cityNames.add(firstCity.getString("NAME"));
            firstCity.delete();
        }
        firstState.delete();

        das.applyChanges(root);

        root = select.executeQuery();

        Iterator iter = root.getList("STATES").iterator();
        while (iter.hasNext()) {
            DataObject state = (DataObject) iter.next();
            assertFalse(state.getString("NAME").equals(stateName));
        }

        iter = root.getList("CITIES").iterator();
        while (iter.hasNext()) {
            DataObject city = (DataObject) iter.next();
            assertFalse(cityNames.contains(city.getString("NAME")));
        }

    }
}
