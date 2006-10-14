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
package org.apache.tuscany.binding.axis2;

import java.lang.reflect.InvocationTargetException;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.receivers.AbstractInMessageReceiver;
import org.apache.tuscany.spi.model.Operation;
import org.apache.tuscany.spi.wire.InvocationRuntimeException;

public class Axis2ServiceInMessageReceiver extends AbstractInMessageReceiver {

    protected Operation<?> operation;

    private Axis2Service axis2Service;

    public Axis2ServiceInMessageReceiver(Axis2Service service, Operation<?> operation) {
        this.axis2Service = service;
        this.operation = operation;
    }

    public Axis2ServiceInMessageReceiver() {

    }

    @Override
    public void invokeBusinessLogic(MessageContext inMC) throws AxisFault {
        try {
            OMElement requestOM = inMC.getEnvelope().getBody().getFirstElement();
            Object[] args = new Object[] {requestOM};

            axis2Service.invokeTarget(operation, args);

        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof Exception) {
                throw AxisFault.makeFault((Exception)t);
            }
            throw new InvocationRuntimeException(e);
        } catch (Throwable t) {
            if (t instanceof Exception) {
                throw AxisFault.makeFault((Exception)t);
            }
            throw new Axis2BindingRunTimeException(t);
        }

    }
}
