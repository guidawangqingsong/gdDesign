package cn.jjxx.modules.workflow.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 流程实例
 * @Description: 流程实例
 * @author jjxx
 * @date 2017-12-22 14:55:58
 * @version V1.0   
 *
 */
@TableName("act_hi_procinst")
@SuppressWarnings("serial")
public class ActHiProcinst extends AbstractEntity<String> {

    /**ID_*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**PROC_INST_ID_*/
    @TableField(value = "PROC_INST_ID_")
	private String procInstId;
    /**BUSINESS_KEY_*/
    @TableField(value = "BUSINESS_KEY_")
	private String businessKey;
    /**PROC_DEF_ID_*/
    @TableField(value = "PROC_DEF_ID_")
	private String procDefId;
    /**START_TIME_*/
    @TableField(value = "START_TIME_")
	private Date startTime;
    /**END_TIME_*/
    @TableField(value = "END_TIME_")
	private Date endTime;
    /**DURATION_*/
    @TableField(value = "DURATION_",el="duration.id")
	private Long duration;
    /**START_USER_ID_*/
    @TableField(value = "START_USER_ID_")
	private String startUserId;
    /**START_ACT_ID_*/
    @TableField(value = "START_ACT_ID_")
	private String startActId;
    /**END_ACT_ID_*/
    @TableField(value = "END_ACT_ID_")
	private String endActId;
    /**SUPER_PROCESS_INSTANCE_ID_*/
    @TableField(value = "SUPER_PROCESS_INSTANCE_ID_")
	private String superProcessInstanceId;
    /**DELETE_REASON_*/
    @TableField(value = "DELETE_REASON_")
	private String deleteReason;
    /**TENANT_ID_*/
    @TableField(value = "TENANT_ID_")
	private String tenantId;
    /**NAME_*/
    @TableField(value = "NAME_")
	private String name;
    @TableField(exist=false)
    private String flowType;//流程类型
    @TableField(exist=false)
    private String flowName;//流程名称
    @TableField(exist=false)
    private String flowIdentify;//流程标识
    @TableField(exist=false)
    private String startor;//发起人
    @TableField(exist=false)
    private String version;//版本
    @TableField(exist=false)
    private String taskId;
	
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
	 * 获取  businessKey
	 *@return: String  BUSINESS_KEY_
	 */
	public String getBusinessKey(){
		return this.businessKey;
	}

	/**
	 * 设置  businessKey
	 *@param: businessKey  BUSINESS_KEY_
	 */
	public void setBusinessKey(String businessKey){
		this.businessKey = businessKey;
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
	 *@return: Long  DURATION_
	 */
	public Long getDuration(){
		return this.duration;
	}

	/**
	 * 设置  duration
	 *@param: duration  DURATION_
	 */
	public void setDuration(Long duration){
		this.duration = duration;
	}
	/**
	 * 获取  startUserId
	 *@return: String  START_USER_ID_
	 */
	public String getStartUserId(){
		return this.startUserId;
	}

	/**
	 * 设置  startUserId
	 *@param: startUserId  START_USER_ID_
	 */
	public void setStartUserId(String startUserId){
		this.startUserId = startUserId;
	}
	/**
	 * 获取  startActId
	 *@return: String  START_ACT_ID_
	 */
	public String getStartActId(){
		return this.startActId;
	}

	/**
	 * 设置  startActId
	 *@param: startActId  START_ACT_ID_
	 */
	public void setStartActId(String startActId){
		this.startActId = startActId;
	}
	/**
	 * 获取  endActId
	 *@return: String  END_ACT_ID_
	 */
	public String getEndActId(){
		return this.endActId;
	}

	/**
	 * 设置  endActId
	 *@param: endActId  END_ACT_ID_
	 */
	public void setEndActId(String endActId){
		this.endActId = endActId;
	}
	/**
	 * 获取  superProcessInstanceId
	 *@return: String  SUPER_PROCESS_INSTANCE_ID_
	 */
	public String getSuperProcessInstanceId(){
		return this.superProcessInstanceId;
	}

	/**
	 * 设置  superProcessInstanceId
	 *@param: superProcessInstanceId  SUPER_PROCESS_INSTANCE_ID_
	 */
	public void setSuperProcessInstanceId(String superProcessInstanceId){
		this.superProcessInstanceId = superProcessInstanceId;
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

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowIdentify() {
		return flowIdentify;
	}

	public void setFlowIdentify(String flowIdentify) {
		this.flowIdentify = flowIdentify;
	}

	public String getStartor() {
		return startor;
	}

	public void setStartor(String startor) {
		this.startor = startor;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
}
