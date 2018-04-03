package cn.jjxx.modules.workflow.service.impl;

import java.util.List;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.utils.UserUtils;
import cn.jjxx.modules.workflow.mapper.ActHiProcinstMapper;
import cn.jjxx.modules.workflow.entity.ActHiProcinst;
import cn.jjxx.modules.workflow.service.IActHiProcinstService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 流程实例
 * @Description: 流程实例
 * @author jjxx
 * @date 2017-12-22 14:55:58
 * @version V1.0   
 *
 */
@Transactional
@Service("actHiProcinstService")
public class ActHiProcinstServiceImpl  extends CommonServiceImpl<ActHiProcinstMapper,ActHiProcinst> implements  IActHiProcinstService {

	@Override
	public Page<ActHiProcinst> selectPage(Page<ActHiProcinst> page,
			Wrapper<ActHiProcinst> wrapper) {
		wrapper.eq("1", "1");
		wrapper.eq("startUserId", UserUtils.getUser().getId());
		List<ActHiProcinst> list = baseMapper.selectProcinstPage(page, wrapper);
		page.setRecords(list);
		return page;
	}
}
