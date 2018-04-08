package cn.jjxx.modules.medicalinfo.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.medicalinfo.mapper.MainMedicalInfoMapper;
import cn.jjxx.modules.medicalinfo.entity.MainMedicalInfo;
import cn.jjxx.modules.medicalinfo.service.IMainMedicalInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 医疗费用总表
 * @Description: 医疗费用总表
 * @author jjxx
 * @date 2018-04-08 14:33:15
 * @version V1.0   
 *
 */
@Transactional
@Service("mainMedicalInfoService")
public class MainMedicalInfoServiceImpl  extends CommonServiceImpl<MainMedicalInfoMapper,MainMedicalInfo> implements  IMainMedicalInfoService {

}
