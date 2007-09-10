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
package org.apache.tuscany.das.rdb.test;

import java.util.List;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.test.data.DocumentsImagesData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class LOBTests extends DasTest {
    protected void setUp() throws Exception {
        super.setUp();

        new DocumentsImagesData(getAutoConnection()).refresh();
    }
    
    protected void tearDown() throws Exception {
    	super.tearDown();
    }
    
    public void testReadWriteBlob() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConnection());
    	
    	//Select image
    	Command read = das.createCommand("SELECT PIC FROM DOCUMENTS_IMAGES WHERE id = 100");
    	DataObject root = read.executeQuery();
    	List result = root.getList("DOCUMENTS_IMAGES");
    	assertEquals(1, result.size());
    	byte[] imgArray = ((DataObject)result.get(0)).getBytes(0);
    	assertEquals(150, imgArray.length);
    	
    	//insert same image
    	Command insert = das.createCommand("INSERT INTO DOCUMENTS_IMAGES (ID, PIC) VALUES (?,?)");
    	insert.setParameter(1, new Integer(101));
    	insert.setParameter(2, imgArray);
    	insert.execute();
    	
    	//Select again
    	Command readNew = das.createCommand("SELECT PIC FROM DOCUMENTS_IMAGES WHERE id = 101");
    	root = readNew.executeQuery();
    	result = root.getList("DOCUMENTS_IMAGES");
    	assertEquals(1, result.size());
    	imgArray = ((DataObject)result.get(0)).getBytes(0);
    	assertEquals(150, imgArray.length);
    }
    
    public void testReadWriteClob() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConnection());
    	
    	//Select image
    	Command read = das.createCommand("SELECT TEXT FROM DOCUMENTS_IMAGES WHERE id = 100");
    	DataObject root = read.executeQuery();
    	List result = root.getList("DOCUMENTS_IMAGES");
    	assertEquals(1, result.size());
    	Object txtObj = ((DataObject)result.get(0)).get(0);
    	assertTrue(txtObj instanceof java.sql.Clob);
    	java.sql.Clob txtClob = (java.sql.Clob)txtObj;
    	assertEquals(19, txtClob.length());
    	
    	//insert same image
    	Command insert = das.createCommand("INSERT INTO DOCUMENTS_IMAGES (ID, TEXT) VALUES (?,?)");
    	insert.setParameter(1, new Integer(101));
    	insert.setParameter(2, txtClob);
    	insert.execute();
    	
    	//Select again
    	Command readNew = das.createCommand("SELECT TEXT FROM DOCUMENTS_IMAGES WHERE id = 101");
    	root = readNew.executeQuery();
    	result = root.getList("DOCUMENTS_IMAGES");
    	assertEquals(1, result.size());
    	txtObj = ((DataObject)result.get(0)).get(0);    	
    	assertTrue(txtObj instanceof java.sql.Clob);
    	txtClob = (java.sql.Clob)txtObj;
    	assertEquals(19, txtClob.length());
    }    
}
