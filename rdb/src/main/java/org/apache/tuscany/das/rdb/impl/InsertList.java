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
package org.apache.tuscany.das.rdb.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * InsertList will sort ChangeOperation objects so that parents are inserted before children
 * 
 * 
 */
public class InsertList {
    private final Logger logger = Logger.getLogger(InsertList.class);

    private Map opsByTableName = new HashMap();

    private List insertOperations = new ArrayList();

    private List order;

    public void add(ChangeOperation op) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Adding insert operation ");
        }

        // If nothing has been added yet, or this is no ordering, simply
        // add the operation to the list
        if ((order.size() == 0) || (op.getTableName() == null)) {
            insertOperations.add(op);
        } else {
            String name = op.getTableName();
            List ops = (List) opsByTableName.get(name);
            if (ops == null) {
                ops = new ArrayList();
            }

            ops.add(op);
            opsByTableName.put(name, ops);
        }
    }

    public Collection getSortedList() {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Getting sorted insert list");
        }

        if ((order.size() > 0) && opsByTableName.keySet().size() > 0) {
            Iterator i = this.order.iterator();
            while (i.hasNext()) {
                String name = (String) i.next();
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Adding operations for table " + name);
                }

                // A null here means a table is in the config but hasn't been changed here
                if (opsByTableName.get(name) != null) {
                    insertOperations.addAll((Collection) opsByTableName.get(name));
                }
            }
        }
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Returning " + insertOperations.size() + " insert operations");
        }

        return insertOperations;
    }

    public void setOrder(List insertOrder) {
        this.order = insertOrder;
    }

}
