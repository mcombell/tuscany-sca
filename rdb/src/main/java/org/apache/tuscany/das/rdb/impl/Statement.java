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

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.util.DebugUtil;

//TODO - Can use some refactoring.  Much code is duplicated in "execute" methods
public class Statement {

    protected final QueryString queryString;

    protected ConnectionImpl jdbcConnection;

    private static final boolean debug = false;

    private PreparedStatement preparedStatement;

    private boolean isPaging = false;

    public Statement(String sqlString) {
        this.queryString = new QueryString(sqlString);
    }

    public ResultSet executeQuery(Parameters parameters) throws SQLException {

        PreparedStatement ps = getPreparedStatement();
        ps = setParameters(ps, parameters);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet executeCall(Parameters parameters) throws SQLException {
        try {
            CallableStatement cs = jdbcConnection.prepareCall(queryString);

            Iterator inParams = parameters.inParams().iterator();
            while (inParams.hasNext()) {
                ParameterImpl param = (ParameterImpl) inParams.next();
                if (param.getIndex() == 0)
                    param.setIndex(queryString.getParameterIndex(param.getName()));
                cs.setObject(param.getIndex(), param.getValue());
            }

            // register out parameters
            Iterator outParams = parameters.outParams().iterator();
            while (outParams.hasNext()) {
                ParameterImpl param = (ParameterImpl) outParams.next();
                if (param.getIndex() == 0)
                    param.setIndex(queryString.getParameterIndex(param.getName()));
                DebugUtil.debugln(getClass(), debug, "Registering parameter " + param.getName());
                cs.registerOutParameter(param.getIndex(), SDODataTypeHelper.sqlTypeFor(param.getType()));
            }

            // Using execute because Derby does not currenlty support
            // executeQuery
            // for SP
            cs.execute();
            ResultSet results = cs.getResultSet();

            Iterator i = parameters.outParams().iterator();
            while (i.hasNext()) {
                ParameterImpl param = (ParameterImpl) i.next();
                param.setValue(cs.getObject(param.getIndex()));
            }

            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public void executeUpdateCall(Parameters parameters) throws SQLException {
        CallableStatement cs = jdbcConnection.prepareCall(queryString);

        Iterator inParams = parameters.inParams().iterator();
        while (inParams.hasNext()) {
            ParameterImpl param = (ParameterImpl) inParams.next();
            if (param.getIndex() == 0)
                param.setIndex(queryString.getParameterIndex(param.getName()));
            cs.setObject(param.getIndex(), param.getValue());
        }

        // register out parameters
        Iterator outParams = parameters.outParams().iterator();
        while (outParams.hasNext()) {
            ParameterImpl param = (ParameterImpl) outParams.next();
            if (param.getIndex() == 0)
                param.setIndex(queryString.getParameterIndex(param.getName()));
            DebugUtil.debugln(getClass(), debug, "Registering parameter " + param.getName());
            cs.registerOutParameter(param.getIndex(), SDODataTypeHelper.sqlTypeFor(param.getType()));
        }

        cs.execute();

        Iterator out = parameters.outParams().iterator();
        while (out.hasNext()) {
            ParameterImpl param = (ParameterImpl) out.next();
            param.setValue(cs.getObject(param.getIndex()));
        }

    }

    /**
     * TODO - We need to look at using specific ps.setXXX methods when a type
     * has been specified and try setObject otherwise.
     */
    public int executeUpdate(Parameters parameters) throws SQLException {
        DebugUtil.debugln(getClass(), debug, "Executing statement " + queryString.getPreparedString());
        PreparedStatement ps = getPreparedStatement();
        Iterator i = parameters.inParams().iterator();
        while (i.hasNext()) {
            ParameterImpl param = (ParameterImpl) i.next();
           
            if (param.getIndex() == 0)
                param.setIndex(queryString.getParameterIndex(param.getName()));
            Object value = param.getValue();
            DebugUtil.debugln(getClass(), debug, "Setting parameter " + param.getIndex() + " to " + value);
            if (value == null) {            
                ps.setNull(param.getIndex(), SDODataTypeHelper.sqlTypeFor(param.getType()));
            } else {           
                ps.setObject(param.getIndex(), value);
            }
        }
        return ps.executeUpdate();
    }

    protected PreparedStatement setParameters(PreparedStatement ps, Parameters parameters)
            throws SQLException {
        Iterator i = parameters.inParams().iterator();
        while (i.hasNext()) {
            ParameterImpl param = (ParameterImpl) i.next();
            if (param.getIndex() == 0)
                param.setIndex(queryString.getParameterIndex(param.getName()));
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

    private PreparedStatement getPreparedStatement() throws SQLException {
        DebugUtil.debugln(getClass(), debug, "Getting prepared statement");
        if (preparedStatement == null)
            if (isPaging)
                preparedStatement = jdbcConnection.preparePagedStatement(queryString);
            else
                preparedStatement = jdbcConnection.prepareStatement(queryString);

        return preparedStatement;
    }

    public Integer getGeneratedKey() throws SQLException {

        ResultSet rs = getPreparedStatement().getGeneratedKeys();
        if (rs.next())
            return new Integer(rs.getInt(1));

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
