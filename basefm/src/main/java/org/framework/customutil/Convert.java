package org.framework.customutil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: Convert.java .<br>
 * @Package org.framework.core.utils .<br>
 * @Description: 该类是参数获取工具类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-10-31 上午9:37:37.<br>
 * @version V1.0.<br>
 */
public class Convert {

	/**
	 * @Description: 通过http请求，获取http传递的参数，并设置当前登录人员信息 .<br>
	 * @param request http请求.<br> 
	 * @return Map<String,Object> 返回map键值对.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-10-31 上午9:38:34.<br>
	 */
	public static Map<String,Object> getMapByReq(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		Enumeration<?> paramNames = request.getParameterNames(); 
		while (paramNames.hasMoreElements()){  
			 String paramName = (String) paramNames.nextElement();
	    	 String[] paramValues=request.getParameterValues(paramName);
	    	 if(paramValues.length==1){
	    		 String pv=null;
				try {
					pv = new String(request.getParameter(paramName));
				} catch (Exception e) {
					e.printStackTrace();
				}
		    		 map.put(paramName,pv);
		    }
	    	 else map.put(paramName, paramValues);
		}
		return map;
	}
	
	/**
	 * @Description: 通过class的名称，获取该class的字段属性，返回list .<br>
	 * @param classname class类的名称.<br>
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-10-31 上午9:40:11.<br>
	 */
	public static List<String> getFilde(String classname){
		List<String> list=new ArrayList<String>();
		Class<?> cl=null;
		try {
			cl = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Field[] fileds=cl.getDeclaredFields();
		for(Field f:fileds){
			list.add(f.getName());
		}
		return list;
	}
	
	/**
	 * @Description: 通过class的类名称，获取sql语句 .<br>
	 * @param classname class的类名称.<br>
	 * @param request .<br>
	 * @return String .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-10-31 上午9:41:44.<br>
	 */
	public static String getSql(String classname,HttpServletRequest request){
		Class<?> cl=null;
		try {
			cl = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Field[] fields=cl.getDeclaredFields();
		StringBuilder sb=new StringBuilder();
		
		String reqfieldstr=request.getParameter("field");
		String[] reqfields=reqfieldstr.split(",");
		for(String fieldname:reqfields){
			for(Field field:fields){
				if(fieldname.equals(field.getName()) && request.getParameter(fieldname)!=null && request.getParameter(fieldname).length()>0){
					if(field.getType().toString().equals("class java.lang.String")){
						sb.append(" and "+fieldname+"='"+request.getParameter(fieldname)+"' ");
					}
				}
				
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {

	}

}
