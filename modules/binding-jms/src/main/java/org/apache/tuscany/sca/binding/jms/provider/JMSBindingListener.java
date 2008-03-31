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
package org.apache.tuscany.sca.binding.jms.provider;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.NamingException;

import org.apache.tuscany.sca.binding.jms.impl.JMSBinding;
import org.apache.tuscany.sca.binding.jms.impl.JMSBindingConstants;
import org.apache.tuscany.sca.binding.jms.impl.JMSBindingException;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;

public class JMSBindingListener implements MessageListener {

    private static final String ON_MESSAGE_METHOD_NAME = "onMessage";
    private JMSBinding jmsBinding;
    private JMSResourceFactory jmsResourceFactory;
    private RuntimeComponentService service;
    private JMSMessageProcessor requestMessageProcessor;
    private JMSMessageProcessor responseMessageProcessor;
    private String correlationScheme;

    public JMSBindingListener(JMSBinding jmsBinding,
                              JMSResourceFactory jmsResourceFactory,
                              RuntimeComponentService service) throws NamingException {
        this.jmsBinding = jmsBinding;
        this.jmsResourceFactory = jmsResourceFactory;
        this.service = service;
        requestMessageProcessor = jmsBinding.getRequestMessageProcessor();
        responseMessageProcessor = jmsBinding.getResponseMessageProcessor();
        correlationScheme = jmsBinding.getCorrelationScheme();
    }

    public void onMessage(Message requestJMSMsg) {
        try {
            Object responsePayload = invokeService(requestJMSMsg);
            sendReply(requestJMSMsg, responsePayload, false);
        } catch (Throwable e) {
            sendReply(requestJMSMsg, e, true);
        }
    }

    /**
     * Turn the JMS message back into a Tuscany message and invoke the target component
     * 
     * @param requestJMSMsg
     * @return
     * @throws JMSException
     * @throws InvocationTargetException
     */
    protected Object invokeService(Message requestJMSMsg) throws JMSException, InvocationTargetException {

        String operationName = requestMessageProcessor.getOperationName(requestJMSMsg);
        Object requestPayload = requestMessageProcessor.extractPayloadFromJMSMessage(requestJMSMsg);

        List<Operation> opList = service.getInterfaceContract().getInterface().getOperations();

        Operation operation = null;

        if (opList.size() == 1) {
            // SCA JMS Binding Specification - Rule 1.5.1 line 203
            operation = opList.get(0);
        } else if (operationName != null) {
            // SCA JMS Binding Specification - Rule 1.5.1 line 205
            for (Operation op : opList) {
                if (op.getName().equals(operationName)) {
                    operation = op;
                    break;
                }
            }
        } else {
            // SCA JMS Binding Specification - Rule 1.5.1 line 207
            for (Operation op : opList) {
                if (op.getName().equals(ON_MESSAGE_METHOD_NAME)) {
                    operation = op;
                    break;
                }
            }
        }

        if (operation != null) {
            return service.getRuntimeWire(jmsBinding).invoke(operation, (Object[])requestPayload);
        } else {
            throw new JMSBindingException("Can't find operation " + (operationName != null ? operationName
                : ON_MESSAGE_METHOD_NAME));
        }

    }

    protected void sendReply(Message requestJMSMsg, Object responsePayload, boolean isFault) {
        try {

            if (requestJMSMsg.getJMSReplyTo() == null) {
                // assume no reply is expected
                return;
            }

            Session session = jmsResourceFactory.createSession();
            Message replyJMSMsg;
            if (isFault) {
                replyJMSMsg = responseMessageProcessor.createFaultMessage(session, (Throwable)responsePayload);
            } else {
                replyJMSMsg = responseMessageProcessor.insertPayloadIntoJMSMessage(session, responsePayload);
            }

            replyJMSMsg.setJMSDeliveryMode(requestJMSMsg.getJMSDeliveryMode());
            replyJMSMsg.setJMSPriority(requestJMSMsg.getJMSPriority());

            if (correlationScheme == null || JMSBindingConstants.CORRELATE_MSG_ID.equalsIgnoreCase(correlationScheme)) {
                replyJMSMsg.setJMSCorrelationID(requestJMSMsg.getJMSMessageID());
            } else if (JMSBindingConstants.CORRELATE_CORRELATION_ID.equalsIgnoreCase(correlationScheme)) {
                replyJMSMsg.setJMSCorrelationID(requestJMSMsg.getJMSCorrelationID());
            }

            Destination destination = requestJMSMsg.getJMSReplyTo();
            MessageProducer producer = session.createProducer(destination);

            producer.send(replyJMSMsg);

            producer.close();
            session.close();

        } catch (JMSException e) {
            throw new JMSBindingException(e);
        } catch (NamingException e) {
            throw new JMSBindingException(e);
        }
    }

}