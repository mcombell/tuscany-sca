/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.das.rdb.test.data;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tuscany.das.rdb.test.framework.TestData;


public class TypesData extends TestData {
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm.SSS");
	private static Timestamp timestamp = getTimestamp();
		
	private static Object[][] customerData = {
			{new Integer(1), timestamp, new Float(1234567.89), new Float(1234567.89)}
			};

	public TypesData(Connection connection) {
		super(connection, customerData);
	}
	
	public String getTableName() {
		return "TYPETEST";
	}

	
	//Utilities
	private static Date getDate() {
		
		try {
			return dateFormat.parse("1966-12-20 00:00:00.0");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}
	
	public static Timestamp getTimestamp() {
		
		return new Timestamp(getDate().getTime());
		
	}
	
}
