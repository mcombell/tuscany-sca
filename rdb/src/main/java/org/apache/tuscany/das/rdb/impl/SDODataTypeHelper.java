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

import java.sql.Types;

import commonj.sdo.Type;

public final class SDODataTypeHelper {

    private SDODataTypeHelper() {
        
    }
    
    public static String columnTypeForSDOType(Type sdoType){
    	if(sdoType == null){
    		return null;
    	}
    	
    	if (sdoType == SDODataTypes.BOOLEAN) {
            return SDODataTypes.BOOLEAN_STR;
        } else if (sdoType == SDODataTypes.STRING) {
            return SDODataTypes.STRING_STR;
        } else if (sdoType == SDODataTypes.BYTE) {
            return SDODataTypes.BYTE_STR;
        } else if (sdoType == SDODataTypes.BYTES) {
            return SDODataTypes.BYTES_STR;
        } else if (sdoType == SDODataTypes.CHARACTER) {
            return SDODataTypes.CHARACTER_STR;
        } else if (sdoType == SDODataTypes.DATE) {
            return SDODataTypes.DATE_STR;
        } else if (sdoType == SDODataTypes.DATETIME) {
            return SDODataTypes.DATETIME_STR;
        } else if (sdoType == SDODataTypes.DAY) {
            return SDODataTypes.DAY_STR;
        } else if (sdoType == SDODataTypes.DECIMAL) {
            return SDODataTypes.DECIMAL_STR;
        } else if (sdoType == SDODataTypes.DOUBLE) {
            return SDODataTypes.DOUBLE_STR;
        } else if (sdoType == SDODataTypes.DURATION) {
            return SDODataTypes.DURATION_STR;
        } else if (sdoType == SDODataTypes.FLOAT) {
            return SDODataTypes.FLOAT_STR;
        } else if (sdoType == SDODataTypes.INT) {
            return SDODataTypes.INT_STR;
        } else if (sdoType == SDODataTypes.INTEGER) {
            return SDODataTypes.INTEGER_STR;
        } else if (sdoType == SDODataTypes.LONG) {
            return SDODataTypes.LONG_STR;
        } else if (sdoType == SDODataTypes.MONTH) {
            return SDODataTypes.MONTH_STR;
        } else if (sdoType == SDODataTypes.MONTHDAY) {
            return SDODataTypes.MONTHDAY_STR;
        } else if (sdoType == SDODataTypes.OBJECT) {
            return SDODataTypes.OBJECT_STR;            
        } else if (sdoType == SDODataTypes.SHORT) {
            return SDODataTypes.SHORT_STR;
        } else if (sdoType == SDODataTypes.STRING) {
            return SDODataTypes.STRING_STR;
        } else if (sdoType == SDODataTypes.STRINGS) {
            return SDODataTypes.STRINGS_STR;
        } else if (sdoType == SDODataTypes.TIME) {
            return SDODataTypes.TIME_STR;
        } else if (sdoType == SDODataTypes.URI) {
            return SDODataTypes.URI_STR;
        } else if (sdoType == SDODataTypes.YEAR) {
            return SDODataTypes.YEAR_STR;
        } else if (sdoType == SDODataTypes.YEARMONTH) {
            return SDODataTypes.YEARMONTH_STR;
        } else if (sdoType == SDODataTypes.YEARMONTHDAY) {
            return SDODataTypes.YEARMONTHDAY_STR;
        } else if (sdoType == SDODataTypes.BOOLEANOBJECT) {
            return SDODataTypes.BOOLEANOBJECT_STR;
        } else if (sdoType == SDODataTypes.BYTEOBJECT) {
            return SDODataTypes.BYTEOBJECT_STR;
        } else if (sdoType == SDODataTypes.CHARACTEROBJECT) {
            return SDODataTypes.CHARACTEROBJECT_STR;
        } else if (sdoType == SDODataTypes.DOUBLEOBJECT) {
            return SDODataTypes.DOUBLEOBJECT_STR;
        } else if (sdoType == SDODataTypes.FLOATOBJECT) {
            return SDODataTypes.FLOATOBJECT_STR;
        } else if (sdoType == SDODataTypes.INTEGEROBJECT) {
            return SDODataTypes.INTEGEROBJECT_STR;
        } else if (sdoType == SDODataTypes.LONGOBJECT) {
            return SDODataTypes.LONGOBJECT_STR;
        } else if (sdoType == SDODataTypes.SHORTOBJECT) {
            return SDODataTypes.SHORTOBJECT_STR;
        } else {
            throw new RuntimeException("Not a valid SDO Type " + sdoType);
        }    	
    }
    
    public static int sqlTypeFor(Type sdoType) {
        if (sdoType == null) {
            return Types.OTHER;
        }

        if (sdoType == SDODataTypes.BOOLEAN) {
            return Types.BOOLEAN;
        } else if (sdoType == SDODataTypes.STRING) {
            return Types.VARCHAR;
        } else if (sdoType == SDODataTypes.BYTE) {
            return Types.TINYINT;
        } else if (sdoType == SDODataTypes.BYTES) {
            return Types.BINARY;
        } else if (sdoType == SDODataTypes.CHARACTER) {
            return Types.CHAR;
        } else if (sdoType == SDODataTypes.DATE) {
            return Types.DATE;
        } else if (sdoType == SDODataTypes.DATETIME) {
            return Types.DATE;
        } else if (sdoType == SDODataTypes.DAY) {
            return java.sql.Types.BINARY;
        } else if (sdoType == SDODataTypes.DECIMAL) {
            return java.sql.Types.DECIMAL;
        } else if (sdoType == SDODataTypes.DOUBLE) {
            return java.sql.Types.DOUBLE;
        } else if (sdoType == SDODataTypes.DURATION) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.FLOAT) {
            return java.sql.Types.REAL;
        } else if (sdoType == SDODataTypes.INT) {
            return java.sql.Types.INTEGER;
        } else if (sdoType == SDODataTypes.INTEGER) {
            return java.sql.Types.INTEGER;
        } else if (sdoType == SDODataTypes.LONG) {
            return java.sql.Types.BIGINT;
        } else if (sdoType == SDODataTypes.MONTH) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.MONTHDAY) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.OBJECT) {
            return java.sql.Types.JAVA_OBJECT;
        } else if (sdoType == SDODataTypes.SHORT) {
            return java.sql.Types.SMALLINT;
        } else if (sdoType == SDODataTypes.STRING) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.STRINGS) {
            return java.sql.Types.OTHER;
        } else if (sdoType == SDODataTypes.TIME) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.URI) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.YEAR) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.YEARMONTH) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.YEARMONTHDAY) {
            return java.sql.Types.VARCHAR;
        } else if (sdoType == SDODataTypes.BOOLEANOBJECT) {
            return java.sql.Types.BOOLEAN;
        } else if (sdoType == SDODataTypes.BYTEOBJECT) {
            return java.sql.Types.TINYINT;
        } else if (sdoType == SDODataTypes.CHARACTEROBJECT) {
            return java.sql.Types.CHAR;
        } else if (sdoType == SDODataTypes.DOUBLEOBJECT) {
            return java.sql.Types.DOUBLE;
        } else if (sdoType == SDODataTypes.FLOATOBJECT) {
            return java.sql.Types.REAL;
        } else if (sdoType == SDODataTypes.INTEGEROBJECT) {
            return java.sql.Types.INTEGER;
        } else if (sdoType == SDODataTypes.LONGOBJECT) {
            return java.sql.Types.BIGINT;
        } else if (sdoType == SDODataTypes.SHORTOBJECT) {
            return java.sql.Types.SMALLINT;
        } else {
            throw new RuntimeException("Not a valid SDO Type " + sdoType);
        }

    }
}
