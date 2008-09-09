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
package org.apache.tuscany.sca.core.invocation;

import org.apache.tuscany.sca.runtime.RuntimeWire;
import org.apache.tuscany.sca.runtime.RuntimeWireProcessor;
import org.apache.tuscany.sca.runtime.RuntimeWireProcessorExtensionPoint;

/**
 * The default implementation of an extensible <code>WireProcessor</code>
 *
 * @version $Rev: 538423 $ $Date: 2007-05-15 21:11:06 -0700 (Tue, 15 May 2007) $
 */
public class ExtensibleWireProcessor implements RuntimeWireProcessor {
    
    private RuntimeWireProcessorExtensionPoint processors;

    public ExtensibleWireProcessor(RuntimeWireProcessorExtensionPoint processors) {
        this.processors = processors;
    }
    
    public void process(RuntimeWire wire) {
        for (RuntimeWireProcessor processor : processors.getWireProcessors()) {
            processor.process(wire);
        }
    }

}