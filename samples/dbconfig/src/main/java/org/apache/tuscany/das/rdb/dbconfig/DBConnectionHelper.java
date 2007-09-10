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
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DBConnectionHelper {
	private static final Logger logger = Logger.getLogger(DBConnectionHelper.class);
	
	protected DBConnectionHelper(){
        if(logger.isDebugEnabled()) {
            logger.log(Level.DEBUG, "DBConnectionHelper()");
        }
	}
    
    /**
     * Basic validation of Config for connection information and call to 
     * connection helper to establish database connection. Connection using 
     * DriverManager or DataSource are supported.
     *
     */
    public static Connection createConnection(ConnectionInfo connectionInfo) {
        if (logger.isDebugEnabled()) {
            logger.log(Level.DEBUG, "DBConnectionHelper.createConnection(ConnectionInfo)");
        }
        
        if (connectionInfo == null || 
            (connectionInfo.getDataSource() == null && connectionInfo.getConnectionProperties() == null)) {
            throw new RuntimeException("No connection has been provided and no data source has been specified");
        }

        if(connectionInfo.getDataSource() != null && connectionInfo.getConnectionProperties() != null){
            throw new RuntimeException("Use either dataSource or ConnectionProperties. Can't use both !");
        }
        
        Connection connection = null;
        
        if(connectionInfo.getConnectionProperties() != null){
            connection = initializeDriverManagerConnection(connectionInfo);
        }else{
            connection = initializeDatasourceConnection(connectionInfo);
        }
        
        return connection;
        
    }
    /**
     * Initializes a DB connection on a managed environmet (e.g inside Tomcat)
     */
	private static Connection initializeDatasourceConnection(ConnectionInfo connectionInfo){
        if (logger.isDebugEnabled()) {
            logger.log(Level.DEBUG, "DBConnectionHelper.initializeDatasourceConnection(ConnectionInfo)");
        }
        
        InitialContext ctx;
        Connection connection;
        
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        try {
            DataSource ds = (DataSource) ctx.lookup(connectionInfo.getDataSource());
            try {
                connection = ds.getConnection();
                if (connection == null) {
                    throw new RuntimeException("Could not obtain a Connection from DataSource");
                }
                connection.setAutoCommit(true);
                
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        if( logger.isDebugEnabled()) {
            logger.log(Level.DEBUG, "DBConnectionHelper.initializeDatasourceConnection() exit");
        }
        return connection;
    }
    
    /**
     * Initialize a DB connection on a J2SE environment
     * For more info, see http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/drivermanager.html
     */
	private static Connection initializeDriverManagerConnection(ConnectionInfo connectionInfo) {
		if (logger.isDebugEnabled()) {
		    logger.log(Level.DEBUG, "DBConnectionHelper.initializeDriverManagerConnection(ConnectionInfo)");
        }
    	
        if (connectionInfo.getConnectionProperties() == null) {
            throw new DataSourceInitializationException("No existing context and no connection properties");
        }

        if (connectionInfo.getConnectionProperties().getDriverClass() == null) {
            throw new DataSourceInitializationException("No jdbc driver class specified!");
        }

        Connection connection;
        
        try {
            //initialize driver and register it with DriverManager
            Class.forName(connectionInfo.getConnectionProperties().getDriverClass());
            
            //prepare to initialize connection
            String databaseUrl = connectionInfo.getConnectionProperties().getDatabaseURL();
            String userName = connectionInfo.getConnectionProperties().getUserName();
            String userPassword = connectionInfo.getConnectionProperties().getPassword();
            int loginTimeout = connectionInfo.getConnectionProperties().getLoginTimeout();
            
            DriverManager.setLoginTimeout(loginTimeout);
            if( (userName == null || userName.length() ==0) && (userPassword == null || userPassword.length()==0) ){
                //no username or password suplied
                connection = DriverManager.getConnection(databaseUrl);
            }else{
                connection = DriverManager.getConnection(databaseUrl, userName, userPassword);
            }
            
            if(connection == null){
                throw new DataSourceInitializationException("Error initializing connection : null");
            }
            
            connection.setAutoCommit(true);
        }catch(ClassNotFoundException cnf){
            throw new DataSourceInitializationException("JDBC Driver '" + connectionInfo.getConnectionProperties().getDriverClass() + "' not found", cnf);
        }catch(SQLException sqle){
            throw new DataSourceInitializationException(sqle.getMessage(), sqle);
        }
        
        if (logger.isDebugEnabled()) {
            logger.log(Level.DEBUG, "DBConnectionHelper.initializeDriverManagerConnection() exit");
        }
        
        return connection;
    }
}
