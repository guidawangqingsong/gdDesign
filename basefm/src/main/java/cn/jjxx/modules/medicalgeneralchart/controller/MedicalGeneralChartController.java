package cn.jjxx.modules.medicalgeneralchart.controller;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jjxx.modules.medicalgeneralchart.entity.MedicalGeneralChart;
import cn.jjxx.modules.medicalgeneralchart.service.IMedicalGeneralChartService;
import cn.jjxx.modules.sys.entity.Staff;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.service.IStaffService;
import cn.jjxx.modules.sys.utils.UserUtils;

/**   
 * @Title: 综合分析
 * @Description: 综合分析
 * @author jjxx.wangqingsong
 * @date 2018-04-11 16:02:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/medicalgeneralchart/medicalgeneralchart")
@RequiresPathPermission("medicalgeneralchart:medicalgeneralchart")
public class MedicalGeneralChartController extends BaseBeanController<MedicalGeneralChart> {

    @Autowired
    protected IMedicalGeneralChartService medicalGeneralChartService;
    @Autowired
    protected IStaffService staffService;

    public MedicalGeneralChart get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return medicalGeneralChartService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("datachart");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<MedicalGeneralChart> entityWrapper = new EntityWrapper<MedicalGeneralChart>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        
        //通过组织查询,如果orgId 不为空，拼接查询条件，通过orgId来查找
	    //判断如果该用户是某组织下的，只能查看自己所属组织下的日志。
	    String orgId = request.getParameter("orgId");
        if(!StringUtils.isEmpty(orgId)){
        	entityWrapper.eq("t.org_id", orgId);
        }
        
        //获取没被删除的数据
        String delFlag = request.getParameter("delFlag");
        entityWrapper.eq("t.del_flag", 0);
        
        //根据创建人查询
    	String creatBy = request.getParameter("createByName");//获取页面的要查询的信息
    	if(!StringUtils.isEmpty(creatBy)){
    		entityWrapper.eq("u.realname", creatBy);//根据输入的用户名查询日志
    	}
        
        //设置时间查询条件
  		String[] arrayTime = request.getParameterValues("createDate"); 
  		Map<String,Object> timeMap =  getStartDateAndEndTime(arrayTime);
		if(!ObjectUtils.isNullOrEmpty(timeMap)){
			entityWrapper.between("t.create_date", timeMap.get("startTime"), timeMap.get("endTime"));
		}
        
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<MedicalGeneralChart> pagejson = new PageJson<MedicalGeneralChart>(medicalGeneralChartService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    /**
	 * 从前端传入的 
	 * @param timeArray 前端获取的时间数组格式，如：request.getParameterValues("applyDate"); 
	 * @return Map<String,Object> 返回Map，Key（起始时间）为startTime；Key2(终止时间)为：endTime
	 */
	public static Map<String,Object> getStartDateAndEndTime(String[] timeArray){
		if(ObjectUtils.isNullOrEmpty(timeArray)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> timeMap = new HashMap<String,Object>();
		String timeStr = timeArray[0];
		int index = timeStr.indexOf(",");
		String startTime = "";
		String endTime = "";
		if(index!=-1){//用户输入的是开始时间或者 两个时间都选了
			String[] times = timeStr.split(",");
			startTime = times[0] +" 00:00:00";
			if(times.length==2){
				endTime = times[1] + " 23:59:59";
			}else{
				endTime = sdf.format(new Date())+ " 23:59:59";
			}
			System.out.println(times.length);
		}else{
			startTime = "1970-01-01 00:00:00";
			endTime = timeStr + " 23:59:59";
		}
		timeMap.put("startTime", startTime);
		timeMap.put("endTime",  endTime);
		return timeMap;
	}
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	User user = UserUtils.getUser();
    	MedicalGeneralChart generalChart=new MedicalGeneralChart();
    	
    	if (!model.containsAttribute("data")) {
    		generalChart.setStaffId(user.getStaffId());
    		Staff staff = staffService.selectById(user.getStaffId());
    		if(!ObjectUtils.isNullOrEmpty(staff)){			//判断staff实体有没有查询出来
    			generalChart.setStaffNumber(staff.getCode());	//获取staff的编号
        	}
            model.addAttribute("data", generalChart);
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") MedicalGeneralChart medicalGeneralChart, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalGeneralChart, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        MedicalGeneralChart medicalGeneralChart = get(id);
        model.addAttribute("data", medicalGeneralChart);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") MedicalGeneralChart medicalGeneralChart, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalGeneralChart, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(MedicalGeneralChart medicalGeneralChart, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(medicalGeneralChart, result)) {
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
            if (StringUtils.isEmpty(medicalGeneralChart.getId())) {
                medicalGeneralChartService.insert(medicalGeneralChart);
            } else {
                medicalGeneralChartService.insertOrUpdate(medicalGeneralChart);
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
            medicalGeneralChartService.deleteById(id);
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
            medicalGeneralChartService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        MedicalGeneralChart medicalGeneralChart = get(id);
        model.addAttribute("data", medicalGeneralChart);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<MedicalGeneralChart> entityWrapper = new EntityWrapper<MedicalGeneralChart>(entityClass);
            valid = medicalGeneralChartService.doValid(duplicateValid,entityWrapper);
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
    @RequestMapping(value = "findGeneralCharts")
    @ResponseBody
	public AjaxJson findGeneralCharts(HttpServletRequest request,HttpServletResponse response){
    	AjaxJson j = new AjaxJson();
    	List<Map<String,Object>> hgradeList = (List<Map<String, Object>>) selectHGradeJson(request).getData();
    	List<Map<String,Object>> lgradeList = (List<Map<String, Object>>) selectLGradeJson(request).getData();
    	List<Map<String,Object>> hpredictList = (List<Map<String, Object>>) selectHPredictJson(request).getData();
    	List<Map<String,Object>> lpredictList = (List<Map<String, Object>>) selectLPredictJson(request).getData();

    	j.put("hgradeList", hgradeList);
    	j.put("lgradearList", lgradeList);
    	j.put("hpredictList", hpredictList);
    	j.put("lpredictList", lpredictList);
    	return j;
    }
    
    @RequestMapping(value = "selectHGradeJson")
    @ResponseBody
    public AjaxJson selectHGradeJson(HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	String orgId = UserUtils.getUser().getOrgId();
    	//boolean flag = checkIsTender();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list = medicalGeneralChartService.selectHGradeJson(orgId);
    	j.setData(list);
    	return j;
    }
    
    @RequestMapping(value = "selectLGradeJson")
    @ResponseBody
    public AjaxJson selectLGradeJson(HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	//boolean flag = checkIsTender();
    	String orgId = UserUtils.getUser().getOrgId();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list = medicalGeneralChartService.selectLGradeJson(orgId);
    	j.setData(list);
    	return j;
    }
    
    @RequestMapping(value = "selectHPredictJson")
    @ResponseBody
    public AjaxJson selectHPredictJson(HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	//boolean flag = checkIsTender();
    	String orgId = UserUtils.getUser().getOrgId();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list = medicalGeneralChartService.selectHPredictJson(orgId);
    	j.setData(list);
    	return j;
    }
    
    @RequestMapping(value = "selectLPredictJson")
    @ResponseBody
    public AjaxJson selectLPredictJson(HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	//boolean flag = checkIsTender();
    	String orgId = UserUtils.getUser().getOrgId();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list = medicalGeneralChartService.selectLPredictJson(orgId);
    	j.setData(list);
    	return j;
    }
}
