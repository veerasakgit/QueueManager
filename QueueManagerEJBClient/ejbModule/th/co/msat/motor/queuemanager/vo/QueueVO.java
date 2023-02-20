/*
 * Created on 25 ¡.Â. 2551
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


package th.co.msat.motor.queuemanager.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueueVO implements Serializable {
	public String getAcitvateClass() {
		return acitvateClass;
	}
	public void setAcitvateClass(String acitvateClass) {
		this.acitvateClass = acitvateClass;
	}
	public BigDecimal getAvailable() {
		return available;
	}
	public void setAvailable(BigDecimal available) {
		this.available = available;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isTerminate() {
		return isTerminate;
	}
	public void setTerminate(boolean isTerminate) {
		this.isTerminate = isTerminate;
	}
	public String getMessageClass() {
		return messageClass;
	}
	public void setMessageClass(String messageClass) {
		this.messageClass = messageClass;
	}
	public BigDecimal getQueueId() {
		return queueId;
	}
	public void setQueueId(BigDecimal queueId) {
		this.queueId = queueId;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public boolean isRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(boolean recordStatus) {
		this.recordStatus = recordStatus;
	}
	public BigDecimal getSize() {
		return size;
	}
	public void setSize(BigDecimal size) {
		this.size = size;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateProgram() {
		return updateProgram;
	}
	public void setUpdateProgram(String updateProgram) {
		this.updateProgram = updateProgram;
	}
	public BigDecimal getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(BigDecimal workingTime) {
		this.workingTime = workingTime;
	}
	private BigDecimal queueId;
	private String queueName;
	private BigDecimal size;
	private BigDecimal available;
	private boolean isTerminate;
	private BigDecimal workingTime;
	private String messageClass;
	private String acitvateClass;
	private Date createDate;
	private Date updateDate;
	private String updateProgram;
	private boolean recordStatus;
	private String createUser;
	private Collection jobTb;
	private String updateUser;
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Collection getJobTb() {
		return jobTb;
	}
	public void setJobTb(Collection jobTb) {
		this.jobTb = jobTb;
	}
	
	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	private String hostName = null;
	private String bootStrap = null;
	
	public void setHostName(String hostName) {this.hostName = hostName;}
	public String getHostName() {return this.hostName;}
	
	public void setBootStrap(String bootStrap) {this.bootStrap = bootStrap;}
	public String getBootStrap() {return this.bootStrap;}
	//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
}
