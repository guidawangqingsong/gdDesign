package cn.jjxx.modules.sys.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.entity.Role;
import cn.jjxx.modules.sys.mapper.RoleMapper;
import cn.jjxx.modules.sys.service.IRoleService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("roleService")
public class RoleServiceImpl extends CommonServiceImpl<RoleMapper, Role> implements IRoleService {

	@Override
	public List<Role> findListByUserId(String userid) {
		return baseMapper.findRoleByUserId(userid);
	}

	@Override
	public List<Role> findRoleByUserIdAndOrgId(String userId,
			String organizationId) {
		return baseMapper.findRoleByUserIdAndOrgId(userId,organizationId);
	}

}
