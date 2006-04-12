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

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import commonj.sdo.DataObject;
import commonj.sdo.Type;

/**
 * An ApplyChangesCommand is used to flush the changes associated with a
 * modified graph of DataObjects to a database.
 * 
 * 
 */
public interface ApplyChangesCommand {

	/**
	 * Adds a user-provided update command. In the absence of a user-provided
	 * command, the DAS will generate one. However this alternative may make
	 * sense if the user requires a SQL statement different from what the DAS
	 * generates.
	 * 
	 * @param type
	 *            The DataObject type this command applys to
	 * @param cmd
	 *            The Command used to UPDATE data objects of the specified type
	 */
	public void addUpdateCommand(Type type, Command cmd);

	/**
	 * Adds a user-provided create command. In the absence of a user-provided
	 * command, the DAS will generate one. However this alternative may make
	 * sense if the user requires a SQL statement different from what the DAS
	 * generates.
	 * 
	 * @param type
	 *            The DataObject type this command applys to
	 * @param cmd
	 *            The Command used to INSERT data objects of the specified type
	 */
	public void addCreateCommand(Type type, Command cmd);

	/**
	 * Adds a user-provided delete command. In the absence of a user-provided
	 * command, the DAS will generate one. However this alternative may make
	 * sense if the user requires a SQL statement different from what the DAS
	 * generates.
	 * 
	 * @param type
	 *            The DataObject type this command applys to
	 * @param cmd
	 *            The Command used to DELETE data objects of the specified type
	 */
	public void addDeleteCommand(Type type, Command cmd);

	/**
	 * The change history is scanned and modifications to the graph of data
	 * objects are flushed to the database.
	 * 
	 * @param root
	 *            the topmost containing data object
	 */
	public void execute(DataObject root);

	/**
	 * Provides the java.sql.Connection to be used for this executing this
	 * command.
	 * 
	 * @param connection
	 *            the java.sql.Connection
	 */
	public void setConnection(Connection connection);

	/**
	 * Provides the java.sql.Connection to be used for this executing this
	 * command.
	 * 
	 * @param connection
	 *            the java.sql.Connection
	 * @param manageTransactions
	 *            <code>true</code> if the DAS should perform tx
	 *            commit/rollback
	 */
	public void setConnection(Connection connection, boolean manageTransactions);

	/**
	 * Add relationship metadata necessary for processing query results. This is
	 * an alternative to providing the same information in a config file.
	 * 
	 * @param parent
	 *            a name identifying the relationship parent key (example:
	 *            "CUSTOMER.ID")
	 * @param child
	 *            a name identifying the relationship child key
	 *            ("ORDER.CUSTOMER_ID")
	 */
	public void addRelationship(String parent, String child);

	/**
	 * Add relationship metadata necessary for processing query results. This is
	 * an alternative to providing the same information in a config file.
	 * 
	 * @param parentKey
	 *            the parent key for the relationship
	 * @param childKey
	 *            the child key in the relationship
	 * @see Key
	 */
	public void addRelationship(Key parentKey, Key childKey);

	/**
	 * Add primary key metadata. This is an alternative to providing the same
	 * information in a config file.
	 * 
	 * @param pk
	 *            the string identifying a prmary key. (Example: "CUSTOMER.ID")
	 */
	public void addPrimaryKey(String columnName);

	/**
	 * Add primary key metadata. This is an alternative to providing the same
	 * information in a config file.
	 * 
	 * @param key
	 *            the primary key
	 * @see Key
	 */
	public void addPrimaryKey(Key key);

	/**
	 * Adds a column to be used in a optimistic concurrency control (OCC)
	 * strategy. The generated UPDATE statement will include a overqualified
	 * where clause using this column
	 * 
	 * @param columnName
	 *            the name of the column to be used for OCC
	 */
	public void addCollisionColumn(String columnName);

	/**
	 * Add metadata that indicate a column is a generated primary key.
	 * 
	 * @param string
	 *            the name of the generated primary key column. Example
	 *            ("COMPANY.ID")
	 */
	public void addGeneratedPrimaryKey(String columnName);

	/**
	 * Associate a {@link Converter} with a column to be used by this command.
	 * This is an alternative to providing the same information in a config
	 * file.
	 * 
	 * @param name
	 *            the name of the column being assigned a converter (example:
	 *            "CUSTOMER.LASTNAME")
	 * @param converterName
	 *            the name of the converter instance being assigned (example:
	 *            org.company.project.StringConverter)
	 * @see Converter
	 **/
	public void addConverter(String name, String converterName);

}
