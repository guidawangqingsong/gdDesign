package cn.jjxx.modules.sys.controller;

import cn.jjxx.core.common.controller.BaseCRUDController;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.query.annotation.PageableDefaults;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Menu;
import cn.jjxx.modules.sys.entity.Role;
import cn.jjxx.modules.sys.entity.RoleMenu;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.service.IMenuService;
import cn.jjxx.modules.sys.service.IRoleMenuService;
import cn.jjxx.modules.sys.service.IUserOrgMenuService;
import cn.jjxx.modules.sys.service.IUserOrgRoleService;
import cn.jjxx.modules.sys.utils.UserUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;

@Controller
@RequestMapping("${admin.url.prefix}/sys/role")
@RequiresPathPermission("sys:role")
public class RoleController extends BaseCRUDController<Role, String> {
	
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IRoleMenuService roleMenuService;
	@Autowired
	private IUserOrgRoleService userOrgRoleService;
	@Autowired
	private IUserOrgMenuService userOrgMenuService;

	/**
	 * 
	 * @Description: 弹出角色配置菜单页面 
	 * @param @param role
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月29日 下午7:20:42
	 */
	@RequestMapping(value = "authMenu", method = RequestMethod.GET)
	public String authMenu(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		//查询当前人员的菜单
		List<Menu> menus = findUserMenus();
		//查询当前角色的菜单
		List<Menu> selectMenus = menuService.findMenuByRoleId(role.getId());
		model.addAttribute("menus", JSONArray.toJSON(menus));
		model.addAttribute("selectMenus", JSONArray.toJSON(selectMenus));
		model.addAttribute("data", role);
		return display("authMenu");
	}
	 
	/**
	 * @Description: 获取当前人员的角色菜单 .<br>
	 * @return List<Menu> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 上午10:46:57.<br>
	 */
	private List<Menu> findUserMenus(){
		EntityWrapper<Menu> entityWrapper = new EntityWrapper<Menu>(Menu.class);
		entityWrapper.orderBy("sort", false);
		User user = UserUtils.getUser();
		List<Menu> menus = new ArrayList<Menu>();
		if(user.getAdminType().equals(User.ADMIN_VIP)){
			menus = menuService.selectTreeList(entityWrapper);
		}else{
			//查询当前用户所在组织拥有的角色
			List<String> roleMenuIds = getUserRoleMenuIds();
			//查询当前组织所拥有的角色
			List<String> orgMenuIds = getUserOrgMenuIds();
			//去掉重复的菜单Id
			roleMenuIds.addAll(orgMenuIds);
			HashSet<String> newList = new HashSet<String>(roleMenuIds);   
			roleMenuIds.clear();   
			roleMenuIds.addAll(newList); 
			//查询当前用户所拥有的菜单
			entityWrapper.in("t.id", roleMenuIds);
			menus = menuService.selectTreeList(entityWrapper);
		}
		return menus;
	}
	
	/**
	 * @Description: 查询当前用户所在组织拥有的角色 .<br>
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 下午2:13:19.<br>
	 */
	public List<String> getUserRoleMenuIds(){
		List<String> list = new ArrayList<String>();
		User user = UserUtils.getUser();
		list = menuService.getUserRoleMenuIds(user.getId(), user.getOrgId());
		return list;
	}
	
	/**
	 * @Description: 查询当前组织所拥有的角色 .<br>
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 下午2:13:19.<br>
	 */
	public List<String> getUserOrgMenuIds(){
		List<String> list = new ArrayList<String>();
		User user = UserUtils.getUser();
		list = menuService.getUserOrgMenuIds(user.getId(), user.getOrgId());
		return list;
	}

	/**
	 * @Description: 保存角色配置的菜单
	 * @param @param role
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return AjaxJson .
	 * @author 周恺 
	 * @date 2017年11月29日 下午7:21:11
	 */
	@RequestMapping(value = "/doAuthMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doAuthMenu(Role role, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("权限设置成功");
		try {
			String roleId = role.getId();
			// 删除菜单关联
			roleMenuService.delete(new EntityWrapper<RoleMenu>(RoleMenu.class).eq("roleId", roleId));
			String selectMenu = request.getParameter("selectMenus");
			String[] selectMenus = selectMenu.split(",");
			List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
			for (String menuId : selectMenus) {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuId);
				roleMenuList.add(roleMenu);
			}
			roleMenuService.insertOrUpdateBatch(roleMenuList);
			UserUtils.clearCache();
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("权限设置失败");
		}
		return ajaxJson;
	}
	
	/**
	 * @Description: 获取当前用户拥有的角色列表 .<br>
	 * @param queryable 查询条件封装.<br>
	 * @param propertyPreFilterable 字段封装.<br>
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 下午2:26:18.<br>
	 */
	@RequestMapping(value = "roleAjaxList", method = { RequestMethod.GET, RequestMethod.POST })
	@PageableDefaults(sort = "id=desc")
	private void roleAjaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = UserUtils.getUser();
		EntityWrapper<Role> entityWrapper = new EntityWrapper<Role>(entityClass);
		preAjaxList(queryable,entityWrapper, request, response);
		propertyPreFilterable.addQueryProperty("id");
		// 预处理
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
		if(user.getAdminType().equals(User.ADMIN_NORMAL)){
			if(UserUtils.getRoleList().size()==0){
				return;
			}
			entityWrapper.in("id", getUserRoleIds());
		}
		PageJson<Role> pagejson = new PageJson<Role>(commonService.list(queryable,entityWrapper));
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}

	/**
	 * @Description: 获取当前用户所拥有的组织 .<br>
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 下午2:36:17.<br>
	 */
	private List<String> getUserRoleIds(){
		List<String> list = new ArrayList<String>();
		List<Role> roleList = UserUtils.getRoleList();
		for(Role rl:roleList){
			list.add(rl.getId());
		}
		return list;
	}
}
