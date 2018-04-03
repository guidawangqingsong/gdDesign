package cn.jjxx.modules.activiti.entity;

import java.io.Serializable;

public class ActivitiProcessDefinitionEntity implements Serializable{

	private static final long serialVersionUID = 2242434735406524503L;
	
	/**流程定义Id*/
	private String id;
	/**流程定义名称*/
	private String name;
	/**流程定义key*/
	private String key;
	/**流程定义部署Id*/
	private String deploymentId;
	/**流程定义版本号*/
	private int version;
	/**流程定义bpmn文件名称*/
	private String resourceName;
	/**流程定义图片职员名称*/
	private String diagramResourceName;
	/**流程定义描述*/
	private String description;
	/**是否挂起*/
	private int suspensionState;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getDiagramResourceName() {
		return diagramResourceName;
	}
	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSuspensionState() {
		return suspensionState;
	}
	public void setSuspensionState(int suspensionState) {
		this.suspensionState = suspensionState;
	}
	
}
