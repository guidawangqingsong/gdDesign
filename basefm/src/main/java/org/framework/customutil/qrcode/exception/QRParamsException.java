package org.framework.customutil.qrcode.exception;

/**
 * @Title: QRParamsException.java .<br>
 * @Package org.framework.customutil.qrcode.exception .<br>
 * @Description: 二维码异常类，处理二维码的异常信息 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-31 下午3:32:53.<br>
 * @version V1.0.<br>
 */
public class QRParamsException extends Exception {  
	
    private static final long serialVersionUID = 8837582301762730656L; 
    
    /**
     * @Description: 用来创建无参数对象.<br>
     * @params  .<br>
     */
    public QRParamsException()  {
    	
    } 
    
    /**
     * @Description: 用来创建指定参数对象 ，调用超类构造器  .<br>
     * @params message 传递的参数.<br>
     */
    public QRParamsException(String message) {
        super(message);                          
    }  
}  

