package cn.jjxx.modules.sys.service;

import java.util.List;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.sys.entity.Staff;

/**   
 * @Title: 职员管理
 * @Description: 职员管理
 * @author jjxx
 * @date 2017-11-09 18:22:19
 * @version V1.0   
 *
 */
public interface IStaffService extends ICommonService<Staff> {
	
	/**
	 * 
	 * @Description: 根据员工ID查询所有员工信息 
	 * @param @param id
	 * @param @return .  
	 * @return Staff .
	 * @author 周恺 
	 * @date 2017年11月10日 下午1:26:44
	 */
	Staff selectStaffById(String id);
	
	/**
	 * @Description: 根据组织Id,获取职员列表 .<br>
	 * @param orgId 组织Id.<br>   
	 * @return List<Staff> .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-6 下午4:24:16.<br>
	 */
	List<Staff> findStaff(String orgId);

}

