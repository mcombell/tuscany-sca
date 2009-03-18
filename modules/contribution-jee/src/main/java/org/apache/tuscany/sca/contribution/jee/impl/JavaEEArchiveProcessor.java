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
