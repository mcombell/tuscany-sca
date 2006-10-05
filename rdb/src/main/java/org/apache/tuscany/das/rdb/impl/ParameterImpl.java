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

import org.apache.tuscany.das.rdb.Converter;

import commonj.sdo.Type;

public class ParameterImpl {

    /**
     * Value for "Direction" that indicates that a parameter is soley for input.
     */
    static final int IN = 1;

    /**
     * Value for "Direction" that indicates that a parameter is soley for output. 
     * Out parameters only apply to Stored Procedures
     */
    static final int OUT = 2;

    /**
     * Value for "Direction" that indicates that a parameter is for both input and output. 
     * In-out parameters only apply to stored procedures
     */
    static final int IN_OUT = 3;

    protected Object value;

    private int index;

    private Type type;

    private String name;

    private int direction = IN;

    private Converter converter;

    
    public ParameterImpl() {
        super();
    }

    public ParameterImpl(int index) {
        this.index = index;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setIndex(int index) {
        if (index == 0) {
            throw new RuntimeException("Index of zero not allowed");
        }
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Type getType() {
        return this.type;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        if (getConverter() != null) {
            return getConverter().getColumnValue(this.value);
        } 
      
        return this.value;        
    }

    public int getDirection() {
        return this.direction;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public Converter getConverter() {
        return this.converter;
    }

}
