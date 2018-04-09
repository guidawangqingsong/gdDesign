package cn.jjxx.modules.medicalinfo.controller;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jjxx.modules.medicalinfo.entity.MainMedicalInfo;
import cn.jjxx.modules.medicalinfo.service.IMainMedicalInfoService;
import cn.jjxx.modules.sys.entity.Staff;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.service.IStaffService;
import cn.jjxx.modules.sys.utils.UserUtils;

/**   
 * @Title: 医疗费用总表
 * @Description: 医疗费用总表
 * @author jjxx.wangqingsong
 * @date 2018-04-08 14:33:15
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/medicalinfo/mainmedicalinfo")
@RequiresPathPermission("medicalinfo:mainmedicalinfo")
public class MainMedicalInfoController extends BaseBeanController<MainMedicalInfo> {

    @Autowired
    protected IMainMedicalInfoService mainMedicalInfoService;
    @Autowired
    protected IStaffService staffService;
    
    public MainMedicalInfo get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return mainMedicalInfoService.selectById(id);
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
        EntityWrapper<MainMedicalInfo> entityWrapper = new EntityWrapper<MainMedicalInfo>(entityClass);
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
        
        //设置时间查询条件
  		String time = request.getParameter("createDate"); 
  		Map<String,Object> timeMap =  getStartDateAndEndTime(time);
  		if(!ObjectUtils.isNullOrEmpty(timeMap)){
  			entityWrapper.eq("t.create_date", timeMap.get("createTime"));
  		}
  		
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<MainMedicalInfo> pagejson = new PageJson<MainMedicalInfo>(mainMedicalInfoService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    /**
	 * 从前端传入的 
	 * @param timeArray 前端获取的时间数组格式，如：request.getParameterValues("applyDate"); 
	 * @return Map<String,Object> 返回Map，Key（起始时间）为startTime；Key2(终止时间)为：endTime
	 */
	public static Map<String,Object> getStartDateAndEndTime(String time){
		if(ObjectUtils.isNullOrEmpty(time)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> timeMap = new HashMap<String,Object>();
		String createTime = "";
		createTime = sdf.format(new Date());
		timeMap.put("createTime", createTime);
		return timeMap;
	}
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	User user = UserUtils.getUser();
    	MainMedicalInfo medicalInfo=new MainMedicalInfo();
        if (!model.containsAttribute("data")) {
        	medicalInfo.setStaffId(user.getStaffId());		//因为user表与staff表有关联，所以可以通过user来获取staffid
        	Staff staff = staffService.selectById(user.getStaffId());//通过staffid来查询staff的所有信息
        	
            if(!ObjectUtils.isNullOrEmpty(staff)){			//判断staff实体有没有查询出来
            	medicalInfo.setStaffNumber(staff.getCode());	//获取staff的编号
        	}
            model.addAttribute("data", medicalInfo);
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") MainMedicalInfo mainMedicalInfo, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(mainMedicalInfo, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        MainMedicalInfo mainMedicalInfo = get(id);
        model.addAttribute("data", mainMedicalInfo);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") MainMedicalInfo mainMedicalInfo, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(mainMedicalInfo, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(MainMedicalInfo mainMedicalInfo, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(mainMedicalInfo, result)) {
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
            if (StringUtils.isEmpty(mainMedicalInfo.getId())) {
                mainMedicalInfoService.insert(mainMedicalInfo);
            } else {
                mainMedicalInfoService.insertOrUpdate(mainMedicalInfo);
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
            mainMedicalInfoService.deleteById(id);
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
            mainMedicalInfoService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        MainMedicalInfo mainMedicalInfo = get(id);
        model.addAttribute("data", mainMedicalInfo);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<MainMedicalInfo> entityWrapper = new EntityWrapper<MainMedicalInfo>(entityClass);
            valid = mainMedicalInfoService.doValid(duplicateValid,entityWrapper);
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
