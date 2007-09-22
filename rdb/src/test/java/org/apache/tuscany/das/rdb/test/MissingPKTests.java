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
import org.apache.tuscany.das.rdb.test.data.BookData;
import org.apache.tuscany.das.rdb.test.data.DogData;
import org.apache.tuscany.das.rdb.test.data.MultiSchemaData;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsData;
import org.apache.tuscany.das.rdb.test.data.OrderDetailsDescriptionData;
import org.apache.tuscany.das.rdb.test.data.OwnerData;
import org.apache.tuscany.das.rdb.test.data.OwnerDogData;
import org.apache.tuscany.das.rdb.test.data.PartData;
import org.apache.tuscany.das.rdb.test.data.SingerData;
import org.apache.tuscany.das.rdb.test.data.SongData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class MissingPKTests  extends DasTest {
    protected void setUp() throws Exception {
        super.setUp();
        new PartData(getAutoConnection()).refresh();
        new DogData(getAutoConnection()).refresh();
        new OwnerData(getAutoConnection()).refresh();
        new OwnerDogData(getAutoConnection()).refresh();
        new OrderDetailsData(getAutoConnection()).refresh();
        new OrderDetailsDescriptionData(getAutoConnection()).refresh();
        new MultiSchemaData(getAutoConnection()).refresh();
        new BookData(getAutoConnection()).refresh();
        new SingerData(getAutoConnection()).refresh();
        new SongData(getAutoConnection()).refresh();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    //join with both tables PKs present in SELECT, PK data in child is null
    public void testNullDataInPK() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("testNullDataInPK");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    //join with both tables PKs present in SELECT, child row is complete null, outer join
    public void testOuterJoin() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("testOuterJoin");
    	DataObject root = select.executeQuery();
    	List singers = root.getList("SINGER");
    	List songs = root.getList("SONG");//as there is no relationship (explicit/implicit)
    	assertNotNull(singers);
    	assertEquals(1, singers.size());
    	assertNotNull(songs);
    	assertEquals(0, songs.size());
    }

    //join with both tables' PKs present in SELECT
    public void test11() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("test11");
    	DataObject root = select.executeQuery();
    	List owners = root.getList("OWNER");
    	List dogs = root.getList("DOG");//as there is no relationship (explicit/implicit)
    	assertNotNull(owners);
    	assertNotNull(dogs);
    	for(int i=0; i<owners.size(); i++){
    		DataObject dobj = (DataObject)owners.get(i);
    	}
    	assertEquals(3, owners.size());
    	assertEquals(3, dogs.size());
    }

    //join with parent table's PK missing in SELECT
    public void test22() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("test22");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    //join with child table's PK missing in SELECT
    public void test33() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("test33");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    //join with both tables' PK missing in SELECT
    public void test44() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("test44");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    //join with both tables' PK present in SELECT (use relationship)
    public void test11_rel() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("test11");
    	DataObject root = select.executeQuery();
    	List owners = root.getList("OWNER");
    	assertNotNull(owners);
    	assertEquals(3, owners.size());

    	if(owners != null){
    		for(int i=0; i<owners.size(); i++){
    			List dogs = ((DataObject)owners.get(i)).getList("owns"); //use relationship
    	    	assertNotNull(dogs);

    	    	if( (((DataObject)owners.get(i)).getInt("ID") == 1 ) &&
    	    			dogs.size()==1){
    	    		assertTrue(true);//expected
    	    	}

    	    	if( (((DataObject)owners.get(i)).getInt("ID") == 2 ) &&
    	    			dogs.size()==1){
    	    		assertTrue(true);//expected
    	    	}

    	    	if( (((DataObject)owners.get(i)).getInt("ID") == 3 ) &&
    	    			dogs.size()==1){
    	    		assertTrue(true);//expected
    	    	}
    		}
    	}
    }

    //join with parent table's PK missing in SELECT (use relationship)
    public void test22_rel() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("test22");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    //join with child table's PK missing in SELECT (use relationship)
    public void test33_rel() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("test33");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    //join with both tables' PK missing in SELECT (use relationship)
    public void test44_rel() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("test44");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    public void testCompoundPks() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("compound_pks");
    	DataObject root = select.executeQuery();
    	assertEquals(4, root.getList("ORDERDETAILS").size());
    }

    public void testCompoundPksFail() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("compound_pks_fail");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    public void testCompoundPksJoin() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("compound_pks_join");
    	DataObject root = select.executeQuery();
    	List ordDetList = root.getList("ORDERDETAILS");
    	assertEquals(4, ordDetList.size());
    	for(int i=0; i<ordDetList.size(); i++){
    		DataObject curDO = (DataObject)ordDetList.get(i);
    		List ordDetDescList = curDO.getList("orderDetailsDesc");
    		if(curDO.getInt("ORDERID")==1 && curDO.getInt("PRODUCTID")==1 && ordDetDescList.size()==2){
    			assertTrue(true);//expected
    		}

    		if(curDO.getInt("ORDERID")==1 && curDO.getInt("PRODUCTID")==2 && ordDetDescList.size()==2){
    			assertTrue(true);//expected
    		}

    		if(curDO.getInt("ORDERID")==2 && curDO.getInt("PRODUCTID")==1 && ordDetDescList.size()==1){
    			assertTrue(true);//expected
    		}

    		if(curDO.getInt("ORDERID")==2 && curDO.getInt("PRODUCTID")==2 && ordDetDescList.size()==1){
    			assertTrue(true);//expected
    		}
    	}
    }

    public void testCompoundPksJoinFail() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPKREL.xml"), getConnection());
    	Command select = das.getCommand("compound_pks_join_fail");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    //case when no PK defined in Config but column ID is there in table and is not in SELECT clause
    //convention over configuration should assume it as PK and fail select
    public void testPartFail() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("testPartFail");
    	try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    public void testMultiSchemaPKMiss() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MultiSchemaDasConfig14.xml"), getConnection());

    	//Explicit compound key relationship between DASTEST1.ORDERDETAILS and DASTEST3.ORDERDETAILSDESC
    	//ORDERID <-> ORDERID, PRODUCTID<->PRODUCTID
    	// Read some order details and related order details descs
        Command select = das
                .createCommand("SELECT DASTEST1.ORDERDETAILS.ORDERID, DASTEST1.ORDERDETAILS.PRODUCTID," +
                		"DASTEST3.ORDERDETAILSDESC.ORDERID FROM DASTEST1.ORDERDETAILS LEFT JOIN DASTEST3.ORDERDETAILSDESC " +
                		" ON DASTEST1.ORDERDETAILS.ORDERID = DASTEST3.ORDERDETAILSDESC.ORDERID " +
                		" AND DASTEST1.ORDERDETAILS.PRODUCTID = DASTEST3.ORDERDETAILSDESC.PRODUCTID");

        try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }

    public void testTypePropsDifferent() throws Exception {
    	DAS das = DAS.FACTORY.createDAS(getConfig("MissingPK.xml"), getConnection());
    	Command select = das.getCommand("get all books");
        try{
    		select.executeQuery();
    		fail("Expected exception");
    	}catch(RuntimeException e){
    		if(e.getMessage().
    				indexOf("in query does not include Primary Key "+
    						"column or has null value in it, can not proceed!") >0){
    			assertTrue(true);//expected failure
    		}
    	}
    }
}
