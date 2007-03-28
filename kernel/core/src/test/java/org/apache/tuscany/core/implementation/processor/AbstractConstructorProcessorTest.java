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
package org.apache.tuscany.core.implementation.processor;

import java.lang.reflect.Constructor;

import junit.framework.TestCase;

import org.apache.tuscany.core.idl.java.JavaInterfaceProcessorRegistryImpl;
import org.apache.tuscany.core.monitor.NullMonitorFactory;
import org.apache.tuscany.spi.deployer.DeploymentContext;
import org.apache.tuscany.spi.implementation.java.ConstructorDefinition;
import org.apache.tuscany.spi.implementation.java.JavaMappedProperty;
import org.apache.tuscany.spi.implementation.java.JavaMappedReference;
import org.apache.tuscany.spi.implementation.java.JavaMappedService;
import org.apache.tuscany.spi.implementation.java.Parameter;
import org.apache.tuscany.spi.implementation.java.PojoComponentType;
import org.apache.tuscany.spi.implementation.java.ProcessingException;

/**
 * Base class to simulate the processor sequences
 * 
 * @version $Rev$ $Date$
 */
public class AbstractConstructorProcessorTest extends TestCase {
    protected ConstructorProcessor constructorProcessor;
    private ReferenceProcessor referenceProcessor = new ReferenceProcessor();
    private PropertyProcessor propertyProcessor = new PropertyProcessor();
    private ResourceProcessor resourceProcessor = new ResourceProcessor();
    private MonitorProcessor monitorProcessor = new MonitorProcessor(new NullMonitorFactory());


    protected AbstractConstructorProcessorTest() {
        constructorProcessor = new ConstructorProcessor();
        referenceProcessor = new ReferenceProcessor();
        referenceProcessor.setInterfaceProcessorRegistry(new JavaInterfaceProcessorRegistryImpl());
        propertyProcessor = new PropertyProcessor();
    }

    protected <T> void visitConstructor(Constructor<T> constructor,
                                        PojoComponentType<JavaMappedService, 
                                        JavaMappedReference, JavaMappedProperty<?>> type,
                                        DeploymentContext context) throws ProcessingException {
        constructorProcessor.visitConstructor(constructor, type, context);
        ConstructorDefinition<?> definition = type.getConstructorDefinition();
        if (definition == null) {
            definition = new ConstructorDefinition<T>(constructor);
            type.getConstructors().put(constructor, definition);
        }
        Parameter[] parameters = definition.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            referenceProcessor.visitConstructorParameter(parameters[i], type, null);
            propertyProcessor.visitConstructorParameter(parameters[i], type, null);
            resourceProcessor.visitConstructorParameter(parameters[i], type, null);
            monitorProcessor.visitConstructorParameter(parameters[i], type, null);
        }
    }

}
