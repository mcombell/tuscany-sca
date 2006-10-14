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

package org.apache.tuscany.databinding.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.tuscany.spi.model.DataType;

import com.example.ipo.jaxb.PurchaseOrderType;
import com.example.ipo.jaxb.USAddress;
import com.example.ipo.jaxb.USState;

/**
 * 
 */
public class JAXBDataBindingTestCase extends TestCase {
    private JAXBDataBinding binding;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        binding = new JAXBDataBinding();
    }

    /**
     * Test method for {@link org.apache.tuscany.databinding.jaxb.JAXBDataBinding#introspect(java.lang.Class)}.
     */
    public final void testIntrospect() {
        DataType<?> dataType = binding.introspect(JAXBElement.class);
        Assert.assertTrue(dataType.getDataBinding().equals(binding.getName()));
        Assert.assertTrue(dataType.getPhysical() == JAXBElement.class && dataType.getLogical() == null);
        dataType = binding.introspect(MockJAXBElement.class);
        Assert.assertEquals(PurchaseOrderType.class, dataType.getPhysical());
        Assert.assertEquals(new QName("http://www.example.com/IPO", "PurchaseOrderType"), dataType.getLogical());
        dataType = binding.introspect(USAddress.class);
        Assert.assertEquals(USAddress.class, dataType.getPhysical());
        Assert.assertEquals(new QName("http://www.example.com/IPO", "USAddress"), dataType.getLogical());
        dataType = binding.introspect(USState.class);
        Assert.assertTrue(dataType.getDataBinding().equals(binding.getName()));
        Assert.assertEquals(USState.class, dataType.getPhysical());
        Assert.assertEquals(new QName("http://www.example.com/IPO", "USState"), dataType.getLogical());

    }

    private static class MockJAXBElement extends JAXBElement<PurchaseOrderType> {

        private static final long serialVersionUID = -2767569071002707973L;

        /**
         * @param elementName
         * @param type
         * @param value
         */
        public MockJAXBElement(QName elementName, Class<PurchaseOrderType> type, PurchaseOrderType value) {
            super(elementName, type, value);
        }

    }

}
