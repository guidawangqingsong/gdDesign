package cn.jjxx.modules.sys.service;

import java.util.List;

import cn.jjxx.core.common.service.ITreeCommonService;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.modules.sys.entity.Organization;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2014-12-20 21:33:51
 * @version V1.0
 *
 */
public interface IOrganizationService extends ITreeCommonService<Organization, String> {
	/**
	 * 通过用户ID查找组织
	 */
	public List<Organization> findListByUserId(String userId);
	/**
	 * 
	 * @Description: 根据组织ID查询所有子组织
	 * @param @param orgid
	 * @param @return .  
	 * @return List<Organization> .
	 * @author 周恺 
	 * @date 2017年11月7日 下午3:57:34
	 */
	public List<Organization> findsubById(String orgId);
	
	/**
	 * 
	 * @Description: 查询wbs树 
	 * @param @param entityWrapper
	 * @param @return .  
	 * @return List<Organization> .
	 * @author 周恺 
	 * @date 2017年11月23日 上午11:48:29
	 */
	public List<Organization> selectWbsTreeList(
			EntityWrapper<Organization> entityWrapper);
}
