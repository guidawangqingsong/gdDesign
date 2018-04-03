package cn.jjxx.modules.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.activiti.workflow.AssigneeInfo;
import cn.jjxx.modules.activiti.workflow.BillBean;
import cn.jjxx.modules.activiti.workflow.Config;
import cn.jjxx.modules.activiti.workflow.OperateBean;
import cn.jjxx.modules.activiti.workflow.history.HistoryTaskComment;
import cn.jjxx.modules.activiti.workflow.run.BillTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import cn.jjxx.modules.activiti.config.Contacts;
import cn.jjxx.modules.activiti.entity.TransactEntity;
import cn.jjxx.modules.activiti.service.impl.FlowService;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.utils.UserUtils;
import cn.jjxx.modules.workflow.controller.ActHiProcinstController;
import cn.jjxx.modules.workflow.controller.HisTaskController;
import cn.jjxx.modules.workflow.controller.TaskController;
import cn.jjxx.modules.workflow.service.IFlowCommonService;

@Controller  
@Scope("prototype")   
@RequestMapping("${admin.url.prefix}/activiti/flow")
public class WorkFlowController extends FlowService{
	
	@Autowired
	protected IFlowCommonService flowCommonService;
	
	/**
	 * @Description: 跳转至个人代办任务界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 跳转.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午9:39:00 .<br>
	 */
	@RequestMapping(value="/personal/needDisposeTask",method=RequestMethod.GET) 
	public String  needDisposeTask(HttpServletRequest request, HttpServletResponse response){
		return display("personal/needDisposeTaskList");
	}
	
	/**
	 * @Description: 跳转至个人已完成任务界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 跳转.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午9:39:00 .<br>
	 */
	@RequestMapping(value="/personal/completedTask") 
	public String completedTask(HttpServletRequest request, HttpServletResponse response){
		return display("personal/completedTaskList");
	}
	
	/**
	 * @Description: 跳转至个人启动任务界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 跳转.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午9:39:00 .<br>
	 */
	@RequestMapping(value="/personal/startTask") 
	public String startTask(HttpServletRequest request, HttpServletResponse response){
		return display("personal/startTaskList");
	}
	
	
	/**
	 * @Description: 获取个人代办任务结果集 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午9:39:00 .<br>
	 */
	@RequestMapping(value="/personal/run/ajaxList") 
	public void findNeedDisposeTaskList(Queryable queryable,PropertyPreFilterable propertyPreFilterable,
			HttpServletRequest request, HttpServletResponse response){
		String content = SpringContextHolder.getApplicationContext().getBean(TaskController.class)
				.getTasks(queryable, propertyPreFilterable, request, response,true);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 获取个人已完成任务结果集 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午9:39:00 .<br>
	 */
	@RequestMapping(value="/personal/history/ajaxList") 
	public void  findCompletedTaskList(Queryable queryable,PropertyPreFilterable propertyPreFilterable,
			HttpServletRequest request, HttpServletResponse response){
		String content = SpringContextHolder.getApplicationContext().getBean(HisTaskController.class)
				.getHisTasks(queryable, propertyPreFilterable, request, response,true);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 获取个人启动任务结果集 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午9:39:00 .<br>
	 */
	@RequestMapping(value="/personal/start/ajaxList") 
	public void findStartTaskList(Queryable queryable,PropertyPreFilterable propertyPreFilterable,
			HttpServletRequest request, HttpServletResponse response){
		String content = SpringContextHolder.getBean(ActHiProcinstController.class)
				.getAjaxListString(queryable, propertyPreFilterable, request, response);
		StringUtils.printJson(response, content);
	}
	
	
	
	/**
	 * @Description: 跳转至流程审批界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回界面模型.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-13 上午9:55:30.<br>
	 */
	@RequestMapping(value="/flowAudit",method=RequestMethod.GET) 
	public String  flowAudit(HttpServletRequest request, HttpServletResponse response,String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
			BillBean billInfo =  (BillBean) runtimeService.getVariable(task.getExecutionId(), Config.variable.表单信息.toString());
			if(billInfo!=null){
				request.setAttribute("formUrl", billInfo.getFormUrl());
			}
		}
		return display("run/flowAudit");
	}
	
	/**
	 * @Description: 跳转到转办任务界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回界面模型.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-25 下午5:24:34.<br>
	 */
	@RequestMapping(value="/transactTask",method=RequestMethod.GET) 
	public String transactTask(Model model,HttpServletRequest request, HttpServletResponse response){
		if (!model.containsAttribute("data")) {
            model.addAttribute("data", new TransactEntity());
        }
		return display("run/transact");
	}
	
	/**
	 * @Description: 跳转至管理员转办任务选择人员界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回界面模型.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-13 上午9:55:30.<br>
	 */
	@RequestMapping(value="/selectUser",method=RequestMethod.GET) 
	public String  selectUser(HttpServletRequest request, HttpServletResponse response){
		return display("run/selectUser");
	}

	/**
	 * @Description: 跳转到正在运行的任务界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回界面模型.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-17 下午2:19:09.<br>
	 */
	@RequestMapping(value="/taskList",method=RequestMethod.GET)  
    public String  taskList(HttpServletRequest request, HttpServletResponse response){  
    	return display("run/taskList");
    }
	
	/**
	 * @Description: 跳转到历史任务界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回界面模型.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-17 下午2:19:09.<br>
	 */
	@RequestMapping(value="/historyTaskList",method=RequestMethod.GET)  
    public String  historyTaskList(HttpServletRequest request, HttpServletResponse response){  
    	return display("history/historyTaskList");
    }
	
	/**
	 * @Description: 跳转至流程图界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回页面.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-13 上午9:49:38.<br>
	 */
	@RequestMapping(value="/flowchart",method=RequestMethod.GET) 
	public String  showFlowImage(HttpServletRequest request, HttpServletResponse response){  
    	return display("history/flowchart");
    }
	
	/**
	 * @Description: 跳转流程审批历史记录界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回页面.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-12-13 上午9:51:01.<br>
	 */
	@RequestMapping(value="/histaskComment",method=RequestMethod.GET) 
	public String  histaskComment(HttpServletRequest request, HttpServletResponse response){  
    	return display("history/histaskComment");
    }
	
	/**
	 * @Description: 显示流程图 .<br>
	 * @param request http请求.<br>
	 * @param response http相应.<br>
	 * @param taskId 任务Id.<br>  
	 * @author 郑成功 .<br>
	 * @date 2017-12-4 上午12:08:27 .<br>
	 */
	@RequestMapping(value="/showFlow",method=RequestMethod.GET) 
	public void  showFlow(HttpServletRequest request, HttpServletResponse response,String taskId){  
		InputStream imageStream = getFlowChart(taskId);
		try {
			byte[] b = new byte[1024];
			int len;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
    }
	
	/**
	 * @Description: 获取历史审批意见列表 .<br>
	 * @param request http请求.<br>
	 * @param response http相应.<br>
	 * @param taskId 任务Id.<br>  
	 * @author 郑成功 .<br>
	 * @date 2017-12-13 上午9:52:22.<br>
	 */
	@RequestMapping(value="/history/histaskComment",method = { RequestMethod.GET, RequestMethod.POST })  
	public void histaskComment(HttpServletRequest request, HttpServletResponse response,String taskId){
		String procInstId = findProcessInstanceByTaskId(taskId).getId();
		String content = SpringContextHolder.getApplicationContext().getBean(HisTaskController.class)
				.getHisTakComment(procInstId);
		
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 获取正在运行的任务列表 .<br>
	 * @param queryable grid封装参数类 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-11-17 下午2:21:07.<br>
	 */
	@RequestMapping(value="/task/ajaxList",method = { RequestMethod.GET, RequestMethod.POST })  
	public void taskAjaxList(Queryable queryable,PropertyPreFilterable propertyPreFilterable,
			HttpServletRequest request, HttpServletResponse response){
		String content = SpringContextHolder.getApplicationContext().getBean(TaskController.class)
				.getTasks(queryable, propertyPreFilterable, request, response,false);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 获取历史任务列表 .<br>
	 * @param queryable grid封装参数类 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-11-17 下午2:21:07.<br>
	 */
	@RequestMapping(value="/history/ajaxList",method = { RequestMethod.GET, RequestMethod.POST })  
	public void historyTaskAjaxList(Queryable queryable,PropertyPreFilterable propertyPreFilterable,
			HttpServletRequest request, HttpServletResponse response){
		String content = SpringContextHolder.getApplicationContext().getBean(HisTaskController.class)
				.getHisTasks(queryable, propertyPreFilterable, request, response,false);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 部署流程 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return AjaxJson 返回ajax处理数据.<br> 
	 * @throws 抛出IO流异常信息.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 下午3:58:35.<br>
	 */
	@RequestMapping("/repositoryFlow")
	@ResponseBody
	public AjaxJson repositoryFlow(HttpServletRequest request,HttpServletResponse response) throws IOException{
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String fileName = request.getParameter("filename");
	    Iterator<String> fileNames = multipartRequest.getFileNames();
	    
	    MultipartFile multfile =  multipartRequest.getFile(fileNames.next());
	    InputStream in = multfile.getInputStream();
		boolean isDeploy = flowService.saveNewDeployInputStream(in, fileName);
		j.setRet(AjaxJson.RET_SUCCESS);
		if(isDeploy){
			j.setMsg("流程模型部署成功");
		}else{
			j.setMsg("流程模型部署失败");
		}
		return j;
	}
	
	/**
	 * @Description: 提交流程 .<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>
	 * @param billBean 单据相关信息.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-9-21 下午5:01:07.<br>
	 */
	@RequestMapping(value="/submitFlow")
    @ResponseBody
    public AjaxJson submitFlow(HttpServletRequest request,HttpServletResponse response,BillBean billBean){
		AjaxJson j = new AjaxJson();
		User user = UserUtils.getUser();
		AssigneeInfo assigneeInfo = new AssigneeInfo();
		assigneeInfo.setAssigneeId(user.getId());
		assigneeInfo.setAssigneeName(user.getRealname());
		//TODO 查询单据信息
		String billId = billBean.getBillId();
		String idFeild = billBean.getIdFeild();
		String statusFeild = billBean.getStatusFeild();
		billBean.setSubmitter(user.getId());
		int status = Contacts.SUBMIT;
		Map<String,Object> billInfo = flowCommonService.findBillInfo(billId, billBean.getTableName(), idFeild);
		boolean flag = submitFlow(billBean, assigneeInfo, true, billInfo);
		if(flag){
			//TODO 修改单据状态
			boolean updateStatus = flowCommonService.updateBillStatus(billId, billBean.getTableName(), idFeild, statusFeild, status);
			if(updateStatus){
				j.setMsg("流程提交成功");
			}else{
				j.setRet(AjaxJson.RET_FAIL);
				j.setMsg("流程提交成功,更新单据状态失败");
			}
		}else{
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("流程提交失败,请联系管理员");
		}
		return j;
	}
	
	/**
	 * @Description: 办理任务，审批通过或者打回任务 .<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>
	 * @param taskId 任务Id.<br>
	 * @param operate 操作信息.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 上午11:31:49.<br>
	 */
	@RequestMapping("/completeTask")
	@ResponseBody
	public AjaxJson completeTask(HttpServletRequest request,HttpServletResponse response,String taskId,OperateBean operate){
		AjaxJson j = new AjaxJson();
		//根据任务Id,获取相关信息
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String executionId = task.getExecutionId();
		
		User user = UserUtils.getUser();
		AssigneeInfo assigneeInfo = new AssigneeInfo();
		assigneeInfo.setAssigneeId(user.getId());
		assigneeInfo.setAssigneeName(user.getRealname());
		BillBean billBean = runtimeService.getVariable(executionId, Config.variable.表单信息.toString(), BillBean.class);
		//TODO 查询单据信息
		String billId = billBean.getBillId();
		String tableName = billBean.getTableName();
		String idFeild = billBean.getIdFeild();
		String statusFeild = billBean.getStatusFeild();
		int status = Contacts.AUDITED;
		Map<String,Object> billInfo = flowCommonService.findBillInfo(billId, tableName, idFeild);
		boolean flag = completeTask(taskId, operate, assigneeInfo, true, billInfo);
		if(flag){
			//TODO 任务是否办理成功，办理成功后，处理相关业务，列如发送短信
			ProcessInstance pi = findProcessInstance(executionId);
			j.setMsg("流程审批成功");
			if(pi==null){
				//TODO 修改单据状态
				boolean updateStatus = flowCommonService.updateBillStatus(billId, tableName, idFeild, statusFeild, status);
				if(!updateStatus){
					j.setRet(AjaxJson.RET_FAIL);
					j.setMsg("流程审批成功,更新单据状态失败");
				}
			}
		}else{
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("流程审批失败,请联系管理员");
		}
		return j;
	}
	
	/**
	 * @Description: 通过当前任务Id以及指定的活动节点,驳回任务 .<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>
	 * @param taskId 当前任务Id.<br>
	 * @param activityId 历史活动节点Id.<br>
	 * @param variables 流程变量参数.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 上午11:29:13.<br>
	 */
	@RequestMapping("/backProcess")
	@ResponseBody
	public AjaxJson backProcess(HttpServletRequest request,HttpServletResponse response,
			String taskId, String activityId, Map<String, Object> variables){
		AjaxJson j = new AjaxJson();
		boolean flag = backProcess(taskId, activityId, variables);
		j.setMsg("驳回任务成功!");
		if(!flag){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("驳回任务失败");
		}
		return j; 
	};
	
	/**
	 * @Description: 通过当前任务Id和被转办人，设置转办流程 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @param taskId 当前任务Id.<br>
	 * @param TransactEntity 转办的实体类.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 上午11:27:39.<br>
	 */
	@RequestMapping(value="/transferAssignee",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson transferAssignee(HttpServletRequest request,HttpServletResponse response,TransactEntity transact){
		AjaxJson j = new AjaxJson();
		boolean flag = transferAssignee(transact.getTaskId(), transact.getAssignee());
		j.setMsg("转办任务成功!");
		if(!flag){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("转办任务失败!");
		}
		return j; 
	};
	
	/**
	 * @Description: 通过任务Id和指定的会签人员,进行会签 .<br>
	 * @param request http响应.<br>
	 * @param response http请求.<br>
	 * @param taskId 任务Id.<br>  
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 上午11:25:21.<br>
	 */
	@RequestMapping("/jointProcess")
	@ResponseBody
	public AjaxJson jointProcess(HttpServletRequest request,HttpServletResponse response,String taskId){
		//会签人员集合
		List<AssigneeInfo> assignees = new ArrayList<AssigneeInfo>();
		AjaxJson j = new AjaxJson();
		boolean flag = jointProcess(taskId, assignees);
		j.setMsg("会签成功!");
		if(!flag){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("会签失败!");
		}
		return j; 
	};
	
	/**
	 * @Description: 通过任务Id,终止流程(权限管理员使用) .<br>
	 * @param request http响应.<br>
	 * @param response http请求.<br>
	 * @param taskId 当前任务Id.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 上午11:24:19.<br>
	 */
	@RequestMapping("/endProcess")
	@ResponseBody
	public AjaxJson endProcess(HttpServletRequest request,HttpServletResponse response,String taskId){
		AjaxJson j = new AjaxJson();
		boolean flag = endProcess(taskId);
		j.setMsg("终止流程成功!");
		if(!flag){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("终止流程失败!");
		}
		return j; 
	};
	
	/**
	 * @Description: 通过任务Id,挂起流程(权限管理员使用) .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @param taskId 任务Id.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 上午11:21:20.<br>
	 */
	@RequestMapping("/suspendProcessInstance")
	@ResponseBody
	public AjaxJson suspendProcessInstance(HttpServletRequest request,HttpServletResponse response,String taskId){
		//通过任务Id,获取流程实例Id
		ProcessInstance pi = findProcessInstanceByTaskId(taskId);
		AjaxJson j = new AjaxJson();
		boolean flag = suspendProcessInstance(pi.getProcessInstanceId());
		j.setMsg("流程挂起成功!");
		if(!flag){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("流程挂起失败!");
		}
		return j; 
	};
	
	/**
	 * @Description: 激活流程 (权限管理员使用).<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>
	 * @param taskId 任务Id.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 上午11:17:20.<br>
	 */
	@RequestMapping("/activateProcessInstance")
	@ResponseBody
	public AjaxJson activateProcessInstance(HttpServletRequest request,HttpServletResponse response,String taskId){
		//通过任务Id,获取流程实例Id
		ProcessInstance pi = findProcessInstanceByTaskId(taskId);
		AjaxJson j = new AjaxJson();
		boolean flag = activateProcessInstance(pi.getProcessInstanceId());
		j.setMsg("激活流程成功!");
		if(!flag){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("激活流程失败!");
		}
		return j; 
	};
	
	/**
	 * @Description: 流程跳转到指定的节点(权限管理员使用) .<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>
	 * @param taskId 当前任务Id .<br>
	 * @param targetTaskDef 目标任务节点（在模型定义里面的节点名称）.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 下午3:51:37.<br>
	 */
	@RequestMapping("/jumpTask")
	@ResponseBody
	public AjaxJson jumpTask(HttpServletRequest request,HttpServletResponse response,String taskId,String targetTaskDef){
		Map<String,Object> variables = new HashMap<String, Object>();
		boolean flag = jumpTask(taskId, targetTaskDef, variables);
		AjaxJson j = new AjaxJson();
		j.setMsg("流程跳转成功!");
		if(!flag){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("流程跳转失败");
		}
		return j; 
	};
	
	/**
	 * @Description: 根据任务Id,获取流程审批记录信息 .<br>
	 * @param taskId 当前任务Id.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 下午4:01:00.<br>
	 */
	@RequestMapping("/findTaskComment")
	@ResponseBody
	public List<HistoryTaskComment> findTaskComment(HttpServletRequest request,HttpServletResponse response,String taskId){
		List<HistoryTaskComment> list = findTaskComment(taskId);
		return list;
	}
	
	/**
	 * @Description: 根据单据Id和启动流程的key,获取流程历史审批信息 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @param billId 单据Id.<br>
	 * @param key 启动流程的key.<br>
	 * @return List<HistoryTaskComment> 历史审批信息.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-28 下午4:24:47.<br>
	 */
	@RequestMapping("/findTaskCommentByBillId")
	@ResponseBody
	public List<HistoryTaskComment> findTaskCommentByBillId(HttpServletRequest request,HttpServletResponse response,String billId,String key){
		List<HistoryTaskComment> list = findTaskComment(billId,key);
		return list;
	}
	
	/**
	 * @Description: 获取当前用户的个人待办任务 .<br>
	 * @param request http请求.<br> 
	 * @return List<Map<String,Object>> 任务信息集合.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-22 下午4:19:01.<br>
	 */
	@RequestMapping("/findNeedTransactTask")
	@ResponseBody
	public List<BillTask> findNeedTransactTask(HttpServletRequest request,HttpServletResponse response){
		List<BillTask> taskList = new ArrayList<BillTask>();
		User user = UserUtils.getUser();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(user.getId()).list();
		for(Task task:tasks){
			BillTask bt = new BillTask();
			bt.setId(task.getId());
			bt.setName(task.getName());
			bt.setAssigneeName(task.getAssignee());
			bt.setBillName("计量单位");
			bt.setBillNumber("010101");
			bt.setStartTime(String.valueOf(task.getCreateTime().getTime()));
			bt.setDescription(task.getDescription());
			taskList.add(bt);
		}
		return taskList;
	}
	
	/**
	 * @Description: 跳转到审批界面 .<br>
	 * @param request http请求.<br>
	 * @param filePath http响应.<br>
	 * @param taskId 任务Id.<br>
	 * @return ModelAndView .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-27 上午10:47:27.<br>
	 */
	@RequestMapping(value = {"/task/{filePath}"})
	public ModelAndView forwordList(HttpServletRequest request,@PathVariable java.lang.String filePath,String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		BillBean billInfo = runtimeService.getVariable(processInstanceId, Config.variable.表单信息.toString(), BillBean.class);
		request.setAttribute("billInfo", billInfo);
		String path = filePath.replace("-", "/");
		return new ModelAndView(path);
	}
}
