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

/*
 * Generated by XDoclet - Do not edit!
 */

// copied from the Geronimo Bank sample: http://cwiki.apache.org/GMOxDOC11/ejb-sample-application.html#EJBsampleapplication-overview

package org.apache.geronimo.samples.bank.ejb;

/**
 * Home interface for BankManagerFacadeBean.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @version ${version}
 */
public interface BankManagerFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/BankManagerFacadeBean";
   public static final String JNDI_NAME="org.apache.geronimo.samples.bank.ejb.BankManagerFacadeBean";

   public org.apache.geronimo.samples.bank.ejb.BankManagerFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}