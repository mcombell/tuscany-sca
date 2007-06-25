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

package org.apache.tuscany.samples.das;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.exception.OptimisticConcurrencyException;
import org.apache.tuscany.samples.das.util.XmlUtil;
import org.apache.tuscany.samples.web.ServiceProcessor;

import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

public class DASQueryProcessor extends ServiceProcessor{
	private DAS das = null;
	private Command read = null;
	private Random generator = new Random();
	private String configFile = null;

	private static DateFormat myformat = new SimpleDateFormat("yyyy.MM.dd");
    private static Date kbday;
    private static Date tbday;

	public String execute(String queryOrcommand, String configFile) throws Exception{
    	String status = null;
    	this.configFile = configFile;

		try{
        	Properties props = validate(queryOrcommand);

        	if(props != null){
        		status = this.getResult((String)props.values().iterator().next(),
        			(String)props.keys().nextElement());
        	}

    		if(queryOrcommand.startsWith("occ:")){
    			status = this.getOcc();
    		}

    		if(queryOrcommand.startsWith("converter:")){
    			status = this.getConverter(queryOrcommand.substring(10));
    		}

    		if(queryOrcommand.startsWith("rss:")){
    			status = this.getRss(queryOrcommand.substring(4));
    		}
        }catch(Exception e){
        	das.releaseResources();
        	e.printStackTrace();
        	throw e;
        }
        das.releaseResources();
        return status ;
	}

	private Properties validate(String queryOrcommand) throws Exception{
		if(queryOrcommand == null){
			throw new Exception("Invalid Command URL -> COMMAND is NULL");
		}

		if(queryOrcommand.startsWith("query:")){
			String qry = queryOrcommand.substring(6);
			if(qry == null || qry.trim().length() == 0){
				throw new Exception("Invalid Command URL -> COMMAND is NULL");
			}
			else{
				Properties props = new Properties();
				props.put("query", qry);
				return props;
			}
		}

		if(queryOrcommand.startsWith("command:")){
			String cmd = queryOrcommand.substring(8);
			if(cmd == null || cmd.trim().length() == 0){
				throw new Exception("Invalid Command URL -> COMMAND is NULL");
			}
			else{
				Properties props = new Properties();
				props.put("command", cmd);
				return props;
			}
		}


		return null;
	}

	/*For any query starting with query: or command: */
    private String getResult(String qry, String qryOrCommand) throws Exception {
    	getDAS();
    	DataObject root = runCommand(qry, qryOrCommand);
    	//BOOK, COMPANY, DEPARTMENT, CUSTOMER
    	Vector elemNames = new Vector();
    	if(root.getList("BOOK") != null && root.getList("BOOK").size() >0){
    		elemNames.add("BOOK");
    	}
    	if(root.getList("COMPANY") != null && root.getList("COMPANY").size() >0){
    		elemNames.add("COMPANY");
    	}
		if(root.getList("DEPARTMENT") != null && root.getList("DEPARTMENT").size() >0){
			elemNames.add("DEPARTMENT");
		}
		if(root.getList("CUSTOMER") != null && root.getList("CUSTOMER").size() >0){
			elemNames.add("CUSTOMER");
		}

    	return formatResult(root, elemNames);

    }

    /* Get DAS config */
	private InputStream getConfig(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

	/* Instantiate DAS*/
	private void getDAS(){
        das = DAS.FACTORY.createDAS(getConfig(configFile));
	}

	/*For any query starting with query: or command: */
	private DataObject runCommand(String qry, String qryOrCommand)throws Exception{
        DataObject root = null;

        //adhoc queries
		if(qryOrCommand.startsWith("query")){//query can come single or batch, when batch, it will be {}{}
			if(!qry.startsWith("{")){
				read = das.createCommand(qry);
				root = read.executeQuery();
			}
			else{
				Vector<String> batch = formQueries(qry);//separate {}{} in different queries

				if(batch != null){
					for(int i=0; i<batch.size(); i++){
						String curQry = batch.get(i);
						if(curQry.substring(0, 6).equalsIgnoreCase("select")){
							read = das.createCommand(curQry);
							root = read.executeQuery();
						}
						else{
							read = das.createCommand(curQry);
							read.execute();
						}
					}
				}
			}
        }

		//DAS config commands
		if(qryOrCommand.startsWith("command")){
			String methodName = "get"+qry; //some convention to form method name instead of lenghtening code

			Method commandMethod = this.getClass().getMethod(methodName, new Class[]{String.class});
			root = (DataObject)commandMethod.invoke(this, qry);
        }
        return root;
	}

	/* Separate batch of queries into vector of queries - for adhoc*/
	private Vector<String> formQueries(String qry){
		Vector<String> batch = new Vector<String>();
		StringTokenizer strTok = new StringTokenizer(qry, "{");
		while(strTok.hasMoreTokens()){
			String curQry = strTok.nextToken();
			curQry = curQry.substring(0, curQry.length()-1);
			batch.add(curQry);
		}
		return batch;
	}

    public static String formatResult(DataObject root, Vector elementNames){
    	String xmlStr = "";
    	XMLHelper helper = XMLHelper.INSTANCE;
    	for(int elemCount=0; elemCount<elementNames.size(); elemCount++){
    		String curElemName = (String)elementNames.get(elemCount);
        	List elemList = root.getList(curElemName);
        	for(int i=0; i<elemList.size(); i++){
        		DataObject curComp = (DataObject)elemList.get(i);
        		xmlStr = xmlStr+helper.save(curComp,null, curElemName);

            	//System.out.println("xmlStr:"+xmlStr);

            	//format xmlstr
            	while(true){
                	String rmvStr = "<?xml version=\"1.0\" encoding=\"ASCII\"?>";
                	int idx = xmlStr.indexOf("<?xml version", 0);
                	if(idx == -1) break;
                	xmlStr = xmlStr.substring(0, idx)+xmlStr.substring(idx+rmvStr.length());

                	rmvStr = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
                	idx = xmlStr.indexOf("xmlns:xsi=", 0);
                	if(idx == -1) break;
                	xmlStr = xmlStr.substring(0, idx)+xmlStr.substring(idx+rmvStr.length());

                	rmvStr = "xmlns:das=\"http:///org.apache.tuscany.das.rdb/das\"";
                	idx = xmlStr.indexOf("xmlns:das=", 0);
                	if(idx == -1) break;
                	xmlStr = xmlStr.substring(0, idx)+xmlStr.substring(idx+rmvStr.length());

                	//System.out.println("xmlStr:"+xmlStr);
            	}

            	while(true){
                	String rmvStr =  "xsi:type=\"";
                	int idx = xmlStr.indexOf(rmvStr, 0);
                	if(idx == -1) break;
                	int idxEnd = xmlStr.indexOf("\"", idx+rmvStr.length());
                	xmlStr = xmlStr.substring(0, idx)+xmlStr.substring(idxEnd+1);

                	//System.out.println("xmlStr:"+xmlStr);
            	}
        	}
    	}

		String add0Str = "<?xml version='1.0' encoding='ISO-8859-1' ?>\n";
		String add1Str = "<root  xmlns:das='http://org.apache.tuscany.das.rdb/config.xsd'  xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>\n";
		String add2Str = "<das:DataGraphRoot>";
		String addEndStr = "</das:DataGraphRoot> </root>";

		xmlStr = add0Str+add1Str+add2Str+xmlStr+"\n"+addEndStr;
		return xmlStr;
    }

    //below has improper serialization due to SDO bugs
	/*From DataGraph get contents related to element <das:DataGraphRoot>*/
	/*private String xmlizeContent(DataGraph graph) throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(graph);
        oos.flush();
        out.flush();
        //oos.close();
        out.close();

        //there are UTF-8 chars which are not ISO-8859-1 ones and xsl transformation does not happen due to that
        //so filter unwanted.
        byte[] graphBytes = out.toByteArray();
        InputStreamReader graphBytesFilter = new InputStreamReader(new ByteArrayInputStream(graphBytes),"ISO-8859-1");
        char[] graphChars = new char[graphBytes.length];
        int curChar = -1;
        int j=0;
        while((curChar = graphBytesFilter.read()) != -1){
        	graphChars[j] = (char)curChar;
        	j++;
        }

        String finalStr = new String(graphChars);

        //remove any junk before first <
        finalStr = finalStr.substring(finalStr.indexOf("<"), finalStr.indexOf("</sdo:datagraph>")+16);
        String xmlSerializationContent = filterJunk(finalStr);//further remove extra characters appearing
        System.out.println("xmlSerializationContent:"+xmlSerializationContent);
        String xmlContent = XmlUtil.getXmlContents(xmlSerializationContent, "<das:DataGraphRoot>", "</das:DataGraphRoot>");
        System.out.println("xmlContent:"+xmlContent);
        return xmlContent;
	}

	//for some reason control chars are appearing, need to filter those
    //this is ugly solution, need to know why from SDO DataGraph these
	//chars are coming in the first place.
	private String filterJunk(String inStr){
        char[] myCharArr  = inStr.toCharArray();
        StringBuffer strBuf = new StringBuffer();

        for(int i=0; i<myCharArr.length; i++){
        	if((myCharArr[i]>=33 && myCharArr[i]<=125) ||
        			Character.isWhitespace(myCharArr[i])){
        		strBuf.append(myCharArr[i]);
        	}
        }
        return strBuf.toString();
	}*/

    /* Start CRUD example */
    public DataObject getAllCompanies(String qry) {
    	read = das.getCommand(qry);
        DataObject root = read.executeQuery();
        return root;
    }

    public DataObject getAllCompaniesAndDepartments(String qry) {
    	read = das.getCommand(qry);
        DataObject root = read.executeQuery();
        return root;
    }

    public DataObject getAddDepartmentToFirstCompany(String qry) {
        Command read = das.getCommand("AllCompaniesAndDepartments");
        DataObject root = read.executeQuery();
        DataObject firstCompany = root.getDataObject("COMPANY[1]");

        DataObject newDepartment = root.createDataObject("DEPARTMENT");
        newDepartment.setString("NAME", "Default Name");
        List deptList = firstCompany.getList("departments");

        deptList.add(newDepartment);
        das.applyChanges(root);

        root = read.executeQuery();
        return root;
    }

    public DataObject getDeleteDepartmentFromFirstCompany(String qry) {
        Command read = das.getCommand("AllCompaniesAndDepartments");
        DataObject root = read.executeQuery();
        DataObject firstCompany = root.getDataObject("COMPANY[1]");

        List departments = firstCompany.getList("departments");
        DataObject departmentToDelete = (DataObject)departments.get(departments.size()-1);
        departmentToDelete.delete();
        das.applyChanges(root);
        root = read.executeQuery();
        return root;
    }

    public  DataObject getUpdateCompanyDepartmentNames(String qry) {
        Command read = das.getCommand("AllCompaniesAndDepartments");
        DataObject root = read.executeQuery();
        DataObject firstCompany = root.getDataObject("COMPANY[1]");

        Iterator i = firstCompany.getList("departments").iterator();

        DataObject department;
        while (i.hasNext()) {
            department = (DataObject) i.next();
            String newName = getRandomDepartmentName();
            department.setString("NAME", newName);
            break;
        }
        das.applyChanges(root);

        root = read.executeQuery();
        return root;
    }

    /* @return random new department name    */
    private String getRandomDepartmentName() {
        int number = generator.nextInt(1000) + 1;
        return "Dept-" + number;
    }
    /*End CRUD example */

    /*Start OCC example */
    public String getOcc() throws Exception{
    	getDAS();
    	Command select = das.createCommand("Select * from BOOK where BOOK_ID = 1");
        DataObject root = select.executeQuery();
        // Explicitly update the DB to force a collision
        Command update = das.createCommand("update BOOK set NAME = 'Puss in Hat' where BOOK_ID = 1");
        update.execute();
        DataObject book = root.getDataObject("BOOK[1]");

        // Modify customer
        book.set("NAME", "Puss in Bat");

        // Build apply changes command
        try {
            das.applyChanges(root);
        } catch (OptimisticConcurrencyException ex) {
            if (!ex.getMessage().equals("An update collision occurred")) {
                throw ex;
            }
            else{
            	return ex.getMessage();
            }
        }
        return "Success";
    }
    /* End OCC example */

    /* Start Result Set Shape example*/
    public String getRss(String stmt) throws Exception{
    	getDAS();
    	read = das.getCommand(stmt);
		//Read
		DataObject root = read.executeQuery();
		Vector elemNames = new Vector();
		elemNames.add("CUSTOMER");
		return formatResult(root, elemNames);
    }
    /* End Result Set Shape example*/

    /*Converter example start*/
    static {
        try {
            kbday = myformat.parse("1957.09.27");
            tbday = myformat.parse("1966.12.20");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConverter(String stmt) throws Exception{
    	getDAS();
		read = das.getCommand("testArbitraryConverter");
		//Read
		DataObject root = read.executeQuery();

    	if(stmt.equals("stmt0")){//Select * from CUSTOMER where ID = 1;
    		Vector elemNames = new Vector();
    		elemNames.add("CUSTOMER");
    		return this.formatResult(root, elemNames);
    	}

    	if(stmt.equals("stmt1")){//Check First Customer's LastName is 1957.09.27
    		return myformat.format(root.getDate("CUSTOMER[1]/LASTNAME"));
    	}

    	if(stmt.equals("stmt2")||stmt.equals("stmt3")){//Set First Customer's LastName to 1966.12.20 OR
    												//Check First Customer's LastName is 1966.12.20
    		root.setDate("CUSTOMER[1]/LASTNAME", tbday);
    		das.applyChanges(root);
            // Read
            root = read.executeQuery();
    		return myformat.format(root.getDate("CUSTOMER[1]/LASTNAME"));
    	}

        return null;
    }
    /* Converter example end**/

    /*For test*/
    public static void main(String[] args){
    	DASQueryProcessor qryProc = new DASQueryProcessor();
    	try{
    		qryProc.execute("command:AllCompaniesAndDepartments", "DasConfig.xml");
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }

}
