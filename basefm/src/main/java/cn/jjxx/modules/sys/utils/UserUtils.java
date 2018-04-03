package cn.jjxx.modules.sys.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.CacheUtils;
import cn.jjxx.core.utils.JeewebPropertiesUtil;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.ServletUtils;
import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Menu;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.entity.Role;
import cn.jjxx.modules.sys.entity.SysUserOrganizationRemember;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.entity.UserOrganization;
import cn.jjxx.modules.sys.security.shiro.realm.UserRealm.Principal;
import cn.jjxx.modules.sys.service.IMenuService;
import cn.jjxx.modules.sys.service.IOrganizationService;
import cn.jjxx.modules.sys.service.IRoleService;
import cn.jjxx.modules.sys.service.ISysUserOrganizationRememberService;
import cn.jjxx.modules.sys.service.IUserOrganizationService;
import cn.jjxx.modules.sys.service.IUserService;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

/**
 * 
 * All rights Reserved, Designed By www.jjxxkj.cn
 * 
 * @title: UserUtils.java
 * @package cn.jjxx.modules.sys.utils
 * @description: 用户工具类
 * @author: www.jjxxkj.cn
 * @date: 2017年6月26日 下午6:00:39
 * @version V1.0
 * @copyright: 2017 www.jjxxkj.cn Inc. All rights reserved.
 *
 */
@SuppressWarnings("unchecked")
public class UserUtils {

	private static IUserService userService = SpringContextHolder.getBean(IUserService.class);
	private static IRoleService roleService = SpringContextHolder.getBean(IRoleService.class);
	private static IMenuService menuService = SpringContextHolder.getBean(IMenuService.class);
	private static IUserOrganizationService userOrganizationService = SpringContextHolder.getBean(IUserOrganizationService.class);
	private static ISysUserOrganizationRememberService sysUserOrganizationRememberService = SpringContextHolder.getBean(ISysUserOrganizationRememberService.class);
	private static IOrganizationService organizationService = SpringContextHolder.getBean(IOrganizationService.class);
	
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_USER_NAME_ = "username_";
	public static final String MENU_CACHE_URL_ = "menu_url_";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	
	
	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userService.selectUserById(id);
			if (user == null) {
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_USER_NAME_ + user.getUsername(), user);
		}
		return user;
	}

	/**
	 * 根据用户名获取用户
	 * 
	 * @param username
	 * @return
	 */
	public static User getByUserName(String username) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_USER_NAME_ + username);
		if (user == null) {
			user = userService.findByUsername(username);
			if (user == null) {
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_USER_NAME_ + user.getUsername(), user);
		}
		return user;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		UserUtils.clearCache(getUser());
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(User user) {
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_USER_NAME_ + user.getUsername());
	}

	/**
	 * 变更当前用户组织信息为默认登陆组织信息
	 * 
	 * @return 
	 */
	public static void changeUser() {
		Principal principal = getPrincipal();
		if (principal != null) {
			User user = get(principal.getId());
			//判断用户默认登陆组织表是否有值 如果没有就为默认组织ID
			EntityWrapper<SysUserOrganizationRemember> entityWrapper = new EntityWrapper<SysUserOrganizationRemember>(SysUserOrganizationRemember.class);
			entityWrapper.eq("userId", user.getId());
			List<SysUserOrganizationRemember> orgIdRemeber = sysUserOrganizationRememberService.selectList(entityWrapper);
			if(!ObjectUtils.isNullOrEmpty(orgIdRemeber)){
				user.setOrgId(orgIdRemeber.get(0).getOrganizationId());
				Organization org = organizationService.selectById(orgIdRemeber.get(0).getOrganizationId());
				if(!ObjectUtils.isNullOrEmpty(org)){
					user.setOrgName(org.getName());	
				}
			}
		}
	}
	
	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static User getUser() {
		Principal principal = getPrincipal();
		if (principal != null) {
			User user = get(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * 
	 * @return
	 */
	public static List<Role> getRoleList() {
		List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);
		if (roleList == null) {
			User user = getUser();
//			roleList = roleService.findListByUserId(user.getId());
			String orgId = user.getOrgId();		
			if(StringUtils.isEmpty(orgId)){//如果默认组织ID为空则设置组织ID为用户组织关联表中的第一个ID
				Map<String, Object> columnMap = new HashMap<String,Object>();
				columnMap.put("user_id", user.getId());
				List<UserOrganization> userOrgs = userOrganizationService.selectByMap(columnMap);
				if(!ObjectUtils.isNullOrEmpty(userOrgs)){
					orgId = userOrgs.get(0).getOrganizationId();
				}
			}
			roleList = roleService.findRoleByUserIdAndOrgId(user.getId(), orgId);
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}

	public static Set<String> getRoleStringList() {
		Set<Role> roles = Sets.newConcurrentHashSet(getRoleList());
		return Sets.newHashSet(Collections2.transform(roles, new Function<Role, String>() {
			@Override
			public String apply(Role role) {
				return role.getCode();
			}
		}));
	}

	/**
	 * 
	 * @Description: 获取当前登录用户的当前所有组织 ID,包含当前组织ID以及所有拥有组织的子组织
	 * @param isLocal 判断是否包含本级  
	 * @return List<String> 返回当前用户拥有的组织ID集合，包含子组织.
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:25:05
	 */
	@Deprecated
	public static List<String> getOrgAndSubIds(boolean isLocal){
		Set<String> orgIds = new HashSet<String>();
		User user = getUser();
		String orgId = user.getOrgId();
		if(isLocal){
			orgIds.add(orgId);
		}
		//找出用户下面所有组织
		List<UserOrganization> userOrgs = getUserOrgList(user.getId());
		for (UserOrganization userOrganization : userOrgs) {			
			orgIds.add(userOrganization.getOrganizationId());
		}
		return new ArrayList<String>(orgIds);
	}
	
	/**
	 * @Description: 查询当前组织及以下组织 .<br>
	 * @param isLocal 是否包含本级 .<br>
	 * @param authorization 是否需要权限控制的组织.<br>
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-27 上午9:10:18.<br>
	 */
	public static List<String> getOrgChilds(boolean isLocal,boolean authorization){
		Set<String> orgIds = new HashSet<String>();
		User user = getUser();
		String orgId = user.getOrgId();
		if(isLocal){
			orgIds.add(orgId);
		}
		//查询当前级以下的菜单
		List<Organization> childs = organizationService.findsubById(orgId);
		if(authorization){
			List<UserOrganization> userOrgs = getUserOrgList(user.getId());
			for(Organization org:childs){
				for(UserOrganization userOrg:userOrgs){
					if(userOrg.getOrganizationId().equals(org.getId())){
						orgIds.add(org.getId());
						continue;
					}
				}
			}
		}else{
			for(Organization org:childs){
				orgIds.add(org.getId());
			}
		}
		return new ArrayList<String>(orgIds);
	}
	
	/**
	 * @Description: 根据用户Id,获取用户组织信息 .<br>
	 * @param userId 用户ID.<br>
	 * @return List<UserOrganization> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-27 上午9:23:42.<br>
	 */
	private static List<UserOrganization> getUserOrgList(String userId){
		//找出用户下面所有组织
		Map<String, Object> columnMap = new HashMap<String,Object>();
		columnMap.put("user_id", userId);
		List<UserOrganization> userOrgs = userOrganizationService.selectByMap(columnMap);
		return userOrgs;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList() {
		List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
		if (menuList == null) {
			User user = getUser();
//			menuList = menuService.findMenuByUserId(user.getId());
			String orgId = user.getOrgId();
			if(StringUtils.isEmpty(orgId)){//如果默认组织ID为空则设置组织ID为用户组织关联表中的第一个ID
				Map<String, Object> columnMap = new HashMap<String,Object>();
				columnMap.put("user_id", user.getId());
				List<UserOrganization> userOrgs = userOrganizationService.selectByMap(columnMap);
				if(!ObjectUtils.isNullOrEmpty(userOrgs)){
					orgId = userOrgs.get(0).getOrganizationId();
				}
			}
			Set<Menu>  menuSet = new HashSet<Menu>();
			//获取当前用户所有角色
			List<Role> roleList = getRoleList();
			List<Menu> roleMenus = null;
			for (Role role : roleList) {
				roleMenus = menuService.findMenuByRoleId(role.getId());
				menuSet.addAll(roleMenus);
			}		
			menuList = menuService.findMenuByUserIdAndOrgId(user.getId(), orgId);
			menuSet.addAll(menuList);
			menuList = new ArrayList<Menu>(menuSet);	
			Comparator<Menu> comparator = new Comparator<Menu>() {
					public int compare(Menu o1, Menu o2) {
						return o1.getSort().compareTo(o2.getSort());
					}
		        };
	        Collections.sort(menuList,comparator);
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 获取当前菜单
	 * 
	 * @return
	 */
	public static Menu getCurrentMenu() {
		String url = ServletUtils.getRequest().getServletPath();
		if (url.endsWith(".jsp")) {
			return null;
		}
		String adminUrlPrefix = JeewebPropertiesUtil.getConfig("admin.url.prefix");
		url = url.substring(adminUrlPrefix.length() + 1, url.length());
		url = StringUtils.trimFirstAndLastChar(url, '/');
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		// 全匹配查找
		List<Menu> menuList = getMenuList();
		return getCurrentMenu(menuList, url);
	}

	private static Menu getCurrentMenu(List<Menu> menuList, String url) {
		for (Menu menu : menuList) {
			if (!StringUtils.isEmpty(menu.getUrl())
					&& url.equals(StringUtils.trimFirstAndLastChar(menu.getUrl(), '/'))) {
				return menu;
			}
		}
		/*if (StringUtils.isEmpty(url)) {
		return null;
		}
		url = url.substring(0, url.lastIndexOf("/"));
		return getCurrentMenu(menuList, url);*/
		return null;
	}

	/**
	 * 通过ID获得菜单信息
	 * 
	 * @return
	 */
	public static Menu getMenuById(String menuid) {
		if (StringUtils.isEmpty(menuid)) {
			return null;
		}
		List<Menu> menuList = getMenuList();
		for (Menu menu : menuList) {
			if (menuid.equals(menu.getId())) {
				return menu;
			}
		}
		return null;
	}

	public static Set<String> getPermissionsList() {
		List<Menu> list = UserUtils.getMenuList();
		Set<String> permissionsList = Sets.newConcurrentHashSet();
		for (Menu menu : list) {
			if (StringUtils.isNotBlank(menu.getPermission())) {
				// 添加基于Permission的权限信息
				for (String permission : StringUtils.split(menu.getPermission(), ",")) {
					if (StringUtils.isNotBlank(permission)) {
						permissionsList.add(permission);
					}
				}
			}
		}
		return permissionsList;
	}

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static Menu getTopMenu() {
		Menu topMenu = getMenuList().get(0);
		return topMenu;
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			// subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	// ============== User Cache ==============
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}

}
