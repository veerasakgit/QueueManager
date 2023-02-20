/*
 * Created on 20 ¡.¤. 2552
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.changegeneralinfo;

import th.co.msat.motor.queuemanager.message.Message;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ChangeGeneralInfoMessage implements Message{

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.message.Message#getReferenceId()
	 */
	
	private String branch ;
	private String classes;
	private String year;
	private String claimNo;
	private String driverAge;
	private String driverBirthDay;
	private String idNo;
	private String lossDate;
	private String natCode;
	private String fullClaimNo;
	
	
	public String getReferenceId() {
		return fullClaimNo;
	}
	

	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getDriverAge() {
		return driverAge;
	}
	public void setDriverAge(String driverAge) {
		this.driverAge = driverAge;
	}
	public String getDriverBirthDay() {
		return driverBirthDay;
	}
	public void setDriverBirthDay(String driverBirthDay) {
		this.driverBirthDay = driverBirthDay;
	}
	public String getFullClaimNo() {
		return fullClaimNo;
	}
	public void setFullClaimNo(String fullClaimNo) {
		this.fullClaimNo = fullClaimNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getLossDate() {
		return lossDate;
	}
	public void setLossDate(String lossDate) {
		this.lossDate = lossDate;
	}
	public String getNatCode() {
		return natCode;
	}
	public void setNatCode(String natCode) {
		this.natCode = natCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
