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
package org.apache.tuscany.sca.vtest.assembly.composite;

import org.osoa.sca.annotations.Scope;

/**
 * This client program shows how to create an SCA runtime, start it,
 * locate the HelloWorld service and invoke it.
 */
@Scope("COMPOSITE")
public class DServiceComponent implements DService {
   
    DService helloWorldService;

    public String getGreetings(String name) {
        return helloWorldService.getGreetings(name);
    }
    
    public String getSomeProperty() {
        return helloWorldService.getSomeProperty();
    }

    public DService getHelloWorldService() {
        return helloWorldService;
    }

    public void setHelloWorldService(DService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}