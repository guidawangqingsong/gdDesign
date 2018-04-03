package cn.jjxx.modules.activiti.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jjxx.modules.activiti.service.IFlowService;
import cn.jjxx.modules.activiti.workflow.AssigneeInfo;
import cn.jjxx.modules.activiti.workflow.BillBean;
import cn.jjxx.modules.activiti.workflow.Config;
import cn.jjxx.modules.activiti.workflow.OperateBean;
import cn.jjxx.modules.activiti.workflow.history.HistoryTaskComment;

public class FlowService extends ActivitiService implements IFlowService{

	@Autowired
	   ProcessEngineConfiguration processEngineConfiguration;
	   @Autowired
	   ProcessEngineFactoryBean processEngine;
	
	/**
	 * @Description: 部署流程 .<br>
	 * @param file zip格式的文件.<br>
	 * @param filename 部署流程的名称.<br>
	 * @return boolean 返回是否部署成功.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午3:24:23.<br>
	 */
	@Override
	public boolean saveNewDeployeFile(File file, String filename) {
		try {
			//2：将File类型的文件转化成ZipInputStream流
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
			//创建部署对象
			DeploymentBuilder builder = repositoryService.createDeployment();
			//添加部署名称和部署文件，并完成部署
			builder.name(filename).addZipInputStream(zipInputStream).deploy();
			return true;
		} catch (Exception e) {			
			return false;
		}		
	}
	
	
	/**
	 * @Description: 部署流程.<br>
	 * @param in 通过输入流的方式 .<br>
	 * @param filename 部署流程的名称 .<br>
	 * @return boolean .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午3:25:14.<br>
	 */
	@Override
	public boolean saveNewDeployInputStream(InputStream in,String filename){
		try {			
			//2：将File类型的文件转化成ZipInputStream流
			ZipInputStream zipInputStream = new ZipInputStream(in);
			//创建部署对象
			DeploymentBuilder builder = repositoryService.createDeployment();
			//添加部署名称和部署文件，并完成部署
			builder.name(filename).addZipInputStream(zipInputStream).deploy();
			return true;
		} catch (Exception e) {			
			return false;
		}
	}
	
	/**
	 * @Description: 提交流程，返回是否提交成功 .<br>
	 * @param billBean 单据的相关信息.<br>
	 * @param assignees 处理人信息.<br>
	 * @param isAutoSubmit 是否自动处理下一步审批 .<br>
	 * @return boolean 是否提交成功 .<br> 
	 * @param billInfo 单据信息，用来走流程条件的信息.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午9:22:01.<br>
	 */
	@Override
	public boolean submitFlow(BillBean billBean, AssigneeInfo assignees, boolean isAutoHandle, Map<String,Object> billInfo) {
		try {
			Map<String,Object> variable = new HashMap<String, Object>();
			String submitUser = assignees.getAssigneeId();
			variable.put(Config.constant.submitUser.toString(), submitUser);
			identityService.setAuthenticatedUserId(submitUser);
			ProcessInstance pi = runtimeService.startProcessInstanceByKey(billBean.getKey(),billBean.getBillId(),variable);			
			if(pi!=null){
				String proInstId = pi.getProcessInstanceId();
				runtimeService.setVariable(proInstId, Config.variable.表单信息.toString(), billBean);
				if(isAutoHandle){
					String assignee = submitUser;
			    	while(findUserTaskByProInst(assignee, proInstId)!=null){
			    		if(billInfo==null||billInfo.size()==0){
			    			taskService.complete(findUserTaskByProInst(assignee, proInstId).getId());
			    		}else{
			    			taskService.complete(findUserTaskByProInst(assignee, proInstId).getId(),billInfo);
			    		}
			    	}
				}
		    	return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

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
	@Override
	public boolean completeTask(String taskId, OperateBean operate, AssigneeInfo assignees, 
			boolean isAutoHandle, Map<String,Object> billInfo) {
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			String executionId = task.getExecutionId();
			String proInstId = task.getProcessInstanceId();
			//根据单据信息的相关条件，判断流程的走向
			billInfo.put(Config.constant.policy.toString(), operate.getPolicy());
			billInfo.put(Config.constant.customAuditor.toString(), operate.getCustomAuditor());
			//添加审批批注
			taskService.addComment(taskId, executionId, String.valueOf(operate.getPolicy()), operate.getOpinion());
			//根据条件完成任务
			taskService.complete(taskId, billInfo);
			//是否自动处理
			if(isAutoHandle){
				String assignee = assignees.getAssigneeId();
		    	while(findUserTaskByProInst(assignee, proInstId)!=null){
		    		if(billInfo==null||billInfo.size()==0){
		    			taskService.complete(findUserTaskByProInst(assignee, proInstId).getId());
		    		}else{
		    			taskService.complete(findUserTaskByProInst(assignee, proInstId).getId(),billInfo);
		    		}
		    	}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} 
	
	/**
	 * @Description: 驳回流程 .<br>
	 * @param taskId 当前任务ID .<br>
	 * @param activityId 驳回节点ID .<br>
	 * @param variables 流程存储参数 .<br>
	 * @return boolean 是否驳回成功.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午11:23:40.<br>
	 */
	@Override
	public boolean backProcess(String taskId, String activityId, Map<String, Object> variables) {
		if (activityId==null) {  
            return false;
        } 
		try {
			//通过taskId,获取流程实例
	        ProcessInstance pi = findProcessInstanceByTaskId(taskId);
	        //通过taskId，获取任务
	        TaskEntity taskEntity = findTaskById(taskId);
	        
	        // 查询本节点发起的会签任务，并结束  
	        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId())  
	                .taskDescription(Config.activiNodeType.jointProcess.toString()).list(); 
	        //清除不是当前节点的其他会签信息
	        for(Task t:tasks){
	        	if(t.getParentTaskId()==null||!t.getParentTaskId().equals(taskId)){
	        		tasks.remove(t);
	        	}
	        }
	        for (Task task : tasks) {  
	            commitProcess(task.getId(), null, null);  
	        }  
	        // 查找所有并行任务节点，同时驳回  
	        List<Task> taskList = findTaskListByKey(pi.getId(), taskEntity.getTaskDefinitionKey());  
	        for (Task task : taskList) {  
	            commitProcess(task.getId(), variables, activityId);  
	        }  
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}  
    
    /**
	 * @Description: 会签操作 .<br>
	 * @param taskId 任务Id .<br>
	 * @param assignees 会签人账号集合 .<br>
	 * @return boolean 是否会签成功 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:34:22.<br>
	 */
    public boolean jointProcess(String taskId, List<AssigneeInfo> assignees) {  
        for (AssigneeInfo assignee : assignees) {  
            TaskEntity task = (TaskEntity) taskService.newTask("");  
            task.setAssignee(assignee.getAssigneeId());  
            task.setName(findTaskById(taskId).getName() + Config.JOIN_PROCESS);  
            task.setProcessDefinitionId(findProcessDefinitionEntityByTaskId(taskId).getId());  
            task.setProcessInstanceId(findProcessInstanceByTaskId(taskId).getId());  
            task.setParentTaskId(taskId);  
            task.setDescription(Config.activiNodeType.jointProcess.toString());  
            taskService.saveTask(task);  
        }
        return true;
    } 
    
    /**
     * @Description: 转办流程  .<br>
     * @param taskId 当前任务节点ID .<br>
     * @param assignee 被转办人.<br>   
     * @return boolean 是否转办成功 .<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-19 下午4:06:58.<br>
     */
    public boolean transferAssignee(String taskId, String assignee) {
    	try {
    		taskService.setAssignee(taskId, assignee); 
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    /**
	 * @Description: 根据当前任务ID，查询可以驳回的任务节点  .<br>
	 * @param @param taskId 当前任务Id.<br>
	 * @return List<ActivityImpl> 可以驳回的任务节点集合.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-20 下午5:36:14.<br>
	 */
	@Override
	public List<ActivityImpl> findBackAvtivity(String taskId) {
		List<ActivityImpl> rtnList = null;  
		if (isJointTask(taskId)) {// 会签任务节点，不允许驳回  
            rtnList = new ArrayList<ActivityImpl>();  
        } else { 
        	try {
        		rtnList = iteratorBackActivity(taskId, findActivitiImpl(taskId,  
                        null), new ArrayList<ActivityImpl>(),  
                        new ArrayList<ActivityImpl>());  
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
        }  
        return reverList(rtnList);  
	}
	
	/**
	 * @Description: 检测是否是会签任务 .<br>
	 * @param taskId 任务Id.<br>
	 * @return boolean 是否是会签任务.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午10:56:41.<br>
	 */
	private boolean isJointTask(String taskId){
		//TODO 检测是否是会签任务
		return false;
	}
	
	/** 
     * 反向排序list集合，便于驳回节点按顺序显示 
     *  
     * @param list 
     * @return 
     */  
    private List<ActivityImpl> reverList(List<ActivityImpl> list) {  
        List<ActivityImpl> rtnList = new ArrayList<ActivityImpl>();  
        // 由于迭代出现重复数据，排除重复  
        for (int i = list.size(); i > 0; i--) {  
            if (!rtnList.contains(list.get(i - 1)))  
                rtnList.add(list.get(i - 1));  
        }  
        return rtnList;  
    }  

	/**
	 * @Description: 根据流程实例Id,挂起流程 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return boolean 是否挂起.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:20:04.<br>
	 */
	@Override
	public boolean suspendProcessInstance(String processInstanceId) {
		try {
			runtimeService.suspendProcessInstanceById(processInstanceId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description: 根据流程实例Id,激活流程 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return boolean 是否激活 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:19:37.<br>
	 */
	@Override
	public boolean activateProcessInstance(String processInstanceId) {
		try {
			runtimeService.activateProcessInstanceById(processInstanceId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description: 根据任务Id,终止流程 .<br>
	 * @param taskId 当前任务Id.<br>
	 * @return boolean 是否终止 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午4:31:48.<br>
	 */
	@Override
	public boolean endProcess(String taskId) {
		try {
			ActivityImpl endActivity = findActivitiImpl(taskId, Config.activiNodeType.end.toString());  
	        commitProcess(taskId, null, endActivity.getId()); 
	        return true;
		} catch (Exception e) {
			return false;
		}
	} 
	
	/**
	 * @Description: 通过任务Id,获取流程定义 .<br>
	 * @param @param taskId 任务Id.<br>
	 * @return TaskEntity task任务实体.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午5:20:51.<br>
	 */
	@Override
	public TaskEntity findTaskById(String taskId) {
		try {
			TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();  
	        return task; 
		} catch (Exception e) {
			return null; 
		}
	} 
	
	/**
	 * @Description: 通过任务Id,获取流程定义实体 .<br>
	 * @param @param taskId 任务Id.<br>
	 * @return ProcessDefinitionEntity 流程定义实体.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午5:21:25.<br>
	 */
	@Override
	public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId) {  
		try {
			String processDefinitionId = findTaskById(taskId).getProcessDefinitionId();
			if(processDefinitionId!=null){
				RepositoryServiceImpl repositoryServiceImpl = (RepositoryServiceImpl) repositoryService;
				ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)repositoryServiceImpl
						.getDeployedProcessDefinition(processDefinitionId); 
				return processDefinition;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;  
		}
    }

	/**
	 * @Description: 根据任务ID和节点ID获取活动节点 .<br>
	 * @param taskId 任务Id .<br>
	 * @param activityId 活动节点Id .<br>
	 * @return ActivityImpl 活动节点信息.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-20 下午5:28:00.<br>
	 */
	@Override
	public ActivityImpl findActivitiImpl(String taskId, String activityId) {
		// 取得流程定义  
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
        // 获取当前活动节点ID  
        if (activityId==null) {  
            activityId = findTaskById(taskId).getTaskDefinitionKey();  
        }  
        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toLowerCase().equals(Config.activiNodeType.end.toString())) {  
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
                List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();  
                if (pvmTransitionList.isEmpty()) {  
                    return activityImpl;  
                }  
            }  
        }  
        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition).findActivity(activityId);  
        return activityImpl;  
	}
	
	/**
     * @Description: el表达式判断 .<br>
     * @param key 判断的key .<br>
     * @param el el表达式  .<br>
     * @param value 判断的值 .<br>
     * @return boolean .<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-20 上午11:28:11.<br>
     */
	private void commitProcess(String taskId, Map<String, Object> variables,String activityId) throws Exception {  
        if (variables == null) {  
            variables = new HashMap<String, Object>();  
        }  
        // 跳转节点为空，默认提交操作  
        if (activityId==null) {  
            taskService.complete(taskId, variables);  
        } else {// 流程转向操作  
            turnTransition(taskId, activityId, variables);  
        }  
    } 
	
	/**
	 * @Description: 流程转向操作 .<br>
	 * @param taskId 当前任务ID .<br>
	 * @param activityId 目标节点任务ID .<br>
	 * @param variables 流程变量  .<br>
	 * @throws Exception 抛出错误异常.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午10:14:03.<br>
	 */
    private void turnTransition(String taskId, String activityId,  
            Map<String, Object> variables) throws Exception {  
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
  
        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);  
  
        // 执行转向任务  
        taskService.complete(taskId, variables);  
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);  
  
        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);  
    } 
  
    /**
     * @Description: 清空指定活动节点流向  .<br>
     * @param @param activityImpl 活动节点 .<br>
     * @return List<PvmTransition> 节点流向集合 .<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-21 上午10:15:37.<br>
     */
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {  
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();  
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : pvmTransitionList) {  
            oriPvmTransitionList.add(pvmTransition);  
        }  
        pvmTransitionList.clear();  
  
        return oriPvmTransitionList;  
    }
    
    /**
     * @Description: 还原指定活动节点流向  .<br>
     * @param @param activityImpl 活动节点  .<br>
     * @param @param oriPvmTransitionList 原有节点流向集合  .<br>   
     * @author 郑成功 .<br>
     * @date 2017-9-21 上午10:16:30.<br>
     */
    private void restoreTransition(ActivityImpl activityImpl,  
            List<PvmTransition> oriPvmTransitionList) {  
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        pvmTransitionList.clear();  
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {  
            pvmTransitionList.add(pvmTransition);  
        }  
    }
    
    /**
     * @Description: el表达式判断 .<br>
     * @param key 判断的key .<br>
     * @param el el表达式  .<br>
     * @param value 判断的值 .<br>
     * @return boolean .<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-20 上午11:28:11.<br>
     */
    public boolean isCondition(String key, String el, String value) {  
        ExpressionFactory factory = new ExpressionFactoryImpl();    
        SimpleContext context = new SimpleContext();    
        context.setVariable(key, factory.createValueExpression(value, String.class));    
        ValueExpression e = factory.createValueExpression(context, el, boolean.class);    
        return (Boolean) e.getValue(context);  
    }


    /**
	 * @Description: 根据流程实例Id和处理人，查询正在运行的任务 .<br>
	 * @param assignee 处理人 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return Task 返回代办任务信息.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午8:30:53.<br>
	 */
	@Override
	public Task findUserTaskByProInst(String assignee, String processInstanceId) {
		try {
			Task task = taskService.createTaskQuery().taskAssignee(assignee)
					 .processInstanceId(processInstanceId).singleResult();
			return task;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** 
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点 
     *  
     * @param taskId 
     *            当前任务ID 
     * @param currActivity 
     *            当前活动节点 
     * @param rtnList 
     *            存储回退节点集合 
     * @param tempList 
     *            临时存储节点集合（存储一次迭代过程中的同级userTask节点） 
     * @return 回退节点集合 
     */  
    private List<ActivityImpl> iteratorBackActivity(String taskId,  
            ActivityImpl currActivity, List<ActivityImpl> rtnList,  
            List<ActivityImpl> tempList) throws Exception {  
        // 查询流程定义，生成流程树结构  
        ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);  
  
        // 当前节点的流入来源  
        List<PvmTransition> incomingTransitions = currActivity  
                .getIncomingTransitions();  
        // 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点  
        List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();  
        // 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点  
        List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();  
        // 遍历当前节点所有流入路径  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            ActivityImpl activityImpl = transitionImpl.getSource();  
            String type = (String) activityImpl.getProperty(Config.constant.type.toString());  
            /** 
             * 并行节点配置要求：<br> 
             * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束) 
             */  
            if (Config.activiNodeType.parallelGateway.toString().equals(type)) {// 并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId.lastIndexOf("_") + 1);  
                if (Config.activiNodeType.start.toString().equals(gatewayType.toLowerCase())) {// 并行起点，停止递归  
                    return rtnList;  
                } else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                    parallelGateways.add(activityImpl);  
                }  
            } else if (Config.activiNodeType.startEvent.toString().equals(type)) {// 开始节点，停止递归  
                return rtnList;  
            } else if (Config.activiNodeType.userTask.toString().equals(type)) {// 用户任务  
                tempList.add(activityImpl);  
            } else if (Config.activiNodeType.exclusiveGateway.toString().equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                currActivity = transitionImpl.getSource();  
                exclusiveGateways.add(currActivity);  
            }  
        }  
  
        /** 
         * 迭代条件分支集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : exclusiveGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
  
        /** 
         * 迭代并行集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : parallelGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
  
        /** 
         * 根据同级userTask集合，过滤最近发生的节点 
         */  
        currActivity = filterNewestActivity(processInstance, tempList);  
        if (currActivity != null) {  
            // 查询当前节点的流向是否为并行终点，并获取并行起点ID  
            String id = findParallelGatewayId(currActivity);  
            if (id==null) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点  
                rtnList.add(currActivity);  
            } else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点  
                currActivity = findActivitiImpl(taskId, id);  
            }  
  
            // 清空本次迭代临时集合  
            tempList.clear();  
            // 执行下次迭代  
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);  
        }  
        return rtnList;  
    }  
	
	/**
	 * @Description: 通过任务Id,获取流程实例 .<br>
	 * @param taskId 任务Id .<br>
	 * @return ProcessInstance 返回流程实例 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午11:11:21.<br>
	 */
	@Override
	public ProcessInstance findProcessInstanceByTaskId(String taskId) {
		try {
			String proInstId = findTaskById(taskId).getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInstId).singleResult(); 
			return processInstance;  
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @Description: 根据流入任务集合，查询最近一次的流入任务节点  .<br>
	 * @param processInstance 流程实例   .<br>
	 * @param tempList 流入任务集合  .<br>
	 * @return ActivityImpl .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午11:16:02.<br>
	 */
    private ActivityImpl filterNewestActivity(ProcessInstance processInstance,  
            List<ActivityImpl> tempList) {  
        while (tempList.size() > 0) {  
            ActivityImpl activity_1 = tempList.get(0);  
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(  
                    processInstance, activity_1.getId());  
            if (activityInstance_1 == null) {  
                tempList.remove(activity_1);  
                continue;  
            }  
  
            if (tempList.size() > 1) {  
                ActivityImpl activity_2 = tempList.get(1);  
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(  
                        processInstance, activity_2.getId());  
                if (activityInstance_2 == null) {  
                    tempList.remove(activity_2);  
                    continue;  
                }  
  
                if (activityInstance_1.getEndTime().before(  
                        activityInstance_2.getEndTime())) {  
                    tempList.remove(activity_1);  
                } else {  
                    tempList.remove(activity_2);  
                }  
            } else {  
                break;  
            }  
        }  
        if (tempList.size() > 0) {  
            return tempList.get(0);  
        }  
        return null;  
    } 
    
    /**
     * @Description: 查询指定任务节点的最新记录  .<br>
     * @param processInstance 流程实例 .<br>
     * @param activityId 活动节点Id.<br>
     * @return HistoricActivityInstance .<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-21 上午11:15:06.<br>
     */
    private HistoricActivityInstance findHistoricUserTask(ProcessInstance processInstance, String activityId) {  
        HistoricActivityInstance rtnVal = null;  
        // 查询当前流程实例审批结束的历史节点  
        List<HistoricActivityInstance> historicActivityInstances = historyService  
                .createHistoricActivityInstanceQuery().activityType(Config.activiNodeType.userTask.toString())  
                .processInstanceId(processInstance.getId()).activityId(  
                        activityId).finished()  
                .orderByHistoricActivityInstanceEndTime().desc().list();  
        if (historicActivityInstances.size() > 0) {  
            rtnVal = historicActivityInstances.get(0);  
        }  
  
        return rtnVal;  
    } 
    
    /**
     * @Description: 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID .<br>
     * @param @param activityImpl 当前节点  .<br>
     * @return String 拼装并行起点Id.<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-21 上午11:13:58.<br>
     */
    private String findParallelGatewayId(ActivityImpl activityImpl) {  
        List<PvmTransition> incomingTransitions = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            activityImpl = transitionImpl.getDestination();  
            String type = (String) activityImpl.getProperty(Config.constant.type.toString());  
            if (Config.activiNodeType.parallelGateway.toString().equals(type)) {// 并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId  
                        .lastIndexOf(Config.UNDERLINE) + 1);  
                if (Config.activiNodeType.end.toString().equals(gatewayType.toLowerCase())) {  
                    return gatewayId.substring(0, gatewayId.lastIndexOf(Config.UNDERLINE))  
                            + Config.UNDERLINE+Config.activiNodeType.start.toString();  
                }  
            }  
        }  
        return null;  
    }

    /**
	 * @Description: 根据流程实例ID和任务key值查询所有同级任务集合  .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @param key 任务key值 .<br>
	 * @return List<Task> 任务集合  .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 上午11:09:31.<br>
	 */
	@Override
	public List<Task> findTaskListByKey(String processInstanceId, String key) {
		return taskService.createTaskQuery().processInstanceId(  
                processInstanceId).taskDefinitionKey(key).list(); 
	}
	
	public static String getNextNode(String procInstanceId){
        // 1、首先是根据流程ID获取当前任务：
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstanceId).list();
        String nextId = "";
        for (Task task : tasks) {
            // 2、然后根据当前任务获取当前流程的流程定义，然后根据流程定义获得所有的节点：
            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(task.getProcessDefinitionId());
            List<ActivityImpl> activitiList = def.getActivities(); // rs是指RepositoryService的实例
            // 3、根据任务获取当前流程执行ID，执行实例以及当前流程节点的ID：
            String excId = task.getExecutionId();
            ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
            String activitiId = execution.getActivityId();
            // 4、然后循环activitiList
            // 并判断出当前流程所处节点，然后得到当前节点实例，根据节点实例获取所有从当前节点出发的路径，然后根据路径获得下一个节点实例：
            for (ActivityImpl activityImpl : activitiList) {
                String id = activityImpl.getId();
                if (activitiId.equals(id)) {
                    List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();// 获取从某个节点出来的所有线路
                    for (PvmTransition tr : outTransitions) {
                        PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
                        System.out.println("下一步任务任务：" + ac.getProperty("name"));
                        nextId = ac.getId();
                    }
                    break;
                }
            }
        }
        return nextId;
    }

	/**
	 * @Description: 根据流程实例Id,查询流程实例 .<br>
	 * @param processInstanceId 流程实例Id .<br>
	 * @return ProcessInstance 返回流程实例.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:09:50.<br>
	 */
	@Override
	public ProcessInstance findProcessInstance(String processInstanceId) {
		try {
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			return pi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 根据流程实例Id,查询历史流程实例集合 .<br>
	 * @param instanceId 流程实例Id.<br>
	 * @return List<HistoricActivityInstance> 返回历史流程实例集合.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午3:15:14.<br>
	 */
	@Override
	public List<HistoricActivityInstance> getHistoricActivityInstances(String instanceId) {
		try {
			List<HistoricActivityInstance> activityInstances = historyService
					.createHistoricActivityInstanceQuery()
					.processInstanceId(instanceId)
					.orderByHistoricActivityInstanceId().asc()
					.list();//获取流程走过的节点，并按照节点生成先后顺序排序
			return activityInstances;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 根据处理人，查询个人历史处理处理信息 .<br>
	 * @param assignee 处理人 .<br>
	 * @return List<HistoricTaskInstance> 历史审批任务.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午3:18:08.<br>
	 */
	@Override
	public List<HistoricTaskInstance> getHisTaskList(String assignee) {
		try {
			List<HistoricTaskInstance> list = historyService
					.createHistoricTaskInstanceQuery()
					.taskAssignee(assignee)
					.orderByTaskId()
					.desc()
					.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 根据处理人，查询当前任务的审批记录 .<br>
	 * @param assignee 处理人 .<br>
	 * @return List<HistoricTaskInstance> 历史审批任务.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午3:18:08.<br>
	 */
	@Override
	public List<HistoricTaskInstance> getHisTaskByTaskId(String taskId) {
		//获取正在运行的任务
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
			String processInstanceId = task.getProcessInstanceId();
			List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery()
					.processInstanceId(processInstanceId).orderByTaskId().asc().list();
			return hisTasks;
		}
		return null;
	}
	
	/**
	 * @Description: 根据处理人，查询个人历史处理处理信息 .<br>
	 * @param assignee 处理人 .<br>
	 * @return List<HistoricTaskInstance> 历史审批任务.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午3:18:08.<br>
	 */
	@Override
	public List<HistoricTaskInstance> getHisTask(String billId, String key) {
		try {
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
					.processInstanceBusinessKey(billId).processDefinitionKey(key)
					.orderByProcessInstanceStartTime().asc().list().get(0);
			String processInstanceId = hpi.getId();
			return historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId)
						.orderByTaskId().asc().list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 通过任务Id,获取批注信息 .<br>
	 * @param taskId 当前任务Id.<br>
	 * @return List<Comment> 返回审批批注.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:18:55.<br>
	 */
	@Override
	public List<Comment> findCommentByTaskId(String taskId) {
		try {
			List<Comment> list = new ArrayList<Comment>();
			//使用当前任务ID，获取当前任务对象
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			//获取流程实例ID
			String processInstanceId = task.getProcessInstanceId();
			list = taskService.getProcessInstanceComments(processInstanceId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 通过单据Id和key值，获取批注信息 .<br>
	 * @param billId 单据Id .<br>
	 * @param key key值 .<br>
	 * @return List<Comment> 返回审批批注 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:20:03.<br>
	 */
	@Override
	public List<Comment> findCommentByBillId(String billId, String key) {
		try {
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
					.processInstanceBusinessKey(billId).processDefinitionKey(key)
					.orderByProcessInstanceStartTime().asc().list().get(0);
			String processInstanceId = hpi.getId();
			return taskService.getProcessInstanceComments(processInstanceId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 获取单列流程实例 .<br>
	 * @param instanceId 流程实例Id .<br>
	 * @return HistoricProcessInstance 返回历史流程实例 .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:21:33.<br>
	 */
	@Override
	public HistoricProcessInstance getHistoricProcessInstance(String instanceId) {
		try {
			HistoricProcessInstance historicProcessInstance = historyService
					.createHistoricProcessInstanceQuery()
					.processInstanceId(instanceId).singleResult();
			return historicProcessInstance;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 根据当前任务Id,获取历史审批信息及批注信息 .<br>
	 * @param taskId  当前任务Id .<br>
	 * @return List<HistoryTaskComment> 历史任务审批及批注.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午10:38:04 .<br>
	 */
	@Override
	public List<HistoryTaskComment> findTaskComment(String taskId) {
		List<HistoryTaskComment> taskComments = new ArrayList<HistoryTaskComment>();
		//获取历史任务信息
		List<HistoricTaskInstance> hisTask = getHisTaskByTaskId(taskId);
		//获取历史批注信息
		List<Comment> hisComments = findCommentByTaskId(taskId);
		for(HistoricTaskInstance ht:hisTask){
			if(ht.getEndTime()!=null){
				HistoryTaskComment htc = new HistoryTaskComment();
				htc.setId(ht.getId());
				htc.setName(ht.getName());
				htc.setAssignee(ht.getAssignee());
				htc.setStartTime(ht.getStartTime());
				htc.setEndTime(ht.getEndTime());
				htc.setDurationInMillis(ht.getDurationInMillis()/60000);
				for(Comment hc:hisComments){
					if(ht.getId().equals(hc.getTaskId())){
						htc.setHistoryCommentId(hc.getId());
						htc.setUserId(hc.getUserId());
						htc.setOperateType(hc.getType());
						htc.setMessage(hc.getFullMessage());
					}
				}
				taskComments.add(htc);
			}
		}
		return taskComments;
	}


	@Override
	public boolean jumpTask(String taskId, String targetTaskDefinitionKey, Map<String,Object> variables) {
		//通过任务Id,获取正在执行的任务信息
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//通过任务Id和
		ActivityImpl activity = findActivitiImpl(taskId, targetTaskDefinitionKey);
        ExecutionEntity execution = (ExecutionEntity)runtimeService.createExecutionQuery()
        		.executionId(task.getExecutionId()).singleResult();

        return true;
	}

	/**
	 * @Description: 根据单据Id,获取单据的最新流程实例 .<br>
	 * @param billId 单据Id.<br>
	 * @return HistoricProcessInstance .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-28 下午4:09:13.<br>
	 */
	@Override
	public HistoricProcessInstance getHisProInst(String billId) {
		List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(billId).orderByProcessInstanceStartTime().desc().list();
		if(hpiList.size()>0){
			return hpiList.get(0);
		}
		return null;
	}

	/**
	 * @Description: 通过单据Id和key，查询单据的审批任务 .<br>
	 * @param billId 单据Id.<br>
	 * @param key 启动流程的key值.<br>
	 * @return List<HistoryTaskComment> 历史审批意见.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-28 下午4:20:49.<br>
	 */
	@Override
	public List<HistoryTaskComment> findTaskComment(String billId, String key) {
		List<HistoryTaskComment> taskComments = new ArrayList<HistoryTaskComment>();
		//获取历史任务信息
		List<HistoricTaskInstance> hisTask = getHisTask(billId, key);
		//获取历史批注信息
		List<Comment> hisComments = findCommentByBillId(billId, key);
		for(HistoricTaskInstance ht:hisTask){
			if(ht.getEndTime()!=null){
				HistoryTaskComment htc = new HistoryTaskComment();
				htc.setId(ht.getId());
				htc.setName(ht.getName());
				htc.setAssignee(ht.getAssignee());
				htc.setStartTime(ht.getStartTime());
				htc.setEndTime(ht.getEndTime());
				htc.setDurationInMillis(ht.getDurationInMillis()/60000);
				for(Comment hc:hisComments){
					if(ht.getId().equals(hc.getTaskId())){
						htc.setHistoryCommentId(hc.getId());
						htc.setUserId(hc.getUserId());
						htc.setOperateType(hc.getType());
						htc.setMessage(hc.getFullMessage());
					}
				}
				taskComments.add(htc);
			}
		}
		return taskComments;
	}


	@Override
	public InputStream getFlowChart(String taskId) {
		String processInstanceId = taskId;
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(taskId).singleResult();
		if(hpi==null){
			processInstanceId = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult().getProcessInstanceId();
		}
		//获取历史流程实例
	    HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    //获取流程图
	    BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
	    processEngineConfiguration = processEngine.getProcessEngineConfiguration();
	    Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

	    ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
	    ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

	    List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
	    //高亮环节id集合
	    List<String> highLightedActivitis = new ArrayList<String>();
	    //高亮线路id集合
	    List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);

	    for(HistoricActivityInstance tempActivity : highLightedActivitList){
           	String activityId = tempActivity.getActivityId();
           	highLightedActivitis.add(activityId);
       	}
	    //当前流程实例执行到哪个节点
	    ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();// 执行实例
	    if(execution!=null){
	    	highLightedActivitis.add(execution.getActivityId());
	    }
	    //中文显示的是口口口，设置字体就好了
	    InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,highLightedFlows,"宋体","宋体",null,null,1.0);
	       
		return imageStream;
	}
	
	/**
	    * 获取需要高亮的线
	    * @param processDefinitionEntity
	    * @param historicActivityInstances
	    * @return
	    */
	   private List<String> getHighLightedFlows(
	           ProcessDefinitionEntity processDefinitionEntity,
	           List<HistoricActivityInstance> historicActivityInstances) {
	       List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
	       for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
	           ActivityImpl activityImpl = processDefinitionEntity
	                   .findActivity(historicActivityInstances.get(i)
	                           .getActivityId());// 得到节点定义的详细信息
	           List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
	           ActivityImpl sameActivityImpl1 = processDefinitionEntity
	                   .findActivity(historicActivityInstances.get(i + 1)
	                           .getActivityId());
	           // 将后面第一个节点放在时间相同节点的集合里
	           sameStartTimeNodes.add(sameActivityImpl1);
	           for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
	               HistoricActivityInstance activityImpl1 = historicActivityInstances
	                       .get(j);// 后续第一个节点
	               HistoricActivityInstance activityImpl2 = historicActivityInstances
	                       .get(j + 1);// 后续第二个节点
	               if (activityImpl1.getStartTime().equals(
	                       activityImpl2.getStartTime())) {
	                   // 如果第一个节点和第二个节点开始时间相同保存
	                   ActivityImpl sameActivityImpl2 = processDefinitionEntity
	                           .findActivity(activityImpl2.getActivityId());
	                   sameStartTimeNodes.add(sameActivityImpl2);
	               } else {
	                   // 有不相同跳出循环
	                   break;
	               }
	           }
	           List<PvmTransition> pvmTransitions = activityImpl
	                   .getOutgoingTransitions();// 取出节点的所有出去的线
	           for (PvmTransition pvmTransition : pvmTransitions) {
	               // 对所有的线进行遍历
	               ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
	                       .getDestination();
	               // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
	               if (sameStartTimeNodes.contains(pvmActivityImpl)) {
	                   highFlows.add(pvmTransition.getId());
	               }
	           }
	       }
	       return highFlows;
	   }
	
}
