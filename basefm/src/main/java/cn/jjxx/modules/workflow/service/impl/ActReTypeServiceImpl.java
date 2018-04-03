package cn.jjxx.modules.workflow.service.impl;

import cn.jjxx.core.common.service.impl.TreeCommonServiceImpl;
import cn.jjxx.modules.workflow.mapper.ActReTypeMapper;
import cn.jjxx.modules.workflow.entity.ActReType;
import cn.jjxx.modules.workflow.service.IActReTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 流程类型表
 * @Description: 流程类型表
 * @author jjxx
 * @date 2017-12-14 20:59:03
 * @version V1.0   
 *
 */
@Transactional
@Service("actReTypeService")
public class ActReTypeServiceImpl  extends TreeCommonServiceImpl<ActReTypeMapper,ActReType,String> implements  IActReTypeService {

}
