package cn.jjxx.modules.activiti.entity;

import java.io.Serializable;
import java.util.Date;

public class ActivitiHistoryTaskEntity extends ActivitiTaskEntity implements Serializable{

	private static final long serialVersionUID = -6994426617362663467L;
	
	/**任务结束时间*/
	private Date endTime;
	/**任务提醒时间*/
	private Date claimTime;
	/**耗时*/
	private String duration;
	/**删除原因*/
	private String deleteReason;
	/**desinger节点定义的form_key属性*/
	private String formKey;
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getClaimTime() {
		return claimTime;
	}
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

}
