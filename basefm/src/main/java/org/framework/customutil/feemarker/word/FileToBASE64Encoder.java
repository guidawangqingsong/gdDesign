package org.framework.customutil.feemarker.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.misc.BASE64Encoder;

/**
 * @Title: FileToBASE64Encoder.java .<br>
 * @Package org.framework.customutil.feemarker.word .<br>
 * @Description: 将文件转换成 BASE64Encoder格式的工具类.<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-30 下午2:28:40.<br>
 * @version V1.0.<br>
 */
public class FileToBASE64Encoder {

	/**
	 * @Description: 传入图片路径，将图片转换成BASE64Encoder的格式 .<br>
	 * @param @param imgPath 图片的路径.<br>
	 * @return String 返回解析后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:24:06.<br>
	 */
	public static String getImageStr(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
	
	/**
	 * @Description: 传入流文件，将流文件转换成BASE64Encoder的格式  .<br>
	 * @param @param is 输入流.<br>
	 * @return String 返回解析后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:25:41.<br>
	 */
	public static String getImageStr(InputStream is){
		byte[] data = null;
		try {
            data = new byte[is.available()];
            is.read(data);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
	}
	
	/**
	 * @Description: 传入文件流，将文件流转换成BASE64Encoder的格式  .<br>
	 * @param @param fis 文件流.<br>
	 * @return String 返回解析后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:25:41.<br>
	 */
	public static String getImageStr(FileInputStream fis){
		byte[] data = null;
		try {
			data = new byte[fis.available()];
			fis.read(data);
			fis.close();
		} catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
	}
	
	/**
	 * @Description: 传入文件，将文件转换成BASE64Encoder的格式   .<br>
	 * @param @param file 文件.<br>
	 * @return String 返回解析后的字符串.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:27:29.<br>
	 */
	public static String getImageStr(File file){
		byte[] data = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			data = new byte[is.available()];
			is.read(data);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
	}
	
}
