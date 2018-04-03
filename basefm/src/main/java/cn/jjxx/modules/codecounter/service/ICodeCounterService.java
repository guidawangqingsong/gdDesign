package cn.jjxx.modules.codecounter.service;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.codecounter.entity.CodeCounter;

/**   
 * @Title: 编码生成器 生成整型数字
 * @Description: 编码生成器 生成整型数字
 * @author jjxx
 * @date 2017-12-21 15:30:55
 * @version V1.0   
 *
 */
public interface ICodeCounterService extends ICommonService<CodeCounter> {

	/**
	 * 
	 * @Description: 根据表名、表字段、初始值来获取编码
	 * @param @param tableNameWbsPoint 表名
	 * @param @param tableColumnWbsPoint 表字段
	 * @param @param tableCodeInit 编码初始值，如果没有设置值就设置该初始值
	 * @param @return .  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年12月21日 下午3:43:55
	 */
	Integer getCodeValue(String tableNameWbsPoint, String tableColumnWbsPoint,
			Integer tableCodeInit);

}

