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
 * Client for ASM_6007_TestCase, which tests that a <composite/> <reference/> 
 * can declare an <interface/> that is a compatible superset of the <interface/> 
 * declared by each  <component/> <reference/> promoted by the composite reference  
 */
public class ASM_6007_TestCase extends BaseJAXWSTestCase {

    protected TestConfiguration getTestConfiguration() {
        TestConfiguration config = new TestConfiguration();
        config.testName = "ASM_6007";
        config.input = "request";
        config.output = "ASM_6007 request service1 operation1 invoked service2 operation1 invoked";
        config.composite = "Test_ASM_6007.composite";
        config.testServiceName = "TestClient";
        config.testClass = ASM_0002_Client.class;
        config.serviceInterface = TestInvocation.class;
        return config;
    }

} // end class Test_ASM_6007