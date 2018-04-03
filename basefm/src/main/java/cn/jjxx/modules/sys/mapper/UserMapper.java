package cn.jjxx.modules.sys.mapper;

import cn.jjxx.modules.sys.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

public interface UserMapper extends BaseMapper<User> {
	
	/**
	 * 
	 * @title: selectUserList
	 * @description: 查找用户列表
	 * @param wrapper
	 * @return
	 * @return: List<User>
	 */
	List<User> selectUserList(Pagination page, @Param("ew") Wrapper<User> wrapper);

	/**
	 * 
	 * @Description: 根据用户ID查询用户详细信息 
	 * @param @param id
	 * @param @return .  
	 * @return User .
	 * @author 周恺 
	 * @date 2017年11月7日 下午8:18:32
	 */
	User selectUserById(String id);
}