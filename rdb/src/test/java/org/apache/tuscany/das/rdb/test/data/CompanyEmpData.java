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
package org.apache.tuscany.das.rdb.test.data;

import java.sql.Connection;

import org.apache.tuscany.das.rdb.test.framework.RelationshipData;


public class CompanyEmpData extends RelationshipData {

    protected static Object[][] data = {
        {"Mary Smith", "ACME Publishing" },
        {"Jane Doe", "Do-rite plumbing"},
        {"Al Smith", "MegaCorp"}};
    
    public CompanyEmpData(Connection c) {
        super(c, data);
    }

    protected String getParentRetrievalStatement() {
        return "select employee.id from employee where employee.name = ?";
    }

    protected String getChildUpdateStatement() {
        return "update company set company.eotmid = ? where company.name = ?";
    }

}
