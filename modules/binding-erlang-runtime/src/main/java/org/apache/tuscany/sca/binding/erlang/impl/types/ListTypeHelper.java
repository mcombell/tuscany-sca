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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;

/**
 * @version $Rev$ $Date$
 */
public class ListTypeHelper implements TypeHelper {

	public OtpErlangObject toErlang(Object object) {
		int i = 0;
		List<OtpErlangObject> elements = new ArrayList<OtpErlangObject>();
		while (true) {
			try {
				Object arrElement = Array.get(object, i);
				Object[] args = new Object[] { arrElement };
				elements.add(TypeHelpersProxy.toErlang(args));
				i++;
			} catch (ArrayIndexOutOfBoundsException e) {
				// expected
				break;
			}
		}
		return new OtpErlangList(elements.toArray(new OtpErlangObject[elements
				.size()]));
	}

	public Object toJava(OtpErlangObject object, Class<?> forClass)
			throws Exception {
		OtpErlangList erlangList = (OtpErlangList) object;
		Object result = Array.newInstance(forClass.getComponentType(),
				erlangList.arity());
		for (int i = 0; i < erlangList.arity(); i++) {
			Array.set(result, i, TypeHelpersProxy.toJava(erlangList
					.elementAt(i), forClass.getComponentType()));
		}
		return result;
	}

}
