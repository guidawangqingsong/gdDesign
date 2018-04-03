/*------------------------------------------------------------------------------
 *    系统名称	： jxdy
 *    文件名	： IActivitiService.java
 *              (©) 贵州交建信息科技有限公司 2016 All Rights Reserved.
 *
 *    注意： 本内容仅限于贵州交建信息科技有限公司内部使用，禁止转发
 *-----------------------------------------------------------------------------*/
package cn.jjxx.modules.activiti.service;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

/**
 * activiti服务封装包
 * 创建日期:2016年10月26日 上午11:28:54
 * @author lgl
 */
public interface IActivitiService {
	public RepositoryService getRepositoryService();
	public RuntimeService getRuntimeService();
	public FormService getFormService();
	public IdentityService getIdentityService();
	public TaskService getTaskService();
	public HistoryService getHistoryService();
	public ManagementService getManagementService();
}
