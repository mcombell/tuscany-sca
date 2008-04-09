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

import org.apache.tuscany.sca.itest.admin.MyTotalService;
import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyTotalServiceTestCase
{
    private static MyTotalService myTotalServiceOrg;
    private static MyTotalService myTotalServiceNew;

   private static SCADomain domain;

	 @Test
    public void testPropertyDefault()
    {
        assertEquals("RTP",myTotalServiceOrg.getLocation());
        assertEquals("2006",myTotalServiceOrg.getYear());
    }

    
	 @Test
    public void testPropertyOverrideVariable()
    {
        assertEquals("Raleigh",myTotalServiceNew.getLocation());
        assertEquals("2008",myTotalServiceNew.getYear());
    }

  
    @BeforeClass
    public static void init() throws Exception {
        try {
        domain = SCADomain.newInstance("Iteration3Composite.composite");
        } catch ( Exception e ) { e.printStackTrace(); }
		
        myTotalServiceOrg =domain.getService(MyTotalService.class, "MyTotalServiceComponent");
        myTotalServiceNew=domain.getService(MyTotalService.class, "MyTotalServiceNewComponent");
    }
	
    @AfterClass
    public static void destroy() throws Exception {
        domain.close();
    }
}
