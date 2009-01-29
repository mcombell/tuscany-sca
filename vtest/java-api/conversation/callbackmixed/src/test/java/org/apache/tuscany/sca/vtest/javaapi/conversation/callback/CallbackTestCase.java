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

package org.apache.tuscany.sca.vtest.javaapi.conversation.callback;

import org.apache.tuscany.sca.vtest.utilities.ServiceFinder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.osoa.sca.ServiceRuntimeException;

/**
 * 
 */
public class CallbackTestCase {

    protected static AService aService = null;

    @Before
    public void init() throws Exception {
        try {
            System.out.println("Setting up");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void destroy() throws Exception {

        System.out.println("Cleaning up");
        ServiceFinder.cleanup();
    }

    /**
     * Lines 534, 535
     * <p>
     * Callbacks may be used for both remotable and local services. Either both
     * interfaces of a bidirectional service must be remotable, or both must be
     * local. It is illegal to mix the two.
     * <p>
     * In this test configuration BServiceCallback is remotable and CService is
     * not
     */
    @Test(expected = ServiceRuntimeException.class)
    public void statefulMixedCallback() throws Exception {
        System.out.println("Setting up for mixed local/remote callback tests");
        ServiceFinder.init("callback-mixed.composite");
        
        // test an error was detected
        
        //aService = ServiceFinder.getService(AService.class, "AComponent");
        //aService.testCallback();
    }

}
