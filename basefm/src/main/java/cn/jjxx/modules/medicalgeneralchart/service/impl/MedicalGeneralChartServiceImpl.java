package cn.jjxx.modules.medicalgeneralchart.service.impl;

import java.util.List;
import java.util.Map;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.medicalgeneralchart.mapper.MedicalGeneralChartMapper;
import cn.jjxx.modules.medicalgeneralchart.entity.MedicalGeneralChart;
import cn.jjxx.modules.medicalgeneralchart.service.IMedicalGeneralChartService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 综合分析
 * @Description: 综合分析
 * @author jjxx.wangqingsong
 * @date 2018-04-11 16:02:57
 * @version V1.0   
 *
 */
@Transactional
@Service("medicalGeneralChartService")
public class MedicalGeneralChartServiceImpl  extends CommonServiceImpl<MedicalGeneralChartMapper,MedicalGeneralChart> implements  IMedicalGeneralChartService {

	@Override
	public List<Map<String, Object>> selectHGradeJson(String orgIds) {
		// TODO Auto-generated method stub
		return baseMapper.selectHGradeJson(orgIds);
	}

	@Override
	public List<Map<String, Object>> selectLGradeJson(String orgIds) {
		// TODO Auto-generated method stub
		return baseMapper.selectLGradeJson(orgIds);
	}

	@Override
	public List<Map<String, Object>> selectHPredictJson(String orgIds) {
		// TODO Auto-generated method stub
		return baseMapper.selectHPredictJson(orgIds);
	}

	@Override
	public List<Map<String, Object>> selectLPredictJson(String orgIds) {
		// TODO Auto-generated method stub
		return baseMapper.selectLPredictJson(orgIds);
	}

}
