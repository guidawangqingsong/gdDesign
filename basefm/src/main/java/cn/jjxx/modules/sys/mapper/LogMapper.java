package cn.jjxx.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jjxx.modules.sys.entity.Log;

public interface LogMapper extends BaseMapper<Log> {
	
	List<Log> selectLogPage(Page<Log> page, @Param("ew") Wrapper<Log> wrapper);
}