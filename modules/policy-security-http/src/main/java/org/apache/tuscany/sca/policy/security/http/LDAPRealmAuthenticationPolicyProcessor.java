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

package org.apache.tuscany.sca.policy.security.http;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.assembly.builder.impl.ProblemImpl;
import org.apache.tuscany.sca.assembly.xml.Constants;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.monitor.Problem;
import org.apache.tuscany.sca.monitor.Problem.Severity;


/**
 *
 * @version $Rev$ $Date$
 */

public class LDAPRealmAuthenticationPolicyProcessor implements StAXArtifactProcessor<LDAPRealmAuthenticationPolicy> {
    static final QName REALM_QNAME = new QName(Constants.SCA10_TUSCANY_NS,"realm");
    public static final QName REALM_CONFIGURATION_QNAME = new QName(Constants.SCA10_TUSCANY_NS,"realmConfigurationName");
    
    private Monitor monitor;
    
    
    public LDAPRealmAuthenticationPolicyProcessor(ModelFactoryExtensionPoint modelFactories, Monitor monitor) {
        this.monitor = monitor;
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
                 Problem problem = new ProblemImpl(this.getClass().getName(), "policy-security-http-validation-messages", Severity.ERROR, model, message, (Object[])messageParameters);
             monitor.problem(problem);
         }        
    }
    
    public Class<LDAPRealmAuthenticationPolicy> getModelType() {
        return LDAPRealmAuthenticationPolicy.class;
    }
    

    public QName getArtifactType() {
        return LDAPRealmAuthenticationPolicy.NAME;
    }
    
    public LDAPRealmAuthenticationPolicy read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        LDAPRealmAuthenticationPolicy policy = new LDAPRealmAuthenticationPolicy();
        int event = reader.getEventType();
        QName name = null;
        
        while (reader.hasNext()) {
            event = reader.getEventType();
            switch (event) {
                case START_ELEMENT : {
                    name = reader.getName();
                    if (name.equals(REALM_QNAME)) {
                        String realmName = reader.getElementText();
                        if (realmName != null) {
                            policy.setRealmName(realmName.trim());
                        }
                    }
                    if (name.equals(REALM_CONFIGURATION_QNAME)) {
                        String realmConfigurationName = reader.getElementText();
                        if (realmConfigurationName != null) {
                            policy.setRealmConfigurationName(realmConfigurationName.trim());
                        }
                    } 

                    break;
                }
            }
            
            if ( event == END_ELEMENT ) {
                if ( LDAPRealmAuthenticationPolicy.NAME.equals(reader.getName()) ) {
                    break;
                } 
            }
            
            //Read the next element
            if (reader.hasNext()) {
                reader.next();
            }
        }
         
        return policy;
    }

    public void write(LDAPRealmAuthenticationPolicy policy, XMLStreamWriter writer) throws ContributionWriteException,
                                                        XMLStreamException {
        String prefix = "tuscany";
        writer.writeStartElement(prefix, 
                                 LDAPRealmAuthenticationPolicy.NAME.getLocalPart(),
                                 LDAPRealmAuthenticationPolicy.NAME.getNamespaceURI());
        writer.writeNamespace("tuscany", Constants.SCA10_TUSCANY_NS);
        
       
        writer.writeEndElement();
    }


    public void resolve(LDAPRealmAuthenticationPolicy policy, ModelResolver resolver) throws ContributionResolveException {

    }
}
