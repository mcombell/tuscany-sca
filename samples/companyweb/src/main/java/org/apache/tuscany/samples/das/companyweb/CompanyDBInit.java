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
package org.apache.tuscany.samples.das.companyweb;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tuscany.das.rdb.dbconfig.DBInitializer;

public class CompanyDBInit extends HttpServlet {
    private static final long serialVersionUID = -4795999792460944805L;
    private static final String dbConfigFile = "CompanyWebDBConfig.xml";

    @Override
    public void init() throws ServletException {
        try {
            DBInitializer dbInitializer;
            dbInitializer = new DBInitializer(getConfig(dbConfigFile)); 
            if (! dbInitializer.isDatabaseReady()) {
                dbInitializer.initializeDatabase(true);
            }
        } catch (Exception e) {

            e.printStackTrace();
            log(e.toString(), e);
            throw new ServletException(e);
        }
    }

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println();
        out.println("<center><h2>CompanyWeb database initialization servlet !<h2></center>");
        out.println();
    }
    
    private InputStream getConfig(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
