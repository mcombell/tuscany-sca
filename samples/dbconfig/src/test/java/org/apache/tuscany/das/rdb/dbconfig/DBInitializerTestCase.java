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

package org.apache.tuscany.das.rdb.dbconfig;

import junit.framework.TestCase;



/**
 * Tests the Converter framwork
 */
public class DBInitializerTestCase extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    public void testCreateDatabase() throws Exception {
        DBInitializer dbInitializer = new DBInitializer("dbConfig.xml");
        dbInitializer.initializeDatabase(true);
    }
    
    public void something() throws Exception {
//        try{            
//            //DBInitHelper dbInit = new DBInitHelper("DBConfig.xml");
//            DBInitializer dbInit = new DBInitializer(new FileInputStream("c:/DBConfig.xml"));
//            dbInit.initializeDatabase(true);
//            
//            System.out.println("check schema created:"+dbInit.isDatabaseReady());
//            System.out.println("check data created:"+dbInit.isDataCreated());
//            
//            dbInit.deleteData();
//            System.out.println("check data created after deleteData:"+dbInit.isDataCreated());
//            
//            dbInit.deleteSchema();
//            System.out.println("after deleteSchema check schema created:"+dbInit.isDatabaseReady());
//            
//            dbInit.createSchema(false);
//            System.out.println("check schema created after createSchema:"+dbInit.isDatabaseReady());
//            
//            dbInit.refreshData(true);
//            System.out.println("check data created after refreshData:"+dbInit.isDataCreated());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
}
