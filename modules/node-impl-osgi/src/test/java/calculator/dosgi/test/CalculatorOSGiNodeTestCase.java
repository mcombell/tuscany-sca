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

package calculator.dosgi.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;

import org.apache.tuscany.sca.node.equinox.launcher.EquinoxHost;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import calculator.dosgi.CalculatorActivator;
import calculator.dosgi.CalculatorService;
import calculator.dosgi.CalculatorServiceDSImpl;
import calculator.dosgi.CalculatorServiceImpl;
import calculator.dosgi.operations.AddService;
import calculator.dosgi.operations.AddServiceImpl;
import calculator.dosgi.operations.DivideService;
import calculator.dosgi.operations.DivideServiceImpl;
import calculator.dosgi.operations.MultiplyService;
import calculator.dosgi.operations.MultiplyServiceImpl;
import calculator.dosgi.operations.OperationsActivator;
import calculator.dosgi.operations.SubtractService;
import calculator.dosgi.operations.SubtractServiceImpl;

/**
 * 
 */
public class CalculatorOSGiNodeTestCase {
    private static EquinoxHost host;
    private static BundleContext context;
    private static Boolean client;

    public static URL getCodeLocation(final Class<?> anchorClass) {
        return AccessController.doPrivileged(new PrivilegedAction<URL>() {
            public URL run() {
                return anchorClass.getProtectionDomain().getCodeSource().getLocation();
            }
        });
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            String prop = System.getProperty("client");
            if (prop != null) {
                client = Boolean.valueOf(prop);
            }
            Set<URL> bundles = new HashSet<URL>();

            if (client == null || client.booleanValue()) {
                System.out.println("Generating calculator.dosgi bundle...");
                bundles.add(OSGiTestBundles.createBundle("target/test-classes/calculator-bundle.jar",
                                                         "calculator/dosgi/META-INF/MANIFEST.MF",
                                                         new String[][] {
                                                                         {"OSGI-INF/calculator-component.xml", null},
                                                                         {"calculator/dosgi/bundle.componentType",
                                                                          "OSGI-INF/sca/bundle.componentType"},
                                                                         {"calculator/dosgi/calculator.composite",
                                                                          "OSGI-INF/sca/bundle.composite"}},
                                                         CalculatorService.class,
                                                         // Package the interfaces so that the operations bundle can be remote
                                                         AddService.class,
                                                         SubtractService.class,
                                                         MultiplyService.class,
                                                         DivideService.class,
                                                         CalculatorServiceImpl.class,
                                                         CalculatorServiceDSImpl.class,
                                                         CalculatorActivator.class));
            }

            if (client == null || !client.booleanValue()) {
                System.out.println("Generating calculator.dosgi.operations bundle...");
                bundles.add(OSGiTestBundles
                    .createBundle("target/test-classes/operations-bundle.jar",
                                  "calculator/dosgi/operations/META-INF/MANIFEST.MF",
                                  new String[][] {
                                                  {"OSGI-INF/add-component.xml", null},
                                                  {"OSGI-INF/subtract-component.xml", null},
                                                  {"OSGI-INF/multiply-component.xml", null},
                                                  {"OSGI-INF/divide-component.xml", null},
                                                  {"calculator/dosgi/operations/bundle.componentType",
                                                   "OSGI-INF/sca/bundle.componentType"},
                                                  {"calculator/dosgi/operations/operations.composite",
                                                   "OSGI-INF/sca/bundle.composite"}},
                                  OperationsActivator.class,
                                  AddService.class,
                                  AddServiceImpl.class,
                                  SubtractService.class,
                                  SubtractServiceImpl.class,
                                  MultiplyService.class,
                                  MultiplyServiceImpl.class,
                                  DivideService.class,
                                  DivideServiceImpl.class));
            }
            host = new EquinoxHost();
            context = host.start();
            for (URL loc : bundles) {
                host.installBundle(loc, null);
            }
            for (Bundle b : context.getBundles()) {
                if (b.getSymbolicName().equals("org.eclipse.equinox.ds") || b.getSymbolicName()
                    .startsWith("org.apache.tuscany.sca.")) {
                    try {
                        b.start();
                    } catch (Exception e) {
                        System.out.println(string(b, false));
                        e.printStackTrace();
                    }
                    System.out.println(string(b, false));
                }
            }
            for (Bundle b : context.getBundles()) {
                if (b.getSymbolicName().startsWith("calculator.dosgi")) {
                    b.start();
                    System.out.println(string(b, false));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static <T> T cast(Object obj, Class<T> cls) {
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        } else {
            return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(),
                                                   new Class<?>[] {cls},
                                                   new InvocationHandlerImpl(obj)));
        }
    }

    private static class InvocationHandlerImpl implements InvocationHandler {
        private Object instance;

        public InvocationHandlerImpl(Object instance) {
            super();
            this.instance = instance;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method m = instance.getClass().getMethod(method.getName(), method.getParameterTypes());
            return m.invoke(instance, args);
        }

    }

    @Test
    public void testOSGi() {
        if (client == null || client.booleanValue()) {
            ServiceReference ref = context.getServiceReference(CalculatorService.class.getName());
            Assert.assertNotNull(ref);
            Object service = context.getService(ref);
            Assert.assertNotNull(service);
            CalculatorService calculator = cast(service, CalculatorService.class);
            System.out.println("2.0 + 1.0 = " + calculator.add(2.0, 1.0));
            System.out.println("2.0 - 1.0 = " + calculator.subtract(2.0, 1.0));
            System.out.println("2.0 * 1.0 = " + calculator.multiply(2.0, 1.0));
            System.out.println("2.0 / 1.0 = " + calculator.divide(2.0, 1.0));
        }
    }

    /**
     * Returns a string representation of the given bundle.
     * 
     * @param b
     * @param verbose
     * @return
     */
    static String string(Bundle bundle, boolean verbose) {
        StringBuffer sb = new StringBuffer();
        sb.append(bundle.getBundleId()).append(" ").append(bundle.getSymbolicName());
        int s = bundle.getState();
        if ((s & Bundle.UNINSTALLED) != 0) {
            sb.append(" UNINSTALLED");
        }
        if ((s & Bundle.INSTALLED) != 0) {
            sb.append(" INSTALLED");
        }
        if ((s & Bundle.RESOLVED) != 0) {
            sb.append(" RESOLVED");
        }
        if ((s & Bundle.STARTING) != 0) {
            sb.append(" STARTING");
        }
        if ((s & Bundle.STOPPING) != 0) {
            sb.append(" STOPPING");
        }
        if ((s & Bundle.ACTIVE) != 0) {
            sb.append(" ACTIVE");
        }

        if (verbose) {
            sb.append(" ").append(bundle.getLocation());
            sb.append(" ").append(bundle.getHeaders());
        }
        return sb.toString();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (host != null) {
            if (client != null && !client.booleanValue()) {
                System.out.println("Press Enter to stop the node...");
                System.in.read();
            }
            host.stop();
            context = null;
        }
    }

}
