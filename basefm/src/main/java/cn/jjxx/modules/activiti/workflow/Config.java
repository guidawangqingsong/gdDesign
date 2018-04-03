package cn.jjxx.modules.activiti.workflow;

/**
 * @Title: Config.java .<br>
 * @Package org.framework.activiti.workflow .<br>
 * @Description: activiti基础公用的相关信息 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-21 下午2:47:50.<br>
 * @version V1.0.<br>
 */
public class  Config {
	
	/**下划线*/
	public static final String UNDERLINE = "_";
	
	/**会签*/
	public static final String JOIN_PROCESS = "-会签";

	/**
	 * @Title: Config.java .<br>
	 * @Package org.framework.activiti.workflow .<br>
	 * @Description: 存放相关信息变量的键名 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-21 下午2:45:43.<br>
	 * @version V1.0.<br>
	 */
	public enum variable{
		表单信息,任务流程变量,操作信息
	}
	
	/**
	 * @Title: Config.java .<br>
	 * @Package org.framework.activiti.workflow .<br>
	 * @Description: 一些固定字段key的名称 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-21 下午2:46:46.<br>
	 * @version V1.0.<br>
	 */
	public enum constant {
		id,policy,customAuditor,submitUser,type
	}
	
	/**
	 * @Title: Config.java .<br>
	 * @Package org.framework.activiti.workflow .<br>
	 * @Description: activiti节点的类型 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-21 下午2:47:26.<br>
	 * @version V1.0.<br>
	 */
	public enum activiNodeType{
		start,end,jointProcess,parallelGateway,exclusiveGateway,userTask,startEvent
	}
}
