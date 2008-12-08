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
package org.apache.tuscany.sca.host.openejb;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.openejb.OpenEJB;
import org.apache.openejb.OpenEJBException;
import org.apache.openejb.assembler.classic.Assembler;
import org.apache.openejb.assembler.classic.StatelessSessionContainerInfo;
import org.apache.openejb.config.ConfigurationFactory;
import org.apache.openejb.core.ServerFederation;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.loader.SystemInstance;
import org.apache.openejb.server.ServiceDaemon;
import org.apache.openejb.server.ServiceException;
import org.apache.openejb.server.ejbd.EjbServer;
import org.apache.tuscany.sca.host.ejb.EJBHost;
import org.apache.tuscany.sca.host.ejb.EJBRegistrationException;
import org.apache.tuscany.sca.host.ejb.EJBSessionBean;

/**
 * OpenEJB-based EJB host implementation. 
 *
 * @version $Rev: $ $Date: $
 */
public class OpenEJBServer implements EJBHost {

    private boolean started;
    private EjbServer ejbServer;
    private ServiceDaemon serviceDaemon;
    private ConfigurationFactory config;
    private Assembler assembler;
    
    public void addSessionBean(String ejbName, EJBSessionBean sessionBean) throws EJBRegistrationException {
        if (!started) {
            start();
        }
        
        try {
            StatelessBean bean = new StatelessBean(ejbName, sessionBean.getImplementationClass());
            bean.addBusinessRemote(sessionBean.getRemoteInterface().getName());
            bean.addPostConstruct("init");
            bean.addPreDestroy("destroy");

            EjbJar ejbJar = new EjbJar();
            ejbJar.addEnterpriseBean(bean);

            assembler.createApplication(config.configureApplication(ejbJar));
            
        } catch (NamingException e) {
            throw new EJBRegistrationException(e);
        } catch (IOException e) {
            throw new EJBRegistrationException(e);
        } catch (OpenEJBException e) {
            throw new EJBRegistrationException(e);
        }
    }

    public EJBSessionBean getSessionBean(String ejbName) throws EJBRegistrationException {
        // TODO Auto-generated method stub
        return null;
    }

    public EJBSessionBean removeSessionBean(String ejbName) throws EJBRegistrationException {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Start the OpenEJB server.
     * 
     * @throws EJBRegistrationException
     */
    private void start() throws EJBRegistrationException {
        try {
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "ejbd://localhost:2888");
            SystemInstance.init(properties);
            
            ejbServer = new EjbServer();
            SystemInstance.get().setComponent(EjbServer.class, ejbServer);
            OpenEJB.init(properties, new ServerFederation());
            ejbServer.init(properties);
            
            serviceDaemon = new ServiceDaemon(ejbServer, 2888, "localhost");
            serviceDaemon.start();
            
            config = new ConfigurationFactory();
            assembler = (Assembler)SystemInstance.get().getComponent(org.apache.openejb.spi.Assembler.class);

            // containers
            StatelessSessionContainerInfo statelessContainerInfo = config.configureService(StatelessSessionContainerInfo.class);
            statelessContainerInfo.properties.setProperty("TimeOut", "10");
            statelessContainerInfo.properties.setProperty("PoolSize", "0");
            statelessContainerInfo.properties.setProperty("StrictPooling", "false");
            assembler.createContainer(statelessContainerInfo);
            
        } catch (OpenEJBException e) {
            throw new EJBRegistrationException(e);
        } catch (Exception e) {
            throw new EJBRegistrationException(e);
        }
        
        started = true;
    }
    
    /**
     * Stop the OpenEJB server.
     */
    void stop() {
        if (started) {
            try {
                serviceDaemon.stop();
            } catch (ServiceException e) {
                throw new EJBRegistrationException(e);
            }
        }
        SystemInstance.get().removeComponent(EjbServer.class);
        OpenEJB.destroy();
    }

}
