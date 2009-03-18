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
import java.util.HashMap;
import java.util.Map;

import org.apache.tuscany.sca.contribution.jee.EjbModuleInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEApplicationInfo;
import org.apache.tuscany.sca.contribution.jee.WebModuleInfo;

public class JavaEEApplicationInfoImpl implements JavaEEApplicationInfo {
    
    private URI uri;
    private String applicationName;
    private Map<String, EjbModuleInfo> ejbModuleInfos = new HashMap<String, EjbModuleInfo>();
    private Map<String, WebModuleInfo> webModuleInfos = new HashMap<String, WebModuleInfo>();
    

    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public EjbModuleInfo getEjbModuleInfo(String moduleName) {
        return ejbModuleInfos.get(moduleName);
    }

    public Map<String, EjbModuleInfo> getEjbModuleInfos() {
        return ejbModuleInfos;
    }

    public WebModuleInfo getWebModuleInfo(String moduleName) {
        return webModuleInfos.get(moduleName);
    }

    public Map<String, WebModuleInfo> getWebModuleInfos() {
        return webModuleInfos;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
