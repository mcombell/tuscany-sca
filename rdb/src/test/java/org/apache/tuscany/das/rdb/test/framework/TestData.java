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
package org.apache.tuscany.das.rdb.test.framework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class TestData {
	
	protected Object[][] data;
	private int currentRow = -1;
	protected Connection connection;
	

	public TestData(Connection c, Object[][] customerData) {
		this.connection = c; 
		this.data = customerData;
	}

	public int size() {
		return data[0].length;
	}
	
	public int numberOfRows() {
		return data.length;
	}
	
	public boolean next() {
		++currentRow;
		if ( currentRow < numberOfRows() ) 
			return true;
		else
			return false;
	}

	public abstract String getTableName();
	
	
	public Object getObject(int i) {
		return data[currentRow][i-1];
	}
	
	public void refresh() throws SQLException {
		deleteRowsFromTable();
		insertRows();
	}
	
	protected void deleteRowsFromTable() throws SQLException {
		PreparedStatement ps = connection.prepareStatement("delete from " + getTableName());
		ps.execute();
		ps.close();
	}
	
	protected void insertRows() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append(getTableName());
		sql.append(" values (");
		for ( int i=1; i < size(); i++) {
			sql.append("?,");
		}
		sql.append("?)");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		
		while ( next() ) {
			for ( int i=1; i <= size(); i++ ) {
				ps.setObject(i, getObject(i));
			}
			ps.execute();
			ps.clearParameters();
		}
		ps.close();
	}
}
