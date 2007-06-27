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

var req;
var url;
var xslFileName;
var brwsr;
var htmlunitlimit;

function startup(pageName) {
	if(pageName == 'adhoc'){
		document.forms[0].sqlQuery.focus = true;
	}
	
	if(pageName == 'command') {
		document.forms[0].DasCommand.focus = true;
	}
}

function init() {
	if (window.XMLHttpRequest) {
		brwsr="NOIE";
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		brwsr="IE";
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "../CommandServlet";
	req.open("POST", url, true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
}

function formWaitMessage(){
    var swappableSection = document.getElementById('msg');    
    if (window.XSLTProcessor)
    {
        swappableSection.innerHTML = '';
        var x = document.createTextNode('Retreiving result..please wait');        
        swappableSection.appendChild(x);
    }
    else if(window.ActiveXObject)
    {
        swappableSection.innerHTML = 'Retreiving result..please wait' ;
    }
}

function executeQuery(reqParams, xslFileNameVal) {
	init();
	req.onreadystatechange = resultHandler;
	xslFileName = xslFileNameVal;
	//alert(reqParams);
	xsldocloaded = false;
	loadXsl();
	req.send(reqParams);
	//alert('req sent');
}

	var xsldocloaded = false;
	var xsldoc;

function loadXsl(){
	if(xslFileName != ''){
		if (window.XSLTProcessor)
		{
		    // support Mozilla/Gecko based browsers
		    xsldoc = document.implementation.createDocument("", "", null);
		    xsldoc.addEventListener("load", onXslLoad, false);
		    xsldoc.load(xslFileName);
		    //alert("done loadXsl Mozilla");
		    htmlunitlimit='false';
		}
		else if(window.ActiveXObject)
		{
		    // support Windows / ActiveX
		    xsldoc = new ActiveXObject("Microsoft.XMLDOM");
		    xsldoc.ondataavailable = onXslLoad;
		    xsldoc.load(xslFileName);
		    //alert("done loadXsl IE");
		    htmlunitlimit='false';
		}
		else{
			//alert('setiing htmlunitlimit true');
			htmlunitlimit='true';
		}
	}
}

function onXslLoad()
{
    // flag that the xsl is loaded
    xsldocloaded = true;
} 

function resultHandler()
{
    // Make sure the request is loaded (readyState = 4)
    if (req.readyState == 4)
    {
        // Make sure the status is "OK"
        if (req.status == 200)
        {
            // Make sure the XSL document is loaded
            if (!xsldocloaded && xslFileName != '')
            {
				if(htmlunitlimit=='false'){
					alert("Unable to transform data.  XSL is not yet loaded.");
					// break out of the function
					return;
				}
				else{
					var swappableSectionUT = document.getElementById('msg');
					if(brwsr=='NOIE'){
						//alert('limit case, xsl present, noie');
						swappableSectionUT.removeChild(swappableSectionUT.childNodes[0]);//remove wait message
					        var x = document.createTextNode(req.responseText);
					        swappableSectionUT.appendChild(x);
					}
					else{
						swappableSectionUT.innerHTML = req.responseText;
					}
					//alert('returning result:'+req.responseText);
					return;
				}
            }
            
            var swappableSection = document.getElementById("msg");
            
            if (window.XSLTProcessor)
            {
				if(xslFileName == ''){
					swappableSection.removeChild(swappableSection.childNodes[0]);//remove wait message
			        var x = document.createTextNode(req.responseText);
			        swappableSection.appendChild(x);
				}
				else{
	                // support Mozilla/Gecko based browsers
	                var xsltProcessor = new XSLTProcessor();
	                xsltProcessor.importStylesheet(xsldoc);
	                var outputXHTML = xsltProcessor
	                        .transformToFragment(req.responseXML, document);
	                swappableSection.innerHTML = "";
	                swappableSection.appendChild(outputXHTML);
				}
            }
            else if(window.ActiveXObject)
            {
				if(xslFileName == ''){
   	                swappableSection.innerHTML = req.responseText;
				}
				else{
	                // support Windows/ActiveX enabled browsers
	                var outputXHTML = req.responseXML.transformNode(xsldoc);
	                swappableSection.innerHTML = outputXHTML;
				}
            }
            else{
				var swappableSectionUT = document.getElementById('msg');
				if(brwsr=='NOIE'){
					swappableSectionUT.removeChild(swappableSectionUT.childNodes[0]);//remove wait message
		        	var x = document.createTextNode(req.responseText);
		        	swappableSectionUT.appendChild(x);
				}
				else{
					swappableSectionUT.innerHTML = req.responseText;
				}
				return;            	
            }
        }
        else
        {
            alert("There was a problem retrieving the XML data:\n" +
                req.statusText);
        }
    }
} 
