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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.test.customer.AnOrder;
import org.apache.tuscany.das.rdb.test.customer.Customer;
import org.apache.tuscany.das.rdb.test.customer.CustomerFactory;
import org.apache.tuscany.das.rdb.test.customer.DataGraphRoot;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.data.OrderData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.DataObject;
import commonj.sdo.helper.TypeHelper;

public class TopDown extends DasTest {

	protected void setUp() throws Exception {
		super.setUp();
		
		new CustomerData(getAutoConnection()).refresh();
		new OrderData(getAutoConnection()).refresh();

	}

	// Uses dynamic SDOs but user provides the model
	public void testUserProvidedModelDynamic() throws SQLException, IOException {

		// Build the select command
		Command select = Command.FACTORY
				.createCommand(
						"SELECT * FROM CUSTOMER LEFT JOIN ANORDER ON CUSTOMER.ID = ANORDER.CUSTOMER_ID where CUSTOMER.ID = :ID",
						getMappingModel_1xM_uni_no_cud_as_stream());

		SDOUtil.registerStaticTypes(CustomerFactory.class);
		
//		 Set the object model
		select.setDataObjectModel(TypeHelper.INSTANCE.getType(DataGraphRoot.class));
	

		// Parameterize the command
		select.setConnection(getConnection());
		select.setParameterValue("ID", new Integer(1));

		// Get the graph - DataGraphRoot is from the typed package
		DataGraphRoot root = (DataGraphRoot) select.executeQuery();

		// Modify a customer
		Customer customer = (Customer) root.getCustomers().get(0);
		customer.setLastName("Pavick");

		// Modify an order
		AnOrder order = (AnOrder) customer.getOrders().get(0);
		order.setProduct("Kitchen Sink 001");

		ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand();
		apply.setConnection(getConnection());
		apply.setMapping(getMappingModel_1xM_uni_no_cud_as_stream());

		// Flush changes
		apply.execute((DataObject) root);

		/*
		 * JDBCDAS das = JDBCDAS.FACTORY.create(); // Build the select command
		 * CrudCommand select = CrudCommand.FACTORY.create( "select CUSTOMER.ID,
		 * CUSTOMER.LASTNAME, CUSTOMER.ADDRESS, ANORDER.ID, ANORDER.PRODUCT,
		 * ANORDER.QUANTITY, ANORDER.CUSTOMER_ID from CUSTOMER left outer join
		 * ANORDER on ANORDER.CUSTOMER_ID=CUSTOMER.ID where CUSTOMER.ID = :ID" ,
		 * getMappingModel_1xM_uni_as_stream() , getGraphType()); //
		 * Parameterize the command select.setConnection(getConnection());
		 * select.setParameter("ID", new Integer(1)); // Get the graph
		 * DataObject root = das.getRootDataObject(select); // Modify a customer
		 * DataObject customer = (DataObject) root.getList("CUSTOMER").get(0);
		 * customer.set("LASTNAME", "Pavick"); // Modify the first order
		 * DataObject order = (DataObject)customer.getList("orders").get(0);
		 * order.setString("PRODUCT", "Kitchen Sink 001"); // Build command for
		 * flush ApplyChangesCommand command =
		 * ApplyChangesCommand.FACTORY.create(getMappingModel_1xM_uni_as_stream());
		 * command.setConnection(getConnection()); // Flush changes
		 * das.applyChanges(root, command);
		 */
	}

	/*
	 * //User provides the model and uses generated classes public void
	 * testUserProvidedModelStatic() throws SQLException, IOException {
	 * 
	 * JDBCDAS das = JDBCDAS.FACTORY.create();
	 * 
	 * //Build the select command CrudCommand select =
	 * CrudCommand.FACTORY.create( "select CUSTOMER.ID, CUSTOMER.LASTNAME,
	 * CUSTOMER.ADDRESS, ANORDER.ID, ANORDER.PRODUCT, ANORDER.QUANTITY,
	 * ANORDER.CUSTOMER_ID from CUSTOMER left outer join ANORDER on
	 * ANORDER.CUSTOMER_ID=CUSTOMER.ID where CUSTOMER.ID = :ID" ,
	 * getMappingModel_1xM_uni_as_stream() , getGraphType());
	 * 
	 * //Parameterize the command select.setConnection(getConnection());
	 * select.setParameter("ID", new Integer(1));
	 * 
	 * //Get the graph DataObject root = das.getRootDataObject(getGraphType(),
	 * select);
	 * 
	 * //Modify a customer DataGraphRoot dgRoot = (DataGraphRoot) root; // TODO
	 * Need to cast to Static customer and use "setLastName()"
	 * 
	 * Customer customer = (Customer) dgRoot.getCustomers().get(0);
	 * customer.setLastName("Pavick");
	 * 
	 * //Modify the first order AnOrder order = customer.getOrders();
	 * order.setProduct("Kitchen Sink 001");
	 * 
	 * //Build command for flush ApplyChangesCommand command =
	 * ApplyChangesCommand.FACTORY.create(getMappingModel_1xM_uni_as_stream());
	 * command.setConnection(getConnection());
	 * 
	 * //Flush changes das.applyChanges(root, command); }
	 */



	private InputStream getMappingModel_1xM_uni_no_cud_as_stream()
			throws FileNotFoundException {
		return getClass().getClassLoader().getResourceAsStream("1xM_mapping_no_cud.xml");
	}
}