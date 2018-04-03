package cn.jjxx.modules.activiti.sqlsession;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.interceptor.Session;

public class CustomDbSqlSessionFactory extends DbSqlSessionFactory {

	public Class<?> getSessionType() {
		return DbSqlSession.class;
	}
	
	
	public Session openSession() {
		return new CustomDbSqlSession(this);
	}
}
