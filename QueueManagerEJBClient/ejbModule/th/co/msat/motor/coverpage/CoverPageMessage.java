/*
 * Created on 1 µ.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.coverpage;

import th.co.msat.motor.queuemanager.message.Message;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CoverPageMessage implements Message{
	private String notificationno;
	private String username;
	private String policyNo;
	private String riskNo;
	private String claimBranch;
	private String claimCat;
	private String claimClass;
	private String claimNo;
	private String claimYear;
	public String getClaimYear() {
		return claimYear;
	}
	public void setClaimYear(String claimYear) {
		this.claimYear = claimYear;
	}
	public String getClaimBranch() {
		return claimBranch;
	}
	public void setClaimBranch(String claimBranch) {
		this.claimBranch = claimBranch;
	}
	public String getClaimCat() {
		return claimCat;
	}
	public void setClaimCat(String claimCat) {
		this.claimCat = claimCat;
	}
	public String getClaimClass() {
		return claimClass;
	}
	public void setClaimClass(String claimClass) {
		this.claimClass = claimClass;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getRiskNo() {
		return riskNo;
	}
	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}
	private boolean oldClaim;
	
	public boolean isOldClaim() {
		return oldClaim;
	}
	public void setOldClaim(boolean oldClaim) {
		this.oldClaim = oldClaim;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReferenceId() {
		// TODO Auto-generated method stub
		return notificationno;
	}
	
	public void setNotificationno(String notificationno){
		this.notificationno= notificationno;
	}
	
	
}
