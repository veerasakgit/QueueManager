/*
 * Created on 3 µ.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.scrapexecutor;

import th.co.msat.motor.queuemanager.message.Message;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScrapMessage implements Message{
	private String notificationno;
	private String username;
	private long jobgroup;
	private String insureName;
	private String license;
	private String contractno;
	private String resurveAmount;
	private String as400Username;
	private String as400Password;
	
	public String getAs400Password() {
		return as400Password;
	}
	public void setAs400Password(String as400Password) {
		this.as400Password = as400Password;
	}
	public String getAs400Username() {
		return as400Username;
	}
	public void setAs400Username(String as400Username) {
		this.as400Username = as400Username;
	}
	public String getResurveAmount() {
		return resurveAmount;
	}
	public void setResurveAmount(String resurveAmount) {
		this.resurveAmount = resurveAmount;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
	public String getInsureName() {
		return insureName;
	}
	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.message.Message#getReferenceId()
	 */
	public String getReferenceId() {
		// TODO Auto-generated method stub
		return notificationno;
	}
	
	public void setNotificationno(String noti){
		this.notificationno = noti;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public long getJobgroup() {
		return jobgroup;
	}
	public void setJobgroup(long jobgroup) {
		this.jobgroup = jobgroup;
	}
}
