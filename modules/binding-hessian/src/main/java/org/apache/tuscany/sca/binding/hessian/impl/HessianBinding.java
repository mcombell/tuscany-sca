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
package org.apache.tuscany.sca.binding.hessian.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.sca.assembly.Binding;

public class HessianBinding implements Binding {

    // properties required to implement the Tuscany
    // binding extension SPI
    private String uri = null;
    private String name = null;
    private boolean unresolved = false;
    private List<Object> extensions = new ArrayList<Object>();

    public HessianBinding() {
        super();
    }

    /**
     * Returns the binding URI.
     * 
     * @return the binding URI
     */
    public String getURI() {
        return this.uri;
    }

    /**
     * Sets the binding URI.
     * 
     * @param uri the binding URI
     */
    public void setURI(String uri) {
        this.uri = uri;
    }

    /**
     * Returns the binding name.
     * 
     * @return the binding name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the binding name.
     * 
     * @param name the binding name
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean isUnresolved() {
        return this.unresolved;
    }

    public void setUnresolved(boolean unresolved) {
        this.unresolved = unresolved;
    }

    public List<Object> getExtensions() {
        return extensions;
    }

    /**
     * Clone the binding
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
