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

package org.apache.tuscany.sca.implementation.widget.provider;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tuscany.sca.runtime.RuntimeComponent;

import org.apache.tuscany.sca.core.web.JavascriptProxyFactoryExtensionPoint;


/**
 * Servlet to handle requests for the widget component .js script.
 * 
 * @version $Rev$ $Date$
 */
public class WidgetComponentScriptServlet extends HttpServlet {
    private static final long serialVersionUID = 2454705532282398190L;
    
    private transient JavascriptProxyFactoryExtensionPoint javascriptProxyFactories;
    private transient RuntimeComponent component;
    
    
    /**
     * Constructor receiving the runtimeComponent reference that is going to be used to generate the widget client js
     * @param component
     */
    public WidgetComponentScriptServlet(RuntimeComponent component, JavascriptProxyFactoryExtensionPoint javascriptProxyFactories) {
        this.component = component;
        this.javascriptProxyFactories = javascriptProxyFactories;
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ServletOutputStream os = response.getOutputStream();
            WidgetComponentScriptGenerator.generateWidgetCode(component, javascriptProxyFactories, os);
        }catch(URISyntaxException e) {
            throw new IOException("Invalid uri creating JavaScript resource");
        }
    }
}
