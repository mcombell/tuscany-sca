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

import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

// TODO - Can use some refactoring. Much code is duplicated in "execute" methods
public class Statement {

    protected final String queryString;

    protected ConnectionImpl jdbcConnection;

    private final Logger logger = Logger.getLogger(Statement.class);
    
    private PreparedStatement preparedStatement;

    private boolean isPaging;

    public Statement(String sqlString) {
        this.queryString = sqlString;
    }

    public List executeQuery(Parameters parameters) throws SQLException {

        PreparedStatement ps = getPreparedStatement(new String[0]);
        ps = setParameters(ps, parameters);
        ResultSet rs = ps.executeQuery();

        return Collections.singletonList(rs);
    }

    public List executeCall(Parameters parameters) throws SQLException {

        CallableStatement cs = jdbcConnection.prepareCall(queryString);

        Iterator inParams = parameters.inParams().iterator();
        while (inParams.hasNext()) {
            ParameterImpl param = (ParameterImpl) inParams.next();
            cs.setObject(param.getIndex(), param.getValue());
        }

        // register out parameters
        Iterator outParams = parameters.outParams().iterator();
        while (outParams.hasNext()) {
            ParameterImpl param = (ParameterImpl) outParams.next();
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Registering parameter " + param.getName());
            }

            cs.registerOutParameter(param.getIndex(), SDODataTypeHelper.sqlTypeFor(param.getType()));
        }

        // Using execute because Derby does not currenlty support
        // executeQuery
        // for SP
        cs.execute();
        List results = new ArrayList();
        results.add(cs.getResultSet());
        while (cs.getMoreResults(java.sql.Statement.KEEP_CURRENT_RESULT)) {
            results.add(cs.getResultSet());
        }

        Iterator i = parameters.outParams().iterator();
        while (i.hasNext()) {
            ParameterImpl param = (ParameterImpl) i.next();
            param.setValue(cs.getObject(param.getIndex()));
        }

        return results;

    }

    public void executeUpdateCall(Parameters parameters) throws SQLException {
        CallableStatement cs = jdbcConnection.prepareCall(queryString);

        Iterator inParams = parameters.inParams().iterator();
        while (inParams.hasNext()) {
            ParameterImpl param = (ParameterImpl) inParams.next();
            cs.setObject(param.getIndex(), param.getValue());
        }

        // register out parameters
        Iterator outParams = parameters.outParams().iterator();
        while (outParams.hasNext()) {
            ParameterImpl param = (ParameterImpl) outParams.next();

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Registering parameter " + param.getName());
            }

            cs.registerOutParameter(param.getIndex(), SDODataTypeHelper.sqlTypeFor(param.getType()));
        }

        cs.execute();

        Iterator out = parameters.outParams().iterator();
        while (out.hasNext()) {
            ParameterImpl param = (ParameterImpl) out.next();
            param.setValue(cs.getObject(param.getIndex()));
        }

    }

    public int executeUpdate(Parameters parameters, String[] generatedKeys) throws SQLException {
        return executeUpdate(getPreparedStatement(generatedKeys), parameters);
    }

    public int executeUpdate(Parameters parameters) throws SQLException {
        return executeUpdate(parameters, new String[0]);
    }

    /**
     * TODO - We need to look at using specific ps.setXXX methods when a type
     * has been specified and try setObject otherwise.
     */
    private int executeUpdate(PreparedStatement ps, Parameters parameters) throws SQLException {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Executing statement " + queryString);
        }

        Iterator i = parameters.inParams().iterator();
        while (i.hasNext()) {
            ParameterImpl param = (ParameterImpl) i.next();

            Object value = param.getValue();
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Setting parameter " + param.getIndex() + " to " + value);
            }

            if (value == null) {
                if (param.getType() == null) {
                    try {
                        ParameterMetaData pmd = ps.getParameterMetaData();
                        ps.setNull(param.getIndex(), pmd.getParameterType(param.getIndex()));
                    } catch (SQLException ex) {
                        ps.setNull(param.getIndex(), SDODataTypeHelper.sqlTypeFor(null));
                    }
                } else {
                    ps.setNull(param.getIndex(), SDODataTypeHelper.sqlTypeFor(param.getType()));
                }
            } else {
                ps.setObject(param.getIndex(), value);
            }
        }
        return ps.executeUpdate();
    }

    protected PreparedStatement setParameters(PreparedStatement ps, Parameters parameters) throws SQLException {
        Iterator i = parameters.inParams().iterator();
        while (i.hasNext()) {
            ParameterImpl param = (ParameterImpl) i.next();
            ps.setObject(param.getIndex(), param.getValue());
        }
        return ps;
    }

    public void setConnection(ConnectionImpl jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public ConnectionImpl getConnection() {
        return this.jdbcConnection;
    }

    private PreparedStatement getPreparedStatement(String[] returnKeys) throws SQLException {

        if (preparedStatement == null) {
            if (isPaging) {
                preparedStatement = jdbcConnection.preparePagedStatement(queryString);
            } else {
                preparedStatement = jdbcConnection.prepareStatement(queryString, returnKeys);
            }
        }

        return preparedStatement;
    }

    public Integer getGeneratedKey() throws SQLException {

        if (getConnection().useGetGeneratedKeys()) {
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return Integer.valueOf(rs.getInt(1));
            }
        }

        return null;
    }

    protected void enablePaging() {
        isPaging = true;
    }

    public void close() {

        if (this.preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
