package org.framework.customutil.excel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.framework.customutil.excel.entity.ExcelModel;

/**
 * @Title: ExportExcelUtil.java .<br>
 * @Package org.framework.customutil.poi.excel .<br>
 * @Description: 基于List<Map<String,Object>>的集合，导出excel数据 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-29 上午9:28:02.<br>
 * @version V1.0.<br>
 */
public class ExportExcelUtil {
	
	/**首航标题*/
	private static String TITLE;
	/**次行标题*/
	private static String EXPORT_PERSON;
	/**导出的列*/
	private static List<ExcelModel> THIRD_TITLES = new ArrayList<ExcelModel>();
	/**导出的数据集合*/
	private static List<Map<String,Object>> RESULTS;
	
	/**
	 * @Description: 设置首航标题信息 .<br>
	 * @param title 首航标题.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午9:12:50.<br>
	 */
	public static void setTitle(String title){
		TITLE = title;
	}
	
	/**
	 * @Description: 设置第二行标题信息 .<br>
	 * @param secondRowTitle 第二行标题.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午9:12:50.<br>
	 */
	public static void setSecTitle(String secondRowTitle){
		EXPORT_PERSON = secondRowTitle;
	}
	
	/**
	 * @Description: 设置需要导出的列 .<br>
	 * @param thirdTitles 列表头集合.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午9:12:50.<br>
	 */
	public static void setThirdTitles(List<ExcelModel> thirdTitles){
		THIRD_TITLES = thirdTitles;
	}
	
	/**
	 * @Description: 设置需要导入的表 .<br>
	 * @param results .<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午9:09:43.<br>
	 */
	public static void setResults(List<Map<String,Object>> results){
		RESULTS = results;
	}
	
	/**
	 * @Description: 导出excel数据 .<br>
	 * @param response http响应.<br>   
	 * @return void 直接通过response输出.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午9:08:45.<br>
	 */
	public static void exportExcel(HttpServletResponse response){
		if(TITLE==null){
			Logger.getLogger(ExportExcelUtil.class).error(Configure.exportMsg.请设置首航标题.toString());
			return;
		}
		if(EXPORT_PERSON==null){
			Logger.getLogger(ExportExcelUtil.class).error(Configure.exportMsg.请设置次行标题.toString());
			return;
		}
		if(THIRD_TITLES==null){
			Logger.getLogger(ExportExcelUtil.class).error(Configure.exportMsg.请设置需要导出的列.toString());
			return;
		}
		if(RESULTS==null){
			RESULTS = new ArrayList<Map<String,Object>>();
		}
		HSSFWorkbook wkb = createWorkbook();
		outExcle(response, wkb);
	}
	
	/**
	 * @Description: 导出excel数据 .<br>
	 * @param filePath 存入的文件夹路径 .<br>
	 * @param fileName 文件名称 .<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-9-26 上午11:02:29.<br>
	 */
	public static void exportExcel(String filePath,String fileName){
		if(TITLE==null){
			Logger.getLogger(ExportExcelUtil.class).error(Configure.exportMsg.请设置首航标题.toString());
			return;
		}
		if(EXPORT_PERSON==null){
			Logger.getLogger(ExportExcelUtil.class).error(Configure.exportMsg.请设置次行标题.toString());
			return;
		}
		if(THIRD_TITLES==null){
			Logger.getLogger(ExportExcelUtil.class).error(Configure.exportMsg.请设置需要导出的列.toString());
			return;
		}
		if(RESULTS==null){
			RESULTS = new ArrayList<Map<String,Object>>();
		}
		HSSFWorkbook wkb = createWorkbook();
		outExcle(filePath,fileName, wkb);
	}
	
	

	/**
	 * @Description: 创建 HSSFWorkbook对象,并加入相关的其他信息.<br>
	 * @return HSSFWorkbook 返回对象.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午8:56:46.<br>
	 */
	private static HSSFWorkbook createWorkbook(){
		//创建HSSFWorkbook对象(excel的文档对象)
	    HSSFWorkbook wkb = new HSSFWorkbook();
	    //建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wkb.createSheet(TITLE);
		//创建第一行并且合并，设置导出excel的名称
		addTitleRowCell(sheet,THIRD_TITLES.size()-1);
		//创建第二行并且合并，设置导出excel的副名称
		addSubheadRowCell(sheet,THIRD_TITLES.size()-1);
		//创建第三行，设置需要导出的字段的列表头
		addThirdTile(sheet);
		//将数据以及通过key的形式，与表都匹配，生成需要的excel数据
		addExcelContents(sheet);
		return wkb;
	}
	
	/**
	 * @Description: 创建第一行表头列，并根据长度合并表头 ,设置第一行信息.<br>
	 * @param @param sheet 已创建的excel的单页对象.<br>   
	 * @param @param length 需要合并的长度.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午8:55:01.<br>
	 */
	private static void addTitleRowCell(HSSFSheet sheet,Integer length){
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);		
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		cell.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中  
		//设置单元格内容
		cell.setCellValue(TITLE);
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,length));
	}
	
	/**
	 * @Description: 创建第二行表头列，并根据长度合并表头,设置第二行信息 .<br>
	 * @param @param sheet 已创建的excel的单页对象.<br>   
	 * @param @param length 需要合并的长度.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午8:55:01.<br>
	 */
	private static void addSubheadRowCell(HSSFSheet sheet,Integer length){
		//在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell1=row2.createCell(0);
		cell1.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 居中  
		//设置单元格内容
		cell1.setCellValue(EXPORT_PERSON);
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,length));
	}
	
	/**
	 * @Description: 根据导入的表头设置，创建第三行表头列 .<br>
	 * @param sheet 已创建的excel的单页对象.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午8:51:34.<br>
	 */
	private static void addThirdTile(HSSFSheet sheet){
		int i = 0;
		//TODO 将list通过字段排序
		Collections.sort(THIRD_TITLES);  
		HSSFRow row3 = sheet.createRow(2);    
		//创建单元格并设置单元格内容
		for(ExcelModel em:THIRD_TITLES){
			row3.createCell(i).setCellValue(em.getFeildValue());
			i++;
		}
	}
	
	/**
	 * @Description: 将需要输出的集合存储到excel,并与导出的字段列匹配 .<br>
	 * @param sheet 已创建的excel的单页对象.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午8:51:34.<br>
	 */
	private static void addExcelContents(HSSFSheet sheet){
		int i = 3;
		for(Map<String,Object> result:RESULTS){
			HSSFRow rowi = sheet.createRow(i);
			int j = 0;
			for(ExcelModel em:THIRD_TITLES){
				for(Map.Entry<String,Object> entry : result.entrySet()){
					if(em.getFeildKey().equals(entry.getKey())){
						rowi.createCell(j).setCellValue(nullToEmpty(entry.getValue()));
					}
				}
				j++;
			}
			i++;
		}
	}
	
	/**
	 * @Description: 通过 response,将生成的excel输出.<br>
	 * @param response http响应.<br>
	 * @param wkb 已创建的excel的对象.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午8:50:02.<br>
	 */
	private static void outExcle(HttpServletResponse response,HSSFWorkbook wkb){
		try {
			OutputStream output=response.getOutputStream();
		    response.reset();
		    response.setContentType("application/x-download");//下面三行是关键代码，处理乱码问题  
		    response.setCharacterEncoding("utf-8");  
		    response.setHeader("Content-Disposition", "attachment;filename="+new String(TITLE.getBytes("gbk"), "iso8859-1")+"."+Configure.type.xls.toString()); 
		    wkb.write(output);
		    output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}    
	}
	
	/**
	 * @Description: 通过文件路径，输出文件到指定文件夹 .<br>
	 * @param filePath 文件路径.<br>
	 * @param fileName 文件名.<br>
	 * @param wkb 已创建的excel的对象.<br>   
	 * @author 郑成功 .<br>
	 * @date 2017-9-26 上午11:05:08.<br>
	 */
	private static void outExcle(String filePath,String fileName,HSSFWorkbook wkb){
		try {
			String fullPath = filePath+fileName+"."+Configure.type.xls.toString();
			FileOutputStream output = new FileOutputStream(fullPath);
		    wkb.write(output);
		    output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 将空的对象转换成空字符串 .<br>
	 * @param obj 需要转换的对象.<br>   
	 * @return String 返回的字符串.<br> 
	 * @throws .<br>
	 * @author 郑成功 .<br>
	 * @date 2017-8-29 上午8:48:48.<br>
	 */
	private static String nullToEmpty(Object obj){
		if(obj==null){
			return "";
		}
		return String.valueOf(obj);
	}
	
	public static void main(String[] args) {
		//createWorkbook();
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", "姓名");
		map.put("class", "班级");
		map.put("biscj", "笔试成绩");
		map.put("jscj", "成绩");
		List<ExcelModel> titles = MapToExcelModelList.mapToExcelModels(map);
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("name", "郑成功");
		map1.put("class", "09（8）班");
		map1.put("biscj", "96");
		map1.put("jscj", "58");
		
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		results.add(map1);
		ExportExcelUtil.setTitle("学生表");
		ExportExcelUtil.setSecTitle("导出人：郑成功");
		ExportExcelUtil.setThirdTitles(titles);
		ExportExcelUtil.exportExcel("d:/","test");
	}
	
}
