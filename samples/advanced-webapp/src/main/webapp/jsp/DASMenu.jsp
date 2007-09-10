<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
  Copyright (c) 2005 The Apache Software Foundation or its licensors, as applicable.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<link rel="stylesheet" type="text/css" href="main.css" media="screen, print" />
		<script type="text/javascript" src="../js/dasmenu.js" >
		</script>

		<title>DASMenu: Web Sample</title>
		<meta name="description" content="DAS Features Demo" />
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="expires" content="-1" />
		<meta http-equiv="pragma" content="no-cache" />
	</head>
	<body onload="initialiseMenu();
				  
				  ">	
		<h1>Tuscany DAS Web Sample</h1>
		<ul id="mainmenu">
			<li><a href="../html/blank.html"  target="dynamic">Advanced Features</a>
				<ul>
					<li><a href="./occ.jsp" target="dynamic">OCC</a></li>
					<li><a href="./converter.jsp" target="dynamic">Converter</a></li>
					<li><a href="./rss.jsp" target="dynamic">Result Set Shape</a></li>
				</ul>
			</li>
			<li><a href="./indexAdhoc.jsp" target="dynamic">Adhoc Query</a></li>
			<li><a href="./indexCommand.jsp"  target="dynamic">Command</a></li>
		</ul>
		<p>Tuscany DAS web sample demonstrates - simple adhoc queries, DAS predefined commands execution and
		some advanced features like Optimistic Concurrency Control (OCC), Converter, Result Set Shape etc.
		</p>
		<p>Check more details by navigating menu.
		Press <b>Refresh!</b> button anytime for database refresh.
		</p>
		
		<FORM name='MenuForm'>		
		   <INPUT TYPE="button" id="refreshButton" name="refreshButton" value="Refresh!" 
			onClick="	refreshdb();
					"
			>
			</INPUT>				
			<!-- Font for Status Message -->
			<font face="Arial,Helvetica,Verdana" size="2" color="#FF0000">
			<b id="dbmsg"></b>
			</font>
		</FORM>
	</body>
</html>