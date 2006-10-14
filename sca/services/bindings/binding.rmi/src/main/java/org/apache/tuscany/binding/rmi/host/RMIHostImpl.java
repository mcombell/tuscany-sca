/**
 *
 * Copyright 2006 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.binding.rmi.host;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.tuscany.host.rmi.RMIHost;
import org.apache.tuscany.host.rmi.RMIHostException;
import org.apache.tuscany.host.rmi.RMIHostRuntimeException;
import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Scope;

/**
 * This class provides an implementation for the RMI Host SPIs
 */
@Scope("MODULE")
public class RMIHostImpl implements RMIHost {

    // map of RMI registries started and running
    private Map<String, Registry> rmiRegistries;

    public RMIHostImpl() {
        rmiRegistries = new ConcurrentHashMap<String, Registry>();
        /*
         * if (System.getSecurityManager() == null) { System.setSecurityManager(new RMISecurityManager()); }
         */
    }

    @Init(eager = true)
    public void init() {
    }

    public void registerService(String serviceName, int port, Remote serviceObject) throws RMIHostException,
                                                                                   RMIHostRuntimeException {
        Registry registry;
        try {
            registry = rmiRegistries.get(Integer.toString(port));
            if (registry == null) {
                registry = LocateRegistry.createRegistry(port);
                rmiRegistries.put(Integer.toString(port),
                                  registry);
            }
            registry.bind(serviceName,
                          serviceObject);
        } catch (AlreadyBoundException e) {
            throw new RMIHostException(e.getMessage());
        } catch (RemoteException e) {
            RMIHostRuntimeException rmiExec = new RMIHostRuntimeException(e.getMessage());
            rmiExec.setStackTrace(e.getStackTrace());
            throw rmiExec;
        }

    }

    public void registerService(String serviceName, Remote serviceObject) throws RMIHostException,
                                                                         RMIHostRuntimeException {
        registerService(serviceName,
                        RMI_DEFAULT_PORT,
                        serviceObject);
    }

    public void unregisterService(String serviceName, int port) throws RMIHostException,
                                                               RMIHostRuntimeException {
        Registry registry;

        try {
            registry = rmiRegistries.get(Integer.toString(port));
            if (registry == null) {
                registry = LocateRegistry.createRegistry(port);
                rmiRegistries.put(Integer.toString(port),
                                  registry);
            }
            registry.unbind(serviceName);
        } catch (RemoteException e) {
            RMIHostRuntimeException rmiExec = new RMIHostRuntimeException(e.getMessage());
            rmiExec.setStackTrace(e.getStackTrace());
            throw rmiExec;
        } catch (NotBoundException e) {
            throw new RMIHostException(e.getMessage());
        }
    }

    public void unregisterService(String serviceName) throws RMIHostException,
                                                     RMIHostRuntimeException {
        unregisterService(serviceName,
                          RMI_DEFAULT_PORT);

    }

    public Remote findService(String host, String port, String svcName) throws RMIHostException,
                                                                       RMIHostRuntimeException {
        Registry registry;
        Remote remoteService = null;
        host = (host == null || host.length() <= 0) ? "localhost" : host;
        int portNumber = (port == null || port.length() <= 0) ? RMI_DEFAULT_PORT : Integer
                .decode(port);

        try {
            registry = LocateRegistry.getRegistry(host,
                                                  portNumber);

            if (registry != null) {
                remoteService = registry.lookup(svcName);
            }
        } catch (RemoteException e) {
            RMIHostRuntimeException rmiExec = new RMIHostRuntimeException(e.getMessage());
            rmiExec.setStackTrace(e.getStackTrace());
            throw rmiExec;
        } catch (NotBoundException e) {
            throw new RMIHostException(e.getMessage());
        }
        return remoteService;
    }

}
