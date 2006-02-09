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

import java.util.Iterator;

import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;

public class DataObjectMaker {

	private final DataObject rootObject;

	private boolean debug = false;

	public DataObjectMaker(DataObject root) {
		this.rootObject = root;
	}

	/**
	 * @param tableData
	 * @return
	 */
	public DataObject createAndAddDataObject(TableData tableData,
			ResultMetadata resultMetadata) {
		// Get a Type from the package and create a standalone DataObject

		DebugUtil.debugln(getClass(), this.debug, "Looking for Type for "
				+ tableData.getTableName());

		Type tableClass = findTableTypeByPropertyName(tableData.getTableName());

		if (tableClass == null)
			throw new RuntimeException("An SDO Type with name "
					+ tableData.getTableName() + " was not found");

		DataObject obj = DataFactory.INSTANCE.create(tableClass);

		// Now, check to see if the root data object has a containment reference
		// to this EClass. If so, add it to the graph. If not, it will be taken
		// care
		// of when we process relationships

		Iterator i = this.rootObject.getType().getProperties().iterator();
		while (i.hasNext()) {
			Property p = (Property) i.next();

			if (p.isContainment() && p.getType().equals(tableClass)) {
				if (p.isMany())
					rootObject.getList(p).add(obj);
				// TODO This was a performance optimization for EMF in SDO 1.1,
				// check to see if there is
				// something equivalent in SDO 2.0
				// ((InternalEList) this.dataGraph.eGet(ref)).addUnique(obj);
				else
					this.rootObject.set(p, obj);
			}

		}

		Iterator columnNames = resultMetadata.getColumnNames(
				tableData.getTableName()).iterator();
		while (columnNames.hasNext()) {
			String columnName = (String) columnNames.next();
			DataObject dataObject = (DataObject) obj;
			Property p = findProperty(dataObject.getType(), columnName);
			Object value = tableData.getColumnData(columnName);

			dataObject.set(p, value);
		}

		return obj;
	}

	// temporary, ignoring case
	private Property findProperty(Type type, String columnName) {
		Iterator properties = type.getProperties().iterator();
		while (properties.hasNext()) {
			Property p = (Property) properties.next();
			if (columnName.equalsIgnoreCase(p.getName()))
				return p;
		}
		return null;
	}

	private Type findTableTypeByPropertyName(String tableName) {
		Iterator i = rootObject.getType().getProperties().iterator();
		while (i.hasNext()) {
			Property p = (Property) i.next();
	//		System.out.println(p.getType().getName());
			if (tableName.equals(p.getType().getName()))
				return p.getType();
		}
		
		return null;
	}

	private Type findTableTypeByRootReference(String refName) {
		return rootObject.getProperty(refName).getType();
	}

}
