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

import java.util.ArrayList;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.merge.impl.GraphMerger;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;


import commonj.sdo.DataObject;

public class GraphMergeTests extends DasTest {
	
	protected void setUp() throws Exception {
		super.setUp();
		
		new CustomerData(getAutoConnection()).refresh();
		new OrderData(getAutoConnection()).refresh();
		
	}


	public void testSingleTableMerge() throws Exception {
		
		Command select = Command.FACTORY
				.createCommand("Select ID, LASTNAME, ADDRESS from CUSTOMER where ID <= :ID");
		select.setConnection(getConnection());
		select.setParameterValue("ID", "3");
		DataObject graph1 = select.executeQuery();
		assertEquals(3, graph1.getList("CUSTOMER").size());

		select.setParameterValue("ID", "5");
		DataObject graph2 = select.executeQuery();
		assertEquals(5, graph2.getList("CUSTOMER").size());

		GraphMerger merger = new GraphMerger();
		merger.addPrimaryKey("CUSTOMER.ID");
		DataObject mergedGraph = merger.merge(graph1, graph2);

		assertEquals(5, mergedGraph.getList("CUSTOMER").size());
	}

	public void testSingleTableMergeThreeGraphs() throws Exception {

		Command select = Command.FACTORY
				.createCommand("Select ID, LASTNAME, ADDRESS from CUSTOMER where ID <= :ID");
		select.setConnection(getConnection());
		select.setParameterValue("ID", "3");
		DataObject graph1 = select.executeQuery();
		assertEquals(3, graph1.getList("CUSTOMER").size());

		select.setParameterValue("ID", "4");
		DataObject graph2 = select.executeQuery();
		assertEquals(4, graph2.getList("CUSTOMER").size());

		select.setParameterValue("ID", "5");
		DataObject graph3 = select.executeQuery();
		assertEquals(5, graph3.getList("CUSTOMER").size());

		GraphMerger merger = new GraphMerger();
		merger.addPrimaryKey("CUSTOMER.ID");
		ArrayList graphs = new ArrayList();
		graphs.add(graph1);
		graphs.add(graph2);
		graphs.add(graph3);
		DataObject mergedGraph = merger.merge(graphs);

		assertEquals(5, mergedGraph.getList("CUSTOMER").size());

	}

    
    public void testMultiTableMerge2() throws Exception {
        //Read some customers and related orders
        Command select = Command.FACTORY.createCommand(
                "SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = :ID", getConfig("CustomersOrdersConfig.xml"));  
        select.setConnection(getConnection());
        
        select.setConnection(getConnection());
        select.setParameterValue("ID", new Integer(1));
        DataObject graph1 = select.executeQuery();
        
        DataObject customer = (DataObject)graph1.getList("CUSTOMER").get(0);
        assertEquals(2, customer.getList("orders").size());    
        
        select.setParameterValue("ID", new Integer(2));
        DataObject graph2 = select.executeQuery();
        DataObject customer2 = (DataObject)graph2.getList("CUSTOMER").get(0);
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
        //Read some customers and related orders
        Command select = Command.FACTORY.createCommand(
                "SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = :ID", getConfig("CustomersOrdersConfig.xml"));  
        select.setConnection(getConnection());
        
        select.setConnection(getConnection());
        select.setParameterValue("ID", new Integer(1));
        DataObject graph1 = select.executeQuery();
        
        DataObject customer = (DataObject)graph1.getList("CUSTOMER").get(0);
        assertEquals(2, customer.getList("orders").size());
        
        Command select2 = Command.FACTORY.createCommand("select * from ANORDER");
        select2.setConnection(getConnection());
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
