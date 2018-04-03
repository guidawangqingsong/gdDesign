package cn.jjxx.modules.sys.service;

import java.util.List;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.sys.entity.UserOrganization;

public interface IUserOrganizationService extends ICommonService<UserOrganization> {
	
	/**
	 * 
	 * @Description: 根据机构ID查询所有用户 
	 * @param @param id
	 * @param @return .  
	 * @return List<UserOrganization> .
	 * @author 周恺 
	 * @date 2017年11月7日 下午12:01:38
	 */
	List<UserOrganization> findUserByOrgId(String id);
	
	/**
	 * 
	 * @Description: 根据用户id和组织查询用户相关组织
	 * @param @param userId,orgId
	 * @param @return .  
	 * @return List<UserOrganization> .
	 * @author 周恺 
	 * @date 2017年11月7日 下午12:01:38
	 */
	List<UserOrganization> selectListByUserId(String userId,String orgId);
	
	/**
	 * 
	 * @Description: 根据用户组织关联表ID 删除所有用户组织关联的角色与权限信息 
	 * @param @param id .  
	 * @return void .
	 * @author 周恺 
	 * @date 2017年11月9日 下午2:11:10
	 */
	void deleteByIdCascade(String id);
	
	
}
