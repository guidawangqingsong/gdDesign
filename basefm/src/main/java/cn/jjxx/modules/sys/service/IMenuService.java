package cn.jjxx.modules.sys.service;

import java.util.List;

import cn.jjxx.core.common.service.ITreeCommonService;
import cn.jjxx.modules.sys.entity.Menu;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2014-12-20 21:33:32
 * @version V1.0
 *
 */
public interface IMenuService extends ITreeCommonService<Menu, String> {

	/**
	 * 通过用户ID查找菜单
	 * 
	 * @return
	 */
	List<Menu> findMenuByUserId(String userId);

	/**
	 * 通过角色查找菜单
	 * 
	 * @return
	 */
	List<Menu> findMenuByRoleId(String roleId);

	/**
	 * 
	 * @Description: 根据用户ID和组织ID查询菜单 
	 * @param @param userId
	 * @param @param organizationId
	 * @param @return .  
	 * @return List<Menu> .
	 * @author 周恺 
	 * @date 2017年11月8日 下午2:02:11
	 */
	List<Menu> findMenuByUserIdAndOrgId(String userId, String organizationId);
	
	/**
	 * @Description: 获取当前用户的角色菜单集合 .<br>
	 * @param userId 用户Id.<br>   
	 * @param orgId 组织Id.<br>   
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 上午11:19:24.<br>
	 */
	List<String> getUserRoleMenuIds(String userId,String orgId);
	
	/**
	 * @Description: 获取当前用户的组织菜单集合 .<br>
	 * @param userId 用户Id.<br>   
	 * @param orgId 组织Id.<br>   
	 * @return List<String> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2018-1-31 上午11:19:24.<br>
	 */
	List<String> getUserOrgMenuIds(String userId,String orgId);
}
