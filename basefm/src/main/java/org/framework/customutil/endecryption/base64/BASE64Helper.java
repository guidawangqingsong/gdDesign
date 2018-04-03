package org.framework.customutil.endecryption.base64;

import org.apache.commons.codec.binary.Base64;
import org.framework.customutil.endecryption.BaseAnDecryption;

/**
 * @Title: BASE64Helper.java .<br>
 * @Package org.framework.customutil.endecryption.base64 .<br>
 * @Description: 通过base64对字符串加解密 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-18 上午9:51:42.<br>
 * @version V1.0.<br>
 */
public class BASE64Helper extends BaseAnDecryption{

	
	/**
	 * @Description: BASE64加密 .<br>
	 * @param @param key 需要加密的字符串 .<br>
	 * @return String 加密过后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:52:15.<br>
	 */
	public static String encryptBASE64(String key) {
		byte[] b = Base64.encodeBase64(key.getBytes(), true);  
		String str = new String(b);  
		return str;
	}
	
	/**
	 * @Description: BASE64解密 .<br>
	 * @param @param key 需要解密的字符串 .<br>
	 * @return String 解密之后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:53:00.<br>
	 */
	public static String decryptBASE64(String key) {
		byte[] b1 = Base64.decodeBase64(key);  
		return new String(b1);
	}
	
	/**
	 * @Description: 测试通过BASE64执行加解密 .<br>
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午11:12:01.<br>
	 */
	public static void main(String[] args) {
		System.out.println(encryptBASE64("测试BASE64加解密"));
		System.out.println(decryptBASE64(encryptBASE64("测试BASE64加解密")));
	}

	
}
