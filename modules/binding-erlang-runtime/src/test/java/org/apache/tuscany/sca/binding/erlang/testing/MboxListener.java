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

import org.apache.tuscany.sca.binding.erlang.impl.types.TypeHelpersProxy;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpMsg;

/**
 * @version $Rev$ $Date$
 */
public class MboxListener implements Runnable {

	private OtpMbox mbox;
	private OtpMsg msg;
	private Object response;
	private long duration;

	public MboxListener(OtpMbox mbox, Object response) {
		this(mbox, response, 0);
	}

	public MboxListener(OtpMbox mbox, Object response, long duration) {
		this.mbox = mbox;
		this.response = response;
		this.duration = duration;
	}

	public void run() {
		try {
			msg = mbox.receiveMsg();
			Thread.sleep(duration);
			if (response != null) {
				Object[] args = new Object[1];
				args[0] = response;
				mbox.send(msg.getSenderPid(), TypeHelpersProxy.toErlang(args));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OtpErlangObject getMsg() {
		try {
			// Sometimes clients tries to get message which isn't fully
			// received.
			// If so - give it more tries. This sometimes caused
			// NullPointerException in
			// ReferenceServiceTestCase.testMultipleArguments().
			for (int i = 0; i < 3; i++) {
				if (msg != null) {
					return ((OtpErlangTuple) msg.getMsg()).elementAt(1);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
			return msg.getMsg();
		} catch (Exception e) {

		}
		return null;
	}
}
