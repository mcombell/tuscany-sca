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

package org.apache.tuscany.sca.node;

import java.net.URL;

import javax.xml.namespace.QName;

/**
 * The SPI for a node
 * 
 * @version $Rev: 580520 $ $Date: 2007-09-29 00:50:25 +0100 (Sat, 29 Sep 2007) $
 */
public interface SCANodeSPI {

    public Object getNodeRuntime();
    
    public void startFromDomain() throws NodeException;
    
    public void stopFromDomain() throws NodeException;
    
    public void addContributionFromDomain(String contributionURI, URL contributionURL, ClassLoader contributionClassLoader ) throws NodeException;   
    
    public void removeContributionFromDomain(String contributionURI) throws NodeException;  
    
    public void addToDomainLevelCompositeFromDomain(QName compositeQName) throws NodeException;     
}