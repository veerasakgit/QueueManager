<%@ page 
language="java"
contentType="text/html; charset=TIS-620"
pageEncoding="TIS-620"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=tis-620">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>index.jsp</title>
</head>
<body>
	<html:form action = "/testAddQueue">
		<html:hidden property="actiontype" value="Y"/><br/>
		queue name:<html:text property="queueName"/><br/>
		size<html:text property="size"/><br/>
		terminate? :<html:radio property="isterminate" value="N">N</html:radio><br/>
		<html:radio property="isterminate" value="Y">Y</html:radio><br/>
		working time limit:<html:text property="workingtime" /><br/>
		activation class:<html:text property="activationclass"/><br/>
		<html:submit value="Create!"></html:submit>
		
	</html:form>
</body>
</html:html>
