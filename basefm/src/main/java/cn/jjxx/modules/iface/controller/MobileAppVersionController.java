package cn.jjxx.modules.iface.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jjxx.core.common.controller.BaseController;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.modules.sys.entity.AppVersion;
import cn.jjxx.modules.sys.service.IAppVersionService;

@Controller
@RequestMapping("/iface/mobile/appversion")
public class MobileAppVersionController  extends BaseController{

	@Autowired
    protected IAppVersionService service;
	
	/**
	 * @Description: 查询APP版本的最新接口 .<br>
	 * @param type 类型（1:安卓;2：IOS）.<br>
	 * @return AjaxJson .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-22 下午7:42:11.<br>
	 */
	@RequestMapping("/findAppVersion")
	@ResponseBody
	public AjaxJson findAppVersion(String type){
		AjaxJson j = new AjaxJson();
		EntityWrapper<AppVersion> wrapper = new EntityWrapper<AppVersion>();
		wrapper.eq("type", type);
		wrapper.eq("version_status", String.valueOf(1));
		wrapper.orderBy("create_date", false);
		List<AppVersion> list = service.selectList(wrapper);
		if(list.size()>0){
			j.setData(list.get(0));
		}else{
			j.setRet(AjaxJson.RET_FAIL);
		}
		return j;
	}
}
