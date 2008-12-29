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

package org.zhulei.test.hessian.first;

import java.io.IOException;
import java.util.List;

import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.SCANodeFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 
 */
public class FirstTestCase {
    private static SCANode node;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        node = SCANodeFactory.newInstance().createSCANodeFromClassLoader("first_hessian.composite", null);
        node.start();
    }
    
    @Test
    public void testServer() throws IOException {
        try {
            HessianProxyFactory proxyFactory = new HessianProxyFactory();
            IFirst service = (IFirst)proxyFactory.create(IFirst.class, "http://localhost:8085/hessian1");
            List<String> datas = service.getData("123", "ABC");
            for (int i = 0; i < datas.size(); i++) {
                System.out.println(datas.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        node.stop();
    }

}
