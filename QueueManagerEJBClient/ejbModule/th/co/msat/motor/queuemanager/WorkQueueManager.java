/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/*****************************************************/
/*------------------ Modify History -----------------*/
/*****************************************************/
/* ---------------------------------------------------
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
 * @since 2009/10/12[yyyy/mm/dd]
 * @description Synchronized queue
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/19[yyyy/mm/dd]
 * @description Reset queue on Queue host
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/12/21[yyyy/mm/dd]
 * @description Reset queue when start server
 * -------------------------------------------------*/


package th.co.msat.motor.queuemanager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.executor.Executor;
import th.co.msat.motor.queuemanager.executor.ExecutorFactory;

import th.co.msat.motor.queuemanager.vo.JobVO;
import th.co.msat.motor.queuemanager.vo.QueueVO;

/**
 * Class that contain Executor
 * This is controller of queue(Update 2009/09/08[yyyy/mm/dd])
 * @author ituser3
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WorkQueueManager {
	//private QueueManager queueManager;

	//private int avalilabelThread;
	//private Collection runningThread;

	//private QueueConfigurationProvider qConfigProvider = QueueConfigurationProvider.getInstance();

	private String queueName;
	//private JobVO jobvo;
	//private Executor e;

	//private ExecutorPoolImpl exPool;

	//private WorkQueueController wqc;
	
	//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	Map allJobThread = null;
	//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	
	//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	private boolean isReset = false;
	//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	public WorkQueueManager(String queueName) {
		this.queueName = queueName;
		//this.jobvo = jobvo;
		//this.e=e;
		//this.wqc = wqc;
		//runningThread = new ArrayList();
		
		//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
		allJobThread = new HashMap();
		//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	}
	
	/**
	 * Executor job
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param jobvo
	 * @param e
	 * @throws Exception
	 */
	public void execute(JobVO jobvo,Executor e) throws Exception {
		/*
		 * **********************************************************************************************************************
		 */
		/*
		//if (exPool == null) {
			//ExecutorPoolProvider_Bak.getInstance().addExecutorPool(qConfigProvider.getQueueConfiguration(queueName));
			//exPool = ExecutorPoolProvider_Bak.getInstance().getExecutorPool(
			//queueName);
			//exPool.initial();
		//}
		//TODO
		//1. check isEmpty() from DefaultQueueManager
		//2. if not empty then getMessage
		//3. execute by executor as a thread. decrese avaliable
		//4. if a work return result
		Connection conn = getConnection();
		if (null == queueManager)
			queueManager = QueueManagerFactory.getInstance().getQueuemanager(queueName);
			
		while (true) {
		if (exPool.hasNext()) {
			System.out.println(" Executor Pool has availabel.");
			Message m = queueManager.getNextMessage();
			if(null==m){
				System.out.println(" Has no message then terminate. ");
				break;
			}
			Executor ex = (Executor) exPool.getNext();
			//ExecutorFactory.getInstance().getExecutor("th.co.msat.motor.queuemanager.ScrapExecutor");
			ex.setConnection(conn);
			
			ex.setMessage(m);
			ex.setTimeout(qConfigProvider.getQueueConfiguration(queueName).getTimeout());
			qConfigProvider.getQueueConfiguration(queueName).setAvalilableThread(
							//qConfigProvider.getQueueConfiguration(queueName).getAvalilableThread()-1);
			Thread ext = new Thread(ex);
			runningThread.add(ex);
				ext.start();
			}else{
				
			}
		}
		//TODO Release itself from WorkQueueManager.
		//wqc.releaseWorkQueueManager(this);*/
		/*
		 * **********************************************************************************************************************
		 */
		
		QueueConfiguration qc = QueueConfigurationProvider.getInstance().getQueueConfiguration(queueName);
		System.out.println("===================================================================================");
		System.out.println(" queue name:"+queueName);
		System.out.println("WorkQueueManager execute for :"+qc.getQueueName());
		//Check queue is turnoff first!!
		if(!qc.isTurnoff()){
			System.out.println("Queue not turn off.");
			//Executor Pool, each queue has a executor pool them self;
			//ExecutorPool exepool = ExecutorPoolProvider.getInstance().getExecutorPool(queueName);
			//Check Executor Pool has an available Executor;
			//if(exepool.hasNext()){
				//System.out.println(" exe pool has next ");
				//Executor extor = exepool.getNext();
				//if(extor!=null){
					//QueueManager qm = QueueManagerFactory.getInstance().getQueuemanager(queueName);
					//JobVO jobvo = qm.getNextJob();
					
					//if(null!=jobvo){
						System.out.println(" has job ! ");
						e.setMessage(jobvo.getMessage());
						e.setConnection(getConnection());
						e.setTimeout(qc.getTimeout());
						e.setJobid(jobvo.getJobid());
						Thread t = new Thread(e);
						t.start();
					//}else{
						//exepool.returnPool(extor);
					//}
				//}
			//}
		}
	}

	/**
	 * Do nothing
	 * @return Connection
	 */
	private Connection getConnection() {
		//TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * 
	 * Check on execute thread and remove all dead thread from runningThread
	 * collection. //
	 */
	//	
	//public void removeRunningThread(Executor ex){
		//runningThread.remove(ex);
		//qConfigProvider.getQueueConfiguration(queueName).setAvalilableThread(
		//qConfigProvider.getQueueConfiguration(queueName).getAvalilableThread()+1);
	//}
	//	
	//private boolean isSomeThreadAlive(){
		//boolean b = false;
		//for (Iterator iter = runningThread.iterator(); iter.hasNext();) {
			//Executor ex = (Executor) iter.next();
			//b=b||ex.isAlive();
			//
		//}
		//return b;
	//}
	//public String getQueueName() {
		//return queueName;
	//}
	//
	//public void setQueueName(String queueName) {
	//this.queueName = queueName;
	//}

	//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	/**
	 * Get queueName
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @return String
	 */
	public String getQueueName() {return this.queueName;}
	
	//Start synchronized Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	/**
	 * Start thred of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 */
	public void runJobThread() throws Exception
	{
		String output = null;
		
		System.out.println("======================== run job thread method ===============================");
		
		JobQueueDAO jobqDAO = new JobQueueDAO();
		QueueVO qVO = jobqDAO.getQueue(this.queueName);
		
		if (!qVO.isTerminate())//queue is terminate
		{
			int test = 0;
			while(true)
			//while (test == 0)
			{test = 1;
				//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
				if (this.isReset)
					break;
				//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
				
				//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
				qVO = jobqDAO.getQueue(this.queueName);
				//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
				int poolAvailable = qVO.getAvailable().intValue();
				System.out.println(poolAvailable);
				
				if (poolAvailable > 0)//pool is available
				{
					System.out.println("run job thread method: ======================== queue avaliable ===============================");
					
					JobVO jobVO = jobqDAO.findNextJob(this.queueName);
					System.out.println("run job thread method: ==================== get next job completed ============================");
					if (jobVO != null)//has job
					{
						System.out.println(" has job ! ");
						
						jobqDAO.increaseQueuePool(this.queueName, -1);
						//
						Thread.currentThread().sleep(800);
						System.out.println("run job thread method: ======================= update queue pool ==============================");
						
						jobqDAO.updateJobStatusForceProcess(jobVO.getJobid(), DefaultQueueConstants.JOB_STATUS_PROCESSING);//update job status
						
						Executor exe = ExecutorFactory.getInstance().getExecutor(qVO.getAcitvateClass().trim());
						exe.setMessage(jobVO.getMessage());
						exe.setConnection(getConnection());
						exe.setTimeout(qVO.getWorkingTime().intValue());
						exe.setJobid(jobVO.getJobid());
						exe.setJobRefNo(jobVO.getReferenceno().trim());
						exe.setQueueName(this.queueName);
						
						Thread thread = new Thread(exe);
						thread.setName(queueName + " " + thread.getName());
						exe.setThreadName(thread.getName());
						
						thread.start();
						allJobThread.put(thread.getName(), thread);
					} else
					{
						break;
					}
				} else
				{
					Thread.currentThread().sleep(800);
				}
			}
		} else
		{
			WorkQueueController.getInstance().removeQueueManager(queueName);
		}
	}
	
	/**
	 * Kill thread of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param threadName
	 * @throws Exception
	 */
	public void killJobThread(String threadName) throws Exception
	{
		killJobThread(threadName, null);
	}
	/**
	 * Kill thread of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param threadName
	 * @param jobRefNo
	 * @throws Exception
	 */
	public void killJobThread(String threadName, String jobRefNo) throws Exception
	{
		System.out.println("kill job thread method: begin =======================================================");
		/*System.out.println("kill job thread method: queue name [" + this.queueName + "]");
		System.out.println("kill job thread method: job ref-no name [" + jobRefNo + "]");
		System.out.println("kill job thread method: thread name [" + threadName + "]");*/
		
		JobQueueDAO jobqDAO = new JobQueueDAO();
		
		if (allJobThread.get(threadName) != null)
		{
			Thread thread = (Thread)allJobThread.get(threadName);
			thread.interrupt();
			allJobThread.remove(threadName);
			
			jobqDAO.increaseQueuePool(this.queueName, 1);
			System.out.println("kill job thread method: ==================== update queue pool ==========================");
			System.out.println("kill job thread method: completed =======================================================");
		}
		
		//check job is doing and has next job
		JobVO jobVO = jobqDAO.findNextJob(this.queueName);
		if (allJobThread.size() == 0 && jobVO == null)
		{
			WorkQueueController.getInstance().removeQueueManager(this.queueName);
		}
	}
	
	/**
	 * Kill all thread of job
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @throws Exception
	 */
	public void killAllJobThread() throws Exception
	{
		System.out.println("kill ALL job thread method: begin =======================================================");
		System.out.println("kill ALL job thread method: queue name [" + this.queueName + "]");
		System.out.println("--------------------------------------------------------------");
		
		JobQueueDAO jobqDAO = new JobQueueDAO();
		
		Collection allThread = allJobThread.values();
		int numJob = 0;
		for (Iterator iter = allThread.iterator(); iter.hasNext();)
		{
			Thread thread = (Thread)iter.next();
			//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			String threadName = thread.getName();
			thread.interrupt();
			
			/*allJobThread.remove(threadName);*/
			//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
			
			numJob++;
		}
		allJobThread.clear();
		jobqDAO.increaseQueuePool(this.queueName, numJob);
		System.out.println("kill ALL job thread method: terminated ===================================================");
		
		WorkQueueController.getInstance().removeQueueManager(this.queueName);
	}
	//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	
	//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	/**
	 * New reset queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/18[yyyy/mm/dd]
	 * @throws Exception
	 */
	synchronized void resetQueue() throws Exception
	{
		this.isReset = true;
		Thread.currentThread().sleep(800);
		this.killAllJobThread();
	}
	//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	
	//Start Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
	/**
	 * Checking queue is reset
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/12/21[yyyy/mm/dd]
	 */
	public boolean isReset() {return this.isReset;}
	//End Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
}
