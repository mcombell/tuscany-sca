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

import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.tuscany.sca.node.SCAClient;
import org.apache.tuscany.sca.node.SCAContribution;
import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.launcher.Contribution;
import org.apache.tuscany.sca.node.launcher.NodeLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.ASM_0001_Client;
import test.TestInvocation;
import testClient.TestException;
import testClient.TestException_Exception;

/**
 * A generic test client based on Tuscany APIs
 */
public class BaseTestCase {

    protected NodeLauncher launcher;
    protected SCANode node;
    protected TestConfiguration testConfiguration = getTestConfiguration();
    protected static boolean proceed = true;
    
    public static void main(String[] args) throws Exception {
    	BaseTestCase test = new BaseTestCase(); 
    	test.setUp();
    	test.tearDown();
    }
    
    @Before
    public void setUp() throws Exception {	
        try {
            startContribution();
        } catch (Exception e) {
            // If the SCA runtime refuses to start an invalid contribution, then this is also
            // regarded as a successful outcome
            System.out.println( "Exception received - detail: " + e.getMessage() );
            assertEquals( testConfiguration.getExpectedOutput(), "exception" );
            System.out.println("Test " + testConfiguration.getTestName() + " completed successfully");
            // Mark this test as not to proceed further
            proceed = false;
        } // end try
    }

    @After
    public void tearDown() throws Exception {
    	stopContribution();
    }

    @Test
    public void testDummy() throws Exception {
        // If an exception were thrown during initialization, let's go no further
        if( proceed == false ) return;
        
        // System.out.println("Test " + testName + " starting");
        String output = null;
        try {
            // Just requires input to proceed
            // System.in.read();
            //
            output = invokeTest( testConfiguration.getInput() );

        } catch ( TestException_Exception e ) {
            TestException exceptionContent = e.getFaultInfo();
            System.out.println("Service fault received - detail: " + exceptionContent.getMessage() );
            assertEquals( testConfiguration.getExpectedOutput(), "exception" );
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println( "Exception received - detail: " + e.getMessage() );
            assertEquals( testConfiguration.getExpectedOutput(), "exception" );
        }
        assertEquals( testConfiguration.getExpectedOutput(), output );
        System.out.println("Test " + testConfiguration.getTestName() + " completed successfully");
    }
    
    public String invokeTest( String input ) throws Exception {

    	TestInvocation service = (TestInvocation) getService( testConfiguration.getServiceInterface(), 
    														  testConfiguration.getTestServiceName() );    	
    	
    	return service.invokeTest( input );
    } // end method invokeTest
    
    protected <T> T getService( Class<T> interfaze, String serviceName ) {
    	T service = ((SCAClient)node).getService( interfaze, serviceName );
    	return service;
    } // end getService
    
    protected void startContribution() throws Exception {
    	// Tuscany specific code which starts the contribution holding the test
        launcher = NodeLauncher.newInstance();
        node = launcher.createNode(testConfiguration.getComposite(), 
        		                   new Contribution(testConfiguration.getTestName(), getContributionURI()));
        System.out.println("SCA Node API ClassLoader: " + node.getClass().getClassLoader());
        node.start();
    } // end method startContribution
    
    protected void stopContribution() throws Exception {
        if (node != null) {
            node.stop();
        }

    } // end method stopContribution
    
    protected String getContributionURI() {
    	String location = getContributionLocation(testConfiguration.getTestClass());
    	return location;
    }
    
    public static String getContributionLocation(final Class<?> anchorClass) {
        URL url = AccessController.doPrivileged(new PrivilegedAction<URL>() {
            public URL run() {
                return anchorClass.getProtectionDomain().getCodeSource().getLocation();
            }
        });
        String uri = url.toString();
        return uri;
    }       
    
    protected TestConfiguration getTestConfiguration() {
    	TestConfiguration config = new TestConfiguration();
    	config.testName 		= "ASM_0001";
    	config.input 			= "request";
    	config.output 			= config.testName + " " + config.input + " invoked ok";
    	config.composite 		= "Test_ASM_0101.composite";
    	config.testServiceName 	= "TestClient";
    	config.testClass 		= ASM_0001_Client.class;
    	config.serviceInterface = TestInvocation.class;
    	return config;
    }
    
} // end class BaseTest
