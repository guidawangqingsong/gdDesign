package org.framework.customutil.feemarker.html;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.framework.customutil.feemarker.BaseConfig;


/**
 * @Title: HtmlFreemarkerUtil.java .<br>
 * @Package org.framework.customutil.feemarker.html .<br>
 * @Description: html模板工具类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-11-1 上午11:34:23.<br>
 * @version V1.0.<br>
 */
public class HtmlFreemarkerUtil  extends BaseConfig{

	/**
	 * @Description: 设置默认的编码格式 .<br>
	 * @param encoded 编码格式，默认是utf-8.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:54:41.<br>
	 */
	public void setEncoded(String encoded){
		default_encoded = encoded;
	}
	
	/**
	 * @Description: 通过freemarker导出html文件 .<br>
	 * @param dataMap 数据集合 .<br>
	 * @param TemplatePath 模板的路径.<br>
	 * @param TemplateName 模板的名称.<br>
	 * @param absolutePath 生成文件的绝对路径.<br>
	 * @param filename 文件名称.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-11-1 下午2:55:17.<br>
	 */
	public static void exportExcelFile(Map<String,Object> dataMap,String TemplatePath,String TemplateName,String absolutePath,String filename){
		TEMPLATE_PATH = TemplatePath;
		DATA_MAP = dataMap;
		TEMPLATE_NAME = TemplateName;
		if(absolutePath==null){
			OUTPUT_PATH = TemplatePath;
		}else{
			OUTPUT_PATH = absolutePath;
		}
		if(TemplateName==null){
			FILE_NAME = TemplateName;
		}else{
			FILE_NAME = filename;
		}
		outFileTemplate(HTML_SUFF);
	}
	
	/**
	 * @Description: 通过htt的response,将生成的html输出到界面 .<br>
	 * @param response http的响应.<br>
	 * @param dataMap 数据集合.<br>
	 * @param relativePath 模板的路径,例如：request.getRealPath("/")+"/templete".<br>
	 * @param TemplateName 模板的名称.<br>
	 * @param filename 输出文件的名称.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:55:27.<br>
	 */
	public static void exportExcelFile(HttpServletResponse response,Map<String,Object> dataMap,String relativePath,String TemplateName,String filename){
		TEMPLATE_PATH = relativePath;
		TEMPLATE_NAME = TemplateName;
		DATA_MAP = dataMap;
		FILE_NAME = filename;
		if(TemplateName==null){
			FILE_NAME = TemplateName;
		}else{
			FILE_NAME = filename;
		}
		responseFileTemplate(response,HTML_SUFF);
	}
}
