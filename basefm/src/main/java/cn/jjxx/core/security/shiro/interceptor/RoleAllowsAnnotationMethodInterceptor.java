package cn.jjxx.core.security.shiro.interceptor;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

/**
 * 注册注解拦截器
 * 
 * @author www.jjxxkj.cn
 *
 */
public class RoleAllowsAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {

	public RoleAllowsAnnotationMethodInterceptor() {
		super(new RolesAllowedAnnotationHandler());
	}

	public RoleAllowsAnnotationMethodInterceptor(AnnotationResolver resolver) {
		super(new RolesAllowedAnnotationHandler(), resolver);
	}

}