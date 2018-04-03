package cn.jjxx.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import cn.jjxx.core.common.mapper.BaseTreeMapper;
import cn.jjxx.modules.sys.entity.Menu;

public interface MenuMapper extends BaseTreeMapper<Menu> {

	/**
	 * 
	 * @title: findMenuByUserId
	 * @description: 通过用户查找菜单
	 * @param userId
	 * @return
	 * @return: List<Menu>
	 */
	List<Menu> findMenuByUserId(String userId);
	
	/**
	 * 
	 * @title: findMenuByRoleId
	 * @description: 通过角色查找菜单
	 * @param userId
	 * @return
	 * @return: List<Menu>
	 */
	List<Menu> findMenuByRoleId(String roleId);
	
	/**
	 * 
	 * @Description: 根据用户ID和组织ID查询所有菜单 
	 * @param @param userId
	 * @param @param organizationId
	 * @param @return .  
	 * @return List<Menu> .
	 * @author 周恺 
	 * @date 2017年11月8日 下午2:04:18
	 */
	List<Menu> findMenuByUserIdAndOrgId(@Param("userId")String userId,@Param("organizationId")String organizationId);
	
	/**
	 * @Description: 获取当前用户的角色菜单集合 .<br>
	 * @param userId 用户Id.<br>   
	 * @param orgId 组织Id.<br>   
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 上午11:19:24.<br>
	 */
	List<String> getUserRoleMenuIds(@Param("userId")String userId,@Param("orgId")String orgId);
	
	/**
	 * @Description: 获取当前用户的组织菜单集合 .<br>
	 * @param userId 用户Id.<br>   
	 * @param orgId 组织Id.<br>   
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 上午11:19:24.<br>
	 */
	List<String> getUserOrgMenuIds(@Param("userId")String userId,@Param("orgId")String orgId);
}