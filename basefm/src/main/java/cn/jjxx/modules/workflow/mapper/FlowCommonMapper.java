package cn.jjxx.modules.workflow.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jjxx.modules.workflow.entity.FlowCommon;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface FlowCommonMapper extends BaseMapper<FlowCommon>{

	/**
	 * @Description: 通过单据Id和表名，获取单据信息 .<br>
	 * @param billId 单据Id.<br>
	 * @param tableName 表名.<br> 
	 * @return Map<String,Object> 返回map类型.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-12 上午9:08:21.<br>
	 */
	Map<String, Object> findBillInfo(@Param("billId")String billId, @Param("tableName")String tableName,@Param("idFeild")String idFeild);

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
	boolean updateBillStatus(@Param("billId")String billId,@Param("tableName")String tableName,
			@Param("idFeild")String idFeild,@Param("statusFeild")String statusFeild,@Param("status")int status);

}
