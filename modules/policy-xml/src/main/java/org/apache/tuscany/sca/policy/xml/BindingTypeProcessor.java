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

package org.apache.tuscany.sca.policy.xml;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.contribution.processor.ContributionResolveException;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.core.FactoryExtensionPoint;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.policy.BindingType;
import org.apache.tuscany.sca.policy.ExtensionType;
import org.apache.tuscany.sca.policy.PolicyFactory;

/**
 * Processor for handling XML models of BindingType meta data definitions
 *
 * @version $Rev$ $Date$
 */
public class BindingTypeProcessor extends ExtensionTypeProcessor {

    public BindingTypeProcessor(PolicyFactory policyFactory,
                                StAXArtifactProcessor<Object> extensionProcessor,
                                Monitor monitor) {
        super(policyFactory, extensionProcessor, monitor);
    }

    public BindingTypeProcessor(FactoryExtensionPoint modelFactories,
                                StAXArtifactProcessor<Object> extensionProcessor,
                                Monitor monitor) {
        super(modelFactories.getFactory(PolicyFactory.class), extensionProcessor, monitor);
    }

    public QName getArtifactType() {
        return BINDING_TYPE_QNAME;
    }

    @Override
    protected ExtensionType resolveExtensionType(ExtensionType extnType, ModelResolver resolver)
        throws ContributionResolveException {
        if (extnType instanceof BindingType) {
            BindingType bindingType = (BindingType)extnType;
            return resolver.resolveModel(BindingType.class, bindingType);
        } else {
            return extnType;
        }

    }
}
