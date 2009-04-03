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
package org.apache.tuscany.sca.implementation.ejb.xml;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;

import java.net.URI;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.assembly.ComponentType;
import org.apache.tuscany.sca.assembly.builder.impl.ProblemImpl;
import org.apache.tuscany.sca.assembly.xml.Constants;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.jee.EjbModuleInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEExtension;
import org.apache.tuscany.sca.contribution.jee.JavaEEOptionalExtension;
import org.apache.tuscany.sca.contribution.jee.impl.EjbModuleInfoImpl;
import org.apache.tuscany.sca.contribution.processor.BaseStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;
import org.apache.tuscany.sca.implementation.ejb.EJBImplementation;
import org.apache.tuscany.sca.implementation.ejb.EJBImplementationFactory;
import org.apache.tuscany.sca.implementation.java.DefaultJavaImplementationFactory;
import org.apache.tuscany.sca.implementation.java.IntrospectionException;
import org.apache.tuscany.sca.implementation.java.JavaImplementation;
import org.apache.tuscany.sca.implementation.java.JavaImplementationFactory;
import org.apache.tuscany.sca.implementation.java.introspect.impl.PropertyProcessor;
import org.apache.tuscany.sca.implementation.java.introspect.impl.ReferenceProcessor;
import org.apache.tuscany.sca.implementation.java.introspect.impl.ServiceProcessor;
import org.apache.tuscany.sca.interfacedef.java.JavaInterfaceFactory;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.monitor.Problem;
import org.apache.tuscany.sca.monitor.Problem.Severity;


/**
 * Implements a StAX artifact processor for EJB implementations.
 *
 * @version $Rev$ $Date$
 */
public class EJBImplementationProcessor extends BaseStAXArtifactProcessor implements StAXArtifactProcessor<EJBImplementation> {
    private static final QName IMPLEMENTATION_EJB = new QName(Constants.SCA10_NS, "implementation.ejb");
    
    private AssemblyFactory assemblyFactory;
    private EJBImplementationFactory implementationFactory;
    private Monitor monitor;
    private JavaEEExtension jeeExtension;
    private JavaEEOptionalExtension jeeOptionalExtension;
    private JavaImplementationFactory javaImplementationFactory;
    private JavaInterfaceFactory javaInterfaceFactory;
    
    public EJBImplementationProcessor(ModelFactoryExtensionPoint modelFactories, Monitor monitor) {
        this.assemblyFactory = modelFactories.getFactory(AssemblyFactory.class);
        this.implementationFactory = modelFactories.getFactory(EJBImplementationFactory.class);
        this.jeeExtension = modelFactories.getFactory(JavaEEExtension.class);
        this.jeeOptionalExtension = modelFactories.getFactory(JavaEEOptionalExtension.class);
        this.monitor = monitor;

        this.javaImplementationFactory = new DefaultJavaImplementationFactory();
        this.javaInterfaceFactory = modelFactories.getFactory(JavaInterfaceFactory.class);
        javaImplementationFactory.addClassVisitor(new ReferenceProcessor(assemblyFactory, javaInterfaceFactory));
        javaImplementationFactory.addClassVisitor(new PropertyProcessor(assemblyFactory));
        javaImplementationFactory.addClassVisitor(new ServiceProcessor(assemblyFactory, javaInterfaceFactory));
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
            Problem problem = new ProblemImpl(this.getClass().getName(), "impl-ejb-validation-messages", Severity.ERROR, model, message, (Object[])messageParameters);
            monitor.problem(problem);
        }
    }

    public QName getArtifactType() {
        // Returns the QName of the XML element processed by this processor
        return IMPLEMENTATION_EJB;
    }

    public Class<EJBImplementation> getModelType() {
        // Returns the type of model processed by this processor
        return EJBImplementation.class;
    }

    public EJBImplementation read(XMLStreamReader reader) throws ContributionReadException, XMLStreamException {
        
        // Read an <implementation.ejb> element
        EJBImplementation implementation = implementationFactory.createEJBImplementation();
        implementation.setUnresolved(true);

        // Read the ejb-link attribute
        String ejbLink = getString(reader, "ejb-link");
        if (ejbLink != null) {
            implementation.setEJBLink(ejbLink);
            
            // Set the URI of the component type 
            //implementation.setURI(ejbLink.replace('#', '/'));
            int hashPosition = ejbLink.indexOf('#');
            if (hashPosition >= 0) {
                implementation.setURI(ejbLink.substring(hashPosition + 1));
            } else {
                implementation.setURI(ejbLink);
            }
        } else {
            error("EJBLinkAttributeMissing", reader);
        }

        // Skip to end element
        while (reader.hasNext()) {
            if (reader.next() == END_ELEMENT && IMPLEMENTATION_EJB.equals(reader.getName())) {
                break;
            }
        }
        
        return implementation;
    }

    public void resolve(EJBImplementation implementation, ModelResolver resolver) throws ContributionResolveException {
        
        // Resolve the component type
        String uri = implementation.getURI();
        String ejbLink = implementation.getEJBLink();
        if (ejbLink != null) {
            String module = ejbLink.indexOf('#') != -1 ? ejbLink.substring(0, ejbLink.indexOf('#')) : "";
            String beanName =  ejbLink.indexOf('#') != -1 ? ejbLink.substring(ejbLink.indexOf('#')+1) : ejbLink;
            EjbModuleInfo ejbModuleInfo = new EjbModuleInfoImpl();
            ejbModuleInfo.setUri(URI.create(module));
            ejbModuleInfo = resolver.resolveModel(EjbModuleInfo.class, ejbModuleInfo);
            
            if(jeeExtension != null) {
                ComponentType ct = jeeExtension.createImplementationEjbComponentType(ejbModuleInfo, beanName);
                // TODO - SL - TUSCANY-2944 - these new JEE processors are causing problems with existing contributions
                //        ct is null if there is no EJBInfo 
                if (ct != null){
                    implementation.getServices().addAll(ct.getServices());
                }
            }

            if(jeeOptionalExtension != null) {
                ComponentType ct = jeeOptionalExtension.createImplementationEjbComponentType(ejbModuleInfo, beanName);
                // TODO - SL - TUSCANY-2944 - these new JEE processors are causing problems with existing contributions
                //              ct is null if there is no EJBInfo  
                if (ct != null){
                    implementation.getReferences().addAll(ct.getReferences());
                    implementation.getProperties().addAll(ct.getProperties());
                }
            }

            // Introspection of bean class
            Class<?> beanClass = ejbModuleInfo.getEjbInfo(uri).beanClass;
            try {
                JavaImplementation ji = javaImplementationFactory.createJavaImplementation(beanClass);
                implementation.getReferences().addAll(ji.getReferences());
                implementation.getProperties().addAll(ji.getProperties());
                implementation.getServices().addAll(ji.getServices());
            } catch (IntrospectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            // Process componentType side file
            ComponentType componentType = assemblyFactory.createComponentType();
            componentType.setURI(uri + ".componentType");
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

    public void write(EJBImplementation implementation, XMLStreamWriter writer) throws ContributionWriteException, XMLStreamException {
        
        // Write <implementation.ejb>
        writeStart(writer, IMPLEMENTATION_EJB.getNamespaceURI(), IMPLEMENTATION_EJB.getLocalPart(),
                                 new XAttr("ejb-link", implementation.getEJBLink()));
        
        writeEnd(writer);
    }
}
