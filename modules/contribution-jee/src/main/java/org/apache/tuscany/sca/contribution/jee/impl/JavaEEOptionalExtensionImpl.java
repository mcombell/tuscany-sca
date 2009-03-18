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

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.assembly.ComponentType;
import org.apache.tuscany.sca.assembly.Multiplicity;
import org.apache.tuscany.sca.assembly.Property;
import org.apache.tuscany.sca.assembly.Reference;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.jee.EjbInfo;
import org.apache.tuscany.sca.contribution.jee.EjbModuleInfo;
import org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo;
import org.apache.tuscany.sca.contribution.jee.EnvEntryInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEApplicationInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEOptionalExtension;
import org.apache.tuscany.sca.contribution.jee.WebModuleInfo;
import org.apache.tuscany.sca.interfacedef.InterfaceContract;
import org.apache.tuscany.sca.interfacedef.InvalidInterfaceException;
import org.apache.tuscany.sca.interfacedef.java.JavaInterfaceFactory;
import org.apache.tuscany.sca.policy.Intent;
import org.apache.tuscany.sca.policy.PolicyFactory;

public class JavaEEOptionalExtensionImpl implements JavaEEOptionalExtension {
    
    private AssemblyFactory assemblyFactory;
    private JavaInterfaceFactory javaInterfaceFactory;
    private PolicyFactory policyFactory;
    private Intent EJB_INTENT;
    
    public static final Map<String, QName> ALLOWED_ENV_ENTRY_TYPES;
    static {
        ALLOWED_ENV_ENTRY_TYPES = new HashMap<String, QName>();
        ALLOWED_ENV_ENTRY_TYPES.put(String.class.getName(), new QName("http://www.w3.org/2001/XMLSchema", "string",
                                                                      "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Character.class.getName(), new QName("http://www.w3.org/2001/XMLSchema", "string",
                                                                         "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Byte.class.getName(), new QName("http://www.w3.org/2001/XMLSchema", "byte", "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Short.class.getName(),
                                    new QName("http://www.w3.org/2001/XMLSchema", "short", "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Integer.class.getName(),
                                    new QName("http://www.w3.org/2001/XMLSchema", "int", "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Long.class.getName(), new QName("http://www.w3.org/2001/XMLSchema", "long", "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Boolean.class.getName(), new QName("http://www.w3.org/2001/XMLSchema", "boolean",
                                                                       "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Double.class.getName(), new QName("http://www.w3.org/2001/XMLSchema", "double",
                                                                      "xsd"));
        ALLOWED_ENV_ENTRY_TYPES.put(Float.class.getName(),
                                    new QName("http://www.w3.org/2001/XMLSchema", "float", "xsd"));
    }

    public JavaEEOptionalExtensionImpl(ModelFactoryExtensionPoint modelFactories) {
        this.assemblyFactory = modelFactories.getFactory(AssemblyFactory.class);
        this.javaInterfaceFactory = modelFactories.getFactory(JavaInterfaceFactory.class);
        this.policyFactory = modelFactories.getFactory(PolicyFactory.class);
        
        EJB_INTENT = policyFactory.createIntent();
        EJB_INTENT.setName(new QName("http://www.osoa.org/xmlns/sca/1.0", "ejb"));
    }
    
    public ComponentType createImplementationWebComponentType(WebModuleInfo webModule) {
        ComponentType componentType = assemblyFactory.createComponentType();
        
        // Process Remote EJB References
        for(Map.Entry<String, EjbReferenceInfo> entry : webModule.getEjbReferences().entrySet()) {
            EjbReferenceInfo ejbRef = entry.getValue();
            String referenceName = entry.getKey();
            referenceName = referenceName.replace("/", "_");
            Reference reference = assemblyFactory.createComponentReference();
            reference.setName(referenceName);
            InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
            try {
                ic.setInterface(javaInterfaceFactory.createJavaInterface(ejbRef.businessInterface));
            } catch (InvalidInterfaceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            reference.setInterfaceContract(ic);
            reference.getRequiredIntents().add(EJB_INTENT);
            reference.setMultiplicity(Multiplicity.ZERO_ONE);
            componentType.getReferences().add(reference);
        }
        
        // Process env-entries to compute properties
        for (Map.Entry<String, EnvEntryInfo> entry : webModule.getEnvEntries().entrySet()) {
            EnvEntryInfo envEntry = entry.getValue();
            String type = envEntry.type;
            if (!ALLOWED_ENV_ENTRY_TYPES.containsKey(type)) {
                continue;
            }
            String propertyName = envEntry.name;
            propertyName = propertyName.replace("/", "_");
            String value = envEntry.value;
            Property property = assemblyFactory.createComponentProperty();
            property.setName(propertyName);
            property.setXSDType(ALLOWED_ENV_ENTRY_TYPES.get(type));
            property.setValue(value);
            componentType.getProperties().add(property);
        }
        
        return componentType;
    }
    public ComponentType createImplementationEjbComponentType(EjbModuleInfo ejbModule, String ejbName) {
        ComponentType componentType = assemblyFactory.createComponentType();
        EjbInfo ejbInfo = ejbModule.getEjbInfo(ejbName);
        if(ejbInfo == null) {
            return null;
        }

        // Process Remote EJB References
        for(Map.Entry<String, EjbReferenceInfo> entry : ejbInfo.ejbReferences.entrySet()) {
            EjbReferenceInfo ejbRef = entry.getValue();
            String referenceName = entry.getKey();
            referenceName = referenceName.replace("/", "_");
            Reference reference = assemblyFactory.createComponentReference();
            reference.setName(referenceName);
            InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
            try {
                ic.setInterface(javaInterfaceFactory.createJavaInterface(ejbRef.businessInterface));
            } catch (InvalidInterfaceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            reference.setInterfaceContract(ic);
            reference.getRequiredIntents().add(EJB_INTENT);
            reference.setMultiplicity(Multiplicity.ZERO_ONE);
            componentType.getReferences().add(reference);
        }
        
        // Process env-entries to compute properties
        for (Map.Entry<String, EnvEntryInfo> entry : ejbInfo.envEntries.entrySet()) {
            EnvEntryInfo envEntry = entry.getValue();
            String type = envEntry.type;
            if (!ALLOWED_ENV_ENTRY_TYPES.containsKey(type)) {
                continue;
            }
            String propertyName = envEntry.name;
            propertyName = propertyName.replace("/", "_");
            String value = envEntry.value;
            Property property = assemblyFactory.createComponentProperty();
            property.setName(propertyName);
            property.setXSDType(ALLOWED_ENV_ENTRY_TYPES.get(type));
            property.setValue(value);
            componentType.getProperties().add(property);
        }
        return componentType;
    }
    
    public ComponentType createImplementationJeeComponentType(WebModuleInfo webModule) {
        ComponentType componentType = assemblyFactory.createComponentType();
        
        // Process Remote EJB References
        for(Map.Entry<String, EjbReferenceInfo> entry : webModule.getEjbReferences().entrySet()) {
            EjbReferenceInfo ejbRef = entry.getValue();
            String referenceName = entry.getKey();
            referenceName = referenceName.replace("/", "_");
            Reference reference = assemblyFactory.createComponentReference();
            reference.setName(referenceName);
            InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
            try {
                ic.setInterface(javaInterfaceFactory.createJavaInterface(ejbRef.businessInterface));
            } catch (InvalidInterfaceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            reference.setInterfaceContract(ic);
            reference.getRequiredIntents().add(EJB_INTENT);
            reference.setMultiplicity(Multiplicity.ZERO_ONE);
            componentType.getReferences().add(reference);
        }
        
        return componentType;
    }

    public ComponentType createImplementationJeeComponentType(EjbModuleInfo ejbModule) {
        ComponentType componentType = assemblyFactory.createComponentType();
        
        for(Map.Entry<String, EjbInfo> entry : ejbModule.getEjbInfos().entrySet()) {
            EjbInfo ejbInfo = entry.getValue();
            // Process Remote EJB References
            for(Map.Entry<String, EjbReferenceInfo> entry1 : ejbInfo.ejbReferences.entrySet()) {
                EjbReferenceInfo ejbRef = entry1.getValue();
                String referenceName = ejbRef.referenceName;
                referenceName = referenceName.replace("/", "_");
                referenceName = ejbInfo.beanName + "_" + referenceName;
                Reference reference = assemblyFactory.createComponentReference();
                reference.setName(referenceName);
                InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                try {
                    ic.setInterface(javaInterfaceFactory.createJavaInterface(ejbRef.businessInterface));
                } catch (InvalidInterfaceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                reference.setInterfaceContract(ic);
                reference.getRequiredIntents().add(EJB_INTENT);
                reference.setMultiplicity(Multiplicity.ZERO_ONE);
                componentType.getReferences().add(reference);
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
                // Process Remote EJB References
                for(Map.Entry<String, EjbReferenceInfo> entry1 : ejbInfo.ejbReferences.entrySet()) {
                    EjbReferenceInfo ejbRef = entry1.getValue();
                    String referenceName = ejbRef.referenceName;
                    referenceName = referenceName.replace("/", "_");
                    referenceName = ejbInfo.beanName + "_" + referenceName;
                    Reference reference = assemblyFactory.createComponentReference();
                    reference.setName(referenceName);
                    InterfaceContract ic = javaInterfaceFactory.createJavaInterfaceContract();
                    try {
                        ic.setInterface(javaInterfaceFactory.createJavaInterface(ejbRef.businessInterface));
                    } catch (InvalidInterfaceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    reference.setInterfaceContract(ic);
                    reference.getRequiredIntents().add(EJB_INTENT);
                    reference.setMultiplicity(Multiplicity.ZERO_ONE);
                    componentType.getReferences().add(reference);
                }
            }
        }
        
        return componentType;
    }
}
