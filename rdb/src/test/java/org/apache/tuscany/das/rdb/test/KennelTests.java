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

import java.sql.Timestamp;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.DogData;
import org.apache.tuscany.das.rdb.test.data.KennelData;
import org.apache.tuscany.das.rdb.test.data.OwnerData;
import org.apache.tuscany.das.rdb.test.data.OwnerDogData;
import org.apache.tuscany.das.rdb.test.data.VisitData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.das.rdb.test.framework.TestData;

import commonj.sdo.DataObject;

public class KennelTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new DogData(getAutoConnection()).refresh();
        new OwnerData(getAutoConnection()).refresh();
        new OwnerDogData(getAutoConnection()).refresh();
        new KennelData(getAutoConnection()).refresh();
        new VisitData(getAutoConnection()).refresh();
                

    }

    public void testSimple() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());

        // Build the select command
        Command selectCommand = das.createCommand("select * from DOG");

        // Get the graph
        DataObject root = selectCommand.executeQuery();

        assertEquals(3, root.getList("DOG").size());

    }
    
    public void testSimple2() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());

        // Build the select command
        Command selectCommand = das.createCommand("select * from OWNER");

        // Get the graph
        DataObject root = selectCommand.executeQuery();

        assertEquals(3, root.getList("OWNER").size());

    }
    
    public void testSimple3() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());

        // Build the select command
        Command selectCommand = das.createCommand("select * from OWNER, DOG "
                + "where DOG.OWNER_ID = OWNER.ID and OWNER.NAME = 'Fanny'");

        // Get the graph
        DataObject root = selectCommand.executeQuery();
        
        //Get Fanny
        DataObject fanny = root.getDataObject("OWNER[1]");
        assertEquals("Fido", fanny.getString("DOG[1]/NAME"));

    }

    public void testSimple4() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());

        // Build the select command
        Command selectCommand = das.createCommand("select * from KENNEL");

        // Get the graph
        DataObject root = selectCommand.executeQuery();
        
        assertEquals(3, root.getList("KENNEL").size());
 
    }
    
    public void testSimple5() throws Exception {

        DAS das = DAS.FACTORY.createDAS(getConnection());

        // Build the select command
        Command selectCommand = das.createCommand("select * from VISIT");

        // Get the graph
        DataObject root = selectCommand.executeQuery();
        
        assertEquals(3, root.getList("VISIT").size());
        assertEquals(TestData.getTimestamp("2006-10-20 00:00:00.0"), (Timestamp)root.get("VISIT[1]/CHECK_IN"));
 
    }

}
