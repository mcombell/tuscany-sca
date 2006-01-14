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

public class InsertCommandImpl extends WriteCommandImpl {

	private int generatedKey;

	private boolean hasGeneratedKey = false;

	public InsertCommandImpl(String sqlString) {
		super(sqlString);
	}

	protected boolean isInsert() {
		return true;
	}

	public int getGeneratedKey() {

		if (hasGeneratedKey)
			return generatedKey;
		
		throw new RuntimeException("No generated key is available");
	}

	public Object getParameterValue(String name) {

		if (name.equals("generated_key"))
			if (hasGeneratedKey)
				return new Integer(generatedKey);
			else
				throw new RuntimeException("No generated key available");
		
		return super.getParameterValue(name);
	}

	protected void subtypeProcessing() throws SQLException {
		loadGeneratedKey();
	}

	private void loadGeneratedKey() throws SQLException {
		Integer key = statement.getGeneratedKey();
		if (key != null) {
			hasGeneratedKey = true;
			generatedKey = key.intValue();
		}

	}

	public String toString() {

		String superString = super.toString();
		StringBuffer buffer = new StringBuffer(superString);

		buffer.append("\nGenerating key: " + hasGeneratedKey);
		if (hasGeneratedKey)
			buffer.append("\nGenerated key: " + generatedKey);

		return buffer.toString();
	}

}
