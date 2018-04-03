package cn.jjxx.modules.workflow.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.modules.workflow.mapper.TaskMapper;
import cn.jjxx.modules.workflow.entity.Task;
import cn.jjxx.modules.workflow.service.ITaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: act_ru_task
 * @Description: 正在运行的任务
 * @author jjxx
 * @date 2017-11-20 10:55:10
 * @version V1.0   
 *
 */
@Transactional
@Service("taskFlowService")
public class TaskServiceImpl  extends CommonServiceImpl<TaskMapper,Task> implements  ITaskService {

	@Override
	public Page<Task> selectPage(Page<Task> page, Wrapper<Task> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectTaskPage(page, wrapper));
		return page;
	}

	
}
