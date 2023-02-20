/*
 * Created on 2 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimerTask;

import th.co.msat.schedulejobmanager.database.MSATServiceLocator;
import th.co.msat.schedulejobmanager.database.MSATServiceLocatorException;
//import th.co.msat.schedulejobmanager.test.ConnectionFactory;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ScheduleTaskExecutor extends TimerTask{
	private String taskName;

	public void run() {
		execute();
	}
	
	public abstract void execute();

	/**
	 * @return Returns the taskName.
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName The taskName to set.
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	private MSATServiceLocator services = new MSATServiceLocator();
	/**
	 * MSATLIB Connection to be use in this class
	 * @return A java.sql.Connection lookup from java:comp/env/MSATLIB 
	 * @throws MSATServiceLocatorException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	protected Connection getConnection() throws Exception{
		
		return services.getConnection(MSATServiceLocator.MSATLIB);
//		return ConnectionFactory.getInstance().getAS400Connection();
		
	}
}
