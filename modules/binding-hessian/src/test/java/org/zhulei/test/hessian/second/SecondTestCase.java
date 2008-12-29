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

package org.zhulei.test.hessian.second;

import java.io.IOException;

import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.SCANodeFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 
 */
public class SecondTestCase {
    private static SCANode node1;
    private static SCANode node2;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        node1 = SCANodeFactory.newInstance().createSCANodeFromClassLoader("second_hessian.composite", null);
        node1.start();
        
        node2 = SCANodeFactory.newInstance().createSCANodeFromClassLoader("first_hessian.composite", null);
        node2.start();
    }
    
    @Test
    public void testServer() throws IOException {
        try {
            HessianProxyFactory proxyFactory = new HessianProxyFactory();
            ISecond service = (ISecond)proxyFactory.create(ISecond.class, "http://localhost:8085/hessian2");
            String str = service.getString("XYZ");
            System.out.println(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        node1.stop();
        node2.stop();
    }

}
