package org.apache.tuscany.das.rdb;

import java.io.InputStream;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.impl.ConfigFactoryImpl;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

public class ConfigHelper {

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

    public Config getConfig() {
        return config;
    }

}
