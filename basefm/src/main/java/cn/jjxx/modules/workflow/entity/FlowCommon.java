package cn.jjxx.modules.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import cn.jjxx.core.common.entity.AbstractEntity;

public class FlowCommon  extends AbstractEntity<String> {

	/**
	 * @Fields serialVersionUID : TODO .<br>
	 */
	private static final long serialVersionUID = -6153866741573928616L;
	
	
	@TableField(exist=false)
    private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
