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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.Type;

public class Parameters {

	private ArrayList parameters = new ArrayList();

	private ArrayList inParams = new ArrayList();

	private ArrayList outParams = new ArrayList();

	private HashMap parametersByName = new HashMap();

	private static boolean debug = false;

	public Parameters() {
		super();
	}

	public Parameter get(String name) {
		return (Parameter) parametersByName.get(name);
	}

	public Parameter get(int index) {
		return (Parameter) parameters.get(index);
	}

	public List outParams() {
		return outParams;
	}

	public List inParams() {
		return inParams;
	}

	private void addParameter(Parameter param) {
		if (param.getDirection() == Parameter.IN)
			inParams.add(param);
		else if ((param.getDirection() == Parameter.OUT)
				|| (param.getDirection() == Parameter.IN_OUT))
			outParams.add(param);

		this.parameters.add(param);
		parametersByName.put(param.getName(), param);
	}

	public void add(Parameter param) {
		addParameter(param);
	}

	public Parameter findOrCreateParameterNamed(String name) {
		Parameter param = get(name);
		if (param == null) {
			param = new ParameterImpl(name);
			addParameter(param);
		}
		return param;
	}

	public Parameter findOrCreateParameterWithIndex(int index, int direction,
			Type sdoType) {
		Iterator i = parameters.iterator();
		while (i.hasNext()) {
			Parameter param = (Parameter) i.next();

			if (param.getIndex() == index)
				return param;
		}
		DebugUtil.debugln(getClass(), debug,
				"Creating new parameter with index " + index);
		Parameter newParam = new ParameterImpl(index);
		newParam.setDirection(direction);
		newParam.setType(sdoType);
		addParameter(newParam);
		return newParam;
	}

	public List parameterList() {
		return parameters;
	}

	public Parameter findOrCreateParameterWithIndex(int index) {
		return findOrCreateParameterWithIndex(index, Parameter.IN,
				SDODataTypes.OBJECT);
	}

	public void setParameter(int index, Object value) {
		Parameter param = findOrCreateParameterWithIndex(index);
		param.setValue(value);
	}

	public void setParameter(String name, Object value) {
		if (name == null)
			throw new RuntimeException("Null parameter name not allowed");
		Parameter param = findOrCreateParameterNamed(name);
		param.setValue(value);
	}

	public void setParameterWithType(String name, Type sdoType) {
		if (name == null)
			throw new RuntimeException("Null parameter name not allowed");
		Parameter p = findOrCreateParameterNamed(name);
		p.setType(sdoType);
	}

    public void setParameterWithType(int index, Type sdoType) {
        if (index == 0)
            throw new RuntimeException("Null parameter index not allowed");
        Parameter p = findOrCreateParameterWithIndex(index);
        p.setType(sdoType);
    }
    
    public Parameter parameterWithIndex(int index) {
		Iterator i = parameters.iterator();
		while (i.hasNext()) {
			Parameter param = (Parameter) i.next();

			if (param.getIndex() == index)
				return param;
		}
		return null;
	}

}
