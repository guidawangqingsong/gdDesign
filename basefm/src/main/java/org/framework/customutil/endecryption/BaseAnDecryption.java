package org.framework.customutil.endecryption;

public class BaseAnDecryption {

	/**
	 * @Description: 可逆的加密算法,传入需要解密的字符串 ，得到可逆的字符串（可以配合MD5双重加密,增加字符串的安全性）.<br>
	 * @param @param inStr 需要加密的字符串.<br>
	 * @return String 加密后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:26:33.<br>
	 */
	public static String reversible(String inStr) {  
		char[] a = inStr.toCharArray();  
		for (int i = 0; i < a.length; i++) {  
			a[i] = (char) (a[i] ^ 't');  
		}  
		String s = new String(a);  
		return s;  
	}  
	  
	/**
	 * @Description: 通过可逆加密算法，解密传入的字符串 .<br>
	 * @param @param inStr 可逆加密算法后的字符串.<br>
	 * @return String 原始字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午9:29:52.<br>
	 */
	public static String decrypt(String inStr) {  
		char[] a = inStr.toCharArray();  
		for (int i = 0; i < a.length; i++) {  
			a[i] = (char) (a[i] ^ 't');  
		}  
		String k = new String(a);  
		return k;  
	} 
}
