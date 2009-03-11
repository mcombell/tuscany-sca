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
 * Client for ASM_0026_TestCase, which tests that where a <component/> <property/> 
 * has its value set by means of a child element that is NOT a <value/> element, 
 * that a) the type of the <property/> element is declared as an XML Schema global 
 * element by its @element attribute and b) that the child element is an instance 
 * of that global element 
 */
public class ASM_0026_TestCase extends BaseJAXWSTestCase {

 
	/**
	 * <p>
	 * OSOA Line 1113~1118
	 * The property element may contain an optional default-property-value, which provides default
	 * value for the property. The default value must match the type declared for the property
	 *  1) a string, if type is a simple type (must match the type declared)
	 *  2) a complex type value matching the type declared by type
	 *  3) an element matching the element named by element
	 *  4) multiple values are permitted if many="true" is specified 
	 * <p>
	 * OASIS 
	 * <p>
	 * ASM50029
	 * If a component property value is declared using a child element of the <property/>
	 * element, the type of the property MUST be an XML Schema global element and the
	 * declared child element MUST be an instance of that global element
	 */
    protected TestConfiguration getTestConfiguration() {
    	TestConfiguration config = new TestConfiguration();
    	config.testName 		= "ASM_0026";
    	config.input 			= "request";
    	config.output 			= "ASM_0026 request service1 operation1 invokedcomplex1complex2";
    	config.composite 		= "Test_ASM_0026.composite";
    	config.testServiceName 	= "TestClient";
    	config.testClass 		= ASM_0002_Client.class;
    	config.serviceInterface = TestInvocation.class;
    	return config;
    }
    
} // end class Test_ASM_0003
