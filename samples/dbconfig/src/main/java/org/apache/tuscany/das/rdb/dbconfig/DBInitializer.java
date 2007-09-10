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

package org.apache.tuscany.das.rdb.dbconfig;

import java.io.InputStream;
import java.sql.Connection;

/**
 * This is the master class having public utility APIs exposed for DAS sample creater - for table and data maintainance.
 * 
 */
public class DBInitializer {
    private static final String DEFAULT_CANNED_DB_CONFIGURATION = "CannedSampleDBConfig.xml";

    /**
     * Database configuration model
     */
    protected DBConfig dbConfig;

    /**
     * Database connection
     */
    protected Connection connection;

    /**
     * To manage database connection
     */
    protected DBConnectionHelper dbConnectionHelper;

    /**
     * To manage database phisical structure
     */
    protected DBHelper dbHelper;

    /**
     * To manage database population
     */
    protected DBDataHelper dbDataHelper;

    /**
     * Create instance of DBInitHelper based on default canned database configuration
     * 
     * @param dbconfigFileLocation
     * @throws Exception
     */
    public DBInitializer() {
        InputStream dbConfigStream = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_CANNED_DB_CONFIGURATION);
        init(dbConfigStream);
    }

    /**
     * Create instance of DBInitHelper based on Config file location
     * 
     * @param dbconfigFileLocation
     * @throws Exception
     */
    public DBInitializer(String dbconfigFileLocation) throws Exception {
        InputStream dbConfigStream = this.getClass().getClassLoader().getResourceAsStream(dbconfigFileLocation);
        init(dbConfigStream);
    }

    /**
     * Create instance of DBInitHelper based on Config stream
     * 
     * @param dbconfigStream
     * @throws Exception
     */
    public DBInitializer(InputStream dbConfigStream) throws Exception {
        init(dbConfigStream);
    }

    /**
     * Initialize helper members based on Config
     * 
     * @param dbconfigStream
     */
    protected void init(InputStream dbconfigStream) {
        dbConfig = DBConfigUtil.loadDBConfig(dbconfigStream);

        dbConnectionHelper = new DBConnectionHelper();
        dbHelper = new DBHelper(dbConfig);
        dbDataHelper = new DBDataHelper(dbConfig);

    }

    /**
     * Check if the Database and all tables have been created on the database
     * 
     * @return return true if tables exist, else return false.
     */
    public boolean isDatabaseReady() {
        return dbHelper.isDatabaseReady();
    }

    /**
     * 
     * @return - return true if all tables have at least a row, false otherwise
     */
    public boolean isDatabasePopulated() {
        return dbDataHelper.isDatabasePopulated();
    }

    /**
     * Create tables and populate data.
     * 
     * @param clean - If true, tables will be force dropped and recreated, else it will skip table creation for pre-existing tables.
     * @throws Exception
     */
    public void initializeDatabase(boolean clean) throws DatabaseInitializerException {
        if (clean) {
            dbHelper.dropDatabaseTables();
        }
        dbHelper.initializeDatabase();
        dbDataHelper.initializeDatabaseData();
    }

    /**
     * Populate database data
     * 
     * @param clean If true, table data will be droped before data is created
     */
    public void initializeDatabaseData(boolean clean) throws DatabaseInitializerException{
        if (clean) {
        	dbDataHelper.deleteDatabaseData();
        }
        dbDataHelper.initializeDatabaseData();
    }

    /**
     * Refresh data in tables.
     * 
     * @throws Exception
     */
    public void refreshDatabaseData() throws DatabaseInitializerException {
        initializeDatabase(true);
    }
}
