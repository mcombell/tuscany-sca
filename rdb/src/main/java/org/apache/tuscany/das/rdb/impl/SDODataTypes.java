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

import commonj.sdo.Type;
import commonj.sdo.helper.TypeHelper;

/**
 * Defines SDO data types. This is used primalirly to type stored procedure OUT parameters.
 * 
 */public class SDODataTypes {

     static TypeHelper helper = TypeHelper.INSTANCE;
     
	final public static Type BOOLEAN = helper.getType("commonj.sdo", "Boolean");
	final public static Type BYTE = helper.getType("commonj.sdo", "Byte");
	final public static Type BYTES = helper.getType("commonj.sdo", "Bytes");
	final public static Type CHARACTER = helper.getType("commonj.sdo", "Character");
	final public static Type DATE = helper.getType("commonj.sdo", "Date");
	final public static Type DATETIME = helper.getType("commonj.sdo", "Date");
	final public static Type DAY = helper.getType("commonj.sdo", "Date");
	final public static Type DECIMAL = helper.getType("commonj.sdo", "Float");
	final public static Type DOUBLE =helper.getType("commonj.sdo", "Double");
	final public static Type DURATION = helper.getType("commonj.sdo", "Date");
	final public static Type FLOAT = helper.getType("commonj.sdo", "Float");
	final public static Type INT = helper.getType("commonj.sdo", "Int");
	final public static Type INTEGER = helper.getType("commonj.sdo", "Integer");
	final public static Type LONG = helper.getType("commonj.sdo", "Long");
	final public static Type MONTH = helper.getType("commonj.sdo", "Date");
	final public static Type MONTHDAY = helper.getType("commonj.sdo", "Date");
	final public static Type OBJECT = helper.getType("commonj.sdo", "Object");
	final public static Type SHORT = helper.getType("commonj.sdo", "Short");
	final public static Type STRING = helper.getType("commonj.sdo", "String");
	final public static Type STRINGS = helper.getType("commonj.sdo", "String");
	final public static Type TIME = helper.getType("commonj.sdo", "Date");
	final public static Type URI = helper.getType("commonj.sdo", "String");
	final public static Type YEAR = helper.getType("commonj.sdo", "Date");
	final public static Type YEARMONTH = helper.getType("commonj.sdo", "Date");
	final public static Type YEARMONTHDAY = helper.getType("commonj.sdo", "Date");
	final public static Type BOOLEANOBJECT = helper.getType("commonj.sdo", "BooleanObject");
	final public static Type BYTEOBJECT = helper.getType("commonj.sdo", "ByteObject");
	final public static Type CHARACTEROBJECT = helper.getType("commonj.sdo", "CharacterObject");
	final public static Type DOUBLEOBJECT = helper.getType("commonj.sdo", "DoubleObject");
	final public static Type FLOATOBJECT = helper.getType("commonj.sdo", "FloatObject");
	final public static Type INTEGEROBJECT = helper.getType("commonj.sdo", "IntObject");
	final public static Type LONGOBJECT = helper.getType("commonj.sdo", "LongObject");
	final public static Type SHORTOBJECT = helper.getType("commonj.sdo", "ShortObject");


}
