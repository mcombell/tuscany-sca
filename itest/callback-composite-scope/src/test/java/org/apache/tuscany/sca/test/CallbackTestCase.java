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

package org.apache.tuscany.sca.test;

import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @version $Rev$ $Date$
 */
public class CallbackTestCase {

    private static SCADomain domain;
    private HelloworldDelegate client;
    private HelloworldDelegate client2;
    private HelloworldCallback callback;
    private HelloworldCallback callback2;

    @Test
    @Ignore("TUSCANY-2823")
    public void testCallbacks() {
        String name = "John";
        Assert.assertEquals("Hello "+callback.whoIs(name) + " " + name, client.sayHello(name));
        Assert.assertEquals("Hello "+callback2.whoIs(name) + " " + name, client2.sayHello(name));
    }

    @Before    
    public void setUp() throws Exception {
    	if( domain == null ) {
    		domain = SCADomain.newInstance("HelloworldDelegate.composite");
    	}
    	
    	client = domain.getService(HelloworldDelegate.class, "HelloworldDelegateComponent/HelloworldDelegate");
        callback = domain.getService(HelloworldCallback.class, "HelloworldDelegateComponent/HelloworldCallback");
        client2 = domain.getService(HelloworldDelegate.class, "HelloworldDelegateComponent2/HelloworldDelegate");
        callback2 = domain.getService(HelloworldCallback.class, "HelloworldDelegateComponent2/HelloworldCallback");
    }

    @After
    public void tearDown() throws Exception {
        domain.close();
    }


}
