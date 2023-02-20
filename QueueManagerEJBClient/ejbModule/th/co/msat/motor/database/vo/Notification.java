/*
 * Created on 10 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.database.vo;

import java.io.Serializable;
 
/**
 * @author ituser3
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Notification implements Serializable{
	String notificationNo;
	int accidentDate;
	int accidentTime;
	int notificationDate;
	int notificationTime;
	String notificationName;
	String telephone;
	String accidentPlace;
	String descriptionLine1;
	String descriptionLine2;
	String descriptionLine3;
	String descriptionLine4;
	String descriptionLine5;
	String branch;
	String referencePolicy;
	int riskNo;
	String poicyNo;
	String carRegisterNo;
	String insurename;
	String carBrand;
	int inceptionDate;
	int expireDate;
	String driverName;
	String surveyorName;
	String surveyorOffice;
	int assignDate;
	int estimateDate;
	String transactionStatus;
	int completedDate;
	int transactionIndex;
	String regisPolicyNo;
	String regisProvinceCode;
	String regisNatureOfLoss;
	float surveryFee;
	float od;
	int regisRiskNo;
	String isScrap;
	String notificationDateString;
	String accidentDateString;
	String inceptionDateString;
	String expireDateString;
	
	String surveyorCode;

	public String getSurveyorCode() {
		return surveyorCode;
	}
	public void setSurveyorCode(String surveyorCode) {
		this.surveyorCode = surveyorCode;
	}
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	public float getOd() {
		return od;
	}
	public void setOd(float od) {
		this.od = od;
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
	public String getRegisProvinceCode() {
		return regisProvinceCode;
	}
	public void setRegisProvinceCode(String regisProvinceCode) {
		this.regisProvinceCode = regisProvinceCode;
	}
	public int getRegisRiskNo() {
		return regisRiskNo;
	}
	public void setRegisRiskNo(int regisRiskNo) {
		this.regisRiskNo = regisRiskNo;
	}
	public float getSurveryFee() {
		return surveryFee;
	}
	public void setSurveryFee(float surveryFee) {
		this.surveryFee = surveryFee;
	}
	public int getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(int accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getAccidentPlace() {
		return accidentPlace;
	}

	public void setAccidentPlace(String accidentPlace) {
		this.accidentPlace = accidentPlace;
	}

	public int getAccidentTime() {
		return accidentTime;
	}

	public void setAccidentTime(int accidentTime) {
		this.accidentTime = accidentTime;
	}

	public int getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(int assignDate) {
		this.assignDate = assignDate;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarRegisterNo() {
		return carRegisterNo;
	}

	public void setCarRegisterNo(String carRegisterNo) {
		this.carRegisterNo = carRegisterNo;
	}

	public int getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(int completedDate) {
		this.completedDate = completedDate;
	}

	public String getDescriptionLine1() {
		return descriptionLine1;
	}

	public void setDescriptionLine1(String descriptionLine1) {
		this.descriptionLine1 = descriptionLine1;
	}

	public String getDescriptionLine2() {
		return descriptionLine2;
	}

	public void setDescriptionLine2(String descriptionLine2) {
		this.descriptionLine2 = descriptionLine2;
	}

	public String getDescriptionLine3() {
		return descriptionLine3;
	}

	public void setDescriptionLine3(String descriptionLine3) {
		this.descriptionLine3 = descriptionLine3;
	}

	public String getDescriptionLine4() {
		return descriptionLine4;
	}

	public void setDescriptionLine4(String descriptionLine4) {
		this.descriptionLine4 = descriptionLine4;
	}

	public String getDescriptionLine5() {
		return descriptionLine5;
	}

	public void setDescriptionLine5(String descriptionLine5) {
		this.descriptionLine5 = descriptionLine5;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public int getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(int estimateDate) {
		this.estimateDate = estimateDate;
	}

	public int getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(int expireDate) {
		this.expireDate = expireDate;
	}

	public int getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(int inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public String getInsurename() {
		return insurename;
	}

	public void setInsurename(String insurename) {
		this.insurename = insurename;
	}

	public int getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(int notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getNotificationName() {
		return notificationName;
	}

	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	public String getNotificationNo() {
		return notificationNo;
	}

	public void setNotificationNo(String notificationNo) {
		this.notificationNo = notificationNo;
	}

	public int getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(int notificationTime) {
		this.notificationTime = notificationTime;
	}

	public String getPoicyNo() {
		return poicyNo;
	}

	public void setPoicyNo(String poicyNo) {
		this.poicyNo = poicyNo;
	}

	public String getReferencePolicy() {
		return referencePolicy;
	}

	public void setReferencePolicy(String referencePolicy) {
		this.referencePolicy = referencePolicy;
	}

	public int getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(int riskNo) {
		this.riskNo = riskNo;
	}

	public String getSurveyorName() {
		return surveyorName;
	}

	public void setSurveyorName(String surveyorName) {
		this.surveyorName = surveyorName;
	}

	public String getSurveyorOffice() {
		return surveyorOffice;
	}

	public void setSurveyorOffice(String surveyorOffice) {
		this.surveyorOffice = surveyorOffice;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public int getTransactionIndex() {
		return transactionIndex;
	}
	public void setTransactionIndex(int transactionIndex) {
		this.transactionIndex = transactionIndex;
	}
	private  String claimNo;
	
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	
	public String getAccidentDateString() {
		return accidentDateString;
	}
	public void setAccidentDateString(String accidentDateString) {
		this.accidentDateString = accidentDateString;
	}
	public String getNotificationDateString() {
		return notificationDateString;
	}
	public void setNotificationDateString(String notificationDateString) {
		this.notificationDateString = notificationDateString;
	}
	public String getExpireDateString() {
		return expireDateString;
	}
	public void setExpireDateString(String expireDateString) {
		this.expireDateString = expireDateString;
	}
	public String getInceptionDateString() {
		return inceptionDateString;
	}
	public void setInceptionDateString(String inceptionDateString) {
		this.inceptionDateString = inceptionDateString;
	}
}
