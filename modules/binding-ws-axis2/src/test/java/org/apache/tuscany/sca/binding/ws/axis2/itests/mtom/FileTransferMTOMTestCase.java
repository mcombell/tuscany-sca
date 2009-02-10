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

package org.apache.tuscany.sca.binding.ws.axis2.itests.mtom;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.awt.Image;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.OMElementImpl;
import org.apache.axiom.om.OMText;
import javax.xml.transform.dom.DOMSource;
import org.apache.axiom.om.OMFactory;
import java.awt.image.BufferedImage;
import junit.framework.TestCase;
import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.apache.tuscany.sca.binding.ws.axis2.itests.mtom.FileTransferServiceClient;
import org.apache.tuscany.sca.databinding.xml.String2Node;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileTransferMTOMTestCase extends TestCase {

    private SCADomain domain;
    
    /**
     * Runs once before the tests
     */
    @BeforeClass
    protected void setUp() throws Exception {
    	domain = SCADomain.newInstance("org/apache/tuscany/sca/binding/ws/axis2/itests/mtom/filetransferservice.composite");
    }
   
    /**
     * Runs once after the tests
     */
    @AfterClass
    protected void tearDown() throws Exception {
    	domain.close();
    }

    @Test
    public void testImageFileTransfer() throws Exception {
        try {        	
            Image image = new BufferedImage(80, 24, BufferedImage.TYPE_INT_RGB);
            
            FileTransferServiceClient filetransfer = domain.getService(FileTransferServiceClient.class, "FileTransferClientComponent");
            assertEquals("File uploaded Sucessfully", filetransfer.uploadImageFileForward(image));            
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testSourceFileTransfer() throws Exception {
        try {        	 
        	String xml = "<a>A<b>B</b><c>C</c></a>";
            Source source = new DOMSource(new String2Node().transform(xml, null));
            
            FileTransferServiceClient filetransfer = domain.getService(FileTransferServiceClient.class, "FileTransferClientComponent");
            assertEquals("File uploaded Sucessfully", filetransfer.uploadSourceFileForward(source));            
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testDataHandlerFileTransfer() throws Exception {
        try {            
            // For testing purpose lets try uploading FileTransferClient.java file.
            DataHandler dataHandler = new DataHandler(new FileDataSource("./LICENSE"));
            
            FileTransferServiceClient filetransfer = domain.getService(FileTransferServiceClient.class, "FileTransferClientComponent");
            assertEquals("File uploaded Sucessfully", filetransfer.uploadDataHandlerFileForward(dataHandler));            
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testOMElementFileTransfer() throws Exception {
        try {        	
        	OMFactory factory = OMAbstractFactory.getOMFactory();                        
            OMElement imageElement = factory.createOMElement(new QName("image"));   
            
            // For testing purpose lets try uploading FileTransferClient.java file.
            DataHandler dataHandler = new DataHandler(new FileDataSource("./LICENSE"));
            
            OMText textData = factory.createOMText(dataHandler, true);
            imageElement.addChild(textData);
                       
            FileTransferServiceClient filetransfer = domain.getService(FileTransferServiceClient.class, "FileTransferClientComponent");
            assertEquals("File uploaded Sucessfully", filetransfer.uploadOMElementFileForward(imageElement));
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
