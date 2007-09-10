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
package org.apache.tuscany.samples.das.util;
/**
 * 
 * XML utilities
 *
 */
public class XmlUtil {
	/**
	 * From the complete serialized DataGraph, get the required portion
	 *relevant to <das:DataGraphRoot> element.
	 */    
    public static String getXmlContents(String xmlString, String xmlStartElement, String xmlEndElement){
        int posStartElement = xmlString.indexOf(xmlStartElement);
        int posEndElement = xmlString.indexOf(xmlEndElement) ;
        
        //check if startElement was not found
        if(posStartElement == -1)
            posStartElement = 0;
        
        //check if endElement was not found
        if(posEndElement == -1)
            posEndElement = xmlString.length();
        else
            //fixup the posEndElement to return the closing element tag
            posEndElement += + xmlEndElement.length();
        
        //return xml contents for the requested element
        String xmlBody = xmlString.substring(posStartElement, posEndElement);
        
        
        String result = 
        "<?xml version='1.0' encoding='ISO-8859-1' ?> \n"+
        "<root  xmlns:das='http://org.apache.tuscany.das.rdb/config.xsd' "+ 
        " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'> \n"+
        xmlBody+" </root>";
        
        return result;
    }

}
