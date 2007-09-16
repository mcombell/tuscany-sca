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

package org.apache.tuscany.sca.node.smaller;

import java.io.IOException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.assembly.builder.CompositeBuilderException;
import org.apache.tuscany.sca.contribution.service.ContributionException;
import org.apache.tuscany.sca.core.assembly.ActivationException;


/**
 * An SCA Node. Where a domain runs components
 * 
 * @version $Rev$ $Date$
 */
public abstract class SCANode {
    
    /**
     * Returns a new instance of an SCA node.
     * @param domainURI
     * @param nodeURI
     *  
     * @return
     */
    public static SCANode newInstance(String domainURI, String nodeURI) {
        // Create a new NodeImpl
        return null;
    }
    
    /**
     * Returns the URL of the contribution containing the specified composite file.
     * @param compositePath
     * @return
     */
    //TODO Not sure we really need this
    public abstract URL findContributionFromComposite(String compositeFile);
    
    /**
     * Returns the URI of this node
     * 
     * @return the node name
     */
    public abstract String getURI();
    
    /**
     * Returns the SCA domain that the node belongs to.
     * @return the SCA domain that the node belongs to.
     */
    public abstract SCADomain getSCADomain();

    /**
     * Accepts a new contribution and passes it onto the domain implementation
     * 
     * @param contributionLocation the URL location of the contribution
     * @throws ActivationException
     */
    public abstract void addContribution(URL contrubutionLocation)
     throws ActivationException, ContributionException, IOException, CompositeBuilderException;

    /**
     * Removes the specified contribution from the domain
     * 
     * @param contributionname
     * throws ActivationException
     */
    public abstract void removeContribution(URL contributionName)
      throws ActivationException, ContributionException;

    /**
     * Starts a named composite
     * 
     * @param domainUri the string uri for the distributed domain
     * @param nodeUri the string uri for the node
     * @param componentName the name of the component to be started
     */
    public abstract void startComposite(QName compositeName);

    /**
     * Stops a named composite
     * 
     * @param domainUri the string uri for the distributed domain
     * @param nodeUri the string uri for the node
     * @param componentName the name of the component to be started
     */
    public abstract void stopComposite(QName compositeName);
    
    /**
     * Starts a named composite
     * 
     * @param domainUri the string uri for the distributed domain
     * @param nodeUri the string uri for the node
     * @param componentName the name of the component to be started
     */
    public abstract void addComposite(QName compositeName);

    /**
     * Stops a named composite
     * 
     * @param domainUri the string uri for the distributed domain
     * @param nodeUri the string uri for the node
     * @param componentName the name of the component to be started
     */
    public abstract void removeComposite(QName compositeName);
    
}
