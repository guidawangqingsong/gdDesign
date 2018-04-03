package cn.jjxx.modules.workflow.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.workflow.mapper.ActIdGroupMapper;
import cn.jjxx.modules.workflow.entity.ActIdGroup;
import cn.jjxx.modules.workflow.service.IActIdGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: act_id_group
 * @Description: 工作流分组
 * @author jjxx
 * @date 2017-12-14 19:51:10
 * @version V1.0   
 *
 */
@Transactional
@Service("actIdGroupService")
public class ActIdGroupServiceImpl  extends CommonServiceImpl<ActIdGroupMapper,ActIdGroup> implements  IActIdGroupService {

	@Override
	public Page<ActIdGroup> selectPage(Page<ActIdGroup> page,
			Wrapper<ActIdGroup> wrapper) {
		wrapper.eq("1", 1);
		page.setRecords(baseMapper.selectGroupPage(page, wrapper));
		return page;
	}
	
}
