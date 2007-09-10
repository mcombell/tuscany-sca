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

import java.util.Collection;
import java.util.Iterator;

/**
 */
public final class CollectionsUtil {

    private CollectionsUtil() {
        
    }
    
    // Utilities
    public static boolean disjoint(Collection c1, Collection c2) {
      
        if (c1.size() > c2.size()) {
          Collection c = c1;
          c1 = c2;
          c2 = c;
        }
        for (Iterator iterator = c2.iterator(); iterator.hasNext();) {
          Object o = (Object)iterator.next();
          if (c1.contains(o)) {
            return false;
          }
        }
        return true;
    }

}
