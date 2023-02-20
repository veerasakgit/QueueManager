
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=TIS-620"
	pageEncoding="TIS-620"%>
<META http-equiv="Content-Type" content="text/html; charset=TIS-620">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE></TITLE>
</HEAD>

<BODY>
<html:form action="/testPutjob">
	<table cellpadding="0" cellspacing="5" border="0" width="100%">
		
		<tr height="25">
			<td colspan="2" bgcolor="#666600"><font color="#FFFFFF"><STRONG>Claim
			Register</STRONG></font></td>
		</tr>
		<tr>
			<td colspan="2" height="30"></td>
		</tr>
		<tr>
			<td align="right">Job Group No.:</td>
			<td><bean:write name="testputform" property="jobgroup" /></td>
		</tr>
		<tr>
			<td align="right">Queue Name:</td>
			<td>
				<html:select property="queueName">
					<logic:iterate id="row" name="allQueueName">
						<option value="<bean:write name="row" />"><bean:write name="row" /></option>
					</logic:iterate>
				</html:select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><html:radio property="scrapType" value="one">Single Notification</html:radio></td>
		</tr>
		<tr>
			<td align="right">Notification No:</td>
			<td><html:text property="message"></html:text></td>
		</tr>
		<tr>
			<td></td>
			<td><html:radio property="scrapType" value="period">From - To</html:radio></td>
		</tr>
		<tr>
			<td align="right">Notification No:</td>
			<td><html:text property="messageFrom"></html:text> To <html:text
				property="messageTo"></html:text></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td align="right">Piority:</td>
			<td><html:select property="piority">
				<html:option value="1">1</html:option>
				<html:option value="2">2</html:option>
				<html:option value="3">3</html:option>
				<html:option value="4">4</html:option>
				<html:option value="5">5</html:option>
				<html:option value="6">6</html:option>
				<html:option value="7">7</html:option>
				<html:option value="8">8</html:option>
				<html:option value="9">9</html:option>
				<html:option value="10">10</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td align="right">Status:</td>
			<td><html:radio property="status" value="R">R</html:radio>,<html:radio
				property="status" value="H">H</html:radio></td>
		</tr>
		<tr>
			<td align="right"><html:hidden property="action" value="submit" /></td>
			<td><html:submit value="submit"></html:submit></td>
		</tr>
	</table>
</html:form>
</BODY>
</html:html>
