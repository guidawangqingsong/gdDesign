package cn.jjxx.modules.codegen.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.codegen.codegenerator.data.DbTableInfo;
import cn.jjxx.modules.codegen.codegenerator.data.GeneratorInfo;
import cn.jjxx.modules.codegen.codegenerator.exception.GenerationException;
import cn.jjxx.modules.codegen.entity.Table;
import cn.jjxx.modules.sys.entity.Menu;
import freemarker.template.TemplateException;

public interface ITableService extends ICommonService<Table> {

	/**
	 * 获得表列表
	 * 
	 * @return
	 */
	public List<DbTableInfo> getTableNameList();

	/**
	 * 代码生成
	 * 
	 * @title: doGenerateCode
	 * @description:代码生成
	 * @return: void
	 */
	public void generateCode(Table table, GeneratorInfo generatorInfo) throws IOException, GenerationException;

	/**
	 * 代码生成
	 * 
	 * @title: doGenerateCode
	 * @description:代码生成
	 * @return: void
	 */
	public void createMenu(Table table, Menu menu);

	/**
	 * 代码生成
	 * 
	 * @title: doGenerateCode
	 * @description:代码生成
	 * @return: void
	 */
	public void importDatabase(Table table);

	public void dropTable(String tableid);

	/**
	 * 数据库生成
	 * 
	 * @title: syncDatabase
	 * @description:数据库生成
	 * @return: void
	 */
	public void syncDatabase(String tableid) throws TemplateException, IOException;

	public void removeById(Serializable id);

	public List<Table> findSubTable(String tablename);
}
