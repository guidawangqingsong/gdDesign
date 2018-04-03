package org.framework.customutil.endecryption.aes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.framework.customutil.endecryption.BaseAnDecryption;
import org.framework.customutil.endecryption.Config;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Title: AESHelper.java .<br>
 * @Package org.framework.core.utils.endecryption.aes .<br>
 * @Description: 通过AES对称算法，加解密字符串 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-18 上午11:08:22.<br>
 * @version V1.0.<br>
 */
public class AESHelper extends BaseAnDecryption{
	
	/**加密方式类型*/
	private static final String AES = Config.type.AES.toString();

	/**
	 * @Description: 通过AES对称算法，加密字符串 .<br>
	 * @param encodeRules 加密的规则.<br>
	 * @param content 需要加密的内容 .<br>
	 * @return String 加密后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 上午10:57:39.<br>
	 */
    public static String AESEncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance(AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
              //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
              //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, AES);
              //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance(AES);
              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes(Config.UTF_8);
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
          //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
          //11.将字符串返回
            return AES_encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;         
    }
    
    /**
     * @Description: 通过AES对称算法，解密字符串 .<br>
     * @param encodeRules 解密的规则（与加密的规则必须一致）.<br>
	 * @param content 需要解密的内容 .<br>
	 * @return String 解密后的字符串.<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-18 上午11:04:14.<br>
     */
    public static String AESDncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance(AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
              //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
              //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, AES);
              //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance(AES);
              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,Config.UTF_8);
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;         
    }
    
    /**
     * @Description: 测试AES加密解密算法 .<br>
     * @return void .<br> 
     * @author 郑成功 .<br>
     * @date 2017-9-18 上午11:07:28.<br>
     */
    public static void main(String[] args) {

        String encodeRules = "123456";
        String content = "加解密算法";
        //加密
        System.out.println("根据输入的规则"+encodeRules+"加密后的密文是:"+AESEncode(encodeRules, content));
        //解密
        System.out.println("根据输入的规则"+encodeRules+"解密后的明文是:"+AESDncode(encodeRules, AESEncode(encodeRules, content)));
    }
}
