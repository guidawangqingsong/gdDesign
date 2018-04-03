package cn.jjxx.modules.activiti.config;

import java.util.UUID;

public class Contacts {

	public static final String ACTIVITI_NAMESPACE = "http://b3mn.org/stencilset/bpmn2.0#";
	
	public static final String ACTIVITI_BPMN_SUFF = ".bpmn20.xml";
	/**流程单据审核-保存*/
	public static final int SAVE = 0;
	/**流程单据审核-提交*/
	public static final int SUBMIT = 1;
	/**流程单据审核-审核中*/
	public static final int AUDITING = 2;
	/**流程单据审核-已审核*/
	public static final int AUDITED = 3;
	/**流程单据审核-打回*/
	public static final int BIT_BACK = 5;
	
	public enum modelKey{
		id,resourceId,namespace,stencilset,L
	}
	
	public enum modelValue{
		canvas
	}
	
	public static String getUUId(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public enum billFeilds{
		id,status
	}
	
}
