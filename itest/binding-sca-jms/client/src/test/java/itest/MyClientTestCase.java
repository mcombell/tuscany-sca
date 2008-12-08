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
package itest;



import junit.framework.Assert;

import org.apache.tuscany.sca.node.SCAClient;
import org.apache.tuscany.sca.node.SCAContribution;
import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.SCANodeFactory;
import org.junit.Test;

/**
 * Runs a distributed domain in a single VM by using and in memory 
 * implementation of the distributed domain
 */
public class MyClientTestCase {

    @Test
    public void testCalculator() throws Exception {       
        
        SCANode serviceNode = SCANodeFactory.newInstance().createSCANode("MyService.composite", new SCAContribution("bla2", "../service/target/itest-binding-sca-jms-service.jar"));
        
        SCANode clientNode = SCANodeFactory.newInstance().createSCANodeFromClassLoader("MyClient.composite", null);
        try {

            serviceNode.start();
            clientNode.start();

            MyService service = ((SCAClient)clientNode).getService(MyService.class, "MyClientComponent");

            Assert.assertEquals("Hi Hello petra", service.sayHello("petra"));

        } finally {
            clientNode.stop();
            serviceNode.stop();
        }
    }
}
