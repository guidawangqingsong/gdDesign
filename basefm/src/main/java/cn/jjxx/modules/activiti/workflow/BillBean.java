package cn.jjxx.modules.activiti.workflow;

import java.io.Serializable;
import java.util.UUID;

import cn.jjxx.modules.activiti.config.Contacts;

/**
 * @Title: BillBean.java .<br>
 * @Package org.framework.activiti.workflow .<br>
 * @Description: 流程审批表单信息实体类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-28 下午2:58:32.<br>
 * @version V1.0.<br>
 */
public class BillBean implements Serializable{

	private static final long serialVersionUID = 4973711095690050528L;
	
	/**id*/
	private String id = UUID.randomUUID().toString();
	/**表单的请求地址*/
	private String formUrl;
	/**mapper控件名*/
	private String mapperSpace;
	/**mapper查询表单唯一信息的方法Id*/
	private String billformId;
	/**mapper更新表单信息的方法Id*/
	private String updateId;
	/**单据Id*/
	private String billId;
	/**状态字段名*/
	private String statusFeild = Contacts.billFeilds.status.toString();
	/**单据中文名称*/
	private String billName;
	/**启动流程的KEY值*/
	private String key;
	/**表的主键字段*/
	private String idFeild = Contacts.modelKey.id.toString();
	/**表的名称*/
	private String tableName;
	/**提交人*/
	private String submitter;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFormUrl() {
		return formUrl;
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	public String getMapperSpace() {
		return mapperSpace;
	}
	public void setMapperSpace(String mapperSpace) {
		this.mapperSpace = mapperSpace;
	}
	public String getBillformId() {
		return billformId;
	}
	public void setBillformId(String billformId) {
		this.billformId = billformId;
	}	
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStatusFeild() {
		return statusFeild;
	}
	public void setStatusFeild(String statusFeild) {
		this.statusFeild = EntityToTable(statusFeild);
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIdFeild() {
		return idFeild;
	}
	public void setIdFeild(String idFeild) {
		this.idFeild = EntityToTable(idFeild);
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = EntityToTable(tableName);
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	
	public static String EntityToTable(String str){
		StringBuffer table = new StringBuffer();
		int i = 0;  
        while(i < str.length()){  
            char chr = str.charAt(i);  
            if(Character.isUpperCase(chr)){  
            	table.append("_"+String.valueOf(chr).toLowerCase());  
            }else if(Character.isLowerCase(chr)){  
            	table.append(chr);  
            }  
            i++;  
        } 
        return table.toString();
	}
	
}
