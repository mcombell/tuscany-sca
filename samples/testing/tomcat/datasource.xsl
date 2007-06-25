<?xml version="1.0"?>

<!--
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at
 
  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
 -->


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

<xsl:output method="xml" indent="yes"/>	

   <xsl:param name="database_location"/>
   <xsl:param name="database_location_ajax"/>
	
<!--Add derby database resource-->
<xsl:template match="GlobalNamingResources">
	
  <xsl:copy>
    <!--Copy existing-->
    <xsl:apply-templates select="@* | node()" />
	  
    <xsl:comment> Global Datasource for Derby dastest database </xsl:comment>
    <xsl:text>
    </xsl:text>  
	 <!--Append this-->
         <Resource name="jdbc/dastest"
              type="javax.sql.DataSource"  auth="Container"
              description="Derby database for DAS Company sample"
              maxActive="100" maxIdle="30" maxWait="10000"
              username="" password="" 
              driverClassName="org.apache.derby.jdbc.EmbeddedDriver"
              url="{$database_location};create=true"/>
         <Resource name="jdbc/ajaxdastest"
              type="javax.sql.DataSource"  auth="Container"
              description="Derby database for Ajax DAS Web sample"
              maxActive="100" maxIdle="30" maxWait="10000"
              username="" password="" 
              driverClassName="org.apache.derby.jdbc.EmbeddedDriver"
              url="{$database_location_ajax};create=true"/>
   </xsl:copy>
	
</xsl:template>	

<!--Copy everything!-->
<xsl:template match="node() | @*">
  <xsl:copy>
    <xsl:apply-templates select="@* | node()" />
  </xsl:copy>
</xsl:template>

</xsl:stylesheet>