package th.co.queuemanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.co.msitb.claimsystem.executor.ClaimRegiterDAO;

public class ClaimSystemCallbackServlet extends HttpServlet implements Servlet
{
	public ClaimSystemCallbackServlet() {super();}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
	{
		doProcess(arg0, arg1);
	}

	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
	{
		doProcess(arg0, arg1);
	}
	
	protected void doPut(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
	{
		doProcess(arg0, arg1);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		StringBuffer output = new StringBuffer("");
		
		String claimNumber = request.getParameter("clmNo");
		String notificationNo = request.getParameter("notificaionNo");
		String reviewCode = request.getParameter("reviewCode");
		String status = request.getParameter("status");
		
		try
		{
			ClaimRegiterDAO dao = new ClaimRegiterDAO();
			if(claimNumber != null && !claimNumber.equals("null") && claimNumber.length() > 0)
			{
				dao.updateClaimNo(notificationNo, claimNumber, reviewCode, status);
			} else
			{
				dao.updateClaimNo(notificationNo, "", reviewCode, status);
			}
			
			output.append("claim system callback completed.");
		} catch (Exception e)
		{
			e.printStackTrace();
			output.append("claim system callback failed.");
		}
		
		PrintWriter out = response.getWriter();
		out.println(output.toString());
	    out.flush();
	    out.close();
	 	response.flushBuffer();
	}
}