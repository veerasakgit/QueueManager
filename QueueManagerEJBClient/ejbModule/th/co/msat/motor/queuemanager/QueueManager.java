/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager;

import java.lang.Exception;

import th.co.msat.motor.queuemanager.message.Message;
import th.co.msat.motor.queuemanager.vo.JobVO;
/**
 * Interface that has basic method of QueueManager
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface QueueManager {

	/**
	 * Check job is empty
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isEmpty() throws Exception;
	/**
	 * Get next queue message
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return Message
	 * @throws Exception
	 */
	public Message getNextMessage() throws Exception;
	/**
	 * Get next job from data base
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return JobVO
	 * @throws Exception
	 */
	public JobVO getNextJob()throws Exception;
}
