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
package org.apache.tuscany.das.rdb.graphbuilder.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.DataObject;



/**
 *
 * Used to store and look up table objects based on primary key
 * This could be a lot more efficient if we could use LinkedHashMap from JDK 1.4
 */
public class MultiTableRegistry implements TableRegistry {
	
	private HashMap tableNameMap;
	private HashMap tableValueMap;
	private boolean debug = false;
	

	public MultiTableRegistry() {
		tableNameMap = new HashMap();
		tableValueMap = new HashMap();
	}



	/**
	 * Get the table with the specified name and primary key
	 * @param tableName
	 * @param primaryKey
	 * @return EDataObject
	 */
	public DataObject get(String tableName, List primaryKey) {
		if ( debug ) {
			DebugUtil.debugln(getClass(), debug, "Looking for table " + tableName  + " with PK " + primaryKey);
			DebugUtil.debugln(getClass(), debug, ("\tReturning " + getPkMap(tableName).get(primaryKey)));
		}
		return (DataObject) getPkMap(tableName).get(primaryKey);
	}

	/**
	 * Add the table with the specified name and primary key
	 * @param tableName
	 * @param primaryKey
	 * @param value
	 */
	public void put(String tableName, List primaryKey, DataObject value) {
		if ( getPkMap(tableName).put(primaryKey, value) == null )
		   getCreateValueList(tableName).add(value);
	}
	
	/**
	 * Get the HashMap that contains the primary key to table object
	 * mappings. 
	 * @param tableName
	 * @return HashMap
	 */
	private HashMap getPkMap(String tableName) {
		HashMap pkMap = (HashMap)tableNameMap.get(tableName);
		if ( pkMap == null ) {
			pkMap = new HashMap();
			tableNameMap.put(tableName, pkMap);
		}
		return pkMap;
	}
	
	private List getCreateValueList(String tableName) {
		List values = (List) tableValueMap.get(tableName);
		if ( values == null ) {
			values = new ArrayList();
			tableValueMap.put(tableName, values);
		}
		return values;
	}



	public boolean contains(String tableName, List primaryKey) {
		return  get(tableName,primaryKey) == null ? false : true;
		
	}
	

}