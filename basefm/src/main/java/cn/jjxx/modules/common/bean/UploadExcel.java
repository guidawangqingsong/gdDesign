package cn.jjxx.modules.common.bean;

import java.io.Serializable;

public class UploadExcel implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO .<br>
	 */
	private static final long serialVersionUID = -4330221773714825432L;
	
	/**上传表单的控制名*/
	private String controllerName;
	/**选择的节点Id*/
	private String nodeId;
	/**其他信息*/
	private String others;
	
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
}
