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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @version $Rev$ $Date$
 */
public class EjbInfo {
    public enum EjbType {SESSION_STATELESS, SESSION_STATEFUL, SESSION_UNKNOWN, MESSAGE_DRIVEN};
    public String beanName;
    public Class<?> beanClass;
    public String mappedName;
    public EjbType ejbType;
    public Collection<Class<?>> businessRemote = new ArrayList<Class<?>>();
    public Collection<Class<?>> businessLocal = new ArrayList<Class<?>>();
    public Map<String, EjbReferenceInfo> ejbReferences = new HashMap<String, EjbReferenceInfo>();
    public Map<String, EnvEntryInfo> envEntries = new HashMap<String, EnvEntryInfo>();
}
