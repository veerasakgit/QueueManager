/*
 * Created on 1 µ.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.vo;

import java.io.Serializable;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueueConfigurationVO implements Serializable{

	private String switchStatus;
	private int size;
	private int availabelThread;
	private int releaseJob;
	private int holdingJob;
	private int processingJob;
	
	public int getAvailabelThread() {
		return availabelThread;
	}
	public void setAvailabelThread(int availabelThread) {
		this.availabelThread = availabelThread;
	}
	public int getHoldingJob() {
		return holdingJob;
	}
	public void setHoldingJob(int holdingJob) {
		this.holdingJob = holdingJob;
	}
	public int getProcessingJob() {
		return processingJob;
	}
	public void setProcessingJob(int processingJob) {
		this.processingJob = processingJob;
	}
	public int getReleaseJob() {
		return releaseJob;
	}
	public void setReleaseJob(int releaseJob) {
		this.releaseJob = releaseJob;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(String switchStatus) {
		this.switchStatus = switchStatus;
	}
}
