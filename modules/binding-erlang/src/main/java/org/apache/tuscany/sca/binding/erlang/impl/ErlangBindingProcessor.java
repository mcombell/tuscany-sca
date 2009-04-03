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

package org.apache.tuscany.sca.binding.erlang.impl;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.assembly.xml.Constants;
import org.apache.tuscany.sca.assembly.xml.PolicyAttachPointProcessor;
import org.apache.tuscany.sca.binding.erlang.ErlangBinding;
import org.apache.tuscany.sca.contribution.ModelFactoryExtensionPoint;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.contribution.service.ContributionWriteException;
import org.apache.tuscany.sca.policy.PolicyFactory;

/**
 * @version $Rev: $ $Date: $
 */
public class ErlangBindingProcessor implements
		StAXArtifactProcessor<ErlangBinding> {

	private PolicyFactory policyFactory;
	private PolicyAttachPointProcessor policyProcessor;

	private static final String ATTR_COOKIE = "cookie";
	private static final String ATTR_MBOX = "mbox";
	private static final String ATTR_MODULE = "module";
	private static final String ATTR_NODE = "node";
	private static final String ATTR_SERVICE_THREAD_POOL = "serviceThreadPool";
	private static final String ATTR_TIMEOUT = "timeout";

	public ErlangBindingProcessor(ModelFactoryExtensionPoint modelFactories) {
		this.policyFactory = modelFactories.getFactory(PolicyFactory.class);
		this.policyProcessor = new PolicyAttachPointProcessor(policyFactory);
	}

	/**
	 * @see org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor#getArtifactType()
	 */
	public QName getArtifactType() {
		return ErlangBinding.BINDING_ERLANG_QNAME;
	}

	/**
	 * @see org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor#read(javax.xml.stream.XMLStreamReader)
	 */
	public ErlangBinding read(XMLStreamReader reader)
			throws ContributionReadException, XMLStreamException {
		ErlangBinding binding = new ErlangBindingImpl();

		// Read the policies
		policyProcessor.readPolicies(binding, reader);

		binding.setNode(reader.getAttributeValue(null, ATTR_NODE));

		String mboxValue = reader.getAttributeValue(null, ATTR_MBOX);

		if (mboxValue != null && mboxValue.length() > 0) {
			try {
				binding.setMbox(Boolean.parseBoolean(mboxValue));
			} catch (Exception e) {
			}
		}

		try {
			binding.setTimeout(Long.parseLong(reader.getAttributeValue(null,
					ATTR_TIMEOUT)));
		} catch (NumberFormatException e) {
		}

		binding.setModule(reader.getAttributeValue(null, ATTR_MODULE));

		binding.setCookie(reader.getAttributeValue(null, ATTR_COOKIE));

		try {
			binding.setServiceThreadPool(Integer.parseInt(reader
					.getAttributeValue(null, ATTR_SERVICE_THREAD_POOL)));
		} catch (NumberFormatException e) {
		}

		return binding;
	}

	/**
	 * @see org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor#write(java.lang.Object,
	 *      javax.xml.stream.XMLStreamWriter)
	 */
	public void write(ErlangBinding model, XMLStreamWriter writer)
			throws ContributionWriteException, XMLStreamException {
		
		
		writer.writeStartElement(Constants.SCA10_TUSCANY_NS, "binding.erlang");
		
		if (model.getCookie() != null && model.getCookie().length() > 0) {
			writer.writeAttribute(ATTR_COOKIE, model.getCookie());
		}

		if (model.isMbox()) {
			writer.writeAttribute(ATTR_MBOX, Boolean.TRUE.toString());
		}

		if (model.getModule() != null && model.getModule().length() > 0) {
			writer.writeAttribute(ATTR_MODULE, model.getModule());
		}

		if (model.getNode() != null && model.getNode().length() > 0) {
			writer.writeAttribute(ATTR_NODE, model.getNode());
		}

		if (!model.isDefaultServiceThreadPool()) {
			writer.writeAttribute(ATTR_SERVICE_THREAD_POOL, Integer
					.toString(model.getServiceThreadPool()));
		}

		if (!model.isDefaultTimeout()) {
			writer.writeAttribute(ATTR_TIMEOUT, Long.toString(model
					.getTimeout()));
		}

		writer.writeEndElement();
	}

	/**
	 * @see org.apache.tuscany.sca.contribution.processor.ArtifactProcessor#getModelType()
	 */
	public Class<ErlangBinding> getModelType() {
		return ErlangBinding.class;
	}

	/**
	 * @see org.apache.tuscany.sca.contribution.processor.ArtifactProcessor#resolve(java.lang.Object,
	 *      org.apache.tuscany.sca.contribution.resolver.ModelResolver)
	 */
	public void resolve(ErlangBinding model, ModelResolver resolver)
			throws ContributionResolveException {
	}

}
