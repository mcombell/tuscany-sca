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
package org.apache.tuscany.das.rdb.util;

import java.util.Iterator;
import java.util.List;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.ChangeSummary.Setting;
import commonj.sdo.helper.DataFactory;

/**
 */
public final class DataObjectUtil {

    private DataObjectUtil() {
        
    }
    
    // Utilities
    public static DataObject getRestoredCopy(DataObject changedDO) {
        DataObject changedCopy = getCopy(changedDO);
        restoreAttributeValues(changedCopy, changedDO);
        return changedCopy;
    }

    public static DataObject getCopy(DataObject original) {

        DataObject copy = DataFactory.INSTANCE.create(original.getType());

        // Fill in values
        Iterator i = original.getType().getProperties().iterator();
        while (i.hasNext()) {
            Property feature = (Property) i.next();
            copy.set(feature, original.get(feature));
        }
        return copy;
    }

    /**
     * @param changedCopy
     * @return
     */
    private static void restoreAttributeValues(DataObject changedCopy, DataObject changedDO) {

        ChangeSummary changeSummary = changedDO.getDataGraph().getChangeSummary();
        List changes = changeSummary.getOldValues(changedDO);
        if (changes == null) {
            return;
        }

        Iterator i = changes.iterator();
        while (i.hasNext()) {
            Setting s = (Setting) i.next();
            if (s.getProperty().getType().isDataType()) {
                changedCopy.set(s.getProperty(), s.getValue());
            }
        }
    }

}
