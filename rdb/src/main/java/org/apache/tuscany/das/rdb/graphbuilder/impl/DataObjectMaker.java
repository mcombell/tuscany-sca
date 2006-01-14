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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.sdo.EDataObject;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

public class DataObjectMaker {

	private final EDataObject dataGraph;
	private boolean debug = false;

	public DataObjectMaker(DataObject dataGraph) {
		this.dataGraph = (EDataObject) dataGraph;
	}
	
	/**
	 * @param tableData
	 * @return
	 */
	public EObject createAndAddDataObject(TableData tableData, ResultMetadata resultMetadata) {
		// Get the EClass from the EPackage, and create a standalone EObject

		DebugUtil.debugln(getClass(), this.debug,"Looking for EClass for " + tableData.getTableName());
	
		EClass tableClass = findTableClass(tableData.getTableName());

		if ( tableClass == null ) 
			throw new RuntimeException("An SDO Type with name " + tableData.getTableName() + " was not found");
		
		EObject obj = SDOUtil.create(tableClass);

		// Now, check to see if the root data object has a containment reference
		// to this EClass. If so, add it to the graph. If not, it will be taken
		// care
		// of when we process relationships
		
		Iterator i = this.dataGraph.eClass().getEReferences().iterator();
		while (i.hasNext()) {
			EReference ref = (EReference) i.next();
			if (ref.isContainment()
					&& ref.getEReferenceType().equals(tableClass)) {
				if ( ref.isMany())
					((InternalEList) this.dataGraph.eGet(ref)).addUnique(obj);
				else
					this.dataGraph.eSet(ref, obj);
			}

		}

		Iterator columnNames = resultMetadata.getColumnNames(tableData.getTableName()).iterator();
		while (columnNames.hasNext()) {
			String columnName = (String) columnNames.next();
			DataObject dataObject = (DataObject)obj;
			Property p = findProperty(dataObject.getType(), columnName);
			Object value = tableData.getColumnData(columnName);
		//	System.out.println("setting " + p.getName()+ " to " + value);
			dataObject.set(p, value);
		}
		
		return obj;
	}

	//temporary, ignoring case
	private Property findProperty(Type type, String columnName) {
		Iterator properties = type.getProperties().iterator();
		while ( properties.hasNext()) {
			Property p = (Property) properties.next();
			if ( columnName.equalsIgnoreCase(p.getName()))
				return p;
		}
		return null;
	}
	
	// Temporary utility to return the Eclass with the given name
	private EClass findTableClass(String tableName) {
		Iterator classifiers = getPackage().getEClassifiers().iterator();
		while (classifiers.hasNext()) {
			EClass tableClass = (EClass) classifiers.next();
			if (tableName.equalsIgnoreCase(tableClass.getName()))
				return tableClass;
		}

		return null;
	}

	private EClass getSchema() {
		return this.dataGraph.eClass();
	}

	private EPackage getPackage() {
		return getSchema().getEPackage();
	}

}
