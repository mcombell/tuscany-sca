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

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.DataObject;
import commonj.sdo.Type;

/**
 * 
 */
public class ApplyChangesCommandImpl extends BaseCommandImpl  {

    private static final boolean debug = false;

    private ChangeSummarizer summarizer = new ChangeSummarizer();
    
    public ApplyChangesCommandImpl(Config config, Connection connection){
        this.configWrapper = new MappingWrapper(config); 
        if ( connection != null )
     	   setConnection(connection, config);
        
    }
    
	public void setConnection(ConnectionImpl connection) {
		summarizer.setConnection(connection);
	}


    public void execute(DataObject root) {
        DebugUtil.debugln(getClass(), debug, "Executing ApplyChangesCmd");

        if (summarizer.getConnection() == null)
            throw new RuntimeException("A connection must be provided");

        if (!root.equals(root.getDataGraph().getRootObject()))
            throw new RuntimeException("'root' argument must be the root of the datagraph");
        
        summarizer.setMapping(configWrapper);
        
        Changes changes = summarizer.loadChanges(root);

        boolean success = false;
        try {
            changes.execute();
            success = true;
        } finally {
            if (success)
                summarizer.getConnection().cleanUp();
            else
                summarizer.getConnection().errorCleanUp();
        }
    }

}
