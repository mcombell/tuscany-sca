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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tuscany.sca.assembly.Binding;
import org.apache.tuscany.sca.assembly.ComponentProperty;
import org.apache.tuscany.sca.assembly.ComponentReference;
import org.apache.tuscany.sca.runtime.RuntimeComponent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This helper class concatenates the necessary JavaScript client code into a
 * single JavaScript per component
 * @version $Rev: 665897 $ $Date: 2008-06-09 14:31:03 -0700 (Mon, 09 Jun 2008) $
 */
public class WidgetComponentScriptGenerator {

    private static final long serialVersionUID = 1L;
    
    public static InputStream generateWidgetCode(RuntimeComponent component) throws IOException, URISyntaxException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(bos);
     
        generateWidgetCode(component, pw);
        
        return new ByteArrayInputStream(bos.toByteArray());
    }
    
    public static void generateWidgetCode(RuntimeComponent component, OutputStream os) throws IOException, URISyntaxException {
        PrintWriter pw = new PrintWriter(os);
        
        generateWidgetCode(component, pw);
    }
            

    /**
     * This helper class concatenates the necessary JavaScript client code into a
     * single JavaScript per component
     */
    public static void generateWidgetCode(RuntimeComponent component, PrintWriter pw) throws IOException, URISyntaxException {
        pw.println();
        pw.println("/* Apache Tuscany SCA Widget header */");
        pw.println();

        Map<String, Boolean> bindingClientProcessed = new HashMap<String, Boolean>();

        for(ComponentReference reference : component.getReferences()) {
            for(Binding binding : reference.getBindings()) {
                String [] bindingProxyNames = WidgetProxyHelper.getJavaScriptProxyFile(binding.getClass().getName());
                //check if binding client code was already processed and inject to the generated script
                if ( bindingProxyNames != null ) {
                    for ( int i = 0; i < bindingProxyNames.length; i++ ) {
                        String bindingProxyName = bindingProxyNames[ i ];
                        if(bindingProxyName != null) {
                            Boolean processedFlag = bindingClientProcessed.get(bindingProxyName);
                            if( processedFlag == null || processedFlag.booleanValue() == false) {
                                generateJavaScriptBindingProxy(pw,bindingProxyName);
                                bindingClientProcessed.put(bindingProxyName, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }

        pw.println();
        pw.println("/* Tuscany Reference/Property injection code */");
        pw.println();

        
        //define tuscany.sca namespace
        generateJavaScriptNamespace(pw);

        pw.println();

        //process properties
        generateJavaScriptPropertyFunction(component, pw);

        pw.println();

        //process references
        generateJavaScriptReferenceFunction(component, pw);


        pw.println();
        pw.println("/** End of Apache Tuscany SCA Widget */");
        pw.println();
        pw.flush();
        pw.close();

        
    }


    /**
     * Retrieve the binding proxy based on the bind name
     * and embedded the JavaScript into this js
     */
    private static void generateJavaScriptBindingProxy(PrintWriter pw, String bindingProxyName) throws IOException {
        //FIXME: Handle the case where the JavaScript binding client is not found
        InputStream is = WidgetComponentScriptGenerator.class.getClassLoader().getResourceAsStream(bindingProxyName);
        if (is != null) {
            int i;
            while ((i = is.read()) != -1) {
                pw.write(i);
            }           
        }
        
        pw.println();
        pw.println();
    }

    /**
     * Generate the tuscany.sca namespace if not yet available
     * @param pw
     * @throws IOException
     */
    private static void generateJavaScriptNamespace(PrintWriter pw) throws IOException {
        pw.println("if (!tuscany) { \n" +
                        "var tuscany = {}; \n" +
                        "}");
        pw.println("if (!tuscany.sca) { \n" +
                        "tuscany.sca = {}; \n" +
                        "}");
    }
   
    /**
     * Generate JavaScript code to inject SCA Properties
     * @param pw
     * @throws IOException
     */
    private static void generateJavaScriptPropertyFunction(RuntimeComponent component, PrintWriter pw) throws IOException {        
        pw.println("tuscany.sca.propertyMap = new String();");
        for(ComponentProperty property : component.getProperties()) {
            String propertyName = property.getName();

            pw.println("tuscany.sca.propertyMap." + propertyName + " = \"" + getPropertyValue(property) + "\"");
        }
        
        pw.println("tuscany.sca.Property = function (name) {");
        pw.println("    return tuscany.sca.propertyMap[name];");
        pw.println("}");
    }
    
    /**
     * Convert property value to String
     * @param property
     * @return
     */
    private static String getPropertyValue(ComponentProperty property) {
        Document doc = (Document)property.getValue();
        Element rootElement = doc.getDocumentElement();

        String value = null;

        //FIXME : Provide support for isMany and other property types

        if (rootElement.getChildNodes().getLength() > 0) {
            value = rootElement.getChildNodes().item(0).getTextContent();
        }

        return value;
    }


    
    /**
     * Generate JavaScript code to inject SCA References
     * @param pw
     * @throws IOException
     */
    private static void generateJavaScriptReferenceFunction (RuntimeComponent component, PrintWriter pw) throws IOException, URISyntaxException {
        
        pw.println("tuscany.sca.referenceMap = new Object();");
        for(ComponentReference reference : component.getReferences()) {
            String referenceName = reference.getName();
            Binding binding = reference.getBindings().get(0);
            if (binding != null) {
                
                String proxyClient = WidgetProxyHelper.getJavaScriptProxyClient(binding.getClass().getName());
                if(proxyClient != null) {
                    
                    // Generate the JavaScript proxy configuration code
                    // Proxies are configured with the target URI path, as at this point we shouldn't
                    // be generating proxies that communicate with other hosts (if a proxy needs to
                    // communicate with another host it should be generated on and served by
                    // that particular host)
                    
                    /*
                    URI targetURI = URI.create(binding.getURI());
                    String targetPath;
                    if(targetURI.getScheme() == null) {
                        targetPath = targetURI.getPath();
                    } else {
                        targetPath = targetURI.toString();
                    }
                     */

                    URI targetURI = URI.create(binding.getURI());
                    String targetPath = targetURI.getPath();
                    
                    if(proxyClient.equals("JSONRpcClient")) {
                        //FIXME Proxies should follow the same pattern, saving us from having to test for JSONRpc here
                        pw.println("tuscany.sca.referenceMap." + referenceName + " = new " + proxyClient + "(\"" + targetPath + "\").Service;");
                    } else {
                        pw.println("tuscany.sca.referenceMap." + referenceName + " = new " + proxyClient + "(\"" + targetPath + "\");");
                    }
                }                
            }
        }
        
        pw.println("tuscany.sca.Reference = function (name) {");
        pw.println("    return tuscany.sca.referenceMap[name];");
        pw.println("}");
    }

}
