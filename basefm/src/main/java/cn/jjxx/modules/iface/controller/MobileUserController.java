package cn.jjxx.modules.iface.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jjxx.core.common.controller.BaseController;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.modules.sys.controller.AttachmentController;
import cn.jjxx.modules.sys.controller.UserController;
import cn.jjxx.modules.sys.entity.Attachment;
import cn.jjxx.modules.sys.entity.Staff;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.service.IStaffService;
import cn.jjxx.modules.sys.service.IUserService;
import cn.jjxx.modules.sys.utils.UserUtils;

/**
 * @Title: MobileUserController.java .<br>
 * @Package cn.jjxx.modules.iface.controller .<br>
 * @Description: 用户中心，获取用户相关的信息类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-12-6 下午4:43:30.<br>
 * @version V1.0.<br>
 */
@Controller
@RequestMapping("/iface/mobile/user")
public class MobileUserController extends BaseController{
	
	@Autowired
	protected IUserService userService;
	@Autowired 
	protected IStaffService staffService;
	
	/**
	 * @Description: 根据组织Id,获取当前组织下的所有职员 .<br>
	 * @param orgId 组织Id.<br>   
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-6 下午4:42:20.<br>
	 */
	@RequestMapping(value = "/staffList")
	@ResponseBody
	public AjaxJson findStaff(String orgId){
		AjaxJson j = new AjaxJson();
		List<String> orgIds = UserUtils.getOrgChilds(true, true);
		EntityWrapper<Staff> entityWrapper = new EntityWrapper<Staff>(Staff.class);
		entityWrapper.in("orgId", orgIds);
		entityWrapper.notIn("id", UserUtils.getUser().getStaffId());
		List<Staff> staffList = staffService.selectList(entityWrapper);
		j.setData(staffList);
		return j;
	};
	
	/**
	 * @Description: 根据职员Id,获取职员详细信息 .<br>
	 * @param staffId 职员Id.<br>   
	 * @return AjaxJson .<br> 
	 * @author 周恺 .<br>
	 * @date 2017-12-6 下午4:42:20.<br>
	 */
	@RequestMapping(value = "/staff")
	@ResponseBody
	public AjaxJson findStaffInfo(String staffId){
		AjaxJson j = new AjaxJson();
		Staff staff = staffService.selectStaffById(staffId);
		j.setData(staff);
		return j;
	};
	
	/**
	 * @Description: 修改用户的密码 .<br>
	 * @param oldPwd 旧密码.<br>
	 * @param newPwd 新密码.<br>
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-6 下午8:18:46.<br>
	 */
	@RequestMapping(value = "/alterPwd")
	@ResponseBody
	public AjaxJson alterPwd(String oldPwd,String newPwd){
		AjaxJson j = new AjaxJson();
		try {
			String id = UserUtils.getUser().getId();
			userService.changePassword(id,newPwd);
			j.setMsg("密码修改成功");
		} catch (Exception e) {
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("密码修改失败");
		}
		return j;
	};
	
	/**
	 * @Description: 修改头像 .<br>
	 * @param request http请求.<br>   
	 * @param response http响应.<br>   
	 * @param user 用户实体类 .<br>   
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-6 下午8:19:36.<br>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/avatar")
	@ResponseBody
	public AjaxJson avatar(HttpServletRequest request, HttpServletResponse response,User user){
		AjaxJson j = new AjaxJson();
		List<Attachment> list = (List<Attachment>) SpringContextHolder.getBean(AttachmentController.class).upload(request, response).getData();
		if(list.size()>0){
			String imgUrl = list.get(0).getFilepath();
			user.setPortrait(imgUrl);
			j = SpringContextHolder.getBean(UserController.class).avatar(user, request, response);
		}
		return j;
	}
	
	
	@RequestMapping(value = "/getUserOrg")
	@ResponseBody
	public AjaxJson getUserOrg(){
		AjaxJson j = new AjaxJson();
		j = SpringContextHolder.getBean(UserController.class).getUserOrgsMoblie();		
		return j;
	}
	
	@RequestMapping(value = "/changeUserOrg")
	@ResponseBody
	public AjaxJson changeUserOrg(String orgId,String orgName){
		AjaxJson j = new AjaxJson();
		j = SpringContextHolder.getBean(UserController.class).changeOrg(orgId, orgName);
		return j;
	}
	
	/**
	 * @Description: 获取当前用户拥有的组织的所有职员.<br>
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-19 下午3:23:39.<br>
	 */
	@RequestMapping(value = "/getStaffList")
	@ResponseBody
	public AjaxJson getStaffList(){
		AjaxJson j = new AjaxJson();
		List<String> orgs = UserUtils.getOrgChilds(true, true);
		Wrapper<Staff> wrapper = new EntityWrapper<Staff>();
		wrapper.in("org_id", orgs);
		List<Staff> list = staffService.selectList(wrapper);
		if(list.size()==0){
			j.setRet(AjaxJson.RET_FAIL);
		}
		j.setData(list);
		return j;
	}
	
}
