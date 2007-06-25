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
 */
public class SDODataTypes {

    public static final TypeHelper TYPE_HELPER = TypeHelper.INSTANCE;

    public static final Type BOOLEAN = TYPE_HELPER.getType("commonj.sdo", "Boolean");

    public static final Type BYTE = TYPE_HELPER.getType("commonj.sdo", "Byte");

    public static final Type BYTES = TYPE_HELPER.getType("commonj.sdo", "Bytes");

    public static final Type CHARACTER = TYPE_HELPER.getType("commonj.sdo", "Character");

    public static final Type DATE = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type DATETIME = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type DAY = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type DECIMAL = TYPE_HELPER.getType("commonj.sdo", "Float");

    public static final Type DOUBLE = TYPE_HELPER.getType("commonj.sdo", "Double");

    public static final Type DURATION = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type FLOAT = TYPE_HELPER.getType("commonj.sdo", "Float");

    public static final Type INT = TYPE_HELPER.getType("commonj.sdo", "Int");

    public static final Type INTEGER = TYPE_HELPER.getType("commonj.sdo", "Integer");

    public static final Type LONG = TYPE_HELPER.getType("commonj.sdo", "Long");

    public static final Type MONTH = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type MONTHDAY = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type OBJECT = TYPE_HELPER.getType("commonj.sdo", "Object");

    public static final Type SHORT = TYPE_HELPER.getType("commonj.sdo", "Short");

    public static final Type STRING = TYPE_HELPER.getType("commonj.sdo", "String");

    public static final Type STRINGS = TYPE_HELPER.getType("commonj.sdo", "String");

    public static final Type TIME = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type URI = TYPE_HELPER.getType("commonj.sdo", "String");

    public static final Type YEAR = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type YEARMONTH = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type YEARMONTHDAY = TYPE_HELPER.getType("commonj.sdo", "Date");

    public static final Type BOOLEANOBJECT = TYPE_HELPER.getType("commonj.sdo", "BooleanObject");

    public static final Type BYTEOBJECT = TYPE_HELPER.getType("commonj.sdo", "ByteObject");

    public static final Type CHARACTEROBJECT = TYPE_HELPER.getType("commonj.sdo", "CharacterObject");

    public static final Type DOUBLEOBJECT = TYPE_HELPER.getType("commonj.sdo", "DoubleObject");

    public static final Type FLOATOBJECT = TYPE_HELPER.getType("commonj.sdo", "FloatObject");

    public static final Type INTEGEROBJECT = TYPE_HELPER.getType("commonj.sdo", "IntObject");

    public static final Type LONGOBJECT = TYPE_HELPER.getType("commonj.sdo", "LongObject");

    public static final Type SHORTOBJECT = TYPE_HELPER.getType("commonj.sdo", "ShortObject");

}
