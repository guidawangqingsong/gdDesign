package cn.jjxx.modules.workflow.service.impl;

import java.util.List;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.workflow.mapper.HisTaskMapper;
import cn.jjxx.modules.workflow.entity.HisTask;
import cn.jjxx.modules.workflow.service.IHisTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 历史任务表
 * @Description: 历史任务表
 * @author jjxx
 * @date 2017-11-24 12:55:31
 * @version V1.0   
 *
 */
@Transactional
@Service("hisTaskService")
public class HisTaskServiceImpl  extends CommonServiceImpl<HisTaskMapper,HisTask> implements  IHisTaskService {

	@Override
	public Page<HisTask> selectPage(Page<HisTask> page, Wrapper<HisTask> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectHisTaskPage(page, wrapper));
		return page;
	}

	@Override
	public List<HisTask> getHisTakComment(String procInstId) {
		return baseMapper.getHisTakComment(procInstId);
	}
}
