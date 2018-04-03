package org.framework.customutil.excel.entity;

import java.io.Serializable;

public class ExcelModel implements Serializable,Comparable<ExcelModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030956621749277051L;
	
	/**excel字段的key*/
	private String feildKey;
	/**excel字段的对应的中文*/
	private String feildValue;
	/**excel字段表头排序*/
	private int sorting = 0;
	
	public String getFeildKey() {
		return feildKey;
	}
	public void setFeildKey(String feildKey) {
		this.feildKey = feildKey;
	}
	public String getFeildValue() {
		return feildValue;
	}
	public void setFeildValue(String feildValue) {
		this.feildValue = feildValue;
	}
	public int getSorting() {
		return sorting;
	}
	public void setSorting(int sorting) {
		this.sorting = sorting;
	}
	
	@Override
	public int compareTo(ExcelModel o) {		
		return this.getSorting()-o.getSorting();
	}
	
	
	

}
