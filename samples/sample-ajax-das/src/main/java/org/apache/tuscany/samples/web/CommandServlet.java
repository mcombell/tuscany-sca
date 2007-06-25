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
package org.apache.tuscany.samples.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tuscany.das.rdb.dbconfig.DBInitializer;
/**
 * This is generic servlet where based on serviceName, it will call the speciliazed processor to
 * process request and obtain response. This servlet can be used in any web sample, just needs to 
 * pass appropriate serviceProcessorClassName and rest is generic 
 *
 */
public class CommandServlet extends HttpServlet {
    final static long serialVersionUID = 1221332432;
	/*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public CommandServlet() {
        super();
    }
    
    public static void refreshDB() throws ServletException {
    	try{
    		DBInitializer dbInitializer = new DBInitializer("AjaxDBConfig.xml");
	    	dbInitializer.refreshDatabaseData();
		}catch(Exception e){
			e.printStackTrace();
			ServletException se = new ServletException(e.getMessage());
			throw se;
		}
    }

    public void init(ServletConfig config) throws ServletException { 
    	refreshDB();
    }
    
    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0,
     *      HttpServletResponse arg1)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doTask(request, response);
    }

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0,
     *      HttpServletResponse arg1)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doTask(request, response);
    }

    /**Invoke passed service and return result
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
	public void doTask(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String textContentType= "text/text";
		String xmlContentType= "text/xml";
		
		String refreshRequest = req.getParameter("refreshDB"); 
		if(refreshRequest != null && refreshRequest.equals("yes")){
			{
				try{
					refreshDB();
				}catch(ServletException e){
					throw e;
				}
			}
			this.writeResponse(resp, "Refreshed database!", textContentType);			
		}
		else{
			String serviceName = req.getParameter("serviceName");//e.g. DASQueryProcessor
			String qry = req.getParameter("Query");//e.g. query:xxx, command:yyy
			String configFile = req.getParameter("configFile");//name of DAS config file
			
			try{
				ServiceProcessor serviceClassInst = null;
				//so any subclass of abstract ServiceProcessor can be used
				Object obj = Class.forName(serviceName).newInstance();
				
				if(obj instanceof ServiceProcessor){
					serviceClassInst = (ServiceProcessor)obj;
				}
				
				String result = serviceClassInst.execute(qry, configFile);
				this.writeResponse(resp, result, xmlContentType);
			}catch(Exception e){
				this.writeResponse(resp, e.getMessage(), textContentType);
				e.printStackTrace();
			}			
		}
	}

	/**Write response in required content type to servlet response
	 * 
	 * @param resp
	 * @param output
	 * @param contentType
	 * @throws IOException
	 */
	public void writeResponse(HttpServletResponse resp, String output, String contentType) throws IOException {
		resp.setContentType(contentType);//"text/text", "text/xml"
		resp.setHeader("Cache-Control", "no-cache");
		resp.getWriter().write(output);		
	}
	
    // Utility methods

    public void gotoURL(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, String url) throws ServletException, IOException {
        url = response.encodeURL(url);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void redirectURL(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, String url) throws ServletException, IOException {
        url = response.encodeURL(request.getContextPath() + url);
        response.sendRedirect(url);
    }

    public void forwardURL(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, String url) throws ServletException, IOException {
        url = response.encodeURL(url);
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(request, response);
    }    
}
