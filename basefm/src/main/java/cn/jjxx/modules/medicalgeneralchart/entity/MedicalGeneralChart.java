package cn.jjxx.modules.medicalgeneralchart.entity;

import cn.jjxx.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import cn.jjxx.modules.sys.entity.User;

import java.util.Date;

/**   
 * @Title: 综合分析
 * @Description: 综合分析
 * @author jjxx.wangqingsong
 * @date 2018-04-11 16:02:57
 * @version V1.0   
 *
 */
@TableName("medical_general_chart")
@SuppressWarnings("serial")
public class MedicalGeneralChart extends AbstractEntity<String> {

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
    /**员工编号*/
    @TableField(value = "staff_id")
	private String staffId;
    /**职员编号*/
    @TableField(exist=false)
    private String staffNumber;
    
	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

    
    /**组织编号*/
    @TableField(value = "org_id")
	private String orgId;
    /**好评数量*/
    @TableField(value = "high_opinon")
	private Integer highOpinon;
    /**差评数量*/
    @TableField(value = "low_opinon")
	private Integer lowOpinon;
	
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
	 * 获取  highOpinon
	 *@return: Integer  好评数量
	 */
	public Integer getHighOpinon(){
		return this.highOpinon;
	}

	/**
	 * 设置  highOpinon
	 *@param: highOpinon  好评数量
	 */
	public void setHighOpinon(Integer highOpinon){
		this.highOpinon = highOpinon;
	}
	/**
	 * 获取  lowOpinon
	 *@return: Integer  差评数量
	 */
	public Integer getLowOpinon(){
		return this.lowOpinon;
	}

	/**
	 * 设置  lowOpinon
	 *@param: lowOpinon  差评数量
	 */
	public void setLowOpinon(Integer lowOpinon){
		this.lowOpinon = lowOpinon;
	}
	
}
