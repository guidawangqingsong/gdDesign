package org.framework.customutil.endecryption.md5;

import java.security.MessageDigest;

import org.framework.customutil.endecryption.BaseAnDecryption;
import org.framework.customutil.endecryption.Config;

/**
 * @Title: MD5Helper.java .<br>
 * @Package org.framework.customutil.endecryption.md5 .<br>
 * @Description: MD5加密解密工具类，（MD5是不能逆向解密的） .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-18 上午9:32:03.<br>
 * @version V1.0.<br>
 */
public class MD5Helper extends BaseAnDecryption{
	
	private static final String MD5 = Config.type.MD5.toString();

	/**
	 * @Description: 传入需要加密的字符串，生成32位长度的加密字符串 .<br>
	 * @param @param encryptStr 需要加密的字符串 .<br>
	 * @return String 返回加密后的32位字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:15:47.<br>
	 */
	public static String encrypt32(String encryptStr) {  
        MessageDigest md5;  
        try {  
            md5 = MessageDigest.getInstance(MD5);  
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());  
            StringBuffer hexValue = new StringBuffer();  
            for (int i = 0; i < md5Bytes.length; i++) {  
                int val = ((int) md5Bytes[i]) & 0xff;  
                if (val < 16)  
                    hexValue.append("0");  
                hexValue.append(Integer.toHexString(val));  
            }  
            encryptStr = hexValue.toString();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        return encryptStr;  
    }
	
	/**
	 * @Description: 传入需要加密的字符串，生成16位长度的加密字符串 .<br>
	 * @param @param encryptStr 需要加密的字符串 .<br>
	 * @return String 返回加密后的16位字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:15:47.<br>
	 */
	public static String encrypt16(String encryptStr) {  
        return encrypt32(encryptStr).substring(8, 24);  
    }
	
	/**
	 * @Description: 测试加密解密算法 .<br>
	 * @param @param args .<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:31:21.<br>
	 */
	public static void main(String[] args) {  
        String encryptStr = "2222,./!@#$%^&*()";  
        System.out.println(MD5Helper.encrypt32(encryptStr));  
        System.out.println(MD5Helper.encrypt16(encryptStr));  
        
        System.out.println(reversible(encryptStr));  
        System.out.println(decrypt(reversible(encryptStr)));  
    }
}
