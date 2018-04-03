package cn.jjxx.modules.sys.security.shiro.realm;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.ServletUtils;
import cn.jjxx.modules.sys.entity.SysUserOrganizationRemember;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.security.shiro.web.filter.authc.UsernamePasswordToken;
import cn.jjxx.modules.sys.service.IOrganizationService;
import cn.jjxx.modules.sys.service.ISysUserOrganizationRememberService;
import cn.jjxx.modules.sys.service.IUserService;
import cn.jjxx.modules.sys.utils.LogUtils;
import cn.jjxx.modules.sys.utils.UserUtils;

/**
 * http://blog.csdn.net/babys/article/details/50151407
 * 
 * @author www.jjxxkj.cn
 *
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	@Autowired
	private ISysUserOrganizationRememberService sysUserOrganizationRememberService;
	@Autowired
	private IOrganizationService organizationService;

	/**
	 * 授权的回调方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//更新principals里面的用户信息与组织信息
		UserUtils.changeUser();
		authorizationInfo.setRoles(UserUtils.getRoleStringList());
		authorizationInfo.setStringPermissions(UserUtils.getPermissionsList());
		System.out.println(authorizationInfo.getStringPermissions());
		return authorizationInfo;
	}

	/**
	 * 认证的回调方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		String username = authcToken.getUsername();
		User user = userService.findByUsername(username);
		if (user == null) {
			user = userService.findByEmail(username);
			if (user == null) {
				user = userService.findByPhone(username);
			}
		}
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (User.STATUS_LOCKED.equals(user.getStatus())) {
			throw new LockedAccountException(); // 帐号锁定
		}		
		EntityWrapper<SysUserOrganizationRemember> entityWrapper = new EntityWrapper<SysUserOrganizationRemember>(SysUserOrganizationRemember.class);
		entityWrapper.eq("userId",user.getId());
		List<SysUserOrganizationRemember> orgIdRemeber = sysUserOrganizationRememberService.selectList(entityWrapper);
		
		if(!ObjectUtils.isNullOrEmpty(orgIdRemeber)){//存在的话更新组织ID
			SysUserOrganizationRemember	sysUserOrganizationRemember = orgIdRemeber.get(0);
			sysUserOrganizationRemember.setOrganizationId(user.getOrgId());
			sysUserOrganizationRemember.setOrganizationName(user.getOrgName());
			sysUserOrganizationRememberService.updateById(sysUserOrganizationRemember);
		}else{//为空的话插入一条用户记录
			SysUserOrganizationRemember	sysUserOrganizationRemember = new SysUserOrganizationRemember();
			sysUserOrganizationRemember.setUserId(user.getId());
			sysUserOrganizationRemember.setUserName(user.getRealname());
			sysUserOrganizationRemember.setOrganizationId(user.getOrgId());
			sysUserOrganizationRemember.setOrganizationName(user.getOrgName());
			sysUserOrganizationRememberService.insert(sysUserOrganizationRemember);
		}
		
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				new Principal(user, authcToken.isMobileLogin()), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
		// 记录登录日志
		LogUtils.saveLog(ServletUtils.getRequest(), "系统登录");
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		private String id; // 编号
		private String username; // 登录名
		private String realname; // 姓名
		private boolean mobileLogin; // 是否手机登录
		private String orgName;//组织名称
		private String staffId; // 职员编号

		public Principal(User user, boolean mobileLogin) {
			this.id = user.getId();
			this.username = user.getUsername();
			this.realname = user.getRealname();
			this.mobileLogin = mobileLogin;
			this.orgName = user.getOrgName();
			this.staffId = user.getStaffId();
		}

		public String getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public String getRealname() {
			return realname;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}

		public String getOrgName() {
			return orgName;
		}
		
		public String getStaffId() {
			return staffId;
		}

		public void setStaffId(String staffId) {
			this.staffId = staffId;
		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try {
				return (String) UserUtils.getSession().getId();
			} catch (Exception e) {
				return "";
			}
		}

		@Override
		public String toString() {
			return id;
		}

	}
}
