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
package org.apache.tuscany.sca.interfacedef.wsdl.impl;

import javax.wsdl.PortType;

import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.core.FactoryExtensionPoint;
import org.apache.tuscany.sca.interfacedef.InvalidInterfaceException;
import org.apache.tuscany.sca.interfacedef.wsdl.WSDLDefinition;
import org.apache.tuscany.sca.interfacedef.wsdl.WSDLFactory;
import org.apache.tuscany.sca.interfacedef.wsdl.WSDLInterface;
import org.apache.tuscany.sca.interfacedef.wsdl.WSDLInterfaceContract;

/**
 * A factory for the WSDL model.
 * 
 * @version $Rev$ $Date$
 */
public abstract class WSDLFactoryImpl implements WSDLFactory {
    
    private WSDLInterfaceIntrospectorImpl introspector;
    
    public WSDLFactoryImpl(FactoryExtensionPoint modelFactories) {
        introspector = new WSDLInterfaceIntrospectorImpl(modelFactories);
    }

    public WSDLInterface createWSDLInterface() {
        return new WSDLInterfaceImpl();
    }
    
    public WSDLInterface createWSDLInterface(PortType portType,
                                             WSDLDefinition wsdlDefinition,
                                             ModelResolver resolver) throws InvalidInterfaceException {
        WSDLInterface wsdlInterface = createWSDLInterface();
        introspector.introspectPortType(wsdlInterface, portType, wsdlDefinition, resolver);
        return wsdlInterface;
    }
    
    public void createWSDLInterface(WSDLInterface wsdlInterface,
                                    PortType portType,
                                    WSDLDefinition wsdlDefinition,
                                    ModelResolver resolver) throws InvalidInterfaceException {
        introspector.introspectPortType(wsdlInterface, portType, wsdlDefinition, resolver);
    }
    
    public WSDLDefinition createWSDLDefinition() {
        return new WSDLDefinitionImpl();
    }
    
    public WSDLInterfaceContract createWSDLInterfaceContract() {
        return new WSDLInterfaceContractImpl();
    }

    public WSDLInterfaceIntrospectorImpl getWSDLInterfaceIntrospector() {
        return introspector;
    }

}
