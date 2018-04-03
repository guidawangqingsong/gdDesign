package cn.jjxx.modules.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class SubmitterListener implements TaskListener{


	private static final long serialVersionUID = -6394541709824149759L;

	@Override
	public void notify(DelegateTask task) {
		//设置当前人为流程提交人
		
	}

}
