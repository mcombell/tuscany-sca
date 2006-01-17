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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

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
        	org.apache.tuscany.das.rdb.config.Command commandConfig = (org.apache.tuscany.das.rdb.config.Command) i.next();
            //TODO - add other possible command types
            commands.put(commandConfig.getName(), new ReadCommandImpl(commandConfig.getSQL(), config));
        }

    }


    /* (non-Javadoc)
     * @see org.apache.tuscany.das.rdb.CommandGroup#getApplyChangesCommand()
     */
    public ApplyChangesCommand getApplyChangesCommand() {
        ApplyChangesCommand cmd = new ApplyChangesCommandImpl(config);
        cmd.setConnection(connection);
        return cmd;
    }

    /* (non-Javadoc)
     * @see org.apache.tuscany.das.rdb.CommandGroup#getCommand(java.lang.String)
     */
    public Command getCommand(String name) {
        if (!commands.containsKey(name))
            throw new RuntimeException("CommandGroup has no command named: "+ name);
        Command cmd = (Command) commands.get(name);
        cmd.setConnection(connection);
        return cmd;
    }

    // Private

    private void setConfig(InputStream stream) {

        XMLResource resource = new XMLResourceImpl();
        HashMap map = new HashMap();
        ExtendedMetaData metadata = ExtendedMetaData.INSTANCE;
        metadata.putPackage(null, ConfigPackage.eINSTANCE);

        map.put(XMLResource.NO_NAMESPACE_SCHEMA_LOCATION, ConfigPackage.eNS_URI);
        map.put(XMLResource.OPTION_EXTENDED_META_DATA, metadata);

        try {
            resource.load(stream, map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        config = (Config) resource.getContents().get(0);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
        
    }

}
