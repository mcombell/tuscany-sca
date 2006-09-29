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

/*
 * This class provides tests for all supported "types".  The current plan is to use the Data
 * type definitions provided in the SDO 2 specification.  We must test the ability to use
 * all of these types as well as different mapping from thse types to types used in the 
 * database.  For example, a SDO Data Type "STRING", might be staored as a "TIMESTAMP" in DB2.
 * 
 */

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.TypesData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class TypeTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new TypesData(getAutoConnection()).refresh();
    }

    /**
     * Read various types.
     */
    public void testRead() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        // Read customer 1
        Command select = das.createCommand("Select * from TYPETEST where ID = 1");
        DataObject root = select.executeQuery();

        DataObject types = (DataObject) root.get("TYPETEST[1]");

        java.sql.Timestamp ts = (java.sql.Timestamp) types.get("ATIMESTAMP");
        assertEquals(ts, TypesData.getTimestamp());

        float decimal = types.getFloat("ADECIMAL");
        assertEquals(1234567.89f, decimal, .0001);

    }

    /**
     * Write various types.  
     * TODO - Need to rethink the Timestamp write.  My current thinking id that writes of non-SDO2 defined types 
     * require a converter
     */
    /*    public void testWrite() throws Exception {

     //Read customer 1
     Command select = Command.FACTORY.createCommand("Select * from TYPETEST where ID = 1");  
     select.setConnection(getConnection());
     DataObject root = select.executeQuery();
     
     DataObject types = (DataObject)root.get("TYPETEST[1]");
     Date now = new Date();
     types.set("ATIMESTAMP", now);
     
     ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand();
     apply.setConnection(getConnection());
     apply.addPrimaryKey("TYPETEST.ID");
     apply.execute(root);
     
     //Verify
     root = select.executeQuery();
     java.sql.Timestamp ts = (java.sql.Timestamp)types.get("ATIMESTAMP");
     assertEquals(now, ts);
     
     }*/

}
