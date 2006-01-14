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

import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.Type;

public class FactoryRegistry {

	private HashMap registry = new HashMap();
	private static final boolean debug = false;
	private final MappingWrapper mapping;
	private final ConnectionImpl connection;
	
	public FactoryRegistry(MappingWrapper mapping, ConnectionImpl connection) {
		this.mapping = mapping;
		this.connection = connection;
	}
	
	public ChangeFactory getFactory(Type type) {
		ChangeFactory factory = (ChangeFactory)registry.get(type);
		if ( factory == null )  {
			DebugUtil.debugln(getClass(), debug, "Creating new ChangeFactory for type " + type.getName());
			factory = new ChangeFactory(type, mapping, connection);
			registry.put(type, factory);
		}
		return factory;
	}
	

}
