package cn.jjxx.modules.test.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.test.mapper.TestFlowMapper;
import cn.jjxx.modules.test.entity.TestFlow;
import cn.jjxx.modules.test.service.ITestFlowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 测试流程demo
 * @Description: 测试流程demo
 * @author jjxx
 * @date 2017-12-15 16:56:30
 * @version V1.0   
 *
 */
@Transactional
@Service("testFlowService")
public class TestFlowServiceImpl  extends CommonServiceImpl<TestFlowMapper,TestFlow> implements  ITestFlowService {

}
