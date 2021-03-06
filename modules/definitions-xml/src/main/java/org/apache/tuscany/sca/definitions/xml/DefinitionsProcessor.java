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

package org.apache.tuscany.sca.definitions.xml;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.assembly.Binding;
import org.apache.tuscany.sca.contribution.processor.BaseStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.ContributionReadException;
import org.apache.tuscany.sca.contribution.processor.ContributionResolveException;
import org.apache.tuscany.sca.contribution.processor.ContributionWriteException;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.core.FactoryExtensionPoint;
import org.apache.tuscany.sca.definitions.Definitions;
import org.apache.tuscany.sca.definitions.DefinitionsFactory;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.policy.BindingType;
import org.apache.tuscany.sca.policy.ImplementationType;
import org.apache.tuscany.sca.policy.Intent;
import org.apache.tuscany.sca.policy.PolicySet;

/**
 * Processor for SCA Definitions
 *
 * @version $Rev$ $Date$
 */
public class DefinitionsProcessor extends BaseStAXArtifactProcessor implements StAXArtifactProcessor<Definitions> {

    private StAXArtifactProcessor<Object> extensionProcessor;
    private DefinitionsFactory definitionsFactory;
    private Monitor monitor;

    public static final String SCA11_NS = "http://docs.oasis-open.org/ns/opencsa/sca/200903";
    public static final String BINDING = "binding";
    public static final String IMPLEMENTATION = "implementation";
    public static final String DEFINITIONS = "definitions";
    public static final QName DEFINITIONS_QNAME = new QName(SCA11_NS, DEFINITIONS);
    public static final String TARGET_NAMESPACE = "targetNamespace";
    public static final String NAME = "name";

    public DefinitionsProcessor(FactoryExtensionPoint factoryExtensionPoint,
                                StAXArtifactProcessor<Object> extensionProcessor,
                                Monitor monitor) {
        this.extensionProcessor = extensionProcessor;
        this.monitor = monitor;
        this.definitionsFactory = factoryExtensionPoint.getFactory(DefinitionsFactory.class);
    }

    public Definitions read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        QName name = null;
        Definitions definitions = null;
        String targetNamespace = null;

        while (reader.hasNext()) {
            int event = reader.getEventType();
            switch (event) {
                case START_ELEMENT: {
                    name = reader.getName();
                    if (DEFINITIONS_QNAME.equals(name)) {
                        definitions = definitionsFactory.createDefinitions();
                        targetNamespace = reader.getAttributeValue(null, TARGET_NAMESPACE);
                        definitions.setTargetNamespace(targetNamespace);
                    } else {
                        Object extension = extensionProcessor.read(reader);
                        if (extension != null) {
                            if (extension instanceof Intent) {
                                Intent intent = (Intent)extension;
                                intent.setName(new QName(targetNamespace, intent.getName().getLocalPart()));
                                definitions.getIntents().add(intent);
                                for (Intent i : intent.getQualifiedIntents()) {
                                    i.setName(new QName(targetNamespace, i.getName().getLocalPart()));
                                }
                            } else if (extension instanceof PolicySet) {
                                PolicySet policySet = (PolicySet)extension;
                                policySet.setName(new QName(targetNamespace, policySet.getName().getLocalPart()));
                                definitions.getPolicySets().add(policySet);
                            } else if (extension instanceof Binding) {
                                Binding binding = (Binding)extension;
                                definitions.getBindings().add(binding);
                            } else if (extension instanceof BindingType) {
                                definitions.getBindingTypes().add((BindingType)extension);
                            } else if (extension instanceof ImplementationType) {
                                definitions.getImplementationTypes().add((ImplementationType)extension);
                            }
                        }
                        break;
                    }
                }

                case XMLStreamConstants.CHARACTERS:
                    break;

                case END_ELEMENT:
                    name = reader.getName();
                    if (DEFINITIONS_QNAME.equals(name)) {
                        return definitions;
                    }
                    break;
            }

            //Read the next element
            if (reader.hasNext()) {
                reader.next();
            }
        }
        return definitions;
    }

    public void write(Definitions definitions, XMLStreamWriter writer) throws ContributionWriteException,
        XMLStreamException {

        writeStartDocument(writer, SCA11_NS, DEFINITIONS, new XAttr(TARGET_NAMESPACE, definitions.getTargetNamespace()));

        for (Intent policyIntent : definitions.getIntents()) {
            extensionProcessor.write(policyIntent, writer);
        }

        for (PolicySet policySet : definitions.getPolicySets()) {
            extensionProcessor.write(policySet, writer);
        }

        for (BindingType bindingType : definitions.getBindingTypes()) {
            extensionProcessor.write(bindingType, writer);
        }

        for (ImplementationType implType : definitions.getImplementationTypes()) {
            extensionProcessor.write(implType, writer);
        }

        writeEndDocument(writer);
    }

    public void resolve(Definitions scaDefns, ModelResolver resolver) throws ContributionResolveException {
        // start by adding all of the top level artifacts into the resolver as there
        // are many cross artifact references in a definitions file and we don't want
        // to be dependent on the order things appear

        List<Intent> intents = new ArrayList<Intent>();
        List<PolicySet> policySets = new ArrayList<PolicySet>();
        List<PolicySet> referredPolicySets = new ArrayList<PolicySet>();

        for (Intent intent : scaDefns.getIntents()) {
            intents.add(intent);
            resolver.addModel(intent);
            for (Intent i : intent.getQualifiedIntents()) {
                intents.add(i);
                resolver.addModel(i);
            }
        }

        for (PolicySet policySet : scaDefns.getPolicySets()) {
            if (policySet.getReferencedPolicySets().isEmpty()) {
                policySets.add(policySet);
            } else {
                referredPolicySets.add(policySet);
            }

            resolver.addModel(policySet);
        }

        for (BindingType bindingType : scaDefns.getBindingTypes()) {
            resolver.addModel(bindingType);
        }

        for (ImplementationType implType : scaDefns.getImplementationTypes()) {
            resolver.addModel(implType);
        }

        // now resolve everything to ensure that any references between
        // artifacts are satisfied

        for (Intent policyIntent : intents)
            extensionProcessor.resolve(policyIntent, resolver);

        for (PolicySet policySet : policySets)
            extensionProcessor.resolve(policySet, resolver);

        for (PolicySet policySet : referredPolicySets)
            extensionProcessor.resolve(policySet, resolver);

        for (BindingType bindingType : scaDefns.getBindingTypes()) {
            extensionProcessor.resolve(bindingType, resolver);
        }

        for (ImplementationType implementationType : scaDefns.getImplementationTypes()) {
            extensionProcessor.resolve(implementationType, resolver);
        }
    }

    public QName getArtifactType() {
        return DEFINITIONS_QNAME;
    }

    public Class<Definitions> getModelType() {
        return Definitions.class;
    }

}
