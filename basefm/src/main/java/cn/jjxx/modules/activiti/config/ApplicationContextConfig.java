package cn.jjxx.modules.activiti.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Title: ApplicationContextUtil.java .<br>
 * @Package org.framework.core.utils .<br>
 * @Description: 定义一个全局的变量,应用的application上下文 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-19 下午3:07:10.<br>
 * @version V1.0.<br>
 */
public class ApplicationContextConfig implements ApplicationContextAware{

	/**全局的上下文*/
	private static ApplicationContext context;

	/**
	 * @discription 设置全部变量上下文.<br>
	 * @author zcg.<br>
	 * @datetime 2017-9-19 下午3:08:38 .<br>
	 * @version 1.0.0.<br>
	 */
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	/**
	 * @Description: 获取全部变量上下文 .<br>
	 * @return ApplicationContext .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 下午3:08:59.<br>
	 */
	public static ApplicationContext getContext() {
		return context;
	}
}
