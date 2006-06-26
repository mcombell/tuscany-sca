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
package org.apache.tuscany.das.rdb.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tuscany.das.rdb.util.DebugUtil;


public class QueryString {

	private final String unmodifiedQueryString;
	private final String preparedString;
	private HashMap parameters = new HashMap();
	private static final boolean debug = false;
	
	public QueryString(String originalString) {
		this.unmodifiedQueryString = originalString;
		this.preparedString = replaceNamesAndSetIndexes(originalString);	
	}

	public String getPreparedString() {
		return this.preparedString;
	}
	
	public String getUnmodifiedString() {
		return this.unmodifiedQueryString;
	}
	
	public int getParameterIndex(String name) {
		DebugUtil.debugln(getClass(), debug, "Looking for parameter index for: " + name);
		return ((Integer)parameters.get(name)).intValue();
	}
	
	private String replaceNamesAndSetIndexes(String query) {
		DebugUtil.debugln(getClass(), debug, "Parameterizing query: " + query);
		Pattern p = Pattern.compile(":[\\S&&[^,()]]*");
		Matcher m = p.matcher(query);

		int index = 1;
		while (m.find()) {
			parameters.put(m.group().substring(1), new Integer(index));
			query = m.replaceFirst("?");
			m = p.matcher(query);
			index++;
		}
		
		DebugUtil.debugln(getClass(), debug, "Parameterized query: " + query);
		return query;
	}

	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nOriginal SQL: ");
		buffer.append(unmodifiedQueryString);
		buffer.append("\nPrepared SQL: ");
		buffer.append(preparedString);
		buffer.append("\nParameters: ");
		Iterator i = parameters.keySet().iterator();
		while ( i.hasNext() ) {
			String key = (String)i.next();
			buffer.append("\n");
			buffer.append(key);
		}
		return buffer.toString();
	}

}
