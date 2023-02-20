<%@ page import="th.co.msat.motor.queuemanager.constants.DefaultQueueConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%@ page import="org.apache.struts.action.DynaActionForm,java.util.*" %>
<%
	DynaActionForm form = (DynaActionForm)request.getAttribute("queueConsoleForm");
	String sQname = (String)form.get("queueName");
	Collection cs = (Collection)form.get("allQueueName");
	
 %>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=TIS-620"
	pageEncoding="TIS-620"%>
<META http-equiv="Content-Type" content="text/html; charset=TIS-620">
<META name="GENERATOR" content="IBM Software Development Platform">
<SCRIPT type="text/javascript">
	function setaction(a){
		document.all.action.value=a;
		if(document.all.switchs.value ==''){
			return false
		}
	}
</SCRIPT>
<TITLE></TITLE>
</HEAD>

<BODY>
<html:form action="/queueConsoleAction">
<table border="0">
	<TBODY>
	<tr>
		<td>Queue Name:</td>
		<td>
		<html:select property="queueName">
		<%
			
			String xss = (String)form.get("queueName");
			
			if(null!=cs){
			for(Iterator iter = cs.iterator();iter.hasNext();){
				String x = (String)iter.next();	
				
		%>
		<option <%=x.equals(xss)?"selected":"" %> value="<%=x %>"><%=x %></option>
		<%}} %>
	
</html:select>
		
		</td>
	</tr>
	<tr>
		<td></td>
		<td><html:radio property="switchs" value="<%=DefaultQueueConstants.QUEUE_READY %>">On</html:radio> </td>
	</tr>
	<tr>
		<td></td>
		<td><html:radio property="switchs" value="<%=DefaultQueueConstants.QUEUE_TERMINATE %>">Off</html:radio> </td>
	</tr>
	<tr>
		<td>Pool Size:</td>
		<td><bean:write name="queueConsoleForm" property="size"/></td>
	</tr>
	<tr>
		<td>Available in Pool:</td>
		<td><bean:write name="queueConsoleForm"  property="availabelThread"/></td>
	</tr>
	<tr>
		<td>Release Job:</td>
		<td><bean:write name="queueConsoleForm" property="releaseJob" /></td>
	</tr>
	<tr>
		<td>Hold Job:</td>
		<td><bean:write name="queueConsoleForm" property="holdJob"/></td>
	</tr>
	<tr>
		<td>Processing Job:</td>
		<td><bean:write name="queueConsoleForm" property="processingJob"/></td>
	</tr>
	<tr>
		<td><html:submit onclick="setaction('resetQueue'); return confirm('This action will take all processing jobs dead.\nDo you want to continue?');" value="Reset Queue"/></td>
		<td><html:submit onclick="return setaction('save');" value="Save"/>&nbsp;
		<html:submit onclick="return setaction('refresh');"  value="Refresh"/>
		<html:hidden property="action" value="x"/></td>
	</tr>
	<TBODY>
</table>
</html:form>
</BODY>
</html:html>
