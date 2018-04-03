package cn.jjxx.modules.sys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jjxx.core.common.controller.BaseBeanController;
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
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Staff;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.service.IStaffService;
import cn.jjxx.modules.sys.service.IUserService;
import cn.jjxx.modules.sys.utils.UserUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

/**   
 * @Title: 职员管理
 * @Description: 职员管理
 * @author jjxx
 * @date 2017-11-09 18:22:19
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/staff")
@RequiresPathPermission("sys:staff")
public class StaffController extends BaseBeanController<Staff> {

    @Autowired
    protected IStaffService staffService;
    @Autowired
    protected IUserService userService;

    public Staff get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return staffService.selectStaffById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }
    
    /**
     * 
     * @Description: 返回所有没有被用户匹配的职员页面 
     * @param @param model
     * @param @param request
     * @param @param response
     * @param @return .  
     * @return String .
     * @author 周恺 
     * @date 2017年11月20日 下午2:20:41
     */   
    @RequestMapping(value = "listStaff", method = { RequestMethod.GET})
    public String listStaff(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("listStaff");
    }
    
    /**
     * 
     * @Description: 返回所有职员页面 
     * @param @param model
     * @param @param request
     * @param @param response
     * @param @return .  
     * @return String .
     * @author 周恺 
     * @date 2017年11月20日 下午2:20:41
     */
    @RequestMapping(value = "listAllStaff", method = { RequestMethod.GET})
    public String listAllStaff(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("listAllStaff");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<Staff> entityWrapper = new EntityWrapper<Staff>(entityClass);
        entityWrapper.setTableAlias("t.");
        propertyPreFilterable.addQueryProperty("id");
        String isUser = request.getParameter("isUser");
        if(!StringUtils.isEmpty(isUser)){//判断如果是用户关联职员，只显示没有绑定的职员
        	entityWrapper.addFilter("(t.user_id = '' OR t.user_id IS NULL )");
        	//entityWrapper.eq("(","userId", "");
        	//entityWrapper.or().isNull("userId");
        }
        if(UserUtils.getUser().getAdminType().equals(User.ADMIN_NORMAL)){
        	entityWrapper.in("t.org_id", UserUtils.getOrgChilds(true, true));
        }
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<Staff> pagejson = new PageJson<Staff>(staffService.list(queryable,entityWrapper));
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
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") Staff staff, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(staff, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) { 	
        Staff staff = get(id);
        model.addAttribute("data", staff);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") Staff staff, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(staff, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(Staff staff, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(staff, result)) {
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
        	if("".equals(staff.getBirthday())){
        		staff.setBirthday(null);
        	}
            if (StringUtils.isEmpty(staff.getId())) {
                staffService.insert(staff);
            } else {
                staffService.insertOrUpdate(staff);
                //修改用户关联职员的电话和邮箱
                if(StringUtils.isNotEmpty(staff.getUserId())){
                	User user = userService.selectById(staff.getUserId());
                	if(!ObjectUtils.isNullOrEmpty(user)){
                		user.setEmail(staff.getEmail());
                    	user.setRealname(staff.getName());
                    	user.setPhone(staff.getMoblie());
                    	user.setStaffName(staff.getName());
                    	userService.insertOrUpdate(user);
                	}
                }
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
        	EntityWrapper<User> entityWrapper = new EntityWrapper<User>(User.class);
        	entityWrapper.eq("staffId",id);
        	List<User> userList = userService.selectList(entityWrapper);
        	if(!ObjectUtils.isNullOrEmpty(userList)){
        		 ajaxJson.fail("有关联用户信息无法删除");
        		 return ajaxJson;
        	}  	
            staffService.deleteById(id);

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
            for (String id : idList) {
            	EntityWrapper<User> entityWrapper = new EntityWrapper<User>(User.class);
            	entityWrapper.eq("staffId",id);
            	List<User> userList = userService.selectList(entityWrapper);
            	if(!ObjectUtils.isNullOrEmpty(userList)){
            		 ajaxJson.fail("有关联用户信息无法删除");
            		 return ajaxJson;
            	}  	
			}
            staffService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        Staff staff = get(id);
        model.addAttribute("data", staff);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<Staff> entityWrapper = new EntityWrapper<Staff>(entityClass);
            valid = staffService.doValid(duplicateValid,entityWrapper);
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
