package cn.jjxx.modules.workflow.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: act_ru_task
 * @Description: 正在运行的任务
 * @author jjxx
 * @date 2017-11-20 10:55:10
 * @version V1.0   
 *
 */
@TableName("act_ru_task")
@SuppressWarnings("serial")
public class Task extends AbstractEntity<String> {

    /**ID_*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**REV_*/
    @TableField(value = "REV_")
	private Integer rev;
    /**EXECUTION_ID_*/
    @TableField(value = "EXECUTION_ID_")
	private String executionId;
    /**PROC_INST_ID_*/
    @TableField(value = "PROC_INST_ID_")
	private String procInstId;
    /**PROC_DEF_ID_*/
    @TableField(value = "PROC_DEF_ID_")
	private String procDefId;
    /**NAME_*/
    @TableField(value = "NAME_")
	private String name;
    /**PARENT_TASK_ID_*/
    @TableField(value = "PARENT_TASK_ID_")
	private String parentTaskId;
    /**DESCRIPTION_*/
    @TableField(value = "DESCRIPTION_")
	private String description;
    /**TASK_DEF_KEY_*/
    @TableField(value = "TASK_DEF_KEY_")
	private String taskDefKey;
    /**OWNER_*/
    @TableField(value = "OWNER_")
	private String owner;
    /**ASSIGNEE_*/
    @TableField(value = "ASSIGNEE_")
	private String assignee;
    /**DELEGATION_*/
    @TableField(value = "DELEGATION_")
	private String delegation;
    /**PRIORITY_*/
    @TableField(value = "PRIORITY_")
	private Integer priority;
    /**CREATE_TIME_*/
    @TableField(value = "CREATE_TIME_")
	private Date createTime;
    /**DUE_DATE_*/
    @TableField(value = "DUE_DATE_")
	private Date dueDate;
    /**CATEGORY_*/
    @TableField(value = "CATEGORY_")
	private String category;
    /**SUSPENSION_STATE_*/
    @TableField(value = "SUSPENSION_STATE_")
	private Integer suspensionState;
    /**TENANT_ID_*/
    @TableField(value = "TENANT_ID_")
	private String tenantId;
    /**FORM_KEY_*/
    @TableField(value = "FORM_KEY_")
	private String formKey;
    @TableField(exist=false)
    private String flowName;
    @TableField(exist=false)
    private String assigneeName;
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
	 * 获取  rev
	 *@return: Integer  REV_
	 */
	public Integer getRev(){
		return this.rev;
	}

	/**
	 * 设置  rev
	 *@param: rev  REV_
	 */
	public void setRev(Integer rev){
		this.rev = rev;
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
	 * 获取  delegation
	 *@return: String  DELEGATION_
	 */
	public String getDelegation(){
		return this.delegation;
	}

	/**
	 * 设置  delegation
	 *@param: delegation  DELEGATION_
	 */
	public void setDelegation(String delegation){
		this.delegation = delegation;
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
	 * 获取  createTime
	 *@return: Date  CREATE_TIME_
	 */
	public Date getCreateTime(){
		return this.createTime;
	}

	/**
	 * 设置  createTime
	 *@param: createTime  CREATE_TIME_
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
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
	 * 获取  suspensionState
	 *@return: Integer  SUSPENSION_STATE_
	 */
	public Integer getSuspensionState(){
		return this.suspensionState;
	}

	/**
	 * 设置  suspensionState
	 *@param: suspensionState  SUSPENSION_STATE_
	 */
	public void setSuspensionState(Integer suspensionState){
		this.suspensionState = suspensionState;
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

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
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
