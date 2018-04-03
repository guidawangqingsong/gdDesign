package cn.jjxx.modules.oa.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jjxx.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 员工日志管理
 * @Description: 员工日志管理
 * @author grace
 * @date 2018-02-06 11:40:23
 * @version V1.0   
 *
 */
@TableName("oa_work_log")
@SuppressWarnings("serial")
public class OaWorkLog extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**创建者id*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    
    /**创建者名称*/
    @TableField(exist=false)
    private String createByName;
    public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	/**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新者*/
    @TableField(value = "update_by",el="updateBy.id",fill = FieldFill.UPDATE)
	private User updateBy;
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
    /**备注信息*/
    @TableField(value = "remarks")
	private String remarks;
    /**员工编号*/
    @TableField(value = "staff_id")
	private String staffId;
    /**日志主题*/
    @TableField(value = "log_theme")
	private String logTheme;
    /**日志内容*/
    @TableField(value = "log_content")
	private String logContent;
    /**明天计划*/
    @TableField(value = "log_nextplan")
	private String nextPlan;
    /**附件*/
    @TableField(value = "log_attach")
	private String logAttach;
    /**日志状态*/
    @TableField(value = "log_state")
	private String logState;
    /**遗留问题*/
    @TableField(value = "unfinished")
	private String unfinished;
    /**未完成的原因*/
    @TableField(value = "reason")
	private String reason;
    /**所属组织*/
    @TableField(value = "org_id")
	private String orgId;
    /**日志类型*/
    @TableField(value = "log_type")
	private String logType;
   
    /**职员编号*/
    @TableField(exist=false)
    private String staffNumber;
    
    /**日志时间*/
    @TableField(value = "log_time")
    private Date logTime;

    /**附件数量*/
    @TableField(exist = false)
    private int attachCount;
    
	

	/**
	 * 获取  id
	 *@return: String  字段主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  字段主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  createBy
	 *@return: User  创建者
	 */
	public User getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建者
	 */
	public void setCreateBy(User createBy){
		this.createBy = createBy;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建时间
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateBy
	 *@return: User  更新者
	 */
	public User getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新者
	 */
	public void setUpdateBy(User updateBy){
		this.updateBy = updateBy;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  更新时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  delFlag
	 *@return: String  删除标记（0：正常；1：删除）
	 */
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  删除标记（0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注信息
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注信息
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	/**
	 * 获取  staffId
	 *@return: String  员工编号
	 */
	public String getStaffId(){
		return this.staffId;
	}

	/**
	 * 设置  staffId
	 *@param: staffId  员工编号
	 */
	public void setStaffId(String staffId){
		this.staffId = staffId;
	}
	/**
	 * 获取  logTheme
	 *@return: String  日志主题
	 */
	public String getLogTheme(){
		return this.logTheme;
	}

	/**
	 * 设置  logTheme
	 *@param: logTheme  日志主题
	 */
	public void setLogTheme(String logTheme){
		this.logTheme = logTheme;
	}
	/**
	 * 获取  logContent
	 *@return: String  日志内容
	 */
	public String getLogContent(){
		return this.logContent;
	}

	/**
	 * 设置  logContent
	 *@param: logContent  日志内容
	 */
	public void setLogContent(String logContent){
		this.logContent = logContent;
	}
	/**
	 * 获取  nextPlan
	 *@return: String  明天计划
	 */
	public String getNextPlan(){
		return this.nextPlan;
	}

	/**
	 * 设置  nextPlan
	 *@param: nextPlan  明天计划
	 */
	public void setNextPlan(String nextPlan){
		this.nextPlan = nextPlan;
	}
	/**
	 * 获取  logAttach
	 *@return: String  附件
	 */
	public String getLogAttach(){
		return this.logAttach;
	}

	/**
	 * 设置  logAttach
	 *@param: logAttach  附件
	 */
	public void setLogAttach(String logAttach){
		this.logAttach = logAttach;
	}
	/**
	 * 获取  logState
	 *@return: Integer  日志状态
	 */
	public String getLogState(){
		return this.logState;
	}

	/**
	 * 设置  logState
	 *@param: logState  日志状态
	 */
	public void setLogState(String logState){
		this.logState = logState;
	}
	/**
	 * 获取  unfinished
	 *@return: String  遗留问题
	 */
	public String getUnfinished(){
		return this.unfinished;
	}

	/**
	 * 设置  unfinished
	 *@param: unfinished  遗留问题
	 */
	public void setUnfinished(String unfinished){
		this.unfinished = unfinished;
	}
	/**
	 * 获取  reason
	 *@return: String  未完成的原因
	 */
	public String getReason(){
		return this.reason;
	}

	/**
	 * 设置  reason
	 *@param: reason  未完成的原因
	 */
	public void setReason(String reason){
		this.reason = reason;
	}
	/**
	 * 获取  orgId
	 *@return: String  所属组织
	 */
	public String getOrgId(){
		return this.orgId;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  所属组织
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	/**
	 * 获取  logType
	 *@return: String  日志类型
	 */
	public String getLogType(){
		return this.logType;
	}

	/**
	 * 设置  logType
	 *@param: logType  日志类型
	 */
	public void setLogType(String logType){
		this.logType = logType;
	}

	
	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	
	
	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	
	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	
	/**
	 * 获取  orgName
	 *@return: String  组织名称
	 *//*
	public String getOrgName(){
		return this.orgName;
	}

	*//**
	 * 设置  orgName
	 *@param: orgName  组织名称
	 *//*
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}*/
	
	
	
}
