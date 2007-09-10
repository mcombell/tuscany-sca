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
package org.apache.tuscany.das.rdb.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

import commonj.sdo.Type;

public class FactoryRegistry {

    private final Logger logger = Logger.getLogger(FactoryRegistry.class);

    private Map registry = new HashMap();

    private final MappingWrapper mapping;

    private final ConnectionImpl connection;

    public FactoryRegistry(MappingWrapper mapping, ConnectionImpl connection) {
        this.mapping = mapping;
        this.connection = connection;
    }

    public ChangeFactory getFactory(Type type) {
        ChangeFactory factory = (ChangeFactory) registry.get(type);
        if (factory == null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Creating new ChangeFactory for type " + type.getName());
            }

            factory = new ChangeFactory(mapping, connection);
            registry.put(type, factory);
        }
        return factory;
    }

}
