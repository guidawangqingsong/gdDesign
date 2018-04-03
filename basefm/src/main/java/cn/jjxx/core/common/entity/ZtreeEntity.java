package cn.jjxx.core.common.entity;

import java.util.LinkedHashMap;

public class ZtreeEntity extends TreeEntity<ZtreeEntity>{

	/**
	 * @Fields serialVersionUID : TODO .<br>
	 */
	private static final long serialVersionUID = 307539160288976070L;
	
	private String  pid;//树上级节点
	private String  type;//组织类型
	//private String  level;//层级
	
	private String  icon;//自定义图标（图标路径）
	private String  title;//树名称1
	private boolean isParent;//是否为父节点
	private String  iconSkin;//显示图标
	private LinkedHashMap<String, Object> extend = new LinkedHashMap<String, Object>();// 扩展数据
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public LinkedHashMap<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(LinkedHashMap<String, Object> extend) {
		this.extend = extend;
	}
	public void put(String key, Object value) {
		extend.put(key, value);
	}
	public void remove(String key) {
		extend.remove(key);
	}
	
}
