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
<input type="hidden" id="type" name="type" value="occ:"/>
<input type="hidden" id="param" name="param" value=""/>
<input type="hidden" id="xslFileName" name="xslFileName" value=""/>

<b>Automatic Optimistic Concurrency Control: </b>
     Try to modify same column in same database table row twice in one transaction.
<br>
<TEXTAREA name="occTransactions" id="occTransactions" rows="3" cols="60" readonly="true">
   Select * from BOOK where BOOK_ID = 1;
   update BOOK set NAME = 'Puss in Hat' where BOOK_ID = 1;
   'UpdateNameForFirstBook'   
</TEXTAREA>

&nbsp; &nbsp;
<input type="button" id="occButton" name="occButton" value="ExecuteTransaction" 
	onclick="
		param.value = 'Query=' + this.form.type.value;
		param.value += '&serviceName='+this.form.serviceName.value;
		param.value += '&configFile='+this.form.configFile.value;		
	executeQuery(param.value, xslFileName.value);
	formWaitMessage();
	">
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
