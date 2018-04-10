package cn.jjxx.modules.medicalevaluation.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.medicalevaluation.mapper.MedicalEvaluationMapper;
import cn.jjxx.modules.medicalevaluation.entity.MedicalEvaluation;
import cn.jjxx.modules.medicalevaluation.service.IMedicalEvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 医用系统民主测评
 * @Description: 医用系统民主测评
 * @author jjxx.wangqingosng
 * @date 2018-04-10 16:40:53
 * @version V1.0   
 *
 */
@Transactional
@Service("medicalEvaluationService")
public class MedicalEvaluationServiceImpl  extends CommonServiceImpl<MedicalEvaluationMapper,MedicalEvaluation> implements  IMedicalEvaluationService {

}
