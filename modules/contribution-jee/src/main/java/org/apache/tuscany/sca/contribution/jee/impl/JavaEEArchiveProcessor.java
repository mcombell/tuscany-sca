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

import org.apache.tuscany.sca.contribution.jee.JavaEEApplicationInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEIntrospector;
import org.apache.tuscany.sca.contribution.processor.URLArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.ContributionResolveException;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.monitor.Monitor;

public class JavaEEArchiveProcessor implements URLArtifactProcessor<JavaEEApplicationInfo> {
    private JavaEEIntrospector jeeIntrospector;
    
    public JavaEEArchiveProcessor(ExtensionPointRegistry registry, Monitor monitor) {
         jeeIntrospector = registry.getExtensionPoint(JavaEEIntrospector.class);
    }

    public String getArtifactType() {
        return ".ear";
    }

    public JavaEEApplicationInfo read(URL contributionURL, URI artifactURI, URL artifactURL) throws ContributionReadException {
        JavaEEApplicationInfo jeeAppInfo = jeeIntrospector.introspectJeeArchive(artifactURL);
        jeeAppInfo.setUri(artifactURI);
        jeeAppInfo.setApplicationName(new File(artifactURL.getFile()).getName());
        return jeeAppInfo;
    }

    public Class<JavaEEApplicationInfo> getModelType() {
        return JavaEEApplicationInfo.class;
    }

    public void resolve(JavaEEApplicationInfo arg0, ModelResolver arg1)
            throws ContributionResolveException {
    }
}
