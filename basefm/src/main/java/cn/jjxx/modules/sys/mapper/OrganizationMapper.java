package cn.jjxx.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jjxx.core.common.mapper.BaseTreeMapper;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.modules.sys.entity.Organization;

public interface OrganizationMapper extends BaseTreeMapper<Organization> {
	
	/**
	 * 
	 * @title: findListByUserId
	 * @description: 通过用户查找组织机构
	 * @param userId
	 * @return
	 * @return: List<Organization>
	 */
	List<Organization> findListByUserId(String userId);
	
	/**
	 * 
	 * @Description: 根据组织ID查询所有子组织 
	 * @param @param id
	 * @param @return .  
	 * @return List<Organization> .
	 * @author 周恺 
	 * @date 2017年11月7日 下午4:06:14
	 */
	List<Organization> findsubById(String orgId);
	
	/**
	 * 
	 * @Description: 查询WBS结构的树 
	 * @param @param entityWrapper
	 * @param @return .  
	 * @return List<Organization> .
	 * @author 周恺 
	 * @date 2017年11月23日 上午11:51:19
	 */
	List<Organization> selectWbsTreeList(
			@Param("ew")EntityWrapper<Organization> entityWrapper);
}