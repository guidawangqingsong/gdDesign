package cn.jjxx.modules.medicalhomechart.controller;

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
import cn.jjxx.core.utils.SpringContextHolder;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jjxx.modules.medicalhomechart.entity.MedicalHomeChart;
import cn.jjxx.modules.medicalhomechart.service.IMedicalHomeChartService;
import cn.jjxx.modules.sys.controller.OrganizationController;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.utils.UserUtils;

/**   
 * @Title: 医用首页图表
 * @Description: 医用首页图表
 * @author jjxx.wangqingsong
 * @date 2018-04-11 10:46:11
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/medicalhomechart/medicalhomechart")
@RequiresPathPermission("medicalhomechart:medicalhomechart")
public class MedicalHomeChartController extends BaseBeanController<MedicalHomeChart> {

    @Autowired
    protected IMedicalHomeChartService medicalHomeChartService;

    public MedicalHomeChart get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return medicalHomeChartService.selectById(id);
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
        EntityWrapper<MedicalHomeChart> entityWrapper = new EntityWrapper<MedicalHomeChart>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<MedicalHomeChart> pagejson = new PageJson<MedicalHomeChart>(medicalHomeChartService.list(queryable,entityWrapper));
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
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") MedicalHomeChart medicalHomeChart, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalHomeChart, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        MedicalHomeChart medicalHomeChart = get(id);
        model.addAttribute("data", medicalHomeChart);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") MedicalHomeChart medicalHomeChart, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalHomeChart, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(MedicalHomeChart medicalHomeChart, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(medicalHomeChart, result)) {
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
            if (StringUtils.isEmpty(medicalHomeChart.getId())) {
                medicalHomeChartService.insert(medicalHomeChart);
            } else {
                medicalHomeChartService.insertOrUpdate(medicalHomeChart);
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
            medicalHomeChartService.deleteById(id);
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
            medicalHomeChartService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        MedicalHomeChart medicalHomeChart = get(id);
        model.addAttribute("data", medicalHomeChart);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<MedicalHomeChart> entityWrapper = new EntityWrapper<MedicalHomeChart>(entityClass);
            valid = medicalHomeChartService.doValid(duplicateValid,entityWrapper);
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
    
    /**
     * @Description: 获取首页图表数据 .<br>
     * @param request .<br>
     * @param response .<br>   
     * @author jjxx.wangqingsong .<br>
     * @date 2018-4-11 下午9:20:21.<br>
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "findHomeCharts")
    @ResponseBody
	public AjaxJson findHomeCharts(HttpServletRequest request,HttpServletResponse response){
    	AjaxJson j = new AjaxJson();
    	List<Map<String,Object>> pieList = (List<Map<String, Object>>) findHomePieJson(request).getData();
    	List<Map<String,Object>> barList = (List<Map<String, Object>>) findHomeBarJson(request).getData();
    	List<Map<String,Object>> maxList = (List<Map<String, Object>>) findHomeMaxJson(request).getData();

    	if(!ObjectUtils.isNullOrEmpty(pieList)){
    		j.put("pieList", pieList);
        	j.put("barList", barList);
        	j.put("maxList", maxList);
    	}    	
    	return j;
    }
    
    @RequestMapping(value = "findHomePieJson")
    @ResponseBody
    public AjaxJson findHomePieJson(HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	String orgId = UserUtils.getUser().getOrgId();
    	//boolean flag = checkIsTender();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list = medicalHomeChartService.findHomePieJson(orgId);
    	j.setData(list);
    	return j;
    }
    
    @RequestMapping(value = "findHomeMaxJson")
    @ResponseBody
    public AjaxJson findHomeMaxJson(HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	//boolean flag = checkIsTender();
    	String orgId = UserUtils.getUser().getOrgId();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list = medicalHomeChartService.findHomeMaxJson(orgId);
    	j.setData(list);
    	return j;
    }
    
    @RequestMapping(value = "findHomeBarJson")
    @ResponseBody
    public AjaxJson findHomeBarJson(HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	//boolean flag = checkIsTender();
    	String orgId = UserUtils.getUser().getOrgId();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list = medicalHomeChartService.findHomeBarJson(orgId);
    	j.setData(list);
    	return j;
    }
    
}
