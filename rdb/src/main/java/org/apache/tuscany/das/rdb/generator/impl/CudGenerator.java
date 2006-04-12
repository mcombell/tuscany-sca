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
package org.apache.tuscany.das.rdb.generator.impl;

import java.util.Collection;

import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.impl.DeleteCommandImpl;
import org.apache.tuscany.das.rdb.impl.InsertCommandImpl;
import org.apache.tuscany.das.rdb.impl.UpdateCommandImpl;

import commonj.sdo.DataObject;

public class CudGenerator {

	private static final UpdateGenerator updateGenerator = UpdateGenerator.instance;

	private static final InsertGenerator insertGenerator = InsertGenerator.instance;

	private static final DeleteGenerator deleteGenerator = DeleteGenerator.instance;

	public DeleteCommandImpl getDeleteCommand(Table t) {
		return deleteGenerator.getDeleteCommand(t);
	}

	public Collection getDeleteParameters(Table table) {
		return deleteGenerator.getDeleteParameters(table);
	}

	public Collection getCreateParameters(MappingWrapper mapping, DataObject changedObject, Table table) {
		return insertGenerator.getInsertParameters(mapping, changedObject, table);
	}

	public InsertCommandImpl getInsertCommand(MappingWrapper mapping, DataObject changedObject,
			Table table) {
		return insertGenerator.getInsertCommand(mapping, changedObject, table);
	}

	public UpdateCommandImpl getUpdateCommand(MappingWrapper mapping,
			DataObject changedObject, Table table) {
		return updateGenerator.getUpdateCommand(mapping, changedObject, table);
	}

	public Collection getUpdateParameters(DataObject changedObject, Table table) {
		return updateGenerator.getUpdateParameters(changedObject, table);
	}

}

