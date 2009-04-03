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

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tuscany.sca.binding.erlang.ErlangBinding;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.provider.BindingProviderFactory;
import org.apache.tuscany.sca.provider.ReferenceBindingProvider;
import org.apache.tuscany.sca.provider.ServiceBindingProvider;
import org.apache.tuscany.sca.runtime.RuntimeComponent;
import org.apache.tuscany.sca.runtime.RuntimeComponentReference;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;

/**
 * @version $Rev$ $Date$
 */
public class ErlangBindingProviderFactory implements
		BindingProviderFactory<ErlangBinding> {

	private static final Logger logger = Logger
			.getLogger(ErlangBindingProviderFactory.class.getName());
	
	private Set<String> nodes = new HashSet<String>();

	public ErlangBindingProviderFactory(ExtensionPointRegistry registry) {

	}

	/**
	 * @see org.apache.tuscany.sca.provider.BindingProviderFactory#createReferenceBindingProvider(org.apache.tuscany.sca.runtime.RuntimeComponent,
	 *      org.apache.tuscany.sca.runtime.RuntimeComponentReference,
	 *      org.apache.tuscany.sca.assembly.Binding)
	 */
	public ReferenceBindingProvider createReferenceBindingProvider(
			RuntimeComponent component, RuntimeComponentReference reference,
			ErlangBinding binding) {
		return new ErlangReferenceBindingProvider(binding, reference);
	}

	/**
	 * @see org.apache.tuscany.sca.provider.BindingProviderFactory#createServiceBindingProvider(org.apache.tuscany.sca.runtime.RuntimeComponent,
	 *      org.apache.tuscany.sca.runtime.RuntimeComponentService,
	 *      org.apache.tuscany.sca.assembly.Binding)
	 */
	public ServiceBindingProvider createServiceBindingProvider(
			RuntimeComponent component, RuntimeComponentService service,
			ErlangBinding binding) {
		ServiceBindingProvider provider = null;
		try {
			if (nodes.contains(binding.getNode())) {
				// TODO: externalize message?
				logger.log(Level.WARNING,
						"Node name '" + binding.getNode() + "' already registered. This service will not be spawned.");
			} else {
				provider = new ErlangServiceBindingProvider(binding, service);
				nodes.add(binding.getNode());
			}
		} catch (Exception e) {
			// TODO: externalize message?
			logger.log(Level.WARNING,
					"Exception during creating ServiceBindingProvider", e);
		}
		return provider;
	}

	/**
	 * @see org.apache.tuscany.sca.provider.ProviderFactory#getModelType()
	 */
	public Class<ErlangBinding> getModelType() {
		return ErlangBinding.class;
	}
}
