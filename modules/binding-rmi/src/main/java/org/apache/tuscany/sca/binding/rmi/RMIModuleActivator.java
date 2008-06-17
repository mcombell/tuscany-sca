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

package org.apache.tuscany.sca.binding.rmi;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.binding.rmi.xml.RMIBindingProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessorExtensionPoint;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.core.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.core.ModuleActivator;
import org.apache.tuscany.sca.invocation.MessageFactory;
import org.apache.tuscany.sca.policy.PolicyFactory;
import org.apache.tuscany.sca.provider.ProviderFactoryExtensionPoint;
import org.apache.tuscany.sca.rmi.RMIHost;

public class RMIModuleActivator implements ModuleActivator {

    public void start(ExtensionPointRegistry registry) {

        ModelFactoryExtensionPoint factories = registry.getExtensionPoint(ModelFactoryExtensionPoint.class);
        AssemblyFactory assemblyFactory = factories.getFactory(AssemblyFactory.class);
        PolicyFactory policyFactory = factories.getFactory(PolicyFactory.class);
        MessageFactory messageFactory = factories.getFactory(MessageFactory.class);
        
        StAXArtifactProcessorExtensionPoint processors = 
            registry.getExtensionPoint(StAXArtifactProcessorExtensionPoint.class);
        
        RMIHost rmiHost = registry.getExtensionPoint(RMIHost.class);
        RMIBindingFactory rmiFactory = new DefaultRMIBindingFactory();
        processors.addArtifactProcessor(new RMIBindingProcessor(assemblyFactory, policyFactory, rmiFactory));
        
        ProviderFactoryExtensionPoint providerFactories = registry.getExtensionPoint(ProviderFactoryExtensionPoint.class);
        providerFactories.addProviderFactory(new RMIBindingProviderFactory(messageFactory, rmiHost));
    }

    public void stop(ExtensionPointRegistry registry) {
    }

    public Object[] getExtensionPoints() {
        return null;
    }

}