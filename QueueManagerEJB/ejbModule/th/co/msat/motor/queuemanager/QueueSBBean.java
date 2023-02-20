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
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/18[yyyy/mm/dd]
 * @description Reset queue and recovery pool
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/19[yyyy/mm/dd]
 * @description Reset queue on Queue host
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/03/23[yyyy/mm/dd]
 * @description Delete job by Ref.No.
 * --------------------------------------------------*/


package th.co.msat.motor.queuemanager;


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
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.message.Message;
import th.co.msat.motor.queuemanager.pool.ExecutorPool;
import th.co.msat.motor.queuemanager.pool.ExecutorPoolProvider;
import th.co.msat.motor.queuemanager.vo.JobVO;
import th.co.msat.motor.queuemanager.vo.QueueConfigurationVO;
import th.co.msat.motor.queuemanager.vo.QueueVO;
import th.co.msat.motor.services.MSATServiceLocator;
import th.co.msat.motor.database.TranLogDAO;
import th.co.msat.motor.database.TranLogDAOImpl;

/**
 * Bean implementation class for Enterprise Bean: QueueSB
 */
public class QueueSBBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;

//	private final static String STATIC_JobtbLocalHome_REF_NAME = "ejb/Jobtb";

//	private final static Class STATIC_JobtbLocalHome_CLASS = JobtbLocalHome.class;

//	private final static String STATIC_QueuetbLocalHome_REF_NAME = "ejb/Queuetb";

//	private final static Class STATIC_QueuetbLocalHome_CLASS = QueuetbLocalHome.class;

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

	/**
	 * Get all queue name
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @return Collection
	 */
	public Collection getAllQueueName() {
		Collection c = new ArrayList();
		try{
			Collection aCollection = new JobQueueDAO().findAllQueue();
			for (Iterator iter = aCollection.iterator(); iter.hasNext();) {
				QueueVO element = (QueueVO) iter.next();
				//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
				//c.add(element.getQueueName());
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
				//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return c;
	}
	/**
	 * Put data into data base
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
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
			,long jobgroup) throws Exception{
		for (Iterator iter = message.iterator(); iter.hasNext();) {
			Message m= (Message) iter.next();
			JobVO job = new JobVO();
			job.setMessage(m);
			job.setPiority(piority);
			job.setRecordStatus(true);
//			System.out.println("##########################"+status+"################################");
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
		
		//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
		//WorkQueueController.getInstance().onPut(queueName);
		//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	}

	/**
	 * Put data into data base
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
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
			,long jobgroup) throws Exception{
		JobVO job = new JobVO();
		job.setMessage(message);
		job.setPiority(piority);
		job.setRecordStatus(true);
		System.out.println("##########################"+status+"################################");
		job.setStatus(status);
		job.setSubmintUser(username);
		job.setSubmitDate(new Date());
		job.setUpdateDate(new Date());
		job.setUpdateProgram(programId);
		job.setUpdateUser(username);
		job.setReferenceno(message.getReferenceId());
		job.setJobgroup(jobgroup);
		put(queueName,job);
		//Thread.currentThread().sleep(5000);
		//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
		//WorkQueueController.getInstance().onPut(queueName);
		//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
		
	}
	
	/**
	 * Put data into data base
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param queueName
	 * @param jobvo
	 * @throws Exception
	 */
	public void put(String queueName, JobVO jobvo) throws Exception {
		JobQueueDAO dao = new JobQueueDAO();
		System.out.println(queueName);
		QueueVO qLocal = new JobQueueDAO().getQueue(queueName.trim());
		//jobvo.setJobid(dao.getMaxJobtbId());
		dao.insertJob(jobvo,qLocal.getQueueId());
//		dao.getMaxJobtbId();
//		jobLocal.setMessage(DataConverter.toByteArray(jobvo));
//		jobLocal.setPiority(new BigDecimal(jobvo.getPiority()));
//		jobLocal.setQ_msatlib_jobtb_queueid_00001(qLocal);
//		jobLocal.setSubmitdate(DataConverter.to400Date(jobvo.getSubmitDate()));
//		jobLocal.setSubmittime(DataConverter.to400Time(jobvo.getSubmitDate()));
//		jobLocal.setSubmituser(jobvo.getSubmintUser());
//		jobLocal.setUpdatedate(DataConverter.to400Date(jobvo.getUpdateDate()));
//		jobLocal.setUpdatetime(DataConverter.to400Time(jobvo.getUpdateDate()));
//		jobLocal.setUpdateuser(jobvo.getUpdateUser());
//		jobLocal.setUpdateprogram(jobvo.getUpdateProgram());
//		jobLocal.setStatus(jobvo.getStatus());
//		jobLocal.setRecordstatus(DefaultQueueConstants.RECORD_STATUS_AVAILABLE);
//		jobLocal.setReferenceno(jobvo.getReferenceno());
//		Here Calling jobqueue to start sservice.
		
		
	}

	/**
	 * Do nothing
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param q
	 * @throws Exception
	 */
	public void createQueue(QueueVO q) throws Exception {
		/*JobQueueDAO dao = new JobQueueDAO();
		QueuetbLocal qlocal = createQueuetbLocal(dao.getMaxQueutbId());
		qlocal.setActivateclass(q.getAcitvateClass());
		qlocal.setAvailable(q.getAvailable());
		qlocal.setCreatedate(DataConverter.to400Date(q.getCreateDate()));
		qlocal.setCreatetime(DataConverter.to400Time(q.getCreateDate()));
		qlocal.setCreateuser(q.getCreateUser());
		qlocal.setIsterminate(q.isTerminate()? DefaultQueueConstants.QUEUE_TERMINATE:DefaultQueueConstants.QUEUE_READY);
		qlocal.setMessageclass(q.getMessageClass());
		qlocal.setQueuename(q.getQueueName());
		qlocal.setRecordstatus(DefaultQueueConstants.RECORD_STATUS_AVAILABLE);
		qlocal.setSize(q.getSize());
		qlocal.setUpdatedate(DataConverter.to400Date(q.getUpdateDate()));
		qlocal.setUpdateprogram(q.getUpdateProgram());
		qlocal.setUpdatetime(DataConverter.to400Time(q.getUpdateDate()));
		qlocal.setUpdateuser(q.getUpdateUser());
		qlocal.setWorkingtime(q.getWorkingTime());
//		qlocal.setRecordstatus(DefaultQueueConstants.RECORD_STATUS_AVAILABLE);*/
	}
	
	/**
	 * Delete job from data base
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param jobid
	 * @throws Exception
	 */
	public void deleteJob(BigDecimal jobid) throws Exception{
		JobQueueDAO dao = new JobQueueDAO();
		dao.deleteJob(jobid);
	}
	
	/**
	 * Update terminate status of queue
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param qname
	 * @param status
	 * @throws Exception
	 */
	public void updateQueueTerminate(String qname,String status) throws Exception{
		JobQueueDAO dao = new JobQueueDAO();
		dao.setQueueTerminateStatus(qname,status);
		
		if(status.equals(DefaultQueueConstants.QUEUE_READY)){
		 	//Start Start Split queue host Veerasak Boonchen 2009/09/07[yyyy/mm/dd]
			//WorkQueueController.getInstance().onPut(qname);
			//End Start Split queue host Veerasak Boonchen 2009/09/07[yyyy/mm/dd]
		}
		
	}
	
	/**
	 * Checking terminate status of queue
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param queueName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isQueueTerminate(String queueName) throws Exception{
		JobQueueDAO jqdao = new JobQueueDAO();
		return jqdao.isQueueTerminate(queueName	);
	}
	
	/**
	 * Update job status
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param jobId
	 * @param jobstatus
	 * @throws Exception
	 */
	public void updateJobStatus(BigDecimal jobId,String jobstatus) throws Exception{
		JobQueueDAO jqdao = new JobQueueDAO();
		String jst = jqdao.getJobStatus(jobId);
		jqdao.updateJobStatus(jobId,jobstatus);
		
		
		if(jobstatus.equals(DefaultQueueConstants.JOB_STATUS_RELEASE)&& (DefaultQueueConstants.JOB_STATUS_RELEASE.equals(jst)) ){
		 	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
			//WorkQueueController.getInstance().onPut(jqdao.getQueueName(jobId));
			//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
		}
		
	}
	
	/**
	 * Update job status
	 * @param c
	 * @param status
	 * @throws Exception
	 */
	public void updateJobStatus(Collection c,String status) throws Exception{
		JobQueueDAO jqdao = new JobQueueDAO();
		Map jobMap = jqdao.getJobStatus(c);
		jqdao.updateJobStatus(c,status);
		
		for (Iterator iter = c.iterator(); iter.hasNext();) {
			BigDecimal jobid = (BigDecimal) iter.next();
			
			if(status.equals(DefaultQueueConstants.JOB_STATUS_RELEASE)
					&& jobMap.get(new Integer(jobid.intValue())).equals(DefaultQueueConstants.JOB_STATUS_RELEASE)){
				//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				//WorkQueueController.getInstance().onPut(jqdao.getQueueName(jobid));
				//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				break;
			}
		}
		
	}
	
	/**
	 * Search data
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
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
			,String refNo) throws Exception{
		JobQueueDAO jqdao = new JobQueueDAO();
		return jqdao.searchJob(qname,jobGroup,submitUser,submitDateFrom,submitDateTo,status,refNo, true);
	}
	
	/**
	 * Awake WorQueuekController
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @throws Exception
	 */
	public void stimulateQueue() throws Exception{
		WorkQueueController wqc = WorkQueueController.getInstance();
		wqc.systemStart();
	}
	
	/**
	 * Get data's queue
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param qname
	 * @return QueueConfigurationVO
	 * @throws Exception
	 */
	public QueueConfigurationVO getQueueConfiguration(String qname) throws Exception{
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
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param qname
	 * @throws Exception
	 */
	public void resetQueue(String qname) throws Exception{
		//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
		/*ExecutorPool pool = ExecutorPoolProvider.getInstance().getExecutorPool(qname);
		pool.reset();*/
		//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
		//WorkQueueController.getInstance().resetQueue(qname);
		callQueueRunningHost(qname, DefaultQueueConstants.PROCESS_RESET_QUEUE);
		//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
		//End Reset queue on Queue host Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
//		ExecutorPoolProvider.getInstance().resetQueue(qname);
	}
	
	/*protected JobtbLocal createJobtbLocal(BigDecimal jobid) {
		JobtbLocalHome aJobtbLocalHome = (JobtbLocalHome) ServiceLocatorManager
				.getLocalHome(STATIC_JobtbLocalHome_REF_NAME,
						STATIC_JobtbLocalHome_CLASS);
		try {
			if (aJobtbLocalHome != null)
				return aJobtbLocalHome.create(jobid);
		} catch (javax.ejb.CreateException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		}
		return null;
	}*/

	/*protected QueuetbLocal find_QueuetbLocalHome_findByQueueName(
			String queueName) {
		QueuetbLocalHome aQueuetbLocalHome = (QueuetbLocalHome) ServiceLocatorManager
				.getLocalHome(STATIC_QueuetbLocalHome_REF_NAME,
						STATIC_QueuetbLocalHome_CLASS);
		try {
			if (aQueuetbLocalHome != null)
				return aQueuetbLocalHome.findByQueueName(queueName);
		} catch (javax.ejb.FinderException fe) {
			// TODO Auto-generated catch block
			fe.printStackTrace();
		}
		return null;
	}
	protected QueuetbLocal createQueuetbLocal(BigDecimal queueid) {
		QueuetbLocalHome aQueuetbLocalHome = (QueuetbLocalHome) ServiceLocatorManager
				.getLocalHome(STATIC_QueuetbLocalHome_REF_NAME,
						STATIC_QueuetbLocalHome_CLASS);
		try {
			if (aQueuetbLocalHome != null)
				return aQueuetbLocalHome.create(queueid);
		} catch (javax.ejb.CreateException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		}
		return null;
	}
	

	protected Collection find_QueuetbLocalHome_findAllQueue() {
		QueuetbLocalHome aQueuetbLocalHome = (QueuetbLocalHome) ServiceLocatorManager
				.getLocalHome(STATIC_QueuetbLocalHome_REF_NAME,
						STATIC_QueuetbLocalHome_CLASS);
		try {
			if (aQueuetbLocalHome != null)
				return aQueuetbLocalHome.findAllQueue();
		} catch (javax.ejb.FinderException fe) {
			// TODO Auto-generated catch block
			fe.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * Call another host for run queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/10/19[yyyy/mm/dd]
	 * @param queueName
	 */
	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	public void callQueueRunningHost(String queueName) throws Exception
	//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
	{
		callQueueRunningHost(queueName, DefaultQueueConstants.PROCESS_EXECUTE);
	}
	/**
	 * Call another host for run queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/10/19[yyyy/mm/dd]
	 * @param queueName
	 * @param process
	 * @throws Exception
	 */
	public void callQueueRunningHost(String queueName, String process) throws Exception
	//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
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
			//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			//WorkQueueController.getInstance().onPut(queueName);
			if (process.equals(DefaultQueueConstants.PROCESS_EXECUTE))
				WorkQueueController.getInstance().onPut(queueName.trim());
			else if (process.equals(DefaultQueueConstants.PROCESS_RESET_QUEUE))
				WorkQueueController.getInstance().resetQueue(queueName.trim());
			//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
		} else
		{
			queueHostName = queueHostName.substring(0, queueHostName.length() - 1);//delete "/" at the end of text
			System.out.println("====================== " + queueName.trim() + ": call Queue-host[" + queueHostName.trim() + "] for " + process + " ======================");			
			StringBuffer param = new StringBuffer();
			param.append(queueName.trim());
			//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			param.append("," + process);
			//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			System.out.println("====================== call URL:[" + queueHostName + DefaultQueueConstants.QUEUE_SERVLET_PATH + "] for " + process + " ======================");
			connection2Servlet(queueHostName + DefaultQueueConstants.QUEUE_SERVLET_PATH, param.toString());
			System.out.println("====================== " + queueName.trim() + ": " + process + " on Queue-host[" + queueHostName.trim() + "] is ready ======================");
		}
	}
	
	/**
	 * Call servlet for call another host to run queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/10/19[yyyy/mm/dd]
	 * @param servletNamePath
	 * @param param
	 * @return String
	 */
	private String connection2Servlet(String servletNamePath, String param)
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
				outttt.writeBytes(param);
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
	
	//Start Delete job by Ref.No. Veerasak Boonchern Aey 2010/03/23[yyyy/mm/dd]
	public int deleteJobByReferenceNo(String queueName, String refNo) throws Exception
	{
		int output = -1;
		
		try
		{
			JobQueueDAO jobqDAO = new JobQueueDAO();
			QueueVO vo = jobqDAO.getQueue(queueName);
			output = jobqDAO.deleteJobByReferenceNo(vo.getQueueId(), refNo);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return output;
	}
	//End Delete job by Ref.No. Veerasak Boonchern Aey 2010/03/23[yyyy/mm/dd]
}
