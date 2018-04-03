package cn.jjxx.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.entity.Staff;
import cn.jjxx.modules.sys.mapper.StaffMapper;
import cn.jjxx.modules.sys.service.IStaffService;

/**   
 * @Title: 职员管理
 * @Description: 职员管理
 * @author jjxx
 * @date 2017-11-09 18:22:19
 * @version V1.0   
 *
 */
@Transactional
@Service("staffService")
public class StaffServiceImpl  extends CommonServiceImpl<StaffMapper,Staff> implements  IStaffService {
	@Autowired
	private StaffMapper staffMapper;
	

	@Override
	public Staff selectStaffById(String id) {
		return staffMapper.selectStaffById(id);
	}

	@Override
	public Page<Staff> selectPage(Page<Staff> page, Wrapper<Staff> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectStaffPage(page, wrapper));
		return page;
	}

	@Override
	public List<Staff> findStaff(String orgId) {
		return staffMapper.findStaff(orgId);
	}
}
