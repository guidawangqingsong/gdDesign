package cn.jjxx.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jjxx.core.common.mapper.BaseTreeMapper;
import cn.jjxx.modules.sys.entity.Region;
 
/**   
 * @Title: 区域管理数据库控制层接口
 * @Description: 区域管理数据库控制层接口
 * @author jjxx
 * @date 2018-01-06 21:52:45
 * @version V1.0   
 *
 */
public interface RegionMapper extends BaseTreeMapper<Region> {
    
	/**
	 * @Description: 级联查询初始化 .<br>
	 * @param pId 上级Id .<br>
	 * @return List<Region> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-7 上午12:25:53 .<br>
	 */
	List<Region> initSelect(@Param("pId")String pId);
}