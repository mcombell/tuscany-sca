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
package org.apache.tuscany.core.implementation;

import java.net.URI;

import org.apache.tuscany.spi.ObjectFactory;
import org.apache.tuscany.spi.component.AtomicComponent;
import org.apache.tuscany.spi.component.ScopeContainer;
import org.apache.tuscany.spi.component.TargetInvokerCreationException;
import org.apache.tuscany.spi.model.Operation;
import org.apache.tuscany.spi.model.Scope;
import org.apache.tuscany.spi.model.physical.PhysicalOperationDefinition;
import org.apache.tuscany.spi.wire.TargetInvoker;
import org.apache.tuscany.spi.wire.Wire;

import junit.framework.TestCase;
import org.apache.tuscany.core.injection.EventInvoker;
import org.apache.tuscany.core.injection.PojoObjectFactory;
import org.easymock.EasyMock;

/**
 * @version $Rev$ $Date$
 */
public class PojoAtomicComponentTestCase extends TestCase {
    private PojoConfiguration config;

    @SuppressWarnings({"unchecked"})
    public void testDestroy() throws Exception {
        EventInvoker<Object> invoker = EasyMock.createMock(EventInvoker.class);
        invoker.invokeEvent(EasyMock.notNull());
        EasyMock.replay(invoker);
        config.setDestroyInvoker(invoker);
        AtomicComponent component = new TestAtomicComponent(config);
        assertTrue(component.isDestroyable());
        component.destroy(new Object());
        EasyMock.verify(invoker);
    }

    @SuppressWarnings({"unchecked"})
    public void testNoCallbackWires() throws Exception {
        ScopeContainer container = EasyMock.createMock(ScopeContainer.class);
        EasyMock.expect(container.getScope()).andReturn(Scope.CONVERSATION);
        container.register(EasyMock.isA(AtomicComponent.class));
        EasyMock.replay(container);
        config.addCallbackSite("callback", Foo.class.getMethod("setCallback", Object.class));
        AtomicComponent component = new TestAtomicComponent(config);
        component.setScopeContainer(container);
        component.start();
        EasyMock.verify(container);
    }

    @SuppressWarnings({"unchecked"})
    public void testInit() throws Exception {
        EventInvoker<Object> invoker = EasyMock.createMock(EventInvoker.class);
        invoker.invokeEvent(EasyMock.notNull());
        EasyMock.replay(invoker);
        config.setInitInvoker(invoker);
        AtomicComponent component = new TestAtomicComponent(config);
        component.init(new Object());
        EasyMock.verify(invoker);
    }

    public void testOptimizable() throws Exception {
        TestAtomicComponent component = new TestAtomicComponent(config);
        assertTrue(component.isOptimizable());
    }

    @SuppressWarnings({"unchecked"})
    public void testDestroyableButOptimizable() throws Exception {
        EventInvoker<Object> invoker = EasyMock.createMock(EventInvoker.class);
        invoker.invokeEvent(EasyMock.notNull());
        EasyMock.replay(invoker);
        config.setDestroyInvoker(invoker);
        TestAtomicComponent component = new TestAtomicComponent(config);
        assertTrue(component.isOptimizable());
    }

    @SuppressWarnings({"unchecked"})
    public void testStatelessOptimizable() throws Exception {
        TestAtomicComponent component = new TestAtomicComponent(config, Scope.STATELESS);
        assertTrue(component.isOptimizable());
    }

    @SuppressWarnings({"unchecked"})
    public void testNotOptimizable() throws Exception {
        EventInvoker<Object> invoker = EasyMock.createMock(EventInvoker.class);
        invoker.invokeEvent(EasyMock.notNull());
        EasyMock.replay(invoker);
        config.setDestroyInvoker(invoker);
        TestAtomicComponent component = new TestAtomicComponent(config, Scope.STATELESS);
        assertFalse(component.isOptimizable());
    }

    public void testPropertyAccess() {
        String value = "Foo!";
        ObjectFactory objectFactory = EasyMock.createMock(ObjectFactory.class);
        EasyMock.expect(objectFactory.getInstance()).andReturn(value);
        EasyMock.replay(objectFactory);

        TestAtomicComponent component = new TestAtomicComponent(config);
        component.addPropertyFactory("foo", objectFactory);
        assertSame(value, component.getProperty(String.class, "foo"));
    }

    public void testServiceLookup() {
        URI uri = URI.create("#service");
        FooService foo = EasyMock.createMock(FooService.class);
        Wire wire = EasyMock.createMock(Wire.class);
        EasyMock.expect(wire.getSourceUri()).andReturn(uri).atLeastOnce();
        EasyMock.replay(wire);
        ObjectFactory factory = EasyMock.createMock(ObjectFactory.class);
        EasyMock.expect(factory.getInstance()).andReturn(foo);
        EasyMock.replay(factory);
        TestAtomicComponent component = new TestAtomicComponent(config, Scope.COMPOSITE, factory);
        component.attachWire(wire);
        assertSame(foo, component.getService(FooService.class, "service"));
        EasyMock.verify(wire);
        EasyMock.verify(factory);
    }

    protected void setUp() throws Exception {
        super.setUp();
        PojoObjectFactory<Foo> factory = new PojoObjectFactory<Foo>(Foo.class.getConstructor());

        config = new PojoConfiguration();
        config.setInstanceFactory(factory);
        config.setName(URI.create("foo"));
    }

    private class TestAtomicComponent extends PojoAtomicComponent {
        private final ObjectFactory factory;
        private final Scope scope;

        public TestAtomicComponent(PojoConfiguration configuration) {
            this(configuration, Scope.COMPOSITE, null);
        }

        public TestAtomicComponent(PojoConfiguration configuration, Scope scope) {
            this(configuration, scope, null);
        }

        public TestAtomicComponent(PojoConfiguration configuration, Scope scope, ObjectFactory factory) {
            super(configuration);
            this.scope = scope;
            this.factory = factory;
        }

        public Scope getScope() {
            return scope;
        }

        @SuppressWarnings({"unchecked"})
        protected <B> ObjectFactory<B> createWireFactory(Class<B> interfaze, Wire wire) {
            return factory;
        }

        public TargetInvoker createTargetInvoker(String targetName, Operation operation)
            throws TargetInvokerCreationException {
            return null;
        }

        public TargetInvoker createTargetInvoker(String targetName, PhysicalOperationDefinition operation)
            throws TargetInvokerCreationException {
            return null;
        }
    }

    private static class Foo {
        public Foo() {
        }

        public void setCallback(Object callback) {

        }
    }

    public static interface FooService {
    }

}


