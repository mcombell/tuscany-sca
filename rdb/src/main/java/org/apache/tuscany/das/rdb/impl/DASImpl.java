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

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.util.ConfigUtil;

import commonj.sdo.DataObject;

/**
 * An ConfiguredCommandFactory produces instances of Command and
 * ApplyChangesCommand. This factory is initialized with a configuration that
 * defines the commands it produces.
 * 
 */
public class DASImpl implements DAS {

    private Config config;

    private Connection connection;

    private Map commands = new HashMap();

    public DASImpl(InputStream stream) {
        this(ConfigUtil.loadConfig(stream));
        
    }

    public DASImpl(Config inConfig) {
    	this.config = inConfig;
        
        Iterator i = config.getCommand().iterator();
        while (i.hasNext()) {
            org.apache.tuscany.das.rdb.config.Command commandConfig = (org.apache.tuscany.das.rdb.config.Command) i
                    .next();
            String kind = commandConfig.getKind();
            if (kind.equalsIgnoreCase("select"))                             
                commands.put(commandConfig.getName(), new ReadCommandImpl(commandConfig.getSQL(), config, commandConfig.getResultDescriptor()));
            else if (kind.equalsIgnoreCase("update"))
                commands.put(commandConfig.getName(), new UpdateCommandImpl(commandConfig.getSQL()));
            else if (kind.equalsIgnoreCase("insert"))
                commands.put(commandConfig.getName(), new InsertCommandImpl(commandConfig.getSQL()));
            else if (kind.equalsIgnoreCase("delete"))
                commands.put(commandConfig.getName(), new DeleteCommandImpl(commandConfig.getSQL()));
            else
                throw new RuntimeException("Invalid kind of command: " + kind);

        }

    }

    public DASImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
     * (non-Javadoc)
     * 
     * @see org.apache.tuscany.das.rdb.CommandGroup#getApplyChangesCommand()
     */
    public ApplyChangesCommandImpl getApplyChangesCommand() {
        ApplyChangesCommandImpl cmd = new ApplyChangesCommandImpl(config, connection);
        return cmd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.tuscany.das.rdb.CommandGroup#getCommand(java.lang.String)
     */
    public Command getCommand(String name) {
        if (!commands.containsKey(name))
            throw new RuntimeException("CommandGroup has no command named: " + name);
        Command cmd = (Command) commands.get(name);
        cmd.setConnection(getConnection());
        return cmd;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        if (connection == null)
            initializeConnection();
        return connection;
    }

    private void initializeConnection() {

        String dataSource = config.getDataSource();
        if (dataSource == null)
            throw new RuntimeException(
                    "No connection has been provided and no data source has been specified");

        Connection connection = null;

        InitialContext ctx;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        try {
            // TODO - I think we should rename this getDataSourceURL?
            DataSource ds = (DataSource) ctx.lookup(dataSource);
            try {
                connection = ds.getConnection();
                connection.setAutoCommit(false);
                setConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    public void releaseResources() {

        if (managingConnections())
            closeConnection();
    }

    private void closeConnection() {
        if (connection != null)
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * If the config has connection properties then we are "managing" the
     * connection via DataSource
     */
    private boolean managingConnections() {

        if (config.getDataSource() == null)
            return false;
        else
            return true;

    }

    public Command createCommand(String sql) {
        return baseCreateCommand(sql, this.config);
    }

    public Command createCommand(String sql, InputStream configStream) {
        return baseCreateCommand(sql, ConfigUtil.loadConfig(configStream));
    }

    public Command createCommand(String sql, Config config) {
        return baseCreateCommand(sql, config);
    }

    private Command baseCreateCommand(String sql, Config config) {

        sql = sql.trim(); // Remove leading white space
        char firstChar = Character.toUpperCase(sql.charAt(0));
        switch (firstChar) {
        case 'S':
            return new ReadCommandImpl(sql, config, null);
        case 'I':
            return new InsertCommandImpl(sql);
        case 'U':
            return new UpdateCommandImpl(sql);
        case 'D':
            return new DeleteCommandImpl(sql);
        case '{':
            return new SPCommandImpl(sql, config);
        default:
            throw new RuntimeException("SQL => " + sql + " is not valid");
        }

    }

	public void applyChanges(DataObject root) {
		getApplyChangesCommand().execute(root);			
	}

}