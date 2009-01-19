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

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.launcher.NodeLauncher;
//import org.apache.tuscany.sca.node.equinox.launcher.Contribution;
//import org.apache.tuscany.sca.node.equinox.launcher.ContributionLocationHelper;
//import org.apache.tuscany.sca.node.equinox.launcher.NodeLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Service;
import javax.xml.namespace.QName;

import test.ASM_0001_Client;
import testClient.TestInvocation;
import testClient.TestException_Exception;
import testClient.TestException;

/**
 * A generic test client based on JAX-WS APIs
 */
public class BaseJAXWSTestCase extends BaseTestCase {

    
    public static void main(String[] args) throws Exception {
    	BaseJAXWSTestCase test = new BaseJAXWSTestCase(); 
    	test.setUp();
    	test.tearDown();
    }
    
    public String invokeTest( String input ) throws Exception {
    	//Web service invocation via JAXWS
    	QName serviceName = new QName("http://test/", "TestInvocationService");
    	URL wsdlLocation = this.getClass().getClassLoader().getResource("TestClient.wsdl");
    	javax.xml.ws.Service webService = Service.create( wsdlLocation, serviceName );
    	TestInvocation wsProxy = (TestInvocation) webService.getPort(testConfiguration.getServiceInterface());

    	String output = wsProxy.invokeTest(input);

    	return output;
    } // end method invokeTest
   
    
} // end class BaseTest
