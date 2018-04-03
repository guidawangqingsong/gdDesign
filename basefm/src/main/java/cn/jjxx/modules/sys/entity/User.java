package cn.jjxx.modules.sys.entity;

import cn.jjxx.core.common.entity.DataEntity;

import java.lang.String;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @Title 用户实体
 * @Description:
 * @author www.jjxxkj.cn
 * @date 2016-12-03 21:31:50
 * @version V1.0
 *
 */
@TableName("sys_user")
@SuppressWarnings("serial")
public class User extends DataEntity<String> {

	/**
	 * 是否锁定（1：正常；-1：删除；0：锁定；）
	 */
	public static final String STATUS_DELETE = "-1";
	public static final String STATUS_LOCKED = "0";
	public static final String STATUS_NORMAL = "1";
	/**
	 * 是否管理员（0：普通用户 1：管理员）
	 */
	public static final String ADMIN_NORMAL = "0";
	public static final String ADMIN_VIP = "1";
	/** id */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	// 姓名
	private String username;
	// 用户名
	private String realname;
	// 头像
	private String portrait;
	// 密码
	private String password;
	// 盐
	private String salt;
	// 邮件
	private String email;
	// 联系电话
	private String phone;
	
	private String type;
	@TableField(value="org_id")
	private String orgId;
	
	@TableField(exist = false)
	private String orgName;
	
	@TableField(value="morg_id")
	private String morgId;
	
	@TableField(exist = false)
	private String morgName;
	
	@TableField(value="staff_id")
	private String staffId;
	
	@TableField(exist = false)
	private String staffName;
	
	@TableField(exist = false)
	private String oldStaffId;
	
	@TableField(value="admin_type")
	private String adminType ;
	
	
	/**
	 * 系统用户的状态
	 */
	private String status = STATUS_NORMAL;

	/**
	 * 获取 username
	 *
	 * @return: String username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * 设置 username
	 *
	 * @param: username
	 *             username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取 password
	 *
	 * @return: String password
	 */
	public String getPassword() {
		return this.password;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	/**
	 * 设置 password
	 *
	 * @param: password
	 *             password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 id
	 *
	 * @return: String id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 *
	 * @param: id
	 *             id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 salt
	 *
	 * @return: String salt
	 */
	public String getSalt() {
		return this.salt;
	}

	/**
	 * 设置 salt
	 *
	 * @param: salt
	 *             salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCredentialsSalt() {
		return username + salt;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMorgId() {
		return morgId;
	}

	public void setMorgId(String morgId) {
		this.morgId = morgId;
	}

	public String getMorgName() {
		return morgName;
	}

	public void setMorgName(String morgName) {
		this.morgName = morgName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getOldStaffId() {
		return oldStaffId;
	}

	public void setOldStaffId(String oldStaffId) {
		this.oldStaffId = oldStaffId;
	}

	public String getAdminType() {
		return adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	
	
}
