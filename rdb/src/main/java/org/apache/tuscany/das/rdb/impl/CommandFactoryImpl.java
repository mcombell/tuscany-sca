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

import java.io.IOException;
import java.io.InputStream;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandFactory;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.helper.XMLHelper;

public class CommandFactoryImpl implements CommandFactory {

    public Command createCommand(String sql) {
        return baseCreateCommand(sql, null);
    }

    public Command createCommand(String sql, InputStream configStream) {
        return baseCreateCommand(sql, loadConfig(configStream));
    }

    public Command createCommand(String sql, Config config) {
        return baseCreateCommand(sql, config);
    }

    public ApplyChangesCommand createApplyChangesCommand() {
        return new ApplyChangesCommandImpl();
    }

    public ApplyChangesCommand createApplyChangesCommand(InputStream configStream) throws IOException {
        return new ApplyChangesCommandImpl(loadConfig(configStream));
    }

    public ApplyChangesCommand createApplyChangesCommand(Config config) {
        return new ApplyChangesCommandImpl(config);
    }

    // Utilities

    private Command baseCreateCommand(String sql, Config config) {

        sql = sql.trim(); // Remove leading white space
        char firstChar = Character.toUpperCase(sql.charAt(0));
        switch (firstChar) {
        case 'S':
            return new ReadCommandImpl(sql, config);
        case 'I':
            return new InsertCommandImpl(sql);
        case 'U':
            return new UpdateCommandImpl(sql);
        case 'D':
            return new DeleteCommandImpl(sql);
        case '{':
            return new SPCommandImpl(sql, config);
        default:
            throw new Error("SQL => " + sql + " is not valid");
        }

    }

    private Config loadConfig(InputStream configStream) {

        SDOUtil.registerStaticTypes(ConfigFactory.class);
        XMLHelper helper = XMLHelper.INSTANCE;

        try {
            Config config = (Config) helper.load(configStream).getRootObject();
            return config;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
