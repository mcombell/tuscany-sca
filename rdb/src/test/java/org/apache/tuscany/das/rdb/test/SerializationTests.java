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
 * This class provides tests for all supported "types". The current plan 
 * is to use the Data type definitions provided in the SDO 2 specification. We
 * must test the ability to use all of these types as well as different mapping 
 * from thse types to types used in the database. For example, a SDO Data
 * Type "STRING", might be staored as a "TIMESTAMP" in DB2.
 * 
 */

import org.apache.tuscany.das.rdb.test.data.TypesData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

public class SerializationTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new TypesData(getAutoConnection()).refresh();
    }

    /**
     * Read various types.
     */

    public void testReadandSerialize() throws Exception {
        /**
         * Currently failing because of TUSCANY-22 
         * Command select = Command.FACTORY .createCommand("Select * from TYPETEST where ID = 1");
         * select.setConnection(getConnection()); DataObject root = select.executeQuery();
         * 
         * DataObject obj = root.getDataObject("TYPETEST[1]");
         * 
         * assertTrue(obj.isSet("ID")); assertTrue(obj.isSet("ATIMESTAMP")); 
         * assertTrue(obj.isSet("ADECIMAL")); assertTrue(obj.isSet("AFLOAT"));
         * 
         * //Java serilaization to file FileOutputStream fos = null; ObjectOutputStream out = null; try { fos = new
         * FileOutputStream("serializedGraph.xml"); out = new ObjectOutputStream(fos); 
         * out.writeObject(root); 
         * out.flush(); 
         * } finally { 
         * out.close();
         * fos.close(); }
         * 
         * //Reconstruct the graph FileInputStream fis = null; ObjectInputStream in = null; 
         * DataObject root2; try { fis = new
         * FileInputStream("serializedGraph.xml"); in = new ObjectInputStream(fis); 
         * root2 = (DataObject) in.readObject(); } finally { in.close();
         * fis.close(); }
         * 
         * assertEquals(1, root.getInt("TYPETEST[1]/ID")); assertEquals(TypesData.getTimestamp(),
         * (java.sql.Timestamp)root.get("TYPETEST[1]/ATIMESTAMP")); 
         * assertEquals(1234567.89f, root2.getFloat("TYPETEST[1]/ADECIMAL"), .001);
         * assertEquals(1234567.89f, root2.getFloat("TYPETEST[1]/AFLOAT"), .001);
         * 
         */
    }
}
