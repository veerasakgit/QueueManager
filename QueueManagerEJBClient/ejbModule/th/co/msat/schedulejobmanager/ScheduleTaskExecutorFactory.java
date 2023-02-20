/*
 * Created on 1 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager;

import th.co.msat.schedulejobmanager.ScheduleTaskExecutor;
/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScheduleTaskExecutorFactory {
	private static ScheduleTaskExecutorFactory instance;
	public static ScheduleTaskExecutorFactory getInstance(){
		if(null==instance)
			instance = new ScheduleTaskExecutorFactory();
		return instance;
	}
	public ScheduleTaskExecutor getScheduleJobExecutor(String classname) {
		try{
			return (ScheduleTaskExecutor)Class.forName(classname).newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
