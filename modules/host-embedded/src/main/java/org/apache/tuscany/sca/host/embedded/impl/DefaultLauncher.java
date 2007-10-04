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

package org.apache.tuscany.sca.host.embedded.impl;

import java.io.IOException;

import javax.xml.namespace.QName;

//import org.apache.tuscany.sca.domain.SCADomain;
//import org.apache.tuscany.sca.node.SCANode;
//import org.apache.tuscany.sca.node.SCANodeFactory;

public class DefaultLauncher {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Tuscany starting...");
        System.out.println("Composite: " + args[0]);

/*        
        try {
            ClassLoader cl = DefaultLauncher.class.getClassLoader();
            SCANodeFactory nodeFactory = SCANodeFactory.newInstance();
            SCANode node = nodeFactory.createSCANode("node", null);
            node.addContribution("node", cl.getResource(args[0]));
            node.startComposite(new QName("??", "??"));
            node.start();  
        } catch (Exception ex) {
            System.err.println("Exception starting node " + ex.toString());
        }
*/
        
        System.out.println("Ready...");
        System.out.println("Press enter to shutdown");
        try {
            System.in.read();
        } catch (IOException e) {
        }
        System.exit(0);
    }

}
