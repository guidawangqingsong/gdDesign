package cn.jjxx.modules.sys.service.impl;

import java.util.List;

import cn.jjxx.core.common.service.impl.TreeCommonServiceImpl;
import cn.jjxx.modules.sys.mapper.RegionMapper;
import cn.jjxx.modules.sys.entity.Region;
import cn.jjxx.modules.sys.service.IRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 区域管理
 * @Description: 区域管理
 * @author jjxx
 * @date 2018-01-06 21:52:45
 * @version V1.0   
 *
 */
@Transactional
@Service("regionService")
public class RegionServiceImpl  extends TreeCommonServiceImpl<RegionMapper,Region,String> implements  IRegionService {

	@Override
	public List<Region> initSelect(String pId) {
		return baseMapper.initSelect(pId);
	}

}
