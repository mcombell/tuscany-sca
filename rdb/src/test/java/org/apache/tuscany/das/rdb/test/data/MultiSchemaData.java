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

package org.apache.tuscany.das.rdb.test.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
//JIRA-952
public class MultiSchemaData {
	Connection con;
	
	public MultiSchemaData(Connection connection){
		this.con = connection;
	}
	
    public void refresh() throws SQLException{
    	//System.out.println("Inserting data in multi schema tables");
	    	Statement s = this.con.createStatement();
	    	
	        String[] statements = {
	        "DELETE FROM DASTEST1.CUSTOMER",
	        "DELETE FROM DASTEST1.EMPLOYEE",
	        "DELETE FROM DASTEST1.CITY",
	        "DELETE FROM DASTEST1.ORDERDETAILS",
	        "DELETE FROM DASTEST2.CUSTOMER",
	        "DELETE FROM DASTEST2.CITY",
	        "DELETE FROM DASTEST2.ACCOUNT",
	        "DELETE FROM DASTEST3.CUSTOMER",
	        "DELETE FROM DASTEST3.CUSTORDER",
	        "DELETE FROM DASTEST3.ORDERDETAILSDESC",
	        
			"INSERT INTO DASTEST1.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (1,'Williams','USA')",
			"INSERT INTO DASTEST1.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (2,'Amita1','INDIA')",
			"INSERT INTO DASTEST1.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (3,'Patrick1','UK')",
			
			"INSERT INTO DASTEST1.EMPLOYEE (ID, LASTNAME, ADDRESS) VALUES (1,'Williams','USA')",
			"INSERT INTO DASTEST1.EMPLOYEE (ID, LASTNAME, ADDRESS) VALUES (2,'Amita1','INDIA')",
			"INSERT INTO DASTEST1.EMPLOYEE (ID, LASTNAME, ADDRESS) VALUES (3,'Patrick1','UK')",
			
			"INSERT INTO DASTEST2.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (1,'John2','USA')",
			"INSERT INTO DASTEST2.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (2,'Amita2','INDIA')",
			"INSERT INTO DASTEST2.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (3,'Patrick2','UK')",
		
			"INSERT INTO DASTEST3.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (1,'JohnAdm','USA')",
			"INSERT INTO DASTEST3.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (2,'AmitaAdm','INDIA')",
			"INSERT INTO DASTEST3.CUSTOMER (ID, LASTNAME, ADDRESS) VALUES (3,'PatrickAdm','UK')",
			
			"INSERT INTO DASTEST1.CITY (INDX, NAME) VALUES (1,'Fremont')",
			"INSERT INTO DASTEST1.CITY (INDX, NAME) VALUES (2,'Belmont')",
			
			"INSERT INTO DASTEST2.CITY (INDX, NAME) VALUES (1,'Milipitas')",
			"INSERT INTO DASTEST2.CITY (INDX, NAME) VALUES (2,'Newark')",
			
			"INSERT INTO DASTEST2.ACCOUNT (ACCOUNT_ID, CUSTOMER_ID, BALANCE) VALUES (10, 1, 101)",
			"INSERT INTO DASTEST2.ACCOUNT (ACCOUNT_ID, CUSTOMER_ID, BALANCE) VALUES (20, 1, 202)",
			"INSERT INTO DASTEST2.ACCOUNT (ACCOUNT_ID, CUSTOMER_ID, BALANCE) VALUES (30, 2, 303)",
			"INSERT INTO DASTEST2.ACCOUNT (ACCOUNT_ID, CUSTOMER_ID, BALANCE) VALUES (40, 2, 404)",
		
			"INSERT INTO DASTEST3.CUSTORDER (ORDER_ID, CUSTOMER_ID, ORDER_COUNT) VALUES (100,1,150)",
			"INSERT INTO DASTEST3.CUSTORDER (ORDER_ID, CUSTOMER_ID, ORDER_COUNT) VALUES (200,1,250)",
			"INSERT INTO DASTEST3.CUSTORDER (ORDER_ID, CUSTOMER_ID, ORDER_COUNT) VALUES (300,2,150)",
			"INSERT INTO DASTEST3.CUSTORDER (ORDER_ID, CUSTOMER_ID, ORDER_COUNT) VALUES (400,2,250)",
			
			"INSERT INTO DASTEST1.ORDERDETAILS (ORDERID, PRODUCTID, PRICE) VALUES (1,1,101)",
			"INSERT INTO DASTEST1.ORDERDETAILS (ORDERID, PRODUCTID, PRICE) VALUES (1,2,102)",
			"INSERT INTO DASTEST1.ORDERDETAILS (ORDERID, PRODUCTID, PRICE) VALUES (2,1,201)",
			"INSERT INTO DASTEST1.ORDERDETAILS (ORDERID, PRODUCTID, PRICE) VALUES (2,2,202)",
			"INSERT INTO DASTEST3.ORDERDETAILSDESC (ID, ORDERID, PRODUCTID, DESCR) VALUES (10,1,1, 'DESC 10,1,1')",
			"INSERT INTO DASTEST3.ORDERDETAILSDESC (ID, ORDERID, PRODUCTID, DESCR) VALUES (20,1,1, 'DESC 20,1,1')",
			"INSERT INTO DASTEST3.ORDERDETAILSDESC (ID, ORDERID, PRODUCTID, DESCR) VALUES (30,1,2, 'DESC 30,1,2')",
			"INSERT INTO DASTEST3.ORDERDETAILSDESC (ID, ORDERID, PRODUCTID, DESCR) VALUES (40,1,2, 'DESC 40,1,2')",
			"INSERT INTO DASTEST3.ORDERDETAILSDESC (ID, ORDERID, PRODUCTID, DESCR) VALUES (50,2,1, 'DESC 50,2,1')",
			"INSERT INTO DASTEST3.ORDERDETAILSDESC (ID, ORDERID, PRODUCTID, DESCR) VALUES (60,2,2, 'DESC 60,2,2')"
	        };
	        
	        for (int i = 0; i < statements.length; i++) {
	                s.execute(statements[i]);
	        }
	        s.close();
        //System.out.println("Multi schema database setup complete!");
    }
}
