/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tuscany.sca.implementation.bpel.ode;

import javax.wsdl.PortType;
import javax.xml.namespace.QName;

import org.apache.ode.bpel.iapi.BindingContext;
import org.apache.ode.bpel.iapi.Endpoint;
import org.apache.ode.bpel.iapi.EndpointReference;
import org.apache.ode.bpel.iapi.PartnerRoleChannel;
import org.w3c.dom.Document;

/**
 * Binding Context information
 * 
 * @version $Rev: 573789 $ $Date: 2007-09-07 23:59:49 -0700 (Fri, 07 Sep 2007) $
 */
public class ODEBindingContext implements BindingContext {

    private EmbeddedODEServer _server;

    public ODEBindingContext(EmbeddedODEServer _server) {
        this._server = _server;
    }

    public EndpointReference activateMyRoleEndpoint(QName pid, Endpoint endpoint) {
        return new TuscanyEPR();
    }

    public void deactivateMyRoleEndpoint(Endpoint endpoint) {
        // TODO
    }

    public PartnerRoleChannel createPartnerRoleChannel(QName qName, PortType portType, Endpoint endpoint) {
        // TODO
        return null;
    }

    // TODO This should hold something that makes sense for Tuscany so that the process has
    // an address that makes sense from the outside world perspective 
    private class TuscanyEPR implements EndpointReference {
        public Document toXML() {
            return null;
        }
    }
}
