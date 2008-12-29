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

package org.apache.tuscany.sca.binding.hessian.provider;

import java.util.logging.Logger;

import javax.servlet.Servlet;

import org.apache.tuscany.sca.binding.hessian.impl.HessianBinding;
import org.apache.tuscany.sca.host.http.ServletHost;
import org.apache.tuscany.sca.interfacedef.Interface;
import org.apache.tuscany.sca.interfacedef.InterfaceContract;
import org.apache.tuscany.sca.invocation.MessageFactory;
import org.apache.tuscany.sca.provider.ServiceBindingProvider;
import org.apache.tuscany.sca.runtime.RuntimeComponent;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;
import org.apache.tuscany.sca.runtime.RuntimeWire;

public class HessianServiceBindingProvider implements ServiceBindingProvider {
    private static final Logger logger = Logger.getLogger(HessianServiceBindingProvider.class.getName());

    private RuntimeComponent component;
    private RuntimeComponentService service;
    private HessianBinding hessianBinding;
    private MessageFactory messageFactory;
    private ServletHost servletHost;
    private String servletMapping;

    public HessianServiceBindingProvider(RuntimeComponent component,
                                         RuntimeComponentService service,
                                         HessianBinding binding,
                                         MessageFactory messageFactory,
                                         ServletHost servletHost) {
        this.component = component;
        this.service = service;
        this.hessianBinding = binding;
        this.messageFactory = messageFactory;
        this.servletHost = servletHost;
    }

    public InterfaceContract getBindingInterfaceContract() {
        return service.getInterfaceContract();
    }

    public boolean supportsOneWayInvocation() {
        return true;
    }

    public void start() {
        Servlet servlet = null;
        Interface homeImpl = service.getInterfaceContract().getInterface();
        RuntimeWire wire = service.getRuntimeWire(hessianBinding);
        servlet = new HessianServiceListenerServlet(homeImpl, wire, messageFactory);

        if (servlet == null) {
            throw new IllegalStateException("No get or service method found on the service");
        }

        servletMapping = hessianBinding.getURI();
        servletHost.addServletMapping(servletMapping, servlet);

        // Save the actual binding URI in the binding
        hessianBinding.setURI(servletHost.getURLMapping(hessianBinding.getURI()).toString());
    }

    public void stop() {
        servletHost.removeServletMapping(servletMapping);
    }

}
