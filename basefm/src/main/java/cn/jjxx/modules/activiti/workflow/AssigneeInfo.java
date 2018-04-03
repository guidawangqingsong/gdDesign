package cn.jjxx.modules.activiti.workflow;

import java.io.Serializable;

/**
 * @Title: AssigneeInfo.java .<br>
 * @Package org.framework.activiti.workflow .<br>
 * @Description: 处理人信息集合实体类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-28 下午2:57:54.<br>
 * @version V1.0.<br>
 */
public class AssigneeInfo implements Serializable{

	private static final long serialVersionUID = 5103126916465136570L;
	/**主键Id*/
	private String id;
	/**处理人账号Id*/
	private String assigneeId;
	/**处理人账号名称*/
	private String assigneeName;
	/**分组Id*/
	private String groupId;
	/**分组名称*/
	private String groupName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
