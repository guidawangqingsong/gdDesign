package cn.jjxx.modules.activiti.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.modules.activiti.config.Contacts;
import cn.jjxx.modules.activiti.controller.ActivitiFlowService;
import cn.jjxx.modules.activiti.workflow.BillBean;
import cn.jjxx.modules.activiti.workflow.Config;

/**
 * @Title: JavaAuditedServiceTask.java .<br>
 * @Package cn.jjxx.modules.activiti.servicetask .<br>
 * @Description: activiti 的java服务类，审批通过，设置审批状态 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-12-19 上午8:58:24.<br>
 * @version V1.0.<br>
 */
public class JavaAuditedServiceTask implements JavaDelegate{
	
	//流传变量
	private Expression status;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Integer state = Contacts.AUDITED;
		if(status!=null){
			Object obj = status.getValue(execution);
			state = Integer.valueOf(obj.toString());
		}
		BillBean billBean = execution.getVariable(Config.variable.表单信息.toString(), BillBean.class);
		SpringContextHolder.getBean(ActivitiFlowService.class).updateBillStatus(state, billBean);
	}

}
