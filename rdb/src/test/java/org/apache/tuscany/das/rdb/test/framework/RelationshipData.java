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
package org.apache.tuscany.das.rdb.test.framework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RelationshipData {
    protected Object[][] data;
    
    protected Connection connection;
    
    private int currentRow = -1;

    public RelationshipData(Connection c, Object[][] inputData) {
        this.connection = c;
        this.data = inputData;
    }

    public int size() {
        return data[0].length;
    }

    public int numberOfRows() {
        return data.length;
    }

    public boolean next() {
        ++currentRow;
        return currentRow < numberOfRows();
    }

    public void refresh() throws SQLException {
        updateRelationships();
    }

    protected abstract String getParentRetrievalStatement();

    protected abstract String getChildUpdateStatement();

    protected void updateRelationships() throws SQLException {
        // { MegaCorp, Advanced Technologies }
        // select company.id from company where company.name = ?
        PreparedStatement retrieveParent = connection.prepareStatement(getParentRetrievalStatement());
        // update department set department.companyid = ? where department.name = ?
        PreparedStatement updateChild = connection.prepareStatement(getChildUpdateStatement());

        while (next()) {
            retrieveParent.setObject(1, data[currentRow][0]);
            retrieveParent.execute();
            ResultSet rs = retrieveParent.getResultSet();
            rs.next();
            Object parentID = rs.getObject(1);
            retrieveParent.clearParameters();

            updateChild.setObject(1, parentID);
            updateChild.setObject(2, data[currentRow][1]);
            updateChild.execute();
            updateChild.clearParameters();
        }
    }
}
