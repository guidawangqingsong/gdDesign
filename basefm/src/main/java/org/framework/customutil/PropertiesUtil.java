package org.framework.customutil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

/**
 * 
 * @author  郑成功
 *
 */
public class PropertiesUtil {
	private String properiesName = "";

	public PropertiesUtil() {

	}
	public PropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}
	
	/**
	 * @功能: 通过传入key，读取配置文件的值 .</br>
	 * @参数 @param key 传入的值.</br>
	 * @返回 String .</br> 
	 * @作者 郑成功 .</br>
	 * @日期 2017-5-19 .</br>
	 */
	public String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			Properties p = new Properties();
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * @功能: 获取配置文件所有配置信息 .</br>
	 * @返回 Properties 所有配置文件信息.</br> 
	 * @作者 郑成功 .</br>
	 * @日期 2017-5-19 .</br>
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	/**
	 * @功能: 像配置文件写入配置信息数据 .</br>
	 * @参数 @param key 写入的键.</br>
	 * @参数 @param value 写入的值.</br>  
	 * @作者 郑成功 .</br>
	 * @日期 2017-5-19 .</br>
	 */
	public void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			String path = this.getClass().getClassLoader().getResource(properiesName).getPath().substring(1); 
			is = new FileInputStream(path);
			p.load(is);
			os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != os)
					os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {
		PropertiesUtil p = new PropertiesUtil("g.properties");
		p.writeProperty("test", "测试数据");
		//p.writeProperty("test", "测试数据");
		//org.jeecgframework.core.util.LogUtil.info(p.readProperty("namess"));
       
	}

}
