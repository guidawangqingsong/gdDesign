package cn.jjxx.modules.mf.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**   
 * @Title: medicalFee
 * @Description: medicalfee
 * @author jjxx
 * @date 2018-03-13 21:45:29
 * @version V1.0   
 *
 */
@TableName("medical_fee_info")
@SuppressWarnings("serial")
public class MedicalFeeInfo extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**挂号费*/
    @TableField(value = "register_fee")
	private Double registerFee;
    /**医药费*/
    @TableField(value = "medical_fee")
	private Double medicalFee;
    /**检查费*/
    @TableField(value = "treatment_fee")
	private Double treatmentFee;
    /**住院费*/
    @TableField(value = "hospital_fee")
	private Double hospitalFee;
    /**其他费用*/
    @TableField(value = "other")
	private Double other;
    /**个人平均费用*/
    @TableField(value = "personal_fee")
	private Double personalFee;
	
	/**
	 * 获取  id
	 *@return: String  字段主键
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
	 *@return: Double  检查费
	 */
	public Double getTreatmentFee(){
		return this.treatmentFee;
	}

	/**
	 * 设置  treatmentFee
	 *@param: treatmentFee  检查费
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
	 *@return: Double  其他费用
	 */
	public Double getOther(){
		return this.other;
	}

	/**
	 * 设置  other
	 *@param: other  其他费用
	 */
	public void setOther(Double other){
		this.other = other;
	}
	/**
	 * 获取  personalFee
	 *@return: Double  个人平均费用
	 */
	public Double getPersonalFee(){
		return this.personalFee;
	}

	/**
	 * 设置  personalFee
	 *@param: personalFee  个人平均费用
	 */
	public void setPersonalFee(Double personalFee){
		this.personalFee = personalFee;
	}
	
}
