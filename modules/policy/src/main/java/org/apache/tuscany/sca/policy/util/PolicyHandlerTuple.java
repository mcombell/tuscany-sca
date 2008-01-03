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

package org.apache.tuscany.sca.policy.util;

import javax.xml.namespace.QName;

/**
 * PolicyHanlder tuples stored in policy handler services files
 */
public class PolicyHandlerTuple {
    private String policyHandlerClassName;
    private QName providedIntentName;
    private String policyModelClassName;
    
    public PolicyHandlerTuple(String handlerClassName,
                              QName providedIntentName,
                              String policyModelClassName) {
        this.policyHandlerClassName = handlerClassName;
        this.providedIntentName = providedIntentName;
        this.policyModelClassName = policyModelClassName;
    }
    
    public String getPolicyHandlerClassName() {
        return policyHandlerClassName;
    }
    public void setPolicyHandlerClassName(String policyHandlerClassName) {
        this.policyHandlerClassName = policyHandlerClassName;
    }
    public String getPolicyModelClassName() {
        return policyModelClassName;
    }
    public void setPolicyModelClassName(String policyModelClassName) {
        this.policyModelClassName = policyModelClassName;
    }
    public QName getProvidedIntentName() {
        return providedIntentName;
    }
    public void setProvidedIntentName(QName providedIntentName) {
        this.providedIntentName = providedIntentName;
    }
}