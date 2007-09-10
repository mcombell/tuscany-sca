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
package org.apache.tuscany.das.rdb.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.config.Parameter;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.TypeHelper;

public class SPCommandImpl extends ReadCommandImpl {
    private final Logger logger = Logger.getLogger(SPCommandImpl.class);
   
    public SPCommandImpl(String sqlString, MappingWrapper config, List params) {
        super(sqlString, config, null);
        Iterator i = params.iterator();
        while (i.hasNext()) {
            Parameter p = (Parameter) i.next();

            int index = p.getColumnType().lastIndexOf('.');
            String pkg = p.getColumnType().substring(0, index);
            String typeName = p.getColumnType().substring(index + 1);

            Type sdoType = TypeHelper.INSTANCE.getType(pkg, typeName);

            String direction = ParameterExtendedImpl.IN;
            if (ParameterExtendedImpl.OUT.equalsIgnoreCase(p.getDirection())) {
                direction = ParameterExtendedImpl.OUT;
            } else if (ParameterExtendedImpl.IN_OUT.equalsIgnoreCase(p.getDirection())) {
                direction = ParameterExtendedImpl.IN_OUT;
            }
            parameters.findOrCreateParameterWithIndex(p.getIndex(), direction, sdoType);
        }

    }

    public DataObject executeQuery() {

        boolean success = false;
        try {
            List results = statement.executeCall(parameters);
            success = true;

            return buildGraph(results);
        } catch (SQLException e) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug(e);
            }

            throw new RuntimeException(e);
        } finally {
            if (success) {
                statement.getConnection().cleanUp();
            } else {
                statement.getConnection().errorCleanUp();
            }
        }
    }

    public void execute() {

        boolean success = false;
        try {
            statement.executeUpdateCall(parameters);
            success = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (success) {
                statement.getConnection().cleanUp();
            } else {
                statement.getConnection().errorCleanUp();
            }
        }

    }

}
