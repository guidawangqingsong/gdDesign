package cn.jjxx.modules.activiti.workflow.history;

import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;

/**
 * @Title: HistoryTaskComment.java .<br>
 * @Package org.framework.activiti.workflow.history .<br>
 * @Description: 历史审批任务及处理意见实体类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-28 下午2:52:17.<br>
 * @version V1.0.<br>
 */
public class HistoryTaskComment extends HistoricTaskInstanceEntity{

	private static final long serialVersionUID = 7780626043783166634L;
	/**历史批注Id*/
	private String historyCommentId;
	/**操作类型*/
	private String operateType;
	/**操作类型名称*/
	private String operateTypeName;
	/**操作时间*/
	private String operateTime;
	/**操作人Id*/
	private String userId;
	/**操作人名称*/
	private String userName;
	/**操作人类型*/
	private String action;
	/**处理消息*/
	private String message;
	
	public String getHistoryCommentId() {
		return historyCommentId;
	}
	public void setHistoryCommentId(String historyCommentId) {
		this.historyCommentId = historyCommentId;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getOperateTypeName() {
		return operateTypeName;
	}
	public void setOperateTypeName(String operateTypeName) {
		this.operateTypeName = operateTypeName;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
