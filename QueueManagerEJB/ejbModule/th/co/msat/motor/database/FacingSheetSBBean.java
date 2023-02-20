/****************************************************/
/* ----------------- Modify History -----------------*/
/****************************************************/
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/07[yyyy/mm/dd]
 * @description Split queue host follow queue.host.running
 * 				in systemenv.properties
 * 				if QUEUETB.hostname = queue.host.running
 * 					run thread
 * --------------------------------------------------*/

package th.co.msat.motor.database;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.coverpage.CoverPageMessage;

import com.ibm.etools.service.locator.ServiceLocatorManager;
import th.co.msat.motor.queuemanager.QueueSBLocalHome;
import th.co.msat.motor.queuemanager.QueueSBLocal;
import th.co.msat.motor.queuemanager.WorkQueueController;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.vo.QueueVO;
/**
 * Bean implementation class for Enterprise Bean: FacingSheetSB
 */
public class FacingSheetSBBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}

	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}

	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	public static final String COVERPAGE_PRINTING_QUEUE = "Coverpage Printing Queue                          ";

	private final static String STATIC_QueueSBLocalHome_REF_NAME = "ejb/QueueSB";
	private final static Class STATIC_QueueSBLocalHome_CLASS = QueueSBLocalHome.class;
	/**
	 * Put data into data base
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param no
	 * @param username
	 * @param status
	 * @param piority
	 * @param jobgroup
	 * @throws Exception
	 */
	public void putJob(String no,String username,String status,int piority,long jobgroup) throws Exception{
		CoverPageMessage message = new CoverPageMessage();
		message.setNotificationno(no);
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		System.out.println(no);
		message.setUsername(username);
		QueueSBLocal  aQueueSBLocal = createQueueSBLocal();
		aQueueSBLocal.put(COVERPAGE_PRINTING_QUEUE,message,piority,username,"Facing Sheet Printing",status,jobgroup);
		//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
		//WorkQueueController.getInstance().onPut(COVERPAGE_PRINTING_QUEUE);
		//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	}
	
	/**
	 * Connect to QueueSBBean
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @return QueueSBLocal
	 */
	protected QueueSBLocal createQueueSBLocal() {
		QueueSBLocalHome aQueueSBLocalHome = (QueueSBLocalHome) ServiceLocatorManager
				.getLocalHome(STATIC_QueueSBLocalHome_REF_NAME,
						STATIC_QueueSBLocalHome_CLASS);
		try {
			if (aQueueSBLocalHome != null)
				return aQueueSBLocalHome.create();
		} catch (javax.ejb.CreateException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Call another host for run queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/07[yyyy/mm/dd]
	 * @param queueName
	 * @throws Exception
	 */
	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	public void callQueueRunningHost(String queueName) throws Exception
	{
		String hostName = SystemEnvironment.getInstance().getDefaultHostName();
		if (!hostName.endsWith("/"))
		{
			hostName = hostName.trim() + "/";
		}
		
		JobQueueDAO jobqDAO = new JobQueueDAO();
		QueueVO qVO = jobqDAO.getQueue(queueName);
		String queueHostName = qVO.getHostName();
		if (!qVO.getHostName().endsWith("/"))
		{
			queueHostName = queueHostName.trim() + "/";
		}
		
		if (hostName.equals(queueHostName))//compare localhost and queue-host
		{
			System.out.println("====================== " + queueName.trim() + ": execute on localhost[" + queueHostName.trim() + "] ======================");
			WorkQueueController.getInstance().onPut(queueName);
		} else
		{
			
			
			queueHostName = queueHostName.substring(0, queueHostName.length() - 1);//delete "/" at the end of text
			System.out.println("====================== " + queueName.trim() + ": call Queue-host[" + queueHostName.trim() + "] for execute ======================");			
			StringBuffer param = new StringBuffer();
			param.append(queueName);
			System.out.println("====================== call URL:[" + queueHostName + DefaultQueueConstants.QUEUE_SERVLET_PATH + "] for execute ======================");
			connection2Servlet(queueHostName + DefaultQueueConstants.QUEUE_SERVLET_PATH, param.toString());
			System.out.println("====================== " + queueName.trim() + ": execute Queue-host[" + queueHostName.trim() + "] is ready ======================");
		}
	}
	
	/**
	 * Call servlet for call another host to run queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since before 2009/09/07[yyyy/mm/dd]
	 * @param servletNamePath
	 * @param data
	 * @return String
	 */
	private String connection2Servlet(String servletNamePath,String data)
	{ 
		String result = "";
		URL url_;
		InputStream inputStream =null;
		
		try
		{
			url_ = new URL(servletNamePath);
			URLConnection connection = url_.openConnection();
			if (connection instanceof HttpURLConnection)
			{
				HttpURLConnection httpConnection = (HttpURLConnection)connection;
				httpConnection.setRequestMethod("PUT");
				httpConnection.setDoOutput(true);
				httpConnection.connect();
				
				//Initialize output stream
				DataOutputStream outttt = new DataOutputStream( httpConnection.getOutputStream() );
				//write dataList
				outttt.writeBytes(data);
				outttt.flush();
				//wait response
				inputStream = httpConnection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				
				String line = null;
				while ((line = reader.readLine()) != null)
				{
					result += line;
				}
				httpConnection.disconnect();
			}
		} catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
}
