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
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;
import java.awt.Image;
import org.apache.axiom.om.OMElement;
import javax.xml.transform.Source;

/**
 * This client program shows how to create an SCA runtime, start it,
 * locate the FileTransfer service and invoke it.
 */
@Service(FileTransferServiceClient.class)
public class FileTransferClient implements FileTransferServiceClient {
    
	@Reference
    public FileTransferService fileTransferService;

    public String uploadImageFileForward (Image attachment) throws Exception {
    	return fileTransferService.uploadImageFile(attachment);
    }
    
    public String uploadSourceFileForward (Source attachment) throws Exception {
    	return fileTransferService.uploadSourceFile(attachment);
    }
    
    public String uploadDataHandlerFileForward (DataHandler attachment) throws Exception {
    	return fileTransferService.uploadDataHandlerFile(attachment);
    }
    
    public String uploadOMElementFileForward (OMElement attachment) throws Exception {
    	return fileTransferService.uploadOMElementFile(attachment);
    }
    
    public String sendMyExceptionForward (MyException attachment) throws Exception {
    	return fileTransferService.sendMyException(attachment);
    }
}
