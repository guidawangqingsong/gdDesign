package cn.jjxx.modules.sys.entity;

import cn.jjxx.core.common.entity.DataEntity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**   
 * @Title: sys_staff
 * @Description: sys_staff
 * @author jjxx
 * @date 2017-11-09 18:45:00
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@TableName("sys_staff")
public class Staff extends DataEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**员工编号*/
    @TableField(value = "code")
	private String code;
    @TableField(value = "org_id")
    private String orgId;
    @TableField(value = "user_id")
    private String userId;
    /**真实姓名*/
    @TableField(value = "name")
	private String name;
    /**性别*/
    @TableField(value = "sex")
	private Integer sex;
    /**手机号*/
    @TableField(value = "moblie")
	private String moblie;
    /**电子邮箱*/
    @TableField(value = "email")
	private String email;
    /**身份证号码*/
    @TableField(value = "idcard")
	private String idcard;
    /**出生日期	*/
    @TableField(value = "birthday")
	private String birthday;
    /**职员状态*/
    @TableField(value = "status")
	private String status;
    /**职员类型*/
    @TableField(value = "type")
	private String type;
    /**办公室电话*/
    @TableField(value = "officetel")
	private String officetel;
    /**民族*/
    @TableField(value = "ethnicities")
	private String ethnicities;
    /**通信地址*/
    @TableField(value = "addr")
	private String addr;
    /**学位*/
    @TableField(value = "degree")
	private String degree;
    /**职称*/
    @TableField(value = "protitle")
	private String protitle;
    /**岗位*/
    @TableField(value = "position")
	private String position;
    /**上级岗位*/
    @TableField(value = "parent_position")
	private String parentPosition;
    /**原工作单位*/
    @TableField(value = "pre_company")
	private String preCompany;
    /**是否领导：1表示是 0表示否*/
    @TableField(value = "lead")
	private Integer lead;
    /**头像地址*/
    @TableField(value = "image_url")
	private String imageUrl;
    
	@TableField(exist = false)
	private String orgName;
	
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
	 *@return: String  员工编号
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * 设置  code
	 *@param: code  员工编号
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 获取  name
	 *@return: String  真实姓名
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  真实姓名
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  sex
	 *@return: Integer  性别
	 */
	public Integer getSex(){
		return this.sex;
	}

	/**
	 * 设置  sex
	 *@param: sex  性别
	 */
	public void setSex(Integer sex){
		this.sex = sex;
	}
	/**
	 * 获取  moblie
	 *@return: String  手机号
	 */
	public String getMoblie(){
		return this.moblie;
	}

	/**
	 * 设置  moblie
	 *@param: moblie  手机号
	 */
	public void setMoblie(String moblie){
		this.moblie = moblie;
	}
	/**
	 * 获取  email
	 *@return: String  电子邮箱
	 */
	public String getEmail(){
		return this.email;
	}

	/**
	 * 设置  email
	 *@param: email  电子邮箱
	 */
	public void setEmail(String email){
		this.email = email;
	}
	/**
	 * 获取  idcard
	 *@return: String  身份证号码
	 */
	public String getIdcard(){
		return this.idcard;
	}

	/**
	 * 设置  idcard
	 *@param: idcard  身份证号码
	 */
	public void setIdcard(String idcard){
		this.idcard = idcard;
	}
	/**
	 * 获取  birthday
	 *@return: String  出生日期	
	 */
	public String getBirthday(){
		return this.birthday;
	}

	/**
	 * 设置  birthday
	 *@param: birthday  出生日期	
	 */
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	/**
	 * 获取  status
	 *@return: String  职员状态
	 */
	public String getStatus(){
		return this.status;
	}

	/**
	 * 设置  status
	 *@param: status  职员状态
	 */
	public void setStatus(String status){
		this.status = status;
	}
	/**
	 * 获取  type
	 *@return: String  职员类型
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  职员类型
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 获取  officetel
	 *@return: String  办公室电话
	 */
	public String getOfficetel(){
		return this.officetel;
	}

	/**
	 * 设置  officetel
	 *@param: officetel  办公室电话
	 */
	public void setOfficetel(String officetel){
		this.officetel = officetel;
	}
	/**
	 * 获取  ethnicities
	 *@return: String  民族
	 */
	public String getEthnicities(){
		return this.ethnicities;
	}

	/**
	 * 设置  ethnicities
	 *@param: ethnicities  民族
	 */
	public void setEthnicities(String ethnicities){
		this.ethnicities = ethnicities;
	}
	/**
	 * 获取  addr
	 *@return: String  通信地址
	 */
	public String getAddr(){
		return this.addr;
	}

	/**
	 * 设置  addr
	 *@param: addr  通信地址
	 */
	public void setAddr(String addr){
		this.addr = addr;
	}
	/**
	 * 获取  degree
	 *@return: String  学位
	 */
	public String getDegree(){
		return this.degree;
	}

	/**
	 * 设置  degree
	 *@param: degree  学位
	 */
	public void setDegree(String degree){
		this.degree = degree;
	}
	/**
	 * 获取  protitle
	 *@return: String  职称
	 */
	public String getProtitle(){
		return this.protitle;
	}

	/**
	 * 设置  protitle
	 *@param: protitle  职称
	 */
	public void setProtitle(String protitle){
		this.protitle = protitle;
	}
	/**
	 * 获取  position
	 *@return: String  岗位
	 */
	public String getPosition(){
		return this.position;
	}

	/**
	 * 设置  position
	 *@param: position  岗位
	 */
	public void setPosition(String position){
		this.position = position;
	}
	/**
	 * 获取  parentPosition
	 *@return: String  上级岗位
	 */
	public String getParentPosition(){
		return this.parentPosition;
	}

	/**
	 * 设置  parentPosition
	 *@param: parentPosition  上级岗位
	 */
	public void setParentPosition(String parentPosition){
		this.parentPosition = parentPosition;
	}
	/**
	 * 获取  preCompany
	 *@return: String  原工作单位
	 */
	public String getPreCompany(){
		return this.preCompany;
	}

	/**
	 * 设置  preCompany
	 *@param: preCompany  原工作单位
	 */
	public void setPreCompany(String preCompany){
		this.preCompany = preCompany;
	}
	/**
	 * 获取  lead
	 *@return: Integer  是否领导：1表示是 0表示否
	 */
	public Integer getLead(){
		return this.lead;
	}

	/**
	 * 设置  lead
	 *@param: lead  是否领导：1表示是 0表示否
	 */
	public void setLead(Integer lead){
		this.lead = lead;
	}
	/**
	 * 获取  imageUrl
	 *@return: String  头像地址
	 */
	public String getImageUrl(){
		return this.imageUrl;
	}

	/**
	 * 设置  imageUrl
	 *@param: imageUrl  头像地址
	 */
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注信息
	 */
	public String getRemarks(){
		return this.remarks;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
