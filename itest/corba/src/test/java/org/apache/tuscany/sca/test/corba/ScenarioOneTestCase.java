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

package org.apache.tuscany.sca.test.corba;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.apache.tuscany.sca.host.corba.naming.TransientNameServer;
import org.apache.tuscany.sca.host.corba.naming.TransientNameService;
import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.apache.tuscany.sca.test.corba.generated.Color;
import org.apache.tuscany.sca.test.corba.generated.InnerStruct;
import org.apache.tuscany.sca.test.corba.generated.RichStruct;
import org.apache.tuscany.sca.test.corba.generated.ScenarioOne;
import org.apache.tuscany.sca.test.corba.generated.ScenarioOneHelper;
import org.apache.tuscany.sca.test.corba.generated.ScenarioOneOperations;
import org.apache.tuscany.sca.test.corba.generated.UnexpectedException;
import org.apache.tuscany.sca.test.corba.generated.WrongColor;
import org.apache.tuscany.sca.test.corba.types.ScenarioOneServant;
import org.apache.tuscany.sca.test.corba.types.TColor;
import org.apache.tuscany.sca.test.corba.types.TInnerStruct;
import org.apache.tuscany.sca.test.corba.types.TRichStruct;
import org.apache.tuscany.sca.test.corba.types.TScenarioOne;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

/**
 * This test class contains three main tests:<br>
 * 1. Tuscany is being used as a consumer of some non-Tuscany CORBA service<br>
 * 2. Tuscany is being used as a service provider, which will be consumed by
 * non-Tuscany CORBA client<br>
 * 3. Tuscany is being used as a service provider, which will be consumed by
 * Tuscany client<br>
 * But that's not all, there are some other variations. Tuscany CORBA binding
 * supports using Java interface generated by IDLJ, also it supports interfaces
 * provided by user - I combined those cases in each test.<br>
 */
public class ScenarioOneTestCase {

    // note that those values are also used in resources/*.composite file
    private static int ORB_INITIAL_PORT = 5060;
    private static String SERVICE_NAME = "ScenarioOne";

    private static SCADomain domain;

    private static TransientNameServer server;
    private static ORB orb;

    /**
     * Sets up name service, creates and registers traditional CORBA service,
     * obtains SCADomain
     */
    @BeforeClass
    public static void setUp() {
        try {
            try {
                server =
                    new TransientNameServer("localhost", ORB_INITIAL_PORT, TransientNameService.DEFAULT_SERVICE_NAME);
                Thread t = server.start();
                if (t == null) {
                    Assert.fail("The naming server cannot be started");
                }
                orb = server.getORB();
            } catch (Throwable e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
            NameComponent nc = new NameComponent(SERVICE_NAME, "");
            NameComponent path[] = {nc};
            ScenarioOne scenarioOne = new ScenarioOneServant();
            ncRef.rebind(path, scenarioOne);
            // obtain domain
            domain = SCADomain.newInstance("ScenarioOne.composite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Kills previously spawned name service.
     */
    @AfterClass
    public static void tearDown() {
        server.stop();
    }

    /**
     * Creates nicely filled structure for user provided interface.
     * 
     * @return
     */
    private static TRichStruct getTRichStruct() {
        int[][] intArr = new int[][] { {1, 2}, {3, 4}};
        TInnerStruct innerStruct = new TInnerStruct(intArr, "Test", TColor.green);
        String[] strSeq = {"i", "Test"};
        return new TRichStruct(innerStruct, strSeq, 1);

    }

    /**
     * Creates nicely filled structure for generated interface.
     * 
     * @return
     */
    private static RichStruct getRichStruct() {
        int[][] intArr = new int[][] { {1, 2}, {3, 4}};
        InnerStruct innerStruct2 = new InnerStruct(intArr, "Test", Color.green);
        String[] strSeq = {"i", "Test"};
        return new RichStruct(innerStruct2, strSeq, 1);
    }

    /**
     * Compares String arrays
     * 
     * @param arg1
     * @param arg2
     * @return
     */
    private boolean areSrringArraysEqual(String[] arg1, String[] arg2) {
        try {
            for (int i = 0; i < arg1.length; i++) {
                if (!arg1[i].equals(arg2[i])) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Compares two dimensional int arrays
     * 
     * @param arg1
     * @param arg2
     * @return
     */
    private boolean areTwoDimIntArraysEqual(int[][] arg1, int[][] arg2) {
        try {
            for (int i = 0; i < arg1.length; i++) {
                for (int j = 0; j < arg1[i].length; j++) {
                    if (arg1[i][j] != arg2[i][j]) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean equalTo(TInnerStruct arg1, TInnerStruct arg2) {
        return (arg1.color.value() == arg2.color.value() && arg1.stringField.equals(arg2.stringField) && areTwoDimIntArraysEqual(arg1.twoDimLongSequence,
                                                                                                                                 arg2.twoDimLongSequence));
    }

    private boolean equalTo(InnerStruct arg1, InnerStruct arg2) {
        return (arg1.color.value() == arg2.color.value() && arg1.stringField.equals(arg2.stringField) && areTwoDimIntArraysEqual(arg1.twoDimLongSequence,
                                                                                                                                 arg2.twoDimLongSequence));
    }

    private boolean equalTo(TRichStruct arg1, TRichStruct arg2) {
        return (equalTo(arg1.innerStruct, arg2.innerStruct) && arg2.longField == arg1.longField && areSrringArraysEqual(arg1.stringSequence,
                                                                                                                        arg2.stringSequence));
    }

    private boolean equalTo(RichStruct arg1, RichStruct arg2) {
        return (equalTo(arg1.innerStruct, arg2.innerStruct) && arg2.longField == arg1.longField && areSrringArraysEqual(arg1.stringSequence,
                                                                                                                        arg2.stringSequence));
    }

    /**
     * Helper method used several times for various components. Executes several
     * tests using Tuscany reference binding. This helper uses generated Java
     * interface.
     * 
     * @param componentName
     */
    private void testClientUsingGeneratedInterface(String componentName) {
        ScenarioOneOperations component = domain.getService(ScenarioOneOperations.class, componentName);
        RichStruct richStruct = getRichStruct();

        try {
            RichStruct result = component.setRichStruct(richStruct);
            assertTrue(equalTo(result, richStruct));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        try {
            richStruct.longField = 0;
            component.setRichStruct(richStruct);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof UnexpectedException);
        }

        try {
            richStruct.longField = 1;
            richStruct.innerStruct.color = Color.red;
            component.setRichStruct(richStruct);
        } catch (Exception e) {
            assertTrue(e instanceof WrongColor);
        }
    }

    /**
     * Helper method used several times for various components. Executes several
     * tests using Tuscany reference binding. This helper uses user provided
     * Java interface.
     * 
     * @param componentName
     */
    private void testClientUsingUserProvidedInterface(String componentName) {
        TScenarioOne component = domain.getService(TScenarioOne.class, componentName);
        TRichStruct tRichStruct = getTRichStruct();

        try {
            TRichStruct result = component.setRichStruct(tRichStruct);
            assertTrue(equalTo(result, tRichStruct));
        } catch (Exception e) {
            fail();
        }

        try {
            tRichStruct.longField = 0;
            component.setRichStruct(tRichStruct);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof UnexpectedException);
        }

        try {
            tRichStruct.longField = 1;
            tRichStruct.innerStruct.color = TColor.red;
            component.setRichStruct(tRichStruct);
        } catch (Exception e) {
            assertTrue(e instanceof WrongColor);
        }
    }

    public void testServiceUsingGeneratedClient(String serviceName) {
        try {
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
            NameComponent nc = new NameComponent(serviceName, "");
            NameComponent path[] = {nc};
            ScenarioOne so = ScenarioOneHelper.narrow(ncRef.resolve(path));

            RichStruct richStruct = getRichStruct();
            RichStruct result = so.setRichStruct(richStruct);
            assertTrue(equalTo(result, richStruct));

            try {
                richStruct.innerStruct.color = Color.red;
                result = so.setRichStruct(richStruct);
                fail();
            } catch (Exception e) {
                assertTrue(e instanceof WrongColor);
            }

            try {
                richStruct.innerStruct.color = Color.green;
                richStruct.longField = 0;
                result = so.setRichStruct(richStruct);
                fail();
            } catch (Exception e) {
                assertTrue(e instanceof UnexpectedException);
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Service is exposed in traditional way (using CORBA API from JDK).
     * Reference is obtained from Tuscany.
     */
    @Test
    public void test_TraditionalService_TuscanyClient() {

        // Client is using user provided interface
        testClientUsingUserProvidedInterface("ScenarioOne");

        // Client is using generated interface
        testClientUsingGeneratedInterface("ScenarioOneGenerated");
    }

    /**
     * Service is exposed by Tuscany. Reference is obtained in traditional way.
     * (using CORBA API from JDK)
     * 
     * @throws Exception
     */
    @Test
    public void test_TuscanyService_TraditionalClient() throws Exception {

        // tests service which uses user provided interface
        testServiceUsingGeneratedClient("ScenarioOneTuscany");

        // tests service which uses generated interface
        testServiceUsingGeneratedClient("ScenarioOneTuscanyGenerated");
    }

    /**
     * Service is exposed by Tuscany. Reference is obtained from Tuscany. There
     * are 4 combinations (basing on if we are using generated or user provided
     * interfaces, both on service and reference side).
     */
    @Test
    public void test_TuscanyService_TuscanyClient() {

        // Client is using user provided interface, service is using user
        // provided interface.
        testClientUsingUserProvidedInterface("TU2TS1");

        // Client is using user provided interface, service is using generated
        // interface.
        testClientUsingUserProvidedInterface("TU2TS2");

        // Client is using generated interface, service is using user provided
        // interface.
        testClientUsingGeneratedInterface("TG2TS1");

        // Client is using generated interface, service is using generated
        // interface.
        testClientUsingGeneratedInterface("TG2TS2");
    }
    
    /**
     * Tests using reference obtained by corbaname URI
     */
    @Test
    public void test_serviceAndReferenceByURI() {
        testClientUsingUserProvidedInterface("UriBinding");
    }

}
