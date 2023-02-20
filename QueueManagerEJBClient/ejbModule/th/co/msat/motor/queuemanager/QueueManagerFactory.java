/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager;
 
import java.util.HashMap;
import java.util.Map;

/**
 * Class for create QueueManager
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueueManagerFactory {
	private static QueueManagerFactory instance;
	private Map qManagers = new HashMap(); 
	/**
	 * Create instance of QueueManagerFactory
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return QueueManagerFactory
	 */
	public static QueueManagerFactory getInstance(){
		if(null==instance)
			instance = new QueueManagerFactory();
		return instance;
	}
	
	/**
	 * Create QueueManager
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param qname
	 * @return QueueManager
	 */
	public QueueManager getQueuemanager(String qname){
		if(null==qManagers.get(qname)){
			DefaultQueueManager dqm = new DefaultQueueManager(qname);
			qManagers.put(qname,dqm);
		}
		return (QueueManager) qManagers.get(qname);
	}

}
