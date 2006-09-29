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
package org.apache.tuscany.das.rdb.test.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.impl.ReadCommandImpl;

public class ReadCustomersWithShapeCommand extends ReadCommandImpl {
    // This sql string ensures that we won't have resultset metadata
    static String sqlString = "select * from customer union select * from customer";

    static List descriptor = new ArrayList();

    static {
        ConfigFactory factory = ConfigFactory.INSTANCE;

        ResultDescriptor desc1 = factory.createResultDescriptor();
        desc1.setColumnName("ID");
        desc1.setColumnType("commonj.sdo.Int");
        desc1.setTableName("CUSTOMER");

        ResultDescriptor desc2 = factory.createResultDescriptor();
        desc2.setColumnName("LASTNAME");
        desc2.setColumnType("commonj.sdo.String");
        desc2.setTableName("CUSTOMER");

        ResultDescriptor desc3 = factory.createResultDescriptor();
        desc3.setColumnName("ADDRESS");
        desc3.setColumnType("commonj.sdo.String");
        desc3.setTableName("CUSTOMER");

        descriptor.add(desc1);
        descriptor.add(desc2);
        descriptor.add(desc3);

    }

    public ReadCustomersWithShapeCommand() {
        super(sqlString, new MappingWrapper(), descriptor);
    }

}
