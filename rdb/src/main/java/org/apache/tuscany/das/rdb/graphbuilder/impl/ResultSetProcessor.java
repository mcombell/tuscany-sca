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
package org.apache.tuscany.das.rdb.graphbuilder.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import commonj.sdo.DataObject;

/**
 * 
 * A ResultSetProcessor is used to transform the data in a ResultSet into a set of inter-related EDataObjects.
 */
public class ResultSetProcessor {
    private final Logger logger = Logger.getLogger(ResultSetProcessor.class);

    private TableRegistry registry;

    private GraphBuilderMetadata metadata;

    private final DataObjectMaker doMaker;

    public ResultSetProcessor(DataObject g, GraphBuilderMetadata gbmd) {

        this.metadata = gbmd;
        if (metadata.getRelationships().size() == 0) {
            registry = new SingleTableRegistry();
        } else {
            registry = new MultiTableRegistry();
        }

        doMaker = new DataObjectMaker(g);

        if (this.logger.isDebugEnabled()) {
            this.logger.debug(metadata);
        }

    }

    /**
     * Process the ResultSet. For each row in the ResultSet, a
     * 
     * @link ResultSetRow object will be created to represent the row as a set of EDataObjects. Then, 
     * the relevant relationships will be constructed
     *       between each object in the
     * @link ResultSetRow.
     * 
     * @param start
     * @param end
     */
    public void processResults(int start, int end) throws SQLException {

        Iterator i = metadata.getResultMetadata().iterator();
        while (i.hasNext()) {
            ResultMetadata resultMetadata = (ResultMetadata) i.next();
            ResultSet results = resultMetadata.getResultSet();

            processResultSet(results, resultMetadata, start, end);

            // TODO These statements HAVE to be closed or we will have major problems
            // results.getStatement().close();
            results.close();
        }

    }

    private void processResultSet(ResultSet rs, ResultMetadata rsMetadata, int start, int end) throws SQLException {

        if (rs.getType() == ResultSet.TYPE_FORWARD_ONLY) {
            while (rs.next() && start < end) {
                ResultSetRow rsr = new ResultSetRow(rs, rsMetadata);
                addRowToGraph(rsr, rsMetadata);
                ++start;
            }
        } else {
            while (rs.absolute(start) && start < end) {
                ResultSetRow rsr = new ResultSetRow(rs, rsMetadata);
                addRowToGraph(rsr, rsMetadata);
                ++start;
            }
        }
    }

    /**
     * @param row
     * @param resultMetadata
     */
    private void addRowToGraph(ResultSetRow row, ResultMetadata resultMetadata) {
        RowObjects tableObjects = new RowObjects(metadata, registry);
        Iterator tables = row.getAllTableData().iterator();
        while (tables.hasNext()) {
            TableData rawDataFromRow = (TableData) tables.next();

            if (!rawDataFromRow.hasValidPrimaryKey()) {
                continue;
            }

            String tableName = rawDataFromRow.getTableName();
            DataObject tableObject = registry.get(tableName, rawDataFromRow.getPrimaryKeyValues());
            if (tableObject == null) {
                tableObject = doMaker.createAndAddDataObject(rawDataFromRow, resultMetadata);

                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Putting table " + tableName + " with PK " 
                            + rawDataFromRow.getPrimaryKeyValues() + " into registry");
                }

                registry.put(tableName, rawDataFromRow.getPrimaryKeyValues(), tableObject);
            }
            tableObjects.put(tableName, tableObject);
        }

        tableObjects.processRelationships();

    }

}
