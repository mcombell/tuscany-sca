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

import java.sql.Connection;

import org.apache.tuscany.das.rdb.Key;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

public abstract class BaseCommandImpl {

    protected MappingWrapper configWrapper = new MappingWrapper();

    public void setConnection(Connection connection) {
        setConnection(new ConnectionImpl(connection));
    }

    public void setConnection(Connection connection, boolean manageTransaction) {
        ConnectionImpl c = new ConnectionImpl(connection);
        c.setManageTransactions(manageTransaction);
        setConnection(c);
    }

    public abstract void setConnection(ConnectionImpl c);

    public void addRelationship(String parentName, String childName) {
        configWrapper.addRelationship(parentName, childName);
    }

    public void addRelationship(Key parentKey, Key childKey) {
        configWrapper.addRelationship(parentKey, childKey);
    }

    public void addPrimaryKey(String pk) {
        configWrapper.addPrimaryKey(pk);
    }

    public void addPrimaryKey(Key pk) {
        configWrapper.addPrimaryKey(pk);
    }

    public void addConverter(String name, String converter) {
        configWrapper.addConverter(name, converter);
    }

    public void addCollisionColumn(String columnName) {
        configWrapper.addCollisionColumn(columnName);
    }

    public void addGeneratedPrimaryKey(String columnName) {
        configWrapper.addGeneratedPrimaryKey(columnName);
    }

}
