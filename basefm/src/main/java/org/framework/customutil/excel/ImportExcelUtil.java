package org.framework.customutil.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.framework.customutil.excel.entity.ExcelModel;

/**
 * @Title: ImportExcelUtil.java .<br>
 * @Package org.framework.customutil.poi.excel .<br>
 * @Description: 导入excel并解析 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-29 下午2:22:57.<br>
 * @version V1.0.<br>
 */
public class ImportExcelUtil {
	
	/**定义表头所在的列*/
	private static final int COLUMN = 2;

	/**输入流*/
	private static InputStream IS;
	
	/**导出的列*/
	private static List<ExcelModel> THIRD_TITLES = new ArrayList<ExcelModel>();
	
	/**
	 * @Description: 通过输入流和表头，解析并返回导入的excel数据 .<br>
	 * @param @param is 输入流.<br>
	 * @param @param thirdTitles 表头的集合列表 .<br>
	 * @return List<Map<String,Object>> 返回解析后的数据.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 下午2:21:28.<br>
	 */
	public static List<Map<String,Object>> getInputStreamList(InputStream is,List<ExcelModel> thirdTitles){
		IS = is;
		THIRD_TITLES = thirdTitles;
		try {
			return resolveXml();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(ImportExcelUtil.class).error(Configure.exportMsg.导入的EXCEL格式错误);
			return new ArrayList<Map<String,Object>>();
		}
		
	}
	
	/**
	 * @Description: 解析excel数据，将第一个sheet的数据存入到List<Map<String,Object>>中 .<br>
	 * @return List<Map<String,Object>> 返回集合数据.<br> 
	 * @throws IOException 抛出IO流异常.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午10:48:37.<br>
	 */
	private static List<Map<String,Object>> resolveXml() throws IOException{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HSSFWorkbook wkb = new  HSSFWorkbook(IS);
		HSSFSheet sheet = wkb.getSheetAt(0);
		if(sheet==null){
			return list;
		}
		HSSFRow row = sheet.getRow(COLUMN);
		List<ExcelModel> titles = getTitlesList(row);
		for (int rowNum = COLUMN+1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Map<String,Object> map = new HashMap<String, Object>();
			HSSFRow rowfeild = sheet.getRow(rowNum);
			for(int index=0; index<titles.size(); index++){
				String value = getValue(rowfeild.getCell(index));
				map.put(titles.get(index).getFeildKey(), value);								
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 * @Description: 将title与 导入的excel表头行关联，得到组合后的表头集合.<br>
	 * @param row 表头行信息.<br> 
	 * @return List<ExcelModel> 返回表头信息集合.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午10:50:33.<br>
	 */
	private static List<ExcelModel> getTitlesList(HSSFRow row){
		List<ExcelModel> list = new ArrayList<ExcelModel>();
		for(int i=0; i<THIRD_TITLES.size(); i++){
			list.add(TitleValueToKey(row.getCell(i),i));
		}
		return list;
	}
	
	/**
	 * @Description: 将导入的excel的表头列中文换成对应的字段的key .<br>
	 * @param @param cell excel单元格.<br>
	 * @param @param sorting 下标排序.<br>
	 * @return ExcelModel .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 下午2:19:51.<br>
	 */
	private static ExcelModel TitleValueToKey(HSSFCell cell,int sorting){
		ExcelModel em = new ExcelModel();
		for(ExcelModel title:THIRD_TITLES){
			if(title.getFeildValue().equals(cell.getStringCellValue())){
				em = title;
				em.setSorting(sorting);
				return em;
			}
		}
		return em;
	}
	
	/**
	 * @Description: 通过传入的cell的信息，返回相关类型的数据 .<br>
	 * @param @param hssfCell excel单元格信息.<br> 
	 * @return String 返回转换后的数据.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 下午2:32:11.<br>
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
	
	public static void main(String[] args) throws FileNotFoundException {
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", "姓名");
		map.put("class", "班级");
		map.put("biscj", "笔试成绩");
		map.put("jscj", "成绩");
		List<ExcelModel> titles = MapToExcelModelList.mapToExcelModels(map);
		InputStream is = new FileInputStream("d:\\学生表.xls");
		getInputStreamList(is, titles);
		
	}
	
}
