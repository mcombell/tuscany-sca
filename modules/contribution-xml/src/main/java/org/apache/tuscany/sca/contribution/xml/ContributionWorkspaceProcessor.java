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

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.ContributionFactory;
import org.apache.tuscany.sca.contribution.Workspace;
import org.apache.tuscany.sca.contribution.processor.BaseStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;

/**
 * A contribution workspace processor.
 * 
 * @version $Rev$ $Date$
 */
public class ContributionWorkspaceProcessor extends BaseStAXArtifactProcessor implements StAXArtifactProcessor<Workspace> {
    
    private ContributionFactory contributionFactory;
    
    private static final String SCA10_TUSCANY_NS = "http://tuscany.apache.org/xmlns/sca/1.0";
    private static final QName WORKSPACE_QNAME = new QName(SCA10_TUSCANY_NS, "workspace");
    private static final QName CONTRIBUTION_QNAME = new QName(SCA10_TUSCANY_NS, "contribution");
    private static final String URI = "uri";
    private static final String LOCATION = "location";
    
    /**
     * Constructs a new contribution workspace processor.
     * @param contributionFactory
     * @param extensionProcessor
     */
    public ContributionWorkspaceProcessor(ContributionFactory contributionFactory, StAXArtifactProcessor<Object> extensionProcessor) {
        this.contributionFactory = contributionFactory;
    }
    
    public Workspace read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        
        Workspace workspace = null;
        Contribution contribution = null;
        
        // Read the workspace document
        while (reader.hasNext()) {
            int event = reader.getEventType();
            switch (event) {
                case START_ELEMENT:
                    QName name = reader.getName();

                    if (WORKSPACE_QNAME.equals(name)) {

                        // Read a <workspace>
                        workspace = contributionFactory.createWorkspace();

                    } else if (CONTRIBUTION_QNAME.equals(name)) {

                        // Read a <contribution>
                        contribution = contributionFactory.createContribution();
                        contribution.setURI(getString(reader, URI));
                        contribution.setLocation(getString(reader, LOCATION));
                        contribution.setUnresolved(true);
                        workspace.getContributions().add(contribution);
                    }
                    break;

                case END_ELEMENT:
                    name = reader.getName();

                    // Clear current state when reading reaching end element
                    if (CONTRIBUTION_QNAME.equals(name)) {
                        contribution = null;
                    }
                    break;
            }
            
            // Read the next element
            if (reader.hasNext()) {
                reader.next();
            }
        }
        return workspace;
    }
    
    public void write(Workspace workspace, XMLStreamWriter writer) throws ContributionWriteException, XMLStreamException {
        
        // Write <workspace> element
        writeStartDocument(writer, WORKSPACE_QNAME);

        // Write <contribution> elements
        for (Contribution contribution: workspace.getContributions()) {
            writeStart(writer, CONTRIBUTION_QNAME,
                           new XAttr(URI, contribution.getURI()), new XAttr(LOCATION, contribution.getLocation()));
            writeEnd(writer);
        }

        writeEndDocument(writer);
    }
    
    public void resolve(Workspace workspace, ModelResolver resolver) throws ContributionResolveException {
        //TODO Resolve contribution models
    }
    
    public QName getArtifactType() {
        return WORKSPACE_QNAME;
    }
    
    public Class<Workspace> getModelType() {
        return Workspace.class;
    }
}