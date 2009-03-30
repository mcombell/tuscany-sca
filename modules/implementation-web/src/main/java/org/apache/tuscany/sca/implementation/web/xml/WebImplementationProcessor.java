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
package org.apache.tuscany.sca.implementation.web.xml;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.assembly.ComponentReference;
import org.apache.tuscany.sca.assembly.ComponentType;
import org.apache.tuscany.sca.assembly.xml.Constants;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.jee.JspReferenceTagInfo;
import org.apache.tuscany.sca.contribution.jee.WebModuleInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEExtension;
import org.apache.tuscany.sca.contribution.jee.JavaEEOptionalExtension;
import org.apache.tuscany.sca.contribution.jee.impl.WebModuleInfoImpl;
import org.apache.tuscany.sca.contribution.processor.BaseStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;
import org.apache.tuscany.sca.implementation.java.DefaultJavaImplementationFactory;
import org.apache.tuscany.sca.implementation.java.IntrospectionException;
import org.apache.tuscany.sca.implementation.java.JavaImplementation;
import org.apache.tuscany.sca.implementation.java.JavaImplementationFactory;
import org.apache.tuscany.sca.implementation.web.WebImplementation;
import org.apache.tuscany.sca.implementation.web.WebImplementationFactory;
import org.apache.tuscany.sca.implementation.web.introspect.PropertyProcessor;
import org.apache.tuscany.sca.implementation.web.introspect.ReferenceProcessor;
import org.apache.tuscany.sca.interfacedef.InvalidInterfaceException;
import org.apache.tuscany.sca.interfacedef.java.JavaInterface;
import org.apache.tuscany.sca.interfacedef.java.JavaInterfaceContract;
import org.apache.tuscany.sca.interfacedef.java.JavaInterfaceFactory;
import org.apache.tuscany.sca.monitor.Monitor;


/**
 * Implements a StAX artifact processor for Web implementations.
 */
public class WebImplementationProcessor extends BaseStAXArtifactProcessor implements StAXArtifactProcessor<WebImplementation> {
    private static final QName IMPLEMENTATION_WEB = new QName(Constants.SCA10_NS, "implementation.web");
    
    private AssemblyFactory assemblyFactory;
    private WebImplementationFactory implementationFactory;
    private Monitor monitor;
    private JavaEEExtension jeeExtension;
    private JavaEEOptionalExtension jeeOptionalExtension;
    private JavaImplementationFactory javaImplementationFactory;
    private JavaInterfaceFactory javaInterfaceFactory;
    
    public WebImplementationProcessor(ModelFactoryExtensionPoint modelFactories, Monitor monitor) {
        this.assemblyFactory = modelFactories.getFactory(AssemblyFactory.class);
        this.implementationFactory = modelFactories.getFactory(WebImplementationFactory.class);
        this.jeeExtension = modelFactories.getFactory(JavaEEExtension.class);
        this.jeeOptionalExtension = modelFactories.getFactory(JavaEEOptionalExtension.class);
        this.monitor = monitor;
        
        this.javaImplementationFactory = new DefaultJavaImplementationFactory();
        this.javaInterfaceFactory = modelFactories.getFactory(JavaInterfaceFactory.class);
        javaImplementationFactory.addClassVisitor(new ReferenceProcessor(assemblyFactory, javaInterfaceFactory));
        javaImplementationFactory.addClassVisitor(new PropertyProcessor(assemblyFactory));
    }

    public QName getArtifactType() {
        // Returns the QName of the XML element processed by this processor
        return IMPLEMENTATION_WEB;
    }

    public Class<WebImplementation> getModelType() {
        // Returns the type of model processed by this processor
        return WebImplementation.class;
    }

    public WebImplementation read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        
        // Read an <implementation.web> element
        WebImplementation implementation = implementationFactory.createWebImplementation();
        implementation.setUnresolved(true);

        // Read the webapp uri attribute
        String webURI = getString(reader, "web-uri");
        if (webURI != null) {
            implementation.setWebURI(webURI);

            // Set the URI of the component type
            // TODO: This should point to the base uri of the WAR file
            implementation.setURI(webURI);
        }

        // Skip to end element
        while (reader.hasNext()) {
            if (reader.next() == END_ELEMENT && IMPLEMENTATION_WEB.equals(reader.getName())) {
                break;
            }
        }
        
        return implementation;
    }

    public void resolve(WebImplementation implementation, ModelResolver resolver) throws ContributionResolveException {
        
        // Resolve the component type
        String uri = implementation.getURI();
        if (uri != null) {
            WebModuleInfo webModuleInfo = new WebModuleInfoImpl();
            webModuleInfo.setUri(URI.create(uri));
            webModuleInfo = resolver.resolveModel(WebModuleInfo.class, webModuleInfo);
            if(jeeOptionalExtension != null) {
                ComponentType ct = jeeOptionalExtension.createImplementationWebComponentType(webModuleInfo);
                implementation.getReferences().addAll(ct.getReferences());
                implementation.getProperties().addAll(ct.getProperties());
            }
            
            // Introspection of classes
            List<Class<?>> webArtifacts = new ArrayList<Class<?>>();
            webArtifacts.addAll(webModuleInfo.getServletClasses());
            webArtifacts.addAll(webModuleInfo.getFilterClasses());
            webArtifacts.addAll(webModuleInfo.getListenerClasses());
            webArtifacts.addAll(webModuleInfo.getJSFClasses());
            JavaImplementation ji = javaImplementationFactory.createJavaImplementation();
            for(Class<?> clazz : webArtifacts) {
                try {
                    javaImplementationFactory.createJavaImplementation(ji, clazz);
                } catch (IntrospectionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            implementation.getReferences().addAll(ji.getReferences());
            implementation.getProperties().addAll(ji.getProperties());

            // SCA References in JSP Tags
            for(JspReferenceTagInfo jspRefTag : webModuleInfo.getJspReferenceTags()) {
                ComponentReference ref = assemblyFactory.createComponentReference();
                ref.setName(jspRefTag.name);
                JavaInterfaceContract intfContract = javaInterfaceFactory.createJavaInterfaceContract();
                try {
                    intfContract.setInterface(javaInterfaceFactory.createJavaInterface(jspRefTag.type));
                } catch (InvalidInterfaceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ref.setInterfaceContract(intfContract);
                implementation.getReferences().add(ref);
            }
            
            // Process componentType side file
            ComponentType componentType = assemblyFactory.createComponentType();
            componentType.setURI("web.componentType");
            componentType = resolver.resolveModel(ComponentType.class, componentType);
            if (!componentType.isUnresolved()) {
                
                // Initialize the implementation's services, references and properties
                implementation.getServices().addAll(componentType.getServices());
                implementation.getReferences().addAll(componentType.getReferences());
                implementation.getProperties().addAll(componentType.getProperties());
            }
        }
        implementation.setUnresolved(false);
    }

    public void write(WebImplementation implementation, XMLStreamWriter writer) throws ContributionWriteException, XMLStreamException {
        
        // Write <implementation.web>
        writeStart(writer, IMPLEMENTATION_WEB.getNamespaceURI(), IMPLEMENTATION_WEB.getLocalPart(),
                   new XAttr("web-uri", implementation.getWebURI()));
        
        writeEnd(writer);
    }
}
