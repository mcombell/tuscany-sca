<?xml version="1.0" encoding="ISO-8859-1"?>
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
 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:das="http://org.apache.tuscany.das.rdb/config.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<html>
			<body>
				<h2>Query Result</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th align="left">ID</th>
						<th align="left">LASTNAME</th>
						<th align="left">ADDRESS</th>
					</tr>
					<xsl:for-each select="root">
						<xsl:for-each select="das:DataGraphRoot">
							<tr>
								<td>
									<xsl:value-of select="CUSTOMER/@ID"/>
								</td>
								<td>
									<xsl:value-of select="CUSTOMER/@LASTNAME"/>
								</td>
								<td>
									<xsl:value-of select="CUSTOMER/@ADDRESS"/>
								</td>
							</tr>
						</xsl:for-each>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
