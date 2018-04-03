package cn.jjxx.modules.sys.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jjxx.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 接口列表
 * @Description: 接口列表
 * @author jjxx
 * @date 2018-01-13 00:29:11
 * @version V1.0   
 *
 */
@TableName("sys_interface")
@SuppressWarnings("serial")
public class Infc extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**接口编号*/
    @TableField(value = "code")
	private String code;
    /**接口名称*/
    @TableField(value = "name")
	private String name;
    /**请求类型*/
    @TableField(value = "req_type")
	private String reqType;
    /**操作类型*/
    @TableField(value = "oper_type")
	private String operType;
    /**使用设备*/
    @TableField(value = "use_eqp")
	private String useEqp;
    /**支持接口类型*/
    @TableField(value = "support_type")
	private String supportType;
    /**接口*/
    @TableField(value = "interface_url")
	private String interfaceUrl;
    /**定义sql*/
    @TableField(value = "defined_sql")
	private String definedSql;
    /**请求body*/
    @TableField(value = "req_body")
	private String reqBody;
    /**成功时返回消息*/
    @TableField(value = "suc_msg")
	private String sucMsg;
    /**失败时返回消息*/
    @TableField(value = "fail_msg")
	private String failMsg;
    /**备注*/
    @TableField(value = "remark")
	private String remark;
    /**状态*/
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
    /**（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
	
	/**
	 * 获取  id
	 *@return: String  主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  code
	 *@return: String  接口编号
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * 设置  code
	 *@param: code  接口编号
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 获取  name
	 *@return: String  接口名称
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  接口名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  reqType
	 *@return: String  请求类型
	 */
	public String getReqType(){
		return this.reqType;
	}

	/**
	 * 设置  reqType
	 *@param: reqType  请求类型
	 */
	public void setReqType(String reqType){
		this.reqType = reqType;
	}
	/**
	 * 获取  operType
	 *@return: String  操作类型
	 */
	public String getOperType(){
		return this.operType;
	}

	/**
	 * 设置  operType
	 *@param: operType  操作类型
	 */
	public void setOperType(String operType){
		this.operType = operType;
	}
	/**
	 * 获取  useEqp
	 *@return: String  使用设备
	 */
	public String getUseEqp(){
		return this.useEqp;
	}

	/**
	 * 设置  useEqp
	 *@param: useEqp  使用设备
	 */
	public void setUseEqp(String useEqp){
		this.useEqp = useEqp;
	}
	/**
	 * 获取  supportType
	 *@return: String  支持接口类型
	 */
	public String getSupportType(){
		return this.supportType;
	}

	/**
	 * 设置  supportType
	 *@param: supportType  支持接口类型
	 */
	public void setSupportType(String supportType){
		this.supportType = supportType;
	}
	/**
	 * 获取  interfaceUrl
	 *@return: String  接口
	 */
	public String getInterfaceUrl(){
		return this.interfaceUrl;
	}

	/**
	 * 设置  interfaceUrl
	 *@param: interfaceUrl  接口
	 */
	public void setInterfaceUrl(String interfaceUrl){
		this.interfaceUrl = interfaceUrl;
	}
	/**
	 * 获取  definedSql
	 *@return: String  定义sql
	 */
	public String getDefinedSql(){
		return this.definedSql;
	}

	/**
	 * 设置  definedSql
	 *@param: definedSql  定义sql
	 */
	public void setDefinedSql(String definedSql){
		this.definedSql = definedSql;
	}
	/**
	 * 获取  reqBody
	 *@return: String  请求body
	 */
	public String getReqBody(){
		return this.reqBody;
	}

	/**
	 * 设置  reqBody
	 *@param: reqBody  请求body
	 */
	public void setReqBody(String reqBody){
		this.reqBody = reqBody;
	}
	/**
	 * 获取  sucMsg
	 *@return: String  成功时返回消息
	 */
	public String getSucMsg(){
		return this.sucMsg;
	}

	/**
	 * 设置  sucMsg
	 *@param: sucMsg  成功时返回消息
	 */
	public void setSucMsg(String sucMsg){
		this.sucMsg = sucMsg;
	}
	/**
	 * 获取  failMsg
	 *@return: String  失败时返回消息
	 */
	public String getFailMsg(){
		return this.failMsg;
	}

	/**
	 * 设置  failMsg
	 *@param: failMsg  失败时返回消息
	 */
	public void setFailMsg(String failMsg){
		this.failMsg = failMsg;
	}
	/**
	 * 获取  remark
	 *@return: String  备注
	 */
	public String getRemark(){
		return this.remark;
	}

	/**
	 * 设置  remark
	 *@param: remark  备注
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	 * 获取  status
	 *@return: Integer  状态
	 */
	public Integer getStatus(){
		return this.status;
	}

	/**
	 * 设置  status
	 *@param: status  状态
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
	/**
	 * 获取  delFlag
	 *@return: String  （0：正常；1：删除）
	 */
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  （0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}
	
}
