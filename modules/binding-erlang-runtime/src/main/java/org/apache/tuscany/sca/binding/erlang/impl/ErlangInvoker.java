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
import org.apache.tuscany.sca.binding.erlang.impl.exceptions.ErlangException;
import org.apache.tuscany.sca.binding.erlang.impl.types.TypeHelpersProxy;
import org.apache.tuscany.sca.invocation.Invoker;
import org.apache.tuscany.sca.invocation.Message;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpEpmd;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpMsg;
import com.ericsson.otp.erlang.OtpNode;
import com.ericsson.otp.erlang.OtpPeer;
import com.ericsson.otp.erlang.OtpSelf;

/**
 * @version $Rev$ $Date$
 */
public class ErlangInvoker implements Invoker {

	private static final Logger logger = Logger.getLogger(ErlangInvoker.class
			.getName());

	private ErlangBinding binding;

	public ErlangInvoker(ErlangBinding binding) {
		this.binding = binding;
	}

	private void reportProblem(Message msg, Exception e) {
		if (msg.getOperation().getFaultTypes().size() > 0) {
			msg.setFaultBody(e);
		} else {
			// NOTE: don't throw exception if not declared
			// TODO: externalize message?
			logger
					.log(Level.WARNING, "Problem while sending/receiving data",
							e);
		}
	}

	private String getClientNodeName() {
		return "_connector_to_" + binding.getNode()
				+ System.currentTimeMillis();
	}

	private Message sendMessage(Message msg) {
		OtpMbox tmpMbox = null;
		OtpNode node = null;
		try {
			node = new OtpNode(getClientNodeName());
			if (binding.hasCookie()) {
				node.setCookie(binding.getCookie());
			}
			tmpMbox = node.createMbox();
			Object[] args = msg.getBody();
			OtpErlangObject msgPayload = TypeHelpersProxy.toErlang(args);
			tmpMbox.send(msg.getOperation().getName(), binding.getNode(),
					msgPayload);
			if (msg.getOperation().getOutputType() != null) {
				OtpMsg resultMsg = null;
				if (binding.hasTimeout()) {
					resultMsg = tmpMbox.receiveMsg(binding.getTimeout());
				} else {
					resultMsg = tmpMbox.receiveMsg();
				}
				OtpErlangObject result = resultMsg.getMsg();
				msg.setBody(TypeHelpersProxy.toJava(result, msg.getOperation()
						.getOutputType().getPhysical()));
			}
		} catch (InterruptedException e) {
			// TODO: externalize message?
			ErlangException ee = new ErlangException(
					"Timeout while receiving message reply", e);
			msg.setBody(null);
			reportProblem(msg, ee);
		} catch (Exception e) {
			reportProblem(msg, e);
		} finally {
			if (tmpMbox != null) {
				tmpMbox.close();
			}
			if (node != null) {
				OtpEpmd.unPublishPort(node);
				node.close();
			}
		}
		return msg;
	}

	private Message invokeOperation(Message msg) {
		OtpSelf self = null;
		OtpPeer other = null;
		OtpConnection connection = null;
		try {
			self = new OtpSelf(getClientNodeName());
			if (binding.hasCookie()) {
				self.setCookie(binding.getCookie());
			}
			other = new OtpPeer(binding.getNode());
			connection = self.connect(other);
			OtpErlangList params = TypeHelpersProxy
					.toErlangAsList((Object[]) msg.getBody());
			OtpErlangTuple message = MessageHelper.rpcMessage(self.pid(), self
					.createRef(), binding.getModule(), msg.getOperation()
					.getName(), params);
			connection.send(MessageHelper.RPC_MBOX, message);
			OtpErlangObject rpcResponse = null;
			if (binding.hasTimeout()) {
				rpcResponse = connection.receive(binding.getTimeout());
			} else {
				rpcResponse = connection.receive();
			}
			OtpErlangObject result = ((OtpErlangTuple) rpcResponse)
					.elementAt(1);
			if (MessageHelper.isfunctionUndefMessage(result)) {
				// TODO: externalize message?
				Exception e = new ErlangException("No '" + binding.getModule()
						+ ":" + msg.getOperation().getName()
						+ "' operation defined on remote '" + binding.getNode()
						+ "' node.");
				reportProblem(msg, e);
				msg.setBody(null);
			} else if (msg.getOperation().getOutputType() != null) {
				if (result.getClass().equals(OtpErlangTuple.class)) {
					OtpErlangObject resultBody = ((OtpErlangTuple) result)
							.elementAt(1);
					msg.setBody(TypeHelpersProxy.toJava(resultBody, msg
							.getOperation().getOutputType().getPhysical()));
				}
			}
		} catch (OtpAuthException e) {
			// TODO: externalize message?
			ErlangException ee = new ErlangException(
					"Problem while authenticating client - check your cookie",
					e);
			msg.setBody(null);
			reportProblem(msg, ee);
		} catch (InterruptedException e) {
			// TODO: externalize message?
			ErlangException ee = new ErlangException(
					"Timeout while receiving RPC reply", e);
			msg.setBody(null);
			reportProblem(msg, ee);
		} catch (Exception e) {
			reportProblem(msg, e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return msg;
	}

	/**
	 * @see org.apache.tuscany.sca.invocation.Invoker#invoke(org.apache.tuscany.sca.invocation.Message)
	 */
	public Message invoke(Message msg) {
		if (binding.isMbox()) {
			return sendMessage(msg);
		} else {
			return invokeOperation(msg);
		}

	}

}
