package cn.jjxx.modules.workflow.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.workflow.entity.FlowCommon;
import cn.jjxx.modules.workflow.mapper.FlowCommonMapper;
import cn.jjxx.modules.workflow.service.IFlowCommonService;

@Transactional
@Service("flowCommonService")
public class FlowCommonService  extends CommonServiceImpl<FlowCommonMapper,FlowCommon> implements IFlowCommonService{

	@Override
	public Map<String, Object> findBillInfo(String billId, String tableName,String idFeild) {
		return baseMapper.findBillInfo(billId,tableName,idFeild);
	}

	@Override
	public boolean updateBillStatus(String billId, String tableName,
			String idFeild, String statusFeild,int status) {
		return baseMapper.updateBillStatus(billId, tableName, idFeild, statusFeild,status);
	}

}
