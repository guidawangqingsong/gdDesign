package cn.jjxx.modules.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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

import cn.jjxx.core.common.controller.BaseCRUDController;
import cn.jjxx.core.common.entity.ZtreeEntity;
import cn.jjxx.core.common.entity.tree.TreeSortUtil;
import cn.jjxx.core.common.service.ITreeCommonService;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.query.annotation.PageableDefaults;
import cn.jjxx.core.query.data.Page;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.QueryPropertyPreFilter;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.core.utils.MessageUtils;
import cn.jjxx.core.utils.MyBeanUtils;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Menu;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.entity.Role;
import cn.jjxx.modules.sys.entity.SysUserOrganizationRemember;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.entity.UserOrgMenu;
import cn.jjxx.modules.sys.entity.UserOrgRole;
import cn.jjxx.modules.sys.entity.UserOrganization;
import cn.jjxx.modules.sys.service.IMenuService;
import cn.jjxx.modules.sys.service.IOrganizationService;
import cn.jjxx.modules.sys.service.IRoleService;
import cn.jjxx.modules.sys.service.ISysUserOrganizationRememberService;
import cn.jjxx.modules.sys.service.IUserOrgMenuService;
import cn.jjxx.modules.sys.service.IUserOrgRoleService;
import cn.jjxx.modules.sys.service.IUserOrganizationService;
import cn.jjxx.modules.sys.service.IUserRoleService;
import cn.jjxx.modules.sys.service.IUserService;
import cn.jjxx.modules.sys.utils.UserUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * 
 * All rights Reserved, Designed By www.jjxxkj.cn
 * 
 * @title: UserController.java
 * @package cn.jjxx.modules.sys.controller
 * @description: 用户操作控制器
 * @author: www.jjxxkj.cn
 * @date: 2017年5月25日 上午9:52:20
 * @version V1.0
 * @copyright: 2017 www.jjxxkj.cn Inc. All rights reserved.
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/user")
@RequiresPathPermission("sys:user")
public class UserController extends BaseCRUDController<User, String> {
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IUserOrganizationService userOrganizationService;
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IUserOrgMenuService userOrgMenuService;
	@Autowired
	private IUserOrgRoleService userOrgRoleService;
	@Autowired
	private ITreeCommonService<Organization,String> treeCommonService;
	@Autowired
	private ISysUserOrganizationRememberService sysUserOrganizationRememberService;

	
	
	
	public UserController() {
		setCommonService(userService);
	}
	
	public User get(String id) {
		if (!ObjectUtils.isNullOrEmpty(id)) {
			return userService.selectUserById(id);
		} else {
			return newModel();
		}
	}
	
	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxListUser", method = { RequestMethod.GET, RequestMethod.POST })
	@PageableDefaults(sort = "id=desc")
	private void ajaxListUser(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		EntityWrapper<User> entityWrapper = new EntityWrapper<User>(entityClass);
		propertyPreFilterable.addQueryProperty("id");
		entityWrapper.setTableAlias("t.");
		if(UserUtils.getUser().getAdminType().equals(User.ADMIN_NORMAL)){//如果是系统用户则不过滤
			entityWrapper.in("orgId", UserUtils.getOrgChilds(true, true));
		}	
		// 预处理
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
		PageJson<User> pagejson = new PageJson<User>(commonService.list(queryable,entityWrapper));
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * 
	 * @Description: treeselct组件专用弹出组织树页面
	 * @param @param user
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:33:48
	 */
	@RequestMapping(value = "authOrg", method = RequestMethod.GET)
	public String authOrg(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		if(StringUtils.isEmpty(gid)){
			return display("authOrg");
		}
		user = get(gid);
		EntityWrapper<Organization> entityWrapper = new EntityWrapper<Organization>(Organization.class);
		List<Organization> treeNodeList = treeCommonService.selectTreeList(entityWrapper);
		TreeSortUtil.create().sort(treeNodeList).async(treeNodeList);
		PropertyPreFilterable propertyPreFilterable = new QueryPropertyPreFilter();
		propertyPreFilterable.addQueryProperty("id", "name", "expanded", "hasChildren", "leaf", "loaded", "level",
				"parentId");
		SerializeFilter filter = propertyPreFilterable.constructFilter(Organization.class);
		PageJson<Organization> pagejson = new PageJson<Organization>(treeNodeList);
		String content = JSON.toJSONString(pagejson.getResults(), filter);
		//List<Organization> orgs = organizationService.selectList(entityWrapper);
		List<Organization> selectOrgs = organizationService.findListByUserId(gid);
		model.addAttribute("orgs", content);
		model.addAttribute("selectOrgs", JSONArray.toJSON(selectOrgs));
		model.addAttribute("data", user);
		return display("authOrg");
	}
	
	
	/**
	 * 
	 * @Description: treeselect组件专用 弹出角色选择页面 
	 * @param @param userOrganization
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:34:31
	 */
	@RequestMapping(value = "authRole", method = RequestMethod.GET)
	public String authRole(@RequestParam(value = "ids", required = false) String[] ids,UserOrganization userOrganization, Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		userOrganization = userOrganizationService.selectById(userOrganization.getId());
		EntityWrapper<Role> entityWrapper = new EntityWrapper<Role>(Role.class);
		List<Role> roles = new ArrayList<Role>();
		if(user.getAdminType().equals(User.ADMIN_VIP)){
			roles = roleService.selectList(entityWrapper);
		}else{
			roles = UserUtils.getRoleList();
		}
		List<Role> selectRoles = roleService.findRoleByUserIdAndOrgId(userOrganization.getUserId(),userOrganization.getOrganizationId());
		model.addAttribute("roles", JSONArray.toJSON(roles));
		model.addAttribute("selectRoles", JSONArray.toJSON(selectRoles));
		model.addAttribute("data", userOrganization);
		return display("authRole");
	}
	
	/**
	 * 
	 * @Description: treeselct组件专用保存组织树页面
	 * @param @param user
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return AjaxJson .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:35:20
	 */
	@RequestMapping(value = "doAuthOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doAuthOrg(User user, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.user.org.add.success"));
		String gid = user.getId();
		if(StringUtils.isEmpty(gid)){
			ajaxJson.fail(MessageUtils.getMessage("info.user.org.add.id.fail"));
			return ajaxJson;
		}
		try {
			// 删除组织关联
			userOrganizationService.delete(new EntityWrapper<UserOrganization>(UserOrganization.class).eq("userId", gid));
			String selectOrg = request.getParameter("selectOrgs");
			String[] selectOrgs = selectOrg.split(",");
			List<UserOrganization> userOrganizationList = new ArrayList<UserOrganization>();
			for (String orgId : selectOrgs) {
				UserOrganization uo= new UserOrganization();
				uo.setOrganizationId(orgId);
				uo.setUserId(gid);
				userOrganizationList.add(uo);
			}
			userOrganizationService.insertOrUpdateBatch(userOrganizationList);
			UserUtils.clearCache();
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.user.org.add.fail"));
		}
		return ajaxJson;
	}

	
	/**
	 * 
	 * @Description: treeselect组件专用 保存角色选择页面 
	 * @param @param userOrganization
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:34:31
	 */
	@RequestMapping(value = "doRoleAuthMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doRoleAuthMenu(UserOrganization userOrganization, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("sys.user.role.add.success"));
		userOrganization = userOrganizationService.selectById(userOrganization.getId());
		try {
			// 删除角色关联
			userOrgRoleService.delete(new EntityWrapper<UserOrgRole>(UserOrgRole.class).eq("userId", userOrganization.getUserId())
					.eq("orgId", userOrganization.getOrganizationId()));
			String selectRole = request.getParameter("selectRoles");
			String[] selectRoles = selectRole.split(",");
			List<UserOrgRole> userOrgRoleList = new ArrayList<UserOrgRole>();
			for (String roleId : selectRoles) {
				UserOrgRole userOrgRole = new UserOrgRole();
				userOrgRole.setOrgId(userOrganization.getOrganizationId());
				userOrgRole.setUserId(userOrganization.getUserId());
				userOrgRole.setRoleId(roleId);
				userOrgRoleList.add(userOrgRole);
			}
			userOrgRoleService.insertOrUpdateBatch(userOrgRoleList);
			UserUtils.clearCache();
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("sys.user.role.add.fail"));
		}
		return ajaxJson;
	}

	/**
	 * 
	 * @Description: 弹出菜单配置页面 
	 * @param @param userOrganization
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:36:19
	 */
	@RequestMapping(value = "authMenu", method = RequestMethod.GET)
	public String authMenu(UserOrganization userOrganization, Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		userOrganization = userOrganizationService.selectById(userOrganization.getId());
		EntityWrapper<Menu> entityWrapper = new EntityWrapper<Menu>(Menu.class);
		entityWrapper.orderBy("sort", false);
		List<Menu> menus = new ArrayList<Menu>();
		if(user.getAdminType().equals(User.ADMIN_VIP)){
			menus = menuService.selectTreeList(entityWrapper);
		}else{
			//查询当前用户所在组织拥有的角色
			List<String> roleMenuIds = SpringContextHolder.getBean(RoleController.class).getUserRoleMenuIds();
			//查询当前组织所拥有的角色
			List<String> orgMenuIds = SpringContextHolder.getBean(RoleController.class).getUserOrgMenuIds();
			//去掉重复的菜单Id
			roleMenuIds.addAll(orgMenuIds);
			HashSet<String> newList = new HashSet<String>(roleMenuIds);   
			roleMenuIds.clear();   
			roleMenuIds.addAll(newList); 
			//查询当前用户所拥有的菜单
			entityWrapper.in("t.id", roleMenuIds);
			menus = menuService.selectTreeList(entityWrapper);
		}		
		if(StringUtils.isNotEmpty(userOrganization.getUserId())&&StringUtils.isNotEmpty(userOrganization.getOrganizationId())){
			List<Menu> selectMenus = menuService.findMenuByUserIdAndOrgId(userOrganization.getUserId(),userOrganization.getOrganizationId());
			model.addAttribute("selectMenus", JSONArray.toJSON(selectMenus));
		}
		model.addAttribute("menus", JSONArray.toJSON(menus));
		model.addAttribute("data", userOrganization);
		return display("authMenu");
	}

	/**
	 * 
	 * @Description: 保存菜单配置页面 
	 * @param @param userOrganization
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return AjaxJson .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:36:39
	 */
	@RequestMapping(value = "/doAuthMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doAuthMenu(UserOrganization userOrganization, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("sys.user.per.add.success"));
		userOrganization = userOrganizationService.selectById(userOrganization.getId());
		if(StringUtils.isEmpty(userOrganization.getUserId())||StringUtils.isEmpty(userOrganization.getOrganizationId())){
			ajaxJson.fail(MessageUtils.getMessage("sys.user.per.add.id.fail"));
			return ajaxJson;
		}
		try {
			// 删除菜单关联
			userOrgMenuService.delete(new EntityWrapper<UserOrgMenu>(UserOrgMenu.class).eq("userId", userOrganization.getUserId())
					.eq("orgId", userOrganization.getOrganizationId()));
			String selectMenu = request.getParameter("selectMenus");
			String[] selectMenus = selectMenu.split(",");
			List<UserOrgMenu> userOrgMenuList = new ArrayList<UserOrgMenu>();
			for (String menuId : selectMenus) {
				UserOrgMenu userOrgMenu = new UserOrgMenu();
				userOrgMenu.setOrgId(userOrganization.getOrganizationId());
				userOrgMenu.setUserId(userOrganization.getUserId());
				userOrgMenu.setMenuId(menuId);
				userOrgMenuList.add(userOrgMenu);
			}
			userOrgMenuService.insertOrUpdateBatch(userOrgMenuList);
			UserUtils.clearCache();
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("sys.user.per.add.fail"));
		}
		return ajaxJson;
	}

	/**
	 * 
	 * @Description: 弹出用户组织配置页面 
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:37:32
	 */
	@RequestMapping(value = "configOrg", method = RequestMethod.GET)
	public String _showUserOrg(Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		User user = get(gid);
		model.addAttribute("user", user);		
		return display("userOrgList");
	}
	
	/**
	 * 
	 * @Description: 查询用户组织配置页面数据 
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:37:32
	 */
	@RequestMapping(value = "ajaxListConfig", method = { RequestMethod.GET, RequestMethod.POST })
	public void ajaxListConfig(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String gid = request.getParameter("gid");
		EntityWrapper<UserOrganization> entityWrapper = new EntityWrapper<UserOrganization>(UserOrganization.class);
		propertyPreFilterable.addQueryProperty("id");
		entityWrapper.eq("userId", gid);
		// 预处理
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, UserOrganization.class);
		SerializeFilter filter = propertyPreFilterable.constructFilter(UserOrganization.class);
		Page<UserOrganization> uopage = userOrganizationService.list(queryable,entityWrapper);
		for (UserOrganization userOrganization : uopage) {//查找关联用户与组织信息
			if(StringUtils.isNotEmpty(userOrganization.getId())){
				userOrganization.setUser(userService.selectById(userOrganization.getUserId()));
			}
			if(StringUtils.isNotEmpty(userOrganization.getOrganizationId())){
				userOrganization.setOrganization(organizationService.selectById(userOrganization.getOrganizationId()));
			}
		}
		PageJson<UserOrganization> pagejson = new PageJson<UserOrganization>(uopage);
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}
	
	
	@RequestMapping(value = "createUser", method = RequestMethod.GET)
	public String _showCreate(Model model, HttpServletRequest request, HttpServletResponse response) {
		preEdit(newModel(), model, request, response);
		String creteaView = showCreate(newModel(), model, request, response);
		if (!model.containsAttribute("data")) {
			model.addAttribute("data", newModel());
		}
		if (!StringUtils.isEmpty(creteaView)) {
			return creteaView;
		}
		String type = request.getParameter("type");
		if(!StringUtils.isEmpty(type)){
			return display("createUser");
		}
		return display("create");
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson create(Model model, @Valid @ModelAttribute("data") User entity, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		return doSave(entity, request, response, result);
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doSave(User entity, HttpServletRequest request, HttpServletResponse response,
			BindingResult result) {
		AjaxJson ajaxJson = new AjaxJson();
		
		ajaxJson.success(MessageUtils.getMessage("info.save.success"));
		if (hasError(entity, result)) {
			// 错误提示
			String errorMsg = errorMsg(result);
			if (!StringUtils.isEmpty(errorMsg)) {
				ajaxJson.fail(errorMsg);
			} else {
				ajaxJson.fail(MessageUtils.getMessage("info.save.fail"));
			}
			return ajaxJson;
		}
		try {
			preSave(entity, request, response);
			if (ObjectUtils.isNullOrEmpty(entity.getId())) {
				userService.insertUser(entity);
			} else {
				userService.insertOrUpdateUser(entity);
			}
			afterSave(entity, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.save.fail.reason")+ e.getMessage());
		}
		return ajaxJson;
	}
	
	
	@RequestMapping(value = "{id}/updateUser", method = RequestMethod.GET)
	public String _showUpdate(@PathVariable("id") String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User entity = get(id);
		preEdit(entity, model, request, response);
		model.addAttribute("data", entity);
		String updateView = showUpdate(newModel(), model, request, response);
		if (!StringUtils.isEmpty(updateView)) {
			return updateView;
		}
		return display("edit");
	}
	
	@RequestMapping(value = "{id}/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson update(Model model, @Valid @ModelAttribute("data") User entity, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		return doSave(entity, request, response, result);
	}
	
	@RequestMapping(value = "{id}/changePassword", method = RequestMethod.GET)
	public String changePassword(@PathVariable("id") String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.selectById(id);
		model.addAttribute("data", user);
		return display("changePassword");
	}

	@RequestMapping(value = "{id}/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson changePassword(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("sys.user.pwd.success"));
		try {
			String password = request.getParameter("password");
			userService.changePassword(id, password);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("sys.user.pwd.fail"));
		}
		return ajaxJson;
	}

	@RequestMapping(value = "{id}/avatar", method = RequestMethod.GET)
	public String avatar(@PathVariable("id") String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.selectById(id);
		model.addAttribute("data", user);
		return display("avatar");
	}

	@RequestMapping(value = "{id}/avatar", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson avatar(User user, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("sys.user.avatar.success"));
		try {
			User oldUser = commonService.selectById(user.getId());
			MyBeanUtils.copyBeanNotNull2Bean(user, oldUser);
			commonService.insertOrUpdate(oldUser);
			String currentUserId = UserUtils.getUser().getId();
			if (currentUserId.equals(user.getId())) {
				UserUtils.clearCache();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("sys.user.avatar.fail"));
		}
		return ajaxJson;
	}

	@Override
	public void preEdit(User user, Model model, HttpServletRequest request, HttpServletResponse response) {

	}

	@Override
	public void preAjaxList(Queryable queryable,EntityWrapper<User> entityWrapper,HttpServletRequest request,
			HttpServletResponse response) {
		// 子查询
		String organizationid = request.getParameter("organizationid");
		if (!StringUtils.isEmpty(organizationid)) {
			entityWrapper.eq("t.org_id", organizationid);
		}
	}

	@Override
	public void preSave(User entity, HttpServletRequest request, HttpServletResponse response) {

	}

	@Override
	public void afterSave(User entity, HttpServletRequest request, HttpServletResponse response) {
		// 增加默认组织添加入用户组织关系库
		String organizationId = entity.getOrgId();
		if (organizationId != null && organizationId.length() > 0) {
			userOrganizationService.delete(new EntityWrapper<UserOrganization>(UserOrganization.class).eq("userId", entity.getId()).eq("organizationId", organizationId));
				UserOrganization userOrganization = new UserOrganization();
				userOrganization.setUserId(entity.getId());
				userOrganization.setOrganizationId(organizationId);
			userOrganizationService.insert(userOrganization);
		}
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String info(@RequestParam(required = false) String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.selectById(id);
		model.addAttribute("data", user);
		return display("info");
	}
	
	
	
	@RequestMapping(value = "batch/deleteOrg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.del.success"));
		try {
			List<String> idList = java.util.Arrays.asList(ids);
			for (String id : idList) {
				userOrganizationService.deleteByIdCascade(id);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.del.fail"));
		}
		return ajaxJson;
	}
	
	@RequestMapping(value = "{id}/deleteOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delete(@PathVariable("id") String id) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.del.success"));
		try {
			userOrganizationService.deleteByIdCascade(id);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.del.fail"));
		}
		return ajaxJson;
	}
	
	@RequestMapping(value = "batch/deleteUser", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson deleteUser(@RequestParam(value = "ids", required = false) String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.del.success"));
		try {
			List<String> idList = java.util.Arrays.asList(ids);
			for (String id : idList) {
				userService.deleteByIdCascade(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.del.fail"));
		}
		return ajaxJson;
	}
	
	@RequestMapping(value = "{id}/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson deleteUser(@PathVariable("id") String id) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.del.success"));	
		try {
			userService.deleteByIdCascade(id);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.del.fail"));
		}
		return ajaxJson;
	}
	
    /**
     * 
     * @Description: 切换当前所在组织
     * @param @param orgId
     * @param @return .  
     * @return AjaxJson .
     * @author 周恺 
     * @date 2017年12月25日 下午2:56:48
     */
    @RequestMapping(value = "changeOrg")
	@ResponseBody
	public AjaxJson changeOrg(@RequestParam(value = "orgId", required = true) String orgId,@RequestParam(value = "orgName", required = true) String orgName){
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("切换组织成功");
		List<String> orgIds = UserUtils.getOrgAndSubIds(true);
		if(!ObjectUtils.isNullOrEmpty(orgIds)&&orgIds.contains(orgId)){//如果切换组织为当前用户所拥有组织则切换
			EntityWrapper<SysUserOrganizationRemember> entityWrapper = new EntityWrapper<SysUserOrganizationRemember>(SysUserOrganizationRemember.class);
			entityWrapper.eq("userId", UserUtils.getUser().getId());
			List<SysUserOrganizationRemember> orgIdRemeber = sysUserOrganizationRememberService.selectList(entityWrapper);
			if(!ObjectUtils.isNullOrEmpty(orgIdRemeber)){//存在的话更新组织ID
				SysUserOrganizationRemember	sysUserOrganizationRemember = orgIdRemeber.get(0);
				sysUserOrganizationRemember.setOrganizationId(orgId);
				sysUserOrganizationRemember.setOrganizationName(orgName);
				sysUserOrganizationRememberService.updateById(sysUserOrganizationRemember);
			}else{//为空的话插入一条用户记录
				SysUserOrganizationRemember	sysUserOrganizationRemember = new SysUserOrganizationRemember();
				sysUserOrganizationRemember.setUserId(UserUtils.getUser().getId());
				sysUserOrganizationRemember.setUserName(UserUtils.getUser().getRealname());
				sysUserOrganizationRemember.setOrganizationId(orgId);
				sysUserOrganizationRemember.setOrganizationName(orgName);
				sysUserOrganizationRememberService.insert(sysUserOrganizationRemember);
			}
			//清空用户session shiro会重新自动加载
			UserUtils.clearCache();
			//更新principals里面的用户信息与组织信息
			UserUtils.changeUser();
		}else{
			ajaxJson.fail("切换组织失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 
	 * @Description: 根据用户ID获取当前用户下所有组织信息 
	 * @param  userId 用户ID
	 * @param .  
	 * @return AjaxJson .
	 * @author 周恺 
	 * @date 2017年12月25日 下午3:20:44
	 */
    @RequestMapping(value = "getUserOrgs", method = RequestMethod.GET)
	public String getUserOrgs(HttpServletRequest request,HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("获取用户关联组织成功");	
		try {
			List<UserOrganization> userOrganizationList = getUserOrgsById(UserUtils.getUser().getId());	
			ajaxJson.setData(userOrganizationList);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("获取用户关联组织失败");
		}
		request.setAttribute("map", ajaxJson);
		return display("cutOrg");
	
	}
    
    /**
     * 
     * @Description: 手机端获取用户组织单位信息 
     * @param @return .  
     * @return AjaxJson .
     * @author 周恺 
     * @date 2017年12月26日 上午10:57:02
     */
    public AjaxJson getUserOrgsMoblie(){
    	AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("获取用户关联组织成功");	
		try {
			List<UserOrganization> userOrganizationList = getUserOrgsById(UserUtils.getUser().getId());	
			ajaxJson.setData(userOrganizationList);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("获取用户关联组织失败");
		}
		return ajaxJson;
    }

	
	/**
	 * 
	 * @Description: 根据用户ID查询用户下所有组织单位 
	 * @param @param userId .  
	 * @return void .
	 * @author 周恺 
	 * @date 2017年12月25日 下午3:27:03
	 */
	public List<UserOrganization> getUserOrgsById(String userId) {
		EntityWrapper<UserOrganization> entityWrapper = new EntityWrapper<UserOrganization>(UserOrganization.class);
		entityWrapper.eq("userId", userId);
		List<UserOrganization> userOrganizationList = userOrganizationService.selectList(entityWrapper);
		for (UserOrganization userOrganization : userOrganizationList) {//查找关联用户与组织信息
			if(StringUtils.isNotEmpty(userOrganization.getId())){
				userOrganization.setUser(userService.selectById(userOrganization.getUserId()));
			}
			if(StringUtils.isNotEmpty(userOrganization.getOrganizationId())){
				userOrganization.setOrganization(organizationService.selectById(userOrganization.getOrganizationId()));
			}
		}
		return userOrganizationList;
		
	}
	
	
	/**
	 * 
	 * @Description: 查询当前登录用户所拥有的组织权限
	 * @param @param nodeid
	 * @param @param request
	 * @param @param response
	 * @param @param propertyPreFilterable
	 * @param @throws IOException .  
	 * @return void .
	 * @author 黄启玲 
	 * @date 2018年1月18日 下午6:03:24
	 */
	@RequestMapping(value = "userOrgTree", method ={ RequestMethod.GET, RequestMethod.POST })
	private void userOrgTree(@RequestParam(value = "nodeid", required = false) String nodeid,
			HttpServletRequest request, HttpServletResponse response, PropertyPreFilterable propertyPreFilterable)
			throws IOException {
		List<ZtreeEntity>  treeList = new ArrayList<ZtreeEntity>();
		List<UserOrganization> userOrganizationList = getUserOrgsByUserIdAndOrgId(UserUtils.getUser().getId(),nodeid);	
		for (UserOrganization organization : userOrganizationList) {
			 ZtreeEntity  tree = new ZtreeEntity();
			 tree.setId(organization.getOrganization().getId());
			 tree.setTitle(organization.getOrganization().getName());
			 tree.setName(organization.getOrganization().getName());
			 tree.setPid(organization.getOrganization().getParentId());
			 tree.setIsParent(organization.getOrganization().isHasChildren());
			 treeList.add(tree);
		}
		String content = JSON.toJSONString(treeList);
		StringUtils.printJson(response, content);
	}
	
	
	/**
	 * 
	 * @Description: 根据用户ID查询用户下所有组织单位 
	 * @param @param userId .  
	 * @return void .
	 * @author 周恺 
	 * @date 2017年12月25日 下午3:27:03
	 */
	public List<UserOrganization> getUserOrgsByUserIdAndOrgId(String userId,String orgId) {
		List<UserOrganization> userOrganizationList = userOrganizationService.selectListByUserId(userId,orgId);
		for (UserOrganization userOrganization : userOrganizationList) {//查找关联用户与组织信息
			if(StringUtils.isNotEmpty(userOrganization.getId())){
				userOrganization.setUser(userService.selectById(userOrganization.getUserId()));
			}
			if(StringUtils.isNotEmpty(userOrganization.getOrganizationId())){
				userOrganization.setOrganization(organizationService.selectById(userOrganization.getOrganizationId()));
			}
		}
		return userOrganizationList;
		
	}
	
}