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
package org.apache.tuscany.das.rdb.generator.impl;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.impl.DeleteCommandImpl;
import org.apache.tuscany.das.rdb.impl.ParameterExtendedImpl;
import org.apache.tuscany.das.rdb.impl.SDODataTypeHelper;
import org.apache.tuscany.das.rdb.impl.SDODataTypes;

public final class DeleteGenerator extends BaseGenerator {

    public static final DeleteGenerator INSTANCE = new DeleteGenerator();

    private final Logger logger = Logger.getLogger(DeleteGenerator.class);

    private DeleteGenerator() {
        super();
    }

    //JIRA-952
    private String getDeleteStatement(MappingWrapper mapping, Table t) {
        TableWrapper table = new TableWrapper(t);

        StringBuffer statement = new StringBuffer();
        statement.append("delete from ");
        if(mapping.getConfig().isDatabaseSchemaNameSupported()){
        	statement.append(t.getSchemaName()+"."+t.getTableName());
        }
        else{
        statement.append(t.getTableName());
        }
        statement.append(" where ");

        Iterator names = table.getPrimaryKeyNames().iterator();
        Iterator properties = table.getPrimaryKeyProperties().iterator();
        while (names.hasNext() && properties.hasNext()) {
            String name = (String) names.next();
            statement.append(name);
            statement.append(" = ?");
            if (names.hasNext() && properties.hasNext()) {
                statement.append(" and ");
            }
        }

        if (this.logger.isDebugEnabled()) {
            this.logger.debug(statement.toString());
        }

        return statement.toString();
    }

    public DeleteCommandImpl getDeleteCommand(MappingWrapper mapping, Table t) {
        TableWrapper tw = new TableWrapper(t);
        DeleteCommandImpl deleteCommand = new DeleteCommandImpl(getDeleteStatement(mapping, t));

        Iterator i = tw.getPrimaryKeyProperties().iterator();
        for (int idx = 1; i.hasNext(); idx++) {
            String property = (String) i.next();
            ParameterExtendedImpl p = new ParameterExtendedImpl();
            p.setName(property);
            p.setColumnType(SDODataTypeHelper.columnTypeForSDOType(SDODataTypes.OBJECT));
            p.setConverter(getConverter(tw.getConverter(property)));
            p.setIndex(idx);
            deleteCommand.addParameter(p);
        }
        return deleteCommand;
    }

}
