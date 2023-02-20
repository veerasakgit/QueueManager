/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/****************************************************/
/*----------------- Modify History -----------------*/
/****************************************************/
/*---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/07[yyyy/mm/dd]
 * @description Split queue host follow queue.host.running
 * 				in systemenv.properties
 * 				if QUEUETB.hostname = queue.host.running
 * 					run thread
 *--------------------------------------------------*/


package th.co.msat.motor.queuemanager;

/**
 * Model of queuetb
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueueConfiguration {

	public int getAvalilableThread() {
		return avalilableThread;
	}
	public void setAvalilableThread(int avalilableThread) {
		this.avalilableThread = avalilableThread;
	}
	public int getMaxThread() {
		return maxThread;
	}
	public void setMaxThread(int maxThread) {
		this.maxThread = maxThread;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public boolean isTurnoff() {
		return turnoff;
	}
	public void setTurnoff(boolean turnoff) {
		this.turnoff = turnoff;
	}
	private int timeout;


	private int maxThread;


	private int avalilableThread;


	private boolean turnoff;


	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	private String queueName;


	private int queueId;
	
	private String executorClass;


	public String getExecutorClass() {
		return executorClass;
	}
	public void setExecutorClass(String executorClass) {
		this.executorClass = executorClass;
	}
	
	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	private String hostName = null;
	
	public void setHostName(String hostName) {this.hostName = hostName;}
	public String getHostName() {return this.hostName;}
	//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
}
