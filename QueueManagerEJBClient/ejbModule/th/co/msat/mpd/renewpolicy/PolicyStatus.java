package th.co.msat.mpd.renewpolicy;

import java.io.Serializable;

public class PolicyStatus implements Serializable{
	
	private String oldPolicy;
	
	private int oldSlNo;
	
	private String newPolicy;
	
	private int newSlNo;
	
	private String licNo;

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public String getNewPolicy() {
		return newPolicy;
	}

	public void setNewPolicy(String newPolicy) {
		this.newPolicy = newPolicy;
	}

	public int getNewSlNo() {
		return newSlNo;
	}

	public void setNewSlNo(int newSlNo) {
		this.newSlNo = newSlNo;
	}

	public String getOldPolicy() {
		return oldPolicy;
	}

	public void setOldPolicy(String oldPolicy) {
		this.oldPolicy = oldPolicy;
	}

	public int getOldSlNo() {
		return oldSlNo;
	}

	public void setOldSlNo(int oldSlNo) {
		this.oldSlNo = oldSlNo;
	}
	
	

}
