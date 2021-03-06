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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.DefaultImport;
import org.apache.tuscany.sca.contribution.Export;
import org.apache.tuscany.sca.contribution.Import;
import org.apache.tuscany.sca.contribution.resolver.DefaultImportModelResolver;
import org.apache.tuscany.sca.core.FactoryExtensionPoint;
import org.apache.tuscany.sca.monitor.Monitor;
import org.apache.tuscany.sca.monitor.Problem;
import org.apache.tuscany.sca.monitor.Problem.Severity;
import org.apache.tuscany.sca.workspace.Workspace;
import org.apache.tuscany.sca.workspace.builder.ContributionBuilder;
import org.apache.tuscany.sca.workspace.builder.ContributionBuilderException;

/**
 * A contribution dependency builder.
 *
 * @version $Rev$ $Date$
 */
public class ContributionDependencyBuilderImpl implements ContributionBuilder {
    private static final Logger logger = Logger.getLogger(ContributionDependencyBuilderImpl.class.getName());
    
    /**
     * Constructs a new ContributionDependencyBuilder.
     */
    public ContributionDependencyBuilderImpl(FactoryExtensionPoint factories) {
    }
    
    public String getID() {
        return "org.apache.tuscany.sca.workspace.builder.ContributionDependencyBuilder";
    }
    
    public void build(Contribution contribution, Workspace workspace, Monitor monitor) throws ContributionBuilderException{
        contribution.getDependencies().clear();
        
        List<Contribution> dependencies = new ArrayList<Contribution>();
        Set<Contribution> set = new HashSet<Contribution>();

        dependencies.add(contribution);
        set.add(contribution);
        addContributionDependencies(contribution, workspace, dependencies, set, monitor);
        
        Collections.reverse(dependencies);
        
        contribution.getDependencies().addAll(dependencies);
    }
    
    /**
     * Analyze a contribution and add its dependencies to the given dependency set.
     * @param contribution
     * @param workspace
     * @param dependencies
     * @param set
     * @param monitor
     */
    private void addContributionDependencies(Contribution contribution, Workspace workspace, List<Contribution> dependencies, Set<Contribution> set, Monitor monitor) {
        
        // Go through the contribution imports
        for (Import import_: contribution.getImports()) {
            boolean resolved = false;
            
            // Go through all contribution candidates and their exports
            List<Export> matchingExports = new ArrayList<Export>();
            for (Contribution dependency: workspace.getContributions()) {
                if (dependency == contribution) {
                    // Do not self import
                    continue;
                }
                for (Export export: dependency.getExports()) {
                    
                    // If an export from a contribution matches the import in hand
                    // add that contribution to the dependency set
                    if (import_.match(export)) {
                        resolved = true;
                        matchingExports.add(export);

                        if (!set.contains(dependency)) {
                            set.add(dependency);
                            dependencies.add(dependency);
                            
                            // Now add the dependencies of that contribution 
                            addContributionDependencies(dependency, workspace, dependencies, set, monitor);
                        }
                    }
                }
            }
            
            if (resolved) {
                
                // Initialize the import's model resolver with a delegating model
                // resolver which will delegate to the matching exports 
                import_.setModelResolver(new DefaultImportModelResolver(matchingExports));
                
            } else {
                // Record import resolution issue
                if (!(import_ instanceof DefaultImport)) {
                    warning(monitor, "UnresolvedImport", import_, import_);
                }
            }
        }
    }

    /**
     * Report a warning.
     * 
     * @param problems
     * @param message
     * @param model
     */
    private static void warning(Monitor monitor, String message, Object model, Object... messageParameters) {
        if (monitor != null) {
            Problem problem = monitor.createProblem(ContributionDependencyBuilderImpl.class.getName(), "workspace-validation-messages", Severity.WARNING, model, message, (Object[])messageParameters);
            monitor.problem(problem);
        }
    }

}
