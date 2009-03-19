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

package org.apache.tuscany.sca.policy.authentication;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.StringTokenizer;

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
import org.apache.tuscany.sca.host.http.UserContext;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.monitor.Problem;
import org.apache.tuscany.sca.monitor.Problem.Severity;

/**
 *  <sca:policySet name="widgetBindingAuthenticationPolicySet"
 *       provides="sca:authentication"
 *       appliesTo="tuscany:binding.http">
 *       <tuscany:authenticationConfiguration>
 *            <tuscany:user username="user1" password="tuscany" roles="admin"/>
 *            <tuscany:user username="user2" password="tuscany" roles="admin, user"/>
 *            <tuscany:user username="user3" password="tuscany" roles="user"/>
 *       </tuscany:authenticationConfiguration>
 *  </sca:policySet>
 *
 *
 * @version $Rev$ $Date$
 */

public class AuthenticationConfigurationPolicyProcessor implements StAXArtifactProcessor<AuthenticationConfigurationPolicy> {
    private static final QName AUTHENTICATION_CONFIGURATION_QNAME = new QName(Constants.SCA10_TUSCANY_NS, "authenticationConfiguration");
    private static final QName USER_QNAME = new QName(Constants.SCA10_TUSCANY_NS, "user");

    private Monitor monitor;
    
    public AuthenticationConfigurationPolicyProcessor(ModelFactoryExtensionPoint modelFactories, Monitor monitor) {
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
    
    public QName getArtifactType() {
        return AuthenticationConfigurationPolicy.NAME;
    }

    public Class<AuthenticationConfigurationPolicy> getModelType() {
        return AuthenticationConfigurationPolicy.class;
    }

    public AuthenticationConfigurationPolicy read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        AuthenticationConfigurationPolicy authenticationConfiguration = new AuthenticationConfigurationPolicy();
        
        int event = reader.getEventType();
        QName start = reader.getName();
        QName name = null;
        while (true) {
            switch (event) {
                case START_ELEMENT:
                    name = reader.getName();
                    if(USER_QNAME.equals(name)) {
                        UserContext user = new UserContext();
                        //<tuscany:user username="user1" password="tuscany" roles="admin, user"/>
                        String username = reader.getAttributeValue(null, "username");
                        if(username == null) {
                            error("RequiredAttributeUsernameMissing", reader);
                        } else {
                            user.setUsername(username);    
                        }
                        
                        String password = reader.getAttributeValue(null, "password");
                        if(password == null) {
                            error("RequiredAttributePasswordMissing", reader);
                        } else {
                            user.setPassword(password);
                        }
                        
                        String roles = reader.getAttributeValue(null, "roles");
                        if(roles == null) {
                            error("RequiredAttributeRolesMissing", reader);
                        } else {
                            for (StringTokenizer tokens = new StringTokenizer(roles, ","); tokens.hasMoreTokens();) {
                                user.getRoles().add(tokens.nextToken());    
                            }
                        }
                        
                        authenticationConfiguration.getUsers().add(user);
                    }
                    break;
                case END_ELEMENT:
                    if (start.equals(reader.getName())) {
                        if (reader.hasNext()) {
                            reader.next();
                        }
                        return authenticationConfiguration;
                    }

            }
            if (reader.hasNext()) {
                event = reader.next();
            } else {
                return authenticationConfiguration;
            }
        }    
    }

    public void write(AuthenticationConfigurationPolicy model, XMLStreamWriter writer) throws ContributionWriteException, XMLStreamException {
        // TODO Auto-generated method stub
    }

    public void resolve(AuthenticationConfigurationPolicy model, ModelResolver resolver) throws ContributionResolveException {

    }

}
