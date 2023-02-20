package th.co.msat.campaign.accumulate;

import java.io.Serializable;

public class CampaignClient implements Serializable{
	private String broker;
	
	private String clientCode;
	
	private String clientCode2;
	
	private String classType;
	
	private String campaignName;
	
	

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientCode2() {
		return clientCode2;
	}

	public void setClientCode2(String clientCode2) {
		this.clientCode2 = clientCode2;
	}

	public CampaignClient(String broker, String clientCode, String clientCode2, String classType, String campaignName) {
		super();
		this.broker = broker;
		this.clientCode = clientCode;
		this.clientCode2 = clientCode2;
		this.classType = classType;
		this.campaignName = campaignName;
	}
	
	
	
	

}
