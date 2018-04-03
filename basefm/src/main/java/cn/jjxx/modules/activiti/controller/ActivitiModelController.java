package cn.jjxx.modules.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.activiti.config.Contacts;
import cn.jjxx.modules.activiti.entity.ActivitiModelEntity;
import cn.jjxx.modules.activiti.entity.ActivitiProcessDefinitionEntity;
import cn.jjxx.modules.activiti.service.impl.FlowService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Controller  
@Scope("prototype")   
@RequestMapping("${admin.url.prefix}/activiti/deploy")
public class ActivitiModelController extends FlowService{
	
	/**
	 * @Description: 跳转到用户分组界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午7:54:33 .<br>
	 */
	@RequestMapping(value="/group/list",method=RequestMethod.GET)  
    public String  groupList(HttpServletRequest request, HttpServletResponse response){  
    	return display("group/groupList");
    }
	
	/**
	 * @Description: 跳转到用户组配置用户界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午7:54:33 .<br>
	 */
	@RequestMapping(value="/group/userList",method=RequestMethod.GET)  
    public String  userList(HttpServletRequest request, HttpServletResponse response){  
    	return display("group/userList");
    }
	
	/**
	 * @Description: 跳转到流程设计list界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-17 上午11:03:41.<br>
	 */
	@RequestMapping(value="/modeler/list",method=RequestMethod.GET)  
    public String  modelerList(HttpServletRequest request, HttpServletResponse response){  
    	return display("modelList");
    }
	@RequestMapping(value="/flowAudit",method=RequestMethod.GET)  
    public String  flowAudit(HttpServletRequest request, HttpServletResponse response){  
    	return display("flowAudit");
    }
	
	/**
	 * @Description:  .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>   
	 * @return void .<br> 
	 * @throws .<br>
	 * @author 郑成功 .<br>
	 * @date 2017-11-16 上午8:39:10.<br>
	 */
	@RequestMapping(value="/model/ajaxList",method = { RequestMethod.GET, RequestMethod.POST })  
	public void ModelList(Queryable queryable,HttpServletRequest request, HttpServletResponse response){
		List<ActivitiModelEntity> medelList = new ArrayList<ActivitiModelEntity>();
		int page = queryable.getPageable().getPageNumber()-1;
		int pageSize = queryable.getPageable().getPageSize();
		List<Model> list = repositoryService.createModelQuery().listPage(page,pageSize);
		List<ProcessDefinition> preDefList = repositoryService.createProcessDefinitionQuery().list();
		Long total = repositoryService.createModelQuery().count();
		for(Model m:list){
			ActivitiModelEntity model = new ActivitiModelEntity();
			model.setId(m.getId());
			model.setName(m.getName());
			model.setKey(m.getKey());
			model.setCategory(m.getCategory());
			model.setCreateTime(m.getCreateTime());
			model.setDiscription(String.valueOf(JSONObject.parseObject(m.getMetaInfo()).get("description")));
			boolean isDeploy = checkIsDeploy(preDefList, m.getKey());
			if(isDeploy){
				model.setStatus(1);
			}else {
				model.setStatus(0);
			}
			medelList.add(model);
		}
		PageJson<ActivitiModelEntity> pagejson = new PageJson<>(page,pageSize,total,medelList);
		String content = JSON.toJSONString(pagejson);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 检测当前模型是否已部署 .<br>
	 * @param preDefList 流程定义集合 .<br>
	 * @param key 模型的key.<br>
	 * @return boolean 是否部署.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-16 下午4:39:28.<br>
	 */
	private static boolean checkIsDeploy(List<ProcessDefinition> preDefList,String key){
		for(ProcessDefinition pd:preDefList){
			if(pd.getKey().equals(key)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @Description: 跳转至流程版本监控界面 .<br>
	 * @param request http请求.<br>
	 * @param response http响应.<br>
	 * @return String 返回流程版本监控界面.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-16 下午5:51:11.<br>
	 */
	@RequestMapping(value="/predef/list",method=RequestMethod.GET)  
    public String  predefList(HttpServletRequest request, HttpServletResponse response){  
    	return display("predef/predefList");
    }
	
	/**
	 * @Description: 获取流程版本监控数据 .<br>
	 * @param queryable grid的绑定属性 .<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>  
	 * @param modelId 模型Id .<br>    
	 * @author 郑成功 .<br>
	 * @date 2017-11-16 下午5:53:38.<br>
	 */
	@RequestMapping(value="/predef/ajaxList",method = { RequestMethod.GET, RequestMethod.POST })  
	public void predefList(Queryable queryable,HttpServletRequest request, HttpServletResponse response,String modelId){
		List<ActivitiProcessDefinitionEntity> pdList = new ArrayList<ActivitiProcessDefinitionEntity>();
		int page = queryable.getPageable().getPageNumber()-1;
		int pageSize = queryable.getPageable().getPageSize();
		Model model = repositoryService.createModelQuery().modelId(modelId).singleResult();
		List<ProcessDefinition> predefList = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(model.getKey()).orderByProcessDefinitionVersion()
				.asc().listPage(page,pageSize);
		Long total = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getKey()).count();
		for(ProcessDefinition pd:predefList){
			ActivitiProcessDefinitionEntity predef = new ActivitiProcessDefinitionEntity();
			predef.setId(pd.getId());
			predef.setName(pd.getName());
			if(pd.getName()==null){
				predef.setName(model.getName());
			}
			predef.setKey(pd.getKey());
			predef.setVersion(pd.getVersion());
			predef.setDeploymentId(pd.getDeploymentId());
			predef.setResourceName(pd.getResourceName());
			predef.setDiagramResourceName(pd.getDiagramResourceName());
			predef.setDescription(pd.getDescription());
			predef.setSuspensionState(1);
			pdList.add(predef);
		}
		PageJson<ActivitiProcessDefinitionEntity> pagejson = new PageJson<>(page,pageSize,total,pdList);
		String content = JSON.toJSONString(pagejson);
		StringUtils.printJson(response, content);
	}
	
	
	/**
	 * @Description: 跳转至流程模型设计界面 .<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>
	 * @return String 返回界面.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-17 上午11:05:34.<br>
	 */
    @RequestMapping(value="/modeler",method=RequestMethod.GET)  
    public String  modeler(HttpServletRequest request, HttpServletResponse response){  
    	return display("modeler");
    }
    
	/**
	 * @Description: 创建模型,保存相关信息 .<br>
	 * @param request http请求 .<br>
	 * @param response http响应 .<br>
	 * @return String .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-17 上午11:02:14.<br>
	 */
	@RequestMapping(value = "createModel", method = RequestMethod.GET)
	public String _showCreateModel(HttpServletRequest request, HttpServletResponse response) {	
		request.setAttribute("data", new ActivitiModelEntity());
		return display("modelEdit");
	}
    
    /**
     * @Description: 创建模型,保存模型的基本信息 .<br>
     * @param request http请求.<br>
     * @param response http响应.<br>
     * @param model 模型的实体.<br>
     * @return AjaxJson .<br> 
     * @author 郑成功 .<br>
     * @date 2017-11-15 下午5:39:24.<br>
     */
	@RequestMapping(value = "createModel", method = RequestMethod.POST)
	@ResponseBody
    public AjaxJson createModel(HttpServletRequest request, HttpServletResponse response,ActivitiModelEntity model) {
    	AjaxJson j = new AjaxJson();
        try {
        	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        	 
        	RepositoryService repositoryService = processEngine.getRepositoryService();
        	 
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put(Contacts.modelKey.id.toString(), Contacts.modelValue.canvas.toString());
            editorNode.put(Contacts.modelKey.resourceId.toString(), Contacts.modelValue.canvas.toString());
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put(Contacts.modelKey.namespace.toString(), Contacts.ACTIVITI_NAMESPACE);
            editorNode.put(Contacts.modelKey.stencilset.toString(), stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, model.getModelName());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            String description = model.getDiscription();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(model.getModelName());
            modelData.setKey(model.getModelKey());
            modelData.setCategory(model.getModelType());
            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8")); 
            j.setMsg("保存成功");
            return j;
        } catch (Exception e) {
        	j.setMsg("保存失败!<br />原因:" + e.getMessage());
        	j.setRet(AjaxJson.RET_FAIL);
            return j;
        }
    }
	
	/**
	 * @Description: 根据模型Id,部署流程 .<br>
	 * @param request http请求.<br>
	 * @param modelId 模型Id.<br>
	 * @return AjaxJson 返回ajax处理结果.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-16 下午2:35:56.<br>
	 */
	@RequestMapping(value = "deployModel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson deploy(HttpServletRequest request,String modelId) {
		AjaxJson j = new AjaxJson();
		try {
			Model modelData = repositoryService.getModel(modelId);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
			byte[] bpmnBytes = null;

			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			String mName = modelData.getName();
			String processName = mName + Contacts.ACTIVITI_BPMN_SUFF;
			repositoryService.createDeployment().name(mName).addString(processName, new String(bpmnBytes, "utf-8")).deploy();
			j.setMsg("流程部署成功");
			return j;
		} catch (Exception e) {
			e.printStackTrace();
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("流程部署失败!<br />原因:"+e.getMessage());
		}
		return j;
	}
	
	/**
	 * @Description: 根据模型Id,删除未发布的模型 .<br>
	 * @param request http请求.<br>
	 * @param modelId 模型Id.<br>
	 * @return AjaxJson 返回处理结果.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-16 下午2:44:43.<br>
	 */
	@RequestMapping(value = "delModel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delModel(HttpServletRequest request,String modelId) {
		AjaxJson j = new AjaxJson();
		try {
			repositoryService.deleteModel(modelId);
			j.setMsg("删除模型成功!");
		} catch (Exception e) {
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("删除模型失败!<br />原因:"+e.getMessage());
		}
		return j;
	}
	
	/**
	 * @Description: 保存工作流用户分组信息 .<br>
	 * @param request http请求.<br>
	 * @param groupId 组Id .<br>
	 * @param 用户 Ids .<br>
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-14 下午8:20:02 .<br>
	 */
	@RequestMapping(value = "saveUserGroup", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveUserGroup(HttpServletRequest request,String groupId,String userIds) {
		AjaxJson j = new AjaxJson();
		return j;
	}

}  
