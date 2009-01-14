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
package calculator;

import static org.junit.Assert.assertEquals;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.tuscany.sca.node.Contribution;
import org.apache.tuscany.sca.node.ContributionLocationHelper;
import org.apache.tuscany.sca.node.Node;
import org.apache.tuscany.sca.node.NodeFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This shows how to test the Calculator service component.
 */
public class CalculatorRMIReferenceTestCase {

    private static Node node;
    private static CalculatorService calculatorService;

    @BeforeClass
    public static void setUp() throws Exception {
        CalculatorRMIServiceImpl rmiCalculatorImpl = new CalculatorRMIServiceImpl();
        Registry rmiRegistry = LocateRegistry.createRegistry(8099);
        rmiRegistry.bind("CalculatorRMIService", rmiCalculatorImpl);
        
        String uri = ContributionLocationHelper.getContributionLocation("CalculatorRMIReference.composite");
        Contribution contribution = new Contribution("c1", uri);
        node = NodeFactory.newInstance().createNode("CalculatorRMIReference.composite", contribution);
        node.start();
        calculatorService = node.getService(CalculatorService.class, "CalculatorServiceComponent");
    }
    
    @AfterClass
    public static void tearDown() throws Exception {
        node.stop();
        LocateRegistry.getRegistry(8099).unbind("CalculatorRMIService");
    }

    @Test
    public void testCalculator() throws Exception {
        // Calculate
        assertEquals(calculatorService.add(3, 2), 5.0, 0.0);
        assertEquals(calculatorService.subtract(3, 2), 1.0, 0.0);
        assertEquals(calculatorService.multiply(3, 2), 6.0, 0.0);
        assertEquals(calculatorService.divide(3, 2), 1.5, 0.0);
    }
}
