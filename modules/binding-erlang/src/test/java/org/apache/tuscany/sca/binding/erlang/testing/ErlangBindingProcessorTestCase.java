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

package org.apache.tuscany.sca.binding.erlang.testing;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.tuscany.sca.assembly.Composite;
import org.apache.tuscany.sca.binding.erlang.ErlangBinding;
import org.apache.tuscany.sca.binding.erlang.impl.ErlangBindingImpl;
import org.apache.tuscany.sca.contribution.processor.DefaultStAXArtifactProcessorExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.ExtensibleStAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessorExtensionPoint;
import org.apache.tuscany.sca.core.DefaultExtensionPointRegistry;
import org.apache.tuscany.sca.core.UtilityExtensionPoint;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.monitor.MonitorFactory;
import org.apache.tuscany.sca.monitor.impl.DefaultMonitorFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * General tests for binding.erlang XML processor
 */
public class ErlangBindingProcessorTestCase {

	private static final String COMPOSITE = "<?xml version=\"1.0\" encoding=\"ASCII\"?>"
			+ "<composite xmlns=\"http://www.osoa.org/xmlns/sca/1.0\" xmlns:tuscany=\"http://tuscany.apache.org/xmlns/sca/1.0\" targetNamespace=\"http://binding-erlang\" name=\"binding-erlang\">"
			+ " <component name=\"HelloWorldComponent\">"
			+ "   <implementation.java class=\"services.HelloWorld\"/>"
			+ "      <service name=\"HelloWorldService\">"
			+ "          <tuscany:binding.erlang node=\"SomeNode\" timeout=\"1000\" cookie=\"cookie\" serviceThreadPool=\"1\"/>"
			+ "      </service>" + " </component>" + "</composite>";

	private static final String COMPOSITE_DEFAULT_TIMEOUT = "<?xml version=\"1.0\" encoding=\"ASCII\"?>"
			+ "<composite xmlns=\"http://www.osoa.org/xmlns/sca/1.0\" xmlns:tuscany=\"http://tuscany.apache.org/xmlns/sca/1.0\" targetNamespace=\"http://binding-erlang\" name=\"binding-erlang\">"
			+ " <component name=\"HelloWorldComponent\">"
			+ "   <implementation.java class=\"services.HelloWorld\"/>"
			+ "      <service name=\"HelloWorldService\">"
			+ "          <tuscany:binding.erlang node=\"SomeNode\"/>"
			+ "      </service>" + " </component>" + "</composite>";

	private static XMLInputFactory inputFactory;
	private static StAXArtifactProcessor<Object> staxProcessor;
	private static Monitor monitor;

	@BeforeClass
	public static void init() throws Exception {
		DefaultExtensionPointRegistry extensionPoints = new DefaultExtensionPointRegistry();
		inputFactory = XMLInputFactory.newInstance();
		// Create a monitor
		UtilityExtensionPoint utilities = extensionPoints
				.getExtensionPoint(UtilityExtensionPoint.class);
		MonitorFactory monitorFactory = new DefaultMonitorFactoryImpl();
		if (monitorFactory != null) {
			monitor = monitorFactory.createMonitor();
			utilities.addUtility(monitorFactory);
		}
		StAXArtifactProcessorExtensionPoint staxProcessors = new DefaultStAXArtifactProcessorExtensionPoint(
				extensionPoints);
		staxProcessor = new ExtensibleStAXArtifactProcessor(staxProcessors,
				inputFactory, null, monitor);
	}

	/**
	 * Tests reading "binding.erlang" element
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadValidComposite() throws Exception {
		XMLStreamReader reader = inputFactory
				.createXMLStreamReader(new StringReader(COMPOSITE));
		Composite composite = (Composite) staxProcessor.read(reader);
		ErlangBinding binding = (ErlangBinding) composite.getComponents()
				.get(0).getServices().get(0).getBindings().get(0);
		assertEquals("SomeNode", binding.getNode());
		assertEquals(1000, binding.getTimeout());
		assertEquals("cookie", binding.getCookie());
		assertEquals(true, binding.hasTimeout());
		assertEquals(true, binding.hasCookie());
		assertEquals(1, binding.getServiceThreadPool());
		assertEquals(false, binding.isDefaultServiceThreadPool());
		assertEquals(false, binding.isDefaultTimeout());
	}

	/**
	 * Tests using default "resultTimeout", default thread pool, no cookie
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadDefaultTimeout() throws Exception {
		XMLStreamReader reader = inputFactory
				.createXMLStreamReader(new StringReader(
						COMPOSITE_DEFAULT_TIMEOUT));
		Composite composite = (Composite) staxProcessor.read(reader);
		ErlangBinding binding = (ErlangBinding) composite.getComponents()
				.get(0).getServices().get(0).getBindings().get(0);
		assertEquals(ErlangBindingImpl.NO_TIMEOUT, binding.getTimeout());
		assertEquals(null, binding.getCookie());
		assertEquals(false, binding.hasTimeout());
		assertEquals(false, binding.hasCookie());
		assertEquals(ErlangBinding.DEFAULT_THREAD_POOL, binding
				.getServiceThreadPool());
		assertEquals(true, binding.isDefaultServiceThreadPool());
		assertEquals(true, binding.isDefaultTimeout());
	}

	@Test
	@Ignore("unbound namespace problem")
	//FIXME: fix it
	public void testWrite() throws Exception {
        XMLStreamReader reader = inputFactory.createXMLStreamReader(new StringReader(COMPOSITE));       
        Composite composite = (Composite)staxProcessor.read(reader);
        ErlangBinding binding1 = (ErlangBinding)composite.getComponents().get(0).getServices().get(0).getBindings().get(0);       
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        
        staxProcessor.write(composite, outputFactory.createXMLStreamWriter(bos));
        
        XMLStreamReader reader2 = inputFactory.createXMLStreamReader(new StringReader(bos.toString()));       
        Composite composite2 = (Composite)staxProcessor.read(reader2);
        ErlangBinding binding2 = (ErlangBinding)composite2.getComponents().get(0).getServices().get(0).getBindings().get(0);       
        
        //compare binding1 to binding2
	}

}
