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
package org.apache.tuscany.das.rdb.test.typed;

import java.io.InputStream;
import java.util.Collection;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.merge.impl.GraphMerger;
import org.apache.tuscany.das.rdb.test.customer.CustomerFactory;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;
import commonj.sdo.impl.HelperProvider;

/**
 */
public class SimplestStaticCrud extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();
        new CustomerData(getAutoConnection()).refresh();
    }

    public void testRead() throws Exception {
        InputStream mapping = getClass().getClassLoader().getResourceAsStream("basicStaticCustomer.xml");
        DAS das = DAS.FACTORY.createDAS(mapping, getConnection());
        HelperContext context = HelperProvider.getDefaultContext();
        CustomerFactory.INSTANCE.register(context);

        Command select = das.createCommand("Select ID, LASTNAME, ADDRESS from CUSTOMER where LASTNAME = ?");
        select.setParameter(1, "Williams");

        DataObject root = select.executeQuery();

        Collection customers = root.getList("Customer");
        assertEquals(4, customers.size());
    }

}
