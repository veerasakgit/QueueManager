package th.co.queuemanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.queuemanager.QueueBean;

public class QueueMonitorServlet extends HttpServlet implements Servlet
{
	private String contextPath = null;
	
	public QueueMonitorServlet() {super();}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
	{
		doProcess(arg0, arg1);
	}

	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
	{
		doProcess(arg0, arg1);
	}

	public void destroy() {}

	public ServletConfig getServletConfig() {return null;}

	public String getServletInfo() {return null;}

	public void init(ServletConfig arg0) throws ServletException {}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		contextPath = request.getContextPath();
		
		response.setContentType("text/html;charset=TIS-620");
		PrintWriter out = response.getWriter();
		
		String mode = request.getParameter("mode");
		String terminated = request.getParameter("terminated");
		String queues = request.getParameter("queues");
		String[] queueArr = null;
		if (queues != null && !queues.equals(""))
			queueArr = queues.split(",");
		
		try
		{
			if (mode.equals("terminate"))
				terminateQueue(terminated, queueArr);
			else if (mode.equals("reset"))
				resetQueue(queueArr);
			
			out.write(searchData());
			
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			out.flush();
			out.close();
		}
	}
	
	private void terminateQueue(String terminated, String[] queueArr) throws Exception
	{
		QueueBean qBean = new QueueBean();
		JobQueueDAO jqDAO = new JobQueueDAO();
		
		if (queueArr != null)
		{
			for (int i = 0; i < queueArr.length; i++)
			{
				try
				{
					Map map = jqDAO.getQueueInfo(new BigDecimal(queueArr[i]));
					String queueName = (String)map.get("queuename");
					qBean.updateQueueTerminate(queueName, terminated);
					
					if(terminated.equals(DefaultQueueConstants.QUEUE_READY))
					{
						qBean.callQueueRunningHost(queueName);
					}
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	private void resetQueue(String[] queueArr) throws Exception
	{
		if (queueArr != null)
		{
			for (int i = 0; i < queueArr.length; i++)
			{
				QueueBean qBean = new QueueBean();
				JobQueueDAO jqDAO = new JobQueueDAO();
				
				try
				{
					Map map = jqDAO.getQueueInfo(new BigDecimal(queueArr[i]));
					String queueName = (String)map.get("queuename");
					qBean.callQueueRunningHost(queueName, DefaultQueueConstants.PROCESS_RESET_QUEUE);
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	private String searchData() throws Exception
	{
		StringBuffer output = new StringBuffer();
		
		JobQueueDAO jqDAO = new JobQueueDAO();
		List queueList = jqDAO.getAllQueueOnServer();
		
		output.append("<TABLE width=\"455px\" cellspacing=\"1\" align=\"left\">");
		
		output.append("<tr bgcolor=\"#7fa3c9\">");
		
		output.append("<td width=\"70px\" style=\"text-align:center;\">");
		output.append("<b><font color=\"#FFFFFF\">Pool<br/>size</font></b>");
		output.append("</td>");
		
		output.append("<td width=\"70px\" style=\"text-align:center;\">");
		output.append("<b><font color=\"#FFFFFF\">Available<br/>in Pool</font></b>");
		output.append("</td>");
		
		output.append("<td width=\"70px\" style=\"text-align:center;\">");
		output.append("<b><font color=\"#FFFFFF\">On/Off</font></b>");
		output.append("</td>");
		
		output.append("<td width=\"70px\" style=\"text-align:center;\">");
		output.append("<b><font color=\"#FFFFFF\">Release<br/>Job</font></b>");
		output.append("</td>");
		
		output.append("<td width=\"70px\" style=\"text-align:center;\">");
		output.append("<b><font color=\"#FFFFFF\">Hold<br/>Job</font></b>");
		output.append("</td>");
		
		output.append("<td style=\"text-align:center;\">");
		output.append("<b><font color=\"#FFFFFF\">Processing<br/>Job</font></b>");
		output.append("</td>");
		
		output.append("</tr>");
		
		for (int i = 0; i < queueList.size(); i++)
		{
			if (i%2 == 0)
				output.append("<tr bgcolor=\"#B4CFEC\" height=\"20px\">");
			else
				output.append("<tr bgcolor=\"#C6DEFF\" height=\"20px\">");
			
			Map map = (Hashtable)queueList.get(i);
			BigDecimal tmp = null;
			
			output.append("<td width=\"70px\" style=\"text-align:center;\">");
			tmp = (BigDecimal)map.get("size");
			output.append("" + tmp.intValue());
			output.append("</td>");
			
			output.append("<td width=\"70px\" style=\"text-align:center;\">");
			tmp = (BigDecimal)map.get("available");
			output.append("" + tmp.intValue());
			output.append("</td>");
			
			output.append("<td width=\"70px\" style=\"text-align:center;\">");
			output.append((String)map.get("isterminate"));
			output.append("</td>");
			
			output.append("<td width=\"70px\" style=\"text-align:center;\">");
			tmp = (BigDecimal)map.get("release");
			if (tmp != null)
				output.append("" + tmp.intValue());
			else
				output.append("0");
			output.append("</td>");
			
			output.append("<td width=\"70px\" style=\"text-align:center;\">");
			tmp = (BigDecimal)map.get("hold");
			if (tmp != null)
				output.append("" + tmp.intValue());
			else
				output.append("0");
			output.append("</td>");
			
			output.append("<td width=\"\" style=\"text-align:center;\">");
			tmp = (BigDecimal)map.get("process");
			if (tmp != null)
				output.append("" + tmp.intValue());
			else
				output.append("0");
			output.append("</td>");
			
			output.append("</TR>");
		}
		output.append("</TABLE>");
		
		return output.toString();
	}
}