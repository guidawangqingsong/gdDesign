package org.framework.customutil.email;

import java.io.File;  
import java.util.Vector;  

/**
 * @Title: SendMail.java .<br>
 * @Package org.framework.core.utils.email .<br>
 * @Description: TODO .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-31 下午2:23:01.<br>
 * @version V1.0.<br>
 */
public class SendMail {  
	
	/**
     * @Description: 发送邮件,选择不同的类型 .<br>
     * @param @param mailInfo 发送的消息载体 .<br>
     * @param @param type 消息发送类型（MailConfig.Type）.<br>
     * @return boolean 是否成功发送.<br> 
     * @author 郑成功 .<br>
     * @date 2017-8-31 上午11:57:10.<br>
     */
    public static boolean sendEmail(MailSenderInfo mailInfo,Enum<?> type){
    	// 这个类主要来发送邮件  
        SimpleMailSender sms = new SimpleMailSender();
        if(mailInfo==null){
        	return false;
        }
        if(MailConfig.Type.TEXT.equals(type)){
        	return sms.sendTextMail(mailInfo);
        }else if(MailConfig.Type.HTML.equals(type)){
        	return sms.sendHtmlMail(mailInfo);
        }
        return false;
    }
	
    /**
     * @Description: 测试邮件发送 .<br>
     * @param @param args .<br>   
     * @return void .<br> 
     * @author 郑成功 .<br>
     * @date 2017-8-31 下午2:38:39.<br>
     */
    public static void main(String[] args) {  
        try {  
            MailSenderInfo mailInfo = new MailSenderInfo();  
            // 设置邮件服务器类型  
            mailInfo.setMailServerHost("smtp.163.com");  
            //设置端口号  
            mailInfo.setMailServerPort("25");  
            //设置是否验证  
            mailInfo.setValidate(true);  
            //设置用户名、密码、发送人地址  
            mailInfo.setUserName("gzjjxxkj123@163.com");  
            mailInfo.setPassword("gzjjxxkj123@");// 您的邮箱密码  
            mailInfo.setFromAddress("gzjjxxkj123@163.com");
            mailInfo.setNickName("江哥");
            /** 
             * 附件 
             */  
            Vector<File> file = new Vector<File>() ;  
            File f1 = new File("E:\\mysql\\share\\czech\\errmsg.sys") ;             
            file.add(f1) ;            
            mailInfo.setFile(file) ;  
            /** 
             * 收件人邮箱 
             */  
            String[] mailToAddress = {"a876459952@qq.com"} ;  
            mailInfo.setToAddress(mailToAddress);  
            mailInfo.setSubject("测试");  
            mailInfo.setContent("注册地址 <a href=\"www.baidu.com\" target='_blank'>百度</a>");  
            

            if(sendEmail(mailInfo,MailConfig.Type.TEXT)){  
                System.out.println("mail send True!");  
            }// 发送html格式  
            else{  
                System.out.println("mail send False!");  
            }  
        } catch (Exception e) {  
            e.printStackTrace() ;  
        }  
    } 
    
}