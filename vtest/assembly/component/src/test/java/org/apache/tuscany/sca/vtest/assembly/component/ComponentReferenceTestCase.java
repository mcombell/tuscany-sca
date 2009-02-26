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
package org.apache.tuscany.sca.vtest.assembly.component;

import junit.framework.Assert;

import org.apache.tuscany.sca.vtest.assembly.component.reference.MyClientA;
import org.apache.tuscany.sca.vtest.assembly.component.reference.MyClientB;
import org.apache.tuscany.sca.vtest.assembly.component.reference.MyClientC;
import org.apache.tuscany.sca.vtest.assembly.component.reference.MyClientD;
import org.apache.tuscany.sca.vtest.assembly.component.reference.MyClientE;
import org.apache.tuscany.sca.vtest.utilities.ServiceFinder;
import org.junit.Ignore;
import org.junit.Test;
import org.osoa.sca.ServiceRuntimeException;

/**
 * Test the reference name, wire, wireByImpl, autowire and so on.
 *
 */
public class ComponentReferenceTestCase {

	private void initDomain(String compositePath) {
        System.out.println("Setting up");        
        ServiceFinder.init(compositePath);
    }

    private void cleanupDomain() {
        System.out.println("Cleaning up");
        ServiceFinder.cleanup();
    }
	
	/**
     * Line 1325-1326:
     * <p>
     * OSOA:
     * the name of the reference. The name must be unique across all the 
     * composite references in the composite.
     * <p>
     * ASM50007
     * <p>
     * OASIS:
     * the name of the reference. Has to match a name of a reference defined by the implementation.
     * The @name attribute of a reference element of a <component/> MUST be unique amongst the 
     * reference elements of that <component/>
     */
    @Test
    public void testReferenceNameUnique() {
    	
    	initDomain("referencename_1.composite");
    	
    	MyClientA service = ServiceFinder.getService(MyClientA.class, "ClientComponent1/MyClientA");
    	Assert.assertEquals("MyService:::MyService" , service.callOtherServices()) ;
    	
        cleanupDomain();
    }
    
    /**
     * Line 1325-1326:
     * <p>
     * OSOA:
     * the name of the reference. The name must be unique across all the 
     * composite references in the composite.
     * <p>
     * ASM50007
     * <p>
     * OASIS:
     * the name of the reference. Has to match a name of a reference defined by the implementation.
     * The @name attribute of a reference element of a <component/> MUST be unique amongst the 
     * reference elements of that <component/>
     */
    @Test
    public void testReferenceNameDuplicated() {
    	//for this case, the reference of "b" in MyClientImpl is null.
        try {
        	initDomain("referencename_2.composite"); 
        } catch (ServiceRuntimeException ex){
            Assert.assertEquals("Duplicate component reference name: Component = ClientComponent2 Reference = b", ex.getMessage());
            return;
        }
        Assert.fail();         
        cleanupDomain();
    }
    
    /**
     * Line 192-193:
     * <p>
     * OSOA:
     * the name of the reference. Has to match a name of a reference
     *  defined by the implementation.
     * <p>
     * ASM50008
     * <p>
     * OASIS:
     * the name of the reference. Has to match a name of a reference defined by the implementation.
     * 
     */    
     @Test
     public void testComponentReferenceNameMatched() {     
         initDomain("referencename_3.composite");        
         MyClientA service = ServiceFinder.getService(MyClientA.class, "ClientComponent1/MyClientA");           
         Assert.assertEquals("MyService:::MyService" , service.callOtherServices()) ;    
         cleanupDomain();
    }
     
     /**
      * Line 192-193:
      * <p>
      * OSOA:
      * the name of the reference. Has to match a name of a reference
      *  defined by the implementation.
      * <p>
      * ASM50008
      * <p>
      * OASIS:
      * the name of the reference. Has to match a name of a reference defined by the implementation.
      * 
      */    
    @Test
    public void testComponentReferenceNameValid() {
    	
        try {
            initDomain("referencename_4.composite");   
        } catch (ServiceRuntimeException ex){
            Assert.assertEquals("Reference not found for component reference: Component = ClientComponent1 Reference = bb", ex.getMessage());
            return;
        }
    	   	
    	Assert.fail();    	
        cleanupDomain();
    	
    }
    
    /**
     * 
     * <p>
     * ASM50010
     * <p>
     * OASIS:
     * If @wiredByImpl="true" is set for a reference, then the reference MUST NOT be 
     * wired statically within a composite, but left unwired
     */
    @Test
    @Ignore("Not implemented in SCA 1.x codebase.")
    public void testWiredByImpl() {
    	initDomain("reference_wiredbyimpl.composite");    	
    	MyClientA service = ServiceFinder.getService(MyClientA.class, "ClientComponent/MyClientA");    	
    	Assert.assertEquals("MyService:::MyService" , service.callOtherServices()) ;    	
        cleanupDomain();
    }
    
    
    /**
     * Line 208:
     * <p>
     * OSOA:
     * zero or one wire can have the reference as a source
     * <p>
     * ASM50018
     * <p>
     * OASIS
     * A reference with multiplicity 0..1 or 0..n MAY have no target 
     * service defined.
     * 
     */
    @Test    
    public void testMultiplicity_1() {
    	
    	initDomain("reference_multiplicity_zerotarget.composite");    	
    	MyClientC service = ServiceFinder.getService(MyClientC.class, "ClientComponent/MyClientC");    	
    	Assert.assertEquals("MyService_MyService" , service.callOtherService()) ;    	
        cleanupDomain();
    }
    
    /**
     * Line 208:
     * OSOA:
     * zero or one wire can have the reference as a source
     * <p>
     * ASM50019
     * <p>
     * OASIS:
     * A reference with multiplicity 0..1 or 1..1 MUST NOT have more than one target service
     *  defined.
     */
    @Test
    @Ignore("TUSCANY-2720")
    public void testMultiplicity_2() {
    	
    	initDomain("reference_multiplicity_moretargets.composite");    	
    	MyClientB service = ServiceFinder.getService(MyClientB.class, "ClientComponent/MyClientB");    	
    	Assert.assertEquals("MyService_MyService2_MyService_MyService2" , service.callOtherServices()) ;    	
        cleanupDomain();
    }
    
    /**
     * Line 207,209
     * <p>
     * OSOA:
     * 1..1 – one wire can have the reference as a source
     * 1..n – one or more wires can have the reference as a source
     * <p>
     * ASM50020
     * <p>
     * OASIS:
     * A reference with multiplicity 1..1 or 1..n MUST have at least one target 
     * service defined.
     */
    @Test    
    public void testMultiplicity_3() {
    	
    	initDomain("reference_multiplicity_ntargets.composite");    	
    	MyClientD service = ServiceFinder.getService(MyClientD.class, "ClientComponent/MyClientD");    	
    	Assert.assertEquals("MyService_MyService" , service.callOtherService_1()) ;
    	Assert.assertEquals("MyService_MyService" , service.callOtherService_2()) ;
    	
        cleanupDomain();
    }
    
    /**
     * Line 209-210
     * <p>
     * OSOA
     * 1..n – one or more wires can have the reference as a source
     * 0..n - zero or more wires can have the reference as a source
     * <p>
     * ASM50020
     * <p>
     * OASIS:
     * A reference with multiplicity 0..n or 1..n MAY have one or more
     * target services defined.
     */
    @Test
    public void testMultiplicity_4() {
    	
    	initDomain("reference_multiplicity_multitargets.composite");    	
    	MyClientE service = ServiceFinder.getService(MyClientE.class, "ClientComponent/MyClientE");    	
    	Assert.assertEquals("MyService_MyService2_MyService" , service.callOtherServices()) ;    	
    	
        cleanupDomain();
    }
    
}
