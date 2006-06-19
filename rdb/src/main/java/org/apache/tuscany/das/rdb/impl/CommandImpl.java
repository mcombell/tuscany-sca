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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.ResultSetShape;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.XSDHelper;

public abstract class CommandImpl extends BaseCommandImpl implements Command {

	protected Statement statement;

	protected Parameters parameters = new Parameters();

	protected static final boolean debug = false;

	protected ResultSetShape resultSetShape;

	public CommandImpl(String sqlString) {
		statement = new Statement(sqlString);

		try {
			URL url = getClass().getResource("/xml/sdoJava.xsd");
			if (url == null)
				throw new RuntimeException(
					"Could not find resource: xml/sdoJava.xsd");
		
			InputStream inputStream = url.openStream();
			XSDHelper.INSTANCE.define(inputStream, url.toString());
			inputStream.close();
		} catch ( IOException ex ) {
			throw new RuntimeException(ex);
		}

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
		if (p == null)
			throw new RuntimeException("Parameter with name " + name
					+ " not found");

		return p;
	}

	public Object getParameterValue(int index) {
		return parameters.parameterWithIndex(index).getValue();
	}

	public void setConnection(ConnectionImpl connection) {
		statement.setConnection(connection);
	}

	protected ConnectionImpl getConnection() {
		return statement.getConnection();
	}


	public void close() {
		statement.close();
	}

}
