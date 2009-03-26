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
package org.apache.tuscany.sca.contribution.jee.impl;

import java.io.File;
import java.net.URI;
import java.net.URL;

import org.apache.tuscany.sca.contribution.jee.EjbModuleInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEIntrospector;
import org.apache.tuscany.sca.contribution.processor.URLArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.monitor.Monitor;

/**
 * URLArtifactProcessor for EJB jars.
 * 
 * @version $Rev$ $Date$
 */
public class EjbArchiveProcessor implements URLArtifactProcessor<EjbModuleInfo> {
    private JavaEEIntrospector jeeIntrospector;
    
    public EjbArchiveProcessor(ExtensionPointRegistry registry, Monitor monitor) {
         jeeIntrospector = registry.getExtensionPoint(JavaEEIntrospector.class);
    }

    public String getArtifactType() {
        return ".jar";
    }

    public EjbModuleInfo read(URL contributionURL, URI artifactURI, URL artifactURL) throws ContributionReadException {
        if (jeeIntrospector != null) {
            EjbModuleInfo ejbModuleInfo = jeeIntrospector.introspectEjbArchive(artifactURL);
            if(ejbModuleInfo != null) {
                ejbModuleInfo.setUri(artifactURI);
                ejbModuleInfo.setModuleName(new File(artifactURL.getFile()).getName());
            }
            return ejbModuleInfo;
        }
        return null;
    }

    public Class<EjbModuleInfo> getModelType() {
        return EjbModuleInfo.class;
    }

    public void resolve(EjbModuleInfo arg0, ModelResolver arg1)
            throws ContributionResolveException {
    }
}
