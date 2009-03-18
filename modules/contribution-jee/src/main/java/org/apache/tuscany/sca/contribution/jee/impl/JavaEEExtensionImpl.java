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
package org.apache.tuscany.sca.contribution.jee.impl;

import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.assembly.ComponentType;
import org.apache.tuscany.sca.assembly.Service;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.jee.EjbInfo;
import org.apache.tuscany.sca.contribution.jee.EjbModuleInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEApplicationInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEExtension;
import org.apache.tuscany.sca.contribution.jee.EjbInfo.EjbType;
import org.apache.tuscany.sca.interfacedef.InterfaceContract;
import org.apache.tuscany.sca.interfacedef.InvalidInterfaceException;
import org.apache.tuscany.sca.interfacedef.java.JavaInterfaceFactory;
import org.apache.tuscany.sca.policy.Intent;
import org.apache.tuscany.sca.policy.PolicyFactory;

public class JavaEEExtensionImpl implements JavaEEExtension {
    
    private AssemblyFactory assemblyFactory;
    private JavaInterfaceFactory javaInterfaceFactory;
    private PolicyFactory policyFactory;
    private Intent EJB_INTENT;
    
    public JavaEEExtensionImpl(ModelFactoryExtensionPoint modelFactories) {
        this.assemblyFactory = modelFactories.getFactory(AssemblyFactory.class);
        this.javaInterfaceFactory = modelFactories.getFactory(JavaInterfaceFactory.class);
        this.policyFactory = modelFactories.getFactory(PolicyFactory.class);
        
        EJB_INTENT = policyFactory.createIntent();
        EJB_INTENT.setName(new QName("http://www.osoa.org/xmlns/sca/1.0", "ejb"));
    }
    
    public ComponentType createImplementationEjbComponentType(EjbModuleInfo ejbModule, String ejbName) {
        ComponentType componentType = assemblyFactory.createComponentType();
        EjbInfo ejbInfo = ejbModule.getEjbInfo(ejbName);
        if(ejbInfo == null) {
            return null;
        }
        if(ejbInfo.ejbType.compareTo(EjbType.MESSAGE_DRIVEN) != 0) {
            for(Class<?> intf : ejbInfo.businessRemote) {
                Service service = assemblyFactory.createComponentService();
                String intfName = intf.getName();
                String serviceName = intfName.lastIndexOf(".") != -1 ? intfName.substring(intfName.lastIndexOf(".") + 1) : intfName;
                service.setName(serviceName);
                InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                try {
                    ic.setInterface(javaInterfaceFactory.createJavaInterface(intf));
                } catch (InvalidInterfaceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                service.setInterfaceContract(ic);
                componentType.getServices().add(service);
            }

            for(Class<?> intf : ejbInfo.businessLocal) {
                Service service = assemblyFactory.createComponentService();
                String intfName = intf.getName();
                String serviceName = intfName.lastIndexOf(".") != -1 ? intfName.substring(intfName.lastIndexOf(".") + 1) : intfName;
                service.setName(serviceName);
                InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                try {
                    ic.setInterface(javaInterfaceFactory.createJavaInterface(intf));
                } catch (InvalidInterfaceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                service.setInterfaceContract(ic);
                service.getRequiredIntents().add(EJB_INTENT);
                
                componentType.getServices().add(service);
            }
        }
        return componentType;
    }
    
    public ComponentType createImplementationJeeComponentType(EjbModuleInfo ejbModule) {
        ComponentType componentType = assemblyFactory.createComponentType();
        
        for(Map.Entry<String, EjbInfo> entry : ejbModule.getEjbInfos().entrySet()) {
            EjbInfo ejbInfo = entry.getValue();
            if(ejbInfo.ejbType.compareTo(EjbType.MESSAGE_DRIVEN) != 0) {
                for(Class<?> intf : ejbInfo.businessRemote) {
                    Service service = assemblyFactory.createComponentService();
                    String intfName = intf.getName();
                    String serviceName = intfName.lastIndexOf(".") != -1 ? intfName.substring(intfName.lastIndexOf(".") + 1) : intfName;
                    serviceName = ejbInfo.beanName+"_"+serviceName;
                    service.setName(serviceName);
                    InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                    try {
                        ic.setInterface(javaInterfaceFactory.createJavaInterface(intf));
                    } catch (InvalidInterfaceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    service.setInterfaceContract(ic);
                    componentType.getServices().add(service);
                }

                for(Class<?> intf : ejbInfo.businessLocal) {
                    Service service = assemblyFactory.createComponentService();
                    String intfName = intf.getName();
                    String serviceName = intfName.lastIndexOf(".") != -1 ? intfName.substring(intfName.lastIndexOf(".") + 1) : intfName;
                    serviceName = ejbInfo.beanName+"_"+serviceName;
                    service.setName(serviceName);
                    InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                    try {
                        ic.setInterface(javaInterfaceFactory.createJavaInterface(intf));
                    } catch (InvalidInterfaceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    service.setInterfaceContract(ic);
                    service.getRequiredIntents().add(EJB_INTENT);
                    
                    componentType.getServices().add(service);
                }
            }
        }
        
        return componentType;
    }

    public ComponentType createImplementationJeeComponentType(JavaEEApplicationInfo appInfo) {
        ComponentType componentType = assemblyFactory.createComponentType();
        
        for(Map.Entry<String, EjbModuleInfo> entry0 : appInfo.getEjbModuleInfos().entrySet()) {
            EjbModuleInfo ejbModule = entry0.getValue();
            
            for(Map.Entry<String, EjbInfo> entry : ejbModule.getEjbInfos().entrySet()) {
                EjbInfo ejbInfo = entry.getValue();
                if(ejbInfo.ejbType.compareTo(EjbType.MESSAGE_DRIVEN) != 0) {
                    for(Class<?> intf : ejbInfo.businessRemote) {
                        Service service = assemblyFactory.createComponentService();
                        String intfName = intf.getName();
                        String serviceName = intfName.lastIndexOf(".") != -1 ? intfName.substring(intfName.lastIndexOf(".") + 1) : intfName;
                        serviceName = ejbInfo.mappedName+"_"+serviceName;
                        service.setName(serviceName);
                        InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                        try {
                            ic.setInterface(javaInterfaceFactory.createJavaInterface(intf));
                        } catch (InvalidInterfaceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        service.setInterfaceContract(ic);
                        componentType.getServices().add(service);
                    }

                    for(Class<?> intf : ejbInfo.businessLocal) {
                        Service service = assemblyFactory.createComponentService();
                        String intfName = intf.getName();
                        String serviceName = intfName.lastIndexOf(".") != -1 ? intfName.substring(intfName.lastIndexOf(".") + 1) : intfName;
                        serviceName = ejbInfo.mappedName+"_"+serviceName;
                        service.setName(serviceName);
                        InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                        try {
                            ic.setInterface(javaInterfaceFactory.createJavaInterface(intf));
                        } catch (InvalidInterfaceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        service.setInterfaceContract(ic);
                        service.getRequiredIntents().add(EJB_INTENT);
                        
                        componentType.getServices().add(service);
                    }
                }
            }
        }
        
        return componentType;
    }
}
