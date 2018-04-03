package cn.jjxx.core.tags.form;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.support.BindStatus;

import cn.jjxx.core.tags.form.support.CustomBindStatus;

import org.springframework.beans.PropertyAccessor;

@SuppressWarnings("serial")
public class HiddenInputTag extends org.springframework.web.servlet.tags.form.HiddenInputTag {

	private BindStatus bindStatus = null;
	private Boolean nested = true; // 是否嵌套使用Form自定的模型，模式为真,
	private String valueway = "";// 1,bean
									// 2,通过参数获取。3.获取getAttribute中的值
	private String defaultValue = "";// 当所有值都无效的时候，是由默认值
	
	private Object showValue = "";// 优先使用该值

	@Override
	protected BindStatus getBindStatus() throws JspException {
		if (this.bindStatus == null) {
			// HTML escaping in tags is performed by the ValueFormatter class.
			String nestedPath = "";
			if (nested) {
				nestedPath = getNestedPath();
			}
			String pathToUse = (nestedPath != null ? nestedPath + getPath() : getPath());
			if (pathToUse.endsWith(PropertyAccessor.NESTED_PROPERTY_SEPARATOR)) {
				pathToUse = pathToUse.substring(0, pathToUse.length() - 1);
			}
//			this.bindStatus = CustomBindStatus.create(pageContext, pathToUse, getRequestContext(), false, nested,
//					valueway, defaultValue);
			//之前作者写的多选框有回显的问题，重新写构造方法通过showvalue来设置回显的值
			this.bindStatus = CustomBindStatus.createMulit(pageContext, pathToUse, getRequestContext(), false, nested,
					valueway, defaultValue,showValue);
		}
		return this.bindStatus;
	}

	public Boolean getNested() {
		return nested;
	}

	public void setNested(Boolean nested) {
		this.nested = nested;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getValueway() {
		return valueway;
	}

	public void setValueway(String valueway) {
		this.valueway = valueway;
	}

	public Object getShowValue() {
		return showValue;
	}

	public void setShowValue(Object showValue) {
		this.showValue = showValue;
	}

	@Override
	public void doFinally() {
		super.doFinally();
		this.bindStatus = null;
	}

}
