package cn.jjxx.modules.sys.entity;

import cn.jjxx.core.common.entity.AbstractEntity;

import java.beans.Transient;
import java.lang.String;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2017-02-16 14:19:35
 * @version V1.0
 *
 */
@TableName("sys_user_organization")
@SuppressWarnings("serial")
public class UserOrganization extends AbstractEntity<String> {

	/** 编号 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/** 用户编号 */
	private String userId;
	@TableField(exist = false)
	private User user;
	/** 部门编号 */
	private String organizationId;
	@TableField(exist = false)
	private Organization organization;

	/**
	 * 获取 id
	 * 
	 * @return: String 编号
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 * 
	 * @param: id
	 *             编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
