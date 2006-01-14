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
import java.util.HashMap;
import java.util.Map;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandFactory;


public class CommandFactoryImpl implements CommandFactory {
	
	protected Map namedCommands = new HashMap();

	public Command createCommand(String sql) {
		return createCommand(sql, null);
	}

	public Command createCommand(String sql, InputStream model) {

		sql = sql.trim(); //Remove leading white space
		char firstChar = Character.toUpperCase(sql.charAt(0));
		switch (firstChar) {
		case 'S':
			return new ReadCommandImpl(sql, model);
		case 'I':
			return new InsertCommandImpl(sql);
		case 'U':
			return new UpdateCommandImpl(sql);
		case 'D':
			return new DeleteCommandImpl(sql);
		case '{':
			return new SPCommandImpl(sql);
		default:
			throw new Error("SQL => " + sql + " is not valid");
		}

	}

	public Command createReadCommand(String sql) {
		return new ReadCommandImpl(sql);
	}

	public Command createReadCommand(String sql, InputStream model) {
		return new ReadCommandImpl(sql, model);
	}

	public ApplyChangesCommand createApplyChangesCommand() {
		return new ApplyChangesCommandImpl();
	}

	public ApplyChangesCommand createApplyChangesCommand(
			InputStream mappingModel) throws IOException {
		return new ApplyChangesCommandImpl(mappingModel);
	}

	public Command getCommand (String name) {
		return (Command) namedCommands.get(name);
	}

	
}
