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

package org.apache.tuscany.sca.policy.confidentiality;

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

public class ConfidentialityPolicyProcessor implements StAXArtifactProcessor<ConfidentialityPolicy> {
    private static final QName KEY_STORE_QNAME = new QName(Constants.SCA10_TUSCANY_NS, "keyStore");
    private static final QName TRUST_STORE_QNAME = new QName(Constants.SCA10_TUSCANY_NS, "trustStore");

    private Monitor monitor;
    
    public ConfidentialityPolicyProcessor(ModelFactoryExtensionPoint modelFactories, Monitor monitor) {
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
            Problem problem = new ProblemImpl(this.getClass().getName(), "policy-security-validation-messages", Severity.ERROR, model, message, (Object[])messageParameters);
            monitor.problem(problem);
        }        
    }    
    
    public QName getArtifactType() {
        return ConfidentialityPolicy.NAME;
    }

    public Class<ConfidentialityPolicy> getModelType() {
        return ConfidentialityPolicy.class;
    }

    public ConfidentialityPolicy read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        ConfidentialityPolicy policy = new ConfidentialityPolicy();
        int event = reader.getEventType();
        QName start = reader.getName();
        QName name = null;
        while (true) {
            switch (event) {
                case START_ELEMENT:
                    name = reader.getName();
                    if(KEY_STORE_QNAME.equals(name)) {
                        //<tuscany:keyStore type="JKS" file="conf/tomcat.keystore" password="apache"/>
                        String type = reader.getAttributeValue(null, "type");
                        if(type == null) {
                            error("RequiredAttributeKeyStoreTypeMissing", reader);
                        } else {
                            policy.setKeyStoreType(type);    
                        }
                        
                        String file = reader.getAttributeValue(null, "file");
                        if(file == null) {
                            error("RequiredAttributeKeyStoreFileMissing", reader);
                        } else {
                            policy.setKeyStore(file);
                        }
                        
                        String password = reader.getAttributeValue(null, "password");
                        if(file == null) {
                            error("RequiredAttributeKeyStorePasswordMissing", reader);
                        } else {
                            policy.setKeyStorePassword(password);
                        }
                        
                    } else if(TRUST_STORE_QNAME.equals(name)) {
                        //<tuscany:trustStore type="" file="" password=""/>
                        String type = reader.getAttributeValue(null, "type");
                        if(type == null) {
                            error("RequiredAttributeTrustStoreTypeMissing", reader);
                        } else {
                            policy.setTrustStoreType(type);    
                        }
                        
                        String file = reader.getAttributeValue(null, "file");
                        if(file == null) {
                            error("RequiredAttributeTrusStoreFileMissing", reader);
                        } else {
                            policy.setTrustStore(file);
                        }
                        
                        String password = reader.getAttributeValue(null, "password");
                        if(file == null) {
                            error("RequiredAttributeTrustStorePasswordMissing", reader);
                        } else {
                            policy.setTrustStorePassword(password);
                        }

                    }
                    break;
                case END_ELEMENT:
                    if (start.equals(reader.getName())) {
                        if (reader.hasNext()) {
                            reader.next();
                        }
                        return policy;
                    }

            }
            if (reader.hasNext()) {
                event = reader.next();
            } else {
                return policy;
            }
        }    }

    public void write(ConfidentialityPolicy model, XMLStreamWriter writer) throws ContributionWriteException,
        XMLStreamException {
        // TODO Auto-generated method stub

    }

    public void resolve(ConfidentialityPolicy model, ModelResolver resolver) throws ContributionResolveException {

    }

}
