package cn.jjxx.modules.sys.service;

import java.util.List;

import cn.jjxx.core.common.service.ITreeCommonService;
import cn.jjxx.modules.sys.entity.Region;

/**   
 * @Title: 区域管理
 * @Description: 区域管理
 * @author jjxx
 * @date 2018-01-06 21:52:45
 * @version V1.0   
 *
 */
public interface IRegionService extends ITreeCommonService<Region,String>{

	/**
	 * @Description: 级联查询初始化 .<br>
	 * @param pId 上级Id .<br>
	 * @return List<Region> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-7 上午12:25:53 .<br>
	 */
	List<Region> initSelect(String pId);
}

