package org.framework.customutil.sms;

/**
 * @Title: SMSConfig.java .<br>
 * @Package org.framework.customutil.sms .<br>
 * @Description: 短信发送公共的配置类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-19 下午2:04:53.<br>
 * @version V1.0.<br>
 */
public class SMSConfig {

	/**String java类型*/
	public static final String FEILD_TYPE = "java.lang.String";
	
	/**
	 * @Title: SMSConfig.java .<br>
	 * @Package org.framework.customutil.sms .<br>
	 * @Description: 定义在文件中使用到的字段 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-19 下午2:05:52.<br>
	 * @version V1.0.<br>
	 */
	public enum other{
		SHORT,name,set,get
	}
	
	/**
	 * @Title: SMSConfig.java .<br>
	 * @Package org.framework.customutil.sms .<br>
	 * @Description: 定义一个枚举类型,用来标记是否成功 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-19 下午2:06:22.<br>
	 * @version V1.0.<br>
	 */
	public enum signType{
		success,fail
	}
}
