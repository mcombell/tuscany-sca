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

package org.apache.tuscany.sca.vtest.assembly.composite;

import junit.framework.Assert;

import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.apache.tuscany.sca.vtest.utilities.ServiceFinder;
import org.junit.Ignore;
import org.junit.Test;
import org.osoa.sca.ServiceRuntimeException;

/**
 *
 */
public class CompositeTestCase {

    static final String LOCAL_DOMAIN_URI = "http://localhost";

    private void initDomain(String compositePath) {
        System.out.println("Setting up");
        ServiceFinder.init(compositePath);
    }

    private void cleanupDomain() {
        System.out.println("Cleaning up");
        ServiceFinder.cleanup();
    }

    /**
     * ASM60001:
     * <p>
     * A composite name must be unique within the namespace of the composite.
     * <p>
     * OSOA specification doesn't have such requirement.
     */
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60001() throws Exception {
        SCADomain domain =
            SCADomain.newInstance(LOCAL_DOMAIN_URI, "/", "composite.composite", "compositecopy.composite");
        domain.getService(BService.class, "BComponent");
        domain.getService(CService.class, "CComponent");
        domain.close();
        domain = null;
    }
    
    /**
     * Lines 1036-1037:
     * <p>
     * local="true" means that all the components must run in the same process. 
     * <p>
     * ASM60002:
     * <p>
     * @local="true" for a composite means that all the components within the composite MUST run 
     * in the same operating system process. This is the only mode of Operation in Tuscany at
     * the moment so we just show here that even when local is false both components are run
     * 
     */
    @Test//(expected = ServiceRuntimeException.class)
    public void ASM60002() throws Exception {
        initDomain("localcomponent.composite");
        DService dservice = ServiceFinder.getService(DService.class, "DServiceComponent");
        Assert.assertEquals("Hello, tester", dservice.getGreetings("tester"));
        BService bService = ServiceFinder.getService(BService.class, "BComponent");
        Assert.assertEquals("some b component value", bService.getSomeProperty());
    }

    /**
     * Lines 1498-1499:
     * <p>
     * name (required) – the name of the service, the name MUST BE unique across
     * all the composite services in the composite.
     * <p>
     * ASM60003:
     * <p>
     * The name of a composite <service/> element MUST be unique across all the
     * composite services in the composite.
     * <p>
     * OSOA specification is the same with OASIS in this function.
     */
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60003() throws Exception {
        initDomain("nonuniqueservice_outer.composite");
        CService cService = ServiceFinder.getService(CService.class, "TestNonUniqueServiceComponent/BCService");
        cService.getName();
        BService bService = ServiceFinder.getService(BService.class, "TestNonUniqueServiceComponent/BCService");
        bService.getSomeProperty();        
        cleanupDomain();
    }

    /**
     * ASM60004:
     * <p>
     * A composite <service/> element's promote attribute MUST identify one of
     * the component services within that composite.
     * <p>
     * OSOA specification doesn't have such requirement.
     */
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60004() throws Exception {
        initDomain("nonpromote_outer.composite");
        ServiceFinder.getService(BService.class, "TestNonPromoteComponent/NoPromoteService");
        cleanupDomain();
    }
    
    /**
     * Lines 1512-1514:
     * <p>
     * If an interface is specified it must be the same or a compatible subset
     * of the interface provided by the promoted component service, i.e. provide
     * a subset of the operations defined by the component service.
     * <p>
     * ASM60005:
     * <p>
     * If a composite service interface is specified it must be the same or a
     * compatible subset of the interface provided by the promoted component
     * service, i.e. provide a subset of the operations defined by the component
     * service.
     * <p>
     * OSOA specification is the same with OASIS in this function.
     */
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60005() throws Exception {
        initDomain("differentinterface_outer.composite");
        BService service = ServiceFinder.getService(BService.class, "DifferentInterfaceComponent/DifferentInterfaceService");
        service.getSomeProperty();
        cleanupDomain();
    }

    /**
     * Lines 1325-1327:
     * <p>
     * name (required) – the name of the reference. The name must be unique
     * across all the composite references in the composite. 
     * <p>
     * ASM60006:
     * <p>
     * name : NCName (1..1) - the name of the reference. The name of a composite
     * <reference/> element MUST be unique across all the composite references
     * in the composite.
     * <p>
     * OSOA specification is the same with OASIS in this function.
     */
    @Ignore("TUSCANY-2701")
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60006() throws Exception {
        initDomain("nonuniquereference_outer.composite");
        AService service = ServiceFinder.getService(AService.class, "TestNonUniqueReferenceComponent/AService");
        service.getBProperty();
        service.getState();
        cleanupDomain();
    }

    
}
