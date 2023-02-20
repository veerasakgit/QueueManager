<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%@page import="java.util.*"%>
<%@page import="java.math.*"%>

<%@page import="th.co.msat.motor.queuemanager.database.JobQueueDAO"%>

<%!
	public String chkStrNull(String input)
	{
		String output = input;
		if (input != null) output = input.trim();
		return output;
	}
%>

<%
	JobQueueDAO jqDAO = new JobQueueDAO();
	List queueList = jqDAO.getAllQueueOnServer();
%>

<html:html>

	<HEAD>
		<%@ page language="java" contentType="text/html; charset=TIS-620" pageEncoding="TIS-620"%>
		<META http-equiv="Content-Type" content="text/html; charset=TIS-620">
		<META name="GENERATOR" content="IBM Software Development Platform">
		
		<SCRIPT language="JavaScript" src="./js/httpRequest.js"></SCRIPT>
		
		<TITLE>Queue Monitor</TITLE>
		
		<SCRIPT language="javascript">
			var time;
			var terminated = 'R';
			
			function beginQueueMonitor()
			{
				clkSearch();
			}
			
			function loopSearch()
			{
				time = setTimeout("clkSearch()", 5000);
			}
			
			function setTerminate(input)
			{
				terminated = input;
			}
			
			function clkSave()
			{
				//clearTimeout(time);
				var queueId = "";
				var queueName = "";
				var chkSize = document.getElementById("chkSize").value;
				var size = parseInt(chkSize);
				
				for (var i = 0; i < size; i++)
				{
					var tmp = document.getElementById("chk" + i).checked;
					if (tmp)
					{
						if (i != 0 && queueId != "")
						{
							queueId += ",";
							queueName += ",";
						}
						
						queueId += document.getElementById("qId" + i).value;
						queueName += document.getElementById("qName" + i).value;
					}
				}
				
				var dom = document.getElementById("saveBtn");
				dom.disabled = true;
				startRequest("<%=request.getContextPath()%>/QueueMonitorServlet?mode=terminate&terminated=" + terminated + "&queues=" + queueId + "&r=" + Math.random(), 'shwQueueInfo');
				time = setTimeout("resetBtn()", 800);
			}
			
			function clkReset()
			{
				if(!confirm('This action will take all processing jobs dead.\nDo you want to continue?'))
					return false;
					
				//clearTimeout(time);
				var queueId = "";
				var queueName = "";
				var chkSize = document.getElementById("chkSize").value;
				var size = parseInt(chkSize);
				
				for (var i = 0; i < size; i++)
				{
					var tmp = document.getElementById("chk" + i).checked;
					if (tmp)
					{
						if (i != 0 && queueId != "")
						{
							queueId += ",";
							queueName += ",";
						}
						
						queueId += document.getElementById("qId" + i).value;
						queueName += document.getElementById("qName" + i).value;
					}
				}
				
				var dom = document.getElementById("resetBtn");
				dom.disabled = true;
				startRequest("<%=request.getContextPath()%>/QueueMonitorServlet?mode=reset&queues=" + queueId + "&r=" + Math.random(), 'shwQueueInfo');
				time = setTimeout("resetBtn()", 800);
			}
			
			function clkSearch()
			{
				var dom = document.getElementById("searchBtn");
				dom.disabled = true;
				startRequest("<%=request.getContextPath()%>/QueueMonitorServlet?mode=search&r=" + Math.random(), 'shwQueueInfo');
				time = setTimeout("resetBtn()", 800);
				//loopSearch();
			}
			
			function resetBtn()
			{
				unchkAll();
				
				var saveBtn = document.getElementById("saveBtn");
				saveBtn.disabled = false;
				
				var resetBtn = document.getElementById("resetBtn");
				resetBtn.disabled = false;
				
				var searchBtn = document.getElementById("searchBtn");
				searchBtn.disabled = false;
			}
			
			function chkAllGroup()
			{
				var chkIndex = document.getElementById("chkSize").value;
				var chkAll = document.getElementById("chkAll").checked;
				
				var allGrp = parseInt(chkIndex);
				
				for (var i = 0; i < allGrp; i++)
				{
					document.getElementById("chk" + i).checked = chkAll;
				}
				
				chkGroup();
			}
			
			function chkGroup()
			{
				var val = "";
				var all = false;
				
				var chkIndex = document.getElementById("chkSize").value;
				var allGrp = parseInt(chkIndex);
				
				for (var i = 0; i < allGrp; i++)
				{
					var tmp = document.getElementById("chk" + i).checked;
					if (tmp)
					{
						all = true;
					} else
					{
						all = false;
						break;
					}
				}
				
				document.getElementById("chkAll").checked = all;
			}
			
			function unchkAll()
			{
				var chkIndex = document.getElementById("chkSize").value;
				
				var allGrp = parseInt(chkIndex);
				
				for (var i = 0; i < allGrp; i++)
				{
					document.getElementById("chk" + i).checked = false;
				}
				
				document.getElementById("chkAll").checked = false;
			}
		</SCRIPT>
	</HEAD>
	
	<BODY onload="beginQueueMonitor()">
		<br/>
		<TABLE width="780px" cellspacing="1" align="center">
			<tr>
				<td>
					<div style="text-align: center">
						<TABLE width="325px" cellspacing="1" align="left">
							<tr bgcolor="#7fa3c9" height="28px">
								<td width="25px" style="text-align:center;">
									<input type="checkbox" id="chkAll" onclick="chkAllGroup()"/>
								</td>
								<td width="300px" style="text-align:center;">
									<b><font color="#FFFFFF">Queue name</font></b>
								</td>
							</tr>
							<%for (int i = 0; i < queueList.size(); i++)
							{
								Map map = (Map)queueList.get(i);
								
								if (i%2 == 0)
								{%>
									<tr id="trQ<%=i%>" bgcolor="#B4CFEC">
								<%}else
								{%>
									<tr id="trQ<%=i%>" bgcolor="#C6DEFF">
								<%}%>
									<td style="text-align:center;">
										<input type="checkbox" id="chk<%=i%>" onclick="chkGroup()"/>
										<input type="hidden" id="qId<%=i%>" value="<%=((BigDecimal)map.get("queueid")).intValue()%>"/>
										<input type="hidden" id="qName<%=i%>" value="<%=((String)map.get("queuename")).trim()%>"/>
									</td>
									<td style="text-align:left;">
										&nbsp;<%=((String)map.get("queuename")).trim()%>
									</td>
								</tr>
							<%}%>
						</TABLE>
						<span style="width:455px;" id="shwQueueInfo"></span>
						<input type="hidden" id="chkSize" value="<%=queueList.size()%>"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" style="text-align:right;">
						<tr>
							<td style="text-align: right">
								<input type="radio" name="terminated" onclick="setTerminate('R')" checked/>On
								<br />
								<input type="radio" name="terminated" onclick="setTerminate('T')"/>Off
							</td>
							<td width="280px">
								<input type="button" id="saveBtn" value="Save" onclick="clkSave()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="resetBtn" value="Reset Queue" onclick="clkReset()"/>
								<input type="button" id="searchBtn" value="Refresh" onclick="clkSearch()"/>
								&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</TABLE>
	</BODY>
	
</html:html>
