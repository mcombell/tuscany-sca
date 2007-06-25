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
package org.apache.tuscany.das.rdb.impl;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.config.Create;
import org.apache.tuscany.das.rdb.config.Delete;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.Update;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.generator.impl.DeleteGenerator;
import org.apache.tuscany.das.rdb.generator.impl.InsertGenerator;
import org.apache.tuscany.das.rdb.generator.impl.UpdateGenerator;

import commonj.sdo.DataObject;

public class ChangeFactory {
    private final Logger logger = Logger.getLogger(ChangeFactory.class);

    private InsertCommandImpl createCommand;

    private UpdateCommandImpl updateCommand;

    private DeleteCommandImpl deleteCommand;

    private final MappingWrapper mapping;

    private final ConnectionImpl connection;

    public ChangeFactory(MappingWrapper mapping, ConnectionImpl connection) {
        this.mapping = mapping;
        this.connection = connection;
    }

    public void setCreateCommand(InsertCommandImpl cmd) {
        createCommand = cmd;
    }

    public void setUpdateCommand(UpdateCommandImpl cmd) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Setting Update Command to " + cmd);
        }

        updateCommand = cmd;
    }

    public void setDeleteCommand(DeleteCommandImpl cmd) {
        deleteCommand = cmd;
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

    private InsertCommandImpl getCreateCommand(DataObject changedObject) {

        if (createCommand == null) {
            Table table = mapping.getTableByTypeName(changedObject.getType().getName());
            if (table == null) {
                if (changedObject.getType().getProperty("ID") != null) {
                    // If the table is not defined in the config, assume it has a primary key of "ID"
                    mapping.addPrimaryKey(changedObject.getType().getName() + ".ID");
                    table = mapping.getTableByTypeName(changedObject.getType().getName());
                } else {
                    throw new RuntimeException("Table " + changedObject.getType().getName()
                            + " was changed in the DataGraph but is not present in the Config");
                }
            }

            Create create = table.getCreate();

            if (create == null) {
                createCommand = InsertGenerator.INSTANCE.getInsertCommand(mapping, changedObject, table);
            } else {
                createCommand = new InsertCommandImpl(create);
            }
            createCommand.setConnection(connection);
            createCommand.configWrapper = mapping;
        }
        return createCommand;
    }

    private DeleteCommandImpl getDeleteCommand(DataObject changedObject) {

        if (deleteCommand == null) {
            Table table = mapping.getTableByTypeName(changedObject.getType().getName());
            if (table == null) {
                if (changedObject.getType().getProperty("ID") != null) {
                    // If the table is not defined in the config, assume it has a primary key of "ID"
                    mapping.addPrimaryKey(changedObject.getType().getName() + ".ID");
                    table = mapping.getTableByTypeName(changedObject.getType().getName());
                } else {
                    throw new RuntimeException("Table " + changedObject.getType().getName()
                            + " was changed in the DataGraph but is not present in the Config");
                }
            }

            Delete delete = table.getDelete();

            if (delete == null) {
                deleteCommand = DeleteGenerator.INSTANCE.getDeleteCommand(mapping, table);//JIRA-952
            } else {
                deleteCommand = new DeleteCommandImpl(delete);
            }
            deleteCommand.setConnection(connection);
            deleteCommand.configWrapper = mapping;
        }
        return deleteCommand;
    }

    private UpdateCommandImpl getUpdateCommand(DataObject changedObject) {

        Table table = mapping.getTableByTypeName(changedObject.getType().getName());
        if (table == null) {
            if (changedObject.getType().getProperty("ID") != null) {
                mapping.addPrimaryKey(changedObject.getType().getName() + ".ID");
                table = mapping.getTableByTypeName(changedObject.getType().getName());
            } else {
                throw new RuntimeException("Table " + changedObject.getType().getName()
                        + " was changed in the DataGraph but is not present in the Config");
            }
        }
        Update update = table.getUpdate();
        if (update == null) {
            updateCommand = UpdateGenerator.INSTANCE.getUpdateCommand(mapping, changedObject, table);
        } else {
            TableWrapper t = new TableWrapper(table);
            if (t.getCollisionColumn() != null) {
                updateCommand = new OptimisticWriteCommandImpl(update);
            } else {
                updateCommand = new UpdateCommandImpl(update);
            }
        }
        updateCommand.setConnection(connection);
        updateCommand.configWrapper = mapping;

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Returning updateCommand: " + updateCommand);
        }

        return updateCommand;
    }

    public MappingWrapper getConfig() {
        return this.mapping;
    }

}
