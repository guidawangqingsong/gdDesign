package cn.jjxx.modules.mf.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.mf.mapper.MedicalFeeInfoMapper;
import cn.jjxx.modules.mf.entity.MedicalFeeInfo;
import cn.jjxx.modules.mf.service.IMedicalFeeInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: medicalFee
 * @Description: medicalfee
 * @author jjxx
 * @date 2018-03-13 21:45:29
 * @version V1.0   
 *
 */
@Transactional
@Service("medicalFeeInfoService")
public class MedicalFeeInfoServiceImpl  extends CommonServiceImpl<MedicalFeeInfoMapper,MedicalFeeInfo> implements  IMedicalFeeInfoService {

}
