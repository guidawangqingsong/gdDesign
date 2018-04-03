package cn.jjxx.modules.sys.entity;

import java.util.Date;
import java.util.List;

import cn.jjxx.core.common.entity.TreeEntity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * @Title:
 * @Description:
 * @author www.jjxxkj.cn
 * @date 2017-02-07 21:06:09
 * @version V1.0
 *
 */
@TableName("sys_organization")
@SuppressWarnings("serial")
public class Organization extends TreeEntity<Organization> {
	@TableField(value = "create_by", el = "createBy.id", fill = FieldFill.INSERT)
	protected User createBy; // 创建者
	@TableField(value = "create_date", fill = FieldFill.INSERT)
	protected Date createDate; // 创建日期
	@TableField(value = "update_by", el = "updateBy.id", fill = FieldFill.UPDATE)
	protected User updateBy; // 更新者
	@TableField(value = "update_date", fill = FieldFill.UPDATE)
	protected Date updateDate; // 更新日期
	@TableField(value = "del_flag", fill = FieldFill.INSERT)
	protected String delFlag = "0"; // 删除标记（0：正常；1：删除 ）
	@TableField(value = "status" )
	protected Integer status = 0; // 状态（0：启用；1：注销 ）
	@TableField(value = "type")
	private Integer type; // 组织类型（0：管理单元；1：组织单元 ）
	@TableField(value = "code")
	private String code; // 组织编码
	/** 备注 */
	@TableField(value = "remarks")
	private String remarks;
	@TableField(value = "org_type")
	private String orgType;
	@TableField(exist = false)
	private String  orgTypeName;
	
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

}
