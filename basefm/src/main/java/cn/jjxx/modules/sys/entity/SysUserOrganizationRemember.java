package cn.jjxx.modules.sys.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**   
 * @Title: sys_user_organization_remember
 * @Description: sys_user_organization_remember
 * @author jjxx
 * @date 2017-12-25 18:07:59
 * @version V1.0   
 *
 */
@TableName("sys_user_organization_remember")
@SuppressWarnings("serial")
public class SysUserOrganizationRemember extends AbstractEntity<String> {

    /**编号*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**用户主键*/
    @TableField(value = "user_id")
	private String userId;
    /**用户姓名*/
    @TableField(value = "user_name")
	private String userName;
    /**部门主键*/
    @TableField(value = "organization_id")
	private String organizationId;
    /**默认登陆组织名称*/
    @TableField(value = "organization_name")
	private String organizationName;
	
	/**
	 * 获取  id
	 *@return: String  编号
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  编号
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  userId
	 *@return: String  用户主键
	 */
	public String getUserId(){
		return this.userId;
	}

	/**
	 * 设置  userId
	 *@param: userId  用户主键
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	/**
	 * 获取  userName
	 *@return: String  用户姓名
	 */
	public String getUserName(){
		return this.userName;
	}

	/**
	 * 设置  userName
	 *@param: userName  用户姓名
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	/**
	 * 获取  organizationId
	 *@return: String  部门主键
	 */
	public String getOrganizationId(){
		return this.organizationId;
	}

	/**
	 * 设置  organizationId
	 *@param: organizationId  部门主键
	 */
	public void setOrganizationId(String organizationId){
		this.organizationId = organizationId;
	}
	/**
	 * 获取  organizationName
	 *@return: String  默认登陆组织名称
	 */
	public String getOrganizationName(){
		return this.organizationName;
	}

	/**
	 * 设置  organizationName
	 *@param: organizationName  默认登陆组织名称
	 */
	public void setOrganizationName(String organizationName){
		this.organizationName = organizationName;
	}
	
}
