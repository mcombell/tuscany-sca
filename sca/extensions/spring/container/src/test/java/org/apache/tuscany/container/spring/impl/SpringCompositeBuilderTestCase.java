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
package org.apache.tuscany.container.spring.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.tuscany.spi.builder.BuilderRegistry;
import org.apache.tuscany.spi.builder.Connector;
import org.apache.tuscany.spi.component.CompositeComponent;
import org.apache.tuscany.spi.component.Service;
import org.apache.tuscany.spi.deployer.DeploymentContext;
import org.apache.tuscany.spi.extension.ServiceBindingExtension;
import org.apache.tuscany.spi.model.ServiceDefinition;
import org.apache.tuscany.spi.model.ComponentDefinition;
import org.apache.tuscany.spi.model.Property;
import org.apache.tuscany.spi.wire.InboundWire;
import org.apache.tuscany.spi.wire.OutboundWire;
import org.apache.tuscany.spi.wire.WireService;

import junit.framework.TestCase;
import org.apache.tuscany.container.spring.mock.TestBean;
import org.apache.tuscany.container.spring.model.SpringComponentType;
import org.apache.tuscany.container.spring.model.SpringImplementation;
import org.apache.tuscany.test.ArtifactFactory;
import org.apache.tuscany.test.binding.TestBindingDefinition;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 * @version $$Rev$$ $$Date$$
 */
public class SpringCompositeBuilderTestCase extends TestCase {
    private ComponentDefinition<SpringImplementation> definition;

    /**
     * Verifies that the builder calls back into the registry to load serviceBindings and wires them to bean targets
     * when no <code>sca:service</code> tag is specified in the Spring application.xml
     */
    @SuppressWarnings("unchecked")
    public void testImplicitServiceWiring() throws Exception {
        // Create a service instance that the mock builder registry will return
        WireService wireService = ArtifactFactory.createWireService();
        ServiceBindingExtension binding =
            new ServiceBindingExtension("fooServiceBinding", null) {
                public QName getBindingType() {
                    return null;
                }
            };
        InboundWire inboundWire = ArtifactFactory.createLocalInboundWire("fooServiceBinding", TestBean.class);
        OutboundWire outboundWire = ArtifactFactory.createLocalOutboundWire("fooServiceBinding", TestBean.class);
        ArtifactFactory.terminateWire(outboundWire);
        binding.setInboundWire(inboundWire);
        binding.setOutboundWire(outboundWire);
        inboundWire.setContainer(binding);
        outboundWire.setContainer(binding);
        Connector connector = ArtifactFactory.createConnector();
        connector.connect(inboundWire, outboundWire, true);

        Service service = ArtifactFactory.createService("fooServiceBinding", null, outboundWire.getServiceContract());
        service.addServiceBinding(binding);
        // Configure the mock builder registry
        BuilderRegistry registry = createMock(BuilderRegistry.class);
        expect(registry.build(isA(CompositeComponent.class),
            isA(ServiceDefinition.class),
            isA(DeploymentContext.class))).andStubReturn(service);
        replay(registry);

        // Test the SpringCompositeBuilder
        SpringCompositeBuilder builder = new SpringCompositeBuilder();
        builder.setWireService(wireService);
        builder.setBuilderRegistry(registry);
        CompositeComponent parent = createNiceMock(CompositeComponent.class);
        DeploymentContext context = createNiceMock(DeploymentContext.class);
        CompositeComponent component = (CompositeComponent) builder.build(parent, definition, context);
        component.start();
        TestBean bean = wireService.createProxy(TestBean.class, component.getInboundWire("fooServiceBinding"));
        assertEquals("call foo", bean.echo("call foo"));
        verify(registry);
    }

    protected void setUp() throws Exception {
        super.setUp();
        URL url = getClass().getClassLoader().getResource("META-INF/sca/testServiceContext.xml");
        Resource resource = new UrlResource(url);
        SpringImplementation impl = new SpringImplementation(createComponentType());
        definition = new ComponentDefinition<SpringImplementation>("spring", impl);
        impl.setApplicationResource(resource);
    }

    private SpringComponentType<Property<?>> createComponentType() {
        SpringComponentType<Property<?>> componentType = new SpringComponentType<Property<?>>();
        ServiceDefinition serviceDefinition = new ServiceDefinition();
        serviceDefinition.setName("fooService");
        serviceDefinition.addBinding(new TestBindingDefinition());
        try {
            serviceDefinition.setTarget(new URI("testBean"));
        } catch (URISyntaxException e) {
            throw new AssertionError();
        }
        componentType.add(serviceDefinition);
        return componentType;
    }


}
