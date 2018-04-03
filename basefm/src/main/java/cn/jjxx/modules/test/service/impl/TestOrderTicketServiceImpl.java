package cn.jjxx.modules.test.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.test.entity.TestOrderTicket;
import cn.jjxx.modules.test.mapper.TestOrderTicketMapper;
import cn.jjxx.modules.test.service.ITestOrderTicketService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 订单票据
 * @Description: 订单票据
 * @author jeeweb
 * @date 2017-09-10 14:48:06
 * @version V1.0   
 *
 */
@Transactional
@Service("testOrderTicketService")
public class TestOrderTicketServiceImpl  extends CommonServiceImpl<TestOrderTicketMapper,TestOrderTicket> implements  ITestOrderTicketService {

}
