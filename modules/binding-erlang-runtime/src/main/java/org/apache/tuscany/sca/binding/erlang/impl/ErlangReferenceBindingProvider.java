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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tuscany.sca.binding.erlang.ErlangBinding;
import org.apache.tuscany.sca.interfacedef.InterfaceContract;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.invocation.Invoker;
import org.apache.tuscany.sca.provider.ReferenceBindingProvider;
import org.apache.tuscany.sca.runtime.RuntimeComponentReference;

/**
 * @version $Rev$ $Date$
 */
public class ErlangReferenceBindingProvider implements ReferenceBindingProvider {

	private static final Logger logger = Logger
			.getLogger(ErlangReferenceBindingProvider.class.getName());
	private RuntimeComponentReference reference;
	private ErlangBinding binding;

	public ErlangReferenceBindingProvider(ErlangBinding binding,
			RuntimeComponentReference reference) {
		this.reference = reference;
		this.binding = binding;
	}

	/**
	 * @see org.apache.tuscany.sca.provider.ReferenceBindingProvider#createInvoker(org.apache.tuscany.sca.interfacedef.Operation)
	 */
	public Invoker createInvoker(Operation operation) {
		try {
			return new ErlangInvoker(binding);
		} catch (Exception e) {
			logger.log(Level.WARNING,
					"Exception during creating Erlang invoker", e);
		}
		return null;
	}

	/**
	 * @see org.apache.tuscany.sca.provider.ReferenceBindingProvider#getBindingInterfaceContract()
	 */
	public InterfaceContract getBindingInterfaceContract() {
		return reference.getInterfaceContract();
	}

	/**
	 * @see org.apache.tuscany.sca.provider.ReferenceBindingProvider#start()
	 */
	public void start() {
	}

	/**
	 * @see org.apache.tuscany.sca.provider.ReferenceBindingProvider#stop()
	 */
	public void stop() {
	}

	/**
	 * @see org.apache.tuscany.sca.provider.ReferenceBindingProvider#supportsOneWayInvocation()
	 */
	public boolean supportsOneWayInvocation() {
		return false;
	}

}
