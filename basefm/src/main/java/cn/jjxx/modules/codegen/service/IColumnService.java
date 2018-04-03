package cn.jjxx.modules.codegen.service;

import java.util.List;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.codegen.entity.Column;

public interface IColumnService extends ICommonService<Column> {
	List<Column> selectListByTableId(String tableId);
}
