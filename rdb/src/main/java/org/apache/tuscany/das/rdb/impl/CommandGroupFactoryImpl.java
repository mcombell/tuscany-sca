package org.apache.tuscany.das.rdb.impl;

import java.io.InputStream;

import org.apache.tuscany.das.rdb.CommandGroup;
import org.apache.tuscany.das.rdb.CommandGroupFactory;

public class CommandGroupFactoryImpl implements CommandGroupFactory {

    
    public CommandGroup createCommandGroup(InputStream configStream) {
        return new CommandGroupImpl(configStream);
    }

}
