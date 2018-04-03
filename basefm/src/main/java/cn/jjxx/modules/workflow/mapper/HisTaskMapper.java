package cn.jjxx.modules.workflow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jjxx.modules.workflow.entity.HisTask;
 
/**   
 * @Title: 历史任务表数据库控制层接口
 * @Description: 历史任务表数据库控制层接口
 * @author jjxx
 * @date 2017-11-24 12:55:31
 * @version V1.0   
 *
 */
public interface HisTaskMapper extends BaseMapper<HisTask> {

	List<HisTask> selectHisTaskPage(Page<HisTask> page, @Param("ew") Wrapper<HisTask> wrapper);
	
	List<HisTask> getHisTakComment(String procInstId);
	
}