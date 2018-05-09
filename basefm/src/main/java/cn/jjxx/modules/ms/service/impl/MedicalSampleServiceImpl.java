package cn.jjxx.modules.ms.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.ms.mapper.MedicalSampleMapper;
import cn.jjxx.modules.ms.entity.MedicalSample;
import cn.jjxx.modules.ms.service.IMedicalSampleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 样本信息
 * @Description: 样本信息
 * @author jjxx.wangqingsong
 * @date 2018-05-09 21:29:54
 * @version V1.0   
 *
 */
@Transactional
@Service("medicalSampleService")
public class MedicalSampleServiceImpl  extends CommonServiceImpl<MedicalSampleMapper,MedicalSample> implements  IMedicalSampleService {

}
