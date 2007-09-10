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
<input type="hidden" id="configFile" name="configFile" value="CustomerConfigWithIDConverter.xml"/>
<input type="hidden" id="type" name="type" value="rss:"/>
<input type="hidden" id="param" name="param" value=""/>
<input type="hidden" id="xslFileName" name="xslFileName" value=""/>

<b>Result Set Shape: </b>
 DAS has ability to specify format(shape) of the ResultSet. This is necessary
 when the JDBC driver in use does not provide adequate support for ResultSetMetadata. 
 Also, we expect that specifying the result set shape will increase performance.
<br>
<hr width="90%" size="1">
<input type="text" name="stmt" id="stmt" value="literal" size="20">
(Select 99, 'Roosevelt', '1600 Pennsylvania Avenue' from customer)

&nbsp; &nbsp;
<input type="button" id="rssButton" name="rssButton" value="ExecuteQuery" 
	onclick="	
		this.form.xslFileName.value='../xsl/customer.xsl';
		param.value = 'Query=' + this.form.type.value+this.form.stmt.value;
		param.value += '&serviceName='+this.form.serviceName.value;
		param.value += '&configFile='+this.form.configFile.value;		
	executeQuery(param.value, xslFileName.value);
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
