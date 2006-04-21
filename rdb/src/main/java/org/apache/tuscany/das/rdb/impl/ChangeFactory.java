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
package org.apache.tuscany.das.rdb.impl;

import java.util.Iterator;

import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.generator.impl.CudGenerator;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

public class ChangeFactory {

	private final Type type; 
	
	private InsertCommandImpl createCommand;

	private UpdateCommandImpl updateCommand;

	private DeleteCommandImpl deleteCommand;

	private static final boolean debug = false;

	private CudGenerator cudGenerator;

	private final MappingWrapper mapping;

	private final ConnectionImpl connection; 
	
	public ChangeFactory(Type type, MappingWrapper mapping, ConnectionImpl connection) {
		this.type = type;
		this.mapping = mapping;
		this.connection = connection;
	}

	public Type getType() {
		return this.type;
	}
	
	public void setCreateCommand(InsertCommandImpl cmd) {
		createCommand = cmd;
	}

	public void setUpdateCommand(UpdateCommandImpl cmd) {
		DebugUtil.debugln(getClass(), debug, "Setting Update Command to " + cmd);
		updateCommand = cmd;
	}

	public void setDeleteCommand(DeleteCommandImpl cmd) {
		deleteCommand = cmd;
	}

	private CudGenerator getCudGenerator() {
		if (this.cudGenerator == null) 
			this.cudGenerator = new CudGenerator();
		return this.cudGenerator;
	}
	
	ChangeOperation createUpdateOperation(DataObject changedObject, String propagatedID) {
		return new UpdateOperation(getUpdateCommand(changedObject), changedObject, propagatedID);
	}
	ChangeOperation createUpdateOperation(DataObject changedObject) {
		return createUpdateOperation(changedObject, null);
	}

	ChangeOperation createDeleteOperation(DataObject changedObject) {
		return new DeleteOperation(getDeleteCommand(changedObject), changedObject);
	}

	ChangeOperation createInsertOperation(DataObject changedObject, String propagatedID) {
		return new CreateOperation(getCreateCommand(changedObject), changedObject, propagatedID);
	}
	
	ChangeOperation createInsertOperation(DataObject changedObject) {
		return createInsertOperation(changedObject, null);
	}


	private InsertCommandImpl getCreateCommand(DataObject changedObject) {
		
		if ( createCommand == null ) {
			Table table = mapping.getTable(changedObject.getType().getName());
			if (table == null ) {
				if (changedObject.getType().getProperty("ID") != null ) {
					// If the table is not defined in the config, assume it has a primary key of "ID"
					mapping.addPrimaryKey(changedObject.getType().getName() + ".ID");
					table = mapping.getTableByPropertyName(changedObject.getType().getName());
				} else {
					throw new RuntimeException("Table " + changedObject.getType().getName() + " was changed in the DataGraph but is not present in the Config");
				}
			}

	
			String createStatement = table.getCreate();
			if ( createStatement == null ) {
				createCommand = getCudGenerator().getInsertCommand(mapping, changedObject, table);
			} else {
				createCommand = new InsertCommandImpl(createStatement);
				Iterator i = getCudGenerator().getCreateParameters(mapping, changedObject, table).iterator();
				while (i.hasNext()) {
					Property p = (Property)i.next();				
					createCommand.addParameter(p.getName(), p.getType());
				}
			}
			createCommand.setConnection(connection);
			createCommand.configWrapper = mapping;
		}
		return createCommand;
	}

	private DeleteCommandImpl getDeleteCommand(DataObject changedObject) {
		
		if ( deleteCommand == null ) {
			Table table = mapping.getTable(changedObject.getType().getName());
			if (table == null )  {
				if (changedObject.getType().getProperty("ID") != null ) {
					// If the table is not defined in the config, assume it has a primary key of "ID"
					mapping.addPrimaryKey(changedObject.getType().getName() + ".ID");
					table = mapping.getTableByPropertyName(changedObject.getType().getName());
				} else {
					throw new RuntimeException("Table " + changedObject.getType().getName() + " was changed in the DataGraph but is not present in the Config");
				}
			}
			
			String deleteStatement = table.getDelete();
			if ( deleteStatement == null ) {
				deleteCommand = getCudGenerator().getDeleteCommand(table);
			} else {
				deleteCommand = new DeleteCommandImpl(deleteStatement);			
				Iterator i = getCudGenerator().getDeleteParameters(table).iterator();
				while (i.hasNext()) {
					deleteCommand.addParameter((String)i.next(), SDODataTypes.OBJECT);
				}
			}
			deleteCommand.setConnection(connection);
		}
		return deleteCommand;
	}

	private UpdateCommandImpl getUpdateCommand(DataObject changedObject) {
	
		if ( updateCommand == null ) {
			Table table = mapping.getTableByPropertyName(changedObject.getType().getName());
			if (table == null ) {
				if (changedObject.getType().getProperty("ID") != null ) {
					// If the table is not defined in the config, assume it has a primary key of "ID"
					mapping.addPrimaryKey(changedObject.getType().getName() + ".ID");
					table = mapping.getTableByPropertyName(changedObject.getType().getName());
				} else {
					throw new RuntimeException("Table " + changedObject.getType().getName() + " was changed in the DataGraph but is not present in the Config");
				}
			}
			String updateStatement = table.getUpdate();
			if ( updateStatement == null ) {
				updateCommand = getCudGenerator().getUpdateCommand(mapping, changedObject,table);
			} else {
				TableWrapper t = new TableWrapper(table);
				if ( t.getCollisionColumn() != null )
					updateCommand = new OptimisticWriteCommandImpl(updateStatement);
				else
					updateCommand = new UpdateCommandImpl(updateStatement);
			
			
				updateCommand.addParameters(getCudGenerator().getUpdateParameters(changedObject, table));
				
			}
			updateCommand.setConnection(connection);
			updateCommand.configWrapper = mapping;
		}
		DebugUtil.debugln(getClass(), debug, "Returning updateCommand: " + updateCommand);
		return updateCommand;
	}

	public MappingWrapper getConfig() {
		return this.mapping;
	}


}
