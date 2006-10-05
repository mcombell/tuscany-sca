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
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.merge.impl.GraphMerger;
import org.apache.tuscany.das.rdb.test.customer.Customer;
import org.apache.tuscany.das.rdb.test.customer.CustomerFactory;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.DataObject;

public class GraphMergeTests extends DasTest {

    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();
        new OrderData(getAutoConnection()).refresh();

    }

    public void testCreateEmptyGraph() throws Exception {
        String typeUri = "http:///org.apache.tuscany.das.rdb.test/customer.xsd";
        SDOUtil.registerStaticTypes(CustomerFactory.class);
        ConfigHelper helper = new ConfigHelper();
        helper.setDataObjectModel(typeUri);
        DataObject graph = new GraphMerger().emptyGraph(helper.getConfig());
        assertEquals(0, graph.getList("Customer").size());
        assertEquals(0, graph.getList("AnOrder").size());

    }

    public void testCreateEmptyGraphAndAddCustomer() throws Exception {
        String typeUri = "http:///org.apache.tuscany.das.rdb.test/customer.xsd";
        SDOUtil.registerStaticTypes(CustomerFactory.class);
        ConfigHelper helper = new ConfigHelper();
        helper.setDataObjectModel(typeUri);
        helper.addTable("CUSTOMER", "Customer");
        helper.addPrimaryKey("CUSTOMER.ID");

        DataObject graph = new GraphMerger().emptyGraph(helper.getConfig());
        Customer c = (Customer) graph.createDataObject("Customer");
        c.setID(4000);
        c.setLastName("Smith");
        c.setAddress("400 Fourth Street");

        DAS das = DAS.FACTORY.createDAS(helper.getConfig(), getConnection());
        das.applyChanges(graph);

        Command cmd = das.createCommand("select * from CUSTOMER order by ID desc");
        graph = cmd.executeQuery();
        assertEquals(6, graph.getList("Customer").size());
        assertEquals("Smith", graph.getDataObject("Customer[1]").getString("lastName"));
        assertEquals("400 Fourth Street", graph.getDataObject("Customer[1]").getString("address"));

    }

    public void testSingleTableMerge() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("Select ID, LASTNAME, ADDRESS from CUSTOMER where ID <= ?");
        select.setParameter(1, "3");
        DataObject graph1 = select.executeQuery();
        assertEquals(3, graph1.getList("CUSTOMER").size());

        select.setParameter(1, "5");
        DataObject graph2 = select.executeQuery();
        assertEquals(5, graph2.getList("CUSTOMER").size());

        GraphMerger merger = new GraphMerger();
        merger.addPrimaryKey("CUSTOMER.ID");
        DataObject mergedGraph = merger.merge(graph1, graph2);

        assertEquals(5, mergedGraph.getList("CUSTOMER").size());
    }

    public void testSingleTableMergeThreeGraphs() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConnection());
        Command select = das.createCommand("Select ID, LASTNAME, ADDRESS from CUSTOMER where ID <= ?");
        select.setParameter(1, "3");
        DataObject graph1 = select.executeQuery();
        assertEquals(3, graph1.getList("CUSTOMER").size());

        select.setParameter(1, "4");
        DataObject graph2 = select.executeQuery();
        assertEquals(4, graph2.getList("CUSTOMER").size());

        select.setParameter(1, "5");
        DataObject graph3 = select.executeQuery();
        assertEquals(5, graph3.getList("CUSTOMER").size());

        GraphMerger merger = new GraphMerger();
        merger.addPrimaryKey("CUSTOMER.ID");
        List graphs = new ArrayList();
        graphs.add(graph1);
        graphs.add(graph2);
        graphs.add(graph3);
        DataObject mergedGraph = merger.merge(graphs);

        assertEquals(5, mergedGraph.getList("CUSTOMER").size());

    }

    public void testMultiTableMerge2() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());
        // Read some customers and related orders
        Command select = das.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON " 
                + "CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = ?");

        select.setParameter(1, Integer.valueOf(1));
        DataObject graph1 = select.executeQuery();

        DataObject customer = (DataObject) graph1.getList("CUSTOMER").get(0);
        assertEquals(2, customer.getList("orders").size());

        select.setParameter(1, Integer.valueOf(2));
        DataObject graph2 = select.executeQuery();
        DataObject customer2 = (DataObject) graph2.getList("CUSTOMER").get(0);
        assertEquals(1, graph2.getList("CUSTOMER").size());
        assertEquals(1, customer2.getList("orders").size());
        assertEquals(2, customer2.getInt("ID"));

        GraphMerger merger = new GraphMerger();
        merger.addPrimaryKey("CUSTOMER.ID");
        merger.addPrimaryKey("ANORDER.ID");
        DataObject mergedGraph = merger.merge(graph1, graph2);

        assertEquals(3, mergedGraph.getList("ANORDER").size());
        assertEquals(2, mergedGraph.getList("CUSTOMER").size());

        DataObject mergedCustomer = (DataObject) mergedGraph.getList("CUSTOMER").get(1);
        assertEquals(2, mergedCustomer.getInt("ID"));
        assertEquals(1, mergedCustomer.getList("orders").size());
        DataObject mergedOrder = (DataObject) mergedCustomer.getList("orders").get(0);
        assertEquals(4, mergedOrder.getInt("ID"));

    }

    public void testMultiTableAppendSingleTable2() throws Exception {
        DAS das = DAS.FACTORY.createDAS(getConfig("CustomersOrdersConfig.xml"), getConnection());
        // Read some customers and related orders
        Command select = das.createCommand("SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON " 
                + "CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = ?");

        select.setParameter(1, Integer.valueOf(1));
        DataObject graph1 = select.executeQuery();

        DataObject customer = (DataObject) graph1.getList("CUSTOMER").get(0);
        assertEquals(2, customer.getList("orders").size());

        DAS das2 = DAS.FACTORY.createDAS(getConnection());
        Command select2 = das2.createCommand("select * from ANORDER");
        DataObject graph2 = select2.executeQuery();
        assertEquals(4, graph2.getList("ANORDER").size());

        GraphMerger merger = new GraphMerger();
        merger.addPrimaryKey("CUSTOMER.ID");
        merger.addPrimaryKey("ANORDER.ID");
        DataObject mergedGraph = merger.merge(graph1, graph2);
        assertEquals(4, mergedGraph.getList("ANORDER").size());
        assertEquals(1, mergedGraph.getList("CUSTOMER").size());
    }

}
