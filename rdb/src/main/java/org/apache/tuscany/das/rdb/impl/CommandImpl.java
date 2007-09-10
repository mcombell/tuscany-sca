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
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XSDHelper;

public abstract class CommandImpl extends BaseCommandImpl implements Command {
    
    protected Statement statement;

    protected ParametersExtendedImpl parameters = new ParametersExtendedImpl();

    protected ResultSetShape resultSetShape;
    
    public CommandImpl(String sqlString) {
        statement = new Statement(sqlString);

        try {
            URL url = getClass().getResource("/xml/sdoJava.xsd");
            if (url == null) {
                throw new RuntimeException("Could not find resource: xml/sdoJava.xsd");
            }

            InputStream inputStream = url.openStream();
            XSDHelper.INSTANCE.define(inputStream, url.toString());
            inputStream.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public CommandImpl(org.apache.tuscany.das.rdb.config.Command command) {
        this(command.getSQL());
        
        if(command.getParameter() != null && command.getParameter().size() > 0) {
    		Iterator itr = command.getParameter().iterator();
    		int index = 1;
    		while(itr.hasNext()){
    			org.apache.tuscany.das.rdb.config.impl.ParameterImpl param = (org.apache.tuscany.das.rdb.config.impl.ParameterImpl)itr.next();
    			if(param.getIndex() <= 0){
    				param.setIndex(index);
    				index++;
    			}
    			ParameterExtendedImpl paramExt = new ParameterExtendedImpl(param);
    			addToParameters(paramExt);
    		}
    	}
    }

    private void addToParameters(ParameterExtendedImpl paramExt) {
		this.parameters.getParameter().add(paramExt);
		if(paramExt.getDirection().equals(ParameterExtendedImpl.IN)){
			parameters.getInParameters().add(paramExt);
		}
		else{
			parameters.getOutParameters().add(paramExt);
		}
    }
    
    public abstract void execute();

    public abstract DataObject executeQuery();

    public void setParameter(int index, Object value) {
    	ParameterExtendedImpl param = parameters.getParameter(index);
    	if(param != null){
    		param.setValue(value);
    		return;
    	}
    		
    	param = new ParameterExtendedImpl();
    	param.setIndex(index);
    	param.setValue(value);
    	param.setDirection(ParameterExtendedImpl.IN);
    	parameters.getParameter().add(param);
    	parameters.getInParameters().add(param);    	    		
    }

    public void addParameter(ParameterExtendedImpl param) {
    	//eliminate/replace duplicate params, index is filled, so can check it for duplicate
    	ParameterExtendedImpl paramExt = parameters.getParameter(param.getIndex());
    	if(paramExt != null)
    		paramExt = new ParameterExtendedImpl(param);
    	
    	paramExt = parameters.getParameter(param.getIndex(), param.getDirection());
    	if(paramExt != null){
    		paramExt = new ParameterExtendedImpl(param);
    		return;
    	}
    	
    	addToParameters(param);
    }

    public List getParameters() {
    	return parameters.getParameter();
    }

    public Object getParameter(int index) {
    	return parameters.getParameter(index).getValue();
    }

    public void setConnection(ConnectionImpl connection) {
        statement.setConnection(connection);
    }

    protected ConnectionImpl getConnection() {
        return statement.getConnection();
    }

    /*
     * The default impl is to throw an exception. This is overridden by InsertCommandImpl
     */
    public int getGeneratedKey() {

        throw new RuntimeException("This method is only valid for insert commands");
    }

    public void close() {
        statement.close();
    }

    //default direction IN assumed
    public void setParameter(String name, Object value) {
    	ParameterExtendedImpl param = parameters.getParameter(name);
    	if(param != null){
			param.setValue(value);
			return;
    	}
    		
    	param = new ParameterExtendedImpl();
    	param.setIndex(parameters.getParameter().size()+1);
    	param.setName(name);
    	param.setValue(value);
    	param.setDirection(ParameterExtendedImpl.IN);
    	parameters.getParameter().add(param);
    	parameters.getInParameters().add(param);
	}

    //default direction IN assumed
    public Object getParameter(String name) {
		Iterator itr = this.parameters.getInParameters().iterator();
		while(itr.hasNext()){
			ParameterExtendedImpl param = ((ParameterExtendedImpl)itr.next());
			
			if(param.getName() != null && param.getName().equalsIgnoreCase(name)){				
				return param.value;
			}
		}
		return null;
	}
    
}
