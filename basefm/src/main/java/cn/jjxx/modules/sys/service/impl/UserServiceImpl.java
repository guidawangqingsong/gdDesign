package cn.jjxx.modules.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Staff;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.entity.UserOrganization;
import cn.jjxx.modules.sys.entity.UserRole;
import cn.jjxx.modules.sys.mapper.UserMapper;
import cn.jjxx.modules.sys.service.IStaffService;
import cn.jjxx.modules.sys.service.IUserOrganizationService;
import cn.jjxx.modules.sys.service.IUserRoleService;
import cn.jjxx.modules.sys.service.IUserService;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

@Transactional
@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	PasswordService passwordService;
	@Autowired
	private IUserOrganizationService userOrganizationService;
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IStaffService staffService;
	
	
	@Override
	public void changePassword(String userid, String newPassword) {
		User user = selectById(userid);
		if (user != null) {
			user.setPassword(newPassword);
			passwordService.encryptPassword(user);
		}
		insertOrUpdate(user);
	}

	@Override
	public User findByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		return selectOne(new EntityWrapper<User>(User.class).eq("username", username));
	}

	@Override
	public User findByEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return null;
		}
		return selectOne(new EntityWrapper<User>(User.class).eq("email", email));
	}

	@Override
	public User findByPhone(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return null;
		}
		return selectOne(new EntityWrapper<User>(User.class).eq("phone", phone));
	}

	@Override
	public boolean insert(User user) {
		passwordService.encryptPassword(user);
		return super.insert(user);
	}

	@Override
	public boolean deleteById(Serializable id) {
		// 删除角色关联
		userRoleService.delete(new EntityWrapper<UserRole>(UserRole.class).eq("userId", id));
		// 删除部门关联
		userOrganizationService.delete(new EntityWrapper<UserOrganization>(UserOrganization.class).eq("userId", id));
		return super.deleteById(id);
	}

	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		for (Object id : idList) {
			this.deleteById((Serializable) id);
		}
		return true;
	}

	@Override
	public Page<User> selectPage(Page<User> page, Wrapper<User> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectUserList(page, wrapper));
		return page;
	}

	@Override
	public User selectUserById(String id) {
		return userMapper.selectUserById(id);
	}

	@Override
	public void deleteByIdCascade(String id) {
		List<UserOrganization> uos = userOrganizationService.selectList(new EntityWrapper<UserOrganization>(UserOrganization.class).eq("userId", id));
		User user = userMapper.selectUserById(id);
		if(user!=null&&!StringUtils.isEmpty(user.getStaffId())){
			Staff staff = staffService.selectStaffById(user.getStaffId());
			if(!ObjectUtils.isNullOrEmpty(staff)){
				staff.setUserId("");
				staffService.insertOrUpdate(staff);
			}
		}
		//删除用户组织关联的角色与权限
		for (UserOrganization userOrganization : uos) {
			userOrganizationService.deleteByIdCascade(userOrganization.getId());
		}
		super.deleteById(id);
	}

	@Override
	public void insertUser(User entity) {
		passwordService.encryptPassword(entity);
		userMapper.insert(entity);
		if(!StringUtils.isEmpty(entity.getStaffId())){
			Staff staff = staffService.selectStaffById(entity.getStaffId());
			if(!ObjectUtils.isNullOrEmpty(staff)){
				staff.setUserId(entity.getId());
				staffService.insertOrUpdate(staff);
			}
		}
	}

	@Override
	public void insertOrUpdateUser(User entity) {
		userMapper.updateById(entity);
		//判断旧的员工号是否与新的员工号不一致
		if(!entity.getOldStaffId().equals(entity.getStaffId())&&!StringUtils.isEmpty(entity.getOldStaffId())){
			Staff staff = staffService.selectStaffById(entity.getOldStaffId());
			if(!ObjectUtils.isNullOrEmpty(staff)){
				staff.setUserId("");
				staffService.insertOrUpdate(staff);
			}			
		}
		if(!StringUtils.isEmpty(entity.getStaffId())){
			Staff staff = staffService.selectStaffById(entity.getStaffId());
			if(!ObjectUtils.isNullOrEmpty(staff)){
				staff.setUserId(entity.getId());
				staffService.insertOrUpdate(staff);
			}
		}

	}

	@Override
	public List<User> selectList(Wrapper<User> wrapper) {
		return baseMapper.selectList(wrapper);
	}
	

}
