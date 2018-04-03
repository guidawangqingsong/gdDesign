package cn.jjxx.modules.activiti.entity;

import java.io.Serializable;
import java.util.Date;

public class ActivitiTaskEntity  implements Serializable{

	private static final long serialVersionUID = 4091973767322358858L;
	
	/**任务ID*/
	private String taskId;
	/**执行实例ID*/
	private String executionId;
	/**流程实例ID*/
	private String procInstId;
	/**流程定义ID*/
	private String procDefId;
	/**流程名称*/
	private String processName;
	/**节点定义名称*/
	private String activitiName;
	/**节点定义的KEY*/
	private String taskDefKey;
	/**节点定义描述*/
	private String description;
	/**处理人*/
	private String assignee;
	/**实际签收人*/
	private String owner;
	/**创建时间*/
	private Date startTime;
	/**过期时间*/
	private Date dueDate;
	/**是否挂起*/
	private int suspensionState;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	public String getProcDefId() {
		return procDefId;
	}
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getActivitiName() {
		return activitiName;
	}
	public void setActivitiName(String activitiName) {
		this.activitiName = activitiName;
	}
	public String getTaskDefKey() {
		return taskDefKey;
	}
	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getSuspensionState() {
		return suspensionState;
	}
	public void setSuspensionState(int suspensionState) {
		this.suspensionState = suspensionState;
	}
	
}
