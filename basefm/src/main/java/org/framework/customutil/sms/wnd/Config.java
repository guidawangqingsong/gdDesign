package org.framework.customutil.sms.wnd;

public class Config {
	
	protected final static String AND = "&";
	
	protected final static String EQUALS = "=";
	
	/** 短信调用的网址 */
	public final static String URL = "http://yl.mobsms.net";
	/** 发送短信 */
	public final static String GSEND = "/send/gsend.aspx";	
	/** 获得用户信息及余额  */
	public final static String GETFEE = "/send/getfee.aspx";
	/** 修改密码   */
	public final static String CPWD = "/send/cpwd.aspx";
	/** 被动接受信息地址   */
	public final static String RECEIVE_URL = "";
	/** 成功的信息 */
	public final static int SUCCESS = 0;
	

	/** 用户帐号不正确 */
	private final static String ERROR_6002 = "6002";
	/** 无效的手机号码 */
	private final static String ERROR_6008 = "6008";
	/** 手机号码是黑名单 */
	private final static String ERROR_6009 = "6009";
	/** 用户密码不正确 */
	private final static String ERROR_6010 = "6010";
	/** 短信内容超过了最大长度限制 */
	private final static String ERROR_6011 = "6011";
	/** 该企业用户设置了 ip 限制 */
	private final static String ERROR_6012 = "6012";
	/** 该企业用户余额不足 */
	private final static String ERROR_6013 = "6013";
	/** 发送短信内容不能为空 */
	private final static String ERROR_6014 = "6014";
	/** 发送内容中含非法字符 */
	private final static String ERROR_6015 = "6015";
	/** 账户已停机，请联系客服 */
	private final static String ERROR_6019 = "6019";
	/** 扩展号码未备案 */
	private final static String ERROR_6021 = "6021";
	/** 发送手机号码超过太长，已超过 300 个号码 */
	private final static String ERROR_6023 = "6023";
	/** 定制时间不正确 */
	private final static String ERROR_6024 = "6024";
	/** 扩展号码太长（总长度超过 20 位） */
	private final static String ERROR_6025 = "6025";
	/**设置请求头信息*/
	public final static String ContentType = "Content-Type";
	/**设置请求头编码信息*/
	public final static String ConTypeValue = "application/x-www-form-urlencoded;charset=GB2312";
	
	/**
	 * @Title: Config.java .<br>
	 * @Package org.framework.customutil.sms.wnd .<br>
	 * @Description: 定义维纳多短信需要的字段名 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-19 上午9:42:27.<br>
	 * @version V1.0.<br>
	 */
	public enum feild{
		name,pwd,dst,sequeid,msg,newpwd
	}

	/**
	 * @Title: Config.java .<br>
	 * @Package org.framework.customutil.sms.wnd .<br>
	 * @Description: 维纳多短信错误信息提醒 .<br>
	 * @author 郑成功 .<br>
	 * @email a876459952@qq.com .<br>
	 * @date 2017-9-18 下午5:06:35.<br>
	 * @version V1.0.<br>
	 */
	protected enum ErrorMsg{
		用户帐号不正确,无效的手机号码,手机号码是黑名单,用户密码不正确,短信内容超过了最大长度限制,该企业用户设置了IP限制,
		该企业用户余额不足,发送短信内容不能为空,发送内容中含非法字符,账户已停机请联系客服,扩展号码未备案 ,发送手机号码超过太长已超过300个号码,
		定制时间不正确, 扩展号码太长总长度超过20位,错误
	}
	
	/**
	 * @Description: 根据状态标识，返回短信的错误信息 .<br>
	 * @param errorCode 错误状态标识.<br>
	 * @return String 返回错误信息.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-9-18 下午5:05:41.<br>
	 */
	public static String returnErrorInfo(String errorCode){
		String errorMsg;
		switch (errorCode) {
		case ERROR_6002:
			errorMsg = ErrorMsg.用户帐号不正确.toString();
			break;
		case ERROR_6008:
			errorMsg = ErrorMsg.无效的手机号码.toString();
			break;
		case ERROR_6009:
			errorMsg = ErrorMsg.手机号码是黑名单.toString();
			break;
		case ERROR_6010:
			errorMsg = ErrorMsg.用户密码不正确.toString();
			break;
		case ERROR_6011:
			errorMsg = ErrorMsg.短信内容超过了最大长度限制.toString();
			break;
		case ERROR_6012:
			errorMsg = ErrorMsg.该企业用户设置了IP限制.toString();
			break;
		case ERROR_6013:
			errorMsg = ErrorMsg.该企业用户余额不足.toString();
			break;
		case ERROR_6014:
			errorMsg = ErrorMsg.发送短信内容不能为空.toString();
			break;
		case ERROR_6015:
			errorMsg = ErrorMsg.发送内容中含非法字符.toString();
			break;
		case ERROR_6019:
			errorMsg = ErrorMsg.账户已停机请联系客服.toString();
			break;
		case ERROR_6021:
			errorMsg = ErrorMsg.扩展号码未备案.toString();
			break;
		case ERROR_6023:
			errorMsg = ErrorMsg.发送手机号码超过太长已超过300个号码.toString();
			break;
		case ERROR_6024:
			errorMsg = ErrorMsg.定制时间不正确.toString();
			break;
		case ERROR_6025:
			errorMsg = ErrorMsg.扩展号码太长总长度超过20位.toString();
			break;
		default:
			errorMsg = ErrorMsg.错误.toString();
			break;
		}
		return errorMsg;
	}
}
