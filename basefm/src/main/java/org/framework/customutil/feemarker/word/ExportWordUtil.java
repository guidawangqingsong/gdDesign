package org.framework.customutil.feemarker.word;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.framework.customutil.feemarker.BaseConfig;


/**
 * @Title: ExportWordUtil.java .<br>
 * @Package org.framework.customutil.feemarker.word .<br>
 * @Description: 这个是对feemarker模板word导出的封装,具体相关资料可以参考feemarker的相关介绍 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-30 下午3:08:37.<br>
 * @version V1.0.<br>
 */
public class ExportWordUtil extends BaseConfig{
	
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
	 * @Description: 通过freemarker导出word文件 .<br>
	 * @param dataMap 数据集合 .<br>
	 * @param TemplatePath 模板的路径.<br>
	 * @param TemplateName 模板的名称.<br>
	 * @param absolutePath 生成文件的绝对路径.<br>
	 * @param filename 文件名称.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:55:17.<br>
	 */
	public static void exportFile(Map<String,Object> dataMap,String TemplatePath,String TemplateName,String absolutePath,String filename){
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
		outFileTemplate(WORD_SUFF);
	}
	
	/**
	 * @Description: 通过htt的response,将生成的word输出到界面 .<br>
	 * @param response http的响应.<br>
	 * @param dataMap 数据集合.<br>
	 * @param relativePath 模板的路径,例如：request.getRealPath("/")+"/templete".<br>
	 * @param TemplateName 模板的名称.<br>
	 * @param filename 输出文件的名称.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午2:55:27.<br>
	 */
	public static void exportFile(HttpServletResponse response,Map<String,Object> dataMap,String relativePath,String TemplateName,String filename){
		TEMPLATE_PATH = relativePath;
		TEMPLATE_NAME = TemplateName;
		DATA_MAP = dataMap;
		FILE_NAME = filename;
		if(TemplateName==null){
			FILE_NAME = TemplateName;
		}else{
			FILE_NAME = filename;
		}
		responseFileTemplate(response,WORD_SUFF);
	}
	
}
