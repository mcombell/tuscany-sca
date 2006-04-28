package org.apache.tuscany.das.rdb.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.helper.XMLHelper;

/**
 * Config util provides config-related utilities such as loading a Config
 * instance from an InputStream
 * 
 */
public class ConfigUtil {

    public static Config loadConfig(InputStream configStream) {

        if (configStream == null)
            throw new Error(
                    "Cannot load configuration from a null InputStream. Possibly caused by an incorrect config xml file name");

        SDOUtil.registerStaticTypes(ConfigFactory.class);
        XMLHelper helper = XMLHelper.INSTANCE;

        try {
            return (Config) helper.load(configStream).getRootObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
