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
package org.apache.tuscany.sca.interfacedef.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tuscany.sca.interfacedef.java.impl.JavaInterfaceFactoryImpl;
import org.apache.tuscany.sca.interfacedef.java.introspect.JavaInterfaceVisitor;

/**
 * A factory for the Java interface model.
 */
public class DefaultJavaInterfaceFactory extends JavaInterfaceFactoryImpl implements JavaInterfaceFactory {
    
    private boolean loadedVisitors; 
    
    public DefaultJavaInterfaceFactory() {
    }
    
    @Override
    public List<JavaInterfaceVisitor> getInterfaceVisitors() {
        loadVisitors();
        return super.getInterfaceVisitors();
    }
    
    /**
     * Load visitors declared under META-INF/services
     */
    @SuppressWarnings("unchecked")
    private void loadVisitors() {
        if (loadedVisitors)
            return;

        // Get the databinding service declarations
        ClassLoader classLoader = JavaInterfaceVisitor.class.getClassLoader();
        List<String> visitorDeclarations; 
        try {
            visitorDeclarations = getServiceClassNames(classLoader, JavaInterfaceVisitor.class.getName());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        
        // Load data bindings
        for (String visitorDeclaration: visitorDeclarations) {
            JavaInterfaceVisitor visitor;
            try {
                Class<JavaInterfaceVisitor> visitorClass = (Class<JavaInterfaceVisitor>)Class.forName(visitorDeclaration, true, classLoader);
                visitor = visitorClass.newInstance();
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            } catch (InstantiationException e) {
                throw new IllegalArgumentException(e);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
            addInterfaceVisitor(visitor);
        }
        
        loadedVisitors = true;
    }

    /**
     * Read the service name from a configuration file
     * 
     * @param classLoader
     * @param name The name of the service class
     * @return A class name which extends/implements the service class
     * @throws IOException
     */
    private List<String> getServiceClassNames(ClassLoader classLoader, String name) throws IOException {
        List<String> classNames = new ArrayList<String>();
        for (URL url: Collections.list(classLoader.getResources("META-INF/services/" + name))) {
            InputStream is = url.openStream();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    String line = reader.readLine();
                    if (line == null)
                        break;
                    line = line.trim();
                    if (!line.startsWith("#") && !"".equals(line)) {
                        classNames.add(line.trim());
                    }
                }
            } finally {
                if (reader != null)
                    reader.close();
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ioe) {}
                }
            }
        }
        return classNames;
    }

}
