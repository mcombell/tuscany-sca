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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.util.DebugUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;


public class RowObjects {

	private HashMap objectsByTableName;

	private ArrayList tableObjects;
	
	private static final boolean debug = false;

	private final GraphBuilderMetadata metadata;

	private final TableRegistry registry;

	public RowObjects(GraphBuilderMetadata metadata, TableRegistry registry) {
		objectsByTableName = new HashMap();
		tableObjects = new ArrayList();
		this.metadata = metadata;
		this.registry = registry;
	}

	public void put(String key, EObject value) {
		objectsByTableName.put(key, value);
		tableObjects.add(value);
	}

	public EObject get(String tablePropertyName) {
		return (EObject) objectsByTableName.get(tablePropertyName);
	}

	void processRelationships() {
		MappingWrapper wrapper = new MappingWrapper(metadata.getMapping());
		if (wrapper.hasRecursiveRelationships()) {
			processRecursiveRelationships(wrapper);
			return;
		}

		Iterator i = metadata.getRelationships().iterator();
		while (i.hasNext()) {
			Relationship r = (Relationship) i.next();

			EObject parentTable = get(wrapper
					.getTablePropertyName(r.getPrimaryKeyTable()));
			EObject childTable = get(wrapper
					.getTablePropertyName(r.getForeignKeyTable()));

			DebugUtil.debugln(getClass(), debug, "Parent table: " + parentTable);
			DebugUtil.debugln(getClass(), debug, "Child table: " + childTable);

			if ((parentTable == null) || (childTable == null))
				continue;

			EReference ref = (EReference) parentTable.eClass()
					.getEStructuralFeature(r.getName());
			setOrAdd(parentTable, childTable, ref);
			
		}
	}


	
	private void processRecursiveRelationships(MappingWrapper wrapper) {
		Iterator i = tableObjects.iterator();
		while (i.hasNext()) {
			EObject table = (EObject) i.next();
		//	System.out.println(table.eClass().getName());
			Iterator relationships = wrapper.getRelationshipsByChildTable(table.eClass().getName()).iterator();
			while ( relationships.hasNext() ) {
				Relationship r = (Relationship) relationships.next();
			//	System.out.println(r.getName());
				EObject parentTable = findParentTable(table, r, wrapper);
				
				if (parentTable == null)
					continue;

				EReference ref = (EReference) parentTable.eClass().getEStructuralFeature(r.getName());
				setOrAdd(parentTable, table, ref);
			}
			
		}
	}
	
	private void setOrAdd(EObject parent, EObject child, EReference ref) {
		if (ref.getUpperBound() == ETypedElement.UNBOUNDED_MULTIPLICITY) {
			Collection value = (Collection) parent.eGet(ref);
			value.add(child);
		} else {
			parent.eSet(ref, child);
		}
	}
	
	private EObject findParentTable(EObject childTable, 
			Relationship r, MappingWrapper wrapper) {
		
		ArrayList fkValue = new ArrayList();
		Iterator keyPairs = r.getKeyPair().iterator();
		while (keyPairs.hasNext()) {
			KeyPair pair = (KeyPair) keyPairs.next();
			String childProperty = wrapper.getColumnPropertyName(r.getPrimaryKeyTable(), pair.getForeignKeyColumn());
	
			EAttribute attr = (EAttribute) childTable.eClass()
					.getEStructuralFeature(childProperty);
			fkValue.add(childTable.eGet(attr));
		}

		DebugUtil.debugln(getClass(), debug, "Trying to find parent of " + r.getForeignKeyTable() + " with FK "
				+ fkValue);
		EObject parentTable = registry.get(r.getPrimaryKeyTable(), fkValue);
		DebugUtil.debugln(getClass(), debug, "Parent table from registry: " + parentTable);
		return parentTable;
	}

}
