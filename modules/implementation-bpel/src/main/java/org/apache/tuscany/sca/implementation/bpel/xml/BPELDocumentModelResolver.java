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

package org.apache.tuscany.sca.implementation.bpel.xml;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.Import;
import org.apache.tuscany.sca.contribution.namespace.NamespaceImport;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.core.FactoryExtensionPoint;
import org.apache.tuscany.sca.implementation.bpel.BPELProcessDefinition;

/**
 * A Model Resolver for BPEL process models.
 *
 * @version $Rev$ $Date$
 */
public class BPELDocumentModelResolver implements ModelResolver {

    private Contribution contribution;
    private Map<QName, BPELProcessDefinition> map = new HashMap<QName, BPELProcessDefinition>();
    
    public BPELDocumentModelResolver(Contribution contribution, FactoryExtensionPoint modelFactories) {
        this.contribution = contribution;
    }

    public void addModel(Object resolved) {
        BPELProcessDefinition process = (BPELProcessDefinition)resolved;
        map.put(process.getName(), process);
    }
    
    public Object removeModel(Object resolved) {
        return map.remove(((BPELProcessDefinition)resolved).getName());
    }
    
    public <T> T resolveModel(Class<T> modelClass, T unresolved) {
        
        // Lookup a definition for the given namespace
        QName qname = ((BPELProcessDefinition)unresolved).getName();
        BPELProcessDefinition resolved = (BPELProcessDefinition) map.get(qname);
        if (resolved != null) {
            return modelClass.cast(resolved);
        }
        
        // No definition found, delegate the resolution to the imports
        for (Import import_ : this.contribution.getImports()) {
            if (import_ instanceof NamespaceImport) {
                NamespaceImport namespaceImport = (NamespaceImport)import_;
                if (namespaceImport.getNamespace().equals(qname.getNamespaceURI())) {
                    
                    // Delegate the resolution to the import resolver
                    resolved = namespaceImport.getModelResolver().resolveModel(BPELProcessDefinition.class, (BPELProcessDefinition)unresolved);
                    if (!resolved.isUnresolved()) {
                        return modelClass.cast(resolved);
                    }
                }
            }
        }
        return (T)unresolved;
    }
    
}
