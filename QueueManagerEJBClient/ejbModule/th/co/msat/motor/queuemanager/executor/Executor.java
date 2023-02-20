/*
 * Created on 19 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.executor;

import java.sql.Connection;
import java.lang.Exception;
import java.math.BigDecimal;


import th.co.msat.motor.queuemanager.message.Message;
import th.co.msat.motor.queuemanager.pool.ExecutorPool;

/**
 * Interface that has basic method of Executor
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Executor extends Runnable{

	/**
	 * Get connection
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception;
	/**
	 * Set connection
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param conn
	 * @throws Exception
	 */
	public void setConnection(Connection conn) throws Exception;
	/**
	 * Execute job
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @throws Exception
	 */
	public void execute() throws Exception;
	/**
	 * Set Message
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param message
	 */
	public void setMessage(Message message);
	/**
	 * Get Message
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return Message
	 */
	public Message getMessage();
	/**
	 * Set time-out
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param timeout
	 */
	public void setTimeout(int timeout);
	/**
	 * Process doing when time-out
	 * 
	 * @author Den
	 * @since before 2009/08
	 */
	public void onTimeout();
	/**
	 * Checking executor is alive
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return boolean
	 */
	public boolean isAlive();
	/**
	 * Get job id
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return BigDecimal
	 */
	public BigDecimal getJobid();
	/**
	 * Set job id
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param jobid
	 */
	public void setJobid(BigDecimal jobid);
	/**
	 * Get queue name
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return String
	 */
	public String getQueueName();
	/**
	 * Set queue name
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param queueName
	 */
	public void setQueueName(String queueName);
	/**
	 * Get executor pool
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return ExecutorPool
	 */
	public ExecutorPool getPool();
	/**
	 * Set executor pool
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param pool
	 */
	public void setPool(ExecutorPool pool);
	
	//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
	/**
	 * Set thread name of executor
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param threadName
	 */
	public void setThreadName(String threadName);
	/**
	 * Get thread name of executor
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @return String
	 */
	public String getThreadName();
	
	/**
	 * Set job reference number
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @param jobRefNo
	 */
	public void setJobRefNo(String jobRefNo);
	/**
	 * Get job reference number
	 * 
	 * @author Veerasak Boonchern Aey
	 * @since 2009/09/08[yyyy/mm/dd]
	 * @return String
	 */
	public String getJobRefNo();
	//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
}
