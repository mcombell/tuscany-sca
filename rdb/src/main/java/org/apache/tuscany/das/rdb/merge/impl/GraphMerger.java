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
package org.apache.tuscany.das.rdb.merge.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


import org.apache.tuscany.das.rdb.config.wrapper.QualifiedColumn;
import org.apache.tuscany.das.rdb.graphbuilder.impl.MultiTableRegistry;
import org.apache.tuscany.das.rdb.graphbuilder.impl.TableRegistry;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.sdo.impl.EChangeSummaryImpl;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

public class GraphMerger {

	private HashMap keys = new HashMap();

	private TableRegistry registry = new MultiTableRegistry();

	private static Logger logger = Logger.getLogger("GraphMerger");

	//TODO lots of cleanup/design
	public GraphMerger() {
		// Empty Constructor
	}

	public DataObject merge(List graphs) {
		DataObject primaryGraph = (DataObject) graphs.get(0);

		Iterator i = graphs.iterator();
		if (i.hasNext())
			i.next();
		while (i.hasNext()) {
			primaryGraph = merge(primaryGraph, (DataObject) i.next());
		}

		return primaryGraph;
	}

	public DataObject merge(DataObject primary, DataObject secondary) {
		addGraphToRegistry(primary);

		EChangeSummaryImpl summary = (EChangeSummaryImpl) primary
				.getDataGraph().getChangeSummary();
		summary.endLogging();
		Iterator i = secondary.getType().getProperties().iterator();

		while (i.hasNext()) {
			Property p = (Property) i.next();

			Iterator objects = secondary.getList(p.getName()).iterator();
			while (objects.hasNext()) {
				DataObject object = (DataObject) objects.next();
				createObjectWithSubtree(primary, p, object);
			}
		}
		summary.resumeLogging();
		return primary;
	}

	private void createObjectWithSubtree(DataObject root, Property p,
			DataObject object) {
		Object pk = getPrimaryKey(object);

		if (!registry.contains(object.getType().getName(), Collections
				.singletonList(pk))) {
	//		logger.info("creating " + object.getType().getName() + " with pk " + pk);
			DataObject newObject = root.createDataObject(p.getName());

			EObject eObjectToCopy = (EObject) object;

			Iterator attrs = eObjectToCopy.eClass().getEAllAttributes()
					.iterator();
			while (attrs.hasNext()) {
				EAttribute attr = (EAttribute) attrs.next();
				newObject.set(attr.getName(), eObjectToCopy.eGet(attr));
			}
			registry.put(object.getType().getName(), Collections
					.singletonList(pk), (EObject) newObject);

			Iterator refs = eObjectToCopy.eClass().getEAllReferences()
					.iterator();
			while (refs.hasNext()) {
				EReference ref = (EReference) refs.next();
				List refObjects;
				if (!ref.isMany()) {
					refObjects = Collections.singletonList(eObjectToCopy
							.eGet(ref));
				} else {
					refObjects = (List) eObjectToCopy.eGet(ref);
				}

				Iterator iter = refObjects.iterator();
				while (iter.hasNext()) {
					DataObject refObject = (DataObject) iter.next();
					createObjectWithSubtree(root, refObject
							.getContainmentProperty(), refObject);
					
					refObject = (DataObject) registry.get(refObject.getType().getName(), Collections.singletonList(getPrimaryKey(refObject)));
					if (ref.isMany()) {
						newObject.getList(
								newObject.getType().getProperty(ref.getName()))
								.add(refObject);
					} else
						newObject.set(newObject.getType().getProperty(
								ref.getName()), refObject);
				}

			}

		}
	
	}

	private void addGraphToRegistry(DataObject graph1) {
		Iterator i = graph1.getType().getProperties().iterator();
		while (i.hasNext()) {
			Property p = (Property) i.next();
			Iterator objects = graph1.getList(p).iterator();
			while (objects.hasNext()) {
				DataObject object = (DataObject) objects.next();
				Object pk = object.get(getPrimaryKeyName(object));
				logger.finest("Adding object with pk " + pk + " to registry");
				registry.put(object.getType().getName(), Collections
						.singletonList(pk), (EObject) object);
			}
		}
	}

	private Object getPrimaryKey(DataObject object) {
		String pkName = getPrimaryKeyName(object);
		return object.get(pkName);
	}

	private String getPrimaryKeyName(DataObject object) {
		return (String) keys.get(object.getType().getName());
	}

	public void addPrimaryKey(String key) {
		QualifiedColumn column = new QualifiedColumn(key);
		logger.finest("Adding " + column.getTableName() + " "
				+ column.getColumnName() + " to keys");
		keys.put(column.getTableName(), column.getColumnName());
	}
}
