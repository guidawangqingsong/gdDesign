package cn.jjxx.modules.codecounter.service.impl;

import java.util.List;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.modules.codecounter.mapper.CodeCounterMapper;
import cn.jjxx.modules.codecounter.entity.CodeCounter;
import cn.jjxx.modules.codecounter.service.ICodeCounterService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 编码生成器 生成整型数字
 * @Description: 编码生成器 生成整型数字
 * @author jjxx
 * @date 2017-12-21 15:30:55
 * @version V1.0   
 *
 */
@Transactional
@Service("codeCounterService")
public class CodeCounterServiceImpl  extends CommonServiceImpl<CodeCounterMapper,CodeCounter> implements  ICodeCounterService {

	@Override
	public Integer getCodeValue(String tableNameWbsPoint,
			String tableColumnWbsPoint, Integer tableCodeInit) {
		  EntityWrapper<CodeCounter> entityWrapper = new EntityWrapper<CodeCounter>(CodeCounter.class);
		  entityWrapper.eq("tableName", tableNameWbsPoint);
		  entityWrapper.eq("tableColumn", tableColumnWbsPoint);
		  List<CodeCounter> codeCounterList = selectList(entityWrapper);
		  if(ObjectUtils.isNullOrEmpty(codeCounterList)){//如果表中没有值，则插入一条返回默认值
			  CodeCounter codeCounter = new CodeCounter();
			  codeCounter.setTableName(tableNameWbsPoint);
			  codeCounter.setTableColumn(tableColumnWbsPoint);
			  codeCounter.setTableCount(tableCodeInit);
			  insertOrUpdate(codeCounter);
			  return tableCodeInit;
		  }else{//如果有值则更新表中统计值并且返回
			  Integer codeVal = codeCounterList.get(0).getTableCount();
			  if(ObjectUtils.isNullOrEmpty(codeVal)){
				  codeVal = tableCodeInit;
			  }else{
				  codeVal = codeVal+1;
				  codeCounterList.get(0).setTableCount(codeVal);
				  insertOrUpdate(codeCounterList.get(0));
			  }
			  return codeVal;			  
		  }
		
	}

}
