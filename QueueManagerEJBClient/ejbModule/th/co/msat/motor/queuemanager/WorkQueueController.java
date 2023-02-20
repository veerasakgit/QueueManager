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
 * @description Trim Queue name
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/12/21[yyyy/mm/dd]
 * @description Reset queue when start server
 * -------------------------------------------------*/


package th.co.msat.motor.queuemanager;


import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.ibm.etools.service.locator.ServiceLocatorManager;
import com.ibm.jvm.zseries.Execute;

import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.database.QueuetbLocalHome;
import th.co.msat.motor.queuemanager.executor.Executor;
import th.co.msat.motor.queuemanager.pool.ExecutorPool;
import th.co.msat.motor.queuemanager.pool.ExecutorPoolProvider;
import th.co.msat.motor.queuemanager.vo.JobVO;
import th.co.msat.motor.queuemanager.vo.QueueVO;

//Aey test
import th.co.msat.motor.config.*;

/**
 * Instance in server application\n
 * This is a manager of queue(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WorkQueueController implements QueueTrigerEvent {
	//	 work queue manager collection 
//	Map workQueueManagers = new HashMap();
	
	private static WorkQueueController instance;
	
	//Start Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
	private Map allQueueManager = null;//contain WorkQueueManager
	private Map allQueueThread = null;//contain Thread of QueueManager
	//End Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]

	private final static String STATIC_QueuetbLocalHome_REF_NAME = "ejb/Queuetb";
	private final static Class STATIC_QueuetbLocalHome_CLASS = QueuetbLocalHome.class;
	
	/**
	 * Create instance of WorkQueueController
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return WorkQueueController
	 */
	public static WorkQueueController getInstance() {
		if (null == instance)
			instance = new WorkQueueController();
		System.out.println("===="+WorkQueueController.class.getResource("."));
		System.out.println("===========================================");
		System.out.println("=================="+instance.hashCode()+"=====================");
		System.out.println("===========================================");
		return instance;
	}
	
	//Start Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
	public WorkQueueController()
	{
		allQueueManager = new HashMap();
		allQueueThread = new HashMap();
	}
	//End Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.QueueTrigerEvent#onPut()
	 */
	public void onPut(final String qname) throws Exception {
		// TODO Auto-generated method stub
		//		Start WorkQueueManager here ;
		//		Apply Queue Configuration to workqueuemanager here ;
//		QueueConfigurationProvider qcp = QueueConfigurationProvider
//				.getInstance();
//		QueueConfiguration qconfig = qcp.getQueueConfiguration(queueName);
//		WorkQueueManager wqm = findWorkQueue(queueName);
//		if (null == wqm) {
//			wqm = createWorkQueueManager(queueName);
//			wqm.execute();
//		}
//		QueueManager qm = QueueManagerFactory.getInstance().getQueuemanager(qname);
//		Message m= null;
//		while(( m = qm.getNextMessage())!=null){
//			
//		}
		//Start Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
		/*QueueManager qm = QueueManagerFactory.getInstance().getQueuemanager(qname);
		System.out.println("================"+qname);
		System.out.println(""+qm);*/
		//End Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
//		JobVO jobvo = qm.getNextJob();
//		if(null!=qm.getNextMessage()){
		
//		 Timer t = new Timer();
//		    t.schedule(new TimerTask(){
//			public void run() {
//				try {
					// TODO Auto-generated method stub
					//Start Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
					//executeOnPut(qname);//Old queue(use pool as instance)
					executeWorkqueue(qname);//new queue(use pool follow database)
					//End Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}},50);
		
//		}
	}
	
	/**
	 * Create queue manager for run queue
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param qname
	 * @throws Exception
	 */
	private void executeOnPut( String qname) throws Exception{
//		System.out.println("==============================================================================");
		System.out.println("======================== execute on put method ===============================");
		ExecutorPool ep = ExecutorPoolProvider.getInstance().getExecutorPool(qname);
//		Executor e =null;
		QueueManager qm = QueueManagerFactory.getInstance().getQueuemanager(qname);
//		JobVO jobvo = qm.getNextJob();
//		System.out.println("ep.hasNext? : "+ep.hasNext()+" !qm.isEmpty() ?:"+(!qm.isEmpty()));
		while(true){
//		while(null!=jobvo){
			Thread.currentThread().sleep(800);
			Executor e = ep.getNext();
			try{
				if(e!=null){
					System.out.println("execute on put method======================== queue avaliable ===============================");
					JobVO j = qm.getNextJob();
					System.out.println("execute on put method====================== get next job completed=============================");
					if(null!=j){
						WorkQueueManager wqm = createWorkQueueManager(qname);
						System.out.println("execute on put method====================== execute=============================");	
						executeWorkqueue(wqm,j,e);
					}else{
						ep.returnPool(e);
						break;
					}
				}else{
					break;
					
				}
			}catch(Exception e1){
				ep.returnPool(e);
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.QueueTrigerEvent#systemStart()
	 */
	public void systemStart() throws Exception {
		//		 TODO Auto-generated method stub
		//		Apply Queue Configuration to workqueuemanager here ;
//		Collection aCollection = find_QueuetbLocalHome_findAllQueue();
//		for (Iterator iter = aCollection.iterator(); iter.hasNext();) {
//			QueuetbLocal qlh = (QueuetbLocal) iter.next();
//			QueueConfiguration qc = new QueueConfiguration();
//			qc.setMaxThread(qlh.getAvailable().toBigInteger().intValue());
//			qc.setTimeout(qlh.getWorkingtime().toBigInteger().intValue());
//			qc.setTurnoff(qlh.getIsterminate().equals(DefaultQueueConstants.QUEUE_TERMINATE));
//			qc.setQueueName(qlh.getQueuename());
//			qc.setAvalilableThread(qlh.getAvailable().toBigInteger().intValue());
//			qc.setExecutorClass(qlh.getActivateclass());
//			qc.setQueueId(qlh.getQueueid().toBigInteger().intValue());
//			ExecutorPoolProvider_Bak.getInstance().addExecutorPool(qc);
//			
//			WorkQueueManager wqm = findWorkQueue(qc.getQueueName());
//			if (null == wqm) {
//				wqm = createWorkQueueManager(qc.getQueueName());
//				System.out.println(" Execute wqm ");
//				executeWorkqueue(wqm);
//				
//			}
//			
//		}
	}
	
	/**
	 * Create queue manager for run queue when pool available
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param qname
	 * @throws Exception
	 */
	public void onQueueAvailabel(String qname) throws Exception{
//		QueueConfiguration qc = QueueConfigurationProvider.getInstance().getQueueConfiguration(qname);
		System.out.println("------------------------- On queue availabel--------------------------------");
		ExecutorPool ep = ExecutorPoolProvider.getInstance().getExecutorPool(qname);
		Thread.currentThread().sleep(800);
		Executor e = ep.getNext();
		
		QueueManager qm = QueueManagerFactory.getInstance().getQueuemanager(qname);
		try{
				
			if(e!=null){
				JobVO jobvo = qm.getNextJob();
				System.out.println("On queue availabel------------------------- Get next job completed --------------------------------");
				if(null!=jobvo){
					WorkQueueManager wqm = createWorkQueueManager(qname);
					System.out.println("On queue availabel------------------------- Execute --------------------------------");
					executeWorkqueue(wqm,jobvo,e);
				}else{
					ep.returnPool(e);
				}
			}
		}catch(Exception e1){
			ep.returnPool(e);
		}
	}
	
	/**
	 * Run queueu manager
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param wqm
	 * @param j
	 * @param e
	 */
	private void executeWorkqueue(final WorkQueueManager wqm,final JobVO j,final Executor e){
		Thread t = new Thread(new Runnable(){
			public void run(){
				try{
					wqm.execute(j,e);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		t.start();
	}
	
	/**
	 * Create queue manager
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param queueName
	 * @return
	 */
	private WorkQueueManager createWorkQueueManager(String queueName){
		WorkQueueManager wqm = new WorkQueueManager(queueName);
//		workQueueManagers.put(queueName,wqm);
		return wqm;
	}
	
//	private WorkQueueManager findWorkQueue(String queueName){
////		if not exists workqueuemanager must be create a new one.
////		WorkQueueManager wqm = (WorkQueueManager)workQueueManagers.get(queueName);
////		return wqm;
////		if(null==wqm){
////			
////			wqm = new WorkQueueManager(queueName);
////			workQueueManagers.put(queueName,wqm);
////		}else{
////			return null;
////		}
////		return wqm;
//	}
	
//	public void releaseWorkQueueManager(WorkQueueManager wqm){
//		workQueueManagers.remove(wqm);
//	}
	
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
	}
	
	/**
	 * Create queue manager and run it.
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param queueName
	 */
	//Start Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
	//Start Synchronized Veerasak Boonchern 2009/10/12[yyyy/mm/dd]
	private void executeWorkqueue(String queueName)
	//synchronized private void executeWorkqueue(String queueName)
	//End Synchronized Veerasak Boonchern 2009/10/12[yyyy/mm/dd]
	{
		//Start Trim queue name Veerasak Boonchern 2009/10/12[yyyy/mm/dd]
		//if (allQueueManager.get(queueName) == null)
		System.out.println("WorkQueueController: " + queueName.trim() + " " + allQueueManager.get(queueName.trim()));
		if (allQueueManager.get(queueName.trim()) == null)
		{
			//final WorkQueueManager qManager = new WorkQueueManager(queueName);
			final WorkQueueManager qManager = new WorkQueueManager(queueName.trim());
			
			Thread thread = new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{
							qManager.runJobThread();
						} catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			);
			/*allQueueManager.put(queueName, qManager);
			allQueueThread.put(queueName, thread);*/
			allQueueManager.put(queueName.trim(), qManager);
			allQueueThread.put(queueName.trim(), thread);
			thread.start();
		}
	}
	
	/**
	 * Remove queue manager when job terminate
	 * 
	 * @author Den
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param queueName
	 * @throws Exception
	 */
	public void removeQueueManager(String queueName) throws Exception
	{
		//if (allQueueManager.get(queueName) != null)
		if (allQueueManager.get(queueName.trim()) != null)
		{
			//Thread thread = (Thread)allQueueThread.get(queueName);
			Thread thread = (Thread)allQueueThread.get(queueName.trim());
			thread.interrupt();
			
			/*allQueueThread.remove(queueName);
			allQueueManager.remove(queueName);*/
			allQueueThread.remove(queueName.trim());
			allQueueManager.remove(queueName.trim());
		}
		
		System.out.println("all job [Queue name: " + queueName.trim() + "]: terminated ===================================================");
	}
	
	/**
	 * Kill thread of job when job is finish
	 * 
	 * @author Den
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param threadName
	 * @param queueName
	 * @param jobRefNo
	 */
	public void killJobThread(String threadName, String queueName, String jobRefNo)
	{
		//WorkQueueManager qManager = (WorkQueueManager)allQueueManager.get(queueName);
		WorkQueueManager qManager = (WorkQueueManager)allQueueManager.get(queueName.trim());
		try
		{
			qManager.killJobThread(threadName, jobRefNo);
		} catch (Exception e)
		{
			//e.printStackTrace();
		}
	}
	//End Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
	
	/**
	 * Reset queue
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param queueName
	 */
	//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	//Start Synchronized Veerasak Boonchern 2009/10/12[yyyy/mm/dd]
	public void resetQueue(String queueName) throws Exception
	//synchronized public void resetQueue(String queueName) throws Exception
	//End Synchronized Veerasak Boonchern 2009/10/12[yyyy/mm/dd]
	{
		//if (allQueueManager.get(queueName) != null)
		if (allQueueManager.get(queueName.trim()) != null)
		{
			//WorkQueueManager qManager = (WorkQueueManager)allQueueManager.get(queueName);
			WorkQueueManager qManager = (WorkQueueManager)allQueueManager.get(queueName.trim());
			qManager.resetQueue();
		}
		//Start Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
		else
		{
			JobQueueDAO jobqDAO = new JobQueueDAO();
			jobqDAO.resetQueue(queueName.trim());
		}
		//End Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
	}
	//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
	//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	
	/**
	 * Get all queue manager
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/12/21[yyyymmdd]
	 * @return Map
	 */
	//Start Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
	public Map getAllQueueManager() {return this.allQueueManager;}
	/**
	 * Get queue manager
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/12/21[yyyymmdd]
	 * @param queueName
	 * @return WorkQueueManager
	 */
	public WorkQueueManager getWorkQueueManager(String queueName)
	{
		return (WorkQueueManager)allQueueManager.get(queueName.trim());
	}
	
	/**
	 * Get all thread of queue manager
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/12/21[yyyymmdd]
	 * @return Map
	 */
	public Map getAllQueueThread() {return this.allQueueThread;}
	/**
	 * Get thread of queue manager
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/12/21[yyyymmdd]
	 * @param queueName
	 * @return Thread
	 */
	public Thread getQueueThread(String queueName)
	{
		return (Thread)allQueueThread.get(queueName.trim());
	}
	//End Reset when start Veerasak Boonchern Aey 2009/12/21[yyyymmdd]
}
