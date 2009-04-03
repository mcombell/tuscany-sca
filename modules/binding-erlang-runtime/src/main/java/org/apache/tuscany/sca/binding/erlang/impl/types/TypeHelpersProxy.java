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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tuscany.sca.binding.erlang.impl.TypeMismatchException;

import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangTuple;

/**
 * @version $Rev$ $Date$
 */
public class TypeHelpersProxy {

	private static Map<Class<?>, TypeHelper> primitiveTypes = null;

	static {
		// initiate type helpers
		primitiveTypes = new HashMap<Class<?>, TypeHelper>();
		primitiveTypes.put(boolean.class, new BooleanTypeHelper());
		primitiveTypes.put(short.class, new ShortTypeHelper());
		primitiveTypes.put(byte.class, new ByteTypeHelper());
		primitiveTypes.put(char.class, new CharTypeHelper());
		primitiveTypes.put(int.class, new IntTypeHelper());
		primitiveTypes.put(long.class, new LongTypeHelper());
		primitiveTypes.put(float.class, new FloatTypeHelper());
		primitiveTypes.put(double.class, new DoubleTypeHelper());
		primitiveTypes.put(String.class, new StringTypeHelper());
		primitiveTypes.put(Boolean.class, primitiveTypes.get(boolean.class));
		primitiveTypes.put(Character.class, primitiveTypes.get(char.class));
		primitiveTypes.put(Short.class, primitiveTypes.get(char.class));
		primitiveTypes.put(Byte.class, primitiveTypes.get(byte.class));
		primitiveTypes.put(Short.class, primitiveTypes.get(short.class));
		primitiveTypes.put(Integer.class, primitiveTypes.get(int.class));
		primitiveTypes.put(Long.class, primitiveTypes.get(long.class));
		primitiveTypes.put(Float.class, primitiveTypes.get(float.class));
		primitiveTypes.put(Double.class, primitiveTypes.get(double.class));
		primitiveTypes.put(byte[].class, new BinaryTypeHelper());
	}

	private static TypeHelper getTypeHelper(Class<?> forClass) {
		TypeHelper typeHelper = primitiveTypes.get(forClass);
		if (typeHelper == null && forClass.isArray()) {
			typeHelper = new ListTypeHelper();
		}
		if (typeHelper == null) {
			typeHelper = new TupleTypeHelper();
		}
		return typeHelper;
	}

	/**
	 * Converts Java objects arrays to Erlang: 1. single object (if array arity
	 * == 1) or 2. tuple (if array arity > 1)
	 * 
	 * @param objects
	 * @return
	 */
	public static OtpErlangObject toErlang(Object[] objects) {
		OtpErlangObject result = null;
		if (objects != null) {
			TypeHelper helper = null;
			switch (objects.length) {
			case 0:
				result = new OtpErlangList();
				break;
			case 1:
				helper = getTypeHelper(objects[0].getClass());
				result = helper.toErlang(objects[0]);
				break;
			default:
				OtpErlangObject[] erlObjects = new OtpErlangObject[objects.length];
				for (int i = 0; i < objects.length; i++) {
					helper = getTypeHelper(objects[i].getClass());
					erlObjects[i] = helper.toErlang(objects[i]);
				}
				result = new OtpErlangTuple(erlObjects);
				break;
			}
		}
		return result;
	}

	/**
	 * Creates Erlang list basing on unknown Java arrays
	 * 
	 * @param array
	 * @return
	 */
	public static OtpErlangList toErlangAsList(Object array) {
		OtpErlangList result = null;
		if (array != null) {
			List<OtpErlangObject> attrsList = new ArrayList<OtpErlangObject>();
			int i = 0;
			while (true) {
				try {
					TypeHelper helper = getTypeHelper(Array.get(array, i)
							.getClass());
					attrsList.add(helper.toErlang(Array.get(array, i)));
					i++;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
			result = new OtpErlangList(attrsList
					.toArray(new OtpErlangObject[attrsList.size()]));
		} else {
			result = new OtpErlangList();
		}
		return result;
	}

	/**
	 * Converts single Erlang object to Java
	 * 
	 * @param object
	 * @param forClass
	 * @return
	 * @throws Exception
	 */
	public static Object toJava(OtpErlangObject object, Class<?> forClass)
			throws Exception {
		try {
			TypeHelper helper = getTypeHelper(forClass);
			return helper.toJava(object, forClass);
		} catch (ClassCastException e) {
			throw new TypeMismatchException(forClass, object.getClass());
		}
	}

	/**
	 * Creates array of Java objects from Erlang list
	 * 
	 * @param objects
	 * @param forClass
	 * @return
	 * @throws Exception
	 */
	public static Object[] toJavaFromList(OtpErlangList objects,
			Class<?>[] forClass) throws Exception {
		Object[] result = new Object[objects.arity()];
		try {
			for (int i = 0; i < objects.arity(); i++) {
				TypeHelper helper = getTypeHelper(forClass[i]);
				result[i] = helper.toJava(objects.elementAt(i), forClass[i]);
			}
		} catch (Exception e) {
			// type mismatch as mismatch of parameters count or parameters type
			if (e.getClass().equals(ClassCastException.class)
					|| e.getClass()
							.equals(ArrayIndexOutOfBoundsException.class))
				throw new TypeMismatchException();
		}
		return result;
	}

	/**
	 * Converts incoming Erlang message to operation arguments
	 * 
	 * @param objects
	 * @param forClass
	 * @return
	 * @throws Exception
	 */
	public static Object[] toJavaAsArgs(OtpErlangObject objects,
			Class<?>[] forClass) throws Exception {
		OtpErlangObject[] args = null;
		// normalize input
		if (objects.getClass().equals(OtpErlangTuple.class)) {
			args = new OtpErlangObject[((OtpErlangTuple) objects).arity()];
			for (int i = 0; i < ((OtpErlangTuple) objects).arity(); i++) {
				args[i] = ((OtpErlangTuple) objects).elementAt(i);
			}
		} else {
			args = new OtpErlangObject[1];
			args[0] = objects;
		}
		Object[] result = new Object[args.length];
		try {
			for (int i = 0; i < args.length; i++) {
				TypeHelper helper = getTypeHelper(forClass[i]);
				result[i] = helper.toJava(args[i], forClass[i]);
			}
		} catch (Exception e) {
			// type mismatch as mismatch of parameters count or parameters type
			if (e.getClass().equals(ClassCastException.class)
					|| e.getClass()
							.equals(ArrayIndexOutOfBoundsException.class))
				throw new TypeMismatchException();
		}
		return result;
	}

}
