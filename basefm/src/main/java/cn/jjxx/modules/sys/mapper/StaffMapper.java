package cn.jjxx.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jjxx.modules.sys.entity.Staff;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
 
/**   
 * @Title: 职员管理数据库控制层接口
 * @Description: 职员管理数据库控制层接口
 * @author jjxx
 * @date 2017-11-09 18:22:19
 * @version V1.0   
 *
 */
public interface StaffMapper extends BaseMapper<Staff> {

	Staff selectStaffById(String id);

	List<Staff> selectStaffPage(Page<Staff> page, @Param("ew")Wrapper<Staff> wrapper);
    
	
	List<Staff> findStaff(String orgId);
}