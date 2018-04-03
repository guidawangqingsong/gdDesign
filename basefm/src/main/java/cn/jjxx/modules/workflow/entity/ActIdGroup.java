package cn.jjxx.modules.workflow.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**   
 * @Title: act_id_group
 * @Description: 工作流分组
 * @author jjxx
 * @date 2017-12-14 19:51:10
 * @version V1.0   
 *
 */
@TableName("act_id_group")
@SuppressWarnings("serial")
public class ActIdGroup extends AbstractEntity<String> {

    /**ID_*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**REV_*/
    @TableField(value = "REV_")
	private Integer rev;
    /**NAME_*/
    @TableField(value = "NAME_")
	private String name;
    /**TYPE_*/
    @TableField(value = "TYPE_")
	private String type;
	
	/**
	 * 获取  id
	 *@return: String  ID_
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  ID_
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  rev
	 *@return: Integer  REV_
	 */
	public Integer getRev(){
		return this.rev;
	}

	/**
	 * 设置  rev
	 *@param: rev  REV_
	 */
	public void setRev(Integer rev){
		this.rev = rev;
	}
	/**
	 * 获取  name
	 *@return: String  NAME_
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  NAME_
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  type
	 *@return: String  TYPE_
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  TYPE_
	 */
	public void setType(String type){
		this.type = type;
	}
	
}
