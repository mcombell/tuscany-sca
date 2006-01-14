/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.das.rdb;

import java.sql.Connection;

import org.apache.tuscany.das.rdb.impl.CommandGroupFactoryImpl;

/**
 * A CommandGroup represents a set of {@link Command} and single
 * {@link ApplyChangesCommand} that are created from a common config file.
 * 
 * 
 */
public interface CommandGroup {

    public static CommandGroupFactory FACTORY = new CommandGroupFactoryImpl();

    /**
     * Return the ApplyChangesCommnad for this configured factory
     * 
     * @return Returns an instance of ApplyChangesCommand
     */
    public ApplyChangesCommand getApplyChangesCommand();

    /**
     * Gets the named command from this factory's inventory
     * 
     * @param name
     *            The identifying name of the requested command
     * @return Returns the identified command
     */
    public Command getCommand(String name);

    /**
     * Set the default connection for all command in the group
     * 
     * @param connection
     */
    public void setConnection(Connection connection);

}