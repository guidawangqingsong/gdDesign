package cn.jjxx.modules.oa.service.impl;

import java.io.Serializable;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.data.Page;
import cn.jjxx.core.query.data.PageImpl;
import cn.jjxx.core.query.data.Pageable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.parse.QueryToWrapper;
import cn.jjxx.modules.oa.mapper.OaWorkLogMapper;
import cn.jjxx.modules.oa.entity.OaWorkLog;
import cn.jjxx.modules.oa.service.IOaWorkLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;

/**   
 * @Title: 员工日志管理
 * @Description: 员工日志管理
 * @author grace
 * @date 2018-02-06 11:40:23
 * @version V1.0   
 *
 */
@Transactional
@Service("oaWorkLogService")
public class OaWorkLogServiceImpl  extends CommonServiceImpl<OaWorkLogMapper,OaWorkLog> implements  IOaWorkLogService {

	/*@Override
	public Page<OaWorkLog> list(Queryable queryable, Wrapper<OaWorkLog> wrapper) {
		QueryToWrapper<OaWorkLog> queryToWrapper = new QueryToWrapper<OaWorkLog>();
		//queryToWrapper.parseCondition(wrapper, queryable);
		// 排序问题
		queryToWrapper.parseSort(wrapper, queryable);
		Pageable pageable = queryable.getPageable();
		com.baomidou.mybatisplus.plugins.Page<OaWorkLog> page = new com.baomidou.mybatisplus.plugins.Page<OaWorkLog>(
				pageable.getPageNumber(), pageable.getPageSize());
		com.baomidou.mybatisplus.plugins.Page<OaWorkLog> content = selectPage(page, wrapper);
		return new PageImpl<OaWorkLog>(content.getRecords(), queryable.getPageable(), content.getTotal());
		//return super.list(queryable, wrapper);
	}*/

	@Override
	public OaWorkLog selectById(Serializable id) {
		return baseMapper.selectById(id);//使用自己的mapper
	}
	
}
