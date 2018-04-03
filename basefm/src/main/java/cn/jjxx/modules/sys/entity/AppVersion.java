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
 * @Title: App版本管理
 * @Description: App版本管理
 * @author jjxx
 * @date 2018-01-12 23:40:56
 * @version V1.0   
 *
 */
@TableName("sys_app_version")
@SuppressWarnings("serial")
public class AppVersion extends AbstractEntity<String> {

    /**id*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**编码*/
    @TableField(value = "code")
	private String code;
    /**名称*/
    @TableField(value = "name")
	private String name;
    /**使用机型*/
    @TableField(value = "type")
	private String type;
    /**版本号*/
    @TableField(value = "version")
	private String version;
    /**是否最新*/
    @TableField(value = "version_status")
	private String versionStatus;
    /**下载地址*/
    @TableField(value = "download_url")
	private String downloadUrl;
    /**二维码地址*/
    @TableField(value = "qrcode_url")
	private String qrcodeUrl;
    /**描述*/
    @TableField(value = "description")
	private String description;
    /**创建人*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**create_date*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新人*/
    @TableField(value = "update_by",el="updateBy.id",fill = FieldFill.UPDATE)
	private User updateBy;
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
    /**文件地址*/
    @TableField(value = "file_path")
    private String filePath;
	
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
	 *@return: String  编码
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * 设置  code
	 *@param: code  编码
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 获取  name
	 *@return: String  名称
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  type
	 *@return: String  使用机型
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  使用机型
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 获取  version
	 *@return: String  版本号
	 */
	public String getVersion(){
		return this.version;
	}

	/**
	 * 设置  version
	 *@param: version  版本号
	 */
	public void setVersion(String version){
		this.version = version;
	}
	/**
	 * 获取  versionStatus
	 *@return: String  是否最新
	 */
	public String getVersionStatus(){
		return this.versionStatus;
	}

	/**
	 * 设置  versionStatus
	 *@param: versionStatus  是否最新
	 */
	public void setVersionStatus(String versionStatus){
		this.versionStatus = versionStatus;
	}
	/**
	 * 获取  downloadUrl
	 *@return: String  下载地址
	 */
	public String getDownloadUrl(){
		return this.downloadUrl;
	}

	/**
	 * 设置  downloadUrl
	 *@param: downloadUrl  下载地址
	 */
	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}
	/**
	 * 获取  qrcodeUrl
	 *@return: String  二维码地址
	 */
	public String getQrcodeUrl(){
		return this.qrcodeUrl;
	}

	/**
	 * 设置  qrcodeUrl
	 *@param: qrcodeUrl  二维码地址
	 */
	public void setQrcodeUrl(String qrcodeUrl){
		this.qrcodeUrl = qrcodeUrl;
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
	 *@return: Date  create_date
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  create_date
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
