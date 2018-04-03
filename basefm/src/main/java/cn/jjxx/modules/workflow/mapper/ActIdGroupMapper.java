package cn.jjxx.modules.workflow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jjxx.modules.workflow.entity.ActIdGroup;
 
/**   
 * @Title: act_id_group数据库控制层接口
 * @Description: 工作流分组数据库控制层接口
 * @author jjxx
 * @date 2017-12-14 19:51:10
 * @version V1.0   
 *
 */
public interface ActIdGroupMapper extends BaseMapper<ActIdGroup> {
	/**
	 * @Description: 获取流程分组信息 .<br>
	 * @param page page分页实体.<br>
	 * @param wrapper 参数实体.<br>
	 * @return List<ActIdGroup> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-15 上午10:18:47.<br>
	 */
	List<ActIdGroup> selectGroupPage(Page<ActIdGroup> page, @Param("ew")Wrapper<ActIdGroup> wrapper);
}