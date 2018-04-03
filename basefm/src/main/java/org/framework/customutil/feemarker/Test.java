package org.framework.customutil.feemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.framework.customutil.feemarker.word.ExportWordUtil;
import org.framework.customutil.feemarker.word.FileToBASE64Encoder;


public class Test {

	   public static void exportSimpleWord(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	       // 要填充的数据, 注意map的key要和word中${xxx}的xxx一致  
	      Map<String,Object> dataMap = new HashMap<String,Object>();  
	      dataMap.put("username", "张三"); 
	      dataMap.put("sex", "男");  
	      dataMap.put("age", "18");  
	      dataMap.put("phone", "15285112398");  
	      dataMap.put("a", "贵州大学");  
	      dataMap.put("b", "电信专业");  
	      dataMap.put("c", "贵州");  
	      dataMap.put("d", "汉族");  
	      
	      dataMap.put("imgStr", FileToBASE64Encoder.getImageStr("d:/aa.jpg"));
	      List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
	        for (int i = 0; i < 30; i++) {  
	            Map<String,Object> map = new HashMap<String,Object>();  
	            map.put("family", "家人"+i);  
	            map.put("relate", "关系"+i);  
	            map.put("tell", "电话"+i);  
	            map.put("zy", "务农"+i);  
	            list.add(map);  
	        }
	      dataMap.put("userList", list);  
	      
	     //ExportWordUtil.exportFile(dataMap, "D:/", "userinfo1", "D:/", "人力资源表");
	      
	     String path =  request.getRealPath("/"+"templetes");
	     ExportWordUtil.exportFile(response, dataMap, path, "userinfo1", "个人信息表");
	   }  
	   
	   public static void main(String[] args) {
		   try {
			   exportSimpleWord(null,null);
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
}
