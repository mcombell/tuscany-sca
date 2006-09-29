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
package org.apache.tuscany.das.rdb;

import org.apache.tuscany.das.rdb.config.Command;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

/**
 * A ConfigHelper is used as an aid in programmatic construction of Config instances.
 * Manual contrution fo COnfig is an alternative to providing needed configuration
 * information in an XML file
 * 
 */
public class ConfigHelper {

    private Config config;

    private MappingWrapper configWrapper;

    private ConfigFactory factory = ConfigFactory.INSTANCE;

    public ConfigHelper() {
        config = factory.createConfig();
        configWrapper = new MappingWrapper(config);
    }

    public ConfigHelper(Config config) {
        this.config = config;
        configWrapper = new MappingWrapper(config);
    }

    public void addPrimaryKey(String columnName) {
        configWrapper.addPrimaryKey(columnName);
    }

    public Relationship addRelationship(String parentName, String childName) {
        return configWrapper.addRelationship(parentName, childName);
    }

    public Table addTable(String name, String propertyName) {
        return configWrapper.addTable(name, propertyName);
    }

    public void addUpdateStatement(Table table, String statement, String parameters) {
        configWrapper.addUpdateStatement(table, statement, parameters);
    }

    public void addCreateStatement(Table table, String statement, String parameters) {
        configWrapper.addCreateStatement(table, statement, parameters);
    }

    public void addDeleteStatement(Table table, String statement, String parameters) {
        configWrapper.addDeleteStatement(table, statement, parameters);
    }

    public void addConnectionInfo(String dataSourceName, boolean managedtx) {
        configWrapper.addConnectionInfo(dataSourceName, managedtx);
    }

    public void addConnectionInfo(String dataSourceName) {
        configWrapper.addConnectionInfo(dataSourceName, true);
    }

    public void setDataObjectModel(String dataObjectModel) {
        configWrapper.getConfig().setDataObjectModel(dataObjectModel);
    }

    public Command addSelectCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "select");
    }

    public Command addUpdateCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "update");
    }

    public Command addInsertCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "insert");
    }

    public Command addDeleteCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "delete");
    }

    public Config getConfig() {
        return config;
    }

}
