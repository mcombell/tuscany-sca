#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- Stripes TLD --%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sdyn" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>

<%-- JSTL TLDs --%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- This is so that you can conveniently refer to the context path with ${symbol_dollar}{contextPath} --%>
<c:set var="contextPath" value="${symbol_dollar}{pageContext.request.contextPath}"/>
