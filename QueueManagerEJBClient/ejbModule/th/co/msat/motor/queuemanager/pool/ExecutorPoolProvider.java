/*
 * Created on 30 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.pool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import th.co.msat.motor.queuemanager.QueueConfiguration;
import th.co.msat.motor.queuemanager.QueueConfigurationProvider;

/**
 * Class for prepare pool of executor
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExecutorPoolProvider {
	Map exePool;
	public static ExecutorPoolProvider instance;
	public static ExecutorPoolProvider getInstance() throws Exception{
		if(null==instance){
			instance = new ExecutorPoolProvider();
		}
		return instance;
	}
	
	/**
	 * Create pool of executor
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @throws Exception
	 */
	public ExecutorPoolProvider() throws Exception{
		System.out.println(" =========================================================================");
		Map qconfigs = QueueConfigurationProvider.getInstance().getQueueConfigurations();
		
		exePool = new HashMap();
		for (Iterator iter = qconfigs.keySet().iterator(); iter.hasNext();) {
			
			String qname = (String) iter.next();
			System.out.println("===========================================================================");
			System.out.println(qname);
			QueueConfiguration qconfig = (QueueConfiguration)qconfigs.get(qname);
			System.out.println("Queue Config Max thread:"+qconfig.getMaxThread());
			ExecutorPoolImpl pool = new ExecutorPoolImpl(qconfig.getExecutorClass(),qconfig.getMaxThread(),qname);
//			pool.setExecutorClass(qconfig.getExecutorClass());
//			pool.setPoolSize(qconfig.getMaxThread());
			exePool.put(qconfig.getQueueName(),pool);
		}
	}
	
	/**
	 * Get pool of executor
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param qname
	 * @return
	 */
	public ExecutorPool getExecutorPool(String qname){
		System.out.println((ExecutorPool)exePool.get(qname));
		System.out.println(" Executor Pool Name:"+qname+" is:"+exePool.get(qname));
		return (ExecutorPool)exePool.get(qname);
	}
	
	/**
	 * Recreate pool of executor
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param qname
	 * @throws Exception
	 */
	public void resetQueue(String qname) throws Exception{
		System.out.println("===========================================================================");
		System.out.println(" reset q "+qname);
		System.out.println("===========================================================================");
		Map qconfigs = QueueConfigurationProvider.getInstance().getQueueConfigurations();
		QueueConfiguration qconfig = (QueueConfiguration)qconfigs.get(qname);
		System.out.println("Queue Config Max thread:"+qconfig.getMaxThread());
		ExecutorPoolImpl pool = new ExecutorPoolImpl(qconfig.getExecutorClass(),qconfig.getMaxThread(),qname);
		exePool.put(qconfig.getQueueName(),pool);
		
	}
	
}
