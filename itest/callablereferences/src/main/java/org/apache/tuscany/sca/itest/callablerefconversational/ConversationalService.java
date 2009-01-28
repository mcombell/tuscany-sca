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
package org.apache.tuscany.sca.itest.callablerefconversational;

import org.oasisopen.sca.ServiceReference;
import org.oasisopen.sca.annotation.Conversational;

/**
 * Simple conversational Service that is used to testing the method
 * ComponentContext.createSelfReference()
 * 
 * @version $Date$ $Revision$
 */
@Conversational
public interface ConversationalService {
    /**
     * Default value for the user data
     */
    String DEFAULT_USER_DATA = "NOT SET";

    /**
     * Retrieves the conversation ID for this Service
     * 
     * @return The conversation ID for this Service
     */
    Object getConversationID();

    /**
     * Creates a self reference to this Service
     * 
     * @return A self reference to this Service
     */
    ServiceReference<ConversationalService> createSelfRef();

    /**
     * Sets some user data on the instance
     * 
     * @param a_Data Some data
     * 
     * @See {@link #getUserData()}
     */
    void setUserData(String a_Data);

    /**
     * Gets some user data on the instance
     * 
     * @return Some data
     * 
     * @See {@link #setUserData(String)}
     */
    String getUserData();
}
