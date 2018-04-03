package cn.jjxx.modules.workflow.service;

import java.util.List;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.workflow.entity.HisTask;

/**   
 * @Title: 历史任务表
 * @Description: 历史任务表
 * @author jjxx
 * @date 2017-11-24 12:55:31
 * @version V1.0   
 *
 */
public interface IHisTaskService extends ICommonService<HisTask> {

	List<HisTask> getHisTakComment(String procInstId);
}

