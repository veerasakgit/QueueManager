/*
 * Created on 23 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.synchronize;

import th.co.msat.motor.queuemanager.message.Message;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NatureOfLossSynchronizeMessage implements Message{
	private String claimNo;
	private String natureOfLoss;
	private String notificationno;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getReferenceId() {
		return claimNo;
	}
	
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	
	public String getNatureOfLoss() {
		return natureOfLoss;
	}
	public void setNatureOfLoss(String natureOfLoss) {
		this.natureOfLoss = natureOfLoss;
	}
	public String getNotificationno() {
		return notificationno;
	}
	public void setNotificationno(String notificationno) {
		this.notificationno = notificationno;
	}
}
