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

import java.util.ArrayList;
import java.util.List;

/**
 * A key instance is used to describe a Table key definition.  This is used
 * primarily for compound keys since simple keys are described with a name.
 * 
 * 
 */public class Key {
	
	private List columnNames = new ArrayList();

	/**
	 * Contructor for a single column key
	 * @param columnName The single column fo rthe key
	 */
	public Key(String columnName) {
		super();
		this.columnNames.add(columnName);
	}
	
	/**
	 * Constructor for a compound key
	 * @param columns The array of column names that compose the key
	 */
	public Key(String[] columns) {
		for (int i=0;i<columns.length; i++) {
			this.columnNames.add(columns[i]);
		}
	}

	/**
	 * Returns a List of column names that compose the key
	 * @return a list of column names
	 */
	public List getColumNames() {
		return this.columnNames;
	}
	
}
