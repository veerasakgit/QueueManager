package th.co.msat.mpd.renewpolicy;

import java.io.Serializable;

public class Policy implements Serializable{
	
	private String brance="";
	
	private String pClass = "";
	
	private String year = "";
	
	private String policyNo = "";

	public String getBrance() {
		return brance;
	}

	public void setBrance(String brance) {
		this.brance = brance;
	}

	public String getPClass() {
		return pClass;
	}

	public void setPClass(String class1) {
		pClass = class1;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getPolicy(){
		return getBrance() + getClass()+ getYear() + getPolicyNo() ;
	}
	
	
}
