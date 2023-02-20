/*
 * Created on 17 ¡.¾. 2552
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.reserveadjustexecutor;

import java.util.List;

import th.co.msat.motor.queuemanager.message.Message;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReserveAdjustMessage implements Message {
	
	private String branch;
	private String classes;
	private String year;
	private String claimno;
	private String transactionDate;
	private List payeeList;
	private String referenceId;
	
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.message.Message#getReferenceId()
	 */
	public String getReferenceId() {
		// TODO Auto-generated method stub
		return referenceId;
	}
	
	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getClaimno() {
		return claimno;
	}
	public void setClaimno(String claimno) {
		this.claimno = claimno;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public List getPayeeList() {
		return payeeList;
	}
	public void setPayeeList(List payeeList) {
		this.payeeList = payeeList;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
