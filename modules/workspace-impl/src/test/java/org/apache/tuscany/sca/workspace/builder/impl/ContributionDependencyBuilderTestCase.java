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

package org.apache.tuscany.sca.workspace.builder.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.ContributionFactory;
import org.apache.tuscany.sca.contribution.DefaultContributionFactory;
import org.apache.tuscany.sca.contribution.namespace.DefaultNamespaceImportExportFactory;
import org.apache.tuscany.sca.contribution.namespace.NamespaceExport;
import org.apache.tuscany.sca.contribution.namespace.NamespaceImport;
import org.apache.tuscany.sca.contribution.namespace.NamespaceImportExportFactory;
import org.apache.tuscany.sca.workspace.DefaultWorkspaceFactory;
import org.apache.tuscany.sca.workspace.Workspace;
import org.apache.tuscany.sca.workspace.WorkspaceFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the contribution dependency analyzer.
 * 
 * @version $Rev$ $Date$
 */
public class ContributionDependencyBuilderTestCase {

    private ContributionFactory contributionFactory;
    private WorkspaceFactory workspaceFactory;
    private NamespaceImportExportFactory importExportFactory;

    @Before
    public void setUp() throws Exception {
        contributionFactory = new DefaultContributionFactory();
        workspaceFactory = new DefaultWorkspaceFactory();
        importExportFactory = new DefaultNamespaceImportExportFactory();
    }

    @Test
    public void testAnalyze() throws Exception {
        Workspace workspace = workspaceFactory.createWorkspace();
        Contribution importer = contributionFactory.createContribution();
        importer.setURI("importer");
        workspace.getContributions().add(importer);
        NamespaceImport import_ = importExportFactory.createNamespaceImport();
        import_.setNamespace("http://foo");
        importer.getImports().add(import_);

        Contribution imported = contributionFactory.createContribution();
        imported.setURI("imported");
        workspace.getContributions().add(imported);
        NamespaceExport export = importExportFactory.createNamespaceExport();
        export.setNamespace("http://foo");
        imported.getExports().add(export);
        import_ = importExportFactory.createNamespaceImport();
        import_.setNamespace("http://bar");
        imported.getImports().add(import_);
        
        Contribution imported2 = contributionFactory.createContribution();
        imported2.setURI("imported2");
        workspace.getContributions().add(imported2);
        export = importExportFactory.createNamespaceExport();
        export.setNamespace("http://bar");
        imported2.getExports().add(export);
        
        Contribution another = contributionFactory.createContribution();
        another.setURI("another");
        workspace.getContributions().add(another);
        export = importExportFactory.createNamespaceExport();
        export.setNamespace("http://another");
        another.getExports().add(export);
        
        ContributionDependencyBuilderImpl builder = new ContributionDependencyBuilderImpl(null);
        builder.build(importer, workspace, null);
        List<Contribution> dependencies = importer.getDependencies();
        assertTrue(dependencies.size() == 3);
        assertTrue(dependencies.contains(importer));
        assertTrue(dependencies.contains(imported));
        assertTrue(dependencies.contains(imported2));
    }
    
}
