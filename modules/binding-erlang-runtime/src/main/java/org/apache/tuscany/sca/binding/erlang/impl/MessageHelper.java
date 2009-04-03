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

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangRef;
import com.ericsson.otp.erlang.OtpErlangTuple;

/**
 * @version $Rev$ $Date$
 */
public class MessageHelper {

	/**
	 * Mbox name used for Remote Procedure Calls
	 */
	public static String RPC_MBOX = "rex";

	public static final OtpErlangAtom ATOM_OK = new OtpErlangAtom("ok");
	public static final OtpErlangAtom ATOM_ERROR = new OtpErlangAtom("error");
	public static final OtpErlangAtom ATOM_BADRPC = new OtpErlangAtom("badrpc");
	private static final OtpErlangAtom ATOM_EXIT = new OtpErlangAtom("EXIT");
	private static final OtpErlangAtom ATOM_UNDEF = new OtpErlangAtom("undef");
	private static final OtpErlangAtom ATOM_CALL = new OtpErlangAtom("call");
	private static final OtpErlangAtom ATOM_GEN_CALL = new OtpErlangAtom(
			"$gen_call");

	public static OtpErlangObject functionUndefMessage(String module,
			String function, OtpErlangList args, String tuscanyMsg) {
		OtpErlangObject[] args4 = new OtpErlangObject[3];
		args4[0] = new OtpErlangAtom(module);
		args4[1] = new OtpErlangAtom(function);
		args4[2] = args;

		OtpErlangObject[] args3 = new OtpErlangObject[2];
		args3[0] = new OtpErlangTuple(args4);
		args3[1] = new OtpErlangAtom(tuscanyMsg);

		OtpErlangObject[] args2 = new OtpErlangObject[2];
		args2[0] = ATOM_UNDEF;
		args2[1] = new OtpErlangList(args3);

		OtpErlangObject[] args1 = new OtpErlangObject[2];
		args1[0] = ATOM_EXIT;
		args1[1] = new OtpErlangTuple(args2);

		OtpErlangTuple result = new OtpErlangTuple(args1);
		return result;
	}

	public static boolean isfunctionUndefMessage(OtpErlangObject msg) {
		if (msg.getClass().equals(OtpErlangTuple.class)) {
			OtpErlangTuple tupleMsg = (OtpErlangTuple) msg;
			if (tupleMsg.arity() == 2
					&& tupleMsg.elementAt(0).getClass().equals(
							OtpErlangAtom.class)
					&& tupleMsg.elementAt(1).getClass().equals(
							OtpErlangTuple.class)
					&& ((OtpErlangAtom) tupleMsg.elementAt(0)).atomValue()
							.equals(ATOM_BADRPC.atomValue())) {
				OtpErlangTuple badrpcTuple = (OtpErlangTuple) tupleMsg
						.elementAt(1);
				if (badrpcTuple.arity() == 2
						&& badrpcTuple.elementAt(0).getClass().equals(
								OtpErlangAtom.class)
						&& badrpcTuple.elementAt(1).getClass().equals(
								OtpErlangTuple.class)
						&& ((OtpErlangAtom) badrpcTuple.elementAt(0))
								.atomValue().equals(ATOM_EXIT.atomValue())) {
					OtpErlangTuple exitTuple = (OtpErlangTuple) badrpcTuple
							.elementAt(1);
					if (exitTuple.arity() == 2
							&& exitTuple.elementAt(0).getClass().equals(
									OtpErlangAtom.class)
							&& ((OtpErlangAtom) exitTuple.elementAt(0))
									.atomValue().equals(ATOM_UNDEF.atomValue())) {
						return true;
					}
				}

			}
		}
		return false;
	}

	public static OtpErlangTuple rpcMessage(OtpErlangPid senderPid,
			OtpErlangRef ref, String module, String function, OtpErlangList args) {
		OtpErlangObject[] args3 = new OtpErlangObject[5];
		args3[0] = ATOM_CALL;
		args3[1] = new OtpErlangAtom(module);
		args3[2] = new OtpErlangAtom(function);
		args3[3] = args;
		args3[4] = senderPid;

		OtpErlangObject[] args2 = new OtpErlangObject[2];
		args2[0] = senderPid;
		args2[1] = ref;

		OtpErlangObject[] args1 = new OtpErlangObject[3];
		args1[0] = ATOM_GEN_CALL;
		args1[1] = new OtpErlangTuple(args2);
		args1[2] = new OtpErlangTuple(args3);

		OtpErlangTuple result = new OtpErlangTuple(args1);
		return result;
	}
}
