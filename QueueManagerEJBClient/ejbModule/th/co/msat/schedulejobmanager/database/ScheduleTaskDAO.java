/*
 * Created on 1 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager.database;

import java.util.Collection;

import th.co.msat.schedulejobmanager.ScheduleTaskVO;
/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ScheduleTaskDAO {

	public Collection getAllScheduleTask() throws Exception;
	public ScheduleTaskVO getScheduleTask(int id) throws Exception;
}
