package cn.jjxx.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName(value="sys_user_org_menu")
@SuppressWarnings("serial")
public class UserOrgMenu implements java.io.Serializable{

	/** 编号 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	@TableField(value="user_id")
	private String userId;
	@TableField(value="org_id")
	private String orgId;
	@TableField(value="menu_id")
	private String menuId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
}
