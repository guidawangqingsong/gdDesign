package cn.jjxx.modules.workflow.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 历史任务表
 * @Description: 历史任务表
 * @author jjxx
 * @date 2017-11-24 12:55:31
 * @version V1.0   
 *
 */
@TableName("act_hi_taskinst")
@SuppressWarnings("serial")
public class HisTask extends AbstractEntity<String> {

    /**ID_*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**PROC_DEF_ID_*/
    @TableField(value = "PROC_DEF_ID_")
	private String procDefId;
    /**TASK_DEF_KEY_*/
    @TableField(value = "TASK_DEF_KEY_")
	private String taskDefKey;
    /**PROC_INST_ID_*/
    @TableField(value = "PROC_INST_ID_")
	private String procInstId;
    /**EXECUTION_ID_*/
    @TableField(value = "EXECUTION_ID_")
	private String executionId;
    /**NAME_*/
    @TableField(value = "NAME_")
	private String name;
    /**PARENT_TASK_ID_*/
    @TableField(value = "PARENT_TASK_ID_")
	private String parentTaskId;
    /**DESCRIPTION_*/
    @TableField(value = "DESCRIPTION_")
	private String description;
    /**OWNER_*/
    @TableField(value = "OWNER_")
	private String owner;
    /**ASSIGNEE_*/
    @TableField(value = "ASSIGNEE_")
	private String assignee;
    /**START_TIME_*/
    @TableField(value = "START_TIME_")
	private Date startTime;
    /**CLAIM_TIME_*/
    @TableField(value = "CLAIM_TIME_")
	private Date claimTime;
    /**END_TIME_*/
    @TableField(value = "END_TIME_")
	private Date endTime;
    /**DURATION_*/
    @TableField(value = "DURATION_")
	private String duration;
    /**DELETE_REASON_*/
    @TableField(value = "DELETE_REASON_")
	private String deleteReason;
    /**PRIORITY_*/
    @TableField(value = "PRIORITY_")
	private Integer priority;
    /**DUE_DATE_*/
    @TableField(value = "DUE_DATE_")
	private Date dueDate;
    /**FORM_KEY_*/
    @TableField(value = "FORM_KEY_")
	private String formKey;
    /**CATEGORY_*/
    @TableField(value = "CATEGORY_")
	private String category;
    /**TENANT_ID_*/
    @TableField(value = "TENANT_ID_")
	private String tenantId;
    @TableField(exist=false)
    private String processName;
    @TableField(exist=false)
    private String assigneeName;
    @TableField(exist=false)
    private String type;
    @TableField(exist=false)
    private String message;
    @TableField(exist=false)
    private String attachName;
    @TableField(exist=false)
    private String flowType;
    @TableField(exist=false)
    private String startor;
	
	/**
	 * 获取  id
	 *@return: String  ID_
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  ID_
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  procDefId
	 *@return: String  PROC_DEF_ID_
	 */
	public String getProcDefId(){
		return this.procDefId;
	}

	/**
	 * 设置  procDefId
	 *@param: procDefId  PROC_DEF_ID_
	 */
	public void setProcDefId(String procDefId){
		this.procDefId = procDefId;
	}
	/**
	 * 获取  taskDefKey
	 *@return: String  TASK_DEF_KEY_
	 */
	public String getTaskDefKey(){
		return this.taskDefKey;
	}

	/**
	 * 设置  taskDefKey
	 *@param: taskDefKey  TASK_DEF_KEY_
	 */
	public void setTaskDefKey(String taskDefKey){
		this.taskDefKey = taskDefKey;
	}
	/**
	 * 获取  procInstId
	 *@return: String  PROC_INST_ID_
	 */
	public String getProcInstId(){
		return this.procInstId;
	}

	/**
	 * 设置  procInstId
	 *@param: procInstId  PROC_INST_ID_
	 */
	public void setProcInstId(String procInstId){
		this.procInstId = procInstId;
	}
	/**
	 * 获取  executionId
	 *@return: String  EXECUTION_ID_
	 */
	public String getExecutionId(){
		return this.executionId;
	}

	/**
	 * 设置  executionId
	 *@param: executionId  EXECUTION_ID_
	 */
	public void setExecutionId(String executionId){
		this.executionId = executionId;
	}
	/**
	 * 获取  name
	 *@return: String  NAME_
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  NAME_
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  parentTaskId
	 *@return: String  PARENT_TASK_ID_
	 */
	public String getParentTaskId(){
		return this.parentTaskId;
	}

	/**
	 * 设置  parentTaskId
	 *@param: parentTaskId  PARENT_TASK_ID_
	 */
	public void setParentTaskId(String parentTaskId){
		this.parentTaskId = parentTaskId;
	}
	/**
	 * 获取  description
	 *@return: String  DESCRIPTION_
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * 设置  description
	 *@param: description  DESCRIPTION_
	 */
	public void setDescription(String description){
		this.description = description;
	}
	/**
	 * 获取  owner
	 *@return: String  OWNER_
	 */
	public String getOwner(){
		return this.owner;
	}

	/**
	 * 设置  owner
	 *@param: owner  OWNER_
	 */
	public void setOwner(String owner){
		this.owner = owner;
	}
	/**
	 * 获取  assignee
	 *@return: String  ASSIGNEE_
	 */
	public String getAssignee(){
		return this.assignee;
	}

	/**
	 * 设置  assignee
	 *@param: assignee  ASSIGNEE_
	 */
	public void setAssignee(String assignee){
		this.assignee = assignee;
	}
	/**
	 * 获取  startTime
	 *@return: Date  START_TIME_
	 */
	public Date getStartTime(){
		return this.startTime;
	}

	/**
	 * 设置  startTime
	 *@param: startTime  START_TIME_
	 */
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	/**
	 * 获取  claimTime
	 *@return: Date  CLAIM_TIME_
	 */
	public Date getClaimTime(){
		return this.claimTime;
	}

	/**
	 * 设置  claimTime
	 *@param: claimTime  CLAIM_TIME_
	 */
	public void setClaimTime(Date claimTime){
		this.claimTime = claimTime;
	}
	/**
	 * 获取  endTime
	 *@return: Date  END_TIME_
	 */
	public Date getEndTime(){
		return this.endTime;
	}

	/**
	 * 设置  endTime
	 *@param: endTime  END_TIME_
	 */
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	/**
	 * 获取  duration
	 *@return: String  DURATION_
	 */
	public String getDuration(){
		return this.duration;
	}

	/**
	 * 设置  duration
	 *@param: duration  DURATION_
	 */
	public void setDuration(String duration){
		this.duration = duration;
	}
	/**
	 * 获取  deleteReason
	 *@return: String  DELETE_REASON_
	 */
	public String getDeleteReason(){
		return this.deleteReason;
	}

	/**
	 * 设置  deleteReason
	 *@param: deleteReason  DELETE_REASON_
	 */
	public void setDeleteReason(String deleteReason){
		this.deleteReason = deleteReason;
	}
	/**
	 * 获取  priority
	 *@return: Integer  PRIORITY_
	 */
	public Integer getPriority(){
		return this.priority;
	}

	/**
	 * 设置  priority
	 *@param: priority  PRIORITY_
	 */
	public void setPriority(Integer priority){
		this.priority = priority;
	}
	/**
	 * 获取  dueDate
	 *@return: Date  DUE_DATE_
	 */
	public Date getDueDate(){
		return this.dueDate;
	}

	/**
	 * 设置  dueDate
	 *@param: dueDate  DUE_DATE_
	 */
	public void setDueDate(Date dueDate){
		this.dueDate = dueDate;
	}
	/**
	 * 获取  formKey
	 *@return: String  FORM_KEY_
	 */
	public String getFormKey(){
		return this.formKey;
	}

	/**
	 * 设置  formKey
	 *@param: formKey  FORM_KEY_
	 */
	public void setFormKey(String formKey){
		this.formKey = formKey;
	}
	/**
	 * 获取  category
	 *@return: String  CATEGORY_
	 */
	public String getCategory(){
		return this.category;
	}

	/**
	 * 设置  category
	 *@param: category  CATEGORY_
	 */
	public void setCategory(String category){
		this.category = category;
	}
	/**
	 * 获取  tenantId
	 *@return: String  TENANT_ID_
	 */
	public String getTenantId(){
		return this.tenantId;
	}

	/**
	 * 设置  tenantId
	 *@param: tenantId  TENANT_ID_
	 */
	public void setTenantId(String tenantId){
		this.tenantId = tenantId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getStartor() {
		return startor;
	}

	public void setStartor(String startor) {
		this.startor = startor;
	}
	
}
