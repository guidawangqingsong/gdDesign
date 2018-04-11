package cn.jjxx.modules.medicalgeneralchart.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jjxx.modules.medicalgeneralchart.entity.MedicalGeneralChart;
 
/**   
 * @Title: 综合分析数据库控制层接口
 * @Description: 综合分析数据库控制层接口
 * @author jjxx.wangqingsong
 * @date 2018-04-11 16:02:57
 * @version V1.0   
 *
 */
public interface MedicalGeneralChartMapper extends BaseMapper<MedicalGeneralChart> {
	List<Map<String,Object>> selectHGradeJson(@Param("orgIds") String orgIds);
	
	List<Map<String,Object>> selectLGradeJson(@Param("orgIds")String orgIds);
	
	List<Map<String,Object>> selectHPredictJson(@Param("orgIds")String orgIds);
	
	List<Map<String, Object>> selectLPredictJson(@Param("orgIds")String orgIds);
}