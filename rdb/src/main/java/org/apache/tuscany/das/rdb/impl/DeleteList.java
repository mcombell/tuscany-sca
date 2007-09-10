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

/**
 * DeleteList will sort delete operations so that child objects are deleted before their parents
 * 
 * 
 */
public class DeleteList {

    private Map opsByTableName = new HashMap();

    private List order;

    private List deleteOperations = new ArrayList();

    public DeleteList() {
        super();
    }

    public void add(ChangeOperation op) {
        if ((order.size() == 0) || (op.getTableName() == null)) {
            deleteOperations.add(op);
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
        if ((order.size() > 0) && (opsByTableName.keySet().size() > 0)) {
            Iterator i = this.order.iterator();
            while (i.hasNext()) {
                String name = (String) i.next();
                if (opsByTableName.get(name) != null) {
                    deleteOperations.addAll((Collection) opsByTableName.get(name));
                }
            }
        }

        return deleteOperations;
    }

    public void setOrder(List deleteOrder) {
        this.order = deleteOrder;
    }

}
