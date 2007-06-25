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
import java.util.List;

import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.graphbuilder.impl.GraphBuilderMetadata;
import org.apache.tuscany.das.rdb.graphbuilder.impl.ResultSetProcessor;
import org.apache.tuscany.sdo.util.SDOUtil;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;

public class ReadCommandImpl extends CommandImpl {

    private int startRow = 1;

    private int endRow = Integer.MAX_VALUE;   

    public ReadCommandImpl(String sqlString, MappingWrapper mapping, List resultDescriptor) {
        super(sqlString);
        this.configWrapper = mapping;
        
        if (resultDescriptor != null && !resultDescriptor.isEmpty()) {
            this.resultSetShape = new ResultSetShape(resultDescriptor, configWrapper.getConfig());//JIRA-952
        }
    }

   
    public void execute() {
        throw new UnsupportedOperationException();
    }

    public DataObject executeQuery() {

        if (statement.getConnection() == null) {
            throw new RuntimeException("A DASConnection object must be specified before executing the query.");
        }

        boolean success = false;
        try {
            List results = statement.executeQuery(parameters);
            success = true;
            return buildGraph(results);
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

    protected DataObject buildGraph(List results) throws SQLException {      

        // Before we use the mappingModel, do some checking/updating. If
        // inferrable information
        // isn't specified, add it in.

        GraphBuilderMetadata gbmd = new GraphBuilderMetadata(results, configWrapper.getConfig(),
                resultSetShape);

        // Create the DataGraph
        DataGraph g = SDOUtil.createDataGraph();

        // Create the root object       
        g.createRootObject(gbmd.getRootType());

        SDOUtil.registerDataGraphTypes(g, gbmd.getDefinedTypes());
        
        ChangeSummary summary = g.getChangeSummary();

        ResultSetProcessor rsp = new ResultSetProcessor(g.getRootObject(), gbmd);
        rsp.processResults(getStartRow(), getEndRow());

        summary.beginLogging();

        return g.getRootObject();
    }


    protected int getStartRow() {
        return startRow;
    }

    protected int getEndRow() {
        return endRow;
    }

    protected void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    protected void setEndRow(int endRow) {
        this.endRow = endRow;
    }


    protected void enablePaging() {
        statement.enablePaging();
    }

}
