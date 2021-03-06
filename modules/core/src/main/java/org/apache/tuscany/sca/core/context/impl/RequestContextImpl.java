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
package org.apache.tuscany.sca.core.context.impl;

import java.util.List;

import javax.security.auth.Subject;

import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.core.context.CallableReferenceExt;
import org.apache.tuscany.sca.core.invocation.ExtensibleProxyFactory;
import org.apache.tuscany.sca.core.invocation.ProxyFactory;
import org.apache.tuscany.sca.core.invocation.ProxyFactoryExtensionPoint;
import org.apache.tuscany.sca.core.invocation.ThreadMessageContext;
import org.apache.tuscany.sca.core.invocation.impl.CallbackReferenceImpl;
import org.apache.tuscany.sca.interfacedef.java.JavaInterface;
import org.apache.tuscany.sca.invocation.Message;
import org.apache.tuscany.sca.runtime.EndpointReference;
import org.apache.tuscany.sca.runtime.ReferenceParameters;
import org.apache.tuscany.sca.runtime.RuntimeComponent;
import org.apache.tuscany.sca.runtime.RuntimeComponentReference;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;
import org.apache.tuscany.sca.runtime.RuntimeWire;
import org.oasisopen.sca.CallableReference;
import org.oasisopen.sca.RequestContext;

/**
 * @version $Rev$ $Date$
 */
public class RequestContextImpl implements RequestContext {

    private ProxyFactoryExtensionPoint proxyFactoryExtensionPoint;

    public RequestContextImpl(RuntimeComponent component) {
        ExtensionPointRegistry registry = component.getComponentContext().getExtensionPointRegistry();
        proxyFactoryExtensionPoint = registry.getExtensionPoint(ProxyFactoryExtensionPoint.class);
    }

    public Subject getSecuritySubject() {
        Subject subject = null;
        
        for (Object header : ThreadMessageContext.getMessageContext().getHeaders()){
            if (header instanceof Subject){
                subject  = (Subject)header;
                break;
            }
        }
        return subject;
    }

    public String getServiceName() {
        return ThreadMessageContext.getMessageContext().getTo().getContract().getName();
    }

    public <B> CallableReference<B> getServiceReference() {
        Message msgContext = ThreadMessageContext.getMessageContext();
        // FIXME: [rfeng] Is this the service reference matching the caller side?
        EndpointReference to = msgContext.getTo();
        RuntimeComponentService service = (RuntimeComponentService) to.getContract();
        RuntimeComponent component = (RuntimeComponent) to.getComponent();
        
        CallableReference<B> callableReference = component.getComponentContext().getCallableReference(null, component, service);
        ReferenceParameters parameters = msgContext.getFrom().getReferenceParameters();
        ((CallableReferenceExt<B>) callableReference).attachCallbackID(parameters.getCallbackID());
        ((CallableReferenceExt<B>) callableReference).attachConversation(parameters.getConversationID());
        return callableReference;
    }

    public <CB> CB getCallback() {
        CallableReference<CB> cb = getCallbackReference(); 
        if (cb == null) {
            return null;
        }
        return cb.getService();
    }

    @SuppressWarnings("unchecked")
    public <CB> CallableReference<CB> getCallbackReference() {
        Message msgContext = ThreadMessageContext.getMessageContext();
        EndpointReference to = msgContext.getTo();
        RuntimeComponentService service = (RuntimeComponentService) to.getContract();
        RuntimeComponentReference callbackReference = (RuntimeComponentReference)service.getCallbackReference();
        if (callbackReference == null) {
            return null;
        }
        JavaInterface javaInterface = (JavaInterface) callbackReference.getInterfaceContract().getInterface();
        Class<CB> javaClass = (Class<CB>)javaInterface.getJavaClass();
        List<RuntimeWire> wires = callbackReference.getRuntimeWires();
        ProxyFactory proxyFactory = new ExtensibleProxyFactory(proxyFactoryExtensionPoint);
        CallbackReferenceImpl ref = CallbackReferenceImpl.newInstance(javaClass, proxyFactory, wires);
        if (ref != null) {  
            //ref.resolveTarget();
            ReferenceParameters parameters = msgContext.getFrom().getReferenceParameters();
            ref.attachCallbackID(parameters.getCallbackID());
            if (ref.getConversation() != null) {
                ref.attachConversationID(parameters.getConversationID());
            }
        }
        return ref;
    }
}
