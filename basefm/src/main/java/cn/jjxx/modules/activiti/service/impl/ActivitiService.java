/*------------------------------------------------------------------------------
 *    系统名称	： jxdy
 *    文件名	： ActivitiService.java
 *              (©) 贵州交建信息科技有限公司 2016 All Rights Reserved.
 *
 *    注意： 本内容仅限于贵州交建信息科技有限公司内部使用，禁止转发
 *-----------------------------------------------------------------------------*/
package cn.jjxx.modules.activiti.service.impl;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import cn.jjxx.core.common.controller.BaseController;
import cn.jjxx.modules.activiti.service.IActivitiService;

/**
 * 创建日期:2016年10月26日 上午10:01:28
 * @author lgl
 */
public class ActivitiService extends BaseController implements IActivitiService{
	public static RepositoryService repositoryService;
	public static RuntimeService runtimeService;
	public static FormService formService;
	public static IdentityService identityService;
	public static TaskService taskService;
	public static HistoryService historyService;
	public static ManagementService managementService;
	public static FlowService flowService;

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	public void setRepositoryService(RepositoryService repositoryService) {
		ActivitiService.repositoryService = repositoryService;
	}
	public RuntimeService getRuntimeService() {
		return runtimeService;
	}
	public void setRuntimeService(RuntimeService runtimeService) {
		ActivitiService.runtimeService = runtimeService;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		ActivitiService.formService = formService;
	}
	public IdentityService getIdentityService() {
		return identityService;
	}
	public void setIdentityService(IdentityService identityService) {
		ActivitiService.identityService = identityService;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		ActivitiService.taskService = taskService;
	}
	public HistoryService getHistoryService() {
		return historyService;
	}
	public void setHistoryService(HistoryService historyService) {
		ActivitiService.historyService = historyService;
	}
	public ManagementService getManagementService() {
		return managementService;
	}
	public void setManagementService(ManagementService managementService) {
		ActivitiService.managementService = managementService;
	}
	public FlowService getFlowService() {
		return flowService;
	}
	public void setFlowService(FlowService flowService) {
		ActivitiService.flowService = flowService;
	}
	
}
