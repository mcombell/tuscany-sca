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
 * Test the ability to query Database schema(metadata) information using regular DAS APIs This is speciic to DB2
 * 
 */

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class ReadDBSchemaTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReadTableInfo() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("SELECT * from SYSIBM.SYSTABLES WHERE TYPE = 'T'");
        DataObject root = select.executeQuery();

        DataObject table = (DataObject) root.get("SYSTABLES[1]");

        assertEquals('T', table.getChar("TYPE"));

    }

    //Utilities

}
