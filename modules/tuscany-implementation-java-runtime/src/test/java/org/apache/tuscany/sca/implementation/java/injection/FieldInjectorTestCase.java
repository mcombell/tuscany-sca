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
package org.apache.tuscany.sca.implementation.java.injection;

import java.lang.reflect.Field;

import junit.framework.TestCase;

/**
 * @version $Rev: 567542 $ $Date: 2007-08-19 22:13:29 -0700 (Sun, 19 Aug 2007) $
 */
public class FieldInjectorTestCase extends TestCase {

    protected Field protectedField;

    public void testIllegalAccess() throws Exception {
        FieldInjector<Foo> injector = new FieldInjector<Foo>(protectedField, new SingletonObjectFactory<String>("foo"));
        Foo foo = new Foo();
        injector.inject(foo);
        assertEquals("foo", foo.hidden);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        protectedField = Foo.class.getDeclaredField("hidden");
    }

    private class Foo {
        private String hidden;
    }
}