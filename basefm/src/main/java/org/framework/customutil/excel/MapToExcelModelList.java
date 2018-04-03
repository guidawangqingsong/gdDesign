package org.framework.customutil.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.framework.customutil.excel.entity.ExcelModel;

/**
 * @ClassName: MapToExcelModelList .<br> 
 * @Package org.framework.customutil.poi.excel .<br>
 * @Description: 将map转换成excel导入导出需要的字段表头的集合 .<br>
 * @author 郑成功 .<br>
 * @date 2017-8-28 下午8:06:00 .<br> 
 * @version V1.0 .<br>
 */
public class MapToExcelModelList {

	/**
	 * @Description: map转换成excel需要的表头 .<br>
	 * @param titles 导出的字段及显示的表都title=name .<br>
	 * @return List<ExcelModel>  返回相关的表都信息集合 .<br>
	 * @throws
	 * @author 郑成功 .<br>
	 * @date 2017-8-28 下午8:06:00 .<br>
	 */
	public static List<ExcelModel> mapToExcelModels(Map<String,String> titles){
		List<ExcelModel> list = new ArrayList<ExcelModel>();
		for(Map.Entry<String,String> entry : titles.entrySet()){
			ExcelModel em = new ExcelModel();
			em.setFeildKey(entry.getKey());
			em.setFeildValue(entry.getValue());
			list.add(em);
		}
		return list;
	}
}
