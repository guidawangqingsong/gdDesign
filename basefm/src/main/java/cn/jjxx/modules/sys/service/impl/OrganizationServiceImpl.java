package cn.jjxx.modules.sys.service.impl;

import cn.jjxx.core.common.service.impl.TreeCommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.mapper.OrganizationMapper;
import cn.jjxx.modules.sys.service.IOrganizationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("organizationService")
public class OrganizationServiceImpl extends TreeCommonServiceImpl<OrganizationMapper, Organization, String>
		implements IOrganizationService {
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Override
	public List<Organization> findListByUserId(String userid) {
		return baseMapper.findListByUserId(userid);
	}

	@Override
	public List<Organization> findsubById(String orgId) {
		return organizationMapper.findsubById(orgId);
	}

	@Override
	public List<Organization> selectWbsTreeList(
			EntityWrapper<Organization> entityWrapper) {
		// 保证有where字句
		entityWrapper.eq("1", "1");
		return organizationMapper.selectWbsTreeList(entityWrapper);
	}

}
