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

package org.apache.tuscany.core.services.deployment;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

public class ContributionRepositoryTestCase extends TestCase {
    protected ContributionRepositoryImpl repository;
    protected File rootRepositoryDir;

    protected void setUp() throws Exception {
        super.setUp();
        this.rootRepositoryDir = new File("target/repository");

        //clean repository
        FileUtils.deleteDirectory(rootRepositoryDir);

        //create repository (this should re-create the root directory)
        this.repository = new ContributionRepositoryImpl(rootRepositoryDir, true);
        
    }

    public void testStore() throws Exception{
        String resourceLocation = "/repository/sample-calculator.jar";
        URI contribution = getClass().getResource(resourceLocation).toURI();
        InputStream contributionStream = getClass().getResourceAsStream(resourceLocation);
        repository.store(contribution,contributionStream);
        
        URL contributionURL = repository.find(contribution);
        assertNotNull(contributionURL);
    }
        
    public void testRemove() throws Exception {
        String resourceLocation = "/repository/sample-calculator.jar";
        URI contribution = getClass().getResource(resourceLocation).toURI();
        InputStream contributionStream = getClass().getResourceAsStream(resourceLocation);
        repository.store(contribution,contributionStream);
        
        repository.remove(contribution);
        URL contributionURL = repository.find(contribution);
        assertNull(contributionURL);
    }
    
    
    public void testList() throws Exception{
        String resourceLocation = "/repository/sample-calculator.jar";
        URI contribution = getClass().getResource(resourceLocation).toURI();
        InputStream contributionStream = getClass().getResourceAsStream(resourceLocation);
        repository.store(contribution,contributionStream);
        
        assertEquals(1, repository.list().size());        
    }
}
