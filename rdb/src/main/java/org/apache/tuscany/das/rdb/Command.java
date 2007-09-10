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

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.tuscany.das.rdb.config.ResultDescriptor;

import commonj.sdo.DataObject;

/**
 * A Command is used to execute a read or write to a database
 */
public interface Command {

    /**
     * Performs the function defined by the command
     */
    void execute();

    /**
     * Performs the function defined by the command and return the results in the root DataObject
     * 
     * @return the root DataObject
     */
    DataObject executeQuery();

    /**
     * Sets the value of the associated Parameter
     * 
     * @param index
     *            the index of the Parameter
     * @param value
     *            the value for the Parameter
     */
    void setParameter(int index, Object value);

    /**
     * Returns the value of the associated Parameter
     * 
     * @param index
     *            the index of the Parameter
     * @return the value of the Parameter
     */
    Object getParameter(int index);

    /**
     * 
     * @param name Name of parameter - should match exact name of database table column 
     * as appearing in Command
     * @param value 
     * 		the value of the parameter
     */
    void setParameter(String name, Object value);
    
    /**
     * 
     * @param name
     * 			the name of the parameter - should match exact name of database table column
     * @return  the value of the parameter
     */
    Object getParameter(String name);
    
    /**
     * Returns the value of the database-generated key. This method is specific 
     * to an "insert" command and will be valid only after the command has
     * been executed.
     * 
     * @return the generated key
     */
    int getGeneratedKey();

    /**
     * Allow set of ResultDescriptor when command is created on-the-fly
     * @param resultDescriptorList
     */
    void setResultDescriptors(List resultDescriptorList);
    
    List getResultDescriptors();
   
    /**
     * Add/replace based on columnIndex (>=0)embedded in resultDescriptor else add at end
     * @param resultDescriptor
     */
    void addResultDescriptor(ResultDescriptor resultDescriptor);
    
    /**
     * remove ResultDescriptor at given columnIndex(>=0) and return same. If not
     * present return null. For -ve index, return null
     * @param columnIndex
     * @return
     */
    ResultDescriptor removeResultDescriptor(int columnIndex);
    
    /**
     * Remove resultDescriptor only if matched for index(>=0), name, type, schema
     * name and table name and return same, else return null For -ve index, ignore  
     *  index and if unique match for rest of the attriutes, remove/return, if multiple
     *  matches found, throw RuntimeException
     * @param resultDescriptor
     * @return
     */
    ResultDescriptor removeResultDescriptor(ResultDescriptor resultDescriptor);
    
    /**
     * Return resultDescriptor if exact match for columnIndex(>=0) found  
     * else return null;
     * 
     * @param columnIndex
     * @return
     */
    ResultDescriptor getResultDescriptor(int columnIndex);
    
    /**
     * Utility method
     * @param ostrm
     * @throws IOException
     */
    void printResultDescriptors(OutputStream ostrm) throws IOException;
}
