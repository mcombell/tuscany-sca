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
package org.apache.tuscany.sca.implementation.ejb.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.sca.assembly.Component;
import org.apache.tuscany.sca.assembly.ConstrainingType;
import org.apache.tuscany.sca.assembly.Property;
import org.apache.tuscany.sca.assembly.Reference;
import org.apache.tuscany.sca.assembly.Service;
import org.apache.tuscany.sca.assembly.builder.ComponentPreProcessor;
import org.apache.tuscany.sca.implementation.ejb.EJBImplementation;
import org.apache.tuscany.sca.runtime.RuntimeComponent;


/**
 * The model representing an EJB implementation in an SCA assembly model.
 *
 * @version $Rev$ $Date$
 */
class EJBImplementationImpl implements EJBImplementation, ComponentPreProcessor {

    private List<Property> properties = new ArrayList<Property>(); 
    private List<Service> services = new ArrayList<Service>(); 
    private List<Reference> references = new ArrayList<Reference>(); 
    private String ejbLink;
    private String uri;
    private boolean unresolved;

    /**
     * Constructs a new EJB implementation.
     */
    EJBImplementationImpl() {
    }

    public ConstrainingType getConstrainingType() {
        // The EJB implementation does not support constrainingTypes
        return null;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<Service> getServices() {
        return services;
    }
    
    public List<Reference> getReferences() {
        return references;
    }

    public String getEJBLink() {
        return ejbLink;
    }
    
    public void setConstrainingType(ConstrainingType constrainingType) {
        // The EJB implementation does not support constrainingTypes
    }

    public void setEJBLink(String ejbLink) {
        this.ejbLink = ejbLink;
    }
    
    public String getURI() {
        return uri;
    }
    
    public void setURI(String uri) {
        this.uri = uri;
    }
    
    public boolean isUnresolved() {
        return unresolved;
    }

    public void setUnresolved(boolean unresolved) {
        this.unresolved = unresolved;
    }

    /**
     * Use preProcess to add any references and properties dynamically
     */
    public void preProcess(Component component) {
        if (!(component instanceof RuntimeComponent)) {
            return;
        }
        RuntimeComponent rtc = (RuntimeComponent) component;
        
        for (Reference reference : rtc.getReferences()) {
            if (getReference(reference.getName()) == null) {
                getReferences().add(createReference(reference));
            }
        }

        for (Property property : rtc.getProperties()) {
            if (getProperty(property.getName()) == null) {
                getProperties().add(createProperty(property));
            }
        }
        
        for(Service service : rtc.getServices()) {
            if (getService(service.getName()) == null) {
                getServices().add(createService(service));
            }
        }
    }

    protected Reference getReference(String name) {
        for (Reference reference : getReferences()) {
            if (reference.getName().equals(name)) {
                return reference;
            }
        }
        return null;
    }

    protected Reference createReference(Reference reference) {
        Reference newReference;
        try {
            newReference = (Reference)reference.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // should not ever happen
        }
        return newReference;
    }

    protected Property getProperty(String name) {
        for (Property property : getProperties()) {
            if (property.getName().equals(name)) {
                return property;
            }
        }
        return null;
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

    protected Service getService(String name) {
        for (Service service : getServices()) {
            if (service.getName().equals(name)) {
                return service;
            }
        }
        return null;
    }

    protected Service createService(Service service) {
        Service newService;
        try {
            newService = (Service)service.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // should not ever happen
        }
        return newService;
    }
}
