package cn.jjxx.modules.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.modules.activiti.controller.ActivitiFlowService;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.utils.UserUtils;

public class LoginUserListener implements TaskListener{

	/**
	 * @Fields serialVersionUID : TODO .<br>
	 */
	private static final long serialVersionUID = -2853276618697736858L;

	
	@Override
	public void notify(DelegateTask task) {
		User user = UserUtils.getUser();
		String executionId = task.getExecutionId();
		String taskDef = task.getTaskDefinitionKey();
		String assignee = SpringContextHolder.getBean(ActivitiFlowService.class).checkIsSubmited(executionId, taskDef);
		if(assignee!=null){
			task.setAssignee(assignee);
		}else{
			task.setAssignee(user.getId());
		}
	}

}
