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

import java.sql.Connection;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

public abstract class BaseCommandImpl {
    protected MappingWrapper configWrapper = new MappingWrapper();

    //when no config in method params, useGetGeneratedKeys will default to True
    //as DBMS metadata method is not reliable in all cases
    public void setConnection(Connection connection) {
        setConnection(new ConnectionImpl(connection));
    }

    public void setConnection(Connection connection, Config config) {
        boolean managed = true;
        String generatedKeysSupported = null;
        
        if (config != null){
        	if(config.getConnectionInfo() != null) {
        		managed = config.getConnectionInfo().isManagedtx();
        	}
        
            generatedKeysSupported = config.getGeneratedKeysSupported();
        	
        	setConnection(connection, managed, generatedKeysSupported);
        }
        else{
        	setConnection(connection);
        }
    }

    public void setConnection(Connection connection, boolean manageTransaction, String generatedKeysSupported) {
        ConnectionImpl c = new ConnectionImpl(connection);
        c.setManageTransactions(manageTransaction);
        c.setGeneratedKeysSupported(generatedKeysSupported);
        setConnection(c);
    }
    
    public abstract void setConnection(ConnectionImpl c);

}
