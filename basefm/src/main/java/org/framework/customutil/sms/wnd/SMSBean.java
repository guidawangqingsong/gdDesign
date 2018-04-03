package org.framework.customutil.sms.wnd;

import java.io.Serializable;

public class SMSBean implements Serializable{

	private static final long serialVersionUID = 5671049852518370843L;
	
	/**已成功提交短信条数*/
	private String num;
	/**群发目标手机号*/
	private String dst;
	/**发送短信内容*/
	private String msg;
	/**长号码*/
	private String sender;
	/**定时时间*/
	private String time;
	/**序列号*/
	private String sequeid;
	/**成功提交的手机号*/
	private String success;
	/**发送失败的手机号*/
	private String faile;
	/**发送错误原因*/
	private String err;
	/**具体错误编码*/
	private String errid;
	/**短信余额*/
	private String id;
	/**企业简称*/
	private String shortname;
	/***/
	private String mmsid;
	
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSequeid() {
		return sequeid;
	}
	public void setSequeid(String sequeid) {
		this.sequeid = sequeid;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getFaile() {
		return faile;
	}
	public void setFaile(String faile) {
		this.faile = faile;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getErrid() {
		return errid;
	}
	public void setErrid(String errid) {
		this.errid = errid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMmsid() {
		return mmsid;
	}
	public void setMmsid(String mmsid) {
		this.mmsid = mmsid;
	}
}
