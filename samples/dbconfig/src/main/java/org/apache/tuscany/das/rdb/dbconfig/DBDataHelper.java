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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DBDataHelper {
    private static final String CLASS_NAME = "DBDataHelper";

    private final Logger logger = Logger.getLogger(DBDataHelper.class);

    private final DBConfig dbConfig;

    protected DBDataHelper(DBConfig dbConfig) {
        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, "DBDataHelper()");
        }
        
        this.dbConfig = dbConfig;
    }

    public boolean isDatabasePopulated() {
        boolean isPopulated = true;

        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + ".isDatabasePopulated()");
        }

        Iterator tableIterator = dbConfig.getTable().iterator();
        while (tableIterator.hasNext()) {
            Table table = (Table) tableIterator.next();

            if (logger.isDebugEnabled()) {
                this.logger.log(Level.DEBUG, CLASS_NAME + ".isDatabasePopulated() calling isTablePopulated() for '" + table.getName() + "'");
            }
            
            isPopulated = this.isTablePopulated(table.getName());
            if (isPopulated == false) {
                break;
            }
        }
        return isPopulated;
    }

    /**
     * 
     * @param tableName
     * @return Count of rows present in the table specified by tableName
     */
    protected boolean isTablePopulated(String tableName) {
        boolean isPopulated = false;
        Connection dbConnection = null;
        Statement dbStatement = null;

        try {
            dbConnection = DBConnectionHelper.createConnection(dbConfig.getConnectionInfo());
            dbStatement = dbConnection.createStatement();
            String sqlString = "select count(*) from " + tableName;

            this.logger.log(Level.DEBUG, CLASS_NAME + ".isTablePopulated()=> sqlString => '" + sqlString + "'");

            ResultSet rs = dbStatement.executeQuery(sqlString);
            rs.next();

            if (rs != null) {
                if (logger.isDebugEnabled()) {
                    this.logger.log(Level.DEBUG, CLASS_NAME + ".isTablePopulated()=> pointer set");
                }
            }

            int count = rs.getInt(1);
            
            if (logger.isDebugEnabled()) {
                this.logger.log(Level.DEBUG, CLASS_NAME + ".isTablePopulated()=> '" + tableName + "' => " + count);
            }

            if (count > 0) {
                isPopulated = true;
            }

        } catch (SQLException e) {
            // ignore and return false
        } finally {
            try {
                dbStatement.close();
                dbConnection.close();
            } catch (SQLException e1) {
                // ignore and return false
            }
        }

        return isPopulated;
    }

    protected String generateInsertSQL(Connection dbConnection, String tableName, String rowValues) throws SQLException {
        StringBuffer sqlBuffer = new StringBuffer(50); 
        
        Statement dbStatement = dbConnection.createStatement();
        ResultSet dummyRS = dbStatement.executeQuery("SELECT * FROM "+tableName);
        ResultSetMetaData rsMetaData = dummyRS.getMetaData(); 
        	
        
        sqlBuffer.append("INSERT INTO ").append(tableName).append(" (");

        int numberOfColumns = rsMetaData.getColumnCount();
        String columnName = null;
        int i;
        for (i = 1; i <= numberOfColumns -1; i++) {
            //get the column's name.
        	columnName = rsMetaData.getColumnName(i);
          if(!rsMetaData.isAutoIncrement(i)){
              sqlBuffer.append(columnName).append(",");
          	}
          	else{
                if (logger.isDebugEnabled()) {
                    this.logger.log(Level.DEBUG, CLASS_NAME + ".initializeDatabaseData()=> auto increment column => " + i + " "+columnName);
                }
          	}
        }
        sqlBuffer.append(rsMetaData.getColumnName(i)).append(") ");
        sqlBuffer.append("VALUES (").append(rowValues).append(")");

        if (logger.isDebugEnabled()) {
            this.logger.log(Level.DEBUG, CLASS_NAME + ".initializeDatabaseData()=> SQL Clause => " + sqlBuffer);
        }
        
        return sqlBuffer.toString();
    }
    
    public void initializeDatabaseData() {
        Connection dbConnection = null;
        Statement dbStatement = null;

        try {
            dbConnection = DBConnectionHelper.createConnection(dbConfig.getConnectionInfo());
            dbStatement = dbConnection.createStatement();

            Iterator tableIterator = dbConfig.getTable().iterator();
            while (tableIterator.hasNext()) {
                Table table = (Table) tableIterator.next();
                
                if (logger.isDebugEnabled()) {
                    this.logger.log(Level.DEBUG, CLASS_NAME + ".initializeDatabaseData()=> INSERT FOR TABLE => '" + table.getName() + "'");
                }
         
                //String columnClause = generateInsertSQL(dbConnection, table.getName());
                                
                String tableName = table.getName();
                Iterator dataIterator = table.getRow().iterator();
                while (dataIterator.hasNext()) {
                    String tableRow = (String) dataIterator.next();                    
                    String sqlString = generateInsertSQL(dbConnection, tableName, tableRow);

                    if (logger.isDebugEnabled()) {
                        this.logger.log(Level.DEBUG, CLASS_NAME + ".initializeDatabaseData()=> sqlString => '" + sqlString + "'");
                    }

                    try {
                        dbStatement.executeUpdate(sqlString);
                    }catch(SQLException e){
                    	//e.printStackTrace();
                        //ignore and jump to new table
                        if (logger.isDebugEnabled()) {
                            this.logger.log(Level.DEBUG, CLASS_NAME + ".initializeDatabaseData() - Error inserting table data : " + e.getMessage(), e);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // ignore and return false
            if (logger.isDebugEnabled()) {
                this.logger.log(Level.DEBUG, CLASS_NAME + ".initializeDatabaseData() - Internal error : " +  e.getMessage(), e);
            }
        } finally {
            try {
                dbStatement.close();
                dbConnection.close();
            } catch (SQLException e1) {
                // ignore and return false
            }
        }
    }

    public void deleteDatabaseData() {
        Connection dbConnection = null;
        Statement dbStatement = null;

        try {
            dbConnection = DBConnectionHelper.createConnection(dbConfig.getConnectionInfo());
            dbStatement = dbConnection.createStatement();
            //inverse order - to take care of parent-child
            List tables = dbConfig.getTable();
            for(int i=tables.size()-1; i>-1; i-- ){
                Table table = (Table) tables.get(i);
                String sqlString = "DELETE FROM " + table.getName() ;
               
                if (logger.isDebugEnabled()) {
                    this.logger.log(Level.DEBUG, CLASS_NAME + ".deleteDatabaseData()=> sqlString => '" + sqlString + "'");
                }

                try {
                    dbStatement.executeQuery(sqlString);
                }catch(SQLException e){
                	e.printStackTrace();
                    //ignore and jump to new table
                    if (logger.isDebugEnabled()) {
                        this.logger.log(Level.DEBUG, CLASS_NAME + ".deleteDatabaseData() - Error inserting table data : " + e.getMessage(), e);
                    }
                }            	            
            }
        } catch (SQLException e) {
            // ignore and return false
            if (logger.isDebugEnabled()) {
                this.logger.log(Level.DEBUG, CLASS_NAME + ".initializeDatabaseData() - Internal error : " +  e.getMessage(), e);
            }
        } finally {
            try {
                dbStatement.close();
                dbConnection.close();
            } catch (SQLException e1) {
                // ignore and return false
            }
        }
    }
}
