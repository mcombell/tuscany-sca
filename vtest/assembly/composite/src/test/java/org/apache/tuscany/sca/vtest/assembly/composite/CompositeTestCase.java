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
    @Test
    public void ASM60001_p() throws Exception {
        SCADomain domain = SCADomain.newInstance(LOCAL_DOMAIN_URI, "/", "compositeb.composite", "compositec.composite");
        BService bService = domain.getService(BService.class, "BComponent");
        Assert.assertEquals("some b component value", bService.getSomeProperty());
        CService cService = domain.getService(CService.class, "CComponent");
        Assert.assertEquals("Some State", cService.getState());
        domain.close();
        domain = null;
    }

    /**
     * ASM60001:
     * <p>
     * A composite name must be unique within the namespace of the composite.
     * <p>
     * OSOA specification doesn't have such requirement.
     */
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60001_n() throws Exception {
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
     * @local="true" for a composite means that all the components within the
     * composite MUST run in the same operating system process.This is the only
     * mode of Operation in Tuscany at the moment so we just show here that even
     * when local is false both components are run OSOA specification is the
     * same with OASIS in this function.
     */
    @Ignore("This feature hasn't been implemented by tuscany yet.")
    @Test
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
    @Test
    public void ASM60003_p() throws Exception {
        initDomain("service_outer.composite");
        CService cService = ServiceFinder.getService(CService.class, "TestServiceComponent/CService");
        Assert.assertEquals("Some State", cService.getState());
        BService bService = ServiceFinder.getService(BService.class, "TestServiceComponent/BService");
        Assert.assertEquals("some b component value", bService.getSomeProperty());
        cleanupDomain();
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
    public void ASM60003_n() throws Exception {
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
    @Test
    public void ASM60004_p() throws Exception {
        initDomain("service_outer.composite");
        CService cService = ServiceFinder.getService(CService.class, "TestServiceComponent/CService");
        Assert.assertEquals("Some State", cService.getState());
        BService bService = ServiceFinder.getService(BService.class, "TestServiceComponent/BService");
        Assert.assertEquals("some b component value", bService.getSomeProperty());
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
    public void ASM60004_n() throws Exception {
        initDomain("nonservicepromote_outer.composite");
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
    @Test
    public void ASM60005_p() throws Exception {
        initDomain("subserviceinterface_outer.composite");
        CSubService cService = ServiceFinder.getService(CSubService.class, "SubInterfaceComponent/SubInterfaceService");
        Assert.assertEquals("Some State", cService.getState());
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
    public void ASM60005_n() throws Exception {
        initDomain("differentserviceinterface_outer.composite");
        BService service =
            ServiceFinder.getService(BService.class, "DifferentInterfaceComponent/DifferentInterfaceService");
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
    @Test
    public void ASM60006_p() throws Exception {
        initDomain("reference_outer.composite");
        AService aService = ServiceFinder.getService(AService.class, "TestReferenceComponent/AService");
        Assert.assertEquals("some b component value", aService.getBProperty());
        Assert.assertEquals("Some State", aService.getState());
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
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60006_n() throws Exception {
        initDomain("nonuniquereference_outer.composite");
        AService service = ServiceFinder.getService(AService.class, "TestNonUniqueReferenceComponent/AService");
        service.getBProperty();
        service.getState();
        cleanupDomain();
    }

    /**
     * ASM60007:
     * <p>
     * Each of the URIs declared by a composite reference's @promote attribute
     * MUST identify a component reference within the composite.
     * <p>
     * OSOA specification doesn't have such requirement.
     */
    @Ignore("TUSCANY-2762")
    @Test(expected = ServiceRuntimeException.class)
    public void ASM60007_n() throws Exception {
        initDomain("nonreferencepromote_outer.composite");
        AService service = ServiceFinder.getService(AService.class, "TestNonPromoteComponent");
        service.getState();
        cleanupDomain();
    }

    /**
     * ASM60007:
     * <p>
     * Each of the URIs declared by a composite reference's @promote attribute
     * MUST identify a component reference within the composite.
     * <p>
     * OSOA specification doesn't have such requirement.
     */
    @Test
    public void ASM60007_p() throws Exception {
        initDomain("reference_outer.composite");
        AService aService = ServiceFinder.getService(AService.class, "TestReferenceComponent/AService");
        Assert.assertEquals("some b component value", aService.getBProperty());
        Assert.assertEquals("Some State", aService.getState());
    }

    /**
     * ASM60008:
     * <p>
     * The interfaces of the component references promoted by a composite
     * reference MUST be the same, or if the composite reference itself declares
     * an interface then all the component reference interfaces must be
     * compatible with the composite reference interface. Compatible means that
     * the component reference interface is the same or is a strict subset of
     * the composite reference interface.
     * <p>
     * OSOA specification doesn't have such requirement.
     */
    @Ignore("TUSCANY-2766,TUSCANY-2883")
    @Test
    public void ASM60008_n() throws Exception {
        initDomain("differentreferenceinterface_outer.composite");
        AService service = ServiceFinder.getService(AService.class, "DifferentInterfaceComponent/AService");
        System.out.println(service.getState());
        cleanupDomain();
    }

    /**
     * ASM60008:
     * <p>
     * The interfaces of the component references promoted by a composite
     * reference MUST be the same, or if the composite reference itself declares
     * an interface then all the component reference interfaces must be
     * compatible with the composite reference interface. Compatible means that
     * the component reference interface is the same or is a strict subset of
     * the composite reference interface.
     * <p>
     * OSOA specification doesn't have such requirement.
     */
    @Test
    public void ASM60008_p() throws Exception {
        initDomain("subreferenceinterface_outer.composite");
        AService service = ServiceFinder.getService(AService.class, "SubReferenceInterfaceComponent/AService");
        Assert.assertEquals("some b component value", service.getBProperty());
        Assert.assertEquals("Some State", service.getState());
        cleanupDomain();
    }

    /**
     * Lines 1637-1638:
     * <p>
     * the source interface and the target interface MUST either both be
     * remotable or they are both local
     * <p>
     * ASM60015:
     * <p>
     * the source interface and the target interface of a wire MUST either both
     * be remotable or else both be local
     * <p>
     * OSOA specification is the same with OASIS in this function.
     */
    @Test
    public void ASM60015_p() throws Exception {
        initDomain("wire.composite");
        FService fservice = ServiceFinder.getService(FService.class, "FComponent");
        Assert.assertEquals("DProperty", fservice.getGreetings(""));
        Assert.assertEquals("Some State", fservice.getString(""));
        cleanupDomain();
    }

    /**
     * Lines 1637-1638:
     * <p>
     * the source interface and the target interface MUST either both be
     * remotable or they are both local
     * <p>
     * ASM60015:
     * <p>
     * the source interface and the target interface of a wire MUST either both
     * be remotable or else both be local
     * <p>
     * OSOA specification is the same with OASIS in this function.
     */
    @Ignore("TUSCANY-2844")
    @Test
    public void ASM60015_n() throws Exception {
        initDomain("differentlocalwire.composite");
        FService fservice = ServiceFinder.getService(FService.class, "FComponent");
        Assert.assertEquals("DProperty", fservice.getGreetings(""));
        Assert.assertEquals("Some State", fservice.getString(""));
        cleanupDomain();
    }

    /**
     * Lines 1639-1640:
     * <p>
     * the operations on the target interface of a wire MUST be the same as or
     * be a superset of the operations in the interface specified on the source
     * ASM60016:
     * <p>
     * the operations on the target interface of a wire MUST be the same as or
     * be a superset of the operations in the interface specified on the source
     * <p>
     * OSOA specification is the same with OASIS in this function.
     */
    @Ignore("TUSCANY-2843")
    @Test
    public void ASM60016_n() throws Exception {
        initDomain("subinterfacewire.composite");
        FService fservice = ServiceFinder.getService(FService.class, "FComponent");
        Assert.assertEquals("DProperty", fservice.getGreetings(""));
        Assert.assertEquals("Some State", fservice.getString(""));
        cleanupDomain();
    }
    
    /**
     * Lines 1639-1640:
     * <p>
     * the operations on the target interface of a wire MUST be the same as or
     * be a superset of the operations in the interface specified on the source
     * ASM60016:
     * <p>
     * the operations on the target interface of a wire MUST be the same as or
     * be a superset of the operations in the interface specified on the source
     * <p>
     * OSOA specification is the same with OASIS in this function.
     */
    @Test
    public void ASM60016_p() throws Exception {
        initDomain("wire.composite");
        FService fservice = ServiceFinder.getService(FService.class, "FComponent");
        Assert.assertEquals("DProperty", fservice.getGreetings(""));
        Assert.assertEquals("Some State", fservice.getString(""));
        cleanupDomain();
    }

}
