package cn.jjxx.modules.medicalinfo.entity;

import cn.jjxx.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import cn.jjxx.modules.sys.entity.User;

import java.util.Date;

/**   
 * @Title: 医疗费用总表
 * @Description: 医疗费用总表
 * @author jjxx
 * @date 2018-04-08 14:33:15
 * @version V1.0   
 *
 */
@TableName("main_medical_info")
@SuppressWarnings("serial")
public class MainMedicalInfo extends AbstractEntity<String> {

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
    /**挂号费*/
    @TableField(value = "register_fee")
	private Double registerFee;
    /**医药费*/
    @TableField(value = "medical_fee")
	private Double medicalFee;
    /**治疗费*/
    @TableField(value = "treatment_fee")
	private Double treatmentFee;
    /**住院费*/
    @TableField(value = "hospital_fee")
	private Double hospitalFee;
    /**其他*/
    @TableField(value = "other")
	private Double other;
    /**人均费用（预测）*/
    @TableField(value = "personal_fee")
	private Double personalFee;
    /**员工编号*/
    @TableField(value = "staff_id")
	private String staffId;
    /**组织编号*/
    @TableField(value = "org_id")
	private String orgId;
	
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
	 * 获取  registerFee
	 *@return: Double  挂号费
	 */
	public Double getRegisterFee(){
		return this.registerFee;
	}

	/**
	 * 设置  registerFee
	 *@param: registerFee  挂号费
	 */
	public void setRegisterFee(Double registerFee){
		this.registerFee = registerFee;
	}
	/**
	 * 获取  medicalFee
	 *@return: Double  医药费
	 */
	public Double getMedicalFee(){
		return this.medicalFee;
	}

	/**
	 * 设置  medicalFee
	 *@param: medicalFee  医药费
	 */
	public void setMedicalFee(Double medicalFee){
		this.medicalFee = medicalFee;
	}
	/**
	 * 获取  treatmentFee
	 *@return: Double  治疗费
	 */
	public Double getTreatmentFee(){
		return this.treatmentFee;
	}

	/**
	 * 设置  treatmentFee
	 *@param: treatmentFee  治疗费
	 */
	public void setTreatmentFee(Double treatmentFee){
		this.treatmentFee = treatmentFee;
	}
	/**
	 * 获取  hospitalFee
	 *@return: Double  住院费
	 */
	public Double getHospitalFee(){
		return this.hospitalFee;
	}

	/**
	 * 设置  hospitalFee
	 *@param: hospitalFee  住院费
	 */
	public void setHospitalFee(Double hospitalFee){
		this.hospitalFee = hospitalFee;
	}
	/**
	 * 获取  other
	 *@return: Double  其他
	 */
	public Double getOther(){
		return this.other;
	}

	/**
	 * 设置  other
	 *@param: other  其他
	 */
	public void setOther(Double other){
		this.other = other;
	}
	/**
	 * 获取  personalFee
	 *@return: Double  人均费用（预测）
	 */
	public Double getPersonalFee(){
		return this.personalFee;
	}

	/**
	 * 设置  personalFee
	 *@param: personalFee  人均费用（预测）
	 */
	public void setPersonalFee(Double personalFee){
		this.personalFee = personalFee;
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
	
}
