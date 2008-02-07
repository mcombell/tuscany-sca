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

import java.util.List;
import java.util.Map;

import org.apache.tuscany.sca.policy.Intent;
import org.apache.tuscany.sca.policy.PolicySet;

/**
 * Utitlity methods to deal with PolicyHandlers
 */
public class PolicyHandlerUtils {
    public static PolicyHandler findPolicyHandler(PolicySet policySet, 
                                                 Map<ClassLoader, List<PolicyHandlerTuple>> policyHandlerClassNames) 
                                throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        
        PolicyHandler handler = null;
        
        for (ClassLoader classLoader : policyHandlerClassNames.keySet()) {
            for ( PolicyHandlerTuple handlerTuple : policyHandlerClassNames.get(classLoader) ) {
                for ( Intent intent : policySet.getProvidedIntents() ) {
                    if ( intent.getName().equals(handlerTuple.getProvidedIntentName())) {
                        for ( Object policy : policySet.getPolicies() ) {
                            if ( policy.getClass().getName().equals(handlerTuple.getPolicyModelClassName())) {
                                handler = 
                                    (PolicyHandler)Class.forName(handlerTuple.getPolicyHandlerClassName(), 
                                                                 true, 
                                                                 classLoader).newInstance();
                                    handler.setApplicablePolicySet(policySet);
                                    return handler;
                            }
                        }
                    }
                }
            }
        }
        
        return handler;
    } 
   

}