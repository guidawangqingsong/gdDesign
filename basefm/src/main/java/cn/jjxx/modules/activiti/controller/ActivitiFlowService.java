package cn.jjxx.modules.activiti.controller;

import java.util.List;

import org.activiti.engine.history.HistoricTaskInstance;
import org.framework.customutil.sms.wnd.SMSHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jjxx.modules.activiti.service.impl.FlowService;
import cn.jjxx.modules.activiti.workflow.BillBean;
import cn.jjxx.modules.workflow.service.IFlowCommonService;

@Controller  
@Scope("prototype") 
public class ActivitiFlowService  extends FlowService{

	@Autowired
	protected IFlowCommonService flowCommonService;
	
	/**
	 * @Description: 修改单据状态 .<br>
	 * @param status 状态值 .<br>
	 * @param billBean 单据信息.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-12-19 上午8:53:23.<br>
	 */
	public void updateBillStatus(int status,BillBean billBean){
		String billId = billBean.getBillId();
		String tableName = billBean.getTableName();
		String idFeild = billBean.getIdFeild();
		String statusFeild = billBean.getStatusFeild();
		flowCommonService.updateBillStatus(billId, tableName, idFeild, statusFeild, status);
	}
	
	/**
	 * @Description: 获取历史的提交人 .<br>
	 * @param executionId 执行Id.<br>
	 * @param taskDef task定义的键.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-12-19 下午1:58:55.<br>
	 */
	public String checkIsSubmited(String executionId,String taskDef){
		List<HistoricTaskInstance> historyList = historyService.createHistoricTaskInstanceQuery()
				.executionId(executionId).taskDefinitionKey(taskDef).list();
		if(historyList!=null&&historyList.size()>0){
			return historyList.get(0).getAssignee();
		}
		return null;
	}
	
	/**
	 * @Description: 发送短信 .<br>
	 * @param phones 手机号码,用“,”隔开.<br>
	 * @param content 发送内容.<br>
	 * @return boolean 是否发送成功.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-26 上午11:27:23.<br>
	 */
	public boolean sendSMS(String phones,String content){
		new SMSHelper("gzjjxx","gzjjxx520").sendMsg(phones, content, null);
		//TODO 在本地做记录
		return false;
	}
	
	public boolean sendEmail(){
		return false;
	}
}
