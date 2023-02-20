package th.co.queuemanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.queuemanager.QueueSB;
import th.co.msat.motor.queuemanager.QueueSBHome;

public class TestServlet extends HttpServlet implements Servlet
{
	public TestServlet()
	{
		super();
	}
	
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
		try
		{
			QueueSB qSB = createQueueSB();
			qSB.callQueueRunningHost("TestQueue");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		String referenceId = request.getParameter("referenceId");
		String status = request.getParameter("status");
		
		System.out.println("==========================> [" + referenceId + "] [" + status + "]");
		
		PrintWriter out = response.getWriter();
		out.println("Test");
	    out.flush();
	    out.close();
	 	response.flushBuffer();
	}

	private QueueSB createQueueSB() throws Exception
	{
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
		env.put(Context.PROVIDER_URL,SystemEnvironment.getInstance().getBootStrapAddress());
		try
		{
			InitialContext ctx = new InitialContext(env);
			Object s = ctx.lookup("ejb/th/co/msat/motor/queuemanager/QueueSBHome");
			QueueSBHome frHome = (QueueSBHome) PortableRemoteObject.narrow(s,QueueSBHome.class);
			return frHome.create();
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw e;
		} catch (RemoteException e)
		{
			e.printStackTrace();
			throw e;
		} catch (CreateException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}