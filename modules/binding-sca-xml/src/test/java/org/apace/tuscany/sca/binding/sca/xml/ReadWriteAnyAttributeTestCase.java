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

package org.apace.tuscany.sca.binding.sca.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.tuscany.sca.assembly.Composite;
import org.apache.tuscany.sca.binding.sca.xml.SCABindingProcessor;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.DefaultStAXAttributeProcessorExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.ExtensibleStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.ExtensibleStAXAttributeProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessorExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.StAXAttributeProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXAttributeProcessorExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.xml.AnyAttributeProcessor;
import org.apache.tuscany.sca.core.DefaultExtensionPointRegistry;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test reading SCA XML assemblies.
 * 
 * @version $Rev$ $Date$
 */
public class ReadWriteAnyAttributeTestCase {

    private static final QName EXTENDED_ATTRIBUTE = new QName("http://test", "customAttribute");
    
    private static final String XML = 
        "<?xml version='1.0' encoding='UTF-8'?>" +
        "<composite xmlns=\"http://www.osoa.org/xmlns/sca/1.0\" " +
                    "xmlns:ns1=\"http://www.osoa.org/xmlns/sca/1.0\" " +
                    "targetNamespace=\"http://temp\" " +
                    "name=\"myComposite\">" +
          "<service name=\"service\">" +
            "<binding.sca xmlns:myPrefix=\"http://myPrefix\" myPrefix:myWSAnyAttribute=\"EJB Value\" />" +  
            "<interface.java interface=\"com.ibm.MyInterface\" xmlns:myPrefix=\"http://myPrefix\" myPrefix:myJavaInterfaceAnyAttribute=\"Java Interface Value\" />" +
          "</service>" +
          "<component name=\"component\">" +
            "<implementation.java class=\"com.ibm.test.MyClass\" xmlns:myPrefix=\"http://myPrefix\" myPrefix:myJavaImplAnyAttribute=\"Java Impl Value\" />" +
          "</component>" +
          "<component name=\"component1\">" +
             "<implementation.composite xmlns:ns2=\"http://temp\" name=\"ns2:myComposite\" xmlns:myPrefix=\"http://myPrefix\" myPrefix:myCompositeImplAnyAttribute=\"Composite Impl Value\" />" +
          "</component>" +
          "<reference name=\"reference\">" +
             "<interface.wsdl interface=\"http://www.example.org/SpaceWarGame/#wsdl.interface(SpaceWarGame)\" xmlns:myPrefix=\"http://myPrefix\" myPrefix:myWSDLInterfaceAnyAttribute=\"WSDL Interface Value\" />" +
          "</reference>" +
          "</composite>";
    
    private XMLInputFactory inputFactory;
    private ExtensibleStAXArtifactProcessor staxProcessor;


    /**
     * Initialize the test environment
     * This takes care to register attribute processors when provided
     *
     * @param attributeProcessor
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    	ExtensionPointRegistry extensionPoints = new DefaultExtensionPointRegistry();
    	ModelFactoryExtensionPoint modelFactories = extensionPoints.getExtensionPoint(ModelFactoryExtensionPoint.class);
    	
    	inputFactory = XMLInputFactory.newInstance();
    	
    	StAXAttributeProcessor attributeProcessor = new AnyAttributeProcessor(modelFactories,null);
    	
    	StAXAttributeProcessorExtensionPoint staxAttributeProcessorsExtensionPoint = new DefaultStAXAttributeProcessorExtensionPoint(extensionPoints);
    	staxAttributeProcessorsExtensionPoint.addArtifactProcessor(attributeProcessor);
        extensionPoints.addExtensionPoint(staxAttributeProcessorsExtensionPoint);

        
        XMLInputFactory inputFactory = modelFactories.getFactory(XMLInputFactory.class);
        XMLOutputFactory outputFactory = modelFactories.getFactory(XMLOutputFactory.class);
        StAXAttributeProcessor<Object> staxAttributeProcessor = new ExtensibleStAXAttributeProcessor(staxAttributeProcessorsExtensionPoint ,inputFactory, outputFactory, null);
        
        SCABindingProcessor wsbp = new SCABindingProcessor(modelFactories, null, staxAttributeProcessor, null);
        
        StAXArtifactProcessorExtensionPoint staxProcessors = extensionPoints.getExtensionPoint(StAXArtifactProcessorExtensionPoint.class);
        staxProcessors.addArtifactProcessor(wsbp);
            	
    	staxProcessor = new ExtensibleStAXArtifactProcessor(staxProcessors, XMLInputFactory.newInstance(), XMLOutputFactory.newInstance(), null);
    }
    
    @After
    public void tearDown() throws Exception {
        
    }


    @Test
    //@Ignore()
    public void testReadWriteCompositeWithBindings() throws Exception {
        XMLStreamReader reader = inputFactory.createXMLStreamReader(new StringReader(XML));
        Composite composite = (Composite)staxProcessor.read(reader);
        assertNotNull(composite);
        reader.close();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        staxProcessor.write(composite, bos);

        // used for debug comparison
        // System.out.println(XML);
        // System.out.println(bos.toString());

        assertEquals(XML, bos.toString());
        bos.close();
    }    
}
