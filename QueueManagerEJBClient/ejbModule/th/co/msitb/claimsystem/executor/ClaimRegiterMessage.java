/****************************************************/
/* ---------------- Modify History ---------------- */
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since	2010/02/09[yyyy/mm/dd]
 * @description Add claim number
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since	2010/02/12[yyyy/mm/dd]
 * @description Add user update
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since	2010/02/18[yyyy/mm/dd]
 * @description Add nature name
 * -------------------------------------------------
 * @modifier Praijittra K. Nok
 * @since	2010/02/25[yyyy/mm/dd]
 * @description Add Claimant
 * -------------------------------------------------*/
 


package th.co.msitb.claimsystem.executor;

import th.co.msat.motor.queuemanager.message.Message;

public class ClaimRegiterMessage implements Message{

	private String referenceId="";	
	private String branch ="";	
	private String classes="";	
	private String year="";	
	private String policyNo="";	
	private String lossDate="";	
	private String notificationDate="";	
	private String interest="";	
	private String natureCode="";	
	private String reviewCode="";	
	private String clauseCode="";	
	private String surveyedBy="";	
	private String locationOfLoss="";	
	private String locationOfLoss2="";	
	private String province="";	
	private String itemNo="";	
	private String surveyReportNo="";
	//Start Add claimant Praijittra K. 2010/02/25[yyyy/mm/dd]
	private String claimant="";	
	//End Add claimant Praijittra K. 2010/02/25[yyyy/mm/dd]
	private String invoiceNo="";	
	private String recovery="";	
	private String timeBar="";	
	private String resCode1="";	
	private String reserveAmount1="";	
	private String resCode2="";	
	private String reserveAmount2="";	
	private String resCode3="";	
	private String reserveAmount3="";	
	private String resCode4="";	
	private String reserveAmount4="";	
	private String resCode5="";	
	private String reserveAmount5="";	
	private String resCode6="";	
	private String reserveAmount6="";	
	
	//Start Add claim number Veerasak Boonchern 2010/02/09[yyyy/mm/dd]
	private String clm_bnc = "";
	private String clm_cls = "";
	private String clm_yr = "";
	private String clm_no = "";
	//End Add claim number Veerasak Boonchern 2010/02/09[yyyy/mm/dd]
	
	//Start Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]
	private String userUpdate = "";
	//End Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]
	
	// for nok
	private String notificationNo="";
	
	//Start Add nature name Veerasak Boonchern 2010/02/18[yyyy/mm/dd]
	private String natureName="";
	//End Add nature name Veerasak Boonchern 2010/02/18[yyyy/mm/dd]

	
	//Start Add claimant Praijittra K. 2010/02/25[yyyy/mm/dd]
	public String getClaimant() {
		return claimant;
	}
	public void setClaimant(String claimant) {
		this.claimant = claimant;
	}
	//End Add claimant Praijittra K. 2010/02/25[yyyy/mm/dd]
	
	
	
	//Start Add claim number Veerasak Boonchern 2010/02/09[yyyy/mm/dd]
	public String getClaimBranch() {return clm_bnc;}
	public void setClaimBranch(String clm_bnc) {this.clm_bnc = clm_bnc;}
	
	public String getClaimClass() {return this.clm_cls;}
	public void setClaimClass(String clm_cls) {this.clm_cls = clm_cls;}
	
	public String getClaimYear() {return this.clm_yr;}
	public void setClaimYear(String clm_yr) {this.clm_yr = clm_yr;}
	
	public String getClaimNo() {return this.clm_no;}
	public void setClaimNo(String clm_no) {this.clm_no = clm_no;}
	//End Add claim number Veerasak Boonchern 2010/02/09[yyyy/mm/dd]
	
	
	public String getNotificationNo() {
		return notificationNo;
	}

	public void setNotificationNo(String notificationNo) {
		this.notificationNo = notificationNo;
	}

	public String getReferenceId() {		
		return referenceId;
	}
	
	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getRecovery() {
		return recovery;
	}
	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}
	
	public String getTimeBar() {
		return timeBar;
	}
	public void setTimeBar(String timeBar) {
		this.timeBar = timeBar;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getClauseCode() {
		return clauseCode;
	}
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getLocationOfLoss() {
		return locationOfLoss;
	}
	public void setLocationOfLoss(String locationOfLoss) {
		this.locationOfLoss = locationOfLoss;
	}
	public String getLossDate() {
		return lossDate;
	}
	public void setLossDate(String lossDate) {
		this.lossDate = lossDate;
	}
	public String getNatureCode() {
		return natureCode;
	}
	public void setNatureCode(String natureCode) {
		this.natureCode = natureCode;
	}
	public String getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getResCode1() {
		return resCode1;
	}
	public void setResCode1(String resCode1) {
		this.resCode1 = resCode1;
	}
	public String getResCode2() {
		return resCode2;
	}
	public void setResCode2(String resCode2) {
		this.resCode2 = resCode2;
	}
	public String getResCode3() {
		return resCode3;
	}
	public void setResCode3(String resCode3) {
		this.resCode3 = resCode3;
	}
	public String getResCode4() {
		return resCode4;
	}
	public void setResCode4(String resCode4) {
		this.resCode4 = resCode4;
	}
	public String getResCode5() {
		return resCode5;
	}
	public void setResCode5(String resCode5) {
		this.resCode5 = resCode5;
	}
	public String getResCode6() {
		return resCode6;
	}
	public void setResCode6(String resCode6) {
		this.resCode6 = resCode6;
	}
	public String getReserveAmount1() {
		return reserveAmount1;
	}
	public void setReserveAmount1(String reserveAmount1) {
		this.reserveAmount1 = reserveAmount1;
	}
	public String getReserveAmount2() {
		return reserveAmount2;
	}
	public void setReserveAmount2(String reserveAmount2) {
		this.reserveAmount2 = reserveAmount2;
	}
	public String getReserveAmount3() {
		return reserveAmount3;
	}
	public void setReserveAmount3(String reserveAmount3) {
		this.reserveAmount3 = reserveAmount3;
	}
	public String getReserveAmount4() {
		return reserveAmount4;
	}
	public void setReserveAmount4(String reserveAmount4) {
		this.reserveAmount4 = reserveAmount4;
	}
	public String getReserveAmount5() {
		return reserveAmount5;
	}
	public void setReserveAmount5(String reserveAmount5) {
		this.reserveAmount5 = reserveAmount5;
	}
	public String getReserveAmount6() {
		return reserveAmount6;
	}
	public void setReserveAmount6(String reserveAmount6) {
		this.reserveAmount6 = reserveAmount6;
	}
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	public String getSurveyedBy() {
		return surveyedBy;
	}
	public void setSurveyedBy(String surveyedBy) {
		this.surveyedBy = surveyedBy;
	}
	public String getSurveyReportNo() {
		return surveyReportNo;
	}
	public void setSurveyReportNo(String surveyReportNo) {
		this.surveyReportNo = surveyReportNo;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public String getLocationOfLoss2() {
		return locationOfLoss2;
	}

	public void setLocationOfLoss2(String locationOfLoss2) {
		this.locationOfLoss2 = locationOfLoss2;
	}
	
	//Start Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]
	public void setUserUpdate(String userUpdate) {this.userUpdate = userUpdate;}
	public String getUserUpdate() {return this.userUpdate;}
	//End Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]
	
	//Start Add nature name Veerasak Boonchern 2010/02/18[yyyy/mm/dd]
	public void setNatureName(String natureName) {this.natureName = natureName;}
	public String getNatureName() {return this.natureName;}
	//End Add nature name Veerasak Boonchern 2010/02/18[yyyy/mm/dd]

}
