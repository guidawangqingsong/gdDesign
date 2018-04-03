
package cn.jjxx.modules.sys.security.shiro.web.filter.online;

import cn.jjxx.core.security.shiro.session.SessionDAO;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.Constants;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.security.shiro.ShiroConstants;
import cn.jjxx.modules.sys.security.shiro.session.mgt.OnlineSession;
import cn.jjxx.modules.sys.security.shiro.session.mgt.eis.OnlineSessionDAO;
import cn.jjxx.modules.sys.utils.UserUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

/**
 * 
 * All rights Reserved, Designed By www.jjxxkj.cn
 * @title:  OnlineSessionFilter.java   
 * @package cn.jjxx.modules.sys.security.shiro.web.filter.online   
 * @description:   在线
 * @author: www.jjxxkj.cn   
 * @date:   2017年6月26日 下午5:55:19   
 * @version V1.0 
 * @copyright: 2017 www.jjxxkj.cn Inc. All rights reserved. 
 *
 */
public class OnlineSessionFilter extends AccessControlFilter {

	/**
	 * 强制退出后重定向的地址
	 */
	private String forceLogoutUrl;

	private SessionDAO sessionDAO;

	public String getForceLogoutUrl() {
		return forceLogoutUrl;
	}

	public void setForceLogoutUrl(String forceLogoutUrl) {
		this.forceLogoutUrl = forceLogoutUrl;
	}

	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		if (subject == null || subject.getSession() == null) {
			return true;
		}
		Session session = sessionDAO.readSession(subject.getSession().getId());
		if (session != null && session instanceof OnlineSession) {
			OnlineSession onlineSession = (OnlineSession) session;
			request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
			// 把user id设置进去
			//boolean isGuest = onlineSession.getUserId() == null  ;
			if (StringUtils.isEmpty(onlineSession.getUserId())) {
				User user = UserUtils.getUser();
				if (user != null) {
					onlineSession.setUserId(user.getId());
					onlineSession.setUsername(user.getUsername());
					onlineSession.markAttributeChanged();
				}
			}

			if (onlineSession.getStatus() == OnlineSession.OnlineStatus.force_logout) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject != null) {
			subject.logout();
		}
		saveRequestAndRedirectToLogin(request, response);
		return true;
	}

	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		WebUtils.issueRedirect(request, response, getForceLogoutUrl());
	}

}
