package cn.jjxx.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.modules.sys.entity.UserOrganization;
import cn.jjxx.modules.sys.mapper.UserOrgMenuMapper;
import cn.jjxx.modules.sys.mapper.UserOrgRoleMapper;
import cn.jjxx.modules.sys.mapper.UserOrganizationMapper;
import cn.jjxx.modules.sys.service.IUserOrganizationService;


@Transactional
@Service("userOrganizationService")
public class UserOrganizationServiceImpl extends CommonServiceImpl<UserOrganizationMapper, UserOrganization>
		implements IUserOrganizationService {
	@Autowired
	private UserOrganizationMapper userOrganizationMapper;
	@Autowired
	private UserOrgRoleMapper userOrgRoleMapper;
	@Autowired
	private UserOrgMenuMapper userOrgMenuMapper;
	
	@Override
	public List<UserOrganization> findUserByOrgId(String id) {
		return userOrganizationMapper.findUserByOrgId(id);
	}

	@Override
	public void deleteByIdCascade(String id) {
		UserOrganization uo = userOrganizationMapper.selectById(id);
		if(!ObjectUtils.isNullOrEmpty(uo)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("user_id", uo.getUserId());
			columnMap.put("org_id", uo.getOrganizationId());
			//删除用户组织关联权限
			userOrgMenuMapper.deleteByMap(columnMap);
			//删除用户组织关联角色
			userOrgRoleMapper.deleteByMap(columnMap);
			//删除用户组织关系
			userOrganizationMapper.deleteById(id);
		}
		
	}

	@Override
	public List<UserOrganization> selectListByUserId(String userId, String orgId) {
		// TODO Auto-generated method stub
		return userOrganizationMapper.selectListByUserId(userId,orgId);
	}

}
