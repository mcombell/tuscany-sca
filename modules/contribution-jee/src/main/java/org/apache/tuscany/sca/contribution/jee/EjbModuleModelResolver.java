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
 * A Model Resolver for EjbModule models.
 *
 * @version $Rev$ $Date$
 */
public class EjbModuleModelResolver implements ModelResolver {

    private Map<URI, EjbModuleInfo> map = new HashMap<URI, EjbModuleInfo>();
    private Contribution contribution;
    
    public EjbModuleModelResolver(Contribution contribution, ModelFactoryExtensionPoint modelFactories) {
        this.contribution = contribution;
    }

    public void addModel(Object resolved) {
        EjbModuleInfo ejbModule = (EjbModuleInfo)resolved;
        map.put(ejbModule.getUri(), ejbModule);
    }
    
    public Object removeModel(Object resolved) {
        return map.remove(((EjbModuleInfo)resolved).getUri());
    }
    
    public <T> T resolveModel(Class<T> modelClass, T unresolved) {
        // Lookup a EjbModuleInfo for the given URI
        URI uri = ((EjbModuleInfo)unresolved).getUri();
        if (uri != null) {
            EjbModuleInfo resolved = (EjbModuleInfo) map.get(uri);
            if (resolved != null) {
                return modelClass.cast(resolved);
            } else {
                uri = URI.create("");
                resolved = (EjbModuleInfo) map.get(uri);
                if (resolved != null) {
                    return modelClass.cast(resolved);
                }
            }
        }
        return unresolved;        
    }
}
