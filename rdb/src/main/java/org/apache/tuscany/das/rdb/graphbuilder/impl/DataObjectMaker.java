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

import java.util.Iterator;

import org.apache.log4j.Logger;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;

public class DataObjectMaker {

    private final DataObject rootObject;

    private final Logger logger = Logger.getLogger(DataObjectMaker.class);

    public DataObjectMaker(DataObject root) {
        this.rootObject = root;
    }

    /**
     * @param tableData
     * @return
     */
    public DataObject createAndAddDataObject(TableData tableData, ResultMetadata resultMetadata) {
        // Get a Type from the package and create a standalone DataObject

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Looking for Type for " + tableData.getTableName());
        }

        Type tableClass = findTableTypeByPropertyName(tableData.getTableName());

        if (tableClass == null) {
            throw new RuntimeException("An SDO Type with name " + tableData.getTableName() + " was not found");
        }

        DataObject obj = DataFactory.INSTANCE.create(tableClass);

        // Now, check to see if the root data object has a containment reference
        // to this EClass. If so, add it to the graph. If not, it will be taken
        // care
        // of when we process relationships

        Iterator i = this.rootObject.getType().getProperties().iterator();
        while (i.hasNext()) {
            Property p = (Property) i.next();

            if (p.isContainment() && p.getType().equals(tableClass)) {
                if (p.isMany()) {
                    rootObject.getList(p).add(obj);
                } else {
                    this.rootObject.set(p, obj);
                }
            }

        }

        Iterator columnNames = resultMetadata.getPropertyNames(tableData.getTableName()).iterator();
        while (columnNames.hasNext()) {
            String propertyName = (String) columnNames.next();

            Property p = findProperty(obj.getType(), propertyName);
            if (p == null) {
                throw new RuntimeException("Type " + obj.getType().getName() 
                        + " does not contain a property named " + propertyName);
            }

            Object value = tableData.getColumnData(propertyName);

            obj.set(p, value);
        }

        return obj;
    }

    // temporary, ignoring case
    private Property findProperty(Type type, String columnName) {
        Iterator properties = type.getProperties().iterator();
        while (properties.hasNext()) {
            Property p = (Property) properties.next();
            if (columnName.equalsIgnoreCase(p.getName())) {
                return p;
            }
        }
        return null;
    }

    private Type findTableTypeByPropertyName(String tableName) {
        Iterator i = rootObject.getType().getProperties().iterator();
        while (i.hasNext()) {
            Property p = (Property) i.next();
            if (tableName.equals(p.getType().getName())) {
                return p.getType();
            }
        }

        return null;
    }

}
