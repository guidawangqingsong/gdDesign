package cn.jjxx.modules.sys.controller;

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
import cn.jjxx.core.utils.PropertiesUtil;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.core.utils.upload.FileUploadUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.jjxx.core.common.controller.BaseBeanController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import cn.jjxx.modules.sys.entity.AppVersion;
import cn.jjxx.modules.sys.service.IAppVersionService;
import cn.jjxx.modules.sys.service.IAttachmentService;

/**   
 * @Title: App版本管理
 * @Description: App版本管理
 * @author jjxx
 * @date 2018-01-12 23:40:56
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/appversion")
@RequiresPathPermission("sys:appversion")
public class AppVersionController extends BaseBeanController<AppVersion> {
		
	private static final String baseDir = "upload/app";
	
	@Autowired
	private IAttachmentService attachmentService;
	
    @Autowired
    protected IAppVersionService appVersionService;

    public AppVersion get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return appVersionService.selectById(id);
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
	 * @Description: 查询APP版本的最新接口 .<br>
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-22 下午7:42:11.<br>
	 */
    @RequestMapping("/findAppVersion")
	@ResponseBody
	public AjaxJson findAppVersion(String type){
		AjaxJson j = new AjaxJson();
		EntityWrapper<AppVersion> wrapper = new EntityWrapper<AppVersion>();
		wrapper.eq("version_status", String.valueOf(1));
		wrapper.groupBy("type");
		wrapper.orderBy("create_date", false);
		List<AppVersion> list = appVersionService.selectList(wrapper);
		if(list.size()>0){
			j.setData(list);
		}else{
			j.setRet(AjaxJson.RET_FAIL);
		}
		return j;
	}

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<AppVersion> entityWrapper = new EntityWrapper<AppVersion>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<AppVersion> pagejson = new PageJson<AppVersion>(appVersionService.list(queryable,entityWrapper));
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
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") AppVersion appVersion, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(appVersion, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        AppVersion appVersion = get(id);
        model.addAttribute("data", appVersion);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") AppVersion appVersion, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(appVersion, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(AppVersion appVersion, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(appVersion, result)) {
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
            if (StringUtils.isEmpty(appVersion.getId())) {
                appVersionService.insert(appVersion);
            } else {
                appVersionService.insertOrUpdate(appVersion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(appVersion);
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            appVersionService.deleteById(id);
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
            appVersionService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        AppVersion appVersion = get(id);
        model.addAttribute("data", appVersion);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<AppVersion> entityWrapper = new EntityWrapper<AppVersion>(entityClass);
            valid = appVersionService.doValid(duplicateValid,entityWrapper);
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
    
    @RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson upload(HttpServletRequest request, HttpServletResponse response,String billId) throws Exception{
		response.setContentType("text/plain");
		AjaxJson ajaxJson = new AjaxJson();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultiValueMap<String, MultipartFile> fileMap = multiRequest.getMultiFileMap();
			Set<Entry<String, List<MultipartFile>>> set = fileMap.entrySet();
			Iterator<Entry<String, List<MultipartFile>>> it = set.iterator();
			String relateTable = request.getParameter("relateTable");
			String relateFeild = request.getParameter("relateFeild");
			while (it.hasNext()) {
				Entry<String, List<MultipartFile>> entry = it.next();
				List<MultipartFile> fileList = entry.getValue();			
				for(MultipartFile file:fileList){
					String url = attachmentService.uploadAndSave(request, file, billId, relateTable, relateFeild,null).getFilepath();
					AppVersion app = appVersionService.selectById(billId);
					app.setFilePath(url);
					appVersionService.insertOrUpdate(app);
				}
			}
		}
		return ajaxJson;
    }
}
