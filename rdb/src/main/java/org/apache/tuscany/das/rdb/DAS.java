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

import org.apache.tuscany.das.rdb.impl.DASFactoryImpl;

import commonj.sdo.DataObject;

/**
 * A CommandGroup represents a set of {@link Command} and single {@link ApplyChangesCommand} 
 * that are created from a common config file.
 * 
 */
public interface DAS {

    DASFactory FACTORY = new DASFactoryImpl();

    /**
     * The change history is scanned and modifications to the graph of data objects are flushed to the database.
     * 
     * @param root
     *            the topmost containing data object
     */
    void applyChanges(DataObject root);

    /**
     * Gets the named command from this factory's inventory
     * 
     * @param name
     *            The identifying name of the requested command
     * @return Returns the identified command
     */
    Command getCommand(String name);

    /**
     * If the CommandGroup is managing connections then this method must be called 
     * when the client is done with the instance.
     * 
     */
    void releaseResources();

    /**
     * Creates a Command based on the provided SQL statement
     * 
     * @param sql
     *            The SQL statement
     * @return returns a Command instance
     */
    Command createCommand(String sql);

}
