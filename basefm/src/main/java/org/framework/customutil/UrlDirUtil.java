package org.framework.customutil;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class UrlDirUtil {

	/**
	 * @功能 获取类加载的根路径.<br>
	 * @return
	 */
	public static String getClassRootPath(Class<?> cls){
		String path = cls.getClass().getResource("/").getPath();
		File f = new File(path);
		return f.toString();
	}
	
	/**
	 * @功能 获取类加载的全路径.<br>
	 * @return
	 */
	public static String getClassPath(Class<?> cls){
		String path = cls.getResource("").getPath();
		File f = new File(path);
		return f.toString();
	}
	
	/**
	 * @功能 获取项目路径.<br>
	 * @return
	 */
	public static String getProjectPath(){
		String courseFile = null;
		try {
			File directory = new File("");// 参数为空
			courseFile = directory.getCanonicalPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseFile;
	}
	
	/**
	 * @功能 获取getClassLoader路劲.<br>
	 * @return
	 */
	public static String getClassLoaderPath(Class<?> cls){
		URL xmlpath = cls.getClassLoader().getResource("");
		return xmlpath.toString();
	}
	
	/**
	 * @功能 获取系统文件路劲.<br>
	 * @return
	 */
	public static String getFilePath(String filename){
		return System.getProperty(filename);
	}
	
	/**
	 * @功能 获取所有文件路劲.<br>
	 * @return
	 */
	public static List<String> getAllFilePath(){
		String path = System.getProperty("java.class.path");
		return Arrays.asList(path.split(";"));
	}
}
