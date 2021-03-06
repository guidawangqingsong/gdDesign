package cn.jjxx.modules.ms.controller;

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

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.framework.superutil.thirdparty.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.jjxx.core.common.controller.BaseBeanController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import cn.jjxx.modules.common.bean.UploadExcel;
import cn.jjxx.modules.ms.entity.MedicalSample;
import cn.jjxx.modules.ms.service.IMedicalSampleService;
import cn.jjxx.modules.sys.utils.UserUtils;

/**   
 * @Title: MedicalSample
 * @Description: MedicalSample
 * @author jjxx.wangqingsong
 * @date 2018-04-04 17:59:20
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/ms/medicalsample")
@RequiresPathPermission("ms:medicalsample")
public class MedicalSampleController extends BaseBeanController<MedicalSample> {

    @Autowired
    protected IMedicalSampleService medicalSampleService;

    public MedicalSample get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return medicalSampleService.selectById(id);
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
        EntityWrapper<MedicalSample> entityWrapper = new EntityWrapper<MedicalSample>(entityClass);
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
        
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        
        PageJson<MedicalSample> pagejson = new PageJson<MedicalSample>(medicalSampleService.list(queryable,entityWrapper));
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
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") MedicalSample medicalSample, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalSample, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        MedicalSample medicalSample = get(id);
        model.addAttribute("data", medicalSample);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") MedicalSample medicalSample, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(medicalSample, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(MedicalSample medicalSample, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(medicalSample, result)) {
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
            if (StringUtils.isEmpty(medicalSample.getId())) {
                medicalSampleService.insert(medicalSample);
            } else {
                medicalSampleService.insertOrUpdate(medicalSample);
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
            medicalSampleService.deleteById(id);
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
            medicalSampleService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        MedicalSample medicalSample = get(id);
        model.addAttribute("data", medicalSample);
        return display("edit");
    }
    
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxJson view(Model model, @PathVariable("id") String id, MedicalSample medicalSample, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
    	AjaxJson ajaxJson = new AjaxJson();
    	EntityWrapper<MedicalSample> entityWrapper = new EntityWrapper<MedicalSample>(entityClass);
    	medicalSampleService.selectById(entityWrapper.eq("id", id));
        return ajaxJson;
    }
    
    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<MedicalSample> entityWrapper = new EntityWrapper<MedicalSample>(entityClass);
            valid = medicalSampleService.doValid(duplicateValid,entityWrapper);
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
    
    @RequestMapping(value = "excelUpload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson excelUpload(Model model,UploadExcel excel,HttpServletRequest request, HttpServletResponse response) {
    	AjaxJson j = new AjaxJson();
    	//设置上传文件类型为文本
    	response.setContentType("text/plain");
    	//创建一个通用的多部分解析器. 
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			//转换成多部分request 
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//定义一个迭代器，用于存放文件的名称
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				try {
					MultipartFile file = multiRequest.getFile(ite.next());
					CommonsMultipartFile cFile = (CommonsMultipartFile) file;
			        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
			        InputStream inputStream = fileItem.getInputStream();
			        
			        //获取对应得组织号
			        String orgId = request.getParameter("nodeId");
			        //得到Excel导入的单元列表
					List<MedicalSample> medicalSampleList = new ExcelUtils<MedicalSample>(new MedicalSample()).readFromFile(null, inputStream);
					for(MedicalSample oneAccount : medicalSampleList){
						//设置组织id
						oneAccount.setOrgId(orgId);
					}
					medicalSampleService.insertBatch(medicalSampleList);
					j.setMsg("文件上传成功！");
				}catch (Exception e) {
					e.printStackTrace(); 
					j.setMsg("文件上传失败!");
					break;
				}
			}
		}
		return j;
    }

    @RequestMapping(value = "exportExcelModel", method = RequestMethod.GET)
    public void exportExcelModel(HttpServletRequest request, HttpServletResponse response) {
    	ExcelUtils.setIsExportTemplate(true);
    	ExcelUtils.setFirstTitle("医疗费用信息样本");
    	ExcelUtils.setSecTitle("导出人："+UserUtils.getUser().getRealname());
    	ExcelUtils.downloadXlsx(response, null, MedicalSample.class, null, "医疗费用信息样本");
    }
    
}
