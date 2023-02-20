/***************************************************/
/* -------------- Create Description ------------- */
/***************************************************/
/* --------------------------------------------------
 * @author Veerasak Boonchern Aey
 * @since 2009/11/06[yyyy/mm/dd]
 * @description Instead of QueueSBBean
 ---------------------------------------------------*/
/****************************************************/
/* ---------------- Modify History ---------------- */
/* ---------------------------------------------------
 * @modifier 
 * @since 
 * @description 
 * -------------------------------------------------*/


package th.co.queuemanager;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.queuemanager.QueueConfiguration;
import th.co.msat.motor.queuemanager.QueueConfigurationProvider;
import th.co.msat.motor.queuemanager.WorkQueueController;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.message.Message;
import th.co.msat.motor.queuemanager.pool.ExecutorPool;
import th.co.msat.motor.queuemanager.pool.ExecutorPoolProvider;
import th.co.msat.motor.queuemanager.vo.JobVO;
import th.co.msat.motor.queuemanager.vo.QueueConfigurationVO;
import th.co.msat.motor.queuemanager.vo.QueueVO;

/**
 * Bean implementation class for Enterprise Bean: QueueSB
 * 
 * @author Veerasak Boonchern Aey
 * @since 2009/11/06[yyyy/mm/dd]
 */
public class QueueBean
{
	/**
	 * Get all queue name
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @return Collection
	 * @throws Exception
	 */
	public Collection getAllQueueName() throws Exception
	{
		Collection c = new ArrayList();
		try
		{
			Collection aCollection = new JobQueueDAO().findAllQueue();
			for (Iterator iter = aCollection.iterator(); iter.hasNext();)
			{
				QueueVO element = (QueueVO) iter.next();
				String hostName = SystemEnvironment.getInstance().getDefaultHostName();
				if (!hostName.endsWith("/"))
				{
					hostName = hostName.trim() + "/";
				}
				
				String queueHostName = element.getHostName();
				if (!element.getHostName().endsWith("/"))
				{
					queueHostName = queueHostName.trim() + "/";
				}
				
				if (hostName.equals(queueHostName))
					c.add(element.getQueueName());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return c;
	}
	
	/**
	 * Put data into database
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param queueName
	 * @param message
	 * @param piority
	 * @param username
	 * @param programId
	 * @param status
	 * @param jobgroup
	 * @throws Exception
	 */
	public void putCollection(String queueName
							, Collection message
							,int piority
							,String username
							,String programId
							,String status
							,long jobgroup) throws Exception
	{
		for (Iterator iter = message.iterator(); iter.hasNext();)
		{
			Message m= (Message) iter.next();
			JobVO job = new JobVO();
			job.setMessage(m);
			job.setPiority(piority);
			job.setRecordStatus(true);
			job.setStatus(status);
			job.setSubmintUser(username);
			job.setSubmitDate(new Date());
			job.setUpdateDate(new Date());
			job.setUpdateProgram(programId);
			job.setUpdateUser(username);
			job.setReferenceno(m.getReferenceId());
			job.setJobgroup(jobgroup);
			put(queueName,job);
		}
	}

	/**
	 * Put data into data base
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param queueName
	 * @param message
	 * @param piority
	 * @param username
	 * @param programId
	 * @param status
	 * @param jobgroup
	 * @throws Exception
	 */
	public void put(String queueName
						, Message message
						,int piority
						,String username
						,String programId
						,String status
						,long jobgroup) throws Exception
	{
		JobVO job = new JobVO();
		job.setMessage(message);
		job.setPiority(piority);
		job.setRecordStatus(true);
		job.setStatus(status);
		job.setSubmintUser(username);
		job.setSubmitDate(new Date());
		job.setUpdateDate(new Date());
		job.setUpdateProgram(programId);
		job.setUpdateUser(username);
		job.setReferenceno(message.getReferenceId());
		job.setJobgroup(jobgroup);
		put(queueName,job);
	}
	
	/**
	 * Put data into database
	 * @param queueName
	 * @param jobvo
	 * @throws Exception
	 */
	public void put(String queueName, JobVO jobvo) throws Exception
	{
		JobQueueDAO dao = new JobQueueDAO();
		System.out.println(queueName);
		QueueVO qLocal = new JobQueueDAO().getQueue(queueName.trim());
		dao.insertJob(jobvo,qLocal.getQueueId());
	}

	/**
	 * Do nothing
	 * @param q
	 * @throws Exception
	 */
	public void createQueue(QueueVO q) throws Exception
	{
	}
	
	/**
	 * Delete data from database
	 * @param jobid
	 * @throws Exception
	 */
	public void deleteJob(BigDecimal jobid) throws Exception
	{
		JobQueueDAO dao = new JobQueueDAO();
		dao.deleteJob(jobid);
	}
	
	/**
	 * Update terminate status of queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param qname
	 * @param status
	 * @throws Exception
	 */
	public void updateQueueTerminate(String qname,String status) throws Exception
	{
		JobQueueDAO dao = new JobQueueDAO();
		dao.setQueueTerminateStatus(qname,status);		
	}
	
	/**
	 * Checking terminate status of queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param queueName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isQueueTerminate(String queueName) throws Exception
	{
		JobQueueDAO jqdao = new JobQueueDAO();
		return jqdao.isQueueTerminate(queueName	);
	}
	
	/**
	 * Update job status
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param jobId
	 * @param jobstatus
	 * @throws Exception
	 */
	public void updateJobStatus(BigDecimal jobId,String jobstatus) throws Exception
	{
		JobQueueDAO jqdao = new JobQueueDAO();
		String jst = jqdao.getJobStatus(jobId);
		jqdao.updateJobStatus(jobId,jobstatus);
	}
	
	/**
	 * Update job status
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param c
	 * @param status
	 * @throws Exception
	 */
	public void updateJobStatus(Collection c,String status) throws Exception
	{
		JobQueueDAO jqdao = new JobQueueDAO();
		Map jobMap = jqdao.getJobStatus(c);
		jqdao.updateJobStatus(c,status);
	}
	
	/**
	 * Search job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param qname
	 * @param jobGroup
	 * @param submitUser
	 * @param submitDateFrom
	 * @param submitDateTo
	 * @param status
	 * @param refNo
	 * @return Collection
	 * @throws Exception
	 */
	public Collection searchJob(String qname
								,String jobGroup
								,String submitUser
								,Date submitDateFrom
								,Date submitDateTo
								,String[] status
								,String refNo) throws Exception
	{
		JobQueueDAO jqdao = new JobQueueDAO();
		return jqdao.searchJob(qname,jobGroup,submitUser,submitDateFrom,submitDateTo,status,refNo, true);
	}
	
	/**
	 * Awake WorkQueueController
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @throws Exception
	 */
	public void stimulateQueue() throws Exception
	{
		WorkQueueController wqc = WorkQueueController.getInstance();
		wqc.systemStart();
	}
	
	/**
	 * Get data of queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param qname
	 * @return QueueConfigurationVO
	 * @throws Exception
	 */
	public QueueConfigurationVO getQueueConfiguration(String qname) throws Exception
	{
		QueueConfiguration qc = QueueConfigurationProvider.getInstance().getQueueConfiguration(qname);
		ExecutorPool pool = ExecutorPoolProvider.getInstance().getExecutorPool(qname);
		QueueConfigurationVO vo =  new QueueConfigurationVO();
		vo.setAvailabelThread(pool.getAvailable());
		JobQueueDAO jqdao = new JobQueueDAO();
		vo.setSwitchStatus(jqdao.getQueueTerminateStatus(qname));
		vo.setHoldingJob(jqdao.getHoldJob(qname));
		vo.setProcessingJob(jqdao.getProcessingJob(qname));
		vo.setReleaseJob(jqdao.getReleaseJob(qname));
		vo.setSize(jqdao.getQueueSize(qname));
		return vo;
	}
	
	/**
	 * Reset queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param qname
	 * @throws Exception
	 */
	public void resetQueue(String qname) throws Exception
	{
		callQueueRunningHost(qname, DefaultQueueConstants.PROCESS_RESET_QUEUE);
	}
	
	/**
	 * Call another host for run queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param queueName
	 * @throws Exception
	 */
	public void callQueueRunningHost(String queueName) throws Exception
	{
		callQueueRunningHost(queueName, DefaultQueueConstants.PROCESS_EXECUTE);
	}
	/**
	 * Call another host for run queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/11/06[yyyy/mm/dd]
	 * @param queueName
	 * @param process
	 * @throws Exception
	 */
	public void callQueueRunningHost(String queueName, String process) throws Exception
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
			System.out.println("====================== " + queueName.trim() + ": " + process + " on localhost[" + queueHostName.trim() + "] ======================");
			if (process.equals(DefaultQueueConstants.PROCESS_EXECUTE))
				WorkQueueController.getInstance().onPut(queueName.trim());
			else if (process.equals(DefaultQueueConstants.PROCESS_RESET_QUEUE))
				WorkQueueController.getInstance().resetQueue(queueName.trim());
		} else
		{
			queueHostName = queueHostName.substring(0, queueHostName.length() - 1);//delete "/" at the end of text
			System.out.println("====================== " + queueName.trim() + ": call Queue-host[" + queueHostName.trim() + "] for " + process + " ======================");			
			StringBuffer param = new StringBuffer();
			param.append(queueName.trim());
			param.append("," + process);
			System.out.println("====================== call URL:[" + queueHostName + DefaultQueueConstants.QUEUE_SERVLET_PATH + "] for " + process + " ======================");
			connection2Servlet(queueHostName + DefaultQueueConstants.QUEUE_SERVLET_PATH, param.toString());
			System.out.println("====================== " + queueName.trim() + ": " + process + " on Queue-host[" + queueHostName.trim() + "] is ready ======================");
		}
	}
	
	/**
	 * Call servlet for call another host to run queue
	 * @param servletNamePath
	 * @param data
	 * @return
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
}
