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

package org.apache.tuscany.sca.policy.security.http;

import java.util.List;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

import org.apache.tuscany.sca.invocation.Interceptor;
import org.apache.tuscany.sca.invocation.Invoker;
import org.apache.tuscany.sca.invocation.Message;
import org.apache.tuscany.sca.policy.security.http.util.HttpSecurityUtil;
import org.osoa.sca.ServiceRuntimeException;

/**
 * @version $Rev$ $Date$
 */
public class LDAPRealmAuthenticationInterceptor  implements Interceptor {
    private List<LDAPRealmAuthenticationPolicy> authenticationPolicies;
    private Invoker next;

    public LDAPRealmAuthenticationInterceptor(List<LDAPRealmAuthenticationPolicy> authenticationPolicies) {
        super();
        this.authenticationPolicies = authenticationPolicies;
    }

    public Invoker getNext() {
        return next;
    }

    public void setNext(Invoker next) {
        this.next = next;
    }

    public Message invoke(Message msg) {
        try {
            for (LDAPRealmAuthenticationPolicy policy : authenticationPolicies) {
                Subject subject = HttpSecurityUtil.getSubject(msg);
                CallbackHandler callbackHandler = new LDAPRealmAuthenticationCallbackHandler(subject);
                LoginContext lc = new LoginContext(policy.getRealmConfigurationName(), callbackHandler);
                lc.login();
            }
        } catch (Exception e) {
            throw new ServiceRuntimeException(e);
        }
        return getNext().invoke(msg);
    }

}
