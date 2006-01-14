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
package org.apache.tuscany.das.rdb.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO Restructure Logging
public class DebugUtil {

	private static StringBuffer buffer = new StringBuffer();
	private static final Logger logger = Logger.getAnonymousLogger();
	
	static {
		logger.setUseParentHandlers(false);
		Handler h = new ConsoleHandler();
		h.setFormatter(new DebugFormatter());
		logger.addHandler(h);
	}
	public static void debugln(Class c, boolean flag, Object obj) {

		if ( flag ) {
			logger.logp(Level.INFO, getClassName(c), null, obj.toString() );
		}
	}
	
	public static void debug(Class c, boolean flag, Object obj) {
		if ( flag ) {
			System.out.print("[" + getClassName(c) + "] " + obj );
		}
	}
	
	public static void buffer(boolean flag, Object obj) {
		if ( flag ) 
			buffer.append(obj);
	}
	
	public static void flushBuffer(Class c, boolean flag) {
		if ( flag ) {
			System.out.println("[" + getClassName(c) + "] " + buffer.toString());
			buffer = new StringBuffer();
		}
	}
	
	private static String getClassName(Class c) {
		String className = c.getName();
		return className.substring(className.lastIndexOf(".") + 1);
	}
	
}
