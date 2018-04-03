package org.framework.customutil.sms;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Title: MapToBean.java .<br>
 * @Package org.framework.customutil.sms .<br>
 * @Description: map与实体类之间的转换 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-18 下午5:26:13.<br>
 * @version V1.0.<br>
 */
public class MapToBean {

	/**
	 * @Description: 将map转换成实体类 .<br>
	 * @param map 需要转换的map.<br>
	 * @param bean 实体类.<br>   
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 下午5:09:53.<br>
	 */
	public static void setValue(Map<String,Object> map,Object bean)  {  
		Set<String> set = map.keySet();  
		Iterator<String> iterator = set.iterator();  
		while (iterator.hasNext())  {  
			Object obj = iterator.next();  
			Object val = map.get(obj);  
			setMethod(obj, val, bean);  
		}  
	}
	
	/**
	 * @Description: 将map的数据驯如到实体类里面 .<br>
	 * @param method 方法 .<br>
	 * @param value 值 .<br>
	 * @param bean 实体类.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 下午5:23:44.<br>
	 */
	public static void setMethod(Object method, Object value ,Object bean)  {  
	    Class<?> c;  
	    try{  
	    	c = Class.forName(bean.getClass().getName());  
	    	String met = (String) method;  	      
	    	met = met.trim();  
	    	if(met.equals(SMSConfig.other.SHORT.toString().toLowerCase())){
	    		met = met+SMSConfig.other.name.toString();
	    	}
	    	if (!met.substring(0, 1).equals(met.substring(0, 1).toUpperCase())){  
	    		met = met.substring(0, 1).toUpperCase() + met.substring(1);  
	    	}  
	    	if (!String.valueOf(method).startsWith(SMSConfig.other.set.toString()))  {  
	    		met = SMSConfig.other.set.toString() + met;  
	    	}  
	    	Class<?> types[] = new Class[1];  
	    	types[0] = Class.forName(SMSConfig.FEILD_TYPE);  
	    	Method m = c.getMethod(met, types);  
	    	m.invoke(bean, value);  
    	}catch (Exception e){  
    		e.printStackTrace();  
	    }  
	}
	
	/**
	 * @Description: 将实体类转换成map .<br>
	 * @param @param bean 需要转换的实体类.<br>
	 * @return Map<String,Object> 返回转换的map.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 下午5:22:13.<br>
	 */
	public static Map<String,Object> getValue(Object bean){  
	    Map<String,Object> map = new HashMap<String,Object>();  
	    Class<?> c;  
	    try{  
	    	c = Class.forName(bean.getClass().getName());  
	    	Method[] m = c.getMethods();  
	    	for (int i = 0; i < m.length; i++){  
	    		String method = m[i].getName();  
	    		if (method.startsWith(SMSConfig.other.get.toString())){  
	    			try{  
	    				Object value = m[i].invoke(bean);  
	    				if (value != null){  
	    					String key=method.substring(3);  
	    					key=key.substring(0,1).toUpperCase()+key.substring(1);  
	    					map.put(method, value);  
	    				}  
	    			}catch (Exception e) {  
	    			}  
	    		}  
	    	}  
    	}catch (Exception e){  
	      e.printStackTrace();  
	    }  
	    return map;  
	} 
	
}
