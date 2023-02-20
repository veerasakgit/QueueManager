/**
 * 
 */
package th.co.msitb.spool.message;

import th.co.msat.motor.queuemanager.message.Message;


/**
 * @author jutamas
 *
 */
public class SpoolMessage implements Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String batchNo;
	private String sequence;
	private String username;
	private long jobgroup;
	private String typeId;
	private String typeName;
	private String broker;
	private String client;
	private String mak;
	private String model;
	private String license;
	private String chassis;
	private String insureName;
	private String policyNo;
	private String referDFS;	
	private String billPayment;
	private String dueDate;

	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getBillPayment() {
		return billPayment;
	}
	public void setBillPayment(String billPayment) {
		this.billPayment = billPayment;
	}
	public String getReferDFS() {
		return batchNo+"#"+sequence+"#"+typeId;
	}
	public void setReferDFS(String referDFS) {
		this.referDFS = referDFS;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getInsureName() {
		return insureName;
	}
	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getMak() {
		return mak;
	}
	public void setMak(String mak) {
		this.mak = mak;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getReferenceId() {
		// TODO Auto-generated method stub
		return batchNo+"#"+sequence;
	}
	public long getJobgroup() {
		return jobgroup;
	}
	public void setJobgroup(long jobgroup) {
		this.jobgroup = jobgroup;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	

}
