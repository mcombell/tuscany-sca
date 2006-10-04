<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

<xsl:output method="xml" indent="yes"/>	

   <xsl:param name="database_location"/>
	
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
              url="{$database_location}"/>

   </xsl:copy>
	
</xsl:template>	

<!--Copy everything!-->
<xsl:template match="node() | @*">
  <xsl:copy>
    <xsl:apply-templates select="@* | node()" />
  </xsl:copy>
</xsl:template>

</xsl:stylesheet>