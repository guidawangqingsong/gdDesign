package cn.jjxx.modules.oa.controller;

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

import net.sf.jsqlparser.statement.alter.Alter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import cn.jjxx.core.common.controller.BaseBeanController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jjxx.modules.oa.entity.OaWorkLog;
import cn.jjxx.modules.oa.service.IOaWorkLogService;
import cn.jjxx.modules.sys.controller.StaffController;
import cn.jjxx.modules.sys.entity.Attachment;
import cn.jjxx.modules.sys.entity.Staff;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.service.IAttachmentService;
import cn.jjxx.modules.sys.service.IStaffService;
import cn.jjxx.modules.sys.service.IUserService;
import cn.jjxx.modules.sys.service.impl.StaffServiceImpl;
import cn.jjxx.modules.sys.utils.UserUtils;

/**   
 * @Title: 费用日志管理
 * @Description: 费用日志管理
 * @author grace
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/oa/oaworklog")//将方法映射到一些请求上，以便让该方法处理那些请求
@RequiresPathPermission("oa:oaworklog")
public class OaWorkLogController extends BaseBeanController<OaWorkLog> {

    @Autowired
    //当使用@Autowired注解时，spring会将我们已经写好的实体类new到我们这个注解下面所需要的实体上面，那么new的功能就是spring直接帮我们实现了。
    protected IOaWorkLogService oaWorkLogService;
    @Autowired
    protected IUserService userService;
    @Autowired
    protected IStaffService staffService;
    @Autowired
    protected IAttachmentService attachmentService;
    
    public OaWorkLog get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return oaWorkLogService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {//request将前端页面有name的字段查询出来并显示到list上
        return display("list");
    }
    
    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    //页面加载时执行
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<OaWorkLog> entityWrapper = new EntityWrapper<OaWorkLog>(entityClass);//因为EntityWrapper是一个范型类，所以需要传入一个实体类型*OaWorkLog
        propertyPreFilterable.addQueryProperty("id");
      
        //首先拿到所有同一组织下的日志
        String logState = request.getParameter("logState");
        // entityWrapper.eq("t.log_state", 0); 
        
        //再根据自己的ID拿到自己不公开的日志
        //获取当前系统登入人的ID,只能查看自己的日志
        User user =  UserUtils.getUser();
        String userId = user.getId();
        if(!user.getUsername().equals("admin")){ //判断如果登陆着为管理员的话就把所有日志都显示出来，否则只能看到自己的日志
        	entityWrapper.eq("t.create_by", userId);
        }
        //通过组织查询,如果orgId 不为空，拼接查询条件，通过orgId来查找
        //判断如果该用户是某组织下的，则只显示没有绑定组织的日志,只能查看自己所属组织下的日志。
        String orgId = request.getParameter("orgId");
        entityWrapper.eq("t.org_id", orgId); 
        
        //获取没被删除的数据
        String delFlag = request.getParameter("delFlag");
        entityWrapper.eq("t.del_flag", 0);
     
        //先获取前端的 日志主题 参数
        String logTheme = request.getParameter("logTheme");
        if(!StringUtils.isEmpty(logTheme)){
        	entityWrapper.like("t.log_theme", logTheme);//模糊查询
        }
       
        //获取前台 日志类型
        String logType = request.getParameter("logType");
    	if(!StringUtils.isEmpty(logType)){
    		entityWrapper.eq("t.log_type", logType);
    	}
        
    	//根据创建人查询日志
    	String creatBy = request.getParameter("createByName");//获取页面的要查询的信息
    	System.out.println(creatBy+"createby");
    	if(!StringUtils.isEmpty(creatBy)){
    		entityWrapper.eq("u.realname", creatBy);//根据输入的用户名查询日志
    	}
    	
    	//设置时间查询条件
		String[] arrayTime = request.getParameterValues("logTime"); 
		Map<String,Object> timeMap =  getStartDateAndEndTime(arrayTime);
		if(!ObjectUtils.isNullOrEmpty(timeMap)){
			entityWrapper.between("t.log_time", timeMap.get("startTime"), timeMap.get("endTime"));
		}
    	
        //设置日志主题查询条件
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<OaWorkLog> pagejson = new PageJson<OaWorkLog>(oaWorkLogService.list(queryable,entityWrapper));
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

    /**
     * @descrption 跳转值新增个人工作日志界面，且自动带入职员编号.<br>
     * @param model 模型实体类.<br>
     * @param request http请求.<br>
     * @param response http相应.<br>
     * @author jjxx.wangqingsong .<br>
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	//从缓存里面取出用户的信息   
    	User user = UserUtils.getUser();
    	OaWorkLog oaWorkLog = new OaWorkLog();
        if (!model.containsAttribute("data")) {				//使用前端通过data绑定的数据。
        	oaWorkLog.setStaffId(user.getStaffId());		//因为user表与staff表有关联，所以可以通过user来获取staffid
        	Staff staff = staffService.selectById(user.getStaffId());//通过staffid来查询staff的所有信息
        	Attachment attachment = new Attachment();		//在新增日志时候，添加附件到数据库中的附件表中去
        	if(!ObjectUtils.isNullOrEmpty(staff)){			//判断staff实体有没有查询出来
        		oaWorkLog.setStaffNumber(staff.getCode());	//获取staff的编号
        	}
            model.addAttribute("data", oaWorkLog);			//将前端的页面添加到实体对象中去
        }
        return display("edit");		//渲染到edit页面
    }

    /**
     * @descrption 执行新增，保存个人工作日志.<br>
     * @param oaWorkLog 个人工作日志实体类，接收对应的字段值.<br>
     * @param request http请求.<br>
     * @param response http相应.<br>
     * @author wyt .<br>
     * @date 2018/2/28 .<br>
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") OaWorkLog oaWorkLog, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(oaWorkLog, request, response, result);
    }

    /**
     * @descrption 更新日志信息.<br>
     * @param model 模型实体类.<br>
     * @param request http请求.<br>
     * @param response http相应.<br>
     * @author jjxx.wangqingsong.<br>
     */
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        OaWorkLog oaWorkLog = get(id);
        model.addAttribute("data", oaWorkLog);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") OaWorkLog oaWorkLog, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(oaWorkLog, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    //这个保存可以传入一个实体对象，也可以传入自定义的属性对象，传入实体对象的原因是简便代码，以免需要传入多个参数不方便操作
    public AjaxJson doSave(OaWorkLog oaWorkLog, HttpServletRequest request, HttpServletResponse response,/*String orgId,*/
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        
        //ajaxJson.success("保存成功");
        if (hasError(oaWorkLog, result)) {
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
        	//判断日志id是否为空并且日志主题也不能为空，若为空，则执行插入操作。不为空则更新操作。
            if (StringUtils.isEmpty(oaWorkLog.getId())&&StringUtils.isNotEmpty(oaWorkLog.getLogTheme())) {
	    		//System.out.print(oaWorkLog.getLogTheme()+"theme");
	    		oaWorkLogService.insert(oaWorkLog);
	    		ajaxJson.success("保存成功");
            	
            } else if(StringUtils.isNotEmpty(oaWorkLog.getId())) {
                oaWorkLogService.insertOrUpdate(oaWorkLog);
                //System.out.print(oaWorkLog.getLogAttach()+"dfsff");
            }
            else{
            	ajaxJson.fail("日志还没填写完整！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());                                                                                                                                                               
        }
        ajaxJson.setData(oaWorkLog);
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable(value="id") String id) {//单条删除
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            EntityWrapper<OaWorkLog> entityWrapper = new EntityWrapper<OaWorkLog>(OaWorkLog.class);
        	entityWrapper.eq("id",id);
        	List<OaWorkLog> userList = oaWorkLogService.selectList(entityWrapper);
        	if(!ObjectUtils.isNullOrEmpty(userList)){
        		 ajaxJson.fail("有关联用户信息无法删除");
        		 return ajaxJson;
        	}  	
        	oaWorkLogService.deleteById(id);
            
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    
    /**
     * @descrption 执行伪删除操作.<br>
     * @param ids 删除的id.<br>
     * @author jjxx.wangqingsong .<br>
     */
    @RequestMapping(value = "batchDelete", method = { RequestMethod.GET, RequestMethod.POST })//批量删除
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);//选中要删除的id列表      
            oaWorkLogService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        OaWorkLog oaWorkLog = get(id);
        model.addAttribute("data", oaWorkLog);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<OaWorkLog> entityWrapper = new EntityWrapper<OaWorkLog>(entityClass);
            valid = oaWorkLogService.doValid(duplicateValid,entityWrapper);
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
     * @descrption 跳转到查看（file）附件界面.<br>
     * @param model 模型实体类.<br>
     * @param request http请求.<br>
     * @param response http相应.<br>
     * @author jjxx.wangqingsong .<br>
     */
    @RequestMapping(value = "{id}/file", method = RequestMethod.GET)
    public String file(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        OaWorkLog oaWorkLog = get(id);
        model.addAttribute("data", oaWorkLog);
        return display("file");
    }
    
}
