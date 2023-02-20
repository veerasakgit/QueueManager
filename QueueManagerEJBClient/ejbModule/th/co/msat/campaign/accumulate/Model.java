package th.co.msat.campaign.accumulate;

import java.io.Serializable;

public class Model implements Serializable{
	private String modelId;
	
	private String subModel;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getSubModel() {
		return subModel;
	}

	public void setSubModel(String subModel) {
		this.subModel = subModel;
	}

	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Model(String modelId, String subModel) {
		super();
		this.modelId = modelId;
		this.subModel = subModel;
	}
	
	
}
