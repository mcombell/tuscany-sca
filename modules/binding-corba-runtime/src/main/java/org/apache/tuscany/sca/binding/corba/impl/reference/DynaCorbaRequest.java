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

package org.apache.tuscany.sca.binding.corba.impl.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tuscany.sca.binding.corba.impl.exceptions.CorbaException;
import org.apache.tuscany.sca.binding.corba.impl.exceptions.RequestConfigurationException;
import org.apache.tuscany.sca.binding.corba.impl.types.TypeTree;
import org.apache.tuscany.sca.binding.corba.impl.types.TypeTreeCreator;
import org.apache.tuscany.sca.binding.corba.impl.types.util.TypeHelpersProxy;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.Object;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.ObjectImpl;
import org.omg.CORBA.portable.OutputStream;

/**
 * @version $Rev$ $Date$ Represents single CORBA request
 */
public class DynaCorbaRequest {

	private TypeTree returnTree;
	private List<TypeTree> arguments = new ArrayList<TypeTree>();
	private Map<String, TypeTree> exceptions = new HashMap<String, TypeTree>();
	private OutputStream outputStream;
	private ObjectImpl remoteObject;
	private String operation;

	/**
	 * Creates request.
	 * 
	 * @param ObjectremoteObject
	 *            remote object reference
	 * @param operation
	 *            operation to invoke
	 */
	public DynaCorbaRequest(Object remoteObject, String operation) {
		outputStream = ((ObjectImpl) remoteObject)._request(operation, true);
		this.remoteObject = (ObjectImpl) remoteObject;
		this.operation = operation;

	}

	/**
	 * Adds operation argument
	 * 
	 * @param argument
	 */
	public void addArgument(java.lang.Object argument) {
		TypeTree tree = TypeTreeCreator.createTypeTree(argument.getClass());
		TypeHelpersProxy.write(tree.getRootNode(), outputStream, argument);
	}

	/**
	 * Sets return type for operation
	 * 
	 * @param forClass
	 */
	public void setOutputType(Class<?> forClass) {
		returnTree = TypeTreeCreator.createTypeTree(forClass);
	}

	private String getExceptionId(Class<?> forClass) {
		String result = forClass.getName().replace('.', '/');
		result = result.replaceAll("Package", "");
		if (result.endsWith("Exception")) {
			result = result
					.substring(0, result.length() - "Exception".length());
		}
		result = "IDL:" + result + ":1.0";
		return result;
	}

	/**
	 * Configures possible exceptions
	 * 
	 * @param forClass
	 */
	public void addExceptionType(Class<?> forClass) {
		TypeTree tree = TypeTreeCreator.createTypeTree(forClass);
		String exceptionId = getExceptionId(forClass);
		exceptions.put(exceptionId, tree);
	}

	/**
	 * Handles application excpeition.
	 * 
	 * @param ae
	 *            occured exception
	 * @throws Exception
	 */
	private void handleApplicationException(ApplicationException ae)
			throws Exception {
		try {
			if (exceptions.size() == 0) {
				RequestConfigurationException exception = new RequestConfigurationException(
						"ApplicationException occured, but no exception type was specified.",
						ae.getId());
				throw exception;
			}
			InputStream is = ae.getInputStream();
			String exceptionId = is.read_string();
			TypeTree tree = exceptions.get(exceptionId);
			if (tree == null) {
				RequestConfigurationException exception = new RequestConfigurationException(
						"ApplicationException occured, but no such exception was defined",
						ae.getId());
				throw exception;
			} else {
				Exception ex = (Exception) TypeHelpersProxy.read(tree
						.getRootNode(), is);
				throw ex;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Handles exceptions generated by CORBA API
	 * 
	 * @param se
	 */
	private void handleSystemException(SystemException se) throws Exception {
		if (se instanceof BAD_OPERATION) {
			throw new CorbaException("Bad operation name: " + operation, se);
		} else {
			// TODO: handle more system exception types
			throw new CorbaException(se.getMessage(), se);
		}
	}

	/**
	 * Invokes previously configured request
	 * 
	 * @return
	 */
	public DynaCorbaResponse invoke() throws Exception {
		DynaCorbaResponse response = new DynaCorbaResponse();
		InputStream is = null;
		try {
			is = remoteObject._invoke(outputStream);
			if (is != null && returnTree != null) {
				response.setContent(TypeHelpersProxy.read(returnTree
						.getRootNode(), is));
			}
		} catch (ApplicationException ae) {
			handleApplicationException(ae);
		} catch (SystemException se) {
			handleSystemException(se);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
