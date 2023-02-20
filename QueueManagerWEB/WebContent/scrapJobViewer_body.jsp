
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="theme/ts_picker.js"></script>
<script type="text/javascript">
function setAction(act){
	document.all.action.value=act;
	//return true;
	return chkValidateStatus();
}

function chkValidateStatus()
{
	var status = document.all.status;
	var chkStatus = '';
	for(var i = 0; i < status.length; i++)
	{
		if(status[i].checked)
			chkStatus = status[i].value;
	}
	
	if(chkStatus=='')
	{
		alert('Please select status');
		return false;
	} else
	{
		return true;
	}
}

function validateStatus(dom){
	dom.disabled = true;
	
	var status = document.all.status;
	var chkStatus = '';
	for(var i=0;i<status.length;i++){
		if(status[i].checked)
			chkStatus = status[i].value;
	}
	
	if(chkStatus==''){
		alert('Please select status');
		dom.disabled = false;
		//return false;
	} else
	{
		document.scrapJobViewerForm.submit();
		//return true;
	}
}

function checkAll(obj){
	if(obj.checked){
		var jobs = document.all.jobidList;
		for(var i=0;i<jobs.length;i++){
			
			jobs[i].checked = true;
		}
	}else{
		var jobs = document.all.jobidList;
		for(var i=0;i<jobs.length;i++){
			
			jobs[i].checked = false;
		}
	}
}
</script>
<TITLE></TITLE>
</HEAD>

<BODY>
<html:form action="/scrapJobViewer"> 
<TABLE border="0" >
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td>Search</td>
	</tr>
	<tr>
		<td align="right">Queue Name:</td>
		<td align="left"><html:select property="qname">
		<%
			org.apache.struts.action.DynaActionForm form = (org.apache.struts.action.DynaActionForm)request.getAttribute("scrapJobViewerForm");
			String xss = (String)form.get("qname");
			Collection c = (Collection)request.getAttribute("allQueueName"); 
			for(Iterator iter = c.iterator();iter.hasNext();){
				String x = (String)iter.next();	
				
		%>
		<option <%=x.equals(xss)?"selected":"" %> value="<%=x %>"><%=x %></option>
		<%} %>
	
</html:select></td>
	</tr>
	
	<tr>
		<td align="right">Job Group:</td>
		<td align="left"><html:text property="jobGroup"></html:text>  </td>
	</tr>
	<tr>
		<td align="right">Reference No:</td>
		<td align="left"><html:text property="referenceNo"></html:text>  </td>
	</tr>
	<tr>
		<td align="right">Submit User:</td>
		<td align="left"><html:text property="submitUser"></html:text>  </td>
	</tr>
	<tr>
		<td align="right">Submit Date:</td>
		<td align="left"><html:text property="submitTimeFrom" size="10"></html:text>&nbsp;<A href="javascript:show_calendar('document.scrapJobViewerForm.submitTimeFrom', document.scrapJobViewerForm.submitTimeFrom.value);""
		onmouseover="window.status='';return true;"><img src="cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></A> To: 
		<html:text property="submitTimeTo" size="10"></html:text>&nbsp;<A
				href="javascript:show_calendar('document.scrapJobViewerForm.submitTimeFrom', document.scrapJobViewerForm.submitTimeFrom.value);"
				onmouseover="window.status='';return true;"><IMG src="cal.gif"
				width="16" height="16" border="0"
				alt="Click Here to Pick up the timestamp"></A></td>
	</tr>
	<%   
			org.apache.struts.action.DynaActionForm form = (org.apache.struts.action.DynaActionForm)request.getAttribute("scrapJobViewerForm");
			String[] status = (String[])form.get("status");
			boolean isR = false;
			boolean isP = false;
			boolean isC = false;
			boolean isE = false;
			boolean isH = false;
			for(int i = 0 ;i<status.length;i++){
				if(status[i].equals("R")){
					isR = true;
				}
				if(status[i].equals("P")){
					isP = true;
				}
				if(status[i].equals("C")){
					isC = true;
				}
				if(status[i].equals("E")){
					isE = true;
				}
				if(status[i].equals("H")){
					isH = true;
				}
			}
				
	%>
	<tr>
		<td align="right">Status:</td>
		<td align="left">
			<input type="checkbox" name="status" <%=isR?" checked=\"checked\"":"" %> value="R"/>Release&nbsp;
			<input type="checkbox" name="status" <%=isP?" checked=\"checked\"":"" %> value="P"/>Process&nbsp;
			<input type="checkbox" name="status" <%=isC?" checked=\"checked\"":"" %> value="C"/>Complete&nbsp;
			<input type="checkbox" name="status" <%=isE?" checked=\"checked\"":"" %> value="E"/>Error&nbsp;
			<input type="checkbox" name="status" <%=isH?" checked=\"checked\"":"" %> value="H"/>Hold&nbsp;
		</td>
	</tr>
	<tr>
		<td><html:hidden property="action" value="search"/></td>
		
		<td><input type="button" value="Search" onclick="validateStatus(this);"/></td>
		 
		 
	</tr>
</TABLE>
<table border="0">
	<tr>
		<td><html:submit value="Release" onclick="return setAction('rel');"></html:submit>
		&nbsp;<html:submit value="Hold" onclick="return setAction('hol');"></html:submit>
		&nbsp;<html:submit value="Delete" onclick="return setAction('del');"></html:submit>
		</td>
	</tr>
</table>
<table border="0">
	<tr>
		<td>
			<display:table name="result" id="row">
			<display:setProperty name="css.table" value="simple" />
				<display:setProperty name="paging.banner.some_items_found"><span class="pagebanner">
{0} Job found,
displaying {2} to {3}.
</span></display:setProperty>
				<% String notino= "";
					notino =((th.co.msat.motor.queuemanager.vo.JobVO)pageContext.getAttribute("row"))==null? "":
					((th.co.msat.motor.queuemanager.vo.JobVO)pageContext.getAttribute("row")).getJobid().toString(); 
					notino = notino.trim();
				%>
				<display:column title="<input type='checkbox' onclick='checkAll(this)'> ">
					<logic:equal value="R" name="row" property="status">
						<html:checkbox property="jobidList" value="<%=notino %>"></html:checkbox>
					</logic:equal>
					<logic:equal value="H" name="row" property="status">
						<html:checkbox property="jobidList" value="<%=notino %>"></html:checkbox>
					</logic:equal>
					<logic:equal value="E" name="row" property="status">
						<html:checkbox property="jobidList" value="<%=notino %>"></html:checkbox>
					</logic:equal>
				</display:column>
				<display:column title="Job ID" property="jobid" sortable="true"></display:column>
				<display:column title="Job Group" sortable="true"><%=((th.co.msat.motor.queuemanager.vo.JobVO)pageContext
				.getAttribute("row"))
				.getJobgroup()%></display:column>
				<display:column title="User" property="submintUser" sortable="true"></display:column>
				<display:column title="Reference No" sortable="true" property="referenceno"></display:column>
				<display:column title="Time" property="displaySubmitDate" sortable="true"></display:column>
				<!-- Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd] -->
				<%
					//<display:column title="Status" property="status" sortable="true"></display:column>
					th.co.msat.motor.queuemanager.vo.JobVO jobVO = (th.co.msat.motor.queuemanager.vo.JobVO)pageContext.getAttribute("row");
				%>
				<display:column title="Status" sortable="true">
					<%=jobVO.getStatus() + " " + jobVO.getResultMessage()%>
				</display:column>
				<!-- End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd] -->
			</display:table></td>
	</tr>
</table>
</html:form>
</BODY>
</html:html>
