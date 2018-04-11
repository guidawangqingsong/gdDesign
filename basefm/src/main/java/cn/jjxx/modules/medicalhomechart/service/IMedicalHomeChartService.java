package cn.jjxx.modules.medicalhomechart.service;

import java.util.List;
import java.util.Map;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.medicalhomechart.entity.MedicalHomeChart;

/**   
 * @Title: 医用首页图表
 * @Description: 医用首页图表
 * @author jjxx.wangqingsong
 * @date 2018-04-11 10:46:11
 * @version V1.0   
 *
 */
public interface IMedicalHomeChartService extends ICommonService<MedicalHomeChart> {
	
	List<Map<String,Object>> findHomePieJson(String orgIds);
	
	List<Map<String,Object>> findHomeBarJson(String orgIds);
	
	List<Map<String,Object>> findHomeMaxJson(String orgIds);
}

