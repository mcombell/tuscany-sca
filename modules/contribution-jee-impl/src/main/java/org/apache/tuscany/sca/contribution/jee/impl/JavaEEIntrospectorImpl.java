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
package org.apache.tuscany.sca.contribution.jee.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.openejb.config.AppModule;
import org.apache.openejb.config.EjbModule;
import org.apache.openejb.config.UnknownModuleTypeException;
import org.apache.openejb.config.WebModule;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.EjbRef;
import org.apache.openejb.jee.EjbRefType;
import org.apache.openejb.jee.EjbReference;
import org.apache.openejb.jee.EnterpriseBean;
import org.apache.openejb.jee.EnvEntry;
import org.apache.openejb.jee.Filter;
import org.apache.openejb.jee.Listener;
import org.apache.openejb.jee.MessageDrivenBean;
import org.apache.openejb.jee.Servlet;
import org.apache.openejb.jee.SessionBean;
import org.apache.openejb.jee.SessionType;
import org.apache.openejb.jee.WebApp;
import org.apache.tuscany.sca.contribution.jee.EjbInfo;
import org.apache.tuscany.sca.contribution.jee.EjbModuleInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEApplicationInfo;
import org.apache.tuscany.sca.contribution.jee.JavaEEIntrospector;
import org.apache.tuscany.sca.contribution.jee.JavaEEModuleHelper;
import org.apache.tuscany.sca.contribution.jee.WebModuleInfo;
import org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo.EjbType;
import org.apache.tuscany.sca.contribution.service.ContributionReadException;
import org.apache.tuscany.sca.contribution.service.util.IOHelper;

/**
 * @version $Rev$ $Date$
 */
public class JavaEEIntrospectorImpl implements JavaEEIntrospector {
    
    public WebModuleInfo introspectWebArchive(URL artifactURL) throws ContributionReadException {
        // Create a temporary file since openejb extracts the archive to process
        InputStream inp = null;
        File tempFile = null;
        try {
            URLConnection connection = artifactURL.openConnection();
            connection.setUseCaches(false);
            inp = connection.getInputStream();
            tempFile = File.createTempFile("temp", ".war");
            FileOutputStream fout = new FileOutputStream(tempFile);
            IOHelper.copy(inp, fout);
            fout.close();
            inp.close();
        } catch (IOException e) {
            throw new ContributionReadException(e);
        }
        
        JavaEEModuleHelper jmh = new JavaEEModuleHelper();
        WebModule wm = jmh.getMetadataCompleteModules(tempFile.getAbsolutePath()).getWebModules().get(0);
        if(!tempFile.delete()) {
            tempFile.deleteOnExit();
        }
        return createWebModuleInfo(wm);
    }

    private WebModuleInfo createWebModuleInfo(WebModule webModule) {
        WebModuleInfo wmInfo = new WebModuleInfoImpl();
        
        WebApp webApp = webModule.getWebApp();
        ClassLoader classLoader = webModule.getClassLoader();
        
        // Process Remote EJB References
        for (Map.Entry<String, EjbRef> entry : webApp.getEjbRefMap().entrySet()) {
            EjbRef ejbRef = entry.getValue();
            if(ejbRef.getHome() != null) {
                // References to only EJB3 beans need to be considered.
                // Skip the current on as it is not a reference to an EJB3 bean.
                continue;
            }
            if (ejbRef.getRefType().compareTo(EjbReference.Type.REMOTE) != 0) {
                // Only Remote EJB references need to be considered.
                // Skip the current one as it is not a remote reference.
                continue;
            }
            //FIXME: ejbRef.getEjbRefType() is null sometimes.  Need a different way to figure the type.
            if(ejbRef.getEjbRefType() != null && ejbRef.getEjbRefType().compareTo(EjbRefType.SESSION) != 0) {
                // Only references to Session beans need to be considered.
                // Skip the current one as it is not a Session bean.
                continue;
            }

            try {
                wmInfo.getEjbReferences().put(ejbRef.getEjbRefName(), createEjbReferenceInfo(ejbRef, classLoader));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        // Process env-entries to compute properties
        for (Map.Entry<String, EnvEntry> entry : webApp.getEnvEntryMap().entrySet()) {
            EnvEntry envEntry = entry.getValue();
            wmInfo.getEnvEntries().put(envEntry.getEnvEntryName(), createEnvEntryInfo(envEntry));
        }        

        // Process Servlets
        for(Servlet servlet: webApp.getServlet()) {
            try {
                wmInfo.getServletClasses().add(classLoader.loadClass(servlet.getServletClass()));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // Process Filters
        for(Filter filter: webApp.getFilter()) {
            try {
                wmInfo.getFilterClasses().add(classLoader.loadClass(filter.getFilterClass()));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // Process Listeners
        for(Listener listener: webApp.getListener()) {
            try {
                wmInfo.getListenerClasses().add(classLoader.loadClass(listener.getListenerClass()));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        // TODO: Process JSF Managed beans
        
        // TODO: Process JSP pages
        
        return wmInfo;
    }
    
    private org.apache.tuscany.sca.contribution.jee.EnvEntryInfo createEnvEntryInfo(EnvEntry envEntry) {
        org.apache.tuscany.sca.contribution.jee.EnvEntryInfo envEntryInfo = new org.apache.tuscany.sca.contribution.jee.EnvEntryInfo();
        envEntryInfo.name = envEntry.getEnvEntryName();
        envEntryInfo.type = envEntry.getEnvEntryType();
        envEntryInfo.value = envEntry.getEnvEntryValue();
        
        return envEntryInfo;
    }

    private org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo createEjbReferenceInfo(EjbRef ejbRef, ClassLoader classLoader) throws ClassNotFoundException {
        org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo ejbReferenceInfo = new org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo();
        
        ejbReferenceInfo.referenceName = ejbRef.getEjbRefName();
        ejbReferenceInfo.referenceType = ejbRef.getRefType().compareTo(EjbReference.Type.REMOTE) == 0 ? org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo.RefType.REMOTE : ejbRef.getRefType().compareTo(EjbReference.Type.LOCAL) == 0 ? org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo.RefType.LOCAL : org.apache.tuscany.sca.contribution.jee.EjbReferenceInfo.RefType.UNKNOWN;
        ejbReferenceInfo.businessInterface = classLoader.loadClass(ejbRef.getInterface());

        ejbReferenceInfo.ejbType = EjbType.UNKNOWN;
        if(ejbRef.getEjbRefType() != null) {
            if(ejbRef.getEjbRefType().compareTo(EjbRefType.SESSION) == 0) {
                ejbReferenceInfo.ejbType = EjbType.SESSION_UNKNOWN;
            }
        }
        
        ejbReferenceInfo.ejbLink = ejbRef.getEjbLink();
        ejbReferenceInfo.mappedName = ejbRef.getMappedName();
        
        return ejbReferenceInfo;
    }

    public EjbModuleInfo introspectEjbArchive(URL artifactURL) throws ContributionReadException {
        // Create a temporary file since openejb extracts the archive to process
        InputStream inp = null;
        File tempFile = null;
        try {
            URLConnection connection = artifactURL.openConnection();
            connection.setUseCaches(false);
            inp = connection.getInputStream();
            tempFile = File.createTempFile("temp", ".jar");
            FileOutputStream fout = new FileOutputStream(tempFile);
            IOHelper.copy(inp, fout);
            fout.close();
            inp.close();
        } catch (IOException e) {
            throw new ContributionReadException(e);
        }
        
        JavaEEModuleHelper jmh = new JavaEEModuleHelper();
        EjbModule em;
        try {
            em = jmh.getMetadataCompleteModules(tempFile.getAbsolutePath()).getEjbModules().get(0);
        } catch(ContributionReadException e) {
            if(e.getCause() instanceof UnknownModuleTypeException) {
                // Not an EJB jar
                return null;
            } else {
                throw e;
            }
        } finally {
            if(!tempFile.delete()) {
                tempFile.deleteOnExit();
            }
        }
        return createEjbModuleInfo(em);
    }

    private EjbModuleInfo createEjbModuleInfo(EjbModule ejbModule) {
        EjbModuleInfo ejbModuleInfo = new EjbModuleInfoImpl();

        EjbJar ejbJar = ejbModule.getEjbJar();
        if (!ejbJar.getVersion().startsWith("3")) {
            // Not an EJB3 module
            // TODO: should throw an exception
            return null;
        }

        ClassLoader classLoader = ejbModule.getClassLoader();
        Map<String, EnterpriseBean> beansMap = ejbJar.getEnterpriseBeansByEjbName();
        for (Map.Entry<String, EnterpriseBean> entry : beansMap.entrySet()) {
            EnterpriseBean bean = entry.getValue();
            EjbInfo ejbInfo = null;
            if (bean instanceof SessionBean) {
                SessionBean sbean = (SessionBean)bean;
                ejbInfo = createEjbInfo(sbean, classLoader);
            } else if (bean instanceof MessageDrivenBean) {
                MessageDrivenBean mdbean = (MessageDrivenBean)bean;
                ejbInfo = createEjbInfo(mdbean, classLoader);
            } else {
                continue;
            }
            if (ejbInfo != null) {
                // Bean is an EJB3 bean
                ejbModuleInfo.getEjbInfos().put(bean.getEjbName(), ejbInfo);
            }
        }
        
        return ejbModuleInfo;
    }

    private EjbInfo createEjbInfo(MessageDrivenBean bean, ClassLoader classLoader) {
        try {
            if(javax.ejb.MessageDrivenBean.class.isAssignableFrom(classLoader.loadClass(bean.getEjbClass()))) {
                // Not an EJB3 bean
                return null;
            }
        } catch (ClassNotFoundException ignored) {
            // Should not happen
        }
        EjbInfo ejbInfo = new EjbInfo();
        
        ejbInfo.beanName = bean.getEjbName();
        
        try {
            ejbInfo.beanClass = classLoader.loadClass(bean.getEjbClass());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ejbInfo.ejbType = org.apache.tuscany.sca.contribution.jee.EjbInfo.EjbType.MESSAGE_DRIVEN;
        
        ejbInfo.mappedName = bean.getMappedName();

        processReferencesEnvEntries(bean, classLoader, ejbInfo);

        return ejbInfo;
    }

    private EjbInfo createEjbInfo(SessionBean bean, ClassLoader classLoader) {
        if(bean.getBusinessRemote().size() == 0 && bean.getBusinessLocal().size() == 0) {
            // Not an EJB3 Session bean
            return null;
        }
        EjbInfo ejbInfo = new EjbInfo();
        
        ejbInfo.beanName = bean.getEjbName();
        
        try {
            ejbInfo.beanClass = classLoader.loadClass(bean.getEjbClass());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ejbInfo.ejbType = bean.getSessionType().equals(SessionType.STATEFUL) ? org.apache.tuscany.sca.contribution.jee.EjbInfo.EjbType.SESSION_STATEFUL : bean.getSessionType().equals(SessionType.STATELESS) ? org.apache.tuscany.sca.contribution.jee.EjbInfo.EjbType.SESSION_STATELESS : org.apache.tuscany.sca.contribution.jee.EjbInfo.EjbType.SESSION_UNKNOWN;

        ejbInfo.mappedName = bean.getMappedName();
        //FIXME: Is it ok to use beanName when mapped name is null?
        if(ejbInfo.mappedName == null) {
            ejbInfo.mappedName = ejbInfo.beanName;
        }

        // Process Remote Business interfaces of the SessionBean
        for (String intfName : bean.getBusinessRemote()) {
            try {
                ejbInfo.businessRemote.add(classLoader.loadClass(intfName));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }        

        // Process Local Business interfaces of the SessionBean
        for (String intfName : bean.getBusinessLocal()) {
            try {
                ejbInfo.businessLocal.add(classLoader.loadClass(intfName));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }        

        processReferencesEnvEntries(bean, classLoader, ejbInfo);
        
        return ejbInfo;
    }

    private void processReferencesEnvEntries(EnterpriseBean bean, ClassLoader classLoader, EjbInfo ejbInfo) {
        // Process Remote EJB References
        for (Map.Entry<String, EjbRef> entry : bean.getEjbRefMap().entrySet()) {
            EjbRef ejbRef = entry.getValue();
            if(ejbRef.getHome() != null) {
                // References to only EJB3 beans need to be considered.
                // Skip the current on as it is not a reference to an EJB3 bean.
                continue;
            }
            if (ejbRef.getRefType().compareTo(EjbReference.Type.REMOTE) != 0) {
                // Only Remote EJB references need to be considered.
                // Skip the current one as it is not a remote reference.
                continue;
            }
            //FIXME: ejbRef.getEjbRefType() is null sometimes.  Need a different way to figure the type.
            if(ejbRef.getEjbRefType() != null && ejbRef.getEjbRefType().compareTo(EjbRefType.SESSION) != 0) {
                // Only references to Session beans need to be considered.
                // Skip the current one as it is not a Session bean.
                continue;
            }

            try {
                ejbInfo.ejbReferences.put(ejbRef.getEjbRefName(), createEjbReferenceInfo(ejbRef, classLoader));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }    

        // Process env-entries
        for (Map.Entry<String, EnvEntry> entry : bean.getEnvEntryMap().entrySet()) {
            EnvEntry envEntry = entry.getValue();
            
            ejbInfo.envEntries.put(envEntry.getEnvEntryName(), createEnvEntryInfo(envEntry));
        }
    }

    public JavaEEApplicationInfo introspectJeeArchive(URL artifactURL) throws ContributionReadException {
        // Create a temporary file since openejb extracts the archive to process
        InputStream inp = null;
        File tempFile = null;
        try {
            URLConnection connection = artifactURL.openConnection();
            connection.setUseCaches(false);
            inp = connection.getInputStream();
            tempFile = File.createTempFile("temp", ".ear");
            FileOutputStream fout = new FileOutputStream(tempFile);
            IOHelper.copy(inp, fout);
            fout.close();
            inp.close();
        } catch (IOException e) {
            throw new ContributionReadException(e);
        }
        
        JavaEEModuleHelper jmh = new JavaEEModuleHelper();
        AppModule appModule = jmh.getMetadataCompleteModules(tempFile.getAbsolutePath());
        if(!tempFile.delete()) {
            tempFile.deleteOnExit();
        }
        return createJavaEEApplicationInfo(appModule);
    }

    private org.apache.tuscany.sca.contribution.jee.JavaEEApplicationInfo createJavaEEApplicationInfo(AppModule appModule) {
        JavaEEApplicationInfo appInfo = new JavaEEApplicationInfoImpl();
        for(EjbModule em : appModule.getEjbModules()){
            EjbModuleInfo ejbModuleInfo = createEjbModuleInfo(em);
            ejbModuleInfo.setModuleName(em.getModuleId());
            appInfo.getEjbModuleInfos().put(em.getModuleId(), ejbModuleInfo);
        }

        for(WebModule wm : appModule.getWebModules()) {
            WebModuleInfo webModuleInfo = createWebModuleInfo(wm);
            webModuleInfo.setModuleName(wm.getModuleId());
            appInfo.getWebModuleInfos().put(wm.getModuleId(), webModuleInfo);
        }
        
        return appInfo;
    }
}
