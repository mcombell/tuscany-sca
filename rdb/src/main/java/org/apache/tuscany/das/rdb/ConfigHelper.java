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

import java.util.Vector;

import org.apache.tuscany.das.rdb.config.Column;
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

    /**
     * Default constructor
     */
    public ConfigHelper() {
        config = factory.createConfig();
        configWrapper = new MappingWrapper(config);
    }

    /**
     * Constructor that receives a Config object
     * @param config The configuration object
     */
    public ConfigHelper(Config config) {
        this.config = config;
        configWrapper = new MappingWrapper(config);
    }

    /**
     * Add PK information
     * @param columnName The column to be taken as PK
     */
    public void addPrimaryKey(String columnName) {
        configWrapper.addPrimaryKey(columnName);
    }

    /**
     * Add relationship information
     * @param parentName
     * @param childName
     * @return
     */
    public Relationship addRelationship(String parentName, String childName) {
        return configWrapper.addRelationship(parentName, childName);
    }
    
    /**
     * Add relationship information
     * @param parentNames
     * @param childNames
     * @return
     */
    public Relationship addRelationship(Vector parentNames, Vector childNames) {
        return configWrapper.addRelationship(parentNames, childNames);
    }

    /**
     * Add table information
     * @param name
     * @param typeName
     * @return
     */
    public Table addTable(String name, String typeName) {
        return configWrapper.addTable(name, typeName);
    }
    
    /**
     * Add table information with schema information
     * @param name
     * @param schemaName
     * @param typeName
     * @return
     */
    public Table addTable(String name, String schemaName, String typeName) {
        return configWrapper.addTable(name, schemaName, typeName);
    }
    
    /**
     * Add column information
     * @param table
     * @param columnName
     * @param propertyName
     * @return
     */
    public Column addColumn(Table table, String columnName, String propertyName) {
        return configWrapper.addColumn(table, columnName, propertyName);
    }

    /**
     * Add an update statement for a given table
     * @param table
     * @param statement
     * @param parameters
     */
    public void addUpdateStatement(Table table, String statement, String parameters) {
        configWrapper.addUpdateStatement(table, statement, parameters);
    }

    /**
     * Add create statement for a given table
     * @param table
     * @param statement
     * @param parameters
     */
    public void addCreateStatement(Table table, String statement, String parameters) {
        configWrapper.addCreateStatement(table, statement, parameters);
    }

    /**
     * Add delete statement for a given table
     * @param table
     * @param statement
     * @param parameters
     */
    public void addDeleteStatement(Table table, String statement, String parameters) {
        configWrapper.addDeleteStatement(table, statement, parameters);
    }

    /**
     * Add datasource connection information
     * @param dataSourceName
     */
    public void addConnectionInfo(String dataSourceName) {
        configWrapper.addConnectionInfo(dataSourceName, true);
    }

    /**
     * Add datasource connection information and flag about using managed transactions
     * @param dataSourceName
     * @param managedtx
     */
    public void addConnectionInfo(String dataSourceName, boolean managedtx) {
        configWrapper.addConnectionInfo(dataSourceName, managedtx);
    }

    /**
     * Add driver manager connection information
     * @param driverClass
     * @param databaseURL
     * @param user
     * @param password
     * @param loginTimeout
     */
    public void addConnectionInfo(String driverClass, String databaseURL, String user, String password, int loginTimeout) {
        configWrapper.addConnectionInfo(driverClass, databaseURL, user, password, loginTimeout);
    }

    /**
     * Set the data object model
     * @param dataObjectModel
     */
    public void setDataObjectModel(String dataObjectModel) {
        configWrapper.getConfig().setDataObjectModel(dataObjectModel);
    }

    /**
     * Add a select command
     * @param name
     * @param sql
     * @return
     */
    public Command addSelectCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "select");
    }

    /**
     * Add a update command
     * @param name
     * @param sql
     * @return
     */
    public Command addUpdateCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "update");
    }

    /**
     * Add a insert command
     * @param name
     * @param sql
     * @return
     */
    public Command addInsertCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "insert");
    }

    /**
     * Add a delete command
     * @param name
     * @param sql
     * @return
     */
    public Command addDeleteCommand(String name, String sql) {
        return configWrapper.addCommand(name, sql, "delete");
    }

    /**
     * Get a reference to the config object
     * @return
     */
    public Config getConfig() {
        return config;
    }

}
