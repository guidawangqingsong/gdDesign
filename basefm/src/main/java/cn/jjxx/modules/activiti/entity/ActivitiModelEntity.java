package cn.jjxx.modules.activiti.entity;

import java.io.Serializable;

import org.activiti.engine.impl.persistence.entity.ModelEntity;

import cn.jjxx.modules.activiti.config.Contacts;

public class ActivitiModelEntity extends ModelEntity implements Serializable{

	private static final long serialVersionUID = 3569794155966896973L;
	/***/
	/**模型分类*/
	private String modelType;
	/**模型名称*/
	private String modelName;
	/**模型的key*/
	private String modelKey = Contacts.modelKey.L.toString()+Contacts.getUUId();
	/**模型描述*/
	private String discription;
	/**组织*/
	private String org;
	/**状态*/
	private int status;
	
	
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelKey() {
		return modelKey;
	}
	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
