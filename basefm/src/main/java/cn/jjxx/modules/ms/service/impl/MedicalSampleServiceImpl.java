package cn.jjxx.modules.ms.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.ms.mapper.MedicalSampleMapper;
import cn.jjxx.modules.ms.entity.MedicalSample;
import cn.jjxx.modules.ms.service.IMedicalSampleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: MedicalSample
 * @Description: MedicalSample
 * @author jjxx.wangqingsong
 * @date 2018-04-04 17:59:20
 * @version V1.0   
 *
 */
@Transactional
@Service("medicalSampleService")
public class MedicalSampleServiceImpl  extends CommonServiceImpl<MedicalSampleMapper,MedicalSample> implements  IMedicalSampleService {

}
