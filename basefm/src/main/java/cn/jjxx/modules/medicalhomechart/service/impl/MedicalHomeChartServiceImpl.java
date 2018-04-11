package cn.jjxx.modules.medicalhomechart.service.impl;

import java.util.List;
import java.util.Map;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.medicalhomechart.mapper.MedicalHomeChartMapper;
import cn.jjxx.modules.medicalhomechart.entity.MedicalHomeChart;
import cn.jjxx.modules.medicalhomechart.service.IMedicalHomeChartService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 医用首页图表
 * @Description: 医用首页图表
 * @author jjxx.wangqingsong
 * @date 2018-04-11 10:46:11
 * @version V1.0   
 *
 */
@Transactional
@Service("medicalHomeChartService")
public class MedicalHomeChartServiceImpl  extends CommonServiceImpl<MedicalHomeChartMapper,MedicalHomeChart> implements  IMedicalHomeChartService {

	@Override
	public List<Map<String, Object>> findHomePieJson(String orgIds) {
		// TODO Auto-generated method stub
		return baseMapper.findHomePieJson(orgIds);
	}

	@Override
	public List<Map<String, Object>> findHomeBarJson(String orgIds) {
		// TODO Auto-generated method stub
		return baseMapper.findHomeBarJson(orgIds);
	}

	@Override
	public List<Map<String, Object>> findHomeMaxJson(String orgIds) {
		// TODO Auto-generated method stub
		return baseMapper.findHomeMaxJson(orgIds);
	}

}
