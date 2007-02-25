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
package org.apache.tuscany.sca.test.exceptions;

import junit.framework.AssertionFailedError;

import org.apache.tuscany.sca.test.exceptions.impl.StockTraderSDO;
import org.apache.tuscany.sca.test.exceptions.sdohandgen.InvalidSymbolSDOException;
import org.apache.tuscany.spi.databinding.TransformationException;
import org.apache.tuscany.test.SCATestCase;
import org.osoa.sca.CompositeContext;
import org.osoa.sca.CurrentCompositeContext;

import stockexceptiontestservice.scatesttool.InvalidSymbolFault;
import stockexceptiontestservice.scatesttool.StockOffer;

public class IntraCompositeTestCase extends SCATestCase {
    private StockTraderSDO stockTrader;

    private CompositeContext context;

    public void testTrading() {
        try {
            StockOffer sp = stockTrader.testTrading();
            assertNotNull(sp);
            assertEquals(99.00F, sp.getPrice());
            assertEquals("IBM", sp.getSymbol());
        } catch (Exception e) {

            e.printStackTrace();
            fail(e + "");

        }

    }

    public void testInvalidSymbolSDOException() {
        try {
            stockTrader.testInvalidSymbolSDOException();
            fail("Expected InvalidSymbolSDOException");
        } catch (InvalidSymbolSDOException e) {
            InvalidSymbolFault isf = e.getFaultInfo();

            assertNotNull(isf);
            StockOffer sp = isf.getOffer();
            assertEquals(11.00F, sp.getPrice());
            assertEquals("IBM0", sp.getSymbol());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Expected InvalidSymbolSDOException" + e);

        }
    }

    public void _testNotDeclaredAtSourceException() {

        Object ret = stockTrader.testNotDeclaredAtSourceTest();

        assertNotNull(ret);

        assertEquals(TransformationException.class, ret.getClass());

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = CurrentCompositeContext.getContext();
        assertNotNull(context);
        stockTrader = context.locateService(StockTraderSDO.class, "stockTraderSDOComponent");

        assertNotNull(context);
    }
}
