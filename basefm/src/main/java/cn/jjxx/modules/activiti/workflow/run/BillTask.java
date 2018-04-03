package cn.jjxx.modules.activiti.workflow.run;

import java.util.Date;

import org.activiti.engine.impl.persistence.entity.TaskEntity;

/**
 * @Title: BillTask.java .<br>
 * @Package org.framework.activiti.workflow.run .<br>
 * @Description: 单据任务相关联的实体类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-29 下午4:57:10.<br>
 * @version V1.0.<br>
 */
public class BillTask {

	/**主键Id*/
	private String id;
	/**任务名称*/
	private String name;
	/**模块名称*/
	private String modelName;
	/**单据编号*/
	private String billNumber;
	/**单据名称*/
	private String billName;
	/**处理人*/
	private String assigneeName;
	/**开始时间*/
	private String startTime;
	/**结束时间*/
	private String endTime;
	/**时长*/
	private Long duration;
	/**描述*/
	private String description;
	
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
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
