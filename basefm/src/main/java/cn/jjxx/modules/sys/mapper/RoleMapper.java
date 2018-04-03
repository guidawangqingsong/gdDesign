package cn.jjxx.modules.sys.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jjxx.modules.sys.entity.Role;

public interface RoleMapper extends BaseMapper<Role> {
	/**
	 * 
	 * @title: findRoleByUserId   
	 * @description: 通过用户查找角色
	 * @param userId
	 * @return      
	 * @return: List<Role>
	 */
	List<Role> findRoleByUserId(String userId);
	
	/**
	 * 
	 * @Description: 通过用户ID与组织ID查询所有角色 
	 * @param @param userId
	 * @param @param organizationId
	 * @param @return .  
	 * @return List<Role> .
	 * @author 周恺 
	 * @date 2017年11月8日 下午5:05:52
	 */
	List<Role> findRoleByUserIdAndOrgId(@Param("userId")String userId,@Param("organizationId")String organizationId);
}
