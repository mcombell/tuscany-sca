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

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;

import junit.framework.TestCase;

import org.apache.tuscany.sca.assembly.Composite;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.ExtensibleStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessorExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.ValidatingXMLInputFactory;
import org.apache.tuscany.sca.core.DefaultExtensionPointRegistry;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.junit.Ignore;
import org.junit.Test;

public class AnyElementReadWriteTestCase extends TestCase {

	private XMLInputFactory inputFactory;
	private static final String XML_RECURSIVE_EXTENDED_ELEMENT = 
		"<?xml version='1.0' encoding='UTF-8'?>" +
	    "<composite xmlns=\"http://www.osoa.org/xmlns/sca/1.0\" xmlns:ns1=\"http://www.osoa.org/xmlns/sca/1.0\" targetNamespace=\"http://temp\" name=\"RecursiveExtendedElement\">" +
	    "<unknownElement>" +
	    "<subUnknownElement1 attribute=\"anyAttribute\" />" +
	    "<subUnknownElement2 />" +
	    "</unknownElement>" +
	    "</composite>";
	

	private static final String XML_UNKNOWN_IMPL = 
		"<?xml version='1.0' encoding='UTF-8'?>" + 
        "<composite xmlns=\"http://www.osoa.org/xmlns/sca/1.0\" xmlns:ns1=\"http://www.osoa.org/xmlns/sca/1.0\" targetNamespace=\"http://temp\" name=\"aaaa\" autowire=\"false\">" +
        "<component name=\"unknownImpl\">" +
        "<implementation.unknown class=\"raymond\" />"  +
        "<service name= \"service\" requires=\"\">" +
        "<binding.ws unknownAttribute=\"unknown\" />" +
        "</service>" +
        "</component>" +
        "</composite>";
	
	private ExtensibleStAXArtifactProcessor staxProcessor;

	@Override
	public void setUp() throws Exception {
		ExtensionPointRegistry extensionPoints = new DefaultExtensionPointRegistry();
		 ModelFactoryExtensionPoint modelFactories = extensionPoints.getExtensionPoint(ModelFactoryExtensionPoint.class);
		 inputFactory = modelFactories.getFactory(ValidatingXMLInputFactory.class);
		
		StAXArtifactProcessorExtensionPoint staxProcessors = extensionPoints
				.getExtensionPoint(StAXArtifactProcessorExtensionPoint.class);
		staxProcessor = new ExtensibleStAXArtifactProcessor(staxProcessors,
				inputFactory, XMLOutputFactory.newInstance(), null);
	}

	@Override
	public void tearDown() throws Exception {
	}
	
	//@Test
	@Ignore
	public void testReadWriteExtendedRecursiveElement() throws Exception {
		XMLStreamReader reader = inputFactory.createXMLStreamReader(new StringReader(XML_RECURSIVE_EXTENDED_ELEMENT));
		Composite composite = (Composite) staxProcessor.read(reader);
		assertNotNull(composite);
		reader.close();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		staxProcessor.write(composite, bos);
		
		// used for debug comparison
		//System.out.println(XML_RECURSIVE_EXTENDED_ELEMENT);
		//System.out.println(bos.toString());

		assertEquals(XML_RECURSIVE_EXTENDED_ELEMENT, bos.toString());
		bos.close();	
	}	
	
	@Test
	public void testReadWriteUnknwonImpl() throws Exception {
		XMLStreamReader reader = inputFactory.createXMLStreamReader(new StringReader(XML_UNKNOWN_IMPL));
		Composite composite = (Composite) staxProcessor.read(reader);
		assertNotNull(composite);
		reader.close();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		staxProcessor.write(composite, bos);

		// used for debug comparison
		System.out.println(XML_UNKNOWN_IMPL);
		System.out.println(bos.toString());

		assertEquals(XML_UNKNOWN_IMPL, bos.toString());
		bos.close();
	}		

}
