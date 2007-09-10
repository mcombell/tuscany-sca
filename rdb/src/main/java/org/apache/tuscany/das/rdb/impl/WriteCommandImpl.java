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

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ParameterImpl;
import org.apache.tuscany.das.rdb.config.Parameters;

import commonj.sdo.DataObject;

public abstract class WriteCommandImpl extends CommandImpl {

	public WriteCommandImpl(org.apache.tuscany.das.rdb.config.Command command) {
		super(command);
	}
	
    public WriteCommandImpl(String sqlString) {
        super(sqlString);
    }

    public void setResultDescriptors(List resultDescriptorList){
    	//ignore , applicable for ReadCommand only
    }
    
    public List getResultDescriptors(){
    	//ignore, applicable for ReadCommand only
    	return null;
    }
    
    public void addResultDescriptor(ResultDescriptor resultDescriptor){
    	//ignore
    }
    

    public ResultDescriptor removeResultDescriptor(int index){
    	return null;
    }
    
    public ResultDescriptor removeResultDescriptor(ResultDescriptor resultDescriptor){
    	return null;
    }
    
    public ResultDescriptor getResultDescriptor(int index){
    	return null;
    }
    
    public void printResultDescriptors(OutputStream ostrm) throws IOException{
    //ignore	
    }
    
    public void execute() {

        boolean success = false;
        try {
            basicExecute();
            success = true;
        } finally {
            if (success) {
                statement.getConnection().cleanUp();
            } else {
                statement.getConnection().errorCleanUp();
            }
        }

    }

    public void basicExecute() {
        try {
            statement.executeUpdate(parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DataObject executeQuery() {
        throw new UnsupportedOperationException();
    }

    public Config getMappingModel() {
        return configWrapper.getConfig();
    }

    public String toString() {

        StringBuffer buffer = new StringBuffer();
        buffer.append("\nSQL: " + statement.queryString);

        return buffer.toString();
    }

    public int getGeneratedKey() {
        throw new RuntimeException("No generated key is available");
    }

    //it is acceptable if params having index set by caller are listed in any order in Parameters
    //but, if index is not set by caller, the order of parameters in Parameters should be in sync
    //with the Command string parameters
    protected void addParameters(Parameters parameters) {
    	List params = parameters.getParameter();
    	if(params == null) 
    		return;

    	boolean paramsIndexed = true;
    	TreeMap sortedParams = null;
    	for(int i=0; i<params.size(); i++){
    		if(((ParameterImpl)params.get(i)).getIndex() <= 0){
    			paramsIndexed=false; //any index not set, ignore all externally set indexes and do auto indexing
    			break;
    		}    		
    	}
    
    	//auto-indexing
    	if(!paramsIndexed) {
    		for(int i=0; i<params.size(); i++){
    			ParameterExtendedImpl param = new ParameterExtendedImpl((ParameterImpl)params.get(i));
    			param.setIndex(i+1);
    			this.addParameter(param);
    		}
    		return;
    	}
    	else {//dont allow duplicates and check indexing with +1 increment from 1st to last param
    		sortedParams = new TreeMap();
    		for(int i=0; i<params.size(); i++){    			
    			ParameterImpl existingParam = (ParameterImpl)sortedParams.put( new Integer(((ParameterImpl)params.get(i)).getIndex()), ((ParameterImpl)params.get(i)));
    			if(existingParam != null) {
    				throw new RuntimeException("Parameters with duplicate indexes!");
    			}
    		}

    		if( ((Integer)sortedParams.firstKey()).intValue() + sortedParams.size() -1 != 
    			((Integer)sortedParams.lastKey()).intValue()) {
    			throw new RuntimeException("Parameters with improper indexes!");
    		}
    	}
    	    	
    	for(int i=0; i<params.size(); i++) {
    		ParameterExtendedImpl param = new ParameterExtendedImpl((ParameterImpl)params.get(i));    		
    		param.setIndex(((ParameterImpl)params.get(i)).getIndex());
    		this.addParameter(param);
    	}
    }    
}
