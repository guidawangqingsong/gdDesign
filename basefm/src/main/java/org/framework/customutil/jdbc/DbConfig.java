package org.framework.customutil.jdbc;

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  

import org.apache.ibatis.io.Resources;
   
/** 
 * 数据库配置文件读取方法 
 * @author WANGYAN 
 * 
 */  
public class DbConfig {  
       
	private String resource = "jdbc.properties";
    private String driver;  
    private String url;  
    private String userName;  
    private String password;  
       
    public DbConfig() throws IOException {  
        InputStream inputStream = Resources.getResourceAsStream(resource);
        Properties p=new Properties();  
        try {  
            p.load(inputStream);  
            this.driver=p.getProperty("driver");  
            this.url=p.getProperty("url");  
            this.userName=p.getProperty("username");  
            this.password=p.getProperty("password");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
           
    }  
       
    public String getDriver() {  
        return driver;  
    }  
    public String getUrl() {  
        return url;  
    }  
    public String getUserName() {  
        return userName;  
    }  
    public String getPassword() {  
        return password;  
    }  
   
}  
