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
 * For the cases where it is valid for the reference to have no target service specified, 
 * the component implementation language specification needs to define the programming model 
 * for interacting with an untargetted reference.
 * So the SCA runtime should throw an exception if it is using an untargetted reference.
 */
public class ASM_0040_TestCase extends BaseJAXWSTestCase {

 
	/**
	 * <p>
	 * OSOA
	 * No corresponding statements in OSOA specification.
	 * <p>
	 * OASIS
	 * ASM50024
	 * Some reference multiplicity errors can only be checked at runtime, 
	 * where the rules defined in ASM50018, ASM50019, ASM50020, ASM50021 are violated.
	 * In these cases, the SCA runtime MUST generate an error no later than when the reference is
	 * invoked by the component implementation.
	 */
    protected TestConfiguration getTestConfiguration() {
    	TestConfiguration config = new TestConfiguration();
    	config.testName 		= "ASM_0040";
    	config.input 			= "request";
    	config.output 			= "exception";
    	config.composite 		= "Test_ASM_0040.composite";
    	config.testServiceName 	= "TestClient";
    	config.testClass 		= ASM_0002_Client.class;
    	config.serviceInterface = TestInvocation.class;
    	return config;
    }
    
} // end class Test_ASM_0040
