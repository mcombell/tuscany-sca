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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.Assert;

import org.apache.tuscany.sca.vtest.assembly.component.callback.MyClient;
import org.apache.tuscany.sca.vtest.utilities.ServiceFinder;
import org.junit.Test;
import org.junit.Ignore;
import org.osoa.sca.ServiceRuntimeException;

/**
 * Test the basic functionalities of the component
 *
 */
public class ComponentBasicTestCase {
	
	
	private void initDomain(String compositePath) {
        System.out.println("Setting up");        
        ServiceFinder.init(compositePath);
    }

    private void cleanupDomain() {
        System.out.println("Cleaning up");
        ServiceFinder.cleanup();
    }
	
    /**
     * Lines 92-96:
     * <p>
     * Components are configured instances of implementations. Components
     * provide and consume services. More than one component can use and
     * configure the same implementation, where each component configures the
     * implementation differently.
     */
    @Test
    public void components1() throws Exception {
        initDomain("component.composite");
        AService service = ServiceFinder.getService(AService.class, "AComponent/AService");
        Assert.assertEquals("some b component value", service.getBProperty());
        Assert.assertEquals("some b2 component value", service.getB2Property());
        cleanupDomain();
    }

    /**
     * Lines 96-97:
     * <p>
     * There can be zero or more component elements within a composite.
     */
    @Test
    public void components2() throws Exception {
        initDomain("zerocomponents.composite");
        cleanupDomain();
    }

    /**
     * Lines 142-143:
     * <p>
     * name (required) ?the name of the component. The name must be unique
     * across all the components in the composite.
     */
    @Test(expected = ServiceRuntimeException.class)
    //@Ignore("TUSCANY-2455")
    public void components3() throws Exception {
        initDomain("nonuniquename.composite");
        cleanupDomain();
    }

    /**
     * Lines 154-158:
     * <p>
     * A component element has zero or one implementation element as its child,
     * which points to the implementation used by the component. A component
     * with no implementation element is not runnable, but components of this
     * kind may be useful during a "top-down" development process as a means of
     * defining the characteristics required of the implementation before the
     * implementation is written.
     */
    @Test
    public void components4() throws Exception {
        initDomain("zeroimplelements.composite");
        cleanupDomain();
    }

    /**
     * Lines 159-160:
     * <p>
     * The component element can have zero or more service elements as children
     * which are used to configure the services of the component.
     */
    @Test
    public void components5() throws Exception {
        initDomain("serviceelement.composite");
        cleanupDomain();
    }

    /**
     * Lines 174-179:
     * <p>
     * A service has zero or one interface, which describes the operations
     * provided by the service. The interface is described by an interface
     * element which is a child element of the service element. If no interface
     * is specified, then the interface specified for the service by the
     * implementation is in effect. If an interface is specified it must provide
     * a compatible subset of the interface provided by the implementation, i.e.
     * provide a subset of the operations defined by the implementation for the
     * service.
     */
    @Test
    public void components6() throws Exception {
        initDomain("servicewithinterface.composite");
        CService service = ServiceFinder.getService(CService.class, "CComponent");
        Assert.assertEquals("Some State", service.getState());
        cleanupDomain();
    }

    /**
     * Lines 180-182:
     * <p>
     * A service element has one or more binding elements as children. If no
     * bindings are specified, then the bindings specified for the service by
     * the implementation are in effect. If bindings are specified, then those
     * bindings override the bindings specified by the implementation.
     */
    @Test
    public void components7() throws Exception {
        initDomain("servicewithbinding.composite");
        CService service = ServiceFinder.getService(CService.class, "CComponent");
        Assert.assertEquals("Some State", service.getState());
        cleanupDomain();
    }
    
	/**
     * Lines 142-143:
     * <p>
     * OSOA:
     * the name of the component. The name must be unique across all the
     * components in the composite.
     * <p>
     * ASM50001
     * <p>
     * OASIS:
     * the name of the component. The name must be unique across all 
     * the components in the composite.
     * 
     */
    @Test(expected=ServiceRuntimeException.class)
    public void testComponentnameUnique() throws Exception {    	
    	initDomain("nonuniquename.composite");
        cleanupDomain();
    	
    }
    
    /**
     * Lines 1498-1499:
     * <p>
     * OSOA:
     * the name of the service, the name MUST BE unique across all the 
     * composite services in the composite
     * <p>
     * ASM50002
     * <p>
     * OASIS:
     * The @name attribute of a service element of a <component/> MUST be unique amongst 
     * the service elements of that <component/>
     * 
     */
    @Test(expected=ServiceRuntimeException.class)
    public void testServicenameUnique() throws Exception {
    	initDomain("nonuniqueservicenameincomposite.composite");
    	DService service = ServiceFinder.getService(DService.class, "CDComponent/CServiceImpl");
        Assert.assertEquals("hello", service.sayHello());
    	cleanupDomain();
    }
    
    /**
     * <p>
     * Not mentioned in OSOA
     * <p>
     * ASM50003
     * <p>
     * OASIS
     * The @name attribute of a service element of a <component/> MUST match the 
     * @name attribute of a service element of the componentType of the 
     * <implementation/> child element of the component.
     * <p>
     * For the service name, OSOA says(1598-1500):
     * name (required) – the name of the service, the name MUST BE unique across all the 
     * composite services in the composite. The name of the composite service can be different
     * from the name of the promoted component service.
     */
    @Test(expected=ServiceRuntimeException.class)
    public void testServicenameNotmatch() throws Exception {
    	initDomain("notmatchofservicename.composite");
    	DService service = ServiceFinder.getService(DService.class, "DComponent1/DService1");
        Assert.assertEquals("hello", service.sayHello());    	
    	cleanupDomain();
    }
    
    /**
     * <p>
     * Not mentioned in OSOA
     * <p>
     * ASM50003
     * <p>
     * OASIS
     * The @name attribute of a service element of a <component/> MUST match the 
     * @name attribute of a service element of the componentType of the 
     * <implementation/> child element of the component.
     * 
     */
    public void testServicenameMatch() throws Exception {
    	initDomain("notmatchofservicename.composite");
    	DService service = ServiceFinder.getService(DService.class, "DComponent1/DService");
        Assert.assertEquals("hello", service.sayHello());    	
    	cleanupDomain();
    }
    
    
    /**
     * Lines 177-178:
     * <p>
     * OSOA:
     * If an interface is specified it must provide a compatible subset of the
     * interface provided by the implementation
     * <p>
     * ASM50004
     * <p>      
     * OASIS:
     * If a <service/> element has an interface subelement specified, the interface MUST 
     * provide a compatible subset of the interface declared on the componentType of the 
     * implementation. 
     */
    @Test(expected=ServiceRuntimeException.class)
    public void testInterfaceCompatible() throws Exception {
    	//for this case, if you remove the method3() in the EEService
    	//the EEService will be a compatible subset of EService.
    	initDomain("notcompatibleinterface.composite");    	
    	EEService service = ServiceFinder.getService(EEService.class, "EEComponent/EEService");
        Assert.assertEquals("method1", service.method1());
    	cleanupDomain();
    }
    
    /**
     * This method helps to check if the binding.ws is working.
     * if the http response contains the "wsdl:definitions",
     * we can regard it as a wsdl message, then the wsdlURL is valid. 
     * @param wsdlURL
     * @return
     */
    private boolean isValidWebService(String wsdlURL) {
    	
    	try {
			URL url = new URL(wsdlURL) ;
			URLConnection conn = url.openConnection() ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
			String lineStr ;
			while ((lineStr = reader.readLine()) != null) {
				if (lineStr.indexOf("wsdl:definitions") != -1 ) {
					return true ;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
		return false ;
    }
    
    /**
     * <p>
     * No matched OSOA statements found
     * <p>
     * ASM50005
     * <p>
     * OASIS:
     * If no binding elements are specified for the service, then the bindings specified for 
     * the equivalent service in the componentType of the implementation MUST be used,
     * but if the componentType also has no bindings specified, then <binding.sca/> 
     * MUST be used as the binding. If binding elements are specified for the service, then 
     * those bindings MUST be used and they override any bindings specified for the 
     * equivalent service in the componentType of the implementation.
     */
    @Test
    @Ignore("broken, see TUSCANY-2946")
    public void testServiceBinding() throws Exception {
    	
    	initDomain("binding_resolution.composite");	
    	
    	FService service ;
    	//test no binding in component
    	service = ServiceFinder.getService(FService.class, "FComponent/FService1");
        Assert.assertEquals("hello", service.sayHello());    
        Assert.assertEquals(true , isValidWebService("http://localhost:8085/FService1?wsdl")) ;
        //test no binding in componentType
        service = ServiceFinder.getService(FService.class, "FComponent/FService2");
        Assert.assertEquals("hello", service.sayHello());
        //test binding overriding
        service = ServiceFinder.getService(FService.class, "FComponent/FService3");
        Assert.assertEquals("hello", service.sayHello());  
        Assert.assertEquals(true , isValidWebService("http://localhost:8085/FService3?wsdl")) ;
    	
    	cleanupDomain();
    }
    
    /**
     * Line 1526-1529:
     * <P>
     * OSOA:
     * A service element has an optional callback element used if the interface 
     * has a callback defined, which has one or more binding elements as children. 
     * The callback and its binding child elements are specified if there is a need 
     * to have binding details used to handle callbacks. If the callback element is not present, 
     * the behaviour is runtime implementation dependent.
     * <p>
     * ASM50006
     * <p>
     * If the callback element is present and contains one or more binding 
     * child elements,then those bindings MUST be used for the callback.
     * 
     */
    @Test
    public void testCallbackBindings() throws Exception{
    	
    	initDomain("callback_bindings.composite");
    	MyClient client = ServiceFinder.getService(MyClient.class, "MyServiceClientComponent/MyClient");
    	client.callService() ;
    	
    	cleanupDomain();
    }
    
    
    
    
    
    
    

}
