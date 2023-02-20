/*
 * Created on 19 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.executor;

import th.co.msat.motor.queuemanager.executor.ExecutorFactory;
import java.lang.Exception;
import java.lang.String;
import th.co.msat.motor.queuemanager.pool.ExecutorPool;
/**
 * Factory of Executor
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExecutorFactory {

	private static ExecutorFactory instance = null;

	/**
	 * Create instance of ExecutorFactory
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @return ExecutorFactory
	 * @throws Exception
	 */
	public static ExecutorFactory getInstance() throws Exception {
		if(null==instance)
			instance = new ExecutorFactory();
		return instance;
	}
	
	/**
	 * Create Executor
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @param classname
	 * @return Executor
	 * @throws Exception
	 */
	public Executor getExecutor(String classname) throws Exception {
		System.out.println("classname: [" + classname+ "]");
		return (Executor)Class.forName(classname).newInstance();
	}
}
