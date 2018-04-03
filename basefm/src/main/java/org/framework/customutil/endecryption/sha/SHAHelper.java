package org.framework.customutil.endecryption.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.framework.customutil.endecryption.BaseAnDecryption;
import org.framework.customutil.endecryption.Config;

/**
 * @Title: SHAHelper.java .<br>
 * @Package org.framework.customutil.endecryption.sha .<br>
 * @Description: 通过SHA加密方式进行加密 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-18 上午10:21:39.<br>
 * @version V1.0.<br>
 */
public class SHAHelper extends BaseAnDecryption{
	
	/**加密方式类型*/
	private static final String SHA = Config.type.SHA.toString();

	/**
	 * @Description: 创建SHA加密字符串 .<br>
	 * @param @param decript 传入的字符串 .<br>
	 * @return String .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:57:17.<br>
	 */
	public static String sha(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance(SHA);
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// 创建SHA字符串
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * @Description: 测试加密 .<br>
	 * @param args .<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午10:23:26.<br>
	 */
	public static void main(String[] args) {
		System.out.println(sha("123456"));
	}
}
