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

import junit.framework.Test;

public class DB2Setup extends DatabaseSetup {

    public DB2Setup(Test test) {
        super(test);
    }

    protected void initConnectionProtocol() {

        platformName = "DB2";
        driverName = "com.ibm.db2.jcc.DB2Driver";
        databaseURL = "jdbc:db2:DASTEST";

    }

}
