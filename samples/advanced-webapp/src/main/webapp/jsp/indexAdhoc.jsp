<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%-- 
 *  Copyright (c) 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 --%>

<%-- JSTL tags --%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>
<link href="default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/ajax.js" >
</script>

</head>

<body onload="startup()">

<%@ page import="java.util.*" %>

<font face="Arial,Helvetica,Verdana" size="3">
     
<form name="DasForm" >

<input type="hidden" id="serviceName" name="serviceName" value="org.apache.tuscany.samples.das.DASQueryProcessor"/>
<input type="hidden" id="configFile" name="configFile" value="DasConfig.xml"/>
<input type="hidden" id="type" name="type" value="query:"/>
<input type="hidden" id="param" name="param" value=""/>
<input type="hidden" id="xslFileName" name="xslFileName" value=""/>

<b>Adhoc SQL Query: </b><br>
<select id="sqlQuery" name="sqlQuery" 	
	onfocus="this.form.queryButton.disabled=false;">
<option value="NullQuery"></option>
<option value="SELECT * FROM COMPANY">SELECT * FROM COMPANY</option>
<option value="SELECT * FROM COMPANY LEFT OUTER JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID">SELECT * FROM COMPANY LEFT OUTER JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID</option>
<option value="{INSERT INTO DEPARTMENT (NAME, COMPANYID) VALUES ('MyDept',1)}{SELECT * FROM COMPANY LEFT OUTER JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID}">
		INSERT INTO DEPARTMENT (NAME, COMPANYID) VALUES ('MyDept',1)</option>
<option value="{DELETE FROM DEPARTMENT WHERE DEPARTMENT.COMPANYID=1 and DEPARTMENT.ID>1}{SELECT * FROM COMPANY LEFT OUTER JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID}">
		DELETE FROM DEPARTMENT WHERE COMPANYID=1 and ID>1</option>
<option value="{UPDATE DEPARTMENT SET NAME='MyUpdDept' WHERE COMPANYID=1 AND ID=1}{SELECT * FROM COMPANY LEFT OUTER JOIN DEPARTMENT ON COMPANY.ID = DEPARTMENT.COMPANYID}">
		UPDATE DEPARTMENT SET NAME='MyUpdDept' WHERE COMPANYID=1 AND ID=1</option>
</select>
&nbsp; &nbsp;
<input type="button" id="queryButton" name="queryButton" value="ExecuteQuery" 
	onclick="
		if(this.form.sqlQuery.value=='SELECT * FROM COMPANY'){
			this.form.xslFileName.value = '../xsl/company.xsl';
		}
		else{
			this.form.xslFileName.value = '../xsl/companyDepartment.xsl';
		}
		param.value = 'Query=' + this.form.type.value+this.form.sqlQuery.value;
		param.value += '&serviceName='+this.form.serviceName.value;
		param.value += '&configFile='+this.form.configFile.value;		
	executeQuery(param.value, xslFileName.value);
	formWaitMessage();
	" >
</input>
<hr>

<!-- Font for Status Message -->
<font face="Arial,Helvetica,Verdana" size="2" color="#FF0000">
<div id="msg">&nbsp;</div>
</font>
<!--  Font End -->
</form>

</font>

</body>
</html>
