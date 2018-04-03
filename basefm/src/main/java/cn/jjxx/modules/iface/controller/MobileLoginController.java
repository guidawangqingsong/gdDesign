package cn.jjxx.modules.iface.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jjxx.core.common.controller.BaseController;
import cn.jjxx.core.model.AjaxJson;

/**
 * @Title: MobileLoginController.java .<br>
 * @Package cn.jjxx.modules.iface.controller .<br>
 * @Description: 用户登录管理系统类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-12-9 下午4:21:52.<br>
 * @version V1.0.<br>
 */
@Controller
@RequestMapping("/iface/mobile")
public class MobileLoginController  extends BaseController{

	/**
	 * @Description: 手机端退出登录 .<br>
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-9 下午4:22:13.<br>
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public AjaxJson logout(){
		AjaxJson j = new AjaxJson();
		System.out.println("退出");
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject != null && subject.isAuthenticated()) {
				subject.logout();
			}
			j.setMsg("退出登录成功");
		}catch(Exception e){
			j.setRet(AjaxJson.RET_FAIL);
			j.setMsg("退出登录失败!<br />原因:" + e.getMessage());
		}
		return j;
	} 
}
