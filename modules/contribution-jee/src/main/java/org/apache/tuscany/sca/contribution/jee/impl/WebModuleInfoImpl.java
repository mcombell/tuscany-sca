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
package org.apache.tuscany.sca.contribution.jee.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo;
import org.apache.tuscany.sca.contribution.jee.EnvEntryInfo;
import org.apache.tuscany.sca.contribution.jee.WebModuleInfo;

public class WebModuleInfoImpl implements WebModuleInfo {
    
    private URI uri;
    private String moduleName;
    private Collection<Class<?>> classesToScan = new ArrayList<Class<?>>();
    private Map<String, EjbReferenceInfo> ejbReferences = new HashMap<String, EjbReferenceInfo>();
    private Map<String, EnvEntryInfo> envEntries = new HashMap<String, EnvEntryInfo>();

    public WebModuleInfoImpl() {
    }
    public Collection<Class<?>> getClassesToScan() {
        return classesToScan;
    }

    public EjbReferenceInfo getEjbReference(String ejbRefName) {
        return ejbReferences.get(ejbRefName);
    }

    public Map<String, EjbReferenceInfo> getEjbReferences() {
        return ejbReferences;
    }

    public Map<String, EnvEntryInfo> getEnvEntries() {
        return envEntries;
    }

    public EnvEntryInfo getEnvEntry(String envEntryName) {
        return envEntries.get(envEntryName);
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    public URI getUri() {
        return uri;
    }
    
    public void setUri(URI uri) {
        this.uri = uri;
    }
}
