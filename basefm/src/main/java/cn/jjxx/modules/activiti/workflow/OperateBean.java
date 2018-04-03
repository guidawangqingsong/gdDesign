package cn.jjxx.modules.activiti.workflow;

import java.io.Serializable;
import java.util.UUID;

/**
 * @Title: OperateBean.java .<br>
 * @Package org.framework.activiti.workflow .<br>
 * @Description: 审批处理实体类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-28 下午3:00:17.<br>
 * @version V1.0.<br>
 */
public class OperateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键*/
	private String id = UUID.randomUUID().toString();
	/**决策(0=同意;1=不同意;2=驳回)*/
	private int policy = 0;
	/**操作类型(审批通过,审批不通过)*/
	private String operate;
	/**审批意见*/
	private String opinion;
	/**下一步审核人*/
	private String customAuditor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPolicy() {
		return policy;
	}

	public void setPolicy(int policy) {
		this.policy = policy;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getCustomAuditor() {
		return customAuditor;
	}

	public void setCustomAuditor(String customAuditor) {
		this.customAuditor = customAuditor;
	}
	
}
