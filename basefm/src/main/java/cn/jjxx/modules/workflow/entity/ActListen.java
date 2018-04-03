package cn.jjxx.modules.workflow.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jjxx.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 流程监听
 * @Description: 流程监听
 * @author jjxx
 * @date 2018-01-13 00:53:48
 * @version V1.0   
 *
 */
@TableName("act_user_listen")
@SuppressWarnings("serial")
public class ActListen extends AbstractEntity<String> {

    /**id*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**code*/
    @TableField(value = "code")
	private String code;
    /**name*/
    @TableField(value = "name")
	private String name;
    /**监听类型*/
    @TableField(value = "listen_type")
	private String listenType;
    /**事件类型*/
    @TableField(value = "event_type")
	private String eventType;
    /**值类型*/
    @TableField(value = "val_type")
	private String valType;
    /**类路径/表达式*/
    @TableField(value = "listen_value")
	private String listenValue;
    /**描述*/
    @TableField(value = "description")
	private String description;
    /**状态（0：启用；1：禁用）*/
    @TableField(value = "status")
	private Integer status;
    /**创建人*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新人*/
    @TableField(value = "update_by",el="updateBy.id",fill = FieldFill.UPDATE)
	private User updateBy;
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
	private Date updateDate;
	
	/**
	 * 获取  id
	 *@return: String  id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  code
	 *@return: String  code
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * 设置  code
	 *@param: code  code
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 获取  name
	 *@return: String  name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  listenType
	 *@return: String  监听类型
	 */
	public String getListenType(){
		return this.listenType;
	}

	/**
	 * 设置  listenType
	 *@param: listenType  监听类型
	 */
	public void setListenType(String listenType){
		this.listenType = listenType;
	}
	/**
	 * 获取  eventType
	 *@return: String  事件类型
	 */
	public String getEventType(){
		return this.eventType;
	}

	/**
	 * 设置  eventType
	 *@param: eventType  事件类型
	 */
	public void setEventType(String eventType){
		this.eventType = eventType;
	}
	/**
	 * 获取  valType
	 *@return: String  值类型
	 */
	public String getValType(){
		return this.valType;
	}

	/**
	 * 设置  valType
	 *@param: valType  值类型
	 */
	public void setValType(String valType){
		this.valType = valType;
	}
	/**
	 * 获取  listenValue
	 *@return: String  类路径/表达式
	 */
	public String getListenValue(){
		return this.listenValue;
	}

	/**
	 * 设置  listenValue
	 *@param: listenValue  类路径/表达式
	 */
	public void setListenValue(String listenValue){
		this.listenValue = listenValue;
	}
	/**
	 * 获取  description
	 *@return: String  描述
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * 设置  description
	 *@param: description  描述
	 */
	public void setDescription(String description){
		this.description = description;
	}
	/**
	 * 获取  status
	 *@return: Integer  状态（0：启用；1：禁用）
	 */
	public Integer getStatus(){
		return this.status;
	}

	/**
	 * 设置  status
	 *@param: status  状态（0：启用；1：禁用）
	 */
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	 * 获取  createBy
	 *@return: User  创建人
	 */
	public User getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建人
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
	 *@return: User  更新人
	 */
	public User getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新人
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
	
}
