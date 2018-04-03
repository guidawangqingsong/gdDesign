package org.framework.customutil.endecryption;

/**
 * @Title: Config.java .<br>
 * @Package org.framework.customutil.endecryption .<br>
 * @Description: 加解密工具配置类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-18 上午10:28:27.<br>
 * @version V1.0.<br>
 */
public class Config {

	/**UTF-8编码方式*/
	public static final String UTF_8 = "UTF-8";
	
	/**
	 * @Title: Config.java .<br>
	 * @Package org.framework.customutil.endecryption .<br>
	 * @Description: 加解密方式枚举 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-18 上午10:29:36.<br>
	 * @version V1.0.<br>
	 */
	public enum type{
		MD5,SHA,AES,BASE64
	}
	
	/**
	 * @Title: Config.java .<br>
	 * @Package org.framework.customutil.endecryption .<br>
	 * @Description: 编码方式枚举 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-18 上午10:29:40.<br>
	 * @version V1.0.<br>
	 */
	public enum encoded{
		UTF8,GBK
	}
}
