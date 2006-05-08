/**
 *
 * Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.apache.tuscany.core.wire.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.apache.tuscany.core.util.MethodHashMap;
import org.apache.tuscany.spi.wire.SourceInvocationConfiguration;
import org.apache.tuscany.spi.wire.SourceWireFactory;
import org.apache.tuscany.spi.wire.WireFactoryInitException;
import org.apache.tuscany.spi.wire.WireSourceConfiguration;

/**
 * Creates proxies that are injected on references using JDK dynamic proxy facilities and front a wire. The
 * proxies implement the business interface associated with the service required by reference.
 *
 * @version $Rev: 394431 $ $Date: 2006-04-15 21:27:44 -0700 (Sat, 15 Apr 2006) $
 */
public class JDKSourceWireFactory<T> implements SourceWireFactory<T> {

    private static final int UNINITIALIZED = 0;

    private static final int INITIALIZED = 1;

    private int state = UNINITIALIZED;

    private Class<T>[] businessInterfaceArray;

    private Map<Method, SourceInvocationConfiguration> methodToInvocationConfig;

    private WireSourceConfiguration configuration;

    public void initialize() throws WireFactoryInitException {
        if (state != UNINITIALIZED) {
            throw new IllegalStateException("Proxy factory in wrong state [" + state + "]");
        }
        Map<Method, SourceInvocationConfiguration> invocationConfigs = configuration.getInvocationConfigurations();
        methodToInvocationConfig = new MethodHashMap<SourceInvocationConfiguration>(invocationConfigs.size());
        for (Map.Entry<Method, SourceInvocationConfiguration> entry : invocationConfigs.entrySet()) {
            Method method = entry.getKey();
            methodToInvocationConfig.put(method, entry.getValue());
        }
        state = INITIALIZED;
    }

    @SuppressWarnings("unchecked")
    public T createProxy() {
        if (state != INITIALIZED) {
            throw new IllegalStateException("Proxy factory not INITIALIZED [" + state + "]");
        }
        InvocationHandler handler = new JDKInvocationHandler(methodToInvocationConfig);
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), businessInterfaceArray, handler);
    }

    public WireSourceConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(WireSourceConfiguration config) {
        configuration = config;
    }

    @SuppressWarnings("unchecked")
    public void setBusinessInterface(Class<T> interfaze) {
        businessInterfaceArray = new Class[]{interfaze};
    }

    public Class<T> getBusinessInterface() {
        return businessInterfaceArray[0];
    }

    public void addInterface(Class<?> claz) {
        throw new UnsupportedOperationException("Additional proxy interfaces not yet supported");
    }

    public Class[] getImplementatedInterfaces() {
        return businessInterfaceArray;
    }

}
