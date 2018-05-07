package cn.jjxx.modules.ms.entity;

import org.framework.superutil.thirdparty.excel.Excel;

import cn.jjxx.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**   
 * @Title: MedicalSample
 * @Description: MedicalSample
 * @author jjxx.wangqingsong
 * @date 2018-04-04 17:59:20
 * @version V1.0   
 *
 */
@TableName("medical_sample")
@SuppressWarnings("serial")
public class MedicalSample extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
    /**挂号费*/
    @TableField(value = "register_fee")
    @Excel(name="挂号费")
	private Double registerFee;
    /**医药费*/
    @TableField(value = "medical_fee")
    @Excel(name="医药费")
	private Double medicalFee;
    /**治疗费*/
    @TableField(value = "treatment_fee")
    @Excel(name="治疗费")
	private Double treatmentFee;
    /**住院费*/
    @TableField(value = "hospital_fee")
    @Excel(name="住院费")
	private Double hospitalFee;
    /**其他*/
    @TableField(value = "other")
    @Excel(name="其他")
	private Double other;
    /**人均费用（样本）*/
    @TableField(value = "personal_fee")
    @Excel(name="人均费用（样本）")
	private Double personalFee;
	
	/**
	 * 获取  id
	 *@return: Integer  字段主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  字段主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  delFlag
	 *@return: Integer  删除标记（0：正常；1：删除）
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
	/**
	 * 获取  registerFee
	 *@return: Double  挂号费
	 */
	public Double getRegisterFee(){
		return this.registerFee;
	}

	/**
	 * 设置  registerFee
	 *@param: registerFee  挂号费
	 */
	public void setRegisterFee(Double registerFee){
		this.registerFee = registerFee;
	}
	/**
	 * 获取  medicalFee
	 *@return: Double  医药费
	 */
	public Double getMedicalFee(){
		return this.medicalFee;
	}

	/**
	 * 设置  medicalFee
	 *@param: medicalFee  医药费
	 */
	public void setMedicalFee(Double medicalFee){
		this.medicalFee = medicalFee;
	}
	/**
	 * 获取  treatmentFee
	 *@return: Double  治疗费
	 */
	public Double getTreatmentFee(){
		return this.treatmentFee;
	}

	/**
	 * 设置  treatmentFee
	 *@param: treatmentFee  治疗费
	 */
	public void setTreatmentFee(Double treatmentFee){
		this.treatmentFee = treatmentFee;
	}
	/**
	 * 获取  hospitalFee
	 *@return: Double  住院费
	 */
	public Double getHospitalFee(){
		return this.hospitalFee;
	}

	/**
	 * 设置  hospitalFee
	 *@param: hospitalFee  住院费
	 */
	public void setHospitalFee(Double hospitalFee){
		this.hospitalFee = hospitalFee;
	}
	/**
	 * 获取  other
	 *@return: Double  其他
	 */
	public Double getOther(){
		return this.other;
	}

	/**
	 * 设置  other
	 *@param: other  其他
	 */
	public void setOther(Double other){
		this.other = other;
	}
	/**
	 * 获取  personalFee
	 *@return: Double  人均费用（样本）
	 */
	public Double getPersonalFee(){
		return this.personalFee;
	}

	/**
	 * 设置  personalFee
	 *@param: personalFee  人均费用（样本）
	 */
	public void setPersonalFee(Double personalFee){
		this.personalFee = personalFee;
	}
	
}
