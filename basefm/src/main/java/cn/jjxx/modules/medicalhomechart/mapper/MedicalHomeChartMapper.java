package cn.jjxx.modules.medicalhomechart.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jjxx.modules.medicalhomechart.entity.MedicalHomeChart;
 
/**   
 * @Title: 医用首页图表数据库控制层接口
 * @Description: 医用首页图表数据库控制层接口
 * @author jjxx.wangqingsong
 * @date 2018-04-11 10:46:11
 * @version V1.0   
 *
 */
public interface MedicalHomeChartMapper extends BaseMapper<MedicalHomeChart> {
    List<Map<String,Object>> findHomePieJson(@Param("orgIds") String orgIds);
	
	List<Map<String,Object>> findHomeBarJson(@Param("orgIds")String orgIds);
	
	List<Map<String,Object>> findHomeMaxJson(@Param("orgIds")String orgIds);
}