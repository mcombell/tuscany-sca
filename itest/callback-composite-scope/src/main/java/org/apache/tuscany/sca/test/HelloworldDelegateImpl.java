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

package org.apache.tuscany.sca.test;

import org.osoa.sca.annotations.ComponentName;
import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.osoa.sca.annotations.Service;

/**
 * A HelloworldDelegate implementation. The service method invocation is delegated to a Helloworld service
 * reference injected into this component.
 *   
 * @version $Rev$ $Date$
 */
@Scope("COMPOSITE")
@Service(interfaces = {HelloworldDelegate.class, HelloworldCallback.class})
public class HelloworldDelegateImpl implements HelloworldDelegate, HelloworldCallback {

    @ComponentName
    protected String componentName;

    @Reference
    public Helloworld helloworld;
    
    @Property
    public String salutation;
    
    public String sayHello(String name) {
        System.out.println(componentName+": HelloworldDelegateImpl("+this+").sayHello: " + name);
        return helloworld.sayHello(name);
    }
    
    public String whoIs(String name) {
        System.out.println(componentName+": HelloworldDelegateImpl("+this+").whoIs: " + name);
        if(!"world".equalsIgnoreCase(name)) {
            return salutation;
        } else {
            return "";
        }
    }
}
