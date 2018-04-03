package cn.jjxx.modules.sys.entity;

import cn.jjxx.core.common.entity.TreeEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;


/**   
 * @Title: 区域管理
 * @Description: 区域管理
 * @author jjxx
 * @date 2018-01-06 21:52:45
 * @version V1.0   
 *
 */
@TableName("sys_region")
@SuppressWarnings("serial")
public class Region extends TreeEntity<Region> {
	
    /**区域编码*/
	@TableField(value = "code")
	private String code;
    /**等级*/
	@TableField(value = "region_level")
	private Integer regionLevel;
    /**排序*/
	@TableField(value = "region_order")
	private Integer regionOrder;
    /**英文名*/
	@TableField(value = "region_name_en")
	private String regionNameEn;
    /**简称*/
	@TableField(value = "region_Sshortname_en")
	private String regionSshortnameEn;
	/**上级*/
	@TableField(exist = false)
	private String parentName;
	
	/**
	 * 获取  code
	 *@return: String  区域编码
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * 设置  code
	 *@param: code  区域编码
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 获取  regionLevel
	 *@return: Integer  等级
	 */
	public Integer getRegionLevel(){
		return this.regionLevel;
	}

	/**
	 * 设置  regionLevel
	 *@param: regionLevel  等级
	 */
	public void setRegionLevel(Integer regionLevel){
		this.regionLevel = regionLevel;
	}
	/**
	 * 获取  regionOrder
	 *@return: Integer  排序
	 */
	public Integer getRegionOrder(){
		return this.regionOrder;
	}

	/**
	 * 设置  regionOrder
	 *@param: regionOrder  排序
	 */
	public void setRegionOrder(Integer regionOrder){
		this.regionOrder = regionOrder;
	}
	/**
	 * 获取  regionNameEn
	 *@return: String  英文名
	 */
	public String getRegionNameEn(){
		return this.regionNameEn;
	}

	/**
	 * 设置  regionNameEn
	 *@param: regionNameEn  英文名
	 */
	public void setRegionNameEn(String regionNameEn){
		this.regionNameEn = regionNameEn;
	}
	/**
	 * 获取  regionSshortnameEn
	 *@return: String  简称
	 */
	public String getRegionSshortnameEn(){
		return this.regionSshortnameEn;
	}

	/**
	 * 设置  regionSshortnameEn
	 *@param: regionSshortnameEn  简称
	 */
	public void setRegionSshortnameEn(String regionSshortnameEn){
		this.regionSshortnameEn = regionSshortnameEn;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	

}