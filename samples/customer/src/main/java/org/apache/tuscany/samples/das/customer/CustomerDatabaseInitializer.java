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
package org.apache.tuscany.samples.das.customer;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.util.ConfigUtil;
import org.apache.tuscany.samples.das.databaseSetup.DerbySetup;
import org.apache.tuscany.samples.das.databaseSetup.MySQLSetup;

public class CustomerDatabaseInitializer {
    public static final String DERBY = "derby";
    public static final String MYSQL = "mysql";

    private final Config config;
    
    private final String dbType;
    private final String dbURL;
    private final String user;
    private final String password;
    
    
    
    public CustomerDatabaseInitializer(String configFile) {
        this.config = ConfigUtil.loadConfig(this.getClass().getClassLoader().getResourceAsStream(configFile));

        if(config.getConnectionInfo().getConnectionProperties().getDriverClass().indexOf(DERBY) != -1) {
            dbType = DERBY;
        } else if(config.getConnectionInfo().getConnectionProperties().getDriverClass().indexOf(MYSQL) != -1) {
            dbType = MYSQL;
        } else {
            dbType = null;
        }
            
        //get connection info from config
        dbURL = config.getConnectionInfo().getConnectionProperties().getDatabaseURL();
        user = config.getConnectionInfo().getConnectionProperties().getUserName();
        password = config.getConnectionInfo().getConnectionProperties().getPassword();
    }
    
    

    /**
     * If database is present connect to it and create necessary tables, procedures, data etc.
     * If database is not present create database and then create the above elements. Create database
     * is implemented for MySQL and Derby.
     */
    public void Initialize() {
        
        //display DB configuration iformation
        DisplayDatabaseConfiguration();
        
        //initialize DB
        try {
            if(dbType.equals(DERBY)){
                new DerbySetup(dbURL+"-"+user+"-"+password);
            }
                        
            if(dbType.equals(MYSQL)){
                new MySQLSetup(dbURL+"-"+user+"-"+password);
            }
            
        } catch(Exception e){
            throw new RuntimeException("Error initializing database !", e);
        }
    }
    
    public void DisplayDatabaseConfiguration() {
        
        System.out.println("************* Initializing database *************");
        System.out.println("** DB type  : " + dbType );
        System.out.println("** Database : " + dbURL );
        System.out.println("** User     : " + user );
        System.out.println("** Password : " + password);
        System.out.println("************************************************");
    }
}
