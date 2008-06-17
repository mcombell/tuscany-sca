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

package org.apache.tuscany.sca.databinding.axiom.module;

import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.core.ModuleActivator;
import org.apache.tuscany.sca.databinding.DataBindingExtensionPoint;
import org.apache.tuscany.sca.databinding.TransformerExtensionPoint;
import org.apache.tuscany.sca.databinding.axiom.AxiomDataBinding;
import org.apache.tuscany.sca.databinding.axiom.OMElement2Object;
import org.apache.tuscany.sca.databinding.axiom.OMElement2String;
import org.apache.tuscany.sca.databinding.axiom.OMElement2XMLStreamReader;
import org.apache.tuscany.sca.databinding.axiom.Object2OMElement;
import org.apache.tuscany.sca.databinding.axiom.String2OMElement;
import org.apache.tuscany.sca.databinding.axiom.XMLStreamReader2OMElement;

/**
 * Module activator for AXIOM databinding
 * 
 * @version $Rev$ $Date$
 */
public class AxiomDataBindingModuleActivator implements ModuleActivator {

    public Object[] getExtensionPoints() {
        return null;
    }

    public void start(ExtensionPointRegistry registry) {
        DataBindingExtensionPoint dataBindings = registry.getExtensionPoint(DataBindingExtensionPoint.class);
        dataBindings.addDataBinding(new AxiomDataBinding());

        TransformerExtensionPoint transformers = registry.getExtensionPoint(TransformerExtensionPoint.class);
        transformers.addTransformer(new Object2OMElement());
        transformers.addTransformer(new OMElement2Object());
        transformers.addTransformer(new OMElement2String());
        transformers.addTransformer(new OMElement2XMLStreamReader());
        transformers.addTransformer(new String2OMElement());
        transformers.addTransformer(new XMLStreamReader2OMElement());
    }

    public void stop(ExtensionPointRegistry registry) {
    }

}