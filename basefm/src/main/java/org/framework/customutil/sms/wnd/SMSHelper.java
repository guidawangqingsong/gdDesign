package org.framework.customutil.sms.wnd;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.framework.customutil.sms.MapToBean;
import org.framework.customutil.sms.SMSConfig;

/**
 * @Title: SMSHelper.java .<br>
 * @Package org.framework.customutil.sms.wnd .<br>
 * @Description: 维纳多短信工具类，该类需要引用 commons-httpclient-3.0.1.jar包.<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-9-19 上午9:50:56.<br>
 * @version V1.0.<br>
 */
public class SMSHelper extends Config{
	
	/**注册的用户名  */
	private String account;
	/**登录网站使用的密钥  */
	private String password;
	
	/**
	 * @Description: 初始化维纳多短信构造器.<br>
	 * @params account 维纳多用户名.<br>
	 * @params password 维纳多密码.<br>
	 */
	public SMSHelper(String account,String password){
		super();
		this.account = account;
		this.password = password;
	}

	/**
	 * @Description: 通过接口,发送短信 .<br>
	 * @param phones 电话号码,多个电话号码用逗号隔开.<br>
	 * @param msg 发送的消息体.<br>
	 * @param sequeid 唯一标识.<br>
	 * @return SMSBean 返回消息结果.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午9:32:35.<br>
	 */
	public SMSBean sendMsg(String phones,String msg,String sequeid){
		try {
			HttpClient client = new HttpClient(); 
			NameValuePair[] data = setData(phones,msg,sequeid);
			PostMethod post = setPostMethod(data,GSEND);
			client.executeMethod(post);
			//Header[] headers = post.getResponseHeaders();  
			//int statusCode = post.getStatusCode();  
			//for (Header h : headers) {     	
			//}  
			String result = new String(post.getResponseBodyAsString());  
			SMSBean msgBean = getMsgBean(result);
			post.releaseConnection();  
			return msgBean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @Description: 接收短信的方法 .<br>
	 * @param recvUrl 接收短信的地址,即公司自己的短信接收地址（例如：http://www.test.com/smsController.do?recv）.<br>
	 * @return String 返回接收短信是成功或者失败.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午10:08:32.<br>
	 */
	public String recv(String recvUrl){
		HttpClient client = new HttpClient(); 
		try {
			PostMethod post = new PostMethod(recvUrl);
			post.addRequestHeader(ContentType,ConTypeValue);// 在头文件中设置转码  
			client.executeMethod(post);
			//int statusCode = post.getStatusCode();		
			post.releaseConnection();  
			return SMSConfig.signType.success.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return SMSConfig.signType.fail.toString();
		}
	}
	
	
	/**
	 * 功能：获取短信余额数</p>
	 * @Title: getSMSbalance <p>获取短信余额数</p>
	 * @return <p>返回短信余额</p>
	 * @throws Exception <p>抛出异常信息</p>
	 */
	public int getSMSbalance(){
		HttpClient client = new HttpClient(); 
		try {
			NameValuePair[] data = setNameAndPws();
			PostMethod post = setPostMethod(data,GETFEE);
			client.executeMethod(post);
			String result = new String(post.getResponseBodyAsString());  
	        SMSBean msgbean = getMsgBean(result);
	        post.releaseConnection();  
			return Integer.valueOf(msgbean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * @Description: 修改维纳多密码 .<br>
	 * @param newpws 新密码.<br>
	 * @return String 返回处理结果信息.<br> 
	 * @throws Exception 抛出异常.<br>
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午9:44:40.<br>
	 */
	public String updatecpwd(String newpws){
		HttpClient client = new HttpClient();  
		NameValuePair[] data = setNewpwd(newpws);
		PostMethod post = setPostMethod(data,CPWD);
		try {
			client.executeMethod(post);
			String result = new String(post.getResponseBodyAsString());
			SMSBean msgbean = getMsgBean(result);
	        post.releaseConnection();  
			return msgbean.getErr();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @Description: 修改密码，设置新密码到请求头里面 .<br>
	 * @param newpwd 新的密码.<br>
	 * @return NameValuePair[] .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午9:46:17.<br>
	 */
	private NameValuePair[] setNewpwd(String newpwd){
		NameValuePair[] data = { new NameValuePair(feild.name.toString(), account),
                new NameValuePair(feild.pwd.toString(), password), 
                new NameValuePair(feild.newpwd.toString(), newpwd)};
		return data;
	}
	
	/**
	 * @Description: 设置维纳多的账号和密码 .<br>
	 * @return NameValuePair[] .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午9:47:34.<br>
	 */
	private NameValuePair[] setNameAndPws(){
		NameValuePair[] data = { new NameValuePair(feild.name.toString(), account), // 注册的用户名  
                new NameValuePair(feild.pwd.toString(), password) }; // 注册成功后,登录网站使用的密钥      
		return data;
	}
	
	/**
	 * @Description: 将数据加载到请求头信息里面 .<br>
	 * @param data 传递到请求头的.<br>
	 * @param method 请求处理的方式（发送短信、获取短信条数等）.<br>
	 * @return PostMethod 返回post提交请求.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午9:24:53.<br>
	 */
	private static PostMethod setPostMethod(NameValuePair[] data,String method){
		PostMethod post = new PostMethod(URL+method);  
	    post.addRequestHeader(ContentType,ConTypeValue);// 在头文件中设置转码  
	    post.setRequestBody(data);
		return post;
	}

	/**
	 * @Description: 设置需要的参数和电话号码 .<br>
	 * @param phones 电话号码,多个电话号码用逗号隔开.<br>
	 * @param msg 发送的消息体.<br>
	 * @param sequeid 唯一标识.<br>
	 * @return NameValuePair[] .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午9:10:24.<br>
	 */
	private NameValuePair[] setData(String phones,String msg,String sequeid){
		if(sequeid==null){
			sequeid = UUID.randomUUID().toString();
		}
		NameValuePair[] data = { new NameValuePair(feild.name.toString(), account),
                new NameValuePair(feild.pwd.toString(), password),
                new NameValuePair(feild.dst.toString(), phones),
                new NameValuePair(feild.sequeid.toString(), sequeid),
                new NameValuePair(feild.msg.toString(), msg) };
		
		return data;
	}
	
	/**
	 * @Description: 将返回的结果转换成实体 .<br>
	 * @param result 需要转换的结果字符串.<br>
	 * @return SMSBean 转换成功的实体类.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午9:18:01.<br>
	 */
	private SMSBean getMsgBean(String result){
		Map<String, Object> map = new HashMap<String, Object>();
		SMSBean msg = new SMSBean();
		String[] results = result.split(AND);
		for(String s:results){
        	String[] ms = s.split(EQUALS);
        	if(ms.length>1){
        		map.put(ms[0], ms[1]);
        	}else{
        		map.put(ms[0], null);
        	}
        }		
		MapToBean.setValue(map, msg);		
		return msg;		
	}
	
	/**
	 * @Description: 测试发送短信 .<br>
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-19 上午11:46:14.<br>
	 */
	public static void main(String[] args) {
		SMSHelper sms = new SMSHelper("gzjjxx", "gzjjxx520");
		sms.sendMsg("15285112398", "测试短信发送", "11111");
		System.out.println(sms.getSMSbalance());
	}
}
