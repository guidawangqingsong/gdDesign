package cn.jjxx.modules.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.JsonObject;

import cn.jjxx.core.common.controller.BaseController;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.core.utils.MessageUtils;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jjxx.core.utils.upload.exception.InvalidExtensionException;
import cn.jjxx.modules.sys.entity.Attachment;
import cn.jjxx.modules.sys.service.IAttachmentService;

@Controller
@RequestMapping("${admin.url.prefix}/sys/attachment")
@RequiresPathPermission("sys:attachment")
public class AttachmentController extends BaseController {

	@Autowired
	private IAttachmentService attachmentService;

	@RequiresMethodPermissions("list")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
		return display("list");
	}

	@RequestMapping(value = "imgview", method = RequestMethod.GET)
    public String imgview(Model model, HttpServletRequest request, HttpServletResponse response) {
    	return display("imgView");
    }
	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxList", method = {RequestMethod.GET,RequestMethod.POST})
	private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		propertyPreFilterable.addQueryProperty("id");
		// 预处理
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, Attachment.class);
		SerializeFilter filter = propertyPreFilterable.constructFilter(Attachment.class);
		PageJson<Attachment> pagejson = new PageJson<Attachment>(attachmentService.list(queryable));
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}

	/**
	 * @title: ajaxUpload
	 * @description: 文件上传
	 * @param request
	 * @param response
	 * @param files
	 * @return
	 * @return: AjaxUploadResponse
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson upload(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		AjaxJson ajaxJson = new AjaxJson();
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultiValueMap<String, MultipartFile> fileMap = multiRequest.getMultiFileMap();
			Set<Entry<String, List<MultipartFile>>> set = fileMap.entrySet();
			Iterator<Entry<String, List<MultipartFile>>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, List<MultipartFile>> entry = it.next();
				List<MultipartFile> fileList = entry.getValue();			
				for(MultipartFile file:fileList){	
					try {
						Attachment attachment = attachmentService.upload(request, file);
						attachmentList.add(attachment);
						continue;
					} catch (IOException e) {
						ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
						continue;
					} catch (InvalidExtensionException e) {
						ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
						continue;
					} catch (FileUploadBase.FileSizeLimitExceededException e) {
						ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
						continue;
					} catch (FileNameLengthLimitExceededException e) {
						ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
						continue;
					}
				}
			}
		}
		ajaxJson.setData(attachmentList);
		return ajaxJson;
	}

	/**
	 * 
	 * @title: ajaxUpload
	 * @description: 文件上传
	 * @param request
	 * @param response
	 * @param files
	 * @return
	 * @return: AjaxUploadResponse
	 */
	@RequestMapping(value = "uploadSimditor", method = RequestMethod.POST)
	@ResponseBody
	public void uploadSimditor(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		AjaxJson ajaxJson = new AjaxJson();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Map<String, Object> data = new HashMap<String, Object>();
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				try {
					Attachment attachment = attachmentService.upload(request, file);
					attachmentList.add(attachment);
					continue;
				} catch (IOException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				} catch (InvalidExtensionException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				} catch (FileUploadBase.FileSizeLimitExceededException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				} catch (FileNameLengthLimitExceededException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				}
			}
			String ctxPath = request.getContextPath();
			ajaxJson.setData(attachmentList);
			data.put("success", Boolean.TRUE);
			data.put("msg", MessageUtils.getMessage("upload.server.error"));
			data.put("file_path", ctxPath + "/" + attachmentList.get(0).getFilepath());
		} else {
			data.put("success", Boolean.FALSE);
			data.put("msg", MessageUtils.getMessage("upload.server.error"));
		}
		StringUtils.printJson(response, data);
	}

	@RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delete(@PathVariable("id") String id) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("删除成功");
		try {
			attachmentService.deleteById(id);
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
			attachmentService.deleteBatchIds(idList);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("删除失败");
		}
		return ajaxJson;
	}

	/**
	 * 
	 * @title: ajaxUpload
	 * @description: 文件上传
	 * @param request
	 * @param response
	 * @param files
	 * @return
	 * @return: AjaxUploadResponse
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson list(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		AjaxJson ajaxJson = new AjaxJson();
		String saveType = request.getParameter("saveType");
		if (saveType.equals("id")) {
			String id = request.getParameter("id");
			List<Attachment> list = attachmentService
					.selectList(new EntityWrapper<Attachment>().in("id", id.split(",")));
			ajaxJson.setData(list);
		}else if(saveType.equals("billId")){
			String billId = request.getParameter("billId");
			List<Attachment> list = attachmentService
					.selectList(new EntityWrapper<Attachment>().eq("bill_id", billId));
			ajaxJson.setData(list);
		} else {
			String filepath = request.getParameter("url");
			List<Attachment> list = attachmentService
					.selectList(new EntityWrapper<Attachment>().in("filepath", filepath.split(",")));
			ajaxJson.setData(list);
		}

		return ajaxJson;
	}
}
