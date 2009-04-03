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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tuscany.sca.binding.erlang.ErlangBinding;
import org.apache.tuscany.sca.binding.erlang.impl.exceptions.ErlangException;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpSelf;

/**
 * @version $Rev$ $Date$
 */
public class ErlangNode implements Runnable {

	private static final Logger logger = Logger.getLogger(ErlangNode.class
			.getName());

	private ErlangNodeElement nodeElement;
	private String name;
	private OtpSelf self;
	private ExecutorService executors;
	private boolean stopRequested;
	private Map<String, List<Operation>> groupedOperations;

	public ErlangNode(String name, ErlangBinding binding,
			RuntimeComponentService service) throws Exception {
		this.name = name;
		self = new OtpSelf(name);
		boolean registered = self.publishPort();
		if (!registered) {
			// TODO: externalize message?
			throw new ErlangException(
					"Problem with publishing service under epmd server.");
		}
		if (binding.hasCookie()) {
			self.setCookie(binding.getCookie());
		}
		registerBinding(binding, service);
	}

	public void stop() {
		stopRequested = true;
		executors.shutdownNow();
	}

	public void run() {
		executors = Executors.newFixedThreadPool(nodeElement.getBinding().getServiceThreadPool());
		while (!stopRequested) {
			try {
				OtpConnection connection = self.accept();
				executors.execute(new ServiceExecutor(connection,
						groupedOperations, nodeElement, name));
			} catch (IOException e) {
				// TODO: externalzie message?
				logger.log(Level.WARNING,
						"Error occured while accepting connection on '" + name
								+ "' node", e);
			} catch (OtpAuthException e) {
				// TODO: externalize message?
				logger.log(Level.WARNING, "Error while authenticating client", e);
			}
		}
		executors.shutdownNow();
	}

	private void registerBinding(ErlangBinding binding,
			RuntimeComponentService service) throws ErlangException {
		if (binding.isMbox()) {
			List<Operation> operations = service.getInterfaceContract()
					.getInterface().getOperations();
			groupedOperations = new HashMap<String, List<Operation>>();
			for (Operation operation : operations) {
				List<Operation> operationsGroup = groupedOperations
						.get(operation.getName());
				if (operationsGroup == null) {
					operationsGroup = new ArrayList<Operation>();
					groupedOperations.put(operation.getName(), operationsGroup);
				}
				operationsGroup.add(operation);
			}
		}
		nodeElement = new ErlangNodeElement();
		nodeElement.setService(service);
		nodeElement.setBinding(binding);
	}

}
