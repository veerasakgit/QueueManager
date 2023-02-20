package th.co.msat.campaign.accumulate;

public class ClientModel {
	private String clientCode;
	
	private String clientCode2;
	
	private String classType;
	
	private String modelId;
	
	private String broker;
	
	
	private String subModel;

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getClientCode2() {
		return clientCode2;
	}

	public void setClientCode2(String clientCode2) {
		this.clientCode2 = clientCode2;
	}

	public String getSubModel() {
		return subModel;
	}

	public void setSubModel(String subModel) {
		this.subModel = subModel;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public ClientModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ClientModel(String clientCode, String clientCode2, String classType, String modelId, String broker, String subModel) {
		super();
		this.clientCode = clientCode;
		this.clientCode2 = clientCode2;
		this.classType = classType;
		this.modelId = modelId;
		this.broker = broker;
		this.subModel = subModel;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	
	
	
}
