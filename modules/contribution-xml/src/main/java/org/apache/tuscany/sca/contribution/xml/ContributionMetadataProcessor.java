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
package org.apache.tuscany.sca.contribution.xml;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.assembly.Composite;
import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.ContributionFactory;
import org.apache.tuscany.sca.contribution.Export;
import org.apache.tuscany.sca.contribution.Import;
import org.apache.tuscany.sca.contribution.processor.BaseStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;

/**
 * Processor for contribution metadata
 * 
 * @version $Rev$ $Date$
 */
public class ContributionMetadataProcessor extends BaseStAXArtifactProcessor implements StAXArtifactProcessor<Contribution> {
    
    private static final String SCA10_NS = "http://www.osoa.org/xmlns/sca/1.0";
    
    private static final QName CONTRIBUTION_QNAME = new QName(SCA10_NS, "contribution");
    private static final QName DEPLOYABLE_QNAME = new QName(SCA10_NS, "deployable");
    
    private final AssemblyFactory assemblyFactory;
    private final ContributionFactory contributionFactory;
    
    private final StAXArtifactProcessor<Object> extensionProcessor;

    public ContributionMetadataProcessor(AssemblyFactory assemblyFactory, ContributionFactory contributionFactory, StAXArtifactProcessor<Object> extensionProcessor) {
        this.assemblyFactory = assemblyFactory;
        this.contributionFactory = contributionFactory;
        this.extensionProcessor = extensionProcessor;
    }
    
    
    public QName getArtifactType() {
        return CONTRIBUTION_QNAME;
    }

    public Class<Contribution> getModelType() {
        return Contribution.class;
    }

    public Contribution read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        Contribution contribution = null;
        QName name = null;
        
        while (reader.hasNext()) {
            int event = reader.getEventType();
            switch (event) {
                case START_ELEMENT:
                    name = reader.getName();
                    
                    if (CONTRIBUTION_QNAME.equals(name)) {

                        // Read <contribution>
                        contribution = this.contributionFactory.createContribution();
                        contribution.setUnresolved(true);
                        
                    } else if (DEPLOYABLE_QNAME.equals(name)) {
                        
                        
                        // Read <deployable>
                        QName compositeName = getQName(reader, "composite");
                        if (compositeName == null) {
                            throw new ContributionReadException("Attribute 'composite' is missing");
                        }

                        if (contribution != null) {
                            Composite composite = assemblyFactory.createComposite();
                            composite.setName(compositeName);
                            composite.setUnresolved(true);
                            contribution.getDeployables().add(composite);
                            
                        }
                    } else{

                        // Read an extension element
                        Object extension = extensionProcessor.read(reader);
                        if (extension != null && contribution != null) {
                            if (extension instanceof Import) {
                                contribution.getImports().add((Import)extension);
                            } else if (extension instanceof Export) {
                                contribution.getExports().add((Export)extension);
                            }
                        }
                    }
                    break;
                    
                case XMLStreamConstants.END_ELEMENT:
                    if (CONTRIBUTION_QNAME.equals(reader.getName())) {
                        return contribution;
                    }
                    break;        
            }
            
            //Read the next element
            if (reader.hasNext()) {
                reader.next();
            }
        }
        
        return contribution;
    }

    public void write(Contribution contribution, XMLStreamWriter writer) throws ContributionWriteException, XMLStreamException {
        
        // Write <contribution>
        writeStartDocument(writer, CONTRIBUTION_QNAME.getNamespaceURI(), CONTRIBUTION_QNAME.getLocalPart());

        // Write <import>
        for (Import imp: contribution.getImports()) {
            extensionProcessor.write(imp, writer);
        }
        
        // Write <export>
        for (Export export: contribution.getExports()) {
            extensionProcessor.write(export, writer);
        }
    
        // Write <deployable>
        for (Composite deployable: contribution.getDeployables()) {
            writeStart(writer, DEPLOYABLE_QNAME.getNamespaceURI(), DEPLOYABLE_QNAME.getLocalPart(),
                       new XAttr("composite", deployable.getName()));
            writeEnd(writer);
        }
        
        writeEndDocument(writer);
    }

    public void resolve(Contribution model, ModelResolver resolver) throws ContributionResolveException {
        model.setUnresolved(false);
        
        // Resolve deployable composites
        List<Composite> deployables = model.getDeployables();
        for (int i = 0, n = deployables.size(); i < n; i++) {
            Composite deployable = deployables.get(i);
            Composite resolved = (Composite)resolver.resolveModel(Composite.class, deployable);
            if (resolved != deployable) {
                deployables.set(i, resolved);
            }
        }
    }
}