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
package org.apache.tuscany.das.rdb.test.commands;

import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.impl.ReadCommandImpl;


public class ReadCustomersCommand extends ReadCommandImpl {

	private static final String sqlString = "select * from CUSTOMER";
	private static final Config mapping;
	
	static {
		ConfigFactory factory = ConfigFactory.eINSTANCE;
		mapping = factory.createConfig();
		Table t = factory.createTable();
		Column id = factory.createColumn();
		id.setName("ID");
		id.setPrimaryKey(true);
		Column lastname = factory.createColumn();
		lastname.setName("LASTNAME");
		Column address = factory.createColumn();
		address.setName("ADDRESS");
	
		t.getColumn().add(id);
		t.getColumn().add(lastname);
		t.getColumn().add(address);
		t.setName("CUSTOMER");
		mapping.getTable().add(t);
	}
	
	public ReadCustomersCommand() {
		super(sqlString, mapping);
	}

}
