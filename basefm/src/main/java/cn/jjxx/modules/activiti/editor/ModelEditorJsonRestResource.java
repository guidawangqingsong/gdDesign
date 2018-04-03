 package cn.jjxx.modules.activiti.editor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelEditorJsonRestResource
  implements ModelDataJsonConstants
{
  protected static final Logger LOGGER = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

  @Autowired
  private RepositoryService repositoryService;

  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value={"/service/model/{modelId}/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ObjectNode getEditorJson(@PathVariable String modelId) { ObjectNode modelNode = null;

  	System.out.println("ModelEditorJsonRestResource.getEditorJson---------");
    Model model = this.repositoryService.getModel(modelId);

    if (model != null) {
      try {
        if (StringUtils.isNotEmpty(model.getMetaInfo())) {
          modelNode = (ObjectNode)this.objectMapper.readTree(model.getMetaInfo());
        } else {
          modelNode = this.objectMapper.createObjectNode();
          modelNode.put("name", model.getName());
        }
        modelNode.put("modelId", model.getId());
        ObjectNode editorJsonNode = (ObjectNode)this.objectMapper.readTree(new String(this.repositoryService
          .getModelEditorSource(model
          .getId()), "utf-8"));
        modelNode.put("model", editorJsonNode);
      }
      catch (Exception e) {
        LOGGER.error("Error creating model JSON", e);
        throw new ActivitiException("Error creating model JSON", e);
      }
    }
    return modelNode;
  }
  
    @RequestMapping(value = {"/service/model/findModelInfo"}, method = RequestMethod.POST)
	@ResponseBody
	public ModelEntity findModelInfo(HttpServletRequest request, HttpServletResponse response,String modelId) {
    	ModelEntity actModel = new ModelEntity();
	  	Model model = repositoryService.createModelQuery().modelId(modelId).singleResult();
	  	actModel.setName(model.getName());
	  	actModel.setKey(model.getKey());
	  	return actModel;
    }
}