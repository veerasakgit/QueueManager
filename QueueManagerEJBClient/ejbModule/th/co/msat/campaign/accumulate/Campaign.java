
package th.co.msat.campaign.accumulate;

import java.io.Serializable;


/**
 * ���ʹ��������Ѻ ���ҧ CAMPAIGN
 * @author Sakarin Ratchapunya 
 * @since 13/02/2009
 * @version 1.0
 */


public class Campaign implements Serializable{	
	
	public Campaign() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����ù��������Ѻ������� CAMPAIGN_ID
	 */
	private int campaignId;
	
	/**
	 * ����ù��������Ѻ������� CAMPAIGN_NAME
	 */
	private String campaignName;
	
	/**
	 * ����ù��������Ѻ������� EFFECTIVE_DATE
	 */
	private String effectiveDate ;
	
	/**
	 * ����ù��������Ѻ������� EXPIRY_DATE
	 */
	private String expiryDate;
	
	/**
	 * ����ù��������Ѻ������� AMOUNT
	 */
	private float amount;
	
	/**
	 * ����ù��������Ѻ������� CAMPAIGN_TYPE
	 */
	private String campaignType;
	
	/**
	 * ����ù��������Ѻ������� STATUS
	 */	
	private String status;
	
	private String perType;
	
	private String classType;
	
	private String carType;
	
	private int carYear;
	
	private int campaignBroker;
	
	
	
	
	

	
	
	
	public int getCampaignBroker() {
		return campaignBroker;
	}

	public void setCampaignBroker(int campaignBroker) {
		this.campaignBroker = campaignBroker;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public int getCarYear() {
		return carYear;
	}

	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getPerType() {
		return perType;
	}

	public void setPerType(String perType) {
		this.perType = perType;
	}

	/**
	 * �ӡ�ô֧��Ңͧ����� effectiveDate
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * ��˹�������Ѻ����� effectiveDate
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * �ӡ�ô֧��Ңͧ����� expiryDate
	 * @return expiryDate �ѹ��������ѭ��
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * ��˹�������Ѻ����� expiryDate
	 * @param expiryDate �ѹ��������ѭ��
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * get status value
	 * @return status 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * set status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
	/**
	 * get amount
	 * @return amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * set amount
	 * @param amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * get campaign id value
	 * @return campaignId
	 */
	public int getCampaignId() {
		return campaignId;
	}

	
	/**
	 * set campaignId
	 * @param campaignId
	 */
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * get capaign name
	 * @return campaignName
	 */
	public String getCampaignName() {
		return campaignName;
	}

	/**
	 * set campaignName
	 * @param campaignName
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	/**
	 * get campaignType
	 * @return campaignType
	 */
	public String getCampaignType() {
		return campaignType;
	}

	/**
	 * set campaignType
	 * @param campaignType
	 */
	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public Campaign(int campaignId, String campaignName, String effectiveDate, String expiryDate, float amount, String campaignType, String status, String perType, String classType, String carType, int carYear, int campaignBroker) {
		super();
		this.campaignId = campaignId;
		this.campaignName = campaignName;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.amount = amount;
		this.campaignType = campaignType;
		this.status = status;
		this.perType = perType;
		this.classType = classType;
		this.carType = carType;
		this.carYear = carYear;
		this.campaignBroker = campaignBroker;
	}


	

	
	

	
}
