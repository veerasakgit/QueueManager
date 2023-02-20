/*
 * Created on 23 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.pool;

import java.math.BigDecimal;

import th.co.msat.motor.queuemanager.executor.Executor;

/**
 * Interface that has basic method of ExecutorPool
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ExecutorPool {

	/**
	 * Get pool size
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return int
	 */
	public int getPoolSize();
	/**
	 * Get next executor
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return Executor
	 */
	public Executor getNext();
	/**
	 * Get pool size that available
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return int
	 */
	public int getAvailable();
	/**
	 * Set pool size
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param size
	 * @throws Exception
	 */
	public void setPoolSize(int size) throws Exception;
	/**
	 * Add object for increase pool size
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param o
	 */
	public void returnPool(Object o);
	/**
	 * Check pool available
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return boolean
	 */
	public boolean hasNext();
	/**
	 * Check executor is running
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param m
	 * @return boolean
	 */
	public boolean isRunning(BigDecimal m);
	/**
	 * Reset pool of executor
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @throws Exception
	 */
	public void reset() throws Exception;
	
}
