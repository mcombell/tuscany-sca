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

package org.apache.tuscany.sca.assembly.xml;

import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;

import junit.framework.TestCase;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.assembly.DefaultAssemblyFactory;
import org.apache.tuscany.sca.contribution.DefaultModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.impl.ContributionFactoryImpl;
import org.apache.tuscany.sca.contribution.processor.DefaultStAXArtifactProcessorExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.ExtensibleStAXArtifactProcessor;
import org.apache.tuscany.sca.interfacedef.InterfaceContractMapper;
import org.apache.tuscany.sca.interfacedef.impl.InterfaceContractMapperImpl;
import org.apache.tuscany.sca.policy.DefaultPolicyFactory;
import org.apache.tuscany.sca.policy.PolicyFactory;

/**
 * Test reading SCA XML assemblies.
 * 
 * @version $Rev$ $Date$
 */
public class ReadTestCase extends TestCase {

    private XMLInputFactory inputFactory;
    private DefaultStAXArtifactProcessorExtensionPoint staxProcessors;
    private ExtensibleStAXArtifactProcessor staxProcessor;
    private AssemblyFactory factory;
    private PolicyFactory policyFactory;
    private InterfaceContractMapper mapper;

    @Override
    public void setUp() throws Exception {
        factory = new DefaultAssemblyFactory();
        policyFactory = new DefaultPolicyFactory();
        mapper = new InterfaceContractMapperImpl();
        inputFactory = XMLInputFactory.newInstance();
        staxProcessors = new DefaultStAXArtifactProcessorExtensionPoint(new DefaultModelFactoryExtensionPoint());
        staxProcessor = new ExtensibleStAXArtifactProcessor(staxProcessors, XMLInputFactory.newInstance(), XMLOutputFactory.newInstance());
    }

    @Override
    public void tearDown() throws Exception {
    }

    public void testReadComponentType() throws Exception {
        ComponentTypeProcessor componentTypeReader = new ComponentTypeProcessor(factory, policyFactory, staxProcessor);
        InputStream is = getClass().getResourceAsStream("CalculatorImpl.componentType");
        XMLStreamReader reader = inputFactory.createXMLStreamReader(is);
        assertNotNull(componentTypeReader.read(reader));
        is.close();
    }

    public void testReadConstrainingType() throws Exception {
        InputStream is = getClass().getResourceAsStream("CalculatorComponent.constrainingType");
        ConstrainingTypeProcessor constrainingTypeReader = new ConstrainingTypeProcessor(factory, policyFactory, staxProcessor);
        XMLStreamReader reader = inputFactory.createXMLStreamReader(is);
        assertNotNull(constrainingTypeReader.read(reader));
        is.close();

    }

    public void testReadComposite() throws Exception {
        InputStream is = getClass().getResourceAsStream("Calculator.composite");
        CompositeProcessor compositeReader = new CompositeProcessor(new ContributionFactoryImpl(), factory, policyFactory, mapper, staxProcessor);
        XMLStreamReader reader = inputFactory.createXMLStreamReader(is);
        assertNotNull(compositeReader.read(reader));
        is.close();

    }

    public void testReadCompositeAndWireIt() throws Exception {
        InputStream is = getClass().getResourceAsStream("Calculator.composite");
        CompositeProcessor compositeReader = new CompositeProcessor(new ContributionFactoryImpl(), factory, policyFactory, mapper, staxProcessor);
        XMLStreamReader reader = inputFactory.createXMLStreamReader(is);
        assertNotNull(compositeReader.read(reader));
        is.close();
    }

}
