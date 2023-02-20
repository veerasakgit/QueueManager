/*
 * Created on 19 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/****************************************************/
/*----------------- Modify History -----------------*/
/****************************************************/
/*---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/08[yyyy/mm/dd]
 * @description Queue real-time follow database
 * 				don't use server instance
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/18[yyyy/mm/dd]
 * @description Reset queue and recovery pool
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/21[yyyy/mm/dd]
 * @description Duplicate Queue tranlogtb
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/11/04[yyyy/mm/dd]
 * @description Session Bean freeze cause
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/12/21[yyyy/mm/dd]
 * @description Reset queue when start server
 *--------------------------------------------------*/


package th.co.msat.motor.queuemanager.executor;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import th.co.msat.motor.database.vo.TranLog;
import th.co.msat.motor.log.TranLogDAOImpl;
import th.co.msat.motor.logger.AS400InterfaceLogger;
import th.co.msat.motor.queuemanager.WorkQueueController;
import th.co.msat.motor.queuemanager.WorkQueueManager;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.message.Message;
import th.co.msat.motor.queuemanager.pool.ExecutorPool;
import th.co.msat.motor.services.MSATServiceLocator;

import java.lang.Exception;
import java.math.BigDecimal;
/**
 * Class that executor job
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ExecutorImpl implements Executor, Runnable {

	private Connection conn = null;
	private Message message = null;
	private BigDecimal jobid;
	private String queueName;
	private ExecutorPool pool;
	
	//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	private String threadName = null;
	private String jobRefNo = null; 
	
	public void setThreadName(String threadName) {this.threadName = threadName;}
	public String getThreadName() {return this.threadName;}
	
	public void setJobRefNo(String jobRefNo) {this.jobRefNo = jobRefNo;}
	public String getJobRefNo() {return this.jobRefNo;}
	//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	
	//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	private String policyNo = null;
	private String notificationNo = null;
	private String claimNo = null;
	
	/**
	 * Set policy number for job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18
	 * @param policyNo
	 */
	public void setPolicyNo(String policyNo) {this.policyNo = policyNo;}
	/**
	 * Get policy number of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18
	 * @return String
	 */
	public String getPolicyNo() {return this.policyNo;}
	
	/**
	 * Set notification number of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18
	 * @param notificationNo
	 */
	public void setNotificationNo(String notificationNo) {this.notificationNo = notificationNo;}
	/**
	 * Get notification number of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18
	 * @return String
	 */
	public String getNotificationNo() {return this.notificationNo;}
	
	/**
	 * Set claim number of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18
	 * @param claimNo
	 */
	public void setClaimNo(String claimNo) {this.claimNo = claimNo;}
	/**
	 * Get claim number of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18
	 * @return String
	 */
	public String getClaimNo() {return this.claimNo;}
	//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	
	public ExecutorImpl(){
	}
	int timeout;
	Timer timer;

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#getConnection()
	 */
	public Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		return conn;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#setConnection(java.sql.Connection)
	 */
	public void setConnection(Connection conn) throws Exception {
		// TODO Auto-generated method stub
		this.conn = conn;
	}
	
	public void setMessage(Message message){
		this.message= message;
	}
	
	public Message getMessage(){
		return message;
	}
	
	public void setTimeout(int timeout){
		this.timeout = timeout;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		System.err.println("Staring process " +getQueueName()+":"+getJobid());
		onStart();
		try {
			System.out.println(" ------------------------- Start execute testMessage-------------------");
			execute();
			System.out.println(" ------------------------ Finish Job -----------------------------");
			onFinish();
		
		} catch (Exception e) {
			e.printStackTrace();
			onException(e);
			
		}
	
//		System.out.println(queueName);
		//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
		/*System.out.println("return to pool");
		pool.returnPool(this);
		try{
			JobQueueDAO jqdao = new JobQueueDAO();
			if(jqdao.findNextJob(queueName)!=null){
			Timer t = new Timer();
			t.schedule( new TimerTask(){
	
			public void run() {
				try {
					// TODO Auto-generated method stub
					executeOnQueueQvailable(queueName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}},50);
			
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		System.err.println("End process" +getQueueName()+":"+getJobid());*/
		//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	}
	
	/**
	 * Executor next job when avialable
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param qname
	 * @throws Exception
	 */
	private void executeOnQueueQvailable(final String qname) throws Exception{
		System.out.println("-------------------------------------------------");
		System.out.println(" on  available");
		WorkQueueController.getInstance().onQueueAvailabel(queueName);
	}
	
	/**
	 * Sart timer when start job
	 * 
	 * @author Den
	 * @since before 2009/08
	 */
	private void onStart() {
//		JobQueueDAO jqdao = new JobQueueDAO();
//		try {
//			jqdao.updateJobStatus(getJobid(),DefaultQueueConstants.JOB_STATUS_PROCESSING);
////		w.removeRunningThread(this);
////		pool.returnPool(this);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				onTimeout();
				//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
				//doTimeout();
				processTimeout();
				//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
			}},timeout);
		System.out.println(" Start Thread :"+this.hashCode()+" "+new Date());
	}
	/**
	 * End timer
	 * 
	 * @author Den
	 * @since before 2009/08
	 */
	private void onFinish() {
		timer.cancel();
		System.out.println(" Finish Thread :"+this.hashCode()+" "+new Date());
		JobQueueDAO jqdao = new JobQueueDAO();
		try {
			System.out.println(" ===================================Job Complete Update Status ==============================");
			jqdao.updateJobStatus(getJobid(),DefaultQueueConstants.JOB_STATUS_COMPLETE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
		finally
		{
			//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
			try
			{
				writeQueueLog(AS400InterfaceLogger.FLAG_COMPLETE, AS400InterfaceLogger.MESSAGE_COMPLETED);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
			
			WorkQueueController.getInstance().killJobThread(this.threadName, this.queueName, this.jobRefNo);
		}
		//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	}
	
	/**
	 * Process do when exception occur
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param t
	 */
	private void onException(Throwable t){
		timer.cancel();
		System.err.println(" On Exception.");
		System.err.println(t.getMessage());
		//System.out.println(t.getCause());
		JobQueueDAO jqdao = new JobQueueDAO();
		try {
			System.err.println(" ===================================JOB ERROR ============================== "+getJobid());
			System.err.println(t.getMessage());
			jqdao.updateJobStatus(getJobid(),DefaultQueueConstants.JOB_STATUS_ERROR);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
		finally
		{
			//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
			try
			{
				writeQueueLog(AS400InterfaceLogger.FLAG_ERROR, t.getMessage());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
			
			//Start Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
			WorkQueueManager qManager = WorkQueueController.getInstance().getWorkQueueManager(this.queueName);
			if (!qManager.isReset())
			{
				WorkQueueController.getInstance().killJobThread(this.threadName, this.queueName, this.jobRefNo);
			}
			//End Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
		}
		//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	}
	
	/**
	 * Process doing where time-out
	 * 
	 * @author Den
	 * @since before 2009/08
	 */
	private void doTimeout(){
		
		JobQueueDAO jqdao = new JobQueueDAO();
		try {
			System.err.println(" ===================================JOB TIMEOUT ============================== "+getJobid());
			jqdao.updateJobStatus(getJobid(),DefaultQueueConstants.JOB_STATUS_ERROR);
//		w.removeRunningThread(this);
//		pool.returnPool(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" Timeout Thread :"+ getQueueName()+":"+this.hashCode()+" "+new Date());
		Thread.currentThread().stop(new Exception("Time out from queue : "+getQueueName().trim() ));
		throw new RuntimeException(" Time out");
	}
	
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	
	public ExecutorPool getPool() {
		return pool;
	}
	public void setPool(ExecutorPool pool) {
		this.pool = pool;
	}
	
	public BigDecimal getJobid() {
		// TODO Auto-generated method stub
		return jobid;
	}
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#setJobid(java.math.BigDecimal)
	 */
	public void setJobid(BigDecimal jobid) {
		// TODO Auto-generated method stub
		this.jobid = jobid;
	}
	
	//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	/**
	 * Process doing when time-out
	 *
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 */
	private void processTimeout()
	{
		System.err.println(" Timeout Thread :"+ getQueueName()+":"+this.hashCode()+" "+new Date());
		System.err.println(" ===================================JOB TIMEOUT ============================== "+getJobid());
		JobQueueDAO jqdao = new JobQueueDAO();
		try
		{
			jqdao.updateJobStatus(getJobid(),DefaultQueueConstants.JOB_STATUS_ERROR);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
		finally
		{
			try
			{
				writeQueueLog(AS400InterfaceLogger.FLAG_ERROR, AS400InterfaceLogger.MESSAGE_TIMEOUT);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
		
		System.out.println("timeout ================================= kill timer: " + Thread.currentThread().getName() + " =================================");
		Thread.currentThread().interrupt();
		
		WorkQueueController.getInstance().killJobThread(this.threadName, this.queueName, this.jobRefNo);
	}
	//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	
	//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	/**
	 * Write log before terminate job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18[yyyy/mm/dd]
	 * @param flag
	 * @param message
	 */
	public void writeQueueLog(String flag, String message) throws Exception
	{
		try
		{
			//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			/*if (conn == null)
				conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);*/
			//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			
			//Start Queue Tranlog Veerasak Boonchern Aey 2009/10/22[yyyy/mm/dd]
			//TranLogDAOImpl tranlogDAO = new  TranLogDAOImpl();
			//TranLog tranlog = tranlogDAO.getTranLogByRecordId(this.jobid, conn);
			
			//if (tranlog == null)
			//{
				AS400InterfaceLogger logger = new AS400InterfaceLogger();
				logger.addQueueLog(this.policyNo
								, this.notificationNo
								, this.claimNo
								, flag
								, message
								, this.getJobid()
								//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
								//, conn
								, this.queueName
								//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
								);
			//}
			//End Queue Tranlog Veerasak Boonchern Aey 2009/10/22[yyyy/mm/dd]
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (conn != null)
					conn.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
}
