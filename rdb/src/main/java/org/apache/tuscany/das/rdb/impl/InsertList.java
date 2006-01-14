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
package org.apache.tuscany.das.rdb.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.util.DebugUtil;

/**
 * InsertList will sort ChangeOperation objects so that parents are inserted
 * before children
 * 
 * 
 */
public class InsertList {

    private HashMap opsByTableName = new HashMap();

    private ArrayList insertOperations = new ArrayList();

    private ArrayList order;

    private static final boolean debug = false;

    public void add(ChangeOperation op) {
        DebugUtil.debugln(getClass(), debug, "Adding insert operation ");
        if ((order.size() == 0) || (op.getTableName() == null)) {
            insertOperations.add(op);
        } else {
            String name = op.getTableName();
            ArrayList ops = (ArrayList) opsByTableName.get(name);
            if (ops == null)
                ops = new ArrayList();

            ops.add(op);
            opsByTableName.put(name, ops);
        }
    }

    public Collection getSortedList() {
        DebugUtil.debugln(getClass(), debug, "Getting sorted insert list");
        if ((order.size() > 0) && opsByTableName.keySet().size() > 0) {
            Iterator i = this.order.iterator();
            while (i.hasNext()) {
                String name = (String) i.next();
                DebugUtil.debugln(getClass(), debug,
                        "Adding operations for table " + name);
                // TODO - KJW added null check ... Brent to verify.
                if (opsByTableName.get(name) != null)
                    insertOperations.addAll((Collection) opsByTableName
                            .get(name));
            }
        }
        DebugUtil.debugln(getClass(), debug, "Returning "
                + insertOperations.size() + " insert operations");
        return insertOperations;
    }

    public void setOrder(ArrayList insertOrder) {
        this.order = insertOrder;
    }

}
