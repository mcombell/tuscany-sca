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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

public class RowObjects {
    private final Logger logger = Logger.getLogger(RowObjects.class);

    private Map objectsByTableName;

    private List tableObjects;

    private final GraphBuilderMetadata metadata;

    private final TableRegistry registry;

    public RowObjects(GraphBuilderMetadata metadata, TableRegistry registry) {
        objectsByTableName = new HashMap();
        tableObjects = new ArrayList();
        this.metadata = metadata;
        this.registry = registry;
    }

    public void put(String key, DataObject value) {
        objectsByTableName.put(key, value);
        tableObjects.add(value);
    }

    public DataObject get(String tablePropertyName) {
        return (DataObject) objectsByTableName.get(tablePropertyName);
    }

    void processRelationships() {
        MappingWrapper wrapper = metadata.getConfigWrapper();
        if (wrapper.hasRecursiveRelationships()) {
            processRecursiveRelationships(wrapper);
            return;
        }

        Iterator i = metadata.getRelationships().iterator();
        while (i.hasNext()) {
            Relationship r = (Relationship) i.next();

            DataObject parentTable = get(wrapper.getTableTypeName(r.getPrimaryKeyTable()));
            DataObject childTable = get(wrapper.getTableTypeName(r.getForeignKeyTable()));

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Parent table: " + parentTable);
                this.logger.debug("Child table: " + childTable);
            }
            if ((parentTable == null) || (childTable == null)) {
                continue;
            }

            Property p = parentTable.getType().getProperty(r.getName());
            setOrAdd(parentTable, childTable, p);

        }
    }

    private void processRecursiveRelationships(MappingWrapper wrapper) {
        Iterator i = tableObjects.iterator();
        while (i.hasNext()) {
            DataObject table = (DataObject) i.next();

            Iterator relationships = wrapper.getRelationshipsByChildTable(table.getType().getName()).iterator();
            while (relationships.hasNext()) {
                Relationship r = (Relationship) relationships.next();

                DataObject parentTable = findParentTable(table, r, wrapper);

                if (parentTable == null) {
                    continue;
                }

                Property p = parentTable.getType().getProperty(r.getName());
                setOrAdd(parentTable, table, p);
            }

        }
    }

    private void setOrAdd(DataObject parent, DataObject child, Property p) {
        if (p.isMany()) {
            parent.getList(p).add(child);
        } else {
            parent.set(p, child);
        }
    }

    private DataObject findParentTable(DataObject childTable, Relationship r, MappingWrapper wrapper) {

        List fkValue = new ArrayList();
        Iterator keyPairs = r.getKeyPair().iterator();
        while (keyPairs.hasNext()) {
            KeyPair pair = (KeyPair) keyPairs.next();
            String childProperty = wrapper.getColumnPropertyName(r.getPrimaryKeyTable(), pair.getForeignKeyColumn());

            Property p = childTable.getType().getProperty(childProperty);
            fkValue.add(childTable.get(p));
        }

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Trying to find parent of " + r.getForeignKeyTable() + " with FK " + fkValue);
        }

        DataObject parentTable = registry.get(r.getPrimaryKeyTable(), fkValue);

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Parent table from registry: " + parentTable);
        }

        return parentTable;
    }

}
