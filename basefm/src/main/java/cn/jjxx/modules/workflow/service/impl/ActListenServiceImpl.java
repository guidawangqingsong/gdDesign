package cn.jjxx.modules.workflow.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.workflow.mapper.ActListenMapper;
import cn.jjxx.modules.workflow.entity.ActListen;
import cn.jjxx.modules.workflow.service.IActListenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 流程监听
 * @Description: 流程监听
 * @author jjxx
 * @date 2018-01-13 00:53:48
 * @version V1.0   
 *
 */
@Transactional
@Service("actListenService")
public class ActListenServiceImpl  extends CommonServiceImpl<ActListenMapper,ActListen> implements  IActListenService {

}
