package cn.jjxx.modules.test.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.test.entity.TestOrderCustomer;
import cn.jjxx.modules.test.mapper.TestOrderCustomerMapper;
import cn.jjxx.modules.test.service.ITestOrderCustomerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 订单客户信息
 * @Description: 订单客户信息
 * @author jeeweb
 * @date 2017-09-10 14:48:06
 * @version V1.0   
 *
 */
@Transactional
@Service("testOrderCustomerService")
public class TestOrderCustomerServiceImpl  extends CommonServiceImpl<TestOrderCustomerMapper,TestOrderCustomer> implements  ITestOrderCustomerService {

}
