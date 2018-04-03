package cn.jjxx.modules.sys.service;

import java.util.List;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.sys.entity.Role;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2014-12-20 21:33:15
 * @version V1.0
 *
 */
public interface IRoleService extends ICommonService<Role> {
	/**
	 * 通过用户ID查找角色
	 */
	public List<Role> findListByUserId(String userid);

	/**
	 * 
	 * @Description: 根据用户ID和组织ID查询所有角色 
	 * @param @param userId
	 * @param @param organizationId
	 * @param @return .  
	 * @return List<Role> .
	 * @author 周恺 
	 * @date 2017年11月8日 下午5:03:52
	 */
	public List<Role> findRoleByUserIdAndOrgId(String userId,
			String organizationId);
}
