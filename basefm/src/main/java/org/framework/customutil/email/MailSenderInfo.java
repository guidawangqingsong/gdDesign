package org.framework.customutil.email;

import java.io.File;
import java.util.Properties;  
import java.util.Vector;  
import javax.mail.internet.MimeUtility;  

/**
 * @Title: MailSenderInfo.java .<br>
 * @Package org.framework.core.utils.email .<br>
 * @Description: 发送邮件的信息类   .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-31 上午11:17:05.<br>
 * @version V1.0.<br>
 */
public class MailSenderInfo {      
    /** 发送邮件的服务器的IP*/
    private String mailServerHost; 
    /** 发送邮件的服务器端口*/
    private String mailServerPort = "25";      
    /** 邮件发送者的地址*/      
    private String fromAddress;      
    /** 邮件接收者的地址 */     
    private String[] toAddress;      
    /** 登陆邮件发送服务器的用户名*/      
    private String userName; 
    /** 登陆邮件发送服务器的密码*/    
    private String password;
    /** 发送邮件人的昵称*/
    private String nickName;
    /** 是否需要身份验证 */ 
    private boolean validate = false;      
    /** 邮件主题*/      
    private String subject;      
    /** 邮件的文本内容*/      
    private String content;      
    /** 邮件附件的文件名 */     
    private String fileName = "" ;      
    /**附件文件集合 */ 
    private Vector<File> file = new Vector<File>() ;  
      
    /**  
     * 获得邮件会话属性     
     * @return  
     */  
    public Properties getProperties(){      
      Properties p = new Properties();      
      p.put("mail.smtp.host", this.mailServerHost);      
      p.put("mail.smtp.port", this.mailServerPort);      
      p.put("mail.smtp.auth", validate ? "true" : "false");      
      return p;      
    }  
    
    /**
     * @Description: 获取 发送邮件的服务器的IP .<br>
     * @return String .<br> 
     */
    public String getMailServerHost() {      
      return mailServerHost;      
    } 
    /**
     * @Description: 设置 发送邮件的服务器的IP .<br>
     * @return String .<br> 
     */
    public void setMailServerHost(String mailServerHost) {      
      this.mailServerHost = mailServerHost;      
    }     
    /**
     * @Description: 获取 发送邮件的服务器端口 .<br>
     * @return String .<br> 
     */
    public String getMailServerPort() {      
      return mailServerPort;      
    } 
    /**
     * @Description: 设置 发送邮件的服务器端口 .<br>
     * @return String .<br> 
     */
    public void setMailServerPort(String mailServerPort) {      
      this.mailServerPort = mailServerPort;      
    } 
    /**
     * @Description: 获取 是否需要身份验证 .<br>
     * @return String .<br> 
     */
    public boolean isValidate() {      
      return validate;      
    }
    /**
     * @Description: 设置 是否需要身份验证 .<br>
     * @return String .<br> 
     */
    public void setValidate(boolean validate) {      
      this.validate = validate;      
    } 
    /**
     * @Description: 获取 邮件发送者的地址 .<br>
     * @return String .<br> 
     */
    public String getFromAddress() {      
      return fromAddress;      
    }
    /**
     * @Description: 设置 邮件发送者的地址 .<br>
     * @return String .<br> 
     */
    public void setFromAddress(String fromAddress) {      
      this.fromAddress = fromAddress;      
    }  
    /**
     * @Description: 获取 登陆邮件发送服务器的密码 .<br>
     * @return String .<br> 
     */
    public String getPassword() {      
      return password;      
    } 
    /**
     * @Description: 设置 登陆邮件发送服务器的密码 .<br>
     * @return String .<br> 
     */
    public void setPassword(String password) {      
      this.password = password;      
    }
    /**
     * @Description: 获取 发送邮件人的昵称 .<br>
     * @return String .<br> 
     */
    public String getNickName() {
		return nickName;
	}
    /**
     * @Description: 设置 发送邮件人的昵称 .<br>
     * @return String .<br> 
     */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
     * @Description: 获取 邮件接收者的地址 .<br>
     * @return String .<br> 
     */
    public String[] getToAddress() {      
      return toAddress;      
    }
    /**
     * @Description: 设置 邮件接收者的地址 .<br>
     * @return String .<br> 
     */
    public void setToAddress(String[] toAddress) {      
      this.toAddress = toAddress;      
    } 
    /**
     * @Description: 获取 登陆邮件发送服务器的用户名 .<br>
     * @return String .<br> 
     */
    public String getUserName() {      
      return userName;      
    }
    /**
     * @Description: 设置 登陆邮件发送服务器的用户名 .<br>
     * @return String .<br> 
     */
    public void setUserName(String userName) {      
      this.userName = userName;      
    } 
    /**
     * @Description: 获取 邮件主题 .<br>
     * @return String .<br> 
     */
    public String getSubject() {      
      return subject;      
    }
    /**
     * @Description: 设置 邮件主题 .<br>
     * @return String .<br> 
     */
    public void setSubject(String subject) {      
      this.subject = subject;      
    }
    /**
     * @Description: 获取 邮件的文本内容 .<br>
     * @return String .<br> 
     */
    public String getContent() {      
      return content;      
    } 
    /**
     * @Description: 设置 邮件的文本内容 .<br>
     * @return String .<br> 
     */
    public void setContent(String textContent) {      
      this.content = textContent;      
    } 
    /**
     * @Description: 获取 邮件附件的文件名 .<br>
     * @return String .<br> 
     */
    public String getFileName() {  
        return fileName;  
    }
    /**
     * @Description: 设置 邮件附件的文件名 .<br>
     * @return String .<br> 
     */
    public void setFileName(String fileName) {  
        this.fileName = fileName;  
    }  
    /**
     * @Description: 获取 附件文件集合 .<br>
     * @return String .<br> 
     */
    public Vector<File> getFile() {  
        return file;  
    } 
    /**
     * @Description: 设置  附件文件集合 .<br>
     * @return String .<br> 
     */
    public void setFile(Vector<File> file) {  
        this.file = file;  
    }  
    
    /**
     * @Description: 方法说明：把主题转换为中文    .<br>
     * @param @param strText c传入的字符串.<br>
     * @return String 转码后的字符串.<br> 
     * @author 郑成功 .<br>
     * @date 2017-8-31 上午11:08:01.<br>
     */
    public static String transferChinese(String strText) {      
        try {      
            strText = MimeUtility.encodeText(new String(strText.getBytes(),"GB2312"), "GB2312", "B");      
        } catch (Exception e) {      
            e.printStackTrace();      
        }      
        return strText;      
    }    
}     