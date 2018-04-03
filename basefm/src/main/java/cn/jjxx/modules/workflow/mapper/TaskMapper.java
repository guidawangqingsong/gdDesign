package cn.jjxx.modules.workflow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jjxx.modules.workflow.entity.Task;
 
/**   
 * @Title: act_ru_task数据库控制层接口
 * @Description: 正在运行的任务数据库控制层接口
 * @author jjxx
 * @date 2017-11-20 10:55:10
 * @version V1.0   
 *
 */
public interface TaskMapper extends BaseMapper<Task> {

	List<Task> selectTaskPage(Page<Task> page,@Param("ew") Wrapper<Task> wrapper);
    
}