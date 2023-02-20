/*
 * Created on 14 µ.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.database.vo;

import java.io.Serializable;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RegisteredClaim  implements Serializable{
	private String descriptionOfLoss;
	private String logMessage;
	private String jobStatus;
	private String thefDeduct;
	private String propertyDamDeduct;
	private boolean within5Days;
	private String within5DaysString;
	private boolean createDebitnote;
	private String createDebitnoteString;
	
	
	public String getCreateDebitnoteString() {
		return createDebitnoteString;
	}
	public void setCreateDebitnoteString(String createDebitnoteString) {
		this.createDebitnoteString = createDebitnoteString;
	}
	public String getWithin5DaysString() {
		return within5DaysString;
	}
	public void setWithin5DaysString(String within5DaysString) {
		this.within5DaysString = within5DaysString;
	}
	public String getPropertyDamDeduct() {
		return propertyDamDeduct;
	}
	public void setPropertyDamDeduct(String propertyDamDeduct) {
		this.propertyDamDeduct = propertyDamDeduct;
	}
	public String getThefDeduct() {
		return thefDeduct;
	}
	public void setThefDeduct(String thefDeduct) {
		this.thefDeduct = thefDeduct;
	}
	public boolean isWithin5Days() {
		return within5Days;
	}
	public void setWithin5Days(boolean within5Days) {
		this.within5Days = within5Days;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getNotificationNo() {
		return notificationNo;
	}
	public void setNotificationNo(String notificationNo) {
		this.notificationNo = notificationNo;
	}
	public String getRegisNatureOfLoss() {
		return regisNatureOfLoss;
	}
	public void setRegisNatureOfLoss(String regisNatureOfLoss) {
		this.regisNatureOfLoss = regisNatureOfLoss;
	}
	public String getRegisPolicyNo() {
		return regisPolicyNo;
	}
	public void setRegisPolicyNo(String regisPolicyNo) {
		this.regisPolicyNo = regisPolicyNo;
	}
	private String notificationNo;
	private String regisPolicyNo;
	private String regisNatureOfLoss;
	private String claimNo;
	
	
	public String getDescriptionOfLoss() {
		return descriptionOfLoss;
	}
	public void setDescriptionOfLoss(String descriptionOfLoss) {
		this.descriptionOfLoss = descriptionOfLoss;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	public boolean isCreateDebitnote() {
		return createDebitnote;
	}
	public void setCreateDebitnote(boolean createDebitnote) {
		this.createDebitnote = createDebitnote;
	}
}
