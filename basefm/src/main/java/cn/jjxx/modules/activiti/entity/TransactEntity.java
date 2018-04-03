package cn.jjxx.modules.activiti.entity;

import java.io.Serializable;

public class TransactEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO .<br>
	 */
	private static final long serialVersionUID = 6964893800808030951L;

	/**主键*/
	private String id;
	/**任务Id*/
	private String taskId;
	/**处理人*/
	private String assignee;
	/**处理人名称*/
	private String assigneeName;
	/**转办原因*/
	private String reason;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
