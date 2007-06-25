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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import commonj.sdo.Type;

public class Parameters {
    private final Logger logger = Logger.getLogger(Parameters.class);

    private List parameters = new ArrayList();

    private List inParams = new ArrayList();

    private List outParams = new ArrayList();

    public Parameters() {
        super();
    }

    public ParameterImpl get(int index) {
        return (ParameterImpl) parameters.get(index);
    }

    public List outParams() {
        return outParams;
    }

    public List inParams() {
        return inParams;
    }

    private void addParameter(ParameterImpl param) {
        if (param.getDirection() == ParameterImpl.IN) {
            inParams.add(param);
        } else if ((param.getDirection() == ParameterImpl.OUT) || (param.getDirection() == ParameterImpl.IN_OUT)) {
            outParams.add(param);
        }

        this.parameters.add(param);
    }

    public void add(ParameterImpl param) {
        addParameter(param);
    }

    public ParameterImpl findOrCreateParameterWithIndex(int index, int direction, Type sdoType) {
        Iterator i = parameters.iterator();
        while (i.hasNext()) {
            ParameterImpl param = (ParameterImpl) i.next();

            if (param.getIndex() == index) {
                return param;
            }
        }
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Creating new parameter with index " + index);
        }

        ParameterImpl newParam = new ParameterImpl(index);
        newParam.setDirection(direction);
        newParam.setType(sdoType);
        addParameter(newParam);
        return newParam;
    }

    public List parameterList() {
        return parameters;
    }

    public ParameterImpl findOrCreateParameterWithIndex(int index) {
        return findOrCreateParameterWithIndex(index, ParameterImpl.IN, null);
    }

    public void setParameter(int index, Object value) {
        ParameterImpl param = findOrCreateParameterWithIndex(index);
        param.setValue(value);
    }

    public ParameterImpl parameterWithIndex(int index) {
        Iterator i = parameters.iterator();
        while (i.hasNext()) {
            ParameterImpl param = (ParameterImpl) i.next();

            if (param.getIndex() == index) {
                return param;
            }
        }
        return null;
    }

}
