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

package echo.module;

import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessorExtensionPoint;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.core.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.core.ModuleActivator;
import org.apache.tuscany.sca.invocation.MessageFactory;
import org.apache.tuscany.sca.provider.ProviderFactoryExtensionPoint;

import echo.DefaultEchoBindingFactory;
import echo.EchoBindingFactory;
import echo.impl.EchoBindingProcessor;
import echo.provider.EchoBindingProviderFactory;
import echo.server.EchoServer;

/**
 * A module activator for the sample Echo binding extension.
 */
public class EchoModuleActivator implements ModuleActivator {
    
    public Object[] getExtensionPoints() {
        // No extensionPoints being contributed here
        return null;
    }

    public void start(ExtensionPointRegistry registry) {
        
        ModelFactoryExtensionPoint factories = registry.getExtensionPoint(ModelFactoryExtensionPoint.class);
        MessageFactory messageFactory = factories.getFactory(MessageFactory.class);
        
        // Create the Echo model factory
        EchoBindingFactory echoFactory = new DefaultEchoBindingFactory();

        // Add the EchoProcessor extension
        StAXArtifactProcessorExtensionPoint processors = registry.getExtensionPoint(StAXArtifactProcessorExtensionPoint.class);
        EchoBindingProcessor echoBindingProcessor = new EchoBindingProcessor(echoFactory);
        processors.addArtifactProcessor(echoBindingProcessor);
        
        ProviderFactoryExtensionPoint providerFactories = registry.getExtensionPoint(ProviderFactoryExtensionPoint.class);
        providerFactories.addProviderFactory(new EchoBindingProviderFactory(messageFactory));
       
        // Start the Echo server
        EchoServer.start();
    }

    public void stop(ExtensionPointRegistry registry) {
        EchoServer.stop();
    }

}