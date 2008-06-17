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
package org.apache.tuscany.sca.implementation.java.context;

import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.scope.TargetInvokerCreationException;

/**
 * @version $Rev$ $Date$
 */
public class TargetMethodNotFoundException extends TargetInvokerCreationException {
    /**
     * 
     */
    private static final long serialVersionUID = -8565552977647191863L;
    private Operation operation;

    /**
     * @param operation
     */
    public TargetMethodNotFoundException(Operation operation) {
        super("Target method not found for operation");
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }
}