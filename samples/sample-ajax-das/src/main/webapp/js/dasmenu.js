//<!--
//  Copyright (c) 2005 The Apache Software Foundation or its licensors, as applicable.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
// -->
var dbstatus;
var brwsrDB;
var reqDB;

function showSubMenu(){
	var objThis = this;	

	for(var i = 0; i  < objThis.childNodes.length; i++)
	{
		if(objThis.childNodes.item(i).nodeName == "UL")			
		{							
			objThis.childNodes.item(i).style.display = "block";						
		}		
	}	
}

function hideSubMenu()
{								
	var objThis = this;	
	
	for(var i = 0; i  < objThis.childNodes.length; i++)			
	{
		if(objThis.childNodes.item(i).nodeName == "UL")
		{				
			objThis.childNodes.item(i).style.display = "none";			
			return;
		}			
	}	
}			

function initialiseMenu()
{
	var objLICollection = document.body.getElementsByTagName("LI");		
	for(var i = 0; i < objLICollection.length; i++)
	{		
		var objLI = objLICollection[i];										
		for(var j = 0; j  < objLI.childNodes.length; j++)
		{
			if(objLI.childNodes.item(j).nodeName == "UL")
			{
				objLI.onmouseover=showSubMenu;
				objLI.onmouseout=hideSubMenu;
				
				for(var j = 0; j  < objLI.childNodes.length; j++)
				{
					if(objLI.childNodes.item(j).nodeName == "A")
					{					
						objLI.childNodes.item(j).className = "hassubmenu";								
					}
				}
			}
		}
	}	
}

function refreshdb(){
	if (window.XMLHttpRequest) {
		brwsrDB='NOIE';
		reqDB = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		brwsrDB='IE';
		reqDB = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var dbRefreshMsg = document.getElementById('dbmsg');
	if(brwsrDB=='NOIE'){
        var x = document.createTextNode('Refreshing database..please wait');
        dbRefreshMsg.appendChild(x);
	}
	else{
		dbmsg.innerHTML = 'Refreshing database..please wait';
	}

	//TODO
	var urlDB = "/sample-ajax-das/CommandServlet";
	reqDB.open("POST", urlDB, true);
	reqDB.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	reqDB.onreadystatechange = dbresultHandler;
	reqDB.send('refreshDB=yes');
}

function dbresultHandler() {
	if (reqDB.readyState == 4) {
		if (reqDB.status == 200) {
			var dbRefreshMsg = document.getElementById('dbmsg');
			if(brwsrDB=='NOIE'){
			  dbRefreshMsg.removeChild(dbRefreshMsg.childNodes[0]);//remove wait message
		      var x = document.createTextNode('Refreshed database!');
		      dbRefreshMsg.appendChild(x);
			}
			else{
			  dbmsg.innerHTML = 'Refreshed database!';
			}
			return;
		}	
	}
}