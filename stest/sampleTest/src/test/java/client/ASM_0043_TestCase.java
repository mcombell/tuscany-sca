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
package client;


import test.ASM_0002_Client;
import testClient.TestInvocation;

/**
 * Client for ASM_0043_TestCase, it tests @uri and subelement of <binding> can't coexist,
 * and some binding can use specific subelements more than a simple URI. 
 * 
 */
public class ASM_0043_TestCase extends BaseJAXWSTestCase {

 
	/**
	 * <p>
	 * OSOA 
	 * <p>
	 * OASIS
	 * ASM50016
	 * It is possible that a particular binding type MAY require that the address of a target service 
	 * uses more than a simple URI. In cases where a reference element has a binding subelement of such a type, 
	 * the @uri attribute of the binding element MUST NOT be used to identify the target service -
	 * instead, binding specific attributes and/or child elements MUST be used.
	 * <p>
	 * Jira issue:
	 * https://issues.apache.org/jira/browse/TUSCANY-2921
	 */
    protected TestConfiguration getTestConfiguration() {
    	TestConfiguration config = new TestConfiguration();
    	config.testName 		= "ASM_0043";
    	config.input 			= "request";
    	config.output 			= "exception";
    	config.composite 		= "Test_ASM_0043.composite";
    	config.testServiceName 	= "TestClient";
    	config.testClass 		= ASM_0002_Client.class;
    	config.serviceInterface = TestInvocation.class;
    	return config;
    }
    
} // end class Test_ASM_0043
