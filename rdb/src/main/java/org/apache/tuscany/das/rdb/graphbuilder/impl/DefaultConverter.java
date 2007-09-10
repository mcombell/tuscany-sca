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
package org.apache.tuscany.das.rdb.graphbuilder.impl;

import java.sql.Blob;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Converter;

public class DefaultConverter implements Converter {

    public DefaultConverter() {
        super();
    }

    public Object getColumnValue(Object data) {
        return data;
    }

    public Object getPropertyValue(Object data) {
        // if (type.isInstance(data))
        // return data;
        //
        // if ( data == null )
        // return null;
        //
        // String name = type.getInstanceClass().getName();
        // if (name == "java.lang.Byte" || name == "byte") {
        // return new Byte(data.toString());
        // }
        //
        // else if (name == "java.lang.Double" || name == "double") {
        // return new Double(data.toString());
        // }
        //
        // else if (name == "java.lang.Float" || name == "float") {
        // return new Float(data.toString());
        // }
        //
        // else if (name == "java.lang.Integer" || name == "int") {
        // return new Integer(data.toString());
        // }
        //
        // else if (name == "java.lang.Long" || name == "long") {
        // return new Long(data.toString());
        // }
        //
        // else if (name == "java.lang.Short" || name == "short") {
        // return new Short(data.toString());
        // }
        //
        // else if (name == "java.lang.String") {
        // return String.valueOf(data.toString());
        // }

        if (data instanceof Blob) {
            Blob b = (Blob) data;
            try {
                return b.getBytes(1, (int) b.length());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } 
       
        return data;
       
    }
}
