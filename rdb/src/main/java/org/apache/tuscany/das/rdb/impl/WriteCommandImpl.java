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
package org.apache.tuscany.das.rdb.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.Parameter;
import org.apache.tuscany.das.rdb.config.Config;

import commonj.sdo.DataObject;
import commonj.sdo.Type;

public abstract class WriteCommandImpl extends CommandImpl {
	

	public WriteCommandImpl(String sqlString) {
		super(sqlString);
	}	

	public void execute() {

		boolean success = false;
		try {
			statement.executeUpdate(parameters);
			subtypeProcessing();
			success = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (success)
				statement.getConnection().cleanUp();
			else
				statement.getConnection().errorCleanUp();
		}

	}

	public DataObject executeQuery() {
		throw new UnsupportedOperationException();
	}

	public Config getMappingModel() {
		return this.mappingModel.getConfig();
	}

	
	/**
	 * Subclasses add specific bahavior
	 * Default is to do nothing
	 */
	protected void subtypeProcessing() throws SQLException {
		if ( false )
			throw new SQLException();
	}

	public void setDataObjectModel(Type schema) {
		// don't really care what the model is here
	}
	
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nSQL: " + statement.queryString.getUnmodifiedString());
		buffer.append("\nModified SQL: " + statement.queryString.getPreparedString());
		return buffer.toString();
	}

	public int getGeneratedKey() {
			throw new RuntimeException("No generated key is available");
	}

	public void addParameters(Collection updateParameters) {
		Iterator i = updateParameters.iterator();
		while ( i.hasNext()) {
			Parameter p = (Parameter) i.next();
			addParameter(p);
		}
	}


}
