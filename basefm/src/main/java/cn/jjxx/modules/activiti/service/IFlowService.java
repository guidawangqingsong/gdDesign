package cn.jjxx.modules.activiti.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import cn.jjxx.modules.activiti.workflow.AssigneeInfo;
import cn.jjxx.modules.activiti.workflow.BillBean;
import cn.jjxx.modules.activiti.workflow.OperateBean;

import cn.jjxx.modules.activiti.workflow.history.HistoryTaskComment;

public interface IFlowService {

	/**
	 * @Description: 部署流程 .<br>
	 * @param file zip格式的文件.<br>
	 * @param filename 部署流程的名称.<br>
	 * @return boolean 返回是否部署成功.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午3:24:23.<br>
	 */
	boolean saveNewDeployeFile(File file, String filename);
	
	/**
	 * @Description: 部署流程.<br>
	 * @param in 通过输入流的方式 .<br>
	 * @param filename 部署流程的名称 .<br>
	 * @return boolean .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午3:25:14.<br>
	 */
	boolean saveNewDeployInputStream(InputStream in,String filename);
	
	
	//TODO 获取流程部署信息(分页)
	
	//TODO 获取流程部署信息(不分页)
	//TODO 获取流程定义信息(分页)
	//TODO 获取流程定义信息(不分页)
	//TODO 根据流程实例ID,获取当前节点的下一节点信息
	
	/**
	 * @Description: 提交流程，返回是否提交成功 .<br>
	 * @param billBean 单据的相关信息.<br>
	 * @param assignees 处理人信息.<br>
	 * @param isAutoSubmit 是否自动处理下一步审批（相同的人） .<br>
	 * @param billInfo 单据信息，用来走流程条件的信息.<br>
	 * @return boolean 是否提交成功 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午9:22:01.<br>
	 */
	boolean submitFlow(BillBean billBean, AssigneeInfo assignees, boolean isAutoSubmit, Map<String,Object> billInfo);
	
	/**
	 * @Description: 审批流程,判断是否审批成功 .<br>
	 * @param taskId 当前任务Id.<br>
	 * @param operate 处理人(当前登录人或其他处理人信息).<br>
	 * @param assignees 处理人信息.<br>
	 * @param isAutoHandle 是否自动处理下一步审批（相同的人） .<br>
	 * @param billInfo 单据信息，用来走流程条件的信息.<br>
	 * @return boolean .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午9:30:00.<br>
	 */
	boolean completeTask(String taskId, OperateBean operate, AssigneeInfo assignees, boolean isAutoHandle, Map<String,Object> billInfo);
	
	/**
	 * @Description: 驳回流程 .<br>
	 * @param taskId 当前任务ID .<br>
	 * @param activityId 驳回节点ID .<br>
	 * @param variables 流程存储参数 .<br>
	 * @return boolean 是否驳回成功.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午11:23:40.<br>
	 */
	boolean backProcess(String taskId, String activityId, Map<String, Object> variables);
	
	/**
	 * @Description: 根据当前任务ID，查询可以驳回的任务节点  .<br>
	 * @param @param taskId 当前任务Id.<br>
	 * @return List<ActivityImpl> 可以驳回的任务节点集合.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-20 下午5:36:14.<br>
	 */	
	List<ActivityImpl> findBackAvtivity(String taskId);
	
	/**
     * @Description: 转办流程  .<br>
     * @param taskId 当前任务节点ID .<br>
     * @param assignee 被转办人.<br>   
     * @return boolean 是否转办成功 .<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-19 下午4:06:58.<br>
     */
	boolean transferAssignee(String taskId, String assignee);
	
	/**
	 * @Description: 会签 .<br>
	 * @param @param taskId 任务Id .<br>
	 * @param @param assignees 处理人信息.<br>
	 * @return boolean 是否会签成功 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:34:22.<br>
	 */
	boolean jointProcess(String taskId, List<AssigneeInfo> assignees);
	
	/**
	 * @Description: 根据任务Id,终止流程 .<br>
	 * @param taskId 当前任务Id.<br>
	 * @return boolean 是否终止 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:31:48.<br>
	 */
	boolean endProcess(String taskId);
	
	/**
	 * @Description: 根据流程实例Id,挂起流程 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return boolean 是否挂起.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:20:04.<br>
	 */
	boolean suspendProcessInstance(String processInstanceId);
	
	/**
	 * @Description: 根据流程实例Id,激活流程 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return boolean 是否激活 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:19:37.<br>
	 */
	boolean activateProcessInstance(String processInstanceId);
	
	/**
	 * @Description: 通过任务Id,获取流程定义 .<br>
	 * @param @param taskId 任务Id.<br>
	 * @return TaskEntity task任务实体.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午5:20:51.<br>
	 */
	TaskEntity findTaskById(String taskId);
	
	/**
	 * @Description: 通过任务Id,获取流程定义实体 .<br>
	 * @param @param taskId 任务Id.<br>
	 * @return ProcessDefinitionEntity 流程定义实体.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午5:21:25.<br>
	 */
	ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId);
	
	/**
	 * @Description: 根据任务ID和节点ID获取活动节点 .<br>
	 * @param taskId 任务Id .<br>
	 * @param activityId 活动节点Id .<br>
	 * @return ActivityImpl 活动节点信息.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-20 下午5:28:00.<br>
	 */
	ActivityImpl findActivitiImpl(String taskId, String activityId);
	
	/**
	 * @Description: 根据流程实例Id和处理人，查询正在运行的任务 .<br>
	 * @param assignee 处理人 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return Task 返回代办任务信息.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午8:30:53.<br>
	 */
	Task findUserTaskByProInst(String assignee,String processInstanceId);
	
	/**
	 * @Description: 根据流程实例Id,查询流程实例 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return ProcessInstance 返回流程实例.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:09:50.<br>
	 */
	ProcessInstance findProcessInstance(String processInstanceId);
	
	/**
	 * @Description: 通过任务Id,获取流程实例 .<br>
	 * @param taskId 任务Id .<br>
	 * @return ProcessInstance 返回流程实例 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午11:11:21.<br>
	 */
	ProcessInstance findProcessInstanceByTaskId(String taskId);
	
	/**
	 * @Description: 根据流程实例ID和任务key值查询所有同级任务集合  .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @param key 任务key值 .<br>
	 * @return List<Task> 任务集合  .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午11:09:31.<br>
	 */
	List<Task> findTaskListByKey(String processInstanceId, String key);
	
	/**
	 * @Description: 根据流程实例Id,查询历史流程实例集合 .<br>
	 * @param instanceId 流程实例Id.<br>
	 * @return List<HistoricActivityInstance> 返回历史流程实例集合.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午3:15:14.<br>
	 */
	List<HistoricActivityInstance> getHistoricActivityInstances(String instanceId);
	
	/**
	 * @Description: 根据处理人，查询个人历史处理处理信息 .<br>
	 * @param assignee 处理人 .<br>
	 * @return List<HistoricTaskInstance> 历史审批任务.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午3:18:08.<br>
	 */
	List<HistoricTaskInstance> getHisTaskList(String assignee);
	
	/**
	 * @Description: 根据处理人，查询当前任务的审批记录 .<br>
	 * @param assignee 处理人 .<br>
	 * @return List<HistoricTaskInstance> 历史审批任务.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午3:18:08.<br>
	 */
	List<HistoricTaskInstance> getHisTaskByTaskId(String taskId);
	
	/**
	 * @Description: 根据单据Id,获取单据的最新流程实例 .<br>
	 * @param billId 单据Id.<br>
	 * @return HistoricProcessInstance .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-28 下午4:09:13.<br>
	 */
	HistoricProcessInstance getHisProInst(String billId);
	
	/**
	 * @Description: 根据单据Id和key值，获取历史审批信息 .<br>
	 * @param billId 单据Id .<br>
	 * @param key key值 .<br>
	 * @return List<HistoricTaskInstance> 历史任务实例 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:23:12.<br>
	 */
	List<HistoricTaskInstance> getHisTask(String billId, String key);
	
	/**
	 * @Description: 通过任务Id,获取批注信息 .<br>
	 * @param taskId 当前任务Id.<br>
	 * @return List<Comment> 返回审批批注.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:18:55.<br>
	 */
	List<Comment> findCommentByTaskId(String taskId);
	
	/**
	 * @Description: 通过单据Id和key值，获取批注信息 .<br>
	 * @param billId 单据Id .<br>
	 * @param key key值 .<br>
	 * @return List<Comment> 返回审批批注 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:20:03.<br>
	 */
	List<Comment> findCommentByBillId(String billId, String key);
	
	/**
	 * @Description: 获取单列流程实例 .<br>
	 * @param instanceId 流程实例Id .<br>
	 * @return HistoricProcessInstance 返回历史流程实例 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:21:33.<br>
	 */
	HistoricProcessInstance getHistoricProcessInstance(String instanceId);
	
	/**
	 * @Description: 根据当前任务Id,获取历史审批信息及批注信息 .<br>
	 * @param taskId  当前任务Id .<br>
	 * @return List<HistoryTaskComment> 历史任务审批及批注.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午10:38:04 .<br>
	 */
	List<HistoryTaskComment> findTaskComment(String taskId);
	
	/**
	 * @Description: 通过单据Id和key，查询单据的审批任务 .<br>
	 * @param billId 单据Id.<br>
	 * @param key 启动流程的key值.<br>
	 * @return List<HistoryTaskComment> 历史审批意见.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-28 下午4:20:49.<br>
	 */
	List<HistoryTaskComment> findTaskComment(String billId, String key);
	
	//TODO 跳转
	boolean jumpTask(String taskId, String targetTaskDefinitionKey, Map<String,Object> variables);
	
	
	InputStream getFlowChart(String taskId);
}
