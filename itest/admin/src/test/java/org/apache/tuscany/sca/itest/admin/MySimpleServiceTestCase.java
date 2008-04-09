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
package org.apache.tuscany.sca.itest.admin;

import static junit.framework.Assert.assertEquals;

import org.apache.tuscany.sca.itest.admin.MyService;
import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MySimpleServiceTestCase
{
    private static SCADomain domain;
    private static MyService myServiceOrg;
    private static MyService myServiceNew;
    private static MyService myServiceCary;

	@Test
    public void testPropertyDefault()
    {
        assertEquals("RTP",myServiceOrg.getLocation());
        assertEquals("2006",myServiceOrg.getYear());
    }

   
	 @Test 
    public void testPropertyOverrideWithValue()
    {
        assertEquals("CARY",myServiceCary.getLocation());
        assertEquals("2007",myServiceCary.getYear());
    }

    
	 @Test 
    public void testPropertyOverrideWithVariable()
    {
        assertEquals("Raleigh",myServiceNew.getLocation());
        assertEquals("2008",myServiceNew.getYear());
    }

  
    @BeforeClass
    public static void init() throws Exception {
        try {
        domain = SCADomain.newInstance("MySimpleService.composite");
        } catch ( Exception e ) { System.out.println("Could not initialize " + e.toString());
                                e.printStackTrace(); }
        myServiceOrg = domain.getService(MyService.class, "MyServiceComponentOrig/MyService");
        myServiceCary = domain.getService(MyService.class, "MyServiceComponentCary2007/MyService");
		myServiceNew = domain.getService(MyService.class, "MyServiceComponentNew/MyService");
    }
	
    @AfterClass
    public static void destroy() throws Exception {
        domain.close();
    }  
}
