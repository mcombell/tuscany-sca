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

import java.math.BigDecimal;

public class ManagedParameterImpl extends ParameterImpl {

    public void setValue(Object oldValue) {
        this.value = updateValue(oldValue);
    }

    private Object updateValue(Object oldValue) {
        if (oldValue instanceof Integer) {
            return Integer.valueOf(((Integer) oldValue).intValue() + 1);
        } else if (oldValue instanceof BigDecimal) {
            return ((BigDecimal) oldValue).add(new BigDecimal(1));
        } else {
            throw new RuntimeException("Unsupported type for managed column: " + oldValue.getClass().getName());
        }
    }

}
