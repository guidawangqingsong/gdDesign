package cn.jjxx.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.entity.UserOrgRole;
import cn.jjxx.modules.sys.mapper.UserOrgRoleMapper;
import cn.jjxx.modules.sys.service.IUserOrgRoleService;


@Transactional
@Service("userOrgRoleService")
public class UserOrgRoleServiceImpl  extends CommonServiceImpl<UserOrgRoleMapper, UserOrgRole> implements IUserOrgRoleService{

}
