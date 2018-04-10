package cn.jjxx.modules.medicalevaluation.controller;

import cn.jjxx.core.common.data.DuplicateValid;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.model.ValidJson;
import cn.jjxx.core.query.annotation.PageableDefaults;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import cn.jjxx.core.common.controller.BaseBeanController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

import cn.jjxx.modules.medicalevaluation.entity.MedicalEvaluation;
import cn.jjxx.modules.medicalevaluation.service.IMedicalEvaluationService;

/**   
 * @Title: 医用系统民主测评
 * @Description: 医用系统民主测评
 * @author jjxx.wangqingosng
 * @date 2018-04-10 16:40:53
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/medicalevaluation/medicalevaluation")
@RequiresPathPermission("medicalevaluation:medicalevaluation")
public class MedicalEvaluationController extends BaseBeanController<MedicalEvaluation> {

    @Autowired
    protected IMedicalEvaluationService medicalEvaluationService;

    public MedicalEvaluation get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return medicalEvaluationService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<MedicalEvaluation> entityWrapper = new EntityWrapper<MedicalEvaluation>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        
        //获取没被删除的数据
        String delFlag = request.getParameter("delFlag");
        entityWrapper.eq("t.del_flag", 0);
        
        //通过组织查询,如果orgId 不为空，拼接查询条件，通过orgId来查找
        //判断如果该用户是某组织下的，则只显示没有绑定组织的日志,只能查看自己所属组织下的日志。
        String orgId = request.getParameter("orgId");
        entityWrapper.eq("t.org_id", orgId);
        
        //根据创建人查询
    	String creatBy = request.getParameter("createByName");//获取页面的要查询的信息
    	if(!StringUtils.isEmpty(creatBy)){
    		entityWrapper.eq("u.realname", creatBy);//根据输入的用户名查询日志
    	}
        
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<MedicalEvaluation> pagejson = new PageJson<MedicalEvaluation>(medicalEvaluationService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") MedicalEvaluation medicalEvaluation, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalEvaluation, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        MedicalEvaluation medicalEvaluation = get(id);
        model.addAttribute("data", medicalEvaluation);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") MedicalEvaluation medicalEvaluation, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalEvaluation, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(MedicalEvaluation medicalEvaluation, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(medicalEvaluation, result)) {
            // 错误提示
            String errorMsg = errorMsg(result);
            if (!StringUtils.isEmpty(errorMsg)) {
                ajaxJson.fail(errorMsg);
            } else {
                ajaxJson.fail("保存失败");
            }
            return ajaxJson;
        }
        try {
            if (StringUtils.isEmpty(medicalEvaluation.getId())) {
                medicalEvaluationService.insert(medicalEvaluation);
            } else {
                medicalEvaluationService.insertOrUpdate(medicalEvaluation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            medicalEvaluationService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            medicalEvaluationService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        MedicalEvaluation medicalEvaluation = get(id);
        model.addAttribute("data", medicalEvaluation);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<MedicalEvaluation> entityWrapper = new EntityWrapper<MedicalEvaluation>(entityClass);
            valid = medicalEvaluationService.doValid(duplicateValid,entityWrapper);
            if (valid) {
                validJson.setStatus("y");
                validJson.setInfo("验证通过!");
            } else {
                validJson.setStatus("n");
                if (!StringUtils.isEmpty(duplicateValid.getErrorMsg())) {
                    validJson.setInfo(duplicateValid.getErrorMsg());
                } else {
                    validJson.setInfo("当前信息重复!");
                }
            }
        } catch (Exception e) {
            validJson.setStatus("n");
            validJson.setInfo("验证异常，请检查字段是否正确!");
        }
        return validJson;
    }
}
