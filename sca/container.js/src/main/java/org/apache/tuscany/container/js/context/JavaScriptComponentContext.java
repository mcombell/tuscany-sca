/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.container.js.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.tuscany.container.js.rhino.RhinoScript;
import org.apache.tuscany.core.context.AbstractContext;
import org.apache.tuscany.core.context.CoreRuntimeException;
import org.apache.tuscany.core.context.LifecycleEventListener;
import org.apache.tuscany.core.context.QualifiedName;
import org.apache.tuscany.core.context.SimpleComponentContext;
import org.apache.tuscany.core.context.TargetException;
import org.apache.tuscany.core.invocation.spi.ProxyCreationException;
import org.apache.tuscany.core.invocation.spi.ProxyFactory;

public class JavaScriptComponentContext extends AbstractContext implements SimpleComponentContext {

    private Map<String, Class> services;

    private RhinoScript rhinoInvoker;

    private Map<String, Object> properties;

    private Map<String, ProxyFactory> sourceProxyFactories;

    private Map<String, ProxyFactory> targetProxyFactories;

    private Object instance;

    public JavaScriptComponentContext(String name, Map<String, Class> services, Map<String, Object> properties,
            Map<String, ProxyFactory> sourceProxyFactories, Map<String, ProxyFactory> targetProxyFactories, RhinoScript invoker) {
        super(name);
        assert (services != null) : "No service interface mapping specified";
        assert (properties != null) : "No properties specified";
        this.services = services;
        this.properties = properties;
        this.rhinoInvoker = invoker;
        this.sourceProxyFactories = sourceProxyFactories;
        this.targetProxyFactories = targetProxyFactories;
    }

    public Object getInstance(QualifiedName qName) throws TargetException {
        return getInstance(qName, true);
    }

    public synchronized Object getInstance(QualifiedName qName, boolean notify) throws TargetException {
        ProxyFactory targetFactory = targetProxyFactories.get(qName.getPortName());
        if (targetFactory == null) {
            TargetException e = new TargetException("Target interface not found");
            e.setIdentifier(qName.getPortName());
            e.addContextName(getName());
            throw e;
        }
        try {
            Object proxy = targetFactory.createProxy(); //createProxy(new Class[] { iface });
            notifyListeners(notify);
            return proxy;
        } catch (ProxyCreationException e) {
            TargetException te = new TargetException("Error returning target", e);
            e.setIdentifier(qName.getPortName());
            e.addContextName(getName());
            throw te;
        }
    }

    public Object getImplementationInstance() throws TargetException {
        return getImplementationInstance(true);
    }

    public Object getImplementationInstance(boolean notify) throws TargetException {
        rhinoInvoker.updateScriptScope(properties); // create prop values
        return rhinoInvoker;
    }

//    private Object createProxy(Class[] ifaces) throws ProxyCreationException {
//        // final RhinoInvoker rhinoInvoker = implementation.getRhinoInvoker().copy();
//        rhinoInvoker.updateScriptScope(properties); // create prop values
//        final Map refs = createInvocationContext();
//        InvocationHandler ih = new InvocationHandler() {
//            public Object invoke(Object proxy, Method method, Object[] args) {
//                return rhinoInvoker.invoke(method.getName(), args, method.getReturnType(), refs);
//                // return rhinoInvoker.invoke(method.getName(), args, method.getReturnType(),createInvocationContext());
//            }
//        };
//        return Proxy.newProxyInstance(ifaces[0].getClassLoader(), ifaces, ih);
//    }

    private void notifyListeners(boolean notify) {
        if (notify) {
            for (Iterator iter = contextListener.iterator(); iter.hasNext();) {
                LifecycleEventListener listener = (LifecycleEventListener) iter.next();
                listener.onInstanceCreate(this);
            }
        }
    }

    /**
     * Creates a map containing any properties and their values
     */
    // private Map createPropertyValues() {
    // Map<String,Object> context = new HashMap<String,Object>();
    // List<ConfiguredProperty> configuredProperties = component.getConfiguredProperties();
    // if (configuredProperties != null) {
    // for (ConfiguredProperty property : configuredProperties) {
    // context.put(property.getProperty().getName(), property.getValue());
    // }
    // }
    // return context;
    // }
    /**
     * Creates a map containing any ServiceReferences
     */
    private Map createInvocationContext() throws ProxyCreationException {
        Map<String, Object> context = new HashMap<String, Object>();
        for (Map.Entry<String, ProxyFactory> entry : sourceProxyFactories.entrySet()) {
            context.put(entry.getKey(), entry.getValue().createProxy());
        }
        return context;
    }

    public boolean isEagerInit() {
        return false;
    }

    public boolean isDestroyable() {
        return false;
    }

    public void start() throws CoreRuntimeException {
    }

    public void stop() throws CoreRuntimeException {
    }

}
