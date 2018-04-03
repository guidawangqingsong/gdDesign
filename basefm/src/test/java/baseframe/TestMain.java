package baseframe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.jjxx.modules.sys.entity.Menu;

public class TestMain{

	

	  public static void main(String[] args) {
		 /* Comparator<Menu> comparator = new Comparator<Menu>() {
				public int compare(Menu o1, Menu o2) {
					return o1.getSort().compareTo(o2.getSort());
				}
	        };
	        Menu stu1 = new Menu ();
	        stu1.setName("第1");
	        stu1.setSort(1);
	        Menu stu2 = new Menu ();
	        stu2.setName("第2");
	        stu2.setSort(2);
	        Menu stu3 = new Menu ();
	        stu3.setName("第3");
	        stu3.setSort(3);
	        Menu stu4 = new Menu ();
	        stu4.setName("第4");
	        stu4.setSort(4);
	        Menu stu5 = new Menu ();
	        stu5.setName("第5");
	        stu5.setSort(5);
	          ArrayList<Menu> list = new ArrayList<Menu>();
	          list.add(stu1);
	          list.add(stu2);
	          list.add(stu3);
	          list.add(stu4);
	          list.add(stu5); 
	          //这里就会自动根据规则进行排序
	          Collections.sort(list,comparator);
	          for(int i=0;i<list.size();i++){
	              Menu stu=list.get(i);
	              System.out.println("姓名:"+stu.getName()+"排序："+stu.getSort());
	          }*/
	          rep();
	    }
	 
	  public static void rep(){
		  Map<String,Object> mp = new HashMap<String, Object>();
		  mp.put("name", "郑成功");
		  mp.put("phone", "15285112398");
		  mp.put("money", "200");
		  String aa = "尊敬的${name}用户,你好,您的手机号码${phone},在活动中活得${money}元话费,请查收";
		  for (Entry<String, Object> entry : mp.entrySet()) {  
			  String key = entry.getKey();
			  String value = entry.getValue().toString();
			  aa = aa.replace("${"+key+"}", value);
		  }
		  System.out.println(aa);
	  }
	  
}
