package org.framework.customutil;




public class zTreeIconUtil {
	
	
	public static final String WBS_PARENT_ICON = "diy_wbs";//wbs图标
	public static final String ORG_PARENT_ICON = "diy_org";//组织图标
	public static final String BD_ORG_ICON = "diy_bd";//标段组织图标
	/**
	 * @功能 根据组织类型编号获取ztree图标.<br>
	 * @return
	 */
	public static String getTypeIdMyIcon(String typeid){
		String icon = null;
		
		if(typeid.indexOf("1") != -1){
			//组织
			icon = ORG_PARENT_ICON;
		}else{
			//wbs
			icon = WBS_PARENT_ICON;
		}
		if(typeid.indexOf("5") != -1){
			//标段
			icon = BD_ORG_ICON;
		}
		System.out.println("===="+icon);
		return icon;
	}
	
	
}
