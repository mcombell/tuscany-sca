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

import org.apache.tuscany.sca.assembly.Binding;
import org.apache.tuscany.sca.assembly.Implementation;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.policy.security.jaas.JaasAuthenticationImplementationPolicyProvider;
import org.apache.tuscany.sca.policy.security.jaas.JaasAuthenticationPolicy;
import org.apache.tuscany.sca.policy.security.jaas.JaasAuthenticationServicePolicyProvider;
import org.apache.tuscany.sca.provider.PolicyProvider;
import org.apache.tuscany.sca.provider.PolicyProviderFactory;
import org.apache.tuscany.sca.runtime.RuntimeComponent;
import org.apache.tuscany.sca.runtime.RuntimeComponentReference;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;

/**
 * @version $Rev$ $Date$
 */
public class LDAPRealmAuthenticationPolicyProviderFactory implements PolicyProviderFactory<LDAPRealmAuthenticationPolicy> {

    public LDAPRealmAuthenticationPolicyProviderFactory(ExtensionPointRegistry registry) {
        super();
    }

    public Class<LDAPRealmAuthenticationPolicy> getModelType() {
        return LDAPRealmAuthenticationPolicy.class;
    }

    public PolicyProvider createImplementationPolicyProvider(RuntimeComponent component, Implementation implementation) {
        return new LDAPRealmAuthenticationImplementationPolicyProvider(component, implementation);
    }

    public PolicyProvider createReferencePolicyProvider(RuntimeComponent component,
                                                        RuntimeComponentReference reference,
                                                        Binding binding) {
        return null;
    }

    public PolicyProvider createServicePolicyProvider(RuntimeComponent component,
                                                      RuntimeComponentService service,
                                                      Binding binding) {
        return new LDAPRealmAuthenticationServicePolicyProvider(component, service, binding);
    }


}
