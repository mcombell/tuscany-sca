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

package org.apache.tuscany.sca.assembly.builder.impl;

import java.util.List;

import org.apache.tuscany.sca.assembly.Component;
import org.apache.tuscany.sca.assembly.ConfiguredOperation;
import org.apache.tuscany.sca.assembly.Implementation;
import org.apache.tuscany.sca.assembly.OperationsConfigurator;
import org.apache.tuscany.sca.policy.Intent;
import org.apache.tuscany.sca.policy.IntentAttachPoint;
import org.apache.tuscany.sca.policy.PolicySet;
import org.apache.tuscany.sca.policy.PolicySetAttachPoint;
import org.apache.tuscany.sca.policy.util.PolicyValidationException;

/**
 * Policy computation methods pertaining to computing Implementation Policies
 */
public class ImplementationPolicyComputer extends PolicyComputer {
    
    public ImplementationPolicyComputer(List<PolicySet> domainPolicySets) {
        super(domainPolicySets);
    }
    
    public void computeImplementationIntentsAndPolicySets(Implementation implementation, Component parent)  
                                                                throws PolicyValidationException, PolicyComputationException {
        if ( implementation instanceof PolicySetAttachPoint ) {
            PolicySetAttachPoint policiedImplementation = (PolicySetAttachPoint)implementation;
            //since for an implementation the component has its policy intents and policysets its possible
            //that there are some intents there that does not constrain the implementation.. so prune 
            List<Intent> prunedIntents = computeInheritableIntents(policiedImplementation.getType(), 
                                                                   parent.getRequiredIntents());
            parent.getRequiredIntents().clear();
            parent.getRequiredIntents().addAll(prunedIntents);
            computeIntents(parent);
            trimInherentlyProvidedIntents(policiedImplementation.getType(), 
                                          parent.getRequiredIntents());
            
            computeIntentsForOperations((OperationsConfigurator)parent,
                                        (IntentAttachPoint)implementation,
                                        parent.getRequiredIntents());
            
            
            List<PolicySet> prunedPolicySets = computeInheritablePolicySets(parent, 
                                                                            policiedImplementation.getType(), 
                                                                            parent.getPolicySets());
            parent.getPolicySets().clear();
            parent.getPolicySets().addAll(prunedPolicySets);
            computePolicySets(parent);
            computePolicySetsForOperations(parent, 
                                           (OperationsConfigurator)parent, 
                                           (PolicySetAttachPoint)implementation);
            
            determineApplicableImplementationPolicySets(parent);
        }
    }
    
    private void determineApplicableImplementationPolicySets(Component component) throws PolicyComputationException {
        if ( component.getImplementation() instanceof PolicySetAttachPoint ) {
            PolicySetAttachPoint policiedImplementation = (PolicySetAttachPoint)component.getImplementation();
           
            //trim intents specified in operations.  First check for policysets specified on the operation
            //and then in the parent implementation
            if ( component instanceof OperationsConfigurator ) {
                OperationsConfigurator opConfigurator = (OperationsConfigurator)component;
                
                for ( ConfiguredOperation confOp : opConfigurator.getConfiguredOperations() ) {
                    trimProvidedIntents(confOp.getRequiredIntents(), confOp.getPolicySets());
                    trimProvidedIntents(confOp.getRequiredIntents(), component.getPolicySets());
                    
                    if (domainPolicySets != null) {
                        determineApplicableDomainPolicySets(component.getImplementation(), 
                                                            confOp,
                                                            policiedImplementation.getType());
    
                        if (confOp.getRequiredIntents().size() > 0) {
                            new PolicyComputationException("The following are unfulfilled intents for operations configured in "
                                    + "component implementation - " + component.getName() + "\nUnfulfilled Intents = " +
                                    confOp.getRequiredIntents());
                        }
                    }
                }
            }
                
            trimProvidedIntents(component.getRequiredIntents(), component.getPolicySets());
                
            //determine additional policysets that match remaining intents
            //if there are intents that are not provided by any policy set throw a warning
            //TODO: resolved to domain policy registry and attach suitable policy sets to the implementation
            //...for now using the SCA Definitions instead of registry
            if ( domainPolicySets != null)  {
                determineApplicableDomainPolicySets(component, 
                                                    component,
                                                    policiedImplementation.getType());
                                                    
                if (component.getRequiredIntents().size() > 0) {
                    throw new PolicyComputationException("The following are unfulfilled intents for component implementation - " + component
                        .getName() + "\nUnfulfilled Intents = " + component.getRequiredIntents());
                }
            }
        }
    }
    
    
    
}