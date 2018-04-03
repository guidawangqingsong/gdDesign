package cn.jjxx.modules.workflow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jjxx.modules.workflow.entity.ActHiProcinst;
 
/**   
 * @Title: 流程实例数据库控制层接口
 * @Description: 流程实例数据库控制层接口
 * @author jjxx
 * @date 2017-12-22 14:55:58
 * @version V1.0   
 *
 */
public interface ActHiProcinstMapper extends BaseMapper<ActHiProcinst> {
    
	List<ActHiProcinst> selectProcinstPage(Page<ActHiProcinst> page,@Param("ew") Wrapper<ActHiProcinst> wrapper);
}