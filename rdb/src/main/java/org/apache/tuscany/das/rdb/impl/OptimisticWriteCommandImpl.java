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

public class OptimisticWriteCommandImpl extends UpdateCommandImpl {

	public OptimisticWriteCommandImpl(String sqlString) {
		super(sqlString);
	}

	public void execute() {

		boolean success = false;
		try {
			int rowsAffected = statement.executeUpdate(parameters);
			success = true;
			if ( rowsAffected == 0 ) 
				throw new RuntimeException("OCC Exception");
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
