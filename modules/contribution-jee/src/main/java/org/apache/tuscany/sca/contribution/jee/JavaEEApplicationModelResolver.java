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

package org.apache.tuscany.sca.contribution.jee;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;

/**
 * A Model Resolver for JavaEEApplicationInfo models.
 *
 * @version $Rev$ $Date$
 */
public class JavaEEApplicationModelResolver implements ModelResolver {

    private Map<URI, JavaEEApplicationInfo> map = new HashMap<URI, JavaEEApplicationInfo>();
    private Contribution contribution;
    
    public JavaEEApplicationModelResolver(Contribution contribution, ModelFactoryExtensionPoint modelFactories) {
        this.contribution = contribution;
    }

    public void addModel(Object resolved) {
        JavaEEApplicationInfo jeeApp = (JavaEEApplicationInfo)resolved;
        map.put(jeeApp.getUri(), jeeApp);
    }
    
    public Object removeModel(Object resolved) {
        return map.remove(((JavaEEApplicationInfo)resolved).getUri());
    }
    
    public <T> T resolveModel(Class<T> modelClass, T unresolved) {
        // Lookup a JavaEEApplicationInfo for the given URI
        URI uri = ((JavaEEApplicationInfo)unresolved).getUri();
        if (uri != null) {
            JavaEEApplicationInfo resolved = (JavaEEApplicationInfo) map.get(uri);
            if (resolved != null) {
                return modelClass.cast(resolved);
            } else {
                uri = URI.create("");
                resolved = (JavaEEApplicationInfo) map.get(uri);
                if (resolved != null) {
                    return modelClass.cast(resolved);
                }
            }
        }
        return unresolved;        
    }
}
