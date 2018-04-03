package cn.jjxx.modules.test.service.impl;

import cn.jjxx.core.common.service.impl.TreeCommonServiceImpl;
import cn.jjxx.modules.test.entity.TestTree;
import cn.jjxx.modules.test.mapper.TestTreeMapper;
import cn.jjxx.modules.test.service.ITestTreeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 测试树
 * @Description: 测试树
 * @author jeeweb
 * @date 2017-09-10 14:48:58
 * @version V1.0   
 *
 */
@Transactional
@Service("testTreeService")
public class TestTreeServiceImpl  extends TreeCommonServiceImpl<TestTreeMapper,TestTree,String> implements  ITestTreeService {

}
