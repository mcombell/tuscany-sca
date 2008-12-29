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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.assembly.builder.impl.ProblemImpl;
import org.apache.tuscany.sca.assembly.xml.PolicyAttachPointProcessor;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.monitor.Problem;
import org.apache.tuscany.sca.monitor.Problem.Severity;
import org.apache.tuscany.sca.policy.PolicyFactory;

public class HessianBindingProcessor implements StAXArtifactProcessor<HessianBinding> {

    private QName BINDING_HESSIAN = new QName("http://hessian", "binding.hessian");

    private PolicyFactory policyFactory;
    private PolicyAttachPointProcessor policyProcessor;
    private Monitor monitor;

    public HessianBindingProcessor(ModelFactoryExtensionPoint modelFactories, Monitor monitor) {
        this.policyFactory = modelFactories.getFactory(PolicyFactory.class);
        this.policyProcessor = new PolicyAttachPointProcessor(policyFactory);
        this.monitor = monitor;
    }

    /**
     * Report a error.
     * 
     * @param problems
     * @param message
     * @param model
     */
    private void warning(String message, Object model, Object... messageParameters) {
        if (monitor != null) {
            Problem problem =
                new ProblemImpl(this.getClass().getName(), "binding-hessian-validation-messages", Severity.WARNING,
                                model, message, (Object[])messageParameters);
            monitor.problem(problem);
        }
    }

    /**
     * Report a error.
     * 
     * @param problems
     * @param message
     * @param model
     */
    private void error(String message, Object model, Object... messageParameters) {
        if (monitor != null) {
            Problem problem =
                new ProblemImpl(this.getClass().getName(), "binding-hessian-validation-messages", Severity.ERROR,
                                model, message, (Object[])messageParameters);
            monitor.problem(problem);
        }
    }

    public QName getArtifactType() {
        return BINDING_HESSIAN;
    }

    public Class<HessianBinding> getModelType() {
        return HessianBinding.class;
    }

    public HessianBinding read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        HessianBinding hessianBinding = new HessianBinding();

        // Read policies
        policyProcessor.readPolicies(hessianBinding, reader);

        // Read binding name
        String name = reader.getAttributeValue(null, "name");
        if (name != null) {
            hessianBinding.setName(name);
        }

        // Read binding URI
        String uri = reader.getAttributeValue(null, "uri");
        if (uri != null) {
            hessianBinding.setURI(uri);
        }

        return hessianBinding;
    }

    public void resolve(HessianBinding model, ModelResolver resolver) throws ContributionResolveException {
    }

    public void write(HessianBinding hessianBinding, XMLStreamWriter writer) throws ContributionWriteException,
        XMLStreamException {
        policyProcessor.writePolicyPrefixes(hessianBinding, writer);
        writer.writeStartElement(BINDING_HESSIAN.getNamespaceURI(), BINDING_HESSIAN.getLocalPart());
        policyProcessor.writePolicyAttributes(hessianBinding, writer);

        if (hessianBinding.getName() != null) {
            writer.writeAttribute("name", hessianBinding.getName());
        }

        if (hessianBinding.getURI() != null) {
            writer.writeAttribute("uri", hessianBinding.getURI());
        }

        writer.writeEndElement();
    }

}
