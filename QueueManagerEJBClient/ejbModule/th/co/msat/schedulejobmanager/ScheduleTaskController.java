/*
 * Created on 1 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import th.co.msat.schedulejobmanager.database.ScheduleTaskDAO;
import th.co.msat.schedulejobmanager.database.ScheduleTaskDAOImpl;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScheduleTaskController {
	
	private Map scheduleTaskPool;
	
	public ScheduleTaskController(){
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws Exception {
		/*ScheduleTaskDAO dao = new ScheduleTaskDAOImpl();
		Collection c = dao.getAllScheduleTask();
		scheduleTaskPool = new HashMap();
		for (Iterator iter = c.iterator(); iter.hasNext();) {
			ScheduleTaskVO task = (ScheduleTaskVO) iter.next();
			
			Timer t = new Timer();
			
			ScheduleTaskExecutor e = task.getActivateClass();
			
			if(null== e){
				System.out.println("synchronize not found.");
				continue;
			}
			e.setTaskName(task.getTaskname());
			long delay = calculateDelay(task.getStartHours(),task.getStartMinutes(),task.getStartSeconds());
			t.schedule(e,delay,task.getPeriod());
			scheduleTaskPool.put(new Integer(task.getId()),t);
			System.out.println(" init: "+task.getTaskname());
			System.out.println("     : "+task.getPeriod());
			System.out.println("     : "+task.getActivateClass());
			System.out.println("     : "+task.getStartHours()+":"+task.getStartMinutes()+":"+task.getStartSeconds());
			System.out.println("     : "+delay);
		}*/
	}
	
	
	long calculateDelay(int h,int m,int s){

		Date d = new Date();
		long current = getTimeInMilSec(d.getHours(),d.getMinutes(),d.getSeconds());
		long starttime = getTimeInMilSec(h,m,s);
		
		
		if(starttime>=current){
			return starttime-current;
		}else{
			return (24*60*60*1000)-Math.abs( (starttime-current));
		}
		
	}
	
	long getTimeInMilSec(int h,int m,int s){
		return (h*60*60+m*60+s)*1000;
	}
	

	
	public void cancelTask(Integer taskId){
		Timer t = (Timer)scheduleTaskPool.get(taskId);
		t.cancel();
		scheduleTaskPool.remove(t);
	}
	
	public void startTimmer(int id) throws Exception{
		ScheduleTaskDAO dao = new ScheduleTaskDAOImpl();
		ScheduleTaskVO task = dao.getScheduleTask(id);
		
		Timer t = new Timer();
		ScheduleTaskExecutor e = task.getActivateClass();
		e.setTaskName(task.getTaskname());
		Date d = new Date();
		d.setHours(task.getStartHours());
		d.setMinutes(task.getStartMinutes());
		d.setSeconds(task.getStartSeconds());
		t.schedule(e,d,task.getPeriod());
		scheduleTaskPool.put(new Integer(task.getId()),t);
	}
	
	public static void main(String a[]){
		System.out.println(new Date());
	}
	
}
