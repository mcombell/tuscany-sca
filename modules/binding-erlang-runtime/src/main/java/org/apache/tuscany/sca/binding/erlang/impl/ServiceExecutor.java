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
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tuscany.sca.binding.erlang.ErlangBinding;
import org.apache.tuscany.sca.binding.erlang.impl.types.TypeHelpersProxy;
import org.apache.tuscany.sca.interfacedef.DataType;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangDecodeException;
import com.ericsson.otp.erlang.OtpErlangExit;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangRef;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMsg;

/**
 * @version $Rev$ $Date$
 */
public class ServiceExecutor implements Runnable {

	private static final Logger logger = Logger.getLogger(ServiceExecutor.class
			.getName());

	private ErlangNodeElement nodeElement;
	private OtpConnection connection;
	private Map<String, List<Operation>> groupedOperations;
	private String name;

	public ServiceExecutor(OtpConnection connection,
			Map<String, List<Operation>> groupedOperations,
			ErlangNodeElement nodeElement, String name) {
		this.connection = connection;
		this.groupedOperations = groupedOperations;
		this.nodeElement = nodeElement;
		this.name = name;
	}

	private void sendMessage(OtpConnection connection, OtpErlangPid pid,
			OtpErlangRef ref, OtpErlangAtom head, OtpErlangObject message)
			throws IOException {
		OtpErlangObject tResult = null;
		if (head != null) {
			tResult = new OtpErlangTuple(
					new OtpErlangObject[] { head, message });
		} else {
			tResult = message;
		}
		OtpErlangObject msg = null;
		msg = new OtpErlangTuple(new OtpErlangObject[] { ref, tResult });
		connection.send(pid, msg);
	}

	private void handleRpc(OtpMsg msg) {
		OtpErlangTuple request = null;
		OtpErlangPid senderPid = null;
		OtpErlangRef senderRef = null;
		try {
			OtpErlangTuple call = (OtpErlangTuple) msg.getMsg();
			OtpErlangTuple from = (OtpErlangTuple) call.elementAt(1);
			request = (OtpErlangTuple) call.elementAt(2);
			senderPid = (OtpErlangPid) from.elementAt(0);
			senderRef = (OtpErlangRef) from.elementAt(1);
			String module = ((OtpErlangAtom) request.elementAt(1)).atomValue();
			String function = ((OtpErlangAtom) request.elementAt(2))
					.atomValue();
			OtpErlangObject args = request.elementAt(3);
			OtpErlangList argsList = null;
			// normalize input
			if (args instanceof OtpErlangList) {
				argsList = (OtpErlangList) args;
			} else {
				argsList = new OtpErlangList(args);
			}
			if (!nodeElement.getBinding().getModule().equals(module)) {
				// TODO: externalize message?
				OtpErlangObject errorMsg = MessageHelper.functionUndefMessage(
						module, function, argsList,
						"Module not found in SCA component.");
				sendMessage(connection, senderPid, senderRef,
						MessageHelper.ATOM_BADRPC, errorMsg);
			} else {
				RuntimeComponentService service = nodeElement.getService();
				ErlangBinding binding = nodeElement.getBinding();
				List<Operation> operations = service.getInterfaceContract()
						.getInterface().getOperations();
				Operation operation = null;
				for (Operation o : operations) {
					if (o.getName().equals(function)) {
						operation = o;
						break;
					}
				}
				if (operation != null) {
					List<DataType> iTypes = operation.getInputType()
							.getLogical();
					Class<?>[] forClasses = new Class<?>[iTypes.size()];
					for (int i = 0; i < iTypes.size(); i++) {
						forClasses[i] = iTypes.get(i).getPhysical();
					}
					try {
						Object result = service.getRuntimeWire(binding,
								service.getInterfaceContract()).invoke(
								operation,
								TypeHelpersProxy.toJavaFromList(argsList,
										forClasses));
						OtpErlangObject response = null;
						if (operation.getOutputType() != null
								&& operation.getOutputType().getPhysical()
										.isArray()) {
							response = TypeHelpersProxy.toErlangAsList(result);
						} else if (operation.getOutputType() == null) {
							Object[] arrArg = new Object[] {};
							response = TypeHelpersProxy.toErlang(arrArg);
						} else {
							Object[] arrArg = new Object[] { result };
							response = TypeHelpersProxy.toErlang(arrArg);
						}
						sendMessage(connection, senderPid, senderRef, null,
								response);
					} catch (Exception e) {
						if ((e.getClass().equals(
								InvocationTargetException.class) && e
								.getCause().getClass().equals(
										IllegalArgumentException.class))
								|| e.getClass().equals(
										TypeMismatchException.class)) {
							// TODO: externalize message?
							OtpErlangObject errorMsg = MessageHelper
									.functionUndefMessage(module, function,
											argsList,
											"Operation name found in SCA component, but parameters types didn't match.");
							sendMessage(connection, senderPid, senderRef,
									MessageHelper.ATOM_BADRPC, errorMsg);
						} else {
							throw e;
						}
					}
				} else {
					// TODO: externalize message?
					OtpErlangObject errorMsg = MessageHelper
							.functionUndefMessage(module, function, argsList,
									"Operation name not found in SCA component.");
					sendMessage(connection, senderPid, senderRef,
							MessageHelper.ATOM_BADRPC, errorMsg);
				}
			}
		} catch (ClassCastException e) {
			// TODO: externalize message?
			try {
				logger
						.log(
								Level.WARNING,
								"On node '"
										+ nodeElement.getBinding().getNode()
										+ "' received RPC request which is invalid. Request content is: "
										+ msg.getMsg());
			} catch (OtpErlangDecodeException e1) {
			}
		} catch (Exception e) {
			try {
				sendMessage(connection, senderPid, senderRef,
						MessageHelper.ATOM_ERROR, new OtpErlangString(
								"Unhandled error while processing request: "
										+ e.getClass().getCanonicalName()
										+ ", message: " + e.getMessage()));
			} catch (Exception e1) {
				// error while sending error message. Can't do anything now
				logger.log(Level.WARNING, "Error during sending error message",
						e);
			}
		}
	}

	private void handleMsg(OtpMsg msg) {
		Operation matchedOperation = null;
		Object args[] = null;
		OtpErlangPid senderPid = null;
		OtpErlangObject msgNoSender = null;
		List<Operation> operations = groupedOperations.get(msg
				.getRecipientName());
		try {
			if (msg.getMsg().getClass().equals(OtpErlangTuple.class)
					&& (((OtpErlangTuple) msg.getMsg()).elementAt(0))
							.getClass().equals(OtpErlangPid.class)) {
				senderPid = (OtpErlangPid) ((OtpErlangTuple) msg.getMsg())
						.elementAt(0);
				msgNoSender = ((OtpErlangTuple) msg.getMsg()).elementAt(1);
			} else {
				msgNoSender = msg.getMsg();
			}
		} catch (Exception e) {

		}

		if (operations == null) {
			// TODO: externalize message?
			// NOTE: I assume in Erlang sender doesn't get confirmation so
			// no message will be send
			logger.log(Level.WARNING, "Node '" + name
					+ "' received message addressed to non exising mbox: "
					+ msg.getRecipientName());
		} else {
			for (Operation operation : operations) {
				List<DataType> iTypes = operation.getInputType().getLogical();
				Class<?>[] forClasses = new Class<?>[iTypes.size()];
				for (int i = 0; i < iTypes.size(); i++) {
					forClasses[i] = iTypes.get(i).getPhysical();
				}
				try {
					args = TypeHelpersProxy.toJavaAsArgs(msgNoSender,
							forClasses);
					matchedOperation = operation;
					break;
				} catch (Exception e) {
					// this exception is expected while processing operation
					// version with mismatched arguments
				}
			}
			if (matchedOperation != null) {
				try {
					Object result = nodeElement.getService().getRuntimeWire(
							nodeElement.getBinding()).invoke(matchedOperation,
							args);
					OtpErlangObject response = null;
					if (matchedOperation.getOutputType() != null
							&& matchedOperation.getOutputType().getPhysical()
									.isArray()) {
						response = TypeHelpersProxy.toErlangAsList(result);
					} else if (matchedOperation.getOutputType() != null) {
						Object[] arrArg = new Object[] { result };
						response = TypeHelpersProxy.toErlang(arrArg);
					}
					if (response != null && senderPid != null) {
						connection.send(senderPid, response);
					} else if (response != null && senderPid == null) {
						// FIXME: cannot send reply - sender didn't provided
						// pid. Use PID obtained by jinteface or log this error?
						// connection.send(msg.getSenderPid(), response);
					}
				} catch (InvocationTargetException e) {
					// FIXME: use linking feature? send some error?
					e.printStackTrace();
					// } catch (IOException e) {
				} catch (Exception e) {
					// FIXME: log this problem? use linking feature? send error?
					e.printStackTrace();
				}
			} else {
				// TODO: externalize message?
				// NOTE: don't send error message if mapping not found
				logger.log(Level.WARNING, "No mapping for such arguments in '"
						+ msg.getRecipientName() + "' operation in '" + name
						+ "' node. Recevied arguments: " + msgNoSender);
			}
		}
	}

	public void run() {
		try {
			// NOTE: there's also a timeout, like in reference bindings
			OtpMsg msg = null;
			if (nodeElement.getBinding().hasTimeout()) {
				msg = connection.receiveMsg(nodeElement.getBinding()
						.getTimeout());
			} else {
				msg = connection.receiveMsg();
			}
			if (msg.getRecipientName().equals(MessageHelper.RPC_MBOX)
					&& !nodeElement.getBinding().isMbox()) {
				handleRpc(msg);
			} else if (!msg.getRecipientName().equals(MessageHelper.RPC_MBOX)
					&& nodeElement.getBinding().isMbox()) {
				handleMsg(msg);
			} else {
				// received wrong message type
			}
		} catch (IOException e) {
			// TODO: externalize message?
			logger.log(Level.WARNING, "Problem while receiving message", e);
		} catch (OtpErlangExit e) {
			// TODO: linking?
		} catch (OtpAuthException e) {
			// TODO: cookies? does this exception occur sometime?
		} catch (InterruptedException e) {
			// NOTE: timeout will be logged
			// TODO: externalize message?
			logger.log(Level.WARNING, "Timeout while waiting for request", e);
		} finally {
			connection.close();
		}
	}
}
