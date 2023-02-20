package th.co.msat.campaign.accumulate;

import th.co.msat.motor.queuemanager.message.Message;

public class AccumulateMessage implements Message{
	
	private String referenceId;
	
	private Integer campaignId;
	
	private String dateTo;
	
	private String dateFrom;
	
	private String functionType = "normal";
	
	private boolean checkPaid;
	
	

	public boolean getCheckPaid() {
		return checkPaid;
	}

	public void setCheckPaid(boolean checkPaid) {
		this.checkPaid = checkPaid;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getReferenceId() {
		return "R" + campaignId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	

}
