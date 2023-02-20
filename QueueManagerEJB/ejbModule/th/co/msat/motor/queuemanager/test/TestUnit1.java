/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.test;


import th.co.msat.motor.queuemanager.QueueConfiguration;
import th.co.msat.motor.queuemanager.QueueConfigurationProvider;
import th.co.msat.motor.queuemanager.WorkQueueManager;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestUnit1 {

	public static void main(String[] args) {
		QueueConfiguration qConfig = QueueConfigurationProvider.getInstance().getQueueConfiguration("a");
//		WorkQueueManager wqm = new WorkQueueManager("a",);
		try {
//			wqm.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
