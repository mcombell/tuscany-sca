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

package org.apache.tuscany.sca.node.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tuscany.sca.domain.SCADomainService;
import org.apache.tuscany.sca.node.SCADomainFactory;
import org.apache.tuscany.sca.node.SCADomain;
import org.osoa.sca.CallableReference;
import org.osoa.sca.ServiceReference;


/**
 * Some utility methods for the Node implementation
 * 
 * @version $Rev: 556897 $ $Date: 2007-09-07 12:41:52 +0100 (Fri, 07 Sep 2007) $
 */
public class SCANodeUtil {
	private final static Logger logger = Logger.getLogger(SCANodeUtil.class.getName());
	
	/**
	 * Given the name of a composite this methid finds the contribution that it belongs to
	 * this could be either a local directory of a jar file.
	 * 
	 * @param classLoader
	 * @param compositeString
	 * @return the contribution URL
	 * @throws MalformedURLException
	 */
    public static URL findContributionFromComposite(ClassLoader classLoader, String compositeString)
      throws MalformedURLException {
    	   	
        URL contributionURL = classLoader.getResource(compositeString);
        
        if ( contributionURL != null ){ 
            String contributionString = contributionURL.toExternalForm();
            int jarPosition = contributionString.indexOf(".jar");
    	
            if (jarPosition> -1){
                // if the node dir is in a jar just contribute the name of the jar
                // file
                // rather the name of the directory in the jar file.
                // changing
                // jar:file:/myjarfile.jar!/contributiondir
                // to
                // file:/myjarfile.jar
                contributionString = contributionString.substring(0, jarPosition + 4);
                contributionString = contributionString.substring(4);
                contributionURL = new URL(contributionString);  
            } else {
                // Assume the node.composite file is in a directory so find the directory name
                // changing
                //   file:/mydir/node.composite
                // to 
                //   file:/mydir
                int compositePosition = contributionString.indexOf(compositeString);
                contributionString = contributionString.substring(0, compositePosition);
                contributionURL = new URL(contributionString);
            } 
        } 
        
    	return contributionURL;
    } 
}
