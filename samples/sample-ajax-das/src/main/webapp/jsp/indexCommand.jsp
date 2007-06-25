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
<script src="../js/ajax.js" language="javascript" type="text/javascript"></script>

</head>

<body onload="startup()">

<%@ page import="java.util.*" %>

<font face="Arial,Helvetica,Verdana" size="3">

<form name="DasForm" >

<input type="hidden" id="serviceName" name="serviceName" value="org.apache.tuscany.samples.das.DASQueryProcessor"/>
<input type="hidden" id="configFile" name="configFile" value="DasConfig.xml"/>
<input type="hidden" id="type" name="type" value="command:"/>
<input type="hidden" id="param" name="param" value=""/>
<input type="hidden" id="xslFileName" name="xslFileName" value=""/>

<b>DAS Command: </b>

<select id="DasCommand" name="DasCommand" 	
	onfocus="this.form.commandButton.disabled=false;">
<option value="NullCommand"></option>
<option value="AllCompanies">all companies</option>
<option value="AllCompaniesAndDepartments">all companies and departments</option>
<option value="AddDepartmentToFirstCompany">Add department to first company</option>
<option value="DeleteDepartmentFromFirstCompany">Delete department from first company</option>
<option value="UpdateCompanyDepartmentNames">Update one department name from first company</option>
</select>


<input type="button" id="commandButton" name="commandButton" value="ExecuteCommand" 
	onclick="this.form.type.value='command:';
			if(this.form.DasCommand.value=='AllCompanies'){
				this.form.xslFileName.value = '../xsl/company.xsl';
			}
			else{
				this.form.xslFileName.value = '../xsl/companyDepartment.xsl';
			}
			
			param.value = 'Query=' + this.form.type.value+this.form.DasCommand.value;
			param.value += '&serviceName='+this.form.serviceName.value;
			param.value += '&configFile='+this.form.configFile.value;		
			executeQuery(param.value, this.form.xslFileName.value);			
			formWaitMessage();
			" >
</input>	
<hr>
<!-- Font for Status Message -->
<font face="Arial,Helvetica,Verdana" size="2" color="#FF0000">
<div  id="msg">&nbsp;</div>
</font>
<!--  Font End -->
</form>

</font>

</body>
</html>
