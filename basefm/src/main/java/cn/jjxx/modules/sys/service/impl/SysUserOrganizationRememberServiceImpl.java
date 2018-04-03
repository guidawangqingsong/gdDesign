package cn.jjxx.modules.sys.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.mapper.SysUserOrganizationRememberMapper;
import cn.jjxx.modules.sys.entity.SysUserOrganizationRemember;
import cn.jjxx.modules.sys.service.ISysUserOrganizationRememberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: sys_user_organization_remember
 * @Description: sys_user_organization_remember
 * @author jjxx
 * @date 2017-12-25 18:07:59
 * @version V1.0   
 *
 */
@Transactional
@Service("sysUserOrganizationRememberService")
public class SysUserOrganizationRememberServiceImpl  extends CommonServiceImpl<SysUserOrganizationRememberMapper,SysUserOrganizationRemember> implements  ISysUserOrganizationRememberService {

}
