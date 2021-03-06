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

package org.apache.tuscany.sca.policy.identity;

import javax.xml.namespace.QName;

/**
 * Models the SCA Implementation Security Policy Assertion for Security Identity.
 * 
 * @version $Rev$ $Date$
 */
public class SecurityIdentityPolicy {
    private final static String SCA11_NS = "http://docs.oasis-open.org/ns/opencsa/sca/200903";    
    public static final QName NAME = new QName(SCA11_NS, "securityIdentity");

    private boolean useCallerIdentity;

    private String runAsRole;

    public SecurityIdentityPolicy() {
    }

    public boolean isUnresolved() {
        return false;
    }

    public void setUnresolved(boolean unresolved) {
    }

    public QName getSchemaName() {
        return NAME;
    }

    public boolean isUseCallerIdentity() {
        return useCallerIdentity;
    }

    public void setUseCallerIdentity(boolean useCallerIdentity) {
        this.useCallerIdentity = useCallerIdentity;
    }

    public String getRunAsRole() {
        return runAsRole;
    }

    public void setRunAsRole(String runAsRole) {
        this.runAsRole = runAsRole;
    }

    @Override
    public String toString() {
        if (useCallerIdentity) {
            return "useCallerIdentity";
        }
        return "runAs " + runAsRole;
    }
}
