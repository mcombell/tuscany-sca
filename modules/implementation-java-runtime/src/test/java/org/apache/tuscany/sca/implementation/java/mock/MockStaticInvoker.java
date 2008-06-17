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
package org.apache.tuscany.sca.implementation.java.mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.tuscany.sca.implementation.java.invocation.TargetInvoker;
import org.apache.tuscany.sca.interfacedef.ConversationSequence;
import org.apache.tuscany.sca.invocation.Interceptor;
import org.apache.tuscany.sca.invocation.Message;
import org.osoa.sca.ServiceRuntimeException;

/**
 * Caches component instances that do not need to be resolved for every wire, e.g. an wire originating from a lesser
 * scope intended for a target with a wider scope
 *
 * @version $Rev$ $Date$
 */
public class MockStaticInvoker implements TargetInvoker {

    private Object instance;
    private Method operation;
    private boolean cacheable;


    public MockStaticInvoker(Method operation, Object instance) {
        this.operation = operation;
        this.instance = instance;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public Object invokeTarget(final Object payload, final ConversationSequence sequence) throws InvocationTargetException {
        try {
            if (payload != null && !payload.getClass().isArray()) {
                return operation.invoke(instance, payload);
            } else {
                return operation.invoke(instance, (Object[]) payload);
            }
        } catch (IllegalAccessException e) {
            throw new ServiceRuntimeException(e);
        }
    }

    public Message invoke(Message msg) {
        try {
            Object resp = invokeTarget(msg.getBody(), null);
            msg.setBody(resp);
        } catch (InvocationTargetException e) {
            msg.setFaultBody(e.getCause());
        } catch (Throwable e) {
            msg.setFaultBody(e);
        }
        return msg;
    }

    public void setNext(Interceptor next) {
        throw new IllegalStateException("This interceptor must be the last interceptor in an interceptor chain");
    }

    public Object clone() throws CloneNotSupportedException {
        try {
            MockStaticInvoker invoker = (MockStaticInvoker) super.clone();
            invoker.instance = this.instance;
            invoker.operation = this.operation;
            return invoker;
        } catch (CloneNotSupportedException e) {
            return null; // will not happen
        }
    }
}