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
<input type="hidden" id="configFile" name="configFile" value="CustomerConfigWithConverter.xml"/>
<input type="hidden" id="type" name="type" value="converter:"/>
<input type="hidden" id="param" name="param" value=""/>
<input type="hidden" id="xslFileName" name="xslFileName" value=""/>

<b>Arbitrary Converter: </b>
     The column converted is a VARCAHAR. 
     ResultSetShape is used to specify that the property will be a SDODataTypes.DATE.
     So this example uses a converter that transforms a string column into a date property 
     and conversely, a date property back to a string for the underlying column.
	<br>
     The converter returns 1957.09.27 if the column value is "Pavick" and 1966.12.20 if 
     the value is "Williams"
    <br>  
      On write, the converter returns "Williams" if the property value is 1966.12.20 and "Pavick" 
      if the property value is 1957.09.27
    <br>
      Check using direct database connection that the database table rows's column values are 
      either "Williams" or "Pavick".
<br>
<hr width="90%" size="1">
<input type="radio" name="stmt" id="stmt" checked="checked" value="stmt0">
Select * from CUSTOMER where ID = 1;
<br>
<input type="radio" name="stmt" id="stmt" value="stmt1">
Check First Customer's LastName is 1957.09.27
<br>
<input type="radio" name="stmt" id="stmt" value="stmt2">
Set First Customer's LastName to 1966.12.20
<br>
<input type="radio" name="stmt" id="stmt" value="stmt3">
Check First Customer's LastName is 1966.12.20
<br>


&nbsp; &nbsp;
<input type="button" id="converterButton" name="converterButton" value="ExecuteTransaction" 
	onclick="				
		var varVal;
		if(this.form.stmt[0].checked){
			varVal = 'stmt0';
			xslFileName.value = '../xsl/customer.xsl';
		}
		if(this.form.stmt[1].checked){
			varVal = 'stmt1';
			xslFileName.value = '';
		}
		if(this.form.stmt[2].checked){
			varVal = 'stmt2';
			xslFileName.value = '';
		}
		if(this.form.stmt[3].checked){
			varVal = 'stmt3';
			xslFileName.value = '';
		}
		
		param.value = 'Query=' + this.form.type.value+varVal;
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
