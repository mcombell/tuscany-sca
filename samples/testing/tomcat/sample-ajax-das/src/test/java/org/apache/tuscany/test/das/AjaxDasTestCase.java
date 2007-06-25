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
package org.apache.tuscany.test.das;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class AjaxDasTestCase extends TestCase {
    public static final String testUrl = "http://localhost:8080/sample-ajax-das";

    protected WebClient _webClient;
    protected URL _url;
    final List collectedAlerts = new ArrayList();

    protected void setUp() throws Exception {
        //New HTMLunit web client
        _webClient = new WebClient(BrowserVersion.MOZILLA_1_0);
        //System.out.println("SETTING ALERT HANDLER");
        _webClient.setAlertHandler( new CollectingAlertHandler(collectedAlerts) );
        _webClient.setRedirectEnabled(true);
        // Going to have the WebClient connect to this URL
        _url = new URL(testUrl);
    }

    public void testHomepage() throws Exception {
        final String TEST_CASE = "AjaxDASHomePage";
        System.out.print("Running:" + TEST_CASE + "\t\t\t");
        //System.out.println("js enabled?"+_webClient.isJavaScriptEnabled());
        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        //System.out.println("page title:"+page.getTitleText());
        String responseText = page.asText();
        //System.out.println("page html-->\n" + responseText);
        //check page header
        assertTrue(-1 != responseText.indexOf("Tuscany DAS Web Sample"));

        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();
        String menuResponseText = menuPage.asText();
        //System.out.println("menu html-->\n" + menuResponseText);
        //check main menu and sub menu
        assertTrue(-1 != menuResponseText.indexOf("Advanced Features"));
        assertTrue(-1 != menuResponseText.indexOf("OCC"));
        assertTrue(-1 != menuResponseText.indexOf("Converter"));
        assertTrue(-1 != menuResponseText.indexOf("Result Set Shape"));
        assertTrue(-1 != menuResponseText.indexOf("Adhoc Query"));
        assertTrue(-1 != menuResponseText.indexOf("Command"));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testDBRefresh() throws Exception{
        final String TEST_CASE = "DBRefresh";
        System.out.print("Running:" + TEST_CASE + "\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();
        HtmlButtonInput commandButton = (HtmlButtonInput)menuPage.getFormByName("MenuForm").getHtmlElementById("refreshButton");
        HtmlPage commandResult = null;
        if(commandButton != null){
        	//System.out.println("command button text:"+commandButton.asText());
        	commandButton.focus();
        	//System.out.println("command button script:"+commandButton.getOnClickAttribute());
        	commandResult = (HtmlPage)commandButton.click();
        }

        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        	//for(int i=0; i<collectedAlerts.size(); i++){
        		//System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        String commandResultText = commandResult.asText();
        //System.out.println("initial commandResultText-->\n"+commandResultText);

        while(commandResultText.indexOf("Refreshed database!") == -1){
        	Thread.sleep(1000);
        	commandResultText = commandResult.asText();
        	//System.out.println("commandResultText-->\n" + commandResultText);
        }
        assertTrue(-1 != commandResultText.indexOf("Refreshed database!"));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testAllCompanies_Adhoc() throws Exception{
        final String TEST_CASE = "AllCompanies_Adhoc";
        System.out.print("Running:" + TEST_CASE + "\t\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();

        //basic content
        HtmlAnchor adhocAnchor = menuPage.getAnchorByHref("./indexAdhoc.jsp");
        HtmlPage adhocPage = (HtmlPage)adhocAnchor.click();
        String adhocText = adhocPage.asText();
        //System.out.println("adhoc html-->\n" + adhocText);
        assertTrue(-1 != adhocText.indexOf("Adhoc SQL Query"));//heading
        assertTrue(-1 != adhocText.indexOf("ExecuteQuery"));//button text

        //list values
        HtmlForm adhocForm = adhocPage.getFormByName("DasForm");
        //adhocPage.getEnclosingWindow().getWebClient()
        HtmlSelect adhocList = (HtmlSelect)adhocForm.getHtmlElementById("sqlQuery");
        List adhocListOptions = adhocList.getOptions();//.asText();
        //System.out.println(" adhocListOptionsText-->\n" + adhocListOptions.toString());
        String allOptionsText = "";
        for(int i=0; i<adhocListOptions.size(); i++){
        	HtmlOption curOption = (HtmlOption)adhocListOptions.get(i);
        	String curOptionText = curOption.getValueAttribute();
        	allOptionsText = allOptionsText + curOptionText;
        	if(curOptionText.equals("SELECT * FROM COMPANY")){
        		adhocList.setSelectedAttribute(curOption, true);
        		//System.out.println("select option..."+curOptionText);
        	}
        }
        //System.out.println("allOptionsText:"+allOptionsText);

        assertTrue(-1 != allOptionsText.indexOf("NullQuery"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("SELECT * FROM COMPANY"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("SELECT * FROM COMPANY LEFT OUTER JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("INSERT INTO DEPARTMENT (NAME, COMPANYID) VALUES ('MyDept',1)"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("DELETE FROM DEPARTMENT WHERE DEPARTMENT.COMPANYID=1 and DEPARTMENT.ID>1"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("UPDATE DEPARTMENT SET NAME='MyUpdDept' WHERE COMPANYID=1 AND ID=1"));//check each option

        //check result
        HtmlButtonInput adhocButton = (HtmlButtonInput)adhocForm.getHtmlElementById("queryButton");
        adhocButton.focus();
        HtmlPage adhocResult = (HtmlPage)adhocButton.click();
        String adhocResultText = adhocResult.asText();
        //System.out.println("initial result:"+adhocResultText);

        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        //	for(int i=0; i<collectedAlerts.size(); i++){
        	//	System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        while(adhocResultText.indexOf("das:DataGraphRoot") == -1){
        	//System.out.println("sleeping:"+adhocResultText);
        	Thread.sleep(1000);
        	adhocResultText = adhocResult.asText();
        }

        //System.out.println("new result:"+adhocResultText);

        //better to check just data in xml
        assertTrue(-1 != adhocResultText.indexOf("ACME Publishing"));
        assertTrue(-1 != adhocResultText.indexOf("Do-rite plumbing"));
        assertTrue(-1 != adhocResultText.indexOf("MegaCorp"));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testAllCompaniesDepartments_Command() throws Exception{
        final String TEST_CASE = "AllCompaniesDepartments_Command";
        System.out.print("Running:" + TEST_CASE + "\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();

        //basic content
        HtmlAnchor commandAnchor = menuPage.getAnchorByHref("./indexCommand.jsp");
        HtmlPage commandPage = (HtmlPage)commandAnchor.click();
        String commandText = commandPage.asText();
        //System.out.println("command html-->\n" + commandText);
        assertTrue(-1 != commandText.indexOf("DAS Command:"));//heading
        assertTrue(-1 != commandText.indexOf("ExecuteCommand"));//button text

        //list values
        HtmlForm commandForm = commandPage.getFormByName("DasForm");
        HtmlSelect commandList = (HtmlSelect)commandForm.getHtmlElementById("DasCommand");
        List commandListOptions = commandList.getOptions();
        //System.out.println(" commandListOptionsText-->\n" + commandListOptions.toString());
        String allOptionsText = "";
        for(int i=0; i<commandListOptions.size(); i++){
        	HtmlOption curOption = (HtmlOption)commandListOptions.get(i);
        	String curOptionText = curOption.getValueAttribute();
        	allOptionsText = allOptionsText + curOptionText;
        	if(curOptionText.equals("AllCompaniesAndDepartments")){
        		commandList.setSelectedAttribute(curOption, true);
        		//System.out.println("select option..."+curOptionText);
        	}
        }
        assertTrue(-1 != allOptionsText.indexOf("NullCommand"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("AllCompanies"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("AllCompaniesAndDepartments"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("AddDepartmentToFirstCompany"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("DeleteDepartmentFromFirstCompany"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("UpdateCompanyDepartmentNames"));//check each option

        //check result
        HtmlButtonInput commandButton = (HtmlButtonInput)commandForm.getHtmlElementById("commandButton");
        HtmlPage commandResult = null;
        if(commandButton != null){
        	//System.out.println("command button text:"+commandButton.asText());
        	commandButton.focus();
        	//System.out.println("command button script:"+commandButton.getOnClickAttribute());
        	commandResult = (HtmlPage)commandButton.click();
        }


        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        	//for(int i=0; i<collectedAlerts.size(); i++){
        		//System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        String commandResultText = commandResult.asText();
        //System.out.println("initial commandResultText-->\n"+commandResultText);

        while(commandResultText.indexOf("das:DataGraphRoot") == -1){
        	Thread.sleep(1000);
        	commandResultText = commandResult.asText();
        }
        //System.out.println("commandResultText-->\n" + commandResultText);

        assertTrue(-1 != commandResultText.indexOf("ACME Publishing"));
        assertTrue(-1 != commandResultText.indexOf("Do-rite plumbing"));
        assertTrue(-1 != commandResultText.indexOf("MegaCorp"));
        assertTrue(-1 != commandResultText.indexOf("Advanced Technologies"));
        assertTrue(-1 != commandResultText.indexOf("COMPANYID=\"1\""));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testInsertCompanies_Adhoc() throws Exception{
        final String TEST_CASE = "InsertCompanies_Adhoc";
        System.out.print("Running:" + TEST_CASE + "\t\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();

        //basic content
        HtmlAnchor adhocAnchor = menuPage.getAnchorByHref("./indexAdhoc.jsp");
        HtmlPage adhocPage = (HtmlPage)adhocAnchor.click();
        String adhocText = adhocPage.asText();
        //System.out.println("adhoc html-->\n" + adhocText);
        assertTrue(-1 != adhocText.indexOf("Adhoc SQL Query"));//heading
        assertTrue(-1 != adhocText.indexOf("ExecuteQuery"));//button text

        //list values
        HtmlForm adhocForm = adhocPage.getFormByName("DasForm");
        //adhocPage.getEnclosingWindow().getWebClient()
        HtmlSelect adhocList = (HtmlSelect)adhocForm.getHtmlElementById("sqlQuery");
        List adhocListOptions = adhocList.getOptions();//.asText();
        //System.out.println(" adhocListOptionsText-->\n" + adhocListOptions.toString());
        String allOptionsText = "";
        for(int i=0; i<adhocListOptions.size(); i++){
        	HtmlOption curOption = (HtmlOption)adhocListOptions.get(i);
        	String curOptionText = curOption.getValueAttribute();
        	allOptionsText = allOptionsText + curOptionText;
        	if(curOptionText.startsWith("{INSERT INTO DEPARTMENT (NAME, COMPANYID) VALUES ('MyDept',1)}" )){
        		adhocList.setSelectedAttribute(curOption, true);
        		//System.out.println("insert option..."+curOptionText);
        	}
        }
        //System.out.println("allOptionsText:"+allOptionsText);

        assertTrue(-1 != allOptionsText.indexOf("NullQuery"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("SELECT * FROM COMPANY"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("SELECT * FROM COMPANY LEFT OUTER JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("INSERT INTO DEPARTMENT (NAME, COMPANYID) VALUES ('MyDept',1)"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("DELETE FROM DEPARTMENT WHERE DEPARTMENT.COMPANYID=1 and DEPARTMENT.ID>1"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("UPDATE DEPARTMENT SET NAME='MyUpdDept' WHERE COMPANYID=1 AND ID=1"));//check each option

        //check result
        HtmlButtonInput adhocButton = (HtmlButtonInput)adhocForm.getHtmlElementById("queryButton");
        HtmlPage adhocResult = (HtmlPage)adhocButton.click();

        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        	//for(int i=0; i<collectedAlerts.size(); i++){
        		//System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        String adhocResultText = adhocResult.asText();
        while(adhocResultText.indexOf("das:DataGraphRoot") == -1){
        	Thread.sleep(1000);
        	adhocResultText = adhocResult.asText();
        	//System.out.println("adhocResultText-->\n" + adhocResultText);
        }

        assertTrue(-1 != adhocResultText.indexOf("ACME Publishing"));
        assertTrue(-1 != adhocResultText.indexOf("Do-rite plumbing"));
        assertTrue(-1 != adhocResultText.indexOf("MegaCorp"));
        assertTrue(-1 != adhocResultText.indexOf("Advanced Technologies"));
        assertTrue(-1 != adhocResultText.indexOf("COMPANYID=\"1\""));
        assertTrue(-1 != adhocResultText.indexOf("MyDept"));
        assertTrue(adhocResultText.indexOf("COMPANYID=\"1\"") != adhocResultText.lastIndexOf("COMPANYID=\"1\""));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testDeleteDepartment_Command() throws Exception{
        final String TEST_CASE = "DeleteDepartment_Command";
        System.out.print("Running:" + TEST_CASE + "\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();

        //basic content
        HtmlAnchor commandAnchor = menuPage.getAnchorByHref("./indexCommand.jsp");
        HtmlPage commandPage = (HtmlPage)commandAnchor.click();
        String commandText = commandPage.asText();
        //System.out.println("command html-->\n" + commandText);
        assertTrue(-1 != commandText.indexOf("DAS Command:"));//heading
        assertTrue(-1 != commandText.indexOf("ExecuteCommand"));//button text

        //list values
        HtmlForm commandForm = commandPage.getFormByName("DasForm");
        HtmlSelect commandList = (HtmlSelect)commandForm.getHtmlElementById("DasCommand");
        List commandListOptions = commandList.getOptions();
        //System.out.println(" commandListOptionsText-->\n" + commandListOptions.toString());
        String allOptionsText = "";
        for(int i=0; i<commandListOptions.size(); i++){
        	HtmlOption curOption = (HtmlOption)commandListOptions.get(i);
        	String curOptionText = curOption.getValueAttribute();
        	allOptionsText = allOptionsText + curOptionText;
        	if(curOptionText.equals("DeleteDepartmentFromFirstCompany")){
        		commandList.setSelectedAttribute(curOption, true);
        		//System.out.println("delete option..."+curOptionText);
        	}
        }
        assertTrue(-1 != allOptionsText.indexOf("NullCommand"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("AllCompanies"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("AllCompaniesAndDepartments"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("AddDepartmentToFirstCompany"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("DeleteDepartmentFromFirstCompany"));//check each option
        assertTrue(-1 != allOptionsText.indexOf("UpdateCompanyDepartmentNames"));//check each option

        //check result
        HtmlButtonInput commandButton = (HtmlButtonInput)commandForm.getHtmlElementById("commandButton");
        HtmlPage commandResult = null;
        if(commandButton != null){
        	//System.out.println("command button text:"+commandButton.asText());
        	commandButton.focus();
        	//System.out.println("command button script:"+commandButton.getOnClickAttribute());
        	commandResult = (HtmlPage)commandButton.click();
        }


        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        	//for(int i=0; i<collectedAlerts.size(); i++){
        		//System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        String commandResultText = commandResult.asText();
        //System.out.println("initial commandResultText-->\n"+commandResultText);

        while(commandResultText.indexOf("das:DataGraphRoot") == -1){
        	Thread.sleep(1000);
        	commandResultText = commandResult.asText();
        	//System.out.println("commandResultText-->\n" + commandResultText);
        }

        assertTrue(-1 != commandResultText.indexOf("ACME Publishing"));
        assertTrue(-1 != commandResultText.indexOf("Do-rite plumbing"));
        assertTrue(-1 != commandResultText.indexOf("MegaCorp"));
        assertTrue(-1 != commandResultText.indexOf("Advanced Technologies"));
        assertTrue(-1 != commandResultText.indexOf("COMPANYID=\"1\""));
        assertTrue(commandResultText.indexOf("COMPANYID=\"1\"") == commandResultText.lastIndexOf("COMPANYID=\"1\""));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testOCC() throws Exception{
        final String TEST_CASE = "OCC";
        System.out.print("Running:" + TEST_CASE + "\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();

        //basic content
        HtmlAnchor commandAnchor = menuPage.getAnchorByHref("./occ.jsp");
        HtmlPage commandPage = (HtmlPage)commandAnchor.click();
        String commandText = commandPage.asText();
        //System.out.println("command html-->\n" + commandText);
        assertTrue(-1 != commandText.indexOf("Automatic Optimistic Concurrency Control:"));//heading
        assertTrue(-1 != commandText.indexOf("ExecuteTransaction"));//button text

        //list values
        HtmlForm commandForm = commandPage.getFormByName("DasForm");
        //check result
        HtmlButtonInput commandButton = (HtmlButtonInput)commandForm.getHtmlElementById("occButton");
        HtmlPage commandResult = null;
        if(commandButton != null){
        	//System.out.println("command button text:"+commandButton.asText());
        	commandButton.focus();
        	//System.out.println("command button script:"+commandButton.getOnClickAttribute());
        	commandResult = (HtmlPage)commandButton.click();
        }

        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        	//for(int i=0; i<collectedAlerts.size(); i++){
        		//System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        String commandResultText = commandResult.asText();
        //System.out.println("initial commandResultText-->\n"+commandResultText);

        while(commandResultText.indexOf("collision") == -1){
        	Thread.sleep(1000);
        	commandResultText = commandResult.asText();
        	//System.out.println("commandResultText-->\n" + commandResultText);
        }

        assertTrue(-1 != commandResultText.indexOf("An update collision occurred"));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testRSS() throws Exception{
        final String TEST_CASE = "RSS";
        System.out.print("Running:" + TEST_CASE + "\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();

        //basic content
        HtmlAnchor commandAnchor = menuPage.getAnchorByHref("./rss.jsp");
        HtmlPage commandPage = (HtmlPage)commandAnchor.click();
        String commandText = commandPage.asText();
        //System.out.println("command html-->\n" + commandText);
        assertTrue(-1 != commandText.indexOf("Result Set Shape:"));//heading
        assertTrue(-1 != commandText.indexOf("ExecuteQuery"));//button text

        //list values
        HtmlForm commandForm = commandPage.getFormByName("DasForm");
        //check result
        HtmlButtonInput commandButton = (HtmlButtonInput)commandForm.getHtmlElementById("rssButton");
        HtmlPage commandResult = null;
        if(commandButton != null){
        	//System.out.println("command button text:"+commandButton.asText());
        	commandButton.focus();
        	//System.out.println("command button script:"+commandButton.getOnClickAttribute());
        	commandResult = (HtmlPage)commandButton.click();
        }

        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        	//for(int i=0; i<collectedAlerts.size(); i++){
        		//System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        String commandResultText = commandResult.asText();
        //System.out.println("initial commandResultText-->\n"+commandResultText);

        while(commandResultText.indexOf("das:DataGraphRoot") == -1){
        	Thread.sleep(1000);
        	commandResultText = commandResult.asText();
        }

        //System.out.println("commandResultText-->\n" + commandResultText);

        assertTrue(-1 != commandResultText.indexOf("Roosevelt"));
        assertTrue(-1 != commandResultText.indexOf("1600 Pennsylvania Avenue"));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    public void testConverter() throws Exception{
        final String TEST_CASE = "Converter";
        System.out.print("Running:" + TEST_CASE + "\t\t");

        final HtmlPage page = (HtmlPage) _webClient.getPage(_url);
        FrameWindow menuFrame = page.getFrameByName("fixed");
        HtmlPage menuPage = (HtmlPage)menuFrame.getEnclosedPage();

        //basic content
        HtmlAnchor commandAnchor = menuPage.getAnchorByHref("./converter.jsp");
        HtmlPage commandPage = (HtmlPage)commandAnchor.click();
        String commandText = commandPage.asText();
        //System.out.println("command html-->\n" + commandText);
        assertTrue(-1 != commandText.indexOf("Arbitrary Converter:"));//heading
        assertTrue(-1 != commandText.indexOf("ExecuteTransaction"));//button text

        //list values
        HtmlForm commandForm = commandPage.getFormByName("DasForm");
        //select first radio buttom
        HtmlRadioButtonInput stmt0 = (HtmlRadioButtonInput)commandForm.getRadioButtonInput("stmt", "stmt0");
        if(stmt0 != null){
        	//System.out.println("got stmt0");
        	stmt0.setChecked(true);
        }

        //check result
        HtmlButtonInput commandButton = (HtmlButtonInput)commandForm.getHtmlElementById("converterButton");
        HtmlPage commandResult = null;
        if(commandButton != null){
        	//System.out.println("command button text:"+commandButton.asText());
        	commandButton.focus();
        	//System.out.println("command button script:"+commandButton.getOnClickAttribute());
        	commandResult = (HtmlPage)commandButton.click();
        }

        //System.out.println("alerts size:"+collectedAlerts.size());
        //if(collectedAlerts.size() > 0){
        	//for(int i=0; i<collectedAlerts.size(); i++){
        		//System.out.println("alert msg:"+collectedAlerts.get(i));
        	//}
        //}

        String commandResultText = commandResult.asText();
        //System.out.println("initial commandResultText-->\n"+commandResultText);

        while(commandResultText.indexOf("das:DataGraphRoot") == -1){
        	Thread.sleep(1000);
        	commandResultText = commandResult.asText();
        	//System.out.println("commandResultText-->\n" + commandResultText);
        }
        assertTrue(-1 != commandResultText.indexOf("1957"));

        collectedAlerts.clear();
        System.out.println("SUCCESS!!!");
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        AjaxDasTestCase t = new AjaxDasTestCase();
        t.setUp();
        try{
            ////t.testHomepage();
            //t.testAllCompanies_Adhoc();
            //t.testAllCompaniesDepartments_Command();
        	//t.testInsertCompanies_Adhoc();
        	//t.testDeleteDepartment_Command();
        	//t.testOCC();
        	////t.testRSS();
        	//t.testConverter();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
