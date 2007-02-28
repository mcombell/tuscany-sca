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
package org.apache.tuscany.binding.axis2;


import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.xml.namespace.QName;

import org.apache.tuscany.spi.model.BindingDefinition;
import org.osoa.sca.Constants;

/**
 * Represents a Celtix binding configuration in an assembly
 *
 * @version $Rev$ $Date$
 */
public class WebServiceBindingDefinition extends BindingDefinition {
    public static final QName CONVERSATION_ID_REFPARM_QN = new QName(Constants.SCA_NS,"conversationID");
    private Definition definition;
    private Port port;
    private Service service;
    private String uri;
    private String namespace;
    private String serviceName;
    private String portName;
    private String bindingName;
    private Binding binding;

    /**
     * @deprecated pre 1.0 binding.ws spec
     */
    @Deprecated
    public WebServiceBindingDefinition(Definition definition, Port port, String uri, String portURI, Service service) {
        this.definition = definition;
        this.port = port;
        this.uri = uri;
        this.service = service;
    }

    public WebServiceBindingDefinition(String ns, Definition definition, String serviceName, String portName, String bindingName, String uri) {
        this.namespace = ns;
        this.definition = definition;
        this.serviceName = serviceName;
        this.portName = portName;
        this.bindingName = bindingName;
        this.uri = uri;
    }

    public Port getWSDLPort() {
        if (port == null) {
            Service service = getWSDLService();
            port = service.getPort(portName);
        }
        return port;
    }

    public Service getWSDLService() {
        if (service == null) {
            if (definition == null) {
                throw new IllegalStateException("WSDL definition is null");
            }
            Map services = definition.getServices();
            if (serviceName != null) {
                QName serviceQN = new QName(namespace, serviceName);
                for (Object o : services.values()) {
                    Service s = (Service) o;
                    if (s.getQName().equals(serviceQN)) {
                        service = s;
                        break;
                    }
                }
                if (service == null) {
                    throw new IllegalStateException("no service: " + serviceQN);
                }
            } else {
                service = (Service)services.values().iterator().next();
            }
        }
        return service;
    }

    public void setWSDLPort(Port value) {
        port = value;
    }

    public Definition getWSDLDefinition() {
        return definition;
    }

    public void setWSDLDefinition(Definition def) {
        definition = def;
    }

    public String getURI() {
        return uri;
    }

    public void setURI(String theUri) {
        this.uri = theUri;
    }
    
    public Binding getBinding() {
        if (binding == null) {
            if (definition == null) {
                throw new IllegalStateException("WSDL definition is null");
            }
            QName bindingQN = new QName(namespace, bindingName);
            this.binding = definition.getBinding(bindingQN);
            if (binding == null) {
                throw new IllegalStateException("no binding: " + bindingQN);
            }
        }
        return binding;
    }
}
