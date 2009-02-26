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

import org.apache.tuscany.sca.vtest.assembly.component.property.ServiceA;
import org.apache.tuscany.sca.vtest.assembly.component.property.ServiceB;
import org.apache.tuscany.sca.vtest.assembly.component.property.ServiceC;
import org.apache.tuscany.sca.vtest.utilities.ServiceFinder;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This test case covers the main functionalities of component property.
 *
 */
public class ComponentPropertyTestCase {
	
	private void initDomain(String compositePath) {
        System.out.println("Setting up");        
        ServiceFinder.init(compositePath);
    }

    private void cleanupDomain() {
        System.out.println("Cleaning up");
        ServiceFinder.cleanup();
    }
    
    /**
     * 
     * <p>
     * OSOA
     * The property has no @value attribute now. 
     * <p>
     * ASM50027
     * <p>
     * OASIS:
     * If the @value attribute of a component property element is declared, the type of the
     * property MUST be an XML Schema simple type and the @value attribute MUST 
     * contain a single value of that type
     */
    @Test
    @Ignore("It will be implemented in SCA 2.x codebase")
    public void testValueAttibuteValid_1() {
    	
    	initDomain("component_property_1.composite"); 
    	ServiceA service = ServiceFinder.getService(ServiceA.class , "AComponent/ServiceA") ;
    	Assert.assertEquals("I am a string"  ,service.getStrProperty()) ;
    	Assert.assertEquals("I am a object"  ,service.getObjProperty()) ;
    	cleanupDomain();
    	
    }
    
    /**
     * <p>
     * OSOA
     * The property has no @value attribute now. 
     * <p>
     * ASM50027
     * <p>
     * OASIS:
     * If the @value attribute of a component property element is declared, the type of the
     * property MUST be an XML Schema simple type and the @value attribute MUST 
     * contain a single value of that type
     */
    @Test
    @Ignore("It will be implemented in SCA 2.x codebase")
    public void testValueAttibuteValid_2() {
    	
    	initDomain("component_property_1.composite"); 
    	ServiceB service = ServiceFinder.getService(ServiceB.class , "BComponent/ServiceB") ;    	
//    	Assert.assertEquals("I am a object"  ,service.getObjProperty()) ;
    	//It will fail to get the value from composite file, so it will be an empty string
    	System.out.println("::::" + service.getStrProperty());
//    	System.out.println("::::" + service.getIntProperty());
//    	Assert.assertEquals("I am a string"  ,service.getStrProperty()) ;
    	
    	Assert.assertEquals(""  ,service.getObjProperty()) ;
    	cleanupDomain();
    	
    }
        
    /**
     * <p>
     * OSOA
     * Not mentioned in OSOA
     * <p> 
     * ASM50030
     * <p>
     * OASIS:
     * A <component/> element MUST NOT contain two <property/> subelements with the 
     * same value of the @name attribute
     */
    @Test
    @Ignore("TUSCANY-2863")
    public void testDuplicateProperty() {
    	
    	initDomain("component_duplicate_property.composite"); 
    	ServiceA service = ServiceFinder.getService(ServiceA.class , "AComponent/ServiceA") ;    	
//    	System.out.println(service.getStrProperty()) ;
    	Assert.assertEquals("value1", service.getStrProperty()) ;
    	cleanupDomain();
    	
    }
    
    /**
     * <p>
     * OSOA
     * No corresponding statements in OSOA. 
     * <p> 
     * ASM50031
     * <p>
     * OASIS:
     * The name attribute of a component property MUST match the name of a property
     * element in the component type of the component implementation.
     */
    @Test
    public void testPropertyNameMatched() {
    	
    	initDomain("component_property_3.composite"); 
    	ServiceC service = ServiceFinder.getService(ServiceC.class , "CComponent/ServiceC") ;    	
    	Assert.assertEquals("I am a string" , service.getStrProperty()) ;
    	cleanupDomain();
    	
    }
    
    /**
     * <p>
     * OSOA
     * No corresponding statements in OSOA.
     * <p> 
     * ASM50031
     * <p>
     * OASIS:
     * The name attribute of a component property MUST match the name of a property
     * element in the component type of the component implementation.
     */
    @Test
    public void testPropertyNameNotMatched() {
    	
    	initDomain("component_property_3.composite"); 
    	ServiceC service = ServiceFinder.getService(ServiceC.class , "CComponent/ServiceC") ;    	
    	Assert.assertNull(service.getObjProperty()) ;
    	cleanupDomain();
    	
    }
    
    
}
