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
package org.apache.tuscany.das.rdb.graphbuilder.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.impl.ResultSetShape;
import org.apache.tuscany.sdo.util.DataObjectUtil;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.Property;
import commonj.sdo.Type;
import commonj.sdo.helper.TypeHelper;

/**
 */
public class GraphBuilderMetadata {

    private MappingWrapper configWrapper;

    private final Collection resultSets = new ArrayList();

    private String typeURI;

    private Type rootType;

    private TypeHelper typeHelper = SDOUtil.createTypeHelper();

    public GraphBuilderMetadata(Collection results, Config model, ResultSetShape shape) throws SQLException {
        this.configWrapper = new MappingWrapper(model);
        if (model != null) {
            this.typeURI = model.getDataObjectModel();
        }

        Iterator i = results.iterator();
        while (i.hasNext()) {
            ResultSet rs = (ResultSet) i.next();
            ResultMetadata resultMetadata = new ResultMetadata(rs, configWrapper, shape);
            resultSets.add(resultMetadata);
        }

    }

    /**
     * Returns the collection of ResultMetadata objects
     */
    public Collection getResultMetadata() {
        return this.resultSets;
    }

    /**
     * Returns the set of defined relationships
     */

    public Collection getRelationships() {
        return configWrapper.getConfig().getRelationship();
    }

    /**
     * Returns the root Type
     */
    public Type getRootType() {
        if (this.rootType == null) {
            if (this.typeURI == null) {
                createDynamicTypes();
            } else {
                createDynamicRoot();
            }
        }

        return this.rootType;
    }

    public MappingWrapper getConfigWrapper() {
        return this.configWrapper;
    }

    /**
     * Creates a set of SDO Types based on the query results and supplied config information
     */

    private void createDynamicTypes() {

        DataObjectUtil.initRuntime();

        Type root = SDOUtil.createType(typeHelper, getDefaultURI(), "DataGraphRoot", false);

        Iterator iter = getResultMetadata().iterator();
        while (iter.hasNext()) {

            ResultMetadata resultMetadata = (ResultMetadata) iter.next();

            // Create a Type for each Table represented in the ResultSet
            Iterator names = resultMetadata.getAllTablePropertyNames().iterator();
            while (names.hasNext()) {
                String tableName = (String) names.next();

                if (root.getProperty(tableName) == null) {
                    Type tableType = SDOUtil.createType(typeHelper, getDefaultURI(), tableName, false);
                    Property property = SDOUtil.createProperty(root, tableName, tableType);
                    SDOUtil.setMany(property, true);
                    SDOUtil.setContainment(property, true);
                }
            }

            // TODO tablePropertyMap is temporary until Tuscany-203 is fixed
            Map tablePropertyMap = new HashMap();

            for (int i = 1; i <= resultMetadata.getResultSetSize(); i++) {

                Property ref = root.getProperty(resultMetadata.getTablePropertyName(i));

                if (ref == null) {
                    throw new RuntimeException("Could not find table " + resultMetadata.getTablePropertyName(i) 
                            + " in the SDO model");
                }
                
                // TODO Temporary code to check to see if a property has already been added.
                // Replace when Tuscany-203 is fixed
                List addedProperties = (List) tablePropertyMap.get(ref.getName());
                if (addedProperties == null) {
                    addedProperties = new ArrayList();
                    tablePropertyMap.put(ref.getName(), addedProperties);
                }

 

                String columnName = resultMetadata.getColumnPropertyName(i);

                // TODO temporary check until Tuscany-203 is fixed
                if (!addedProperties.contains(columnName)) {
                    addedProperties.add(columnName);
                    Type atype = resultMetadata.getDataType(i);

                    SDOUtil.createProperty(ref.getType(), columnName, atype);

                }

            }
        }

        MappingWrapper wrapper = getConfigWrapper();
        Iterator i = getRelationships().iterator();
        while (i.hasNext()) {
            Relationship r = (Relationship) i.next();

            String parentName = wrapper.getTableTypeName(r.getPrimaryKeyTable());
            String childName = wrapper.getTableTypeName(r.getForeignKeyTable());

            if (parentName == null) {
                throw new RuntimeException("The parent table (" + r.getPrimaryKeyTable() 
                        + ") in relationship " + r.getName()
                        + " was not found in the mapping information.");
            } else if (childName == null) {
                throw new RuntimeException("The child table (" + r.getForeignKeyTable() 
                        + ") in relationship " + r.getName()
                        + " was not found in the mapping information.");
            }

            Property parentProperty = root.getProperty(parentName);
            Property childProperty = root.getProperty(childName);

            if (parentProperty == null) {
                throw new RuntimeException("The parent table (" + parentName + ") in relationship " 
                        + r.getName() + " was not found.");
            } else if (childProperty == null) {
                throw new RuntimeException("The child table (" + childName + ") in relationship " 
                        + r.getName() + " was not found.");
            }

            Type parent = parentProperty.getType();
            Type child = childProperty.getType();

            Property parentProp = SDOUtil.createProperty(parent, r.getName(), child);
            Property childProp = SDOUtil.createProperty(child, r.getName() + "_opposite", parent);
            SDOUtil.setOpposite(parentProp, childProp);
            SDOUtil.setOpposite(childProp, parentProp);
            SDOUtil.setMany(parentProp, r.isMany());
        }

        this.rootType = root;
    }

    private String getDefaultURI() {
        return "http:///org.apache.tuscany.das.rdb/das";
    }

    /**
     * Create a dynamic root Type to act as a container Type for a set of generated Types
     * 
     */
    private void createDynamicRoot() {
        Type root = SDOUtil.createType(typeHelper, getDefaultURI() + "/DataGraphRoot", "DataGraphRoot", false);

        List types = SDOUtil.getTypes(typeHelper, typeURI);
        if (types == null) {
            throw new RuntimeException("SDO Types have not been registered for URI " + typeURI);
        }

        Iterator i = types.iterator();
        while (i.hasNext()) {
            Type type = (Type) i.next();
            Property property = SDOUtil.createProperty(root, type.getName(), type);
            SDOUtil.setContainment(property, true);
            SDOUtil.setMany(property, true);
        }
        this.rootType = root;
    }

    public List getDefinedTypes() {
        if (this.typeURI == null) {
            return SDOUtil.getTypes(typeHelper, getDefaultURI());
        } 
            
        List types = SDOUtil.getTypes(typeHelper, typeURI);
        types.add(rootType);
        return types;
        
    }

}
