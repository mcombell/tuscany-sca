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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DBHelper {
    private static final String CLASS_NAME = "DBHelper";

    private final Logger logger = Logger.getLogger(DBHelper.class);

    private final DBConfig dbConfig;

    /**
     * Constructor
     * 
     * @param DBConfig
     */
    protected DBHelper(DBConfig dbConfig) {
        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + "()");
        }
        this.dbConfig = dbConfig;
    }

    /**
     * Check if tables specified in Config exist
     * 
     * @return true if all specified tables exist, false otherwise
     */
    protected boolean isDatabaseReady() {
        boolean bResult = true;

        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + "isDatabaseReady()");
        }

        Connection dbConnection = null;
        DatabaseMetaData dbMetaData = null;
        try {
            dbConnection = DBConnectionHelper.createConnection(dbConfig.getConnectionInfo());
            dbMetaData = dbConnection.getMetaData();

            if (dbConfig.getTable() != null && dbConfig.getTable().size() > 0) {
                Iterator tableIterator = dbConfig.getTable().iterator();
                while (tableIterator.hasNext()) {
                    Table table = (Table) tableIterator.next();

                    if (!dbMetaData.getTables(null, null, table.getName(), null).next()) {
                        bResult = false;
                    }

                }
            }
        } catch (SQLException e) {
            if (logger.isDebugEnabled()) {
                this.logger.log(Level.DEBUG, "Error retrieving database metadata", e);
            }
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                // ignore here
            }
        }

        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + "isDatabaseReady() exit");
        }
        
        return bResult;
    }

    /**
     * Create the database tables based on dbConfig definition
     */
    protected void initializeDatabase() {
        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + "initializeDatabase()");
        }

        Connection dbConnection = null;
        Statement dbStatement = null;
        try {
            dbConnection = DBConnectionHelper.createConnection(dbConfig.getConnectionInfo());
            dbStatement = dbConnection.createStatement();

            if (dbConfig.getTable() != null && dbConfig.getTable().size() > 0) {
                Iterator tableIterator = dbConfig.getTable().iterator();
                while (tableIterator.hasNext()) {
                    Table table = (Table) tableIterator.next();

                    if (table.getSQLCreate() != null && table.getSQLCreate().length() > 0) {
                        if (logger.isDebugEnabled()) {
                            this.logger.log(Level.DEBUG, "Creating table '" + table.getName() + "' => " + table.getSQLCreate());
                        }

                        dbStatement.execute(table.getSQLCreate());
                    }
                }
            }
        } catch (SQLException e) {
            if (logger.isDebugEnabled()) {
                this.logger.log(Level.DEBUG, "Error retrieving database metadata", e);
            }
        } finally {
            try {
                dbStatement.close();
                dbConnection.close();
            } catch (SQLException e) {
                // ignore here
            }
        }

        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + "initializeDatabase() exit");
        }
    }

    /**
     * Drop the database tables based on dbConfig definition
     */
    protected void dropDatabaseTables() {
        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + "dropDatabaseTables()");
        }

        Connection dbConnection = null;
        Statement dbStatement = null;
        try {
            dbConnection = DBConnectionHelper.createConnection(dbConfig.getConnectionInfo());
            dbStatement = dbConnection.createStatement();

            if (dbConfig.getTable() != null && dbConfig.getTable().size() > 0) {
                Iterator tableIterator = dbConfig.getTable().iterator();
                while (tableIterator.hasNext()) {
                    Table table = (Table) tableIterator.next();

                    if (table.getSQLCreate() != null && table.getSQLCreate().length() > 0) {
                        if (logger.isDebugEnabled()) {
                            this.logger.log(Level.DEBUG, "Dropping table '" + table.getName() );
                        }

                        try {
                            dbStatement.execute("DROP TABLE " + table.getName());
                        } catch (SQLException e) {
                        	//ignore
                            if (logger.isDebugEnabled()) {
                                this.logger.log(Level.DEBUG, "Error droping table '" + table.getName() + "'", e);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            if (logger.isDebugEnabled()) {
                this.logger.log(Level.DEBUG, "Error droping table", e);
            }
        } finally {
            try {
                dbStatement.close();
                dbConnection.close();
            } catch (SQLException e) {
                // ignore here
            }
        }

        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + "dropDatabaseTables() exit");
        }
    }
}
