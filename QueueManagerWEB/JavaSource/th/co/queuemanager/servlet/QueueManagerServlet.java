/**********************************************/
/*------------ Create Description ------------*/
/**********************************************/
/*---------------------------------------------
 * @author Veerasak Boonchern Aey
 * @since 2009/09/10[yyyy/mm/dd]
 * @description for other host call QueueManager
 * 				in this host
 ---------------------------------------------*/
/**********************************************/
/*-------------- Modify History --------------*/
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/19[yyyy/mm/dd]
 * @description Reset queue on Queue host
 * --------------------------------------------------*/


package th.co.queuemanager.servlet;

import java.io.BufferedReader;
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
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;

/**
 * Servlet that another module use for start queue
 * 
 * @author Veerasak Boonchern Aey
 * @since 2009/09/10[yyyy/mm/dd] 
 */
public class QueueManagerServlet extends HttpServlet implements Servlet
{
	public QueueManagerServlet()
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
	
	public void doPut(HttpServletRequest arg0, HttpServletResponse  arg1)throws IOException, ServletException
	{
		doProcess(arg0, arg1);
	}
	
	/**
	 * Call QueueSBBean for run queue
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/10[yyyy/mm/dd]
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		StringBuffer output = new StringBuffer("");
	 	
	 	BufferedReader bf = new BufferedReader(request.getReader());
	 	String data="";
		String ln = null; 
		try
		{
			int line=0;
			while ((ln = bf.readLine()) != null )
			{ 
				data+=ln;
				line++;
			}
			bf.close();
		} catch(IOException e) {}
	 	String param[] = data.split(",");
		
		String queueName = param[0];
		//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
		String process = null;
		if (param.length > 1)
			process = param[1];
		//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
		//String process = DefaultQueueConstants.PROCESS_EXECUTE;
		
		try
		{
			QueueSB qSB = createQueueSB();
			//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			//qSB.callQueueRunningHost(queueName);
			if (process != null && !process.equals(""))
				qSB.callQueueRunningHost(queueName, process);
			else
				qSB.callQueueRunningHost(queueName);
			//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			output.append("0");
			
		} catch (Exception e)
		{
			output.append("-1");
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.println(output.toString());
	    out.flush();
	    out.close();
	 	response.flushBuffer();
	}
	
	/**
	 * Connect to QueueSBBean
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/10[yyyy/mm/dd]
	 * @return QueueSB
	 * @throws Exception
	 */
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
	
	/**
	 * Checking string is null
	 * @param input
	 * @return input.trim() if input != null, "" if input = null
	 */
	private String chkStrNull(String input)
	{
		StringBuffer output = new StringBuffer("");
		if (input != null) output.append(input.trim());
		return output.toString();
	}
}