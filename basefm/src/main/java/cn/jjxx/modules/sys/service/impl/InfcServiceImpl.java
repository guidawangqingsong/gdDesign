package cn.jjxx.modules.sys.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.mapper.InfcMapper;
import cn.jjxx.modules.sys.entity.Infc;
import cn.jjxx.modules.sys.service.IInfcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 接口列表
 * @Description: 接口列表
 * @author jjxx
 * @date 2018-01-13 00:29:11
 * @version V1.0   
 *
 */
@Transactional
@Service("infcService")
public class InfcServiceImpl  extends CommonServiceImpl<InfcMapper,Infc> implements  IInfcService {

}
