package cn.jjxx.modules.sys.service.impl;

import cn.jjxx.core.common.service.impl.TreeCommonServiceImpl;
import cn.jjxx.modules.sys.entity.Menu;
import cn.jjxx.modules.sys.mapper.MenuMapper;
import cn.jjxx.modules.sys.service.IMenuService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends TreeCommonServiceImpl<MenuMapper, Menu, String> implements IMenuService {

	@Override
	public List<Menu> findMenuByUserId(String userId) {
		return baseMapper.findMenuByUserId(userId);
	}

	@Override
	public List<Menu> findMenuByRoleId(String roleId) {
		return baseMapper.findMenuByRoleId(roleId);
	}

	@Override
	public List<Menu> findMenuByUserIdAndOrgId(String userId,
			String organizationId) {
		return baseMapper.findMenuByUserIdAndOrgId(userId,organizationId);
	}

	@Override
	public List<String> getUserRoleMenuIds(String userId, String orgId) {
		return baseMapper.getUserRoleMenuIds(userId, orgId);
	}

	@Override
	public List<String> getUserOrgMenuIds(String userId, String orgId) {
		return baseMapper.getUserOrgMenuIds(userId, orgId);
	}

}
