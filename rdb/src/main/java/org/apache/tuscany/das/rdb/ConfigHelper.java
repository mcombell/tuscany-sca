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
package org.apache.tuscany.das.rdb;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.impl.ConfigFactoryImpl;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

/**
 * A ConfigHelper is used as an aid in programmatic construction of Config instances.
 * Manual contrution fo COnfig is an alternative to providing needed configuration
 * information in an XML file
 * 
 */public class ConfigHelper {

    private Config config;

    private MappingWrapper configWrapper;

    private ConfigFactory factory = ConfigFactoryImpl.eINSTANCE;

    public ConfigHelper() {
        config = factory.createConfig();
        configWrapper = new MappingWrapper(config);
    }

    public ConfigHelper(Config config) {
        this.config = config;
        configWrapper = new MappingWrapper(config);
    }

    public Config newInstance() {
        return factory.createConfig();
    }

    public void addPrimaryKey(String columnName) {
        configWrapper.addPrimaryKey(columnName);
    }
    
    public void addRelationship(String parentName, String childName) {
        configWrapper.addRelationship(parentName, childName);
    }
    
    public void addTable(String name, String propertyName) {
        configWrapper.addTable(name, propertyName);
    }
    
    public Config getConfig() {
        return config;
    }

}
