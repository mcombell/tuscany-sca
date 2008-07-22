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

package org.apache.tuscany.sca.core.databinding.wire;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.databinding.DataBinding;
import org.apache.tuscany.sca.databinding.Mediator;
import org.apache.tuscany.sca.interfacedef.DataType;
import org.apache.tuscany.sca.interfacedef.FaultExceptionMapper;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.interfacedef.impl.DataTypeImpl;
import org.apache.tuscany.sca.interfacedef.util.FaultException;
import org.apache.tuscany.sca.interfacedef.util.XMLType;
import org.apache.tuscany.sca.invocation.DataExchangeSemantics;
import org.apache.tuscany.sca.invocation.Interceptor;
import org.apache.tuscany.sca.invocation.Invoker;
import org.apache.tuscany.sca.invocation.Message;
import org.apache.tuscany.sca.runtime.RuntimeWire;
import org.osoa.sca.ServiceRuntimeException;

/**
 * An interceptor to transform data across databindings on the wire
 * 
 * @version $Rev$ $Date$
 */
public class DataTransformationInterceptor implements Interceptor, DataExchangeSemantics {
    private Invoker next;

    private Operation sourceOperation;

    private Operation targetOperation;
    private RuntimeWire wire;
    private Mediator mediator;
    private FaultExceptionMapper faultExceptionMapper;
    private FaultTransformHelper faultTransformHelper;

    public DataTransformationInterceptor(RuntimeWire wire,
                                         Operation sourceOperation,
                                         Operation targetOperation,
                                         Mediator mediator,
                                         FaultExceptionMapper faultExceptionMapper) {
        super();
        this.sourceOperation = sourceOperation;
        this.targetOperation = targetOperation;
        this.mediator = mediator;
        this.wire = wire;
        this.faultExceptionMapper = faultExceptionMapper;
        this.faultTransformHelper = new FaultTransformHelper(mediator);
    }

    public Invoker getNext() {
        return next;
    }

    public Message invoke(Message msg) {
        Object input = transform(msg.getBody(), sourceOperation.getInputType(), targetOperation.getInputType(), false);
        msg.setBody(input);
        Message resultMsg = next.invoke(msg);
        Object result = resultMsg.getBody();
        if (sourceOperation.isNonBlocking()) {
            // Not to reset the message body
            return resultMsg;
        }

        // FIXME: Should we fix the Operation model so that getOutputType
        // returns DataType<DataType<T>>?
        DataType<DataType> targetType =
            new DataTypeImpl<DataType>(DataBinding.IDL_OUTPUT, Object.class, targetOperation.getOutputType());

        DataType<DataType> sourceType =
            new DataTypeImpl<DataType>(DataBinding.IDL_OUTPUT, Object.class, sourceOperation.getOutputType());

        if (resultMsg.isFault()) {
            Object transformedFault = null;
            if ((result instanceof Exception) && !(result instanceof RuntimeException)) {
                transformedFault = faultTransformHelper.transformFault(result, sourceOperation, targetOperation, wire);
            } 
            // Otherwise, we leave it to another layer to actually throw the RuntimeException which constitutes
            // the message body.  We don't throw it here.
            if (transformedFault != result) {
                resultMsg.setFaultBody(transformedFault);
            }
        } else {
            assert !(result instanceof Throwable) : "Expected messages that are not throwable " + result;
            Object newResult = transform(result, targetType, sourceType, true);
            resultMsg.setBody(newResult);
        }        

        return resultMsg;
    }

    private Object transform(Object source, DataType sourceType, DataType targetType, boolean isResponse) {
        if (sourceType == targetType || (sourceType != null && sourceType.equals(targetType))) {
            return source;
        }
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("source.operation", isResponse ? targetOperation : sourceOperation);
        metadata.put("target.operation", isResponse ? sourceOperation : targetOperation);
        metadata.put("wire", wire);
        return mediator.mediate(source, sourceType, targetType, metadata);
    }

    public void setNext(Invoker next) {
        this.next = next;
    }

    public boolean allowsPassByReference() {
        return true;
    }

}
