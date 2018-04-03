package org.framework.customutil.email;

import javax.mail.Authenticator;  
import javax.mail.PasswordAuthentication;  
/**
 * @Title: MyAuthenticator.java .<br>
 * @Package org.framework.core.utils.email .<br>
 * @Description: 发邮件的身份验证器  .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-31 上午11:01:53.<br>
 * @version V1.0.<br>
 */
public class MyAuthenticator extends Authenticator{     
  String userName=null;     
  String password=null;     
        
  public MyAuthenticator(){     
      
  }     
  public MyAuthenticator(String username, String password) {      
      this.userName = username;      
      this.password = password;      
  }    
  protected PasswordAuthentication getPasswordAuthentication(){     
      return new PasswordAuthentication(userName, password);     
  }     
}     
