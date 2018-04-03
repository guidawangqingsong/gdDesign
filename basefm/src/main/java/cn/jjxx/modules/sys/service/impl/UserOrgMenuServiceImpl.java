package cn.jjxx.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.entity.UserOrgMenu;
import cn.jjxx.modules.sys.mapper.UserOrgMenuMapper;
import cn.jjxx.modules.sys.service.IUserOrgMenuService;


@Transactional
@Service("userOrgMenuService")
public class UserOrgMenuServiceImpl extends CommonServiceImpl<UserOrgMenuMapper, UserOrgMenu> implements IUserOrgMenuService {

}
