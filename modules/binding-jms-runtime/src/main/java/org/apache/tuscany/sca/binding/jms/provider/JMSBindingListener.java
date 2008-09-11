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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.NamingException;

import org.apache.tuscany.sca.assembly.Binding;
import org.apache.tuscany.sca.binding.jms.impl.JMSBinding;
import org.apache.tuscany.sca.binding.jms.impl.JMSBindingConstants;
import org.apache.tuscany.sca.binding.jms.impl.JMSBindingException;
import org.apache.tuscany.sca.core.assembly.EndpointReferenceImpl;
import org.apache.tuscany.sca.core.invocation.MessageImpl;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.runtime.EndpointReference;
import org.apache.tuscany.sca.runtime.ReferenceParameters;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;

/**
 * Listener for the JMSBinding.
 * 
 * @version $Rev$ $Date$
 */
public class JMSBindingListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(JMSBindingListener.class.getName());

    private static final String ON_MESSAGE_METHOD_NAME = "onMessage";
    private JMSBinding jmsBinding;
    private Binding targetBinding;
    private JMSResourceFactory jmsResourceFactory;
    private RuntimeComponentService service;
    private JMSMessageProcessor requestMessageProcessor;
    private JMSMessageProcessor responseMessageProcessor;
    private String correlationScheme;
    private List<Operation> serviceOperations;

    public JMSBindingListener(JMSBinding jmsBinding, JMSResourceFactory jmsResourceFactory, RuntimeComponentService service, Binding targetBinding) throws NamingException {
        this.jmsBinding = jmsBinding;
        this.jmsResourceFactory = jmsResourceFactory;
        this.service = service;
        this.targetBinding = targetBinding;
        requestMessageProcessor = JMSMessageProcessorUtil.getRequestMessageProcessor(jmsBinding);
        responseMessageProcessor = JMSMessageProcessorUtil.getResponseMessageProcessor(jmsBinding);
        correlationScheme = jmsBinding.getCorrelationScheme();
        serviceOperations = service.getInterfaceContract().getInterface().getOperations();

    }

    public void onMessage(Message requestJMSMsg) {
        logger.log(Level.FINE, "JMS service '" + service.getName() + "' received message " + requestJMSMsg);
        try {
            Object responsePayload = invokeService(requestJMSMsg);
            sendReply(requestJMSMsg, responsePayload, false);
        } catch (Throwable e) {
            logger.log(Level.SEVERE, "Exception invoking service '" + service.getName(), e);
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
        Operation operation = getTargetOperation(operationName);

        MessageImpl tuscanyMsg = new MessageImpl();
        tuscanyMsg.setOperation(operation);
        if ("onMessage".equals(operation.getName())) {
            tuscanyMsg.setBody(new Object[]{requestJMSMsg});
        } else {
            Object requestPayload = requestMessageProcessor.extractPayloadFromJMSMessage(requestJMSMsg);
            tuscanyMsg.setBody(requestPayload);
        }

        setHeaderProperties(requestJMSMsg, tuscanyMsg, operation);

        return service.getRuntimeWire(targetBinding).invoke(operation, tuscanyMsg);
    }

    protected Operation getTargetOperation(String operationName) {
        Operation operation = null;

        if (serviceOperations.size() == 1) {

            // SCA JMS Binding Specification - Rule 1.5.1 line 203
            operation = serviceOperations.get(0);

        } else if (operationName != null) {

            // SCA JMS Binding Specification - Rule 1.5.1 line 205
            for (Operation op : serviceOperations) {
                if (op.getName().equals(operationName)) {
                    operation = op;
                    break;
                }
            }

        } else {

            // SCA JMS Binding Specification - Rule 1.5.1 line 207
            for (Operation op : serviceOperations) {
                if (op.getName().equals(ON_MESSAGE_METHOD_NAME)) {
                    operation = op;
                    break;
                }
            }
        }

        if (operation == null) {
            throw new JMSBindingException("Can't find operation " + (operationName != null ? operationName : ON_MESSAGE_METHOD_NAME));
        }

        return operation;
    }

    protected void setHeaderProperties(Message requestJMSMsg, MessageImpl tuscanyMsg, Operation operation) throws JMSException {

        EndpointReference from = new EndpointReferenceImpl(null);
        tuscanyMsg.setFrom(from);
        from.setCallbackEndpoint(new EndpointReferenceImpl("/")); // TODO: whats this for?
        ReferenceParameters parameters = from.getReferenceParameters();

        String conversationID = requestJMSMsg.getStringProperty(JMSBindingConstants.CONVERSATION_ID_PROPERTY);
        if (conversationID != null) {
            parameters.setConversationID(conversationID);
        }

        if (service.getInterfaceContract().getCallbackInterface() != null) {

            String callbackdestName = requestJMSMsg.getStringProperty(JMSBindingConstants.CALLBACK_Q_PROPERTY);
            if (callbackdestName == null && operation.isNonBlocking()) {
                // if the request has a replyTo but this service operation is oneway but the service uses callbacks
                // then use the replyTo as the callback destination
                Destination replyTo = requestJMSMsg.getJMSReplyTo();
                if (replyTo != null) {
                    callbackdestName = (replyTo instanceof Queue) ? ((Queue)replyTo).getQueueName() : ((Topic)replyTo).getTopicName();
                }
            }

            if (callbackdestName != null) {
                // append "jms:" to make it an absolute uri so the invoker can determine it came in on the request
                // as otherwise the invoker should use the uri from the service callback binding
                parameters.setCallbackReference(new EndpointReferenceImpl("jms:" + callbackdestName));
            }

            String callbackID = requestJMSMsg.getStringProperty(JMSBindingConstants.CALLBACK_ID_PROPERTY);
            if (callbackID != null) {
                parameters.setCallbackID(callbackID);
            }
        }
    }

    protected void sendReply(Message requestJMSMsg, Object responsePayload, boolean isFault) {
        try {

            if (requestJMSMsg.getJMSReplyTo() == null) {
                // assume no reply is expected
                if (responsePayload != null) {
                    logger.log(Level.FINE, "JMS service '" + service.getName() + "' dropped response as request has no replyTo");
                }
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
