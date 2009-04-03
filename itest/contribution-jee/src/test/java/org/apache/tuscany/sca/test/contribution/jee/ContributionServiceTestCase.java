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

package org.apache.tuscany.sca.test.contribution.jee;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.tuscany.sca.assembly.Component;
import org.apache.tuscany.sca.assembly.Composite;
import org.apache.tuscany.sca.assembly.Reference;
import org.apache.tuscany.sca.assembly.Service;
import org.apache.tuscany.sca.contribution.Artifact;
import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.service.ContributionService;
import org.apache.tuscany.sca.host.embedded.impl.EmbeddedSCADomain;
import org.apache.tuscany.sca.implementation.ejb.EJBImplementation;
import org.apache.tuscany.sca.implementation.jee.JEEImplementation;
import org.apache.tuscany.sca.implementation.web.WebImplementation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This is an integration test for Java EE contribution processing. *
 * 
 * @version $Rev$ $Date$
 */

public class ContributionServiceTestCase {
    private static final String CONTRIBUTION_001_ID = "contribution001/";
    private static final String CONTRIBUTION_002_ID = "contribution002/";
    private static final String JAR_CONTRIBUTION = "/repository/helloworld-service.jar";
    private static final String EJB_JAR_CONTRIBUTION = "/repository/helloworld-ejb.jar";
    private static final String EJB_JAR_W_DEPLOYABLE_CONTRIBUTION = "/repository/helloworld-ejb-w-dep.jar";
    private static final String WAR_CONTRIBUTION = "/repository/helloworld-web.war";
    private static final String WAR_W_DEPLOYABLE_CONTRIBUTION = "/repository/helloworld-web-w-dep.war";
    private static final String EAR_CONTRIBUTION = "/repository/helloworld-ejb.ear";
    private static final String EAR_NONENHANCED_CONTRIBUTION = "/repository/simple-app-ear.jar";
    private static final String EAR_ENHANCED_16_CONTRIBUTION = "/repository/simple-app.ear";
 

    private ClassLoader cl;
    private EmbeddedSCADomain domain;
    private ContributionService contributionService;

    /**
     * setUp() is a method in JUnit Frame Work which is executed before all others methods in the class extending
     * unit.framework.TestCase. So this method is used to create a test Embedded SCA Domain, to start the SCA Domain and
     * to get a reference to the contribution service
     */

    @Before
    public void setUp() throws Exception {
        //Create a test embedded SCA domain
        cl = getClass().getClassLoader();
        domain = new EmbeddedSCADomain(cl, "http://localhost");

        //Start the domain
        domain.start();

        //get a reference to the contribution service
        contributionService = domain.getContributionService();
    }

    /**
     * This method tests a non Java EE contribution.
     */
    @Test
    public void testContributeJAR() throws Exception {
        URL contributionLocation = getClass().getResource(JAR_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Assert.assertNotNull(contributionService.getContribution(contributionId));
    }

    /**
     * This method tests an EJB jar as a contribution.
     */
    @Test
    public void testContributeEJBJAR() throws Exception {
        URL contributionLocation = getClass().getResource(EJB_JAR_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Assert.assertNotNull(contributionService.getContribution(contributionId));

        Contribution contribution = contributionService.getContribution(contributionId);
        List<Artifact> artifacts = contribution.getArtifacts();
        for(Artifact artifact : artifacts) {
            if(artifact.getURI().toString().equals("META-INF/ejb-jar.composite")) {
                Composite composite = (Composite)artifact.getModel();
                Assert.assertEquals("Number of components", 1, composite.getComponents().size());
                Component component = composite.getComponents().get(0);
                Assert.assertTrue("Component with implementation.ejb", component.getImplementation() instanceof EJBImplementation);
                EJBImplementation ejbImpl = (EJBImplementation) component.getImplementation();
                Assert.assertEquals("Number of services", 1, ejbImpl.getServices().size());
                Service service = ejbImpl.getServices().get(0);
                Assert.assertEquals("Service name", "HelloworldService", service.getName());
            }
        }
    }

    /**
     * This method tests an EJB jar with sca-contribution.xml as a contribution.
     */
    @Test
    public void testContributeEJBJARwDeployable() throws Exception {
        URL contributionLocation = getClass().getResource(EJB_JAR_W_DEPLOYABLE_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Assert.assertNotNull(contributionService.getContribution(contributionId));

        Contribution contribution = contributionService.getContribution(contributionId);
        List<Artifact> artifacts = contribution.getArtifacts();
        for(Artifact artifact : artifacts) {
            if(artifact.getURI().toString().equals("META-INF/ejb-jar.composite")) {
                Composite composite = (Composite)artifact.getModel();
                Assert.assertEquals("Number of components", 1, composite.getComponents().size());
                Component component = composite.getComponents().get(0);
                Assert.assertTrue("Component with implementation.ejb", component.getImplementation() instanceof EJBImplementation);
                EJBImplementation ejbImpl = (EJBImplementation) component.getImplementation();
                Assert.assertEquals("Number of services", 1, ejbImpl.getServices().size());
                Service service = ejbImpl.getServices().get(0);
                Assert.assertEquals("Service name", "HelloworldService", service.getName());
            }
        }
        
        List<Composite> deployables = contribution.getDeployables();
        Assert.assertEquals("Deployable composites", 1, deployables.size());
        Composite composite = deployables.get(0);
        Assert.assertEquals("Number of components", 1, composite.getComponents().size());
        Component component = composite.getComponents().get(0);
        Assert.assertTrue("Component with implementation.ejb", component.getImplementation() instanceof EJBImplementation);
        EJBImplementation ejbImpl = (EJBImplementation) component.getImplementation();
        Assert.assertEquals("Number of services", 1, ejbImpl.getServices().size());
        Service service = ejbImpl.getServices().get(0);
        Assert.assertEquals("Service name", "HelloworldService", service.getName());
    }

    /**
     * This method tests WAR as a contribution.
     */
    @Test
    public void testContributeWAR() throws Exception {
        URL contributionLocation = getClass().getResource(WAR_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Assert.assertNotNull(contributionService.getContribution(contributionId));

        Contribution contribution = contributionService.getContribution(contributionId);
        List<Artifact> artifacts = contribution.getArtifacts();
        for(Artifact artifact : artifacts) {
            if(artifact.getURI().toString().equals("WEB-INF/web.composite")) {
                Composite composite = (Composite)artifact.getModel();
                Assert.assertEquals("Number of components", 1, composite.getComponents().size());
                Component component = composite.getComponents().get(0);
                Assert.assertTrue("Component with implementation.web", component.getImplementation() instanceof WebImplementation);
                WebImplementation webImpl = (WebImplementation) component.getImplementation();
                Assert.assertEquals("Number of reference", 2, webImpl.getReferences().size());
                List<String> referenceNames = new ArrayList<String>();
                referenceNames.add("name1");
                referenceNames.add("sample.HelloworldEjbServlet_service");
                for(Reference ref : webImpl.getReferences()) {
                    Assert.assertTrue(referenceNames.contains(ref.getName()));
                }
            }
        }
    }

    /**
     * This method tests WAR with sca-contribution.xml as a contribution.
     */
    @Test
    public void testContributeWARwDeployable() throws Exception {
        URL contributionLocation = getClass().getResource(WAR_W_DEPLOYABLE_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Assert.assertNotNull(contributionService.getContribution(contributionId));

        Contribution contribution = contributionService.getContribution(contributionId);
        List<Artifact> artifacts = contribution.getArtifacts();
        for(Artifact artifact : artifacts) {
            if(artifact.getURI().toString().equals("WEB-INF/web.composite")) {
                Composite composite = (Composite)artifact.getModel();
                Assert.assertEquals("Number of components", 1, composite.getComponents().size());
                Component component = composite.getComponents().get(0);
                Assert.assertTrue("Component with implementation.web", component.getImplementation() instanceof WebImplementation);
                WebImplementation webImpl = (WebImplementation) component.getImplementation();
                Assert.assertEquals("Number of reference", 2, webImpl.getReferences().size());
                List<String> referenceNames = new ArrayList<String>();
                referenceNames.add("name1");
                referenceNames.add("sample.HelloworldEjbServlet_service");
                for(Reference ref : webImpl.getReferences()) {
                    Assert.assertTrue(referenceNames.contains(ref.getName()));
                }
            }
        }
        
        List<Composite> deployables = contribution.getDeployables();
        Assert.assertEquals("Deployable composites", 1, deployables.size());
        Composite composite = deployables.get(0);
        Assert.assertEquals("Number of components", 1, composite.getComponents().size());
        Component component = composite.getComponents().get(0);
        Assert.assertTrue("Component with implementation.web", component.getImplementation() instanceof WebImplementation);
        WebImplementation webImpl = (WebImplementation) component.getImplementation();
        Assert.assertEquals("Number of reference", 2, webImpl.getReferences().size());
        List<String> referenceNames = new ArrayList<String>();
        referenceNames.add("name1");
        referenceNames.add("sample.HelloworldEjbServlet_service");
        for(Reference ref : webImpl.getReferences()) {
            Assert.assertTrue(referenceNames.contains(ref.getName()));
        }
    }

    /**
     * This method test EAR as a contribution.
     */
    @Test
    public void testContributeEAR() throws Exception {
        URL contributionLocation = getClass().getResource(EAR_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Assert.assertNotNull(contributionService.getContribution(contributionId));
    }

    /**
     * This method tests a JAR containing an EAR as a contribution.
     * non-enhanced EAR containing non-enhanced WAR, non-enhanced EJB JAR.
     */
    @Test
    public void testContributeEAR_NonEnhanced() throws Exception {
        URL contributionLocation = getClass().getResource(EAR_NONENHANCED_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Contribution contribution = contributionService.getContribution(contributionId);
        Assert.assertNotNull(contribution);
        List<Composite> deployables = contribution.getDeployables();
        Assert.assertEquals("Deployable composites", 1, deployables.size());
        Composite composite = deployables.get(0);
        Assert.assertEquals("Number of components", 1, composite.getComponents().size());
        Component component = composite.getComponents().get(0);
        Assert.assertTrue("Component with implementation.jee", component.getImplementation() instanceof JEEImplementation);
        JEEImplementation jeeImpl = (JEEImplementation)component.getImplementation();
        Assert.assertEquals("Number of services", 2, jeeImpl.getServices().size());
        Assert.assertEquals("Number of references", 1, jeeImpl.getReferences().size());
    }

    /**
     * This method tests an EAR as a contribution.
     * Enhanced EAR containing non-enhanced WAR, non-enhanced EJB JAR.
     */
    @Test
    public void testContributeEAR_Enhanced16() throws Exception {
        URL contributionLocation = getClass().getResource(EAR_ENHANCED_16_CONTRIBUTION);
        String contributionId = CONTRIBUTION_001_ID;
        contributionService.contribute(contributionId, contributionLocation, false);
        Contribution contribution = contributionService.getContribution(contributionId);
        Assert.assertNotNull(contribution);
        List<Composite> deployables = contribution.getDeployables();
        Assert.assertEquals("Deployable composites", 1, deployables.size());
        Composite composite = deployables.get(0);
        Assert.assertEquals("Number of components", 1, composite.getComponents().size());
        Component component = composite.getComponents().get(0);
        Assert.assertTrue("Component with implementation.jee", component.getImplementation() instanceof JEEImplementation);
        JEEImplementation jeeImpl = (JEEImplementation)component.getImplementation();
        Assert.assertEquals("Number of services", 2, jeeImpl.getServices().size());
        Assert.assertEquals("Number of references", 1, jeeImpl.getReferences().size());
    }
}
