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

package org.apache.tuscany.sca.binding.jsonrpc.js;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.assembly.Binding;
import org.apache.tuscany.sca.assembly.ComponentReference;
import org.apache.tuscany.sca.binding.jsonrpc.JSONRPCBinding;
import org.apache.tuscany.sca.core.web.JavascriptProxyFactory;

public class JSONRPCBindingJavascriptProxyFactoryImpl implements JavascriptProxyFactory {
    private static final QName NAME = new QName("http://tuscany.apache.org/xmlns/sca/1.0", "binding.jsonrpc");
    private static final String JAVASCRIPT_FILE_NAME = "binding-jsonrpc.js";
    
    public Class<?> getModelType() {
        return JSONRPCBinding.class;
    }

    public QName getQName() {
        return NAME;
    }

    public String getJavascriptProxyFile() {
        return JAVASCRIPT_FILE_NAME;
    }

    public InputStream getJavascriptProxyFileAsStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(JAVASCRIPT_FILE_NAME);
        if (is == null) {
            throw new IOException("Could not find Javascript '" + JAVASCRIPT_FILE_NAME + "'" );
        }
        
        return is;
    }
    
    public String createJavascriptReference(ComponentReference componentReference) throws IOException {
        Binding binding = componentReference.getBindings().get(0);
        URI targetURI = URI.create(binding.getURI());
        String targetPath = targetURI.getPath();
        
        return "JSONRpcClient(\"" + targetPath + "\").Service";        
    }

}