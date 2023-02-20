package th.co.msat.campaign.accumulate;

import java.io.Serializable;

public class CampaignRec implements Serializable{

	private int id;
	
	private int campaignId;
	
	private String broker;
	
	private String clientCode;
	
	private String clientName;
	
	private String clientAddress;
	
	private String taxId;
	
	private String receiedDate;
	
	private String voucherNo;
	
	private String effectiveFrom;
	
	private String effectiveTo;
	
	private String policyNo;
	
	private String endtNo;
	
	private String insuredName;
	
	private double premium;
	
	private String model;
	
	private double incentive;
	
	private String vat;
	
	private float tax;
	
	private String vehicleCode;
	
	private String lastEndt;
	
	private int installNo = 1;

	public int getInstallNo() {
		return installNo;
	}

	public void setInstallNo(int installNo) {
		this.installNo = installNo;
	}

	public String getLastEndt() {
		return lastEndt;
	}

	public void setLastEndt(String lastEndt) {
		this.lastEndt = lastEndt;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getIncentive() {
		return incentive;
	}

	public void setIncentive(double d) {
		this.incentive = d;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public String getReceiedDate() {
		return receiedDate;
	}

	public void setReceiedDate(String receiedDate) {
		this.receiedDate = receiedDate;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	

	public CampaignRec() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public CampaignRec(int id, int campaignId, String broker, String clientCode, String clientName, String clientAddress, String taxId, String receiedDate, String voucherNo, String effectiveFrom, String effectiveTo, String policyNo , String entdNo, String insuredName, double premium, String model, double incentive, String vat, float tax, String vehicleCode, String lastEndt, int installNo) {
		super();
		this.id = id;
		this.campaignId = campaignId;
		this.broker = broker;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.taxId = taxId;
		this.receiedDate = receiedDate;
		this.voucherNo = voucherNo;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.policyNo = policyNo;
		this.endtNo = entdNo;
		this.insuredName = insuredName;
		this.premium = premium;
		this.model = model;
		this.incentive = incentive;
		this.vat = vat;
		this.tax = tax;
		this.vehicleCode = vehicleCode;
		this.lastEndt = lastEndt;
		this.installNo = installNo;
	}

	public String getEndtNo() {
		return endtNo;
	}

	public void setEndtNo(String endtNo) {
		this.endtNo = endtNo;
	}



	 
	
}
