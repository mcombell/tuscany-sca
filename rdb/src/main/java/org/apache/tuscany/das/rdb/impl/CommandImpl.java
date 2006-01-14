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
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.Key;
import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.ResultSetShape;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

import commonj.sdo.DataObject;
import commonj.sdo.Type;

public abstract class CommandImpl implements Command {

	protected Statement statement;
	
	protected Parameters parameters = new Parameters();

	protected MappingWrapper mappingModel = new MappingWrapper();

	protected static final boolean debug = false;

	protected ResultSetShape resultSetShape;

	public CommandImpl(String sqlString) {
		statement = new Statement(sqlString);
	}

	public abstract void execute();

	public abstract DataObject executeQuery();

	public void setParameterValue(String name, Object value) {
		parameters.setParameter(name, value);
	}
	public void setParameterValue(int index, Object value) {
		parameters.setParameter(index, value);
	}

    public void setParameterType(String name, Type dataType) {
        parameters.setParameterWithType(name, dataType);
    }
    
    public void setParameterType(int index, Type dataType) {
        parameters.setParameterWithType(index, dataType);
    }

    public void addParameter(int index, Type sdoType) {
		addParameter(index, Parameter.IN, sdoType);
	}

	public void addParameter(int index, int direction, Type sdoType) {
		parameters.findOrCreateParameterWithIndex(index, direction, sdoType);
	}

	public void addParameter(String name, Type sdoType) {
		addParameter(name, Parameter.IN, sdoType);
	}

	public void addParameter(String name, int direction, Type sdoType) {
		parameters.setParameterWithType(name, sdoType);
	}

	public void addParameter(Parameter param) {
		parameters.add(param);
	}

	public Parameter getParameter(String name) {
		return parameters.get(name);
	}

	public Parameter getParameter(int index) {
		return parameters.get(index);

	}

	public List getParameters() {
		return parameters.parameterList();
	}


	public Object getParameterValue(String name) {
		Parameter p = parameters.get(name);
		if ( p == null ) 
			throw new RuntimeException("Parameter with name " + name + " not found");
		
		return p;
	}

	public Object getParameterValue(int index) {
		return parameters.parameterWithIndex(index).getValue();
	}

	public void setConnection(Connection connection) {
		setConnection(new ConnectionImpl(connection));
	}

	public void setConnection(Connection connection, boolean manageTransaction) {
		ConnectionImpl c = new ConnectionImpl(connection);
		c.setManageTransactions(manageTransaction);
		setConnection(c);
	}

	public void setConnection(ConnectionImpl connection) {
		statement.setConnection(connection);
	}

	protected ConnectionImpl getConnection() {
		return statement.getConnection();
	}

	public void addRelationship(String parentName, String childName) {
		mappingModel.addRelationship(parentName, childName);
	}

	public void addRelationship(Key parentKey, Key childKey) {
		mappingModel.addRelationship(parentKey, childKey);
	}

	public void addPrimaryKey(String pk) {
		mappingModel.addPrimaryKey(pk);
	}

	public void addPrimaryKey(Key pk) {
		mappingModel.addPrimaryKey(pk);
	}

	public void setResultSetShape(ResultSetShape shape) {
		this.resultSetShape = shape;
	}

	public void addConverter(String name, String converter) {
		mappingModel.addConverter(name, converter);
	}

	public void close() {
		statement.close();
	}

}
