package cn.jjxx.modules.workflow.service;

import java.util.Map;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.workflow.entity.FlowCommon;
/**
 * @Title: IFlowCommonService.java .<br>
 * @Package cn.jjxx.modules.workflow.service .<br>
 * @Description: 工作流单据信息服务类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-12-12 上午9:07:43.<br>
 * @version V1.0.<br>
 */
public interface IFlowCommonService  extends ICommonService<FlowCommon>{

	/**
	 * @Description: 通过单据Id和表名，获取单据信息 .<br>
	 * @param billId 单据Id.<br>
	 * @param tableName 表名.<br> 
	 * @param idFeild 主键字段名.<br>
	 * @return Map<String,Object> 返回map类型.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-12 上午9:08:21.<br>
	 */
	Map<String,Object> findBillInfo(String billId,String tableName,String idFeild);
	
	/**
	 * @Description: 修改单据的状态 .<br>
	 * @param billId 单据Id.<br>
	 * @param tableName 表的名称.<br>
	 * @param idFeild 主键字段名.<br>
	 * @param statusFeild 需要修改的状态字段.<br>
	 * @param status 状态.<br>
	 * @return boolean 返回是否更新单据成功.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-12 上午9:45:29.<br>
	 */
	boolean updateBillStatus(String billId,String tableName,String idFeild,String statusFeild,int status);
}
