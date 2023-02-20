
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
	Collection c = new ArrayList();
	for(int i=0;i<=8;i++){
		c.add("aaaa"+i);
	}
	request.setAttribute("col",c);
 %>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE></TITLE>
</HEAD>

<BODY>
<html:form action="/testArray">
<logic:iterate id="row" name="col" >
<% 

 String a = (String)pageContext.getAttribute("row");
%>
<html:checkbox property="set" value="<%=a%>"><%=a %></html:checkbox><br/>
</logic:iterate>
<html:hidden property="action" value="submit"/>
<html:submit value="."></html:submit>
</html:form>

</BODY>
</html:html>
