/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/****************************************************/
/*----------------- Modify History -----------------*/
/****************************************************/
/*---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/07[yyyy/mm/dd]
 * @description Split queue host follow queue.host.running
 * 				in systemenv.properties
 * 				if QUEUETB.hostname = queue.host.running
 * 					run thread
 *--------------------------------------------------*/


package th.co.msat.motor.queuemanager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.database.QueuetbLocal;
import th.co.msat.motor.queuemanager.database.QueuetbLocalHome;
import th.co.msat.motor.queuemanager.vo.QueueVO;

/**
 * Class that contain QueueConfiguration
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueueConfigurationProvider {
	public Map qConfig = new HashMap();
	private static QueueConfigurationProvider instance;
	private final static String STATIC_QueuetbLocalHome_REF_NAME = "ejb/Queuetb";
	private final static Class STATIC_QueuetbLocalHome_CLASS = QueuetbLocalHome.class;
	
	public QueueConfigurationProvider(){	
//		Collection aCollection = find_QueuetbLocalHome_findAllQueue();
		JobQueueDAO jqdao = new JobQueueDAO();
		Collection c=null;
		try {
			c = jqdao.findAllQueue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Iterator iter = c.iterator(); iter.hasNext();) {
			QueueVO qvo = (QueueVO) iter.next();
			QueueConfiguration qc = new QueueConfiguration();
			qc.setMaxThread(qvo.getAvailable().toBigInteger().intValue());
			qc.setTimeout(qvo.getWorkingTime().toBigInteger().intValue());
			qc.setTurnoff(qvo.isTerminate());
			qc.setQueueName(qvo.getQueueName());
			qc.setAvalilableThread(qvo.getAvailable().toBigInteger().intValue());
			qc.setExecutorClass(qvo.getAcitvateClass());
			qc.setQueueId(qvo.getQueueId().toBigInteger().intValue());
			//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
			qc.setHostName(qvo.getHostName());
			//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
//			System.out.println("???????????????????????????????????????????????????????????????????????");
//			System.out.println("???????????????????????????????????????????????????????????????????????");
//			System.out.println("???????????????????????????????????????????????????????????????????????");
			System.out.println(qc.getQueueName());
			System.out.println(qc.getAvalilableThread());
			System.out.println(qvo.getAvailable());
//			System.out.println("???????????????????????????????????????????????????????????????????????");
//			System.out.println("???????????????????????????????????????????????????????????????????????");
//			System.out.println("???????????????????????????????????????????????????????????????????????");
			addQueueConfiguration(qc);
		}
	}
	
	/**
	 * Add QueueConfiguration
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param qc
	 */
	public void addQueueConfiguration(QueueConfiguration qc){
		qConfig.put(qc.getQueueName(),qc);
	}
	
	/**
	 * Create instance of QueueConfigurationProvider
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return QueueConfigurationProvider
	 */
	public static QueueConfigurationProvider getInstance(){
		if(null==instance)
			instance = new QueueConfigurationProvider();
		return instance;
	}
	
	/**
	 * Get QueueConfiguration
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param queueName
	 * @return QueueConfiguration
	 */
	public QueueConfiguration getQueueConfiguration(String queueName){
		return (QueueConfiguration) qConfig.get(queueName);
	}
	
	/**
	 * Get map of QueueConfiguration
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return Map
	 */
	public Map getQueueConfigurations(){
		return qConfig;
	}
	
}
