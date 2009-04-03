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
		binding.setNode(reader.getAttributeValue(null, "node"));
		String mboxValue = reader.getAttributeValue(null, "mbox");
		if (mboxValue != null && mboxValue.length() > 0) {
			try {
				boolean boolMboxValue = Boolean.parseBoolean(mboxValue);
				binding.setMbox(boolMboxValue);
			} catch (Exception e) {
			}
		}
		String timeoutValue = reader.getAttributeValue(null, "timeout");
		try {
			long longTimeoutValue = Long.parseLong(timeoutValue);
			binding.setTimeout(longTimeoutValue);
		} catch (NumberFormatException e) {
		}
		binding.setModule(reader.getAttributeValue(null, "module"));
		binding.setCookie(reader.getAttributeValue(null, "cookie"));
		return binding;
	}

	/**
	 * @see org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor#write(java.lang.Object,
	 *      javax.xml.stream.XMLStreamWriter)
	 */
	public void write(ErlangBinding model, XMLStreamWriter writer)
			throws ContributionWriteException, XMLStreamException {
		writer.writeStartElement(Constants.SCA10_TUSCANY_NS, "binding.erlang");
		// TODO: implement writing binding element
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
