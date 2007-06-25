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

import java.util.Iterator;

import org.apache.log4j.Logger;

import commonj.sdo.DataObject;

/**
 */
public abstract class ChangeOperation {
    protected DatabaseObject dObject;

    protected String propagatedID;
    
    private final Logger logger = Logger.getLogger(ChangeOperation.class);

    private final WriteCommandImpl writeCommand;

    private boolean isInsert;
    

    public ChangeOperation(DeleteCommandImpl command) {
        writeCommand = command;
    }

    public ChangeOperation(InsertCommandImpl command, DataObject changedObject) {
        writeCommand = command;
        dObject = new DatabaseObject(command.getMappingModel(), changedObject);
        this.isInsert = true;
    }

    public ChangeOperation(UpdateCommandImpl command, DataObject changedObject) {
        writeCommand = command;
        dObject = new DatabaseObject(command.getMappingModel(), changedObject);
    }

    public void execute() {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Executing change operation");
        }

        Iterator i = writeCommand.getParameters().iterator();
        while (i.hasNext()) {
            ParameterImpl parm = (ParameterImpl) i.next();

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("setting " + parm.getName() + " to " + dObject.get(parm.getName()));
            }

            parm.setValue(dObject.get(parm.getName()));
        }

        writeCommand.basicExecute();

        if (isInsert && (propagatedID != null)) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Propagating key " + propagatedID);
            }
            int id = writeCommand.getGeneratedKey();
            dObject.setPropagatedID(propagatedID, id);
        }
    }

    public String getTableName() {
        return dObject.getTableName();
    }

}
