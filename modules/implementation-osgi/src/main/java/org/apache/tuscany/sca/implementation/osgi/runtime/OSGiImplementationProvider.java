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
package org.apache.tuscany.sca.implementation.osgi.runtime;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.tuscany.sca.assembly.Component;
import org.apache.tuscany.sca.assembly.ComponentReference;
import org.apache.tuscany.sca.assembly.ComponentService;
import org.apache.tuscany.sca.assembly.Extensible;
import org.apache.tuscany.sca.assembly.Multiplicity;
import org.apache.tuscany.sca.assembly.Reference;
import org.apache.tuscany.sca.assembly.Service;
import org.apache.tuscany.sca.core.factory.ObjectCreationException;
import org.apache.tuscany.sca.core.invocation.ProxyFactory;
import org.apache.tuscany.sca.core.invocation.ProxyFactoryExtensionPoint;
import org.apache.tuscany.sca.core.scope.Scope;
import org.apache.tuscany.sca.core.scope.ScopeRegistry;
import org.apache.tuscany.sca.databinding.DataBindingExtensionPoint;
import org.apache.tuscany.sca.implementation.osgi.OSGiImplementation;
import org.apache.tuscany.sca.implementation.osgi.OSGiProperty;
import org.apache.tuscany.sca.interfacedef.Interface;
import org.apache.tuscany.sca.interfacedef.InterfaceContractMapper;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.interfacedef.java.JavaInterface;
import org.apache.tuscany.sca.invocation.Invoker;
import org.apache.tuscany.sca.invocation.MessageFactory;
import org.apache.tuscany.sca.provider.ImplementationProvider;
import org.apache.tuscany.sca.runtime.EndpointReference;
import org.apache.tuscany.sca.runtime.RuntimeComponent;
import org.apache.tuscany.sca.runtime.RuntimeComponentReference;
import org.apache.tuscany.sca.runtime.RuntimeComponentService;
import org.apache.tuscany.sca.runtime.RuntimeWire;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.PackageAdmin;

/**
 * The runtime instantiation of OSGi component implementations
 *
 * @version $Rev$ $Date$
 */
public class OSGiImplementationProvider implements ImplementationProvider, FrameworkListener, BundleListener {

    private static final String COMPONENT_SERVICE_NAME = "component.service.name";

    // Maximum milliseconds to wait for a method to complete
    private static final long METHOD_TIMEOUT_MILLIS = 60000;
    // Maximum milliseconds to wait for services to be registered into OSGi service registry
    private static final long SERVICE_TIMEOUT_MILLIS = 300000;

    private OSGiImplementation implementation;
    private Hashtable<RuntimeWire, Reference> referenceWires = new Hashtable<RuntimeWire, Reference>();
    private Hashtable<RuntimeWire, ComponentReference> componentReferenceWires =
        new Hashtable<RuntimeWire, ComponentReference>();
    private HashSet<RuntimeWire> resolvedWires = new HashSet<RuntimeWire>();
    private boolean wiresResolved;

    private AtomicInteger startBundleEntryCount = new AtomicInteger();
    private AtomicInteger processAnnotationsEntryCount = new AtomicInteger();

    private Hashtable<String, Object> componentProperties = new Hashtable<String, Object>();
    private RuntimeComponent runtimeComponent;

    Bundle osgiBundle;
    private ArrayList<Bundle> dependentBundles = new ArrayList<Bundle>();
    private OSGiServiceListener osgiServiceListener;
    private PackageAdmin packageAdmin;

    private ScopeRegistry scopeRegistry;
    private DataBindingExtensionPoint dataBindingRegistry;
    private ProxyFactoryExtensionPoint proxyFactoryExtensionPoint;

    private boolean packagesRefreshed;

    private MessageFactory messageFactory;
    private InterfaceContractMapper mapper;

    public OSGiImplementationProvider(RuntimeComponent definition,
                                      OSGiImplementation impl,
                                      DataBindingExtensionPoint dataBindingRegistry,
                                      ScopeRegistry scopeRegistry,
                                      MessageFactory messageFactory,
                                      ProxyFactoryExtensionPoint proxyFactoryExtensionPoint,
                                      InterfaceContractMapper mapper) throws BundleException {

        this.implementation = impl;
        this.runtimeComponent = definition;
        this.dataBindingRegistry = dataBindingRegistry;
        this.scopeRegistry = scopeRegistry;
        this.messageFactory = messageFactory;
        this.proxyFactoryExtensionPoint = proxyFactoryExtensionPoint;
        this.mapper = mapper;

        BundleContext bundleContext = OSGiImplementationActivator.getBundleContext();
        osgiBundle = (Bundle)implementation.getBundle();
        bundleContext.addBundleListener(this);
        osgiServiceListener = new OSGiServiceListener(osgiBundle);
        bundleContext.addServiceListener(osgiServiceListener);

        // PackageAdmin is used to resolve bundles 
        org.osgi.framework.ServiceReference packageAdminReference =
            bundleContext.getServiceReference("org.osgi.service.packageadmin.PackageAdmin");
        if (packageAdminReference != null) {
            packageAdmin = (PackageAdmin)bundleContext.getService(packageAdminReference);
            bundleContext.addFrameworkListener(this);
        }

    }

    protected RuntimeComponent getRuntimeComponent() {
        return runtimeComponent;
    }

    protected OSGiImplementation getImplementation() {
        return implementation;
    }

    private String getOSGiFilter(Hashtable<String, Object> props) {

        String filter = "";

        if (props != null && props.size() > 0) {
            int propCount = 0;
            for (String propName : props.keySet()) {
                if (propName.equals("service.pid"))
                    continue;
                filter = filter + "(" + propName + "=" + props.get(propName) + ")";
                propCount++;
            }

            if (propCount > 1)
                filter = "(&" + filter + ")";
        } else
            filter = null;
        return filter;
    }

    /*
     * Return a matching service registered by the specified bundle.
     * If <implementation.osgi /> has the attribute filter defined, return a service
     * reference that matches the filter. Otherwise, return a service which has a component
     * name equal to this component's name. If not found, return a service which no
     * component name set.
     * 
     * Even though services registered by this bundle can be filtered using the
     * service listener, we use this method to filter all service references so that 
     * the service matching functionality of OSGi can be directly used.
     */
    private org.osgi.framework.ServiceReference getOSGiServiceReference(String scaServiceName,
                                                                        String osgiServiceName,
                                                                        String filter) throws InvalidSyntaxException {

        String compServiceName = runtimeComponent.getName() + "/" + scaServiceName;
        if (filter != null && filter.length() > 0) {
            org.osgi.framework.ServiceReference[] references =
                osgiBundle.getBundleContext().getServiceReferences(osgiServiceName, filter);

            org.osgi.framework.ServiceReference reference = null;
            if (references != null) {
                for (org.osgi.framework.ServiceReference ref : references) {
                    if (ref.getBundle() != osgiBundle)
                        continue;
                    Object compName = ref.getProperty(COMPONENT_SERVICE_NAME);
                    if (compName == null && reference == null)
                        reference = ref;
                    if (scaServiceName == null || compServiceName.equals(compName)) {
                        reference = ref;
                        break;
                    }
                }
            }

            return reference;

        }

        filter = scaServiceName == null ? null : "(" + COMPONENT_SERVICE_NAME + "=" + compServiceName + ")";

        org.osgi.framework.ServiceReference[] references =
            osgiBundle.getBundleContext().getServiceReferences(osgiServiceName, filter);

        if (references != null) {
            for (org.osgi.framework.ServiceReference ref : references) {
                if (ref.getBundle() == osgiBundle) {
                    return ref;
                }
            }
        }

        references = osgiBundle.getBundleContext().getServiceReferences(osgiServiceName, null);

        org.osgi.framework.ServiceReference reference = null;

        if (references != null) {
            for (org.osgi.framework.ServiceReference ref : references) {

                if (ref.getBundle() != osgiBundle)
                    continue;
                Object compName = ref.getProperty(COMPONENT_SERVICE_NAME);
                if (compName == null && reference == null)
                    reference = ref;
                if (compServiceName.equals(compName)) {
                    reference = ref;
                    break;
                }
            }
        }

        return reference;
    }

    /**
     * This method is used to avoid full synchronization of methods which should
     * be executed only once. 
     * 
     * entryCount=0: The count is incremented, and this thread executes the method. Returns true.
     * 
     * entryCount=1: Another thread is already executing this method. 
     *               Wait for the thread to complete if doWait is true. Returns false. 
     * 
     * entryCount=2: The method has already been executed. Returns false.
     * 
     * @param doWait     If true, and another method is executing this method
     *                   wait for method execution to complete
     * @param entryCount Atomic integer used to ensure that the method is
     *                   executed only once
     * @return true if this thread has exclusive access to execute this method
     */
    private boolean enterMethod(boolean doWait, AtomicInteger entryCount) {

        if (entryCount.compareAndSet(0, 1)) {
            return true;
        } else {
            if (doWait) {
                synchronized (entryCount) {
                    if (entryCount.get() != 2) {
                        try {
                            entryCount.wait(METHOD_TIMEOUT_MILLIS);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * Called on method exit of methods which were entered after 
     * enterMethod returned true. Increments entryCount, and wakes
     * up threads waiting for the method to complete.
     * 
     * @param entryCount Atomic integer used for synchronization
     */
    private void exitMethod(AtomicInteger entryCount) {
        entryCount.compareAndSet(1, 2);
        synchronized (entryCount) {
            entryCount.notifyAll();
        }
    }

    protected Bundle startBundle(boolean doWait) throws ObjectCreationException {

        try {

            if (enterMethod(doWait, startBundleEntryCount)) {

                configurePropertiesUsingConfigAdmin();

                resolveBundle();

                for (Bundle bundle : dependentBundles) {
                    try {
                        if (bundle.getState() != Bundle.ACTIVE && bundle.getState() != Bundle.STARTING) {
                            bundle.start();
                        }
                    } catch (BundleException e) {
                        if (bundle.getHeaders().get("Fragment-Host") == null)
                            throw e;
                    }
                }

            }

            if (osgiBundle.getState() != Bundle.ACTIVE && osgiBundle.getState() != Bundle.STARTING) {

                int retry = 0;

                while (retry++ < 10) {
                    try {
                        AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                            public Object run() throws BundleException {
                                osgiBundle.start();
                                return null;
                            }
                        });
                        break;
                        // } catch ( BundleException e) {
                    } catch (PrivilegedActionException e) {
                        // It is possible that the thread "Refresh Packages" is in the process of
                        // changing the state of this bundle. 
                        Thread.yield();

                        if (retry == 10)
                            throw e;
                    }
                }
            }

        } catch (Exception e) {
            throw new ObjectCreationException(e);
        } finally {
            exitMethod(startBundleEntryCount);
        }
        return osgiBundle;
    }

    // This method is called by OSGiInstanceWrapper.getInstance to obtain the OSGi service reference
    // corresponding to the specified service. The properties used to filter the service should
    // be chosen based on whether this is a normal service or a callback.
    /**
     * @param component
     * @param service
     * @return
     */
    protected ServiceReference getOSGiServiceReference(Component component, ComponentService service)
        throws ObjectCreationException {

        Hashtable<String, Object> props = getOSGiProperties(service);

        String filter = getOSGiFilter(props);
        Interface serviceInterface = service.getInterfaceContract().getInterface();
        String scaServiceName = service.getName();

        return getOSGiServiceReference(serviceInterface, filter, scaServiceName);

    }

    /**
     * Get all the OSGi properties from the extension list
     * @param extensible
     * @return
     */
    private Hashtable<String, Object> getOSGiProperties(Extensible extensible) {
        Hashtable<String, Object> props = new Hashtable<String, Object>();
        for (Object ext : extensible.getExtensions()) {
            if (ext instanceof OSGiProperty) {
                OSGiProperty p = (OSGiProperty)ext;
                props.put(p.getName(), p.getValue());
            }
        }
        return props;
    }

    protected ServiceReference getOSGiServiceReference(EndpointReference from, Interface callbackInterface)
        throws ObjectCreationException {

        RuntimeWire refWire = null;
        String filter = null;
        for (RuntimeWire wire : referenceWires.keySet()) {
            if (wire.getSource() == from) {
                refWire = wire;
                break;
            }
        }
        if (refWire != null) {
            ComponentReference scaRef = componentReferenceWires.get(refWire);
            Hashtable<String, Object> props = getOSGiProperties(scaRef);
            filter = getOSGiFilter(props);
        }

        return getOSGiServiceReference(callbackInterface, filter, null);
    }

    private ServiceReference getOSGiServiceReference(Interface serviceInterface, String filter, String scaServiceName)
        throws ObjectCreationException {

        try {

            String serviceInterfaceName = null;

            ServiceReference osgiServiceReference = null;

            if (serviceInterface instanceof JavaInterface) {
                serviceInterfaceName = ((JavaInterface)serviceInterface).getJavaClass().getName();

                if ((osgiServiceReference = getOSGiServiceReference(scaServiceName, serviceInterfaceName, filter)) == null) {

                    // The service listener for our bundle will notify us when the service is registered.
                    synchronized (implementation) {

                        // When declarative services are used, the component is started asynchronously
                        // So this thread has to wait for the service to be registered by the component
                        // activate method
                        // For regular bundle activators, bundle.start activates the bundle synchronously
                        // and hence the service would probably have been started by the bundle activator
                        long startTime = System.currentTimeMillis();
                        while ((osgiServiceReference =
                            getOSGiServiceReference(scaServiceName, serviceInterfaceName, filter)) == null) {

                            // Wait for the bundle to register the service
                            implementation.wait(100);
                            if (System.currentTimeMillis() - startTime > SERVICE_TIMEOUT_MILLIS)
                                break;
                        }
                    }

                }
            }

            return osgiServiceReference;

        } catch (Exception e) {
            throw new ObjectCreationException(e);
        }
    }

    /**
     * For OSGi->Java wires, create a proxy corresponding to the Java interfaces
     * and register the proxy with the OSGi registry, so that the source OSGi bundle can 
     * locate the target Java instance from the registry like a regular OSGi service.
     * 
     * For OSGi->OSGi wires, start the target OSGi bundle, so that references of the
     * target are resolved before the source OSGi bundle is started. If the reference
     * has properties specified, create a Proxy and register a service with highest
     * possible ranking. The Proxy should wire to the correct OSGi instance specified
     * in the SCA composite.
     * 
     * The first phase determines whether a proxy should be installed. It also registers
     * a dummy bundle if necessary to resolve the bundle. When phase1 is completed on all
     * wires of the component, the bundle should be resolved. Phase2 registers the proxy service.
     */
    private boolean resolveWireResolveReferences(Bundle bundle,
                                                 Class<?> interfaceClass,
                                                 RuntimeWire wire,
                                                 boolean isOSGiToOSGiWire) throws Exception {

        // FIXME: At the moment injection of values into instances require an instance to be obtained
        // through the instance wrapper, and hence requires a proxy. When we do this processing here,
        // we don't yet know whether the target requires any property or callback injection. So it is
        // safer to create a proxy all the time. 
        boolean createProxy = true;

        ComponentReference scaRef = componentReferenceWires.get(wire);
        Hashtable<String, Object> targetProperties = getOSGiProperties(scaRef);

        if (isOSGiToOSGiWire) {

            OSGiImplementationProvider implProvider =
                (OSGiImplementationProvider)wire.getTarget().getComponent().getImplementationProvider();

            // This is an OSGi->OSGi wire
            isOSGiToOSGiWire = true;

            // If the target component is stateless, use a proxy to create a new service each time 
            if (!implProvider.getScope().equals(Scope.COMPOSITE))
                createProxy = true;

            Interface interfaze = wire.getTarget().getInterfaceContract().getInterface();

            // If the target interface is remotable, create a proxy to support pass-by-value semantics
            // AllowsPassByReference is not detected until the target instance is obtained.
            if (interfaze.isRemotable())
                createProxy = true;

            // If any of the operations in the target interface is non-blocking, create a proxy
            List<Operation> ops = interfaze.getOperations();
            for (Operation op : ops) {
                if (op.isNonBlocking())
                    createProxy = true;
            }

            // If properties are specified for the reference, create a proxy since rewiring may be required
            if (targetProperties.size() > 0) {
                createProxy = true;
            }

            // If properties are specified for the component, create a proxy for configuring
            // the component services.
            if (componentProperties.size() > 0) {
                createProxy = true;
            }

            // Since this is an OSGi->OSGi wire, start the target bundle before starting the
            // source bundle if there is no proxy. For direct wiring without a proxy, this ordering 
            // is irrelevant in terms of class resolution, but the target needs to be started at some 
            // point. But there is no opportunity later on to start the target OSGi bundle without a proxy.    
            // When a Proxy is used, the target bundle needs to be resolved for the source bundle
            // to be resolved so that the interface is visible to the source. In this case the bundle
            // will be started when an instance is needed.                    
            if (!createProxy) {
                implProvider.startBundle(false);
            } else {
                implProvider.resolveBundle();
            }
        } else {
            createProxy = true;
        }

        return createProxy;
    }

    // Register proxy service 
    private void resolveWireRegisterProxyService(final Bundle bundle, final Class interfaceClass, RuntimeWire wire)
        throws Exception {

        ComponentReference scaRef = componentReferenceWires.get(wire);
        Hashtable<String, Object> targetProperties = getOSGiProperties(scaRef);
        targetProperties.put(Constants.SERVICE_RANKING, Integer.MAX_VALUE);

        if (targetProperties.get(COMPONENT_SERVICE_NAME) == null && wire.getTarget().getComponent() != null) {
            String compServiceName =
                wire.getTarget().getComponent().getName() + "/" + wire.getTarget().getContract().getName();
            targetProperties.put(COMPONENT_SERVICE_NAME, compServiceName);
        }

        ProxyFactory proxyService = proxyFactoryExtensionPoint.getInterfaceProxyFactory();
        if (!interfaceClass.isInterface()) {
            proxyService = proxyFactoryExtensionPoint.getClassProxyFactory();
        }

        // Allow privileged access to load classes. Requires getClassLoader permission in security
        // policy.
        final Class<?> proxyInterface = AccessController.doPrivileged(new PrivilegedExceptionAction<Class<?>>() {
            public Class<?> run() throws Exception {
                return bundle.loadClass(interfaceClass.getName());
            }
        });

        final Object proxy = proxyService.createProxy(proxyInterface, wire);
        final Hashtable<String, Object> finalTargetProperties = targetProperties;
        AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
            public Object run() throws Exception {
                osgiBundle.getBundleContext().registerService(proxyInterface.getName(), proxy, finalTargetProperties);
                return null;
            }
        });

    }

    private void refreshPackages() {

        if (packageAdmin != null) {
            synchronized (this) {
                packagesRefreshed = false;
                packageAdmin.refreshPackages(null);

                if (!packagesRefreshed) {
                    try {
                        this.wait(2000);
                    } catch (InterruptedException e) {
                        // ignore
                    }
                }
                packagesRefreshed = false;
            }
        }
    }

    private void resolveBundle() throws ObjectCreationException {

        try {

            if (!wiresResolved) {
                wiresResolved = true;

                if (!setReferencesAndProperties()) {
                    wiresResolved = false;
                    return;
                }

                int refPlusServices = referenceWires.size() + runtimeComponent.getServices().size();
                boolean[] createProxyService = new boolean[refPlusServices];
                Class<?>[] interfaceClasses = new Class<?>[refPlusServices];
                boolean[] isOSGiToOSGiWire = new boolean[refPlusServices];
                boolean[] wireResolved = new boolean[refPlusServices];
                int index = 0;
                for (RuntimeWire wire : referenceWires.keySet()) {

                    Reference reference = referenceWires.get(wire);

                    isOSGiToOSGiWire[index] =
                        wire.getTarget().getComponent() != null && wire.getTarget().getComponent()
                            .getImplementationProvider() instanceof OSGiImplementationProvider;

                    Interface refInterface = reference.getInterfaceContract().getInterface();
                    if (refInterface instanceof JavaInterface) {
                        interfaceClasses[index] = ((JavaInterface)refInterface).getJavaClass();

                        //                        if (!isOSGiToOSGiWire[index])
                        //                            resolveWireCreateDummyBundles(interfaceClasses[index]);

                    }

                    if (!resolvedWires.contains(wire)) {
                        resolvedWires.add(wire);
                    } else
                        wireResolved[index] = true;

                    index++;
                }
                for (ComponentService service : runtimeComponent.getServices()) {
                    Interface callbackInterface = service.getInterfaceContract().getCallbackInterface();
                    if (callbackInterface instanceof JavaInterface) {
                        interfaceClasses[index] = ((JavaInterface)callbackInterface).getJavaClass();

//                        resolveWireCreateDummyBundles(interfaceClasses[index]);
                    }

                    index++;
                }

                index = 0;
                for (RuntimeWire wire : referenceWires.keySet()) {

                    if (!wireResolved[index]) {
                        createProxyService[index] =
                            resolveWireResolveReferences(osgiBundle,
                                                         interfaceClasses[index],
                                                         wire,
                                                         isOSGiToOSGiWire[index]);
                    }
                    index++;
                }

                refreshPackages();

                index = 0;
                for (RuntimeWire wire : referenceWires.keySet()) {

                    if (createProxyService[index] && !wireResolved[index])
                        resolveWireRegisterProxyService(osgiBundle, interfaceClasses[index], wire);
                    index++;
                }
            } else if (osgiBundle.getState() == Bundle.INSTALLED && packageAdmin != null) {
                packageAdmin.resolveBundles(new Bundle[] {osgiBundle});
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectCreationException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void configurePropertiesUsingConfigAdmin() {

        try {

            if (componentProperties.size() == 0)
                return;

            org.osgi.framework.ServiceReference configAdminReference =
                osgiBundle.getBundleContext().getServiceReference("org.osgi.service.cm.ConfigurationAdmin");
            if (configAdminReference != null) {

                Object cm = osgiBundle.getBundleContext().getService(configAdminReference);
                Class cmClass = cm.getClass().getClassLoader().loadClass("org.osgi.service.cm.ConfigurationAdmin");
                Method getConfigMethod = cmClass.getMethod("getConfiguration", String.class, String.class);

                Class configClass = cm.getClass().getClassLoader().loadClass("org.osgi.service.cm.Configuration");

                Method getMethod = configClass.getMethod("getProperties");
                Method updateMethod = configClass.getMethod("update", Dictionary.class);

                List<Service> services = implementation.getServices();
                HashSet<String> pidsProcessed = new HashSet<String>();

                for (Service service : services) {

                    Hashtable<String, Object> properties = getOSGiProperties(service);
                    String pid = null;

                    if (properties != null) {
                        for (Map.Entry<String, Object> prop : properties.entrySet()) {
                            pid = (String)prop.getValue();
                        }
                    }
                    if (pid == null || pidsProcessed.contains(pid))
                        continue;

                    Object config = getConfigMethod.invoke(cm, pid, null);
                    Dictionary props = (Dictionary)getMethod.invoke(config);
                    if (props == null) {
                        props = new Hashtable<String, Object>();
                    }
                    for (String propertyName : componentProperties.keySet()) {

                        props.put(propertyName, componentProperties.get(propertyName));
                    }

                    updateMethod.invoke(config, props);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isOptimizable() {
        return false;
    }

    public Scope getScope() {
        return Scope.COMPOSITE;
    }

    public boolean isEagerInit() {
        return false;
    }

    public long getMaxAge() {
        return 0l;
    }

    public long getMaxIdleTime() {
        return 0l;
    }

    public Invoker createTargetInvoker(RuntimeComponentService service, Operation operation) {
        Interface serviceInterface = operation.getInterface();
        Invoker invoker = new OSGiTargetInvoker(operation, this, service);
        return invoker;
    }

    public Invoker createInvoker(RuntimeComponentService service, Operation operation) {
        return createTargetInvoker(service, operation);
    }

    public boolean supportsOneWayInvocation() {
        return false;
    }

    private boolean setReferencesAndProperties() {

        for (Reference ref : implementation.getReferences()) {
            List<RuntimeWire> wireList = null;
            ComponentReference compRef = null;
            for (ComponentReference cRef : runtimeComponent.getReferences()) {
                if (cRef.getName().equals(ref.getName())) {

                    wireList = ((RuntimeComponentReference)cRef).getRuntimeWires();

                    compRef = cRef;
                    break;
                }
            }

            if (ref.getMultiplicity() == Multiplicity.ONE_N || ref.getMultiplicity() == Multiplicity.ZERO_N) {
                for (RuntimeWire wire : wireList) {
                    referenceWires.put(wire, ref);
                    componentReferenceWires.put(wire, compRef);
                }

            } else {
                if (wireList == null && ref.getMultiplicity() == Multiplicity.ONE_ONE) {
                    throw new IllegalStateException("Required reference is missing: " + ref.getName());
                }
                if (wireList != null && !wireList.isEmpty()) {
                    RuntimeWire wire = wireList.get(0);
                    referenceWires.put(wire, ref);
                    componentReferenceWires.put(wire, compRef);
                }

            }

        }

        componentProperties = getOSGiProperties(runtimeComponent);

        return true;

    }

    public void start() {
        setReferencesAndProperties();
    }

    public void stop() {

        if (osgiServiceListener != null) {
            OSGiImplementationActivator.getBundleContext().removeServiceListener(osgiServiceListener);
        }
    }

    public void frameworkEvent(FrameworkEvent event) {
        if (event.getType() == FrameworkEvent.PACKAGES_REFRESHED) {
            synchronized (this) {
                packagesRefreshed = true;
                this.notifyAll();
            }
        }

    }

    public void bundleChanged(BundleEvent event) {
        if (event.getType() == BundleEvent.RESOLVED && event.getBundle() == osgiBundle) {
            // TODO
        }
    }

    private class OSGiServiceListener implements ServiceListener {

        private Bundle bundle;

        OSGiServiceListener(Bundle bundle) {
            this.bundle = bundle;
        }

        public void serviceChanged(org.osgi.framework.ServiceEvent event) {

            org.osgi.framework.ServiceReference reference = event.getServiceReference();

            if (event.getType() == ServiceEvent.REGISTERED && reference.getBundle() == bundle) {

                synchronized (implementation) {

                    implementation.notifyAll();
                }
            }

            if (event.getType() == ServiceEvent.UNREGISTERING && reference.getBundle() == bundle) {
                // TODO: Process deregistering of OSGi services.
            }
        }
    }
}
