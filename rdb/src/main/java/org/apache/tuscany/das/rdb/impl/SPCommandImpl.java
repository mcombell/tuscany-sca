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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.DataObject;

public class SPCommandImpl extends ReadCommandImpl {

	public SPCommandImpl(String sqlString) {
		super(sqlString);
	}
    
    public SPCommandImpl(String sqlString, Config config) {
        super(sqlString, config);
    }

	public DataObject executeQuery() {

		boolean success = false;
		try {
			ResultSet rs = statement.executeCall(parameters);
			success = true;
			
			return buildGraph(rs);
		} catch (SQLException e) {
			DebugUtil.debugln(getClass(), debug, e);
			throw new RuntimeException(e);
		} finally {
			if (success)
				statement.getConnection().cleanUp();
			else
				statement.getConnection().errorCleanUp();
		}
	}
	
	public void execute() {

		boolean success = false;
		try {
			statement.executeUpdateCall(parameters);
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

}
