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

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 * 
 */
public class DasTest extends TestCase {

    protected static Connection connection;

    /**
     * Tears down the fixture, for example, close a network connection. This method is called after a test is executed.
     */
    protected void tearDown() throws Exception {
        // if (usingDefaultSetup)
        // connection = null;
    }

    protected Connection getAutoConnection() throws SQLException {

        Connection c = primGetConnection();
        c.setAutoCommit(true);
        return connection;

    }

    protected Connection getConnection() throws SQLException {

        Connection c = primGetConnection();
        c.setAutoCommit(false);
        return connection;
    }

    /**
     * This provides the default connection for runing single test cases on a chosen platform.
     */
    private Connection primGetConnection() {
        if (connection == null) {
            defaultSetup();
        }
        return connection;
    }

    /**
     * This is a bit of a hack since it counts on constructor initialization 
     * of the DatabaseSet up class and also calls its setUp method directly.
     * This is a misuse of this JUnit TestSetup subclass .
     * 
     * TODO - refactor to avoid this hackiness ... could move this logic to its 
     * own class that is then invoked by DatabaseSetUp
     */
    private void defaultSetup() {

        // DatabaseSetup setUp = new DB2Setup(this);
        DatabaseSetup setUp = new DerbySetup(this);
        try {
            setUp.setUp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Utilities
    protected InputStream getConfig(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    protected void write(String label, ResultSet rs) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        int count = md.getColumnCount();
        System.out.println("Contents of ResultSet from " + label);
        for (int i = 1; i <= count; i++) {
            System.out.print("\t");
            System.out.println(md.getColumnLabel(i));
        }
        System.out.println("");
        while (rs.next()) {
            for (int i = 1; i <= count; i++) {
                System.out.print("\t");
                System.out.print(rs.getString(i));
            }
            System.out.println("\t");
        }
        System.out.println("done");
    }

    protected void printList(List data) {
        Iterator i = data.iterator();
        while (i.hasNext()) {
            System.out.println();
            DataObject obj = (DataObject) i.next();
            Iterator props = obj.getType().getProperties().iterator();
            while (props.hasNext()) {
                Property p = (Property) props.next();
                if (p.isMany()) {
                    System.out.print("[ " + p.getName() + " ] ");
                    Iterator children = obj.getList(p).iterator();
                    while (children.hasNext()) {
                        DataObject child = (DataObject) children.next();
                        System.out.print("[ " + child.get("ID") + " ]");
                    }
                    System.out.println();
                } else if (!p.getType().isDataType()) {
                    DataObject child = obj.getDataObject(p);
                    if (child != null) {
                        System.out.println("[ " + p.getName() + " ] " + "[ " + child.get("ID") + " ]");
                    }
                } else {
                    System.out.println("[ " + p.getName() + " ] " + obj.get(p));
                }
            }
        }
    }

}
