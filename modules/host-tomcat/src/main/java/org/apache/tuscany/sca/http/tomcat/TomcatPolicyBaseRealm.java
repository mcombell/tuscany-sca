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

package org.apache.tuscany.sca.http.tomcat;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.realm.RealmBase;
import org.apache.tuscany.sca.host.http.UserContext;

public class TomcatPolicyBaseRealm extends RealmBase {
    private static final String REALM_NAME = "Tuscany Realm";
    
    private Map<String, UserContext> userMap = new HashMap<String, UserContext>();
    
    public TomcatPolicyBaseRealm(List<UserContext> users) {
        for(UserContext userContext : users) {
            userMap.put(userContext.getUsername(), userContext);
        }
    }

    @Override
    protected String getName() {
        return REALM_NAME;
    }

    @Override
    protected String getPassword(String username) {
        UserContext userContext = userMap.get(username);
        
        if (userContext != null) {
            return userContext.getPassword();
        }
        
        return null;
    }

    @Override
    protected Principal getPrincipal(String username) {
        UserContext userContext = userMap.get(username);
        
        if (userContext != null) {
            Principal principal = new TuscanyPrincipal(userContext.getUsername());
            return principal;
        }
        
        return null;
    }
    
    @Override
    public boolean hasRole(java.security.Principal principal, java.lang.String role) {
        UserContext userContext = userMap.get(principal.getName());
        
        if (userContext != null) {
            if (userContext.getRoles().contains(role)) {
                return true;
            }
        }
        
        return false;        
    }

    
    class TuscanyPrincipal implements java.security.Principal {
        private final String username;
        
        TuscanyPrincipal(String username) {
            this.username = username;
        }

        public String getName() {
            return this.username;
        }
        
    }
}
