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

package org.apache.tuscany.sca.binding.erlang.impl.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangTuple;

/**
 * @version $Rev$ $Date$
 */
public class TupleTypeHelper implements TypeHelper {

	public OtpErlangObject toErlang(Object object) {
		Class<?> forClass = object.getClass();
		List<OtpErlangObject> tupleMembers = new ArrayList<OtpErlangObject>();
		Field[] fields = forClass.getFields();
		for (int i = 0; i < fields.length; i++) {
			Object[] args = null;
			try {
				args = new Object[] { fields[i].get(object) };
			} catch (IllegalArgumentException e) {
				// no problem should occur here
			} catch (IllegalAccessException e) {
				// and here
			}
			OtpErlangObject member = TypeHelpersProxy.toErlang(args);
			tupleMembers.add(member);
		}
		OtpErlangObject result = new OtpErlangTuple(tupleMembers
				.toArray(new OtpErlangObject[tupleMembers.size()]));
		return result;
	}

	public Object toJava(OtpErlangObject object, Class<?> forClass)
			throws Exception {
		Object result = null;
		OtpErlangTuple tuple = (OtpErlangTuple) object;
		Field[] fields = forClass.getFields();
		result = forClass.newInstance();
		for (int i = 0; i < tuple.arity(); i++) {
			OtpErlangObject tupleMember = tuple.elementAt(i);
			Object javaMember = TypeHelpersProxy.toJava(tupleMember, fields[i]
					.getType());
			fields[i].setAccessible(true);
			fields[i].set(result, javaMember);
		}
		return result;
	}

}
