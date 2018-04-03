package cn.jjxx.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jjxx.modules.sys.entity.UserOrganization;

public interface UserOrganizationMapper extends BaseMapper<UserOrganization> {
	
	List<UserOrganization> findUserByOrgId(@Param("id")String id);
	
	List<UserOrganization> selectListByUserId(@Param("userId")String userId,@Param("orgId")String orgId);

}