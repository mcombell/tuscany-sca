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
package org.apache.tuscany.das.rdb.merge.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.wrapper.QualifiedColumn;
import org.apache.tuscany.das.rdb.graphbuilder.impl.MultiTableRegistry;
import org.apache.tuscany.das.rdb.graphbuilder.impl.TableRegistry;
import org.apache.tuscany.sdo.impl.ChangeSummaryImpl;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;
import commonj.sdo.helper.TypeHelper;

public class GraphMerger {

    private static Logger logger = Logger.getLogger("GraphMerger");

    private Map keys = new HashMap();

    private TableRegistry registry = new MultiTableRegistry();
    
    private Config config = null;//JIRA-962 for any new tests with schema , we need this

    // TODO lots of cleanup/design
    public GraphMerger() {
        // Empty Constructor
    }

    //JIRA-952
    public GraphMerger(Config cfg) {
        this.config = cfg;
    }
    
    // TODO Replace EMF reference with SDOUtil function when available
    // (Tuscany-583)
    public DataObject emptyGraph(Config config) {

        if (config.getDataObjectModel() == null) {
            throw new RuntimeException("DataObjectModel must be specified in the Config");
        }

        String uri = "http:///org.apache.tuscany.das.rdb/das";
        TypeHelper typeHelper = SDOUtil.createTypeHelper();
        Type rootType = SDOUtil.createType(typeHelper, uri + "/DataGraphRoot", "DataGraphRoot", false);

        List types = SDOUtil.getTypes(typeHelper, config.getDataObjectModel());
        if (types == null) {
            throw new RuntimeException("SDO Types have not been registered for URI " + config.getDataObjectModel());
        }

        Iterator i = types.iterator();
        while (i.hasNext()) {
            Type type = (Type) i.next();
            Property property = SDOUtil.createProperty(rootType, type.getName(), type);
            SDOUtil.setContainment(property, true);
            SDOUtil.setMany(property, true);
        }

        // Create the DataGraph
        DataGraph g = SDOUtil.createDataGraph();

        // Create the root object
        g.createRootObject(rootType);

        ChangeSummary summary = g.getChangeSummary();
        summary.beginLogging();

        return g.getRootObject();
    }

    public DataObject merge(List graphs) {
        DataObject primaryGraph = (DataObject) graphs.get(0);

        Iterator i = graphs.iterator();
        if (i.hasNext()) {
            i.next();
        }
        while (i.hasNext()) {
            primaryGraph = merge(primaryGraph, (DataObject) i.next());
        }

        return primaryGraph;
    }

    public DataObject merge(DataObject primary, DataObject secondary) {
        addGraphToRegistry(primary);

        ChangeSummary summary = primary.getDataGraph().getChangeSummary();
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
        ((ChangeSummaryImpl) summary).resumeLogging();
        return primary;
    }

    private void createObjectWithSubtree(DataObject root, Property p, DataObject object) {
        Object pk = getPrimaryKey(object);

        if (!registry.contains(object.getType().getName(), Collections.singletonList(pk))) {
            DataObject newObject = root.createDataObject(p.getName());
            Iterator attrs = object.getType().getProperties().iterator();
            while (attrs.hasNext()) {
                Property attr = (Property) attrs.next();
                if (attr.getType().isDataType()) {
                    newObject.set(attr.getName(), object.get(attr));
                }
            }
            registry.put(object.getType().getName(), Collections.singletonList(pk), newObject);
            Iterator refs = object.getType().getProperties().iterator();
            while (refs.hasNext()) {
                Property ref = (Property) refs.next();
                if (!ref.getType().isDataType()) {
                    List refObjects;
                    if (!ref.isMany()) {
                        refObjects = Collections.singletonList(object.get(ref));
                    } else {
                        refObjects = (List) object.get(ref);
                    }
                    Iterator iter = refObjects.iterator();
                    while (iter.hasNext()) {
                        DataObject refObject = (DataObject) iter.next();
                        createObjectWithSubtree(root, refObject.getContainmentProperty(), refObject);
                        refObject = registry.get(refObject.getType().getName(), 
                                Collections.singletonList(getPrimaryKey(refObject)));
                        if (ref.isMany()) {
                            newObject.getList(newObject.getType().getProperty(ref.getName())).add(refObject);
                        } else {
                            newObject.set(newObject.getType().getProperty(ref.getName()), refObject);
                        }
                    }
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
                registry.put(object.getType().getName(), Collections.singletonList(pk), object);
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

    //JIRA-952
    public void addPrimaryKey(String key) {
    	QualifiedColumn column = null;
    	if(this.config != null && this.config.isDatabaseSchemaNameSupported()){
    		column = new QualifiedColumn(key, this.config.isDatabaseSchemaNameSupported()); 
    	}
    	else{
    		column = new QualifiedColumn(key);
    	}
        
        logger.finest("Adding " + column.getTableName() + " " + column.getColumnName() + " to keys");
        keys.put(column.getTableName(), column.getColumnName());
    }
}
