package cn.jjxx.modules.sys.service;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.sys.entity.User;

/**
 * 
 * All rights Reserved, Designed By www.jjxxkj.cn
 * 
 * @title: IUserService.java
 * @package cn.jjxx.modules.sys.service
 * @description: 用户
 * @author: www.jjxxkj.cn
 * @date: 2017年7月11日 下午9:21:07
 * @version V1.0
 * @copyright: 2017 www.jjxxkj.cn Inc. All rights reserved.
 *
 */
public interface IUserService extends ICommonService<User> {
	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(String userid, String newPassword);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 根据Email查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByEmail(String email);

	/**
	 * 根据Email查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByPhone(String phone);

	/**
	 * 
	 * @Description: 根据用户ID查询用户详细信息
	 * @param @param id 用户ID
	 * @param @return .  
	 * @return User .
	 * @author 周恺 
	 * @date 2017年11月7日 下午8:16:57
	 */
	public User selectUserById(String id);
	
	/**
	 * 
	 * @Description: 根据用户ID 删除所有用户关联的组织 以及 用户组织角色 与用户组织权限 
	 * @param @param id .  
	 * @return void .
	 * @author 周恺 
	 * @date 2017年11月9日 下午3:17:33
	 */
	public void deleteByIdCascade(String id);

	/**
	 * 
	 * @Description: 插入用户并且同步更新员工表中的userid 
	 * @param @param entity .  
	 * @return void .
	 * @author 周恺 
	 * @date 2017年11月15日 上午9:40:19
	 */
	public void insertUser(User entity);

	/**
	 * 
	 * @Description: 更新用户并且同步更新员工表中的userid  
	 * @param @param entity .  
	 * @return void .
	 * @author 周恺 
	 * @date 2017年11月15日 上午9:41:12
	 */
	public void insertOrUpdateUser(User entity);

}
