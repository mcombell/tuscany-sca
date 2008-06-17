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
package org.apache.tuscany.sca.test.osgi.runtime.impl;

import java.lang.reflect.Method;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public abstract class OSGiTestRuntime {

    public abstract BundleContext getBundleContext();

    protected abstract BundleContext startRuntime() throws Exception;
       
    private static OSGiTestRuntime instance;
    
    private BundleContext bundleContext;
    
    
    /**
     * System property org.apache.tuscany.implementation.osgi.runtime.OSGiRuntime can be set to the
     * name of the OSGiRuntime class (eg. EquinoxRuntime). If set, start this runtime and return the
     * system bundlecontext. If not set, start Felix from the classpath.
     * 
     * @throws BundleException
     */
    public synchronized static OSGiTestRuntime findRuntime() throws Exception {
        if (instance != null) {            
            return instance;
        }
        String runtimeClassName = System.getProperty(OSGiTestRuntime.class.getName());
        
        if (instance != null)
            return instance;

        if (runtimeClassName != null) {
            try {
                Class<?> runtimeClass = OSGiTestRuntime.class.getClassLoader().loadClass(runtimeClassName);
                Method method = runtimeClass.getMethod("getInstance");
                instance = (OSGiTestRuntime) method.invoke(null);
                return instance;
                
            } catch (Exception e) {
                throw new BundleException("Could not start OSGi runtime " + runtimeClassName, e);
            }
        }

        try {
            instance = EquinoxTestRuntime.getInstance();
        } catch (Throwable e) {
            instance = FelixTestRuntime.getInstance();
        }
        
        return instance;
 
    }
    
 
    public synchronized static OSGiTestRuntime getRuntime() throws Exception {
        
        instance = findRuntime();
        
        if (instance != null) {
            
            if (instance.bundleContext == null) {
                instance.startRuntime();
                instance.bundleContext = instance.getBundleContext();
            }
            return instance;
        }
        return instance;
    }


    public void shutdown() throws Exception {
        
        bundleContext = null;
        if (this == instance)
            instance = null;
    }
    
    protected void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }
    

    /**
     * @return the instance
     */
    public synchronized static void stop() throws Exception {
        if (instance != null) {
            instance.shutdown();
            instance = null;
        }
    }

}
