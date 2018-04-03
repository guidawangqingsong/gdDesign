package org.framework.customutil.feemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class BaseConfig {

	/**word模板的路径*/
	protected static String TEMPLATE_PATH;
	/**输出的数据集合*/
	protected static Map<String,Object> DATA_MAP;
	/**输出的文件名称*/
	protected static String FILE_NAME;
	/**模板文件的名称*/
	protected static String TEMPLATE_NAME;
	/**word输出的路径*/
	protected static String OUTPUT_PATH;
	/**斜杠分隔符*/
	protected static final String SPRIT = "/";
	/**模板的后缀名称*/
	protected static final String TEMPLATE_SUFF = ".ftl";
	/**word的后缀名称*/
	protected static final String WORD_SUFF = ".doc";
	/**excel的后缀名称*/
	protected static final String EXCEL_SUFF = ".xls";
	/**html的后缀名称*/
	protected static final String HTML_SUFF = ".html";
	/**xml的后缀名称*/
	protected static final String XML_SUFF = ".xml";
	/**默认的编码格式*/
	protected static String default_encoded = "utf-8";
	
	/**
	 * @Description: 通过模板路径和模板名称,得到模板信息 .<br>
	 * @param TemplatePath 模板路径.<br>
	 * @param TemplateName 模板名称.<br>
	 * @return Template 返回模板.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午3:03:29.<br>
	 */
	@SuppressWarnings("deprecation")
	public static Template getTemplateWord(String TemplatePath,String TemplateName ){
		Configuration configuration = new Configuration();  
		Template t = null;
		try {
			configuration.setDefaultEncoding(default_encoded);
	        File file = new File(TEMPLATE_PATH);
	    	configuration.setDirectoryForTemplateLoading(file);
	        t = configuration.getTemplate(TEMPLATE_NAME+TEMPLATE_SUFF,default_encoded);//以utf-8的编码读取ftl文件  
		} catch (Exception e) {
			e.printStackTrace();
		}
       return t;
	}
    
	/**
	 * @Description: 根据模板，输出文件到指定的文件夹下 .<br>
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午3:05:34.<br>
	 */
	@SuppressWarnings("deprecation")
	protected static void outFileTemplate(String fileSuf){
		Configuration configuration = new Configuration();  
	    configuration.setDefaultEncoding(default_encoded); 
	    try {
	    	File file = new File(TEMPLATE_PATH);
		    configuration.setDirectoryForTemplateLoading(file);   
		    // 输出文档路径及名称  
		    File outFile = new File(OUTPUT_PATH+FILE_NAME+fileSuf);  
		    //以utf-8的编码读取ftl文件  
		    Template t =  configuration.getTemplate(TEMPLATE_NAME+TEMPLATE_SUFF,default_encoded);  
		    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), default_encoded),10240); 
		    t.process(DATA_MAP, out); 
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 通过http请求的响应,用response输出 .<br>
	 * @param response http响应.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-30 下午3:06:37.<br>
	 */
	@SuppressWarnings("deprecation")
	protected static void responseFileTemplate(HttpServletResponse response,String fileSuf){
		response.reset();
		Configuration configuration = new Configuration();  
        configuration.setDefaultEncoding(default_encoded);  
        try {
        	String fileName = new String((FILE_NAME+fileSuf).getBytes("GBK"), "iso8859-1");
        	File file = new File(TEMPLATE_PATH);
        	configuration.setDirectoryForTemplateLoading(file);//指定ftl所在目录,根据自己的改  
            response.setContentType("application/msword");  
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");  
            response.setCharacterEncoding(default_encoded);//此句非常关键,不然word文档全是乱码  
            PrintWriter out = response.getWriter();  
            Template t =  configuration.getTemplate(TEMPLATE_NAME+TEMPLATE_SUFF,default_encoded);//以utf-8的编码读取ftl文件  
            t.process(DATA_MAP, out);
            out.flush();
            out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}                
	}

}
