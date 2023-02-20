package th.co.msat.mpd.renewpolicy;

import java.util.List;
import java.util.Map;

import th.co.msat.motor.queuemanager.message.Message;

public class RenewPolicyMessage implements Message {

	private String referenceId;
	
	private String scrapMethod;
	
	private String newRenew;
	
	private String policyNumber;
	
	private String username;
	
	private String password;
	
	private String br = "";
	
	private String cat = "";
	
	private String packageCode = "";
	
	private String policyYear = "";
	
	private String policyNo = "";
	
	private String entd = "";
	
	private String slNo = "";
	
	private String agentCode = "";
	
	private String customerCode = "";
	
	private  String customerSubCode = "";
	
	private String customerName = "";
	
	private String customerAddress1 = "";
	
	private String customerAddress2 = "";
	
	private String customerAddress3 = "";
	
	private String customerAddress4 = "";
	
	private String inceptDate = "";
	
	private String expiryDate = "";
	
	private String coverNoteDate = "";
	
	private String oldPolicyNo = "";
	
	private String polOccupation = "";
	
	private String idNumber = ""; //--------- add
	
	private String policyTepe = "";
	
	private String stdNew = "";
	
	private String title= "";
	
	private String additionalName = "";
	
	private String coverType = "";
	
	private String classUser = "";
	
	private String licNo = "";
	
	private String chassisNo = "";
	
	private String vehCode = "";
	
	private String driverNo = "";
	
	private String body = "";
	
	private String engineNo = "";
	
	private String mfrYear = "";
	
	private String seat = "";
	
	private String displ = "";
	
	private String gvw = "";
	
	private String medicalExp = "";
	
	private String passengerNo = "";
	
	private String beneficiary = "";
	
	private String dob1 = "";
	
	private String occupation1  = "";
	
	private String licNo1  = "";
	
	private String idNo1 = "";
	
	private String dob2 = "";
	
	private String occupation2  = "";
	
	private String licNo2  = "";
	
	private String idNo2 = "";
	
	private String sex1 = "";
	
	private String sex2 = "";
	
	private String name1 = "";
	
	private String name2 = "";
	
	private String basis = "";
	
	private String brand = "";
	
	private String model = "";
	
	private String subModel = "";
	
	private String color = "";
	
	private String regDate = "";
	
	private String accessories ="";
	
	private String territory = "";
	
	private String premium1 = "";
	
	private String premium2 = "";
	
	private String premium3 = "";
	
	private String premium4 = "";
	
	private String premium5 = "";
	
	private String premium6 = "";
	
	private String premium7 = "";
	
	private String premium8 = "";
	
	private String premium9 = "";
	
	private String premium10 = "";
	
	private String premium11 = "";
	
	private String ncdGB = "";
	
	private String fleet = "";
	
	private String ncd = "";
	
	private String otherDiscount = "";
	
	private String freeText1 = "";
	
	private String freeText2 = "";
	
	private String freeText3 = "";
	
	private String freeText4 = "";
	
	private String freeText5 = "";
	
	private String freeText6 = "";
	
	private String freeText7 = "";
	
	private String freeText8 = "";
	
	private String freeText9 = "";
	
	private String freeText10 = "";
	
	private String freeText11 = "";
	
	private String freeText12 = "";
	
	private String freeText13 = "";
	
	private String freeText14 = "";
	
	private String freeText15 = "";
	
	private String freeText16 = "";
	
	private String freeText17 = "";
	
	private String freeText20 = "";
	
	private String freeText34 = "";
	
	private Map dataMap;
	
	

	public Map getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}

	public String getFreeText34() {
		return freeText34;
	}

	public void setFreeText34(String freeText34) {
		this.freeText34 = freeText34;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBr() {
		return br;
	}

	public void setBr(String br) {
		this.br = br;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}

	public String getCustomerAddress1() {
		return customerAddress1;
	}

	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}

	public String getCustomerAddress2() {
		return customerAddress2;
	}

	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}

	public String getCustomerAddress3() {
		return customerAddress3;
	}

	public void setCustomerAddress3(String customerAddress3) {
		this.customerAddress3 = customerAddress3;
	}

	public String getCustomerAddress4() {
		return customerAddress4;
	}

	public void setCustomerAddress4(String customerAddress4) {
		this.customerAddress4 = customerAddress4;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSubCode() {
		return customerSubCode;
	}

	public void setCustomerSubCode(String customerSubCode) {
		this.customerSubCode = customerSubCode;
	}

	public String getDispl() {
		return displ;
	}

	public void setDispl(String displ) {
		this.displ = displ;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getEntd() {
		return entd;
	}

	public void setEntd(String entd) {
		this.entd = entd;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFleet() {
		return fleet;
	}

	public void setFleet(String fleet) {
		this.fleet = fleet;
	}

	public String getFreeText1() {
		return freeText1;
	}

	public void setFreeText1(String freeText1) {
		this.freeText1 = freeText1;
	}

	public String getFreeText2() {
		return freeText2;
	}

	public void setFreeText2(String freeText2) {
		this.freeText2 = freeText2;
	}

	public String getFreeText20() {
		return freeText20;
	}

	public void setFreeText20(String freeText20) {
		this.freeText20 = freeText20;
	}

	public String getFreeText3() {
		return freeText3;
	}

	public void setFreeText3(String freeText3) {
		this.freeText3 = freeText3;
	}

	public String getFreeText4() {
		return freeText4;
	}

	public void setFreeText4(String freeText4) {
		this.freeText4 = freeText4;
	}

	public String getFreeText5() {
		return freeText5;
	}

	public void setFreeText5(String freeText5) {
		this.freeText5 = freeText5;
	}

	public String getFreeText6() {
		return freeText6;
	}

	public void setFreeText6(String freeText6) {
		this.freeText6 = freeText6;
	}

	public String getGvw() {
		return gvw;
	}

	public void setGvw(String gvw) {
		this.gvw = gvw;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getInceptDate() {
		return inceptDate;
	}

	public void setInceptDate(String inceptDate) {
		this.inceptDate = inceptDate;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public String getMedicalExp() {
		return medicalExp;
	}

	public void setMedicalExp(String medicalExp) {
		this.medicalExp = medicalExp;
	}

	public String getMfrYear() {
		return mfrYear;
	}

	public void setMfrYear(String mfrYear) {
		this.mfrYear = mfrYear;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNcd() {
		return ncd;
	}

	public void setNcd(String ncd) {
		this.ncd = ncd;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getPassengerNo() {
		return passengerNo;
	}

	public void setPassengerNo(String passengerNo) {
		this.passengerNo = passengerNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyYear() {
		return policyYear;
	}

	public void setPolicyYear(String policyYear) {
		this.policyYear = policyYear;
	}

	public String getPolOccupation() {
		return polOccupation;
	}

	public void setPolOccupation(String polOccupation) {
		this.polOccupation = polOccupation;
	}

	public String getPremium1() {
		return premium1;
	}

	public void setPremium1(String premium1) {
		this.premium1 = premium1;
	}

	public String getPremium10() {
		return premium10;
	}

	public void setPremium10(String premium10) {
		this.premium10 = premium10;
	}

	public String getPremium2() {
		return premium2;
	}

	public void setPremium2(String premium2) {
		this.premium2 = premium2;
	}

	public String getPremium3() {
		return premium3;
	}

	public void setPremium3(String premium3) {
		this.premium3 = premium3;
	}

	public String getPremium4() {
		return premium4;
	}

	public void setPremium4(String premium4) {
		this.premium4 = premium4;
	}

	public String getPremium5() {
		return premium5;
	}

	public void setPremium5(String premium5) {
		this.premium5 = premium5;
	}

	public String getPremium6() {
		return premium6;
	}

	public void setPremium6(String premium6) {
		this.premium6 = premium6;
	}

	public String getPremium7() {
		return premium7;
	}

	public void setPremium7(String premium7) {
		this.premium7 = premium7;
	}

	public String getPremium8() {
		return premium8;
	}

	public void setPremium8(String premium8) {
		this.premium8 = premium8;
	}

	public String getPremium9() {
		return premium9;
	}

	public void setPremium9(String premium9) {
		this.premium9 = premium9;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVehCode() {
		return vehCode;
	}

	public void setVehCode(String vehCode) {
		this.vehCode = vehCode;
	}

	public String getClassUser() {
		return classUser;
	}

	public void setClassUser(String classUser) {
		this.classUser = classUser;
	}

	public String getNcdGB() {
		return ncdGB;
	}

	public void setNcdGB(String ncdGB) {
		this.ncdGB = ncdGB;
	}

	public String getOldPolicyNo() {
		return oldPolicyNo;
	}

	public void setOldPolicyNo(String oldPolicyNo) {
		this.oldPolicyNo = oldPolicyNo;
	}

	public String getOtherDiscount() {
		return otherDiscount;
	}

	public void setOtherDiscount(String otherDiscount) {
		this.otherDiscount = otherDiscount;
	}

	public String getSubModel() {
		return subModel;
	}

	public void setSubModel(String subModel) {
		this.subModel = subModel;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getScrapMethod() {
		return scrapMethod;
	}

	public void setScrapMethod(String scrapMethod) {
		this.scrapMethod = scrapMethod;
	}

	public String getAdditionalName() {
		return additionalName;
	}

	public void setAdditionalName(String additionalName) {
		this.additionalName = additionalName;
	}

	public String getCoverNoteDate() {
		return coverNoteDate;
	}

	public void setCoverNoteDate(String coverNoteDate) {
		this.coverNoteDate = coverNoteDate;
	}

	public String getDob1() {
		return dob1;
	}

	public void setDob1(String dob1) {
		this.dob1 = dob1;
	}

	public String getDob2() {
		return dob2;
	}

	public void setDob2(String dob2) {
		this.dob2 = dob2;
	}

	public String getIdNo1() {
		return idNo1;
	}

	public void setIdNo1(String idNo1) {
		this.idNo1 = idNo1;
	}

	public String getIdNo2() {
		return idNo2;
	}

	public void setIdNo2(String idNo2) {
		this.idNo2 = idNo2;
	}

	public String getLicNo1() {
		return licNo1;
	}

	public void setLicNo1(String licNo1) {
		this.licNo1 = licNo1;
	}

	public String getLicNo2() {
		return licNo2;
	}

	public void setLicNo2(String licNo2) {
		this.licNo2 = licNo2;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getOccupation1() {
		return occupation1;
	}

	public void setOccupation1(String occupation1) {
		this.occupation1 = occupation1;
	}

	public String getOccupation2() {
		return occupation2;
	}

	public void setOccupation2(String occupation2) {
		this.occupation2 = occupation2;
	}

	public String getPolicyTepe() {
		return policyTepe;
	}

	public void setPolicyTepe(String policyTepe) {
		this.policyTepe = policyTepe;
	}

	public String getSex1() {
		return sex1;
	}

	public void setSex1(String sex1) {
		this.sex1 = sex1;
	}

	public String getSex2() {
		return sex2;
	}

	public void setSex2(String sex2) {
		this.sex2 = sex2;
	}

	public String getStdNew() {
		return stdNew;
	}

	public void setStdNew(String stdNew) {
		this.stdNew = stdNew;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPremium11() {
		return premium11;
	}

	public void setPremium11(String premium11) {
		this.premium11 = premium11;
	}

	public String getFreeText10() {
		return freeText10;
	}

	public void setFreeText10(String freeText10) {
		this.freeText10 = freeText10;
	}

	public String getFreeText11() {
		return freeText11;
	}

	public void setFreeText11(String freeText11) {
		this.freeText11 = freeText11;
	}

	public String getFreeText12() {
		return freeText12;
	}

	public void setFreeText12(String freeText12) {
		this.freeText12 = freeText12;
	}

	public String getFreeText13() {
		return freeText13;
	}

	public void setFreeText13(String freeText13) {
		this.freeText13 = freeText13;
	}

	public String getFreeText14() {
		return freeText14;
	}

	public void setFreeText14(String freeText14) {
		this.freeText14 = freeText14;
	}

	public String getFreeText15() {
		return freeText15;
	}

	public void setFreeText15(String freeText15) {
		this.freeText15 = freeText15;
	}

	public String getFreeText16() {
		return freeText16;
	}

	public void setFreeText16(String freeText16) {
		this.freeText16 = freeText16;
	}

	public String getFreeText17() {
		return freeText17;
	}

	public void setFreeText17(String freeText17) {
		this.freeText17 = freeText17;
	}

	public String getFreeText7() {
		return freeText7;
	}

	public void setFreeText7(String freeText7) {
		this.freeText7 = freeText7;
	}

	public String getFreeText8() {
		return freeText8;
	}

	public void setFreeText8(String freeText8) {
		this.freeText8 = freeText8;
	}

	public String getFreeText9() {
		return freeText9;
	}

	public void setFreeText9(String freeText9) {
		this.freeText9 = freeText9;
	}



	public String getNewRenew() {
		return newRenew;
	}

	public void setNewRenew(String newRenew) {
		this.newRenew = newRenew;
	}
	
	
	

}
