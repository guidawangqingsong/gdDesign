package cn.jjxx.modules.activiti.sqlsession;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;

public class CustomDbSqlSession extends DbSqlSession {

	public CustomDbSqlSession(DbSqlSessionFactory dbSqlSessionFactory) {
		super(dbSqlSessionFactory);
	}
	
	protected void dbSchemaCreateEngine() {
		super.dbSchemaCreateEngine();
		executeMandatorySchemaResource("custom", "custom");
	}
	
	
	public String getResourceForDbOperation(String directory, String operation,String component) {
		String databaseType = dbSqlSessionFactory.getDatabaseType();
		if (operation.equals("shareniu")) {
			return "cn/jjxx/modules/activiti/sqlsession/custom/init.sql";
		}else {
			return "org/activiti/db/" + directory + "/activiti." + databaseType
					+ "." + operation + "." + component + ".sql";
		}
	}
}
