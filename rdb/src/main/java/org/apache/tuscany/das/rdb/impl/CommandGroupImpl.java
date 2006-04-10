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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl;

import commonj.sdo.helper.XMLHelper;

/**
 * An ConfiguredCommandFactory produces instances of Command and
 * ApplyChangesCommand. This factory is initialized with a configuration that
 * defines the commands it produces.
 * 
 * <p>
 * TODO - This is still underdevelopment and is likely to replace CommandFactory
 * as the preferred mechanism to set commands at runtime.
 * 
 * 
 */
public class CommandGroupImpl implements CommandGroup {

    private Config config;

    private Connection connection;

    private Map commands = new HashMap();

    public CommandGroupImpl(InputStream stream) {
        super();
        setConfig(stream);
        initialize();
    }

    private void initialize() {

        Iterator i = config.getCommand().iterator();
        while (i.hasNext()) {
            org.apache.tuscany.das.rdb.config.Command commandConfig = (org.apache.tuscany.das.rdb.config.Command) i
                    .next();
            String kind = commandConfig.getKind();
            if (kind.equalsIgnoreCase("select"))
                commands.put(commandConfig.getName(), new ReadCommandImpl(commandConfig.getSQL(), config));
            else if (kind.equalsIgnoreCase("update"))
                commands.put(commandConfig.getName(), new UpdateCommandImpl(commandConfig.getSQL()));
            else if (kind.equalsIgnoreCase("insert"))
                commands.put(commandConfig.getName(), new InsertCommandImpl(commandConfig.getSQL()));
            else if (kind.equalsIgnoreCase("delete"))
                commands.put(commandConfig.getName(), new DeleteCommandImpl(commandConfig.getSQL()));
            else
                throw new Error("Invalid kind of command: " + kind);

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.tuscany.das.rdb.CommandGroup#getApplyChangesCommand()
     */
    public ApplyChangesCommand getApplyChangesCommand() {
        ApplyChangesCommand cmd = new ApplyChangesCommandImpl(config);
        cmd.setConnection(getConnection());
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

    // Private

    private void setConfig(InputStream stream) {
        XMLHelper helper = XMLHelper.INSTANCE;

        ConfigPackageImpl impl = ConfigPackageImpl.eINSTANCE;

        try {
            config = (Config) helper.load(stream).getRootObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        ConnectionProperties cp = config.getConnectionProperties();
        if (cp == null)
            throw new Error(
                    "No connection properties have been configured and no connection has been provided");

        if (cp.getDataSource() != null)
            initViaDataSource(cp);
        else
            initViaDriverManager(cp);

    }

    private void initViaDriverManager(ConnectionProperties cp) {
        Connection connection = null;

        try {
            Class.forName(cp.getDriverClassName());
            if (cp.getDriverUserName() == null)
                connection = DriverManager.getConnection(cp.getDriverURL());
            else
                connection = DriverManager.getConnection(cp.getDriverURL(), cp.getDriverUserName(), cp
                        .getDriverPassword());
            connection.setAutoCommit(false);
            setConnection(connection);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    // TODO - Refactor to eliminate common initialization code after connection
    // is got
    private void initViaDataSource(ConnectionProperties cp) {

        Connection connection = null;

        InitialContext ctx;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            throw new Error(e);
        }
        try {
            // TODO - I think we should rename this getDataSourceURL?
            DataSource ds = (DataSource) ctx.lookup(cp.getDataSource());
            try {
                connection = ds.getConnection();
                connection.setAutoCommit(false);
                setConnection(connection);
            } catch (SQLException e) {
                throw new Error(e);
            }
        } catch (NamingException e) {
            throw new Error(e);
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
     * If the config has connection properties then we are "manageing" the
     * connection via DriverManager or DataSource
     */
    private boolean managingConnections() {

        if (config.getConnectionProperties() == null)
            return false;
        else
            return true;

    }

}