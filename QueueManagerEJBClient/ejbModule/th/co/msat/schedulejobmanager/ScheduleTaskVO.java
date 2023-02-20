/*
 * Created on 1 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScheduleTaskVO {
	private int id;
	private String taskname ;
	private long period;
	private ScheduleTaskExecutor activateClass;
//	private long startTime;
	private int startHours;
	private int startMinutes;
	private int startSeconds;
	private String taskStatus;
	
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the taskname.
	 */
	public String getTaskname() {
		return taskname;
	}
	/**
	 * @param taskname The taskname to set.
	 */
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	/**
	 * @return Returns the period.
	 */
	public long getPeriod() {
		return period;
	}
	/**
	 * @param period The period to set.
	 */
	public void setPeriod(long period) {
		this.period = period;
	}
	/**
	 * @return Returns the updatedate.
	 */
	public int getUpdatedate() {
		return updatedate;
	}
	/**
	 * @param updatedate The updatedate to set.
	 */
	public void setUpdatedate(int updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * @return Returns the updatetime.
	 */
	public int getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime The updatetime to set.
	 */
	public void setUpdatetime(int updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * @return Returns the updateuser.
	 */
	public String getUpdateuser() {
		return updateuser;
	}
	/**
	 * @param updateuser The updateuser to set.
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	private int updatedate;
	private int updatetime;
	private String updateuser;

	/**
	 * @return Returns the activateClass.
	 */
	public ScheduleTaskExecutor getActivateClass() {
		return activateClass;
	}
	/**
	 * @param activateClass The activateClass to set.
	 */
	public void setActivateClass(ScheduleTaskExecutor activateClass) {
		this.activateClass = activateClass;
	}
/**
 * @return Returns the startHours.
 */
public int getStartHours() {
	return startHours;
}
/**
 * @param startHours The startHours to set.
 */
public void setStartHours(int startHours) {
	this.startHours = startHours;
}
	/**
	 * @return Returns the startMinutes.
	 */
	public int getStartMinutes() {
		return startMinutes;
	}
	/**
	 * @param startMinutes The startMinutes to set.
	 */
	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;
	}
	/**
	 * @return Returns the startSeconds.
	 */
	public int getStartSeconds() {
		return startSeconds;
	}
	/**
	 * @param startSeconds The startSeconds to set.
	 */
	public void setStartSeconds(int startSeconds) {
		this.startSeconds = startSeconds;
	}
	/**
	 * @return Returns the taskStatus.
	 */
	public String getTaskStatus() {
		return taskStatus;
	}
	/**
	 * @param taskStatus The taskStatus to set.
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
}
