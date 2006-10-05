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
package org.apache.tuscany.das.rdb;

/**
 * A lightweight Table-column <--> DataObject-property converter framework. Converters 
 * allow a user to insert a transformation between a column value
 * and is destination DataObject property value. For example, by default, a VARCHAR 
 * column will be represented as a String in its corresponding
 * DataObject property. A user could insert a converter that transforms the the VARCHAR 
 * value to an Integer. If this is done then although the column
 * returns character data, the DataObject property will be an Integer
 * 
 * 
 */
public interface Converter {

    /**
     * Transform the columnData object to a new value and possibly 
     * new type. This should be the invers operation of #getColumnValue
     * 
     * @param columnData
     *            The column value to transorm
     * @return Returns the transformed value
     */
    Object getPropertyValue(Object columnData);

    /**
     * Transform the columnData object to a new value and possibly new 
     * type. This should be the invers operation of #getPropertyValue
     * 
     * @param propertyData
     *            The property value to transform
     * @return Returns the transformed value
     */
    Object getColumnValue(Object propertyData);

}
