package cn.jjxx.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import cn.jjxx.core.common.entity.DataEntity;

import java.lang.String;
import java.util.Date;

@TableName("sys_attachment")
@SuppressWarnings("serial")
public class Attachment extends DataEntity<String> {

	@TableId(value = "id", type = IdType.UUID)
	private String id;
	@TableField(value = "filename")
	private String filename; // 文件名称

	@TableField(value = "filepath")
	private String filepath;// 文件路径

	@TableField(value = "filesize")
	private Long filesize;// 文件大小

	@TableField(value = "fileext")
	private String fileext;// 文件扩展名

	@TableField(value = "userid", el = "user.id")
	private User user; // 用戶ID

	@TableField(value = "uploadtime")
	private Date uploadtime; // 上传时间

	@TableField(value = "uploadip")
	private String uploadip;// 上传的ID

	@TableField(value = "status")
	private String status;// 状态
	
	@TableField(value = "bill_id")
	private String billId;// 单据id
	
	@TableField(value = "relate_table")
	private String relateTable;//关联表
	
	@TableField(value = "relate_feild")
	private String relateFeild;//关联表字段

	/**
	 * 获取 id
	 * 
	 * @return: String 主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 * 
	 * @param: id
	 *             主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getUploadip() {
		return uploadip;
	}

	public void setUploadip(String uploadip) {
		this.uploadip = uploadip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillid(String billId) {
		this.billId = billId;
	}

	public String getRelateTable() {
		return relateTable;
	}

	public void setRelateTable(String relateTable) {
		this.relateTable = relateTable;
	}

	public String getRelateFeild() {
		return relateFeild;
	}

	public void setRelateFeild(String relateFeild) {
		this.relateFeild = relateFeild;
	}
	
}
