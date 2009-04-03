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

/**
 * @version $Rev$ $Date$
 */
public interface MboxInterface {

	String sendArgs(String arg) throws Exception;

	boolean sendArgs(boolean arg) throws Exception;

	float sendArgs(float arg) throws Exception;

	double sendArgs(double arg) throws Exception;

	long sendArgs(long arg) throws Exception;

	int sendArgs(int arg) throws Exception;

	char sendArgs(char arg) throws Exception;

	short sendArgs(short arg) throws Exception;

	byte sendArgs(byte arg) throws Exception;

	void sendArgs(int arg1, String arg2) throws Exception;

	StructuredTuple sendArgs(StructuredTuple arg) throws Exception;

	String[] sendArgs(String[] arg) throws Exception;

	String[][] sendArgs(String[][] arg);
	
	byte[] sendArgs(byte[] arg);
	
}
