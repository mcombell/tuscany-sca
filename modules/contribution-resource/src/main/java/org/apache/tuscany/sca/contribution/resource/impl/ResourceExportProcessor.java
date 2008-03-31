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

package org.apache.tuscany.sca.contribution.resource.impl;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.resource.ResourceExport;
import org.apache.tuscany.sca.contribution.resource.ResourceImportExportFactory;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;

/**
 * Artifact processor for Resource export
 * 
 * @version $Rev$ $Date$
 */
public class ResourceExportProcessor implements StAXArtifactProcessor<ResourceExport> {

    private static final String SCA10_NS = "http://www.osoa.org/xmlns/sca/1.0";
    private static final QName EXPORT_RESOURCE = new QName(SCA10_NS, "export.resource");
    private static final String URI = "uri";
    
    private final ResourceImportExportFactory factory;
    
    public ResourceExportProcessor(ModelFactoryExtensionPoint modelFactories) {
        this.factory = modelFactories.getFactory(ResourceImportExportFactory.class);
    }

    public QName getArtifactType() {
        return EXPORT_RESOURCE;
    }
    
    public Class<ResourceExport> getModelType() {
        return ResourceExport.class;
    }
    
    /**
     * Process <export.resource uri=""/>
     */
    public ResourceExport read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
    	ResourceExport resourceExport = this.factory.createResourceExport();
        QName element = null;
        
        while (reader.hasNext()) {
            int event = reader.getEventType();
            switch (event) {
                case START_ELEMENT:
                    element = reader.getName();
                    
                    // Read <export.resource>
                    if (EXPORT_RESOURCE.equals(element)) {
                        String uri = reader.getAttributeValue(null, URI);
                        if (uri == null) {
                            throw new ContributionReadException("Attribute 'uri' is missing");
                        }
                        resourceExport.setURI(uri);
                    } 
                    
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (EXPORT_RESOURCE.equals(reader.getName())) {
                        return resourceExport;
                    }
                    break;        
            }
            
            // Read the next element
            if (reader.hasNext()) {
                reader.next();
            }
        }
        
        return resourceExport;
    }

    public void write(ResourceExport resourceExport, XMLStreamWriter writer) throws ContributionWriteException, XMLStreamException {
        
        // Write <export.resource>
        writer.writeStartElement(EXPORT_RESOURCE.getNamespaceURI(), EXPORT_RESOURCE.getLocalPart());
        
        if (resourceExport.getURI() != null) {
            writer.writeAttribute(URI, resourceExport.getURI());
        }
        
        writer.writeEndElement();
    }

    public void resolve(ResourceExport model, ModelResolver resolver) throws ContributionResolveException {
        
    }
}