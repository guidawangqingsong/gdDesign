package cn.jjxx.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.jjxx.core.common.controller.BaseController;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.common.bean.UploadExcel;

/**
 * @Title:
 * @Description: 公用控制器
 * @author www.jjxxkj.cn
 * @date 2017-02-08 22:27:30
 * @version V1.0
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/common")
public class CommonController extends BaseController {

	/**
	 * 图表资源
	 * 
	 * @return
	 */
	@RequestMapping(value = "icons", method = RequestMethod.GET)
	public String list(Model model) {
		return display("icons");
	}

	@RequestMapping(value = "treeselect", method = RequestMethod.GET)
	public String treeselect(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getParameter("url")); // 树结构数据URL
		String multiselect = request.getParameter("multiselect");
		String isLeaf = request.getParameter("isLeaf");	
		String chkboxType = request.getParameter("chkboxType");
		model.addAttribute("chkboxType", chkboxType); // 父子关联 
		if (StringUtils.isEmpty(multiselect)) {
			multiselect = Boolean.TRUE + "";
		}
		model.addAttribute("multiselect", multiselect); // 是否多选
		String selectNodes = request.getParameter("selectNodes");
		model.addAttribute("selectNodes", selectNodes);// 默认值
		model.addAttribute("isLeaf", isLeaf);// true表示只能选择叶子节点 
		return display("treeselect");
	}
	
	/**
	 * @Description: excel上传跳转界面 .<br>
	 * @param model 模型层实体类.<br>   
	 * @param excel excel上传实体类.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-12-1 上午11:58:59.<br>
	 */
	@RequestMapping(value = "excelUpload", method = RequestMethod.GET)
	public String excelUpload(Model model,UploadExcel excel) {
		model.addAttribute("data", excel);
		return display("excelUpload");
	}
	
}
