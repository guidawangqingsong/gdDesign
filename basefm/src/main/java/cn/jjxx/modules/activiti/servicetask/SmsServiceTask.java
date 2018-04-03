package cn.jjxx.modules.activiti.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.modules.activiti.controller.ActivitiFlowService;

/**
 * @Title: JavaAuditedServiceTask.java .<br>
 * @Package cn.jjxx.modules.activiti.servicetask .<br>
 * @Description: activiti 的java服务类，发送短信 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-12-19 上午8:58:24.<br>
 * @version V1.0.<br>
 */
public class SmsServiceTask  implements JavaDelegate{

	//流传变量-手机号，多个用逗号隔开
	private Expression phones;
	//流传变量-发送的内容
	private Expression content;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		if(phones!=null&&content!=null){
			String phone = String.valueOf(phones.getValue(execution));
			String msg = String.valueOf(content.getValue(execution));
			SpringContextHolder.getBean(ActivitiFlowService.class).sendSMS(phone, msg);
		}
	}
}
