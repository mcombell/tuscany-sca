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

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.ConfigHelper;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.test.data.CompanyData;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class CheckSupportGeneratedKeys extends DasTest {
    protected void setUp() throws Exception {
        super.setUp();

        new CustomerData(getAutoConnection()).refresh();//no generated keys used
        new CompanyData(getAutoConnection()).refresh();//generated keys used
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    //<Config> useGetGeneratedKeys set to TRUE - Company
    public void testInsert() throws Exception {
    	ConfigHelper helper = new ConfigHelper();
        Config config = helper.getConfig();
        config.setGeneratedKeysSupported("true");
        
        DAS das = DAS.FACTORY.createDAS(config, getConnection());
        Command insert = das.createCommand("insert into COMPANY (NAME) values (?)");
        insert.setParameter(1, "AAA Rental");
        insert.execute();
        Integer key = new Integer(insert.getGeneratedKey());
        // Verify insert
        Command select = das.createCommand("Select ID, NAME from COMPANY where ID = ?");
        select.setParameter(1, key);
        DataObject root = select.executeQuery();
        assertEquals(key, root.get("COMPANY[1]/ID"));
    }

    //<Config> useGetGeneratedKeys not set - Company (should work as default TRUE)
    public void testInsert1() throws Exception {
        ConfigHelper helper = new ConfigHelper();
        Config config = helper.getConfig();
    	
        DAS das = DAS.FACTORY.createDAS(config, getConnection());
        Command insert = das.createCommand("insert into COMPANY (NAME) values (?)");
        insert.setParameter(1, "AAA Rental");
        insert.execute();
        Integer key = new Integer(insert.getGeneratedKey());
        // Verify insert
        Command select = das.createCommand("Select ID, NAME from COMPANY where ID = ?");
        select.setParameter(1, key);
        DataObject root = select.executeQuery();
        assertEquals(key, root.get("COMPANY[1]/ID"));
    }
    
    //<Config> useGetGeneratedKeys set to FALSE - Company - 
    // exception as insert.getGen..Keys will throw RuntimeException
    public void testInsert2() throws Exception {
        ConfigHelper helper = new ConfigHelper();
        Config config = helper.getConfig();
        config.setGeneratedKeysSupported("false");

        DAS das = DAS.FACTORY.createDAS(config, getConnection());
        Command insert = das.createCommand("insert into COMPANY (NAME) values (?)");
        insert.setParameter(1, "AAA Rental");
        insert.execute();//this will happen as table is created with proper clause
                
        try{
        	Integer key = new Integer(insert.getGeneratedKey());
        	fail("Should not be able to retrieve key "+key);//unexpected
        }catch(RuntimeException e){
        	//e.printStackTrace();
        	assertTrue(true);//expected
        }
    }
    
    //<Config> useGetGeneratedKeys set to TRUE - Customer - flag should be ignored during insert
    public void testInsert3() throws Exception {
        ConfigHelper helper = new ConfigHelper();
        Config config = helper.getConfig();
        config.setGeneratedKeysSupported("true");
    	
        DAS das = DAS.FACTORY.createDAS(config, getConnection());
        Command insert = das.createCommand("insert into CUSTOMER (ID, LASTNAME, ADDRESS) values (?, ?, ?)");
        String key = "10";
        insert.setParameter(1, key);
        insert.setParameter(2, "CUST10");
        insert.setParameter(3, "ADDR10");
        insert.execute();
        
        // Verify insert
        Command select = das.createCommand("Select ID, LASTNAME from CUSTOMER where ID = ?");
        select.setParameter(1, key);
        DataObject root = select.executeQuery();
        assertEquals(key, root.get("CUSTOMER[1]/ID").toString());
    }
    
    //<Config> useGetGeneratedKeys set to FALSE - Customer, flag has no effect
    public void testInsert4() throws Exception {
        ConfigHelper helper = new ConfigHelper();
        Config config = helper.getConfig();
        config.setGeneratedKeysSupported("false");
    	
        DAS das = DAS.FACTORY.createDAS(config, getConnection());
        Command insert = das.createCommand("insert into CUSTOMER (ID, LASTNAME, ADDRESS) values (?, ?, ?)");
        String key = "10";
        insert.setParameter(1, key);
        insert.setParameter(2, "CUST10");
        insert.setParameter(3, "ADDR10");
        insert.execute();
        try{
        	Integer retkey = new Integer(insert.getGeneratedKey());
        	fail("Should not be able to retrieve key "+retkey);
        }catch(RuntimeException e){
        	//e.printStackTrace();
        	assertTrue(true);//expected
        }
        
        // Verify insert
        Command select = das.createCommand("Select ID, LASTNAME from CUSTOMER where ID = ?");
        select.setParameter(1, key);
        DataObject root = select.executeQuery();
        assertEquals(key, root.get("CUSTOMER[1]/ID").toString());
    }
    
    //<Config> useGetGeneratedKeys not set - Customer - default TRUE is ignored
    public void testInsert5() throws Exception {
        ConfigHelper helper = new ConfigHelper();
        Config config = helper.getConfig();
    	
        DAS das = DAS.FACTORY.createDAS(config, getConnection());
        Command insert = das.createCommand("insert into CUSTOMER (ID, LASTNAME, ADDRESS) values (?, ?, ?)");
        String key = "10";
        insert.setParameter(1, key);
        insert.setParameter(2, "CUST10");
        insert.setParameter(3, "ADDR10");
        insert.execute();
        
        // Verify insert
        Command select = das.createCommand("Select ID, LASTNAME from CUSTOMER where ID = ?");
        select.setParameter(1, key);
        DataObject root = select.executeQuery();
        assertEquals(key, root.get("CUSTOMER[1]/ID").toString());
    }
}
