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
import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class BestPracticeTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CompanyData(getAutoConnection()).refresh();
    }

    //Read list of companies
    public void testReadCompanies() throws Exception {

        CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CompanyConfig.xml"));
        Command read = commandGroup.getCommand("all companies");
        DataObject root = read.executeQuery(); 
        assertEquals(3, root.getList("COMPANY").size());

    }

}
