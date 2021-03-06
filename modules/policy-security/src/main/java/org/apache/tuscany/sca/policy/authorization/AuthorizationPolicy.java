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

package org.apache.tuscany.sca.policy.authorization;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

/**
 * Models the SCA Implementation Security Policy Assertion for Authorization.
 * 
 * @version $Rev$ $Date$
 */
public class AuthorizationPolicy {
    private final static String SCA11_NS = "http://docs.oasis-open.org/ns/opencsa/sca/200903";
    // private final static String SCA10_TUSCANY_NS = "http://tuscany.apache.org/xmlns/sca/1.1";
    public static final QName NAME = new QName(SCA11_NS, "authorization");

    public static enum AcessControl {
        permitAll, denyAll, allow
    };

    private List<String> roleNames = new ArrayList<String>();

    public AuthorizationPolicy() {
    }

    private AcessControl accessControl;

    public AcessControl getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(AcessControl accessControl) {
        this.accessControl = accessControl;
    }

    public List<String> getRoleNames() {
        if (accessControl == AcessControl.allow) {
            return roleNames;
        } else {
            throw new IllegalArgumentException("Role names are only available for 'allow'");
        }
    }

    public boolean isUnresolved() {
        return false;
    }

    public void setUnresolved(boolean unresolved) {
    }

    public QName getSchemaName() {
        return NAME;
    }

    @Override
    public String toString() {
        if (accessControl == AcessControl.allow) {
            return accessControl.name() + " " + roleNames;
        }
        return accessControl.name();
    }

}
