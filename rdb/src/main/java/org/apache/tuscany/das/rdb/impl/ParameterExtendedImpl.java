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
import org.apache.tuscany.das.rdb.config.impl.ParameterImpl;
import commonj.sdo.Type;

public class ParameterExtendedImpl extends ParameterImpl{
    /**
     * Value for "Direction" that indicates that a parameter is soley for input.
     */
    static final String IN = "IN";

    /**
     * Value for "Direction" that indicates that a parameter is soley for output. 
     * Out parameters only apply to Stored Procedures
     */
    static final String OUT = "OUT";

    /**
     * Value for "Direction" that indicates that a parameter is for both input and output. 
     * In-out parameters only apply to stored procedures
     */
    static final String IN_OUT = "IN_OUT";

    private Type type;
    
    protected Object value;

    private Converter converter;
    
    public ParameterExtendedImpl() {
    	super();
    	this.direction = IN;
    }
    
    public ParameterExtendedImpl(org.apache.tuscany.das.rdb.config.impl.ParameterImpl parameterImpl) {
    	this.columnType = parameterImpl.getColumnType();
    	this.direction = parameterImpl.getDirection();
    	this.index = parameterImpl.getIndex();
    	this.name = parameterImpl.getName();
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public void setColumnType(String newColumnType) {
    	super.setColumnType(newColumnType);
    	if(newColumnType != null){
        	String arg0 = newColumnType.substring(0, newColumnType.lastIndexOf("."));
        	String arg1 = newColumnType.substring(newColumnType.lastIndexOf(".")+1);
        	this.type = SDODataTypes.TYPE_HELPER.getType(arg0, arg1);    		
    	}
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
    
    public Object getValue() {
        if (getConverter() != null) {
            return getConverter().getColumnValue(this.value);
        } 
      
        return this.value;        
    }
    
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public Converter getConverter() {
        return this.converter;
    }
    
}
