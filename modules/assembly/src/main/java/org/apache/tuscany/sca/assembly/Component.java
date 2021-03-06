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
package org.apache.tuscany.sca.assembly;

import java.util.List;

import org.apache.tuscany.sca.policy.PolicySubject;

/**
 * Represents a component. A component is a configured instance of an
 * implementation.
 * 
 * @version $Rev$ $Date$
 */
public interface Component extends Base, Extensible, PolicySubject, Cloneable {
    
    /**
     * Returns the URI of the component.
     *  
     * @return the URI of the component
     */
    String getURI();
    
    /**
     * Sets the URI of the component.
     * 
     * @param uri the URI of the component
     */
    void setURI(String uri);

    /**
     * Returns the name of the component.
     * 
     * @return the name of the component
     */
    String getName();

    /**
     * Sets the name of the component.
     * 
     * @param name the name of the component
     */
    void setName(String name);

    /**
     * Returns the component implementation.
     * 
     * @return the component implementation
     */
    Implementation getImplementation();

    /**
     * Sets the component implementation
     * 
     * @param implementation the component implementation
     */
    void setImplementation(Implementation implementation);

    /**
     * Returns a list of references used by the component.
     * 
     * @return a list of references used by the component
     */
    List<ComponentReference> getReferences();

    /**
     * Returns a list of services exposed by the component.
     * 
     * @return a list of services exposed by the component
     */
    List<ComponentService> getServices();

    /**
     * Returns a list of properties for the component.
     * 
     * @return a list of properties
     */
    List<ComponentProperty> getProperties();

    /**
     * Returns a constraining type defining the shape of the component.
     * 
     * @return a constraining type
     */
    ConstrainingType getConstrainingType();

    /**
     * Sets a constraining type defining the shape of the component.
     * 
     * @param constrainingType the constraining type
     */
    void setConstrainingType(ConstrainingType constrainingType);

    /**
     * Return the Boolean value of autowire
     * @return null/TRUE/FALSE
     */
    Boolean getAutowire();

    /**
     * Sets whether component references should be autowired.
     * 
     * @param autowire whether component references should be autowired
     */
    void setAutowire(Boolean autowire);
    

    /**
     * Returns a clone of the component.
     * 
     * @return a clone of the component
     * @throws CloneNotSupportedException
     */
    Object clone() throws CloneNotSupportedException;

}
