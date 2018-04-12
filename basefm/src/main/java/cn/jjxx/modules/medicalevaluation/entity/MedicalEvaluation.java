package cn.jjxx.modules.medicalevaluation.entity;

import cn.jjxx.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import cn.jjxx.modules.sys.entity.User;

import java.util.Date;

/**   
 * @Title: 医用系统民主测评
 * @Description: 医用系统民主测评
 * @author jjxx.wangqingosng
 * @date 2018-04-10 16:40:53
 * @version V1.0   
 *
 */
@TableName("medical_evaluation")
@SuppressWarnings("serial")
public class MedicalEvaluation extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**创建者*/
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
    /**更新者名称*/
    @TableField(exist=false)
    private String updateByName;
    public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
    
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
    /**备注信息*/
    @TableField(value = "remarks")
	private String remarks;
    /**组织评定*/
    @TableField(value = "origin_eva")
	private Integer originEva;
    /**系统前端界面*/
    @TableField(value = "sys_frontUI")
	private String sysFrontUI;
    /**系统后台设计*/
    @TableField(value = "sys_backstage")
	private String sysBackstage;
    /**系统主要功能*/
    @TableField(value = "sys_predict")
	private Integer sysPredict;
    /**系统设置*/
    @TableField(value = "sys_config")
	private String sysConfig;
    /**员工编号*/
    @TableField(value = "staff_id")
	private String staffId;
    /**组织编号*/
    @TableField(value = "org_id")
	private String orgId;
    /**测评附件*/
    @TableField(value = "eva_attach")
	private String evaAttach;
	
    /**职员编号*/
    @TableField(exist=false)
    private String staffNumber;
    
	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
    
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
	 * 获取  originEva
	 *@return: String  组织评定
	 */
	public Integer getOriginEva(){
		return this.originEva;
	}

	/**
	 * 设置  originEva
	 *@param: originEva  组织评定
	 */
	public void setOriginEva(Integer originEva){
		this.originEva = originEva;
	}
	/**
	 * 获取  sysFrontUI
	 *@return: String  系统前端界面
	 */
	public String getSysFrontUI(){
		return this.sysFrontUI;
	}

	/**
	 * 设置  sysFrontUI
	 *@param: sysFrontUI  系统前端界面
	 */
	public void setSysFrontUI(String sysFrontUI){
		this.sysFrontUI = sysFrontUI;
	}
	/**
	 * 获取  sysBackstage
	 *@return: String  系统后台设计
	 */
	public String getSysBackstage(){
		return this.sysBackstage;
	}

	/**
	 * 设置  sysBackstage
	 *@param: sysBackstage  系统后台设计
	 */
	public void setSysBackstage(String sysBackstage){
		this.sysBackstage = sysBackstage;
	}
	/**
	 * 获取  sysPredict
	 *@return: String  系统主要功能
	 */
	public Integer getSysPredict(){
		return this.sysPredict;
	}

	/**
	 * 设置  sysPredict
	 *@param: sysPredict  系统主要功能
	 */
	public void setSysPredict(Integer sysPredict){
		this.sysPredict = sysPredict;
	}
	/**
	 * 获取  sysConfig
	 *@return: String  系统设置
	 */
	public String getSysConfig(){
		return this.sysConfig;
	}

	/**
	 * 设置  sysConfig
	 *@param: sysConfig  系统设置
	 */
	public void setSysConfig(String sysConfig){
		this.sysConfig = sysConfig;
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
	 * 获取  orgId
	 *@return: String  组织编号
	 */
	public String getOrgId(){
		return this.orgId;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  组织编号
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	/**
	 * 获取  evaAttach
	 *@return: String  测评附件
	 */
	public String getEvaAttach(){
		return this.evaAttach;
	}

	/**
	 * 设置  evaAttach
	 *@param: evaAttach  测评附件
	 */
	public void setEvaAttach(String evaAttach){
		this.evaAttach = evaAttach;
	}
	
}
