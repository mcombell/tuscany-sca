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
package org.apache.tuscany.sca.implementation.spring;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.List;

import org.apache.tuscany.sca.assembly.Component;
import org.apache.tuscany.sca.assembly.ComponentType;
import org.apache.tuscany.sca.assembly.Extensible;
import org.apache.tuscany.sca.assembly.Implementation;
import org.apache.tuscany.sca.assembly.Property;
import org.apache.tuscany.sca.assembly.Reference;
import org.apache.tuscany.sca.assembly.Service;
import org.apache.tuscany.sca.assembly.impl.ImplementationImpl;
import org.apache.tuscany.sca.interfacedef.InterfaceContract;
import org.apache.tuscany.sca.assembly.builder.ComponentPreProcessor;
import org.apache.tuscany.sca.implementation.java.impl.JavaConstructorImpl;
import org.apache.tuscany.sca.implementation.spring.xml.SpringBeanElement;
import org.apache.tuscany.sca.policy.util.PolicyHandlerTuple;
import org.apache.tuscany.sca.runtime.RuntimeComponent;
import org.springframework.core.io.Resource;

/**
 * Represents a Spring implementation.
 * 
 * @version $Rev: 511195 $ $Date: 2007-02-24 02:29:46 +0000 (Sat, 24 Feb 2007) $ 
 */
public class SpringImplementation extends ImplementationImpl implements Implementation, ComponentPreProcessor, Extensible {

    // The location attribute which points to the Spring application-context XML file
    private String location;
    // The application-context file as a Spring Resource
    private Resource resource;
    private ComponentType componentType;
    // Mapping of Services to Beans
    private Hashtable<String, SpringBeanElement> serviceMap;
    // Mapping of property names to Java class
    private Hashtable<String, Class> propertyMap;
    private List<PolicyHandlerTuple> policyHandlerClassNames = null;
    // List of unresolved bean property references
    private Hashtable<String, Reference> unresolvedBeanRef;
    
    // Method marked with @Init annotation
    private Method initMethod = null;
    // Method marked with @Destroy annotation
    private Method destroyMethod = null;
    // Method marked with @Constructor annotation
    private JavaConstructorImpl<?> constructorDefinition = null;

    protected SpringImplementation() {
        this.location = null;
        this.resource = null;
        setUnresolved(true);
        serviceMap = new Hashtable<String, SpringBeanElement>();
        propertyMap = new Hashtable<String, Class>();
        unresolvedBeanRef = new Hashtable<String, Reference>();
    } // end method SpringImplementation

    /* Returns the location attribute for this Spring implementation */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location attribute for this Spring implementation
     * location - a URI to the Spring application-context file
     */
    public void setLocation(String location) {
        this.location = location;
        return;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
    
    public JavaConstructorImpl<?> getConstructor() {
        return constructorDefinition;
    }

    public void setConstructor(JavaConstructorImpl<?> definition) {
        this.constructorDefinition = definition;
    }
    
    public Method getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(Method initMethod) {
        this.initMethod = initMethod;
    }

    public Method getDestroyMethod() {
        return destroyMethod;
    }

    public void setDestroyMethod(Method destroyMethod) {
        this.destroyMethod = destroyMethod;
    }

    /* 
     * Returns the componentType for this Spring implementation 
     */
    public ComponentType getComponentType() {
        return componentType;
    }

    /*
     * Sets the componentType for this Spring implementation
     */
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public List<Service> getServices() {
        return componentType.getServices();
    }

    @Override
    public List<Reference> getReferences() {
        return componentType.getReferences();
    }

    @Override
    public List<Property> getProperties() {
        return componentType.getProperties();
    }

    /**
     * Returns the Spring Bean which implements a particular service
     * @param service the service
     * @return the bean which implements the service, as a SpringBeanElement
     */
    public SpringBeanElement getBeanFromService(Service service) {
        SpringBeanElement theBean = serviceMap.get(service.getName());
        return theBean;
    }

    /**
     * Sets the mapping from a service to the Spring Bean that implements the service
     * @param service the service
     * @param theBean a SpringBeanElement for the Bean implementing the service
     */
    public void setBeanForService(Service service, SpringBeanElement theBean) {
        serviceMap.put(service.getName(), theBean);
    }

    /**
     * Add a mapping from a SCA property name to a Java class for the property
     * @param propertyName
     * @param propertyClass
     */
    public void setPropertyClass(String propertyName, Class propertyClass) {
        if (propertyName == null || propertyClass == null)
            return;
        propertyMap.put(propertyName, propertyClass);
        return;
    } // end method setPropertyClass

    /**
     * Gets the Java Class for an SCA property 
     * @param propertyName - the property name
     * @return - a Class object for the type of the property
     */
    public Class getPropertyClass(String propertyName) {
        return propertyMap.get(propertyName);
    } // end method getPropertyClass
    
    public void setUnresolvedBeanRef(String refName, Reference reference) {
        if (refName == null || reference == null)
            return;
        unresolvedBeanRef.put(refName, reference);
        return;
    } // end method setUnresolvedBeanRef
    
    public Reference getUnresolvedBeanRef(String refName) {
        return unresolvedBeanRef.get(refName);
    } // end method getUnresolvedBeanRef
    
    public List<PolicyHandlerTuple> getPolicyHandlerClassNames() {
        return policyHandlerClassNames;
    }

    public void setPolicyHandlerClassNames(List<PolicyHandlerTuple> policyHandlerClassNames) {
        this.policyHandlerClassNames = policyHandlerClassNames;
    } // end method setPolicyHandlerClassNames
    
    
    /**
     * Use preProcess to validate and map the references and properties dynamically 
     */
    public void preProcess(Component component) {
        if (!(component instanceof RuntimeComponent))
            return;
        
        RuntimeComponent rtc = (RuntimeComponent) component;
        
        // Check if the SCDL is properly configured for all the services
        // exposed by Spring application context, otherwise report a error
        /*Enumeration<SpringBeanElement> itr = serviceMap.elements();
        while (itr.hasMoreElements()) {
        	SpringBeanElement beanElement = itr.nextElement();
        	if (!rtc.getServices().contains(beanElement.getId())) {
        		throw new AssertionError("Configuration Error:");
        	}
        }*/
        
        for (Reference reference : rtc.getReferences()) {
            if (unresolvedBeanRef.containsKey(reference.getName())) {
            	Reference ref = unresolvedBeanRef.get(reference.getName());
            	componentType.getReferences().add(
            			createReference(reference, ref.getInterfaceContract()));
            	unresolvedBeanRef.remove(reference.getName());
            }
        }

        for (Property property : rtc.getProperties()) {
        	if (unresolvedBeanRef.containsKey(property.getName())) {        		
        		componentType.getProperties().add(createProperty(property));
        		this.setPropertyClass(property.getName(), property.getClass());
        		unresolvedBeanRef.remove(property.getName());
            }
        }
        
        // Check if the SCDL is properly configured for all the SCA references
        // used in the Spring application context, otherwise report a error
        /*for (Reference reference: componentType.getReferences()) {
        	if (!rtc.getReferences().contains(reference.getName())) {
        		throw new AssertionError("Configuration Error:");
        	}
        }*/
        
        // Check if the SCDL is properly configured for all the SCA property
        // used in the Spring application context, otherwise report a error
        /*for (Property property: componentType.getProperties()) {
        	if (!rtc.getProperties().contains(property.getName())) {
        		throw new AssertionError("Configuration Error:");
        	}
        }*/      
    }
    
    protected Reference createReference(Reference reference, InterfaceContract interfaze) {
        Reference newReference;
        try {
            newReference = (Reference)reference.clone();
            if (newReference.getInterfaceContract() == null)
            	newReference.setInterfaceContract(interfaze);
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // should not ever happen
        }
        return newReference;
    }
    
    protected Property createProperty(Property property) {
        Property newProperty;
        try {
            newProperty = (Property)property.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // should not ever happen
        }
        return newProperty;
    }
}
